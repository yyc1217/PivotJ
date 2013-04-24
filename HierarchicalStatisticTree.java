
import java.util.ArrayList;
import java.util.List;

public class HierarchicalStatisticTree<K> implements Comparable<K>, IHierarchicalStatisticTree<K> {	
	private K key;
	private Integer count;
	private ArrayList<HierarchicalStatisticTree<K>> children;
	
	public HierarchicalStatisticTree(){
		this.key = null;
		this.count = 0; 
		this.children = new ArrayList<HierarchicalStatisticTree<K>>();
	}
	
	public HierarchicalStatisticTree(K key){
		this.key = key;
		this.count = 0;
		this.children = new ArrayList<HierarchicalStatisticTree<K>>();
	}

	/**
	 * Building a HST recursively.
	 * @param kArray
	 * @param index
	 * @param num
	 */
	private void buildBySum(K[] kArray, int index, int num) {
		//Stopped by out of bound or null value
		//and assume that the last one in kArray is the number we want to accumulate.
		if(index >= (kArray.length-1) || kArray[index]==null)
			return;
		
		//Search the tempHST in children
		HierarchicalStatisticTree<K> tempHST = new HierarchicalStatisticTree<K>(kArray[index]);		
		int indexOfChildren = this.children.indexOf(tempHST);
				
		if(indexOfChildren >= 0){
			//If exist
			tempHST = this.children.get(indexOfChildren);
			tempHST.incrSum(num);
			tempHST.buildBySum(kArray, ++index, num);
		}else{
			//If not exist
			tempHST.incrSum(num);
			tempHST.buildBySum(kArray, ++index, num);
			this.children.add(tempHST);
		}

	}
	
	/**
	 * Building a HST recursively which assume the accumulate value is the last
	 * element in kArray. 
	 * @param list
	 */
	public void buildTreeBySum(List<K[]> list){
		
		for(K[] kArray: list){
			//assume that the last one in kArray is the number we want to accumulate.
			int num = this.getNum(kArray);
			this.incrSum(num);
			this.buildBySum(kArray, 0, num);
		}
	}

	/**
	 * get the value we want to accumulate
	 * you may override this method by different number type.
	 * @param kArray
	 * @return
	 */
	private java.lang.Number getNum(K[] kArray){
		return (Integer) kArray[kArray.length-1];
	}
	
	private void incrSum(int num){
		this.count += num;
	}
	
	/**
	 * Get the accumulate value by input a serial or keys.
	 * @param kArray
	 * @return
	 */
	public Integer getSum(K[] kArray){

		if(kArray==null || kArray.length<=0)
			return this.count;
		
		HierarchicalStatisticTree<K> tempHST = new HierarchicalStatisticTree<K>(kArray[0]);
		//Searching tempHST is in children or not
		for(HierarchicalStatisticTree<K> HST: this.children){
			//If exist
			if(tempHST.equals(HST)){
				//Start to searching accumulate value from this PivotTable
				return HST.sum(kArray, 0);
			}
		}
		//If not exist
		return 0;
	}
	
	private Integer sum(K[] kArray, int index){
		//Stopped by out of bound or null value
		if(index >= kArray.length || kArray[index]==null)
			return 0;
		
		//Search by tempHST
		HierarchicalStatisticTree<K> tempHST = new HierarchicalStatisticTree<K>(kArray[index]);
		
		//If tempHST equals current PivotTable
		if(tempHST.equals(this)){
			//looking for next key value
			index++;
			if(index >= kArray.length){
				//Stopped by out of bound
				return this.count;
			}else if(kArray[index]==null){
				//if next one is null value, then this key not exist
				return 0;
			}else{
				//searching next key value in children
				tempHST = new HierarchicalStatisticTree<K>(kArray[index]);
				int indexOfChildren = this.children.indexOf(tempHST);
				if(indexOfChildren >= 0){
					//if exist
					return this.children.get(indexOfChildren).sum(kArray, index);
				}else{
					//if not exist
					return 0;
				}
			}
		}
		return 0;
	}
	
	@Override
	public String toString(){
		if(this.key==null)
			return "[TOTAL:".concat(this.count.toString()) + "]";
		else
			return "[" + this.key.toString().concat(":").concat(this.count.toString()) + "]";
	}

	@SuppressWarnings("unchecked")
	public boolean equals(Object obj) {
		if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        return this.key.equals(((HierarchicalStatisticTree<K>) obj).getKey());
 	}
	
	private void buildByCount(List<K> list, int index) {
		//Stopped by out of bound or null value
		if(index >= (list.size()) || list.get(index)==null)
			return;
		
		//Search the key in children
		K tempKey = list.get(index);
		int indexOfChildren = this.children.indexOf(new HierarchicalStatisticTree<K>(tempKey));
		
		if(indexOfChildren >= 0){
			//If exist
			HierarchicalStatisticTree<K> hst = this.children.get(indexOfChildren);
			hst.incrCount();
			hst.buildByCount(list, ++index);
		}else{
			//If not exist
			HierarchicalStatisticTree<K> hst = new HierarchicalStatisticTree<K>(tempKey);
			hst.incrCount();
			hst.buildByCount(list, ++index);
			this.children.add(hst);
		}
	
	}

	public void buildTreeByCount(List<List<K>> list) {
		this.incrCount();
		for(List<K> klist:list){
			this.buildByCount(klist, 0);
		}
	}

	private void incrCount(){
		this.count+=1;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int compareTo(K o) {
		return ((Comparable<K>)this.key).compareTo((K)o);
	}

	@Override
	public void printTree() {
		System.out.println(this.toString());
		for(HierarchicalStatisticTree<K> hst: this.children){
			hst.print(this.toString());
		}
		
	}

	private void print(String parent){
		System.out.println(parent + this.toString());
		for(HierarchicalStatisticTree<K> hst: this.children){
			hst.print(parent + this.toString());
		}
	}
	
	@Override
	public void addBySum(K[] list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addByCount(K[] list) {
		// TODO Auto-generated method stub
		
	}

}
