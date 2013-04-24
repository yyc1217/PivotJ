import java.util.ArrayList;
import java.util.List;


public class SumTree<K> extends AbstractHierarchicalStatisticTree<K> implements IHierarchicalStatisticTree<K> {

	ArrayList<SumTree<K>> children;
	
	public SumTree(){
		super();
		this.children = new ArrayList<SumTree<K>>();
	}
	
	public SumTree(K key) {
		this();
		this.key = key;
	}

	@Override
	public void buildTree(List<K[]> list) {
		
		for(K[] kArray: list){
			//assume that the last one in kArray is the number we want to accumulate.
			int num = (Integer) this.getNum(kArray);
			this.incrSum(num);
			this.buildBySum(kArray, 0, num);
		}
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
		SumTree<K> tempHST = new SumTree<K>(kArray[index]);		
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
	
	@Override
	public void add(K[] list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public java.lang.Number getResult(K[] kArray) {
		
		if(kArray==null || kArray.length<=0)
			return this.getCount();
		
		IHierarchicalStatisticTree<K> tempHST = new SumTree<K>(kArray[0]);
		//Searching tempHST is in children or not
		for(SumTree<K> HST: this.children){
			//If exist
			if(tempHST.equals(HST)){
				//Start to searching accumulate value from this PivotTable
				return HST.sum(kArray, 0);
			}
		}
		//If not exist
		return 0;
	}
	
	private void incrSum(int num){
		this.count += num;
	}

	private Integer sum(K[] kArray, int index){
		//Stopped by out of bound or null value
		if(index >= kArray.length || kArray[index]==null)
			return 0;
		
		//Search by tempHST
		SumTree<K> tempHST = new SumTree<K>(kArray[index]);
		
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
				tempHST = new SumTree<K>(kArray[index]);
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
	public void printTree() {
		System.out.println(this.toString());
		for(SumTree<K> hst: this.children){
			hst.print(this.toString());
		}
		
	}

	private void print(String parent){
		System.out.println(parent + this.toString());
		for(SumTree<K> hst: this.children){
			hst.print(parent + this.toString());
		}
	}
	
	/**
	 * get the value we want to accumulate
	 * you may override this method by different number type.
	 * @param kArray
	 * @return
	 */
	private java.lang.Number getNum(K[] kArray){
		return (java.lang.Number) kArray[kArray.length-1];
	}
}
