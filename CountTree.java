import java.util.ArrayList;
import java.util.List;


public class CountTree<K> extends AbstractHierarchicalStatisticTree<K> implements IHierarchicalStatisticTree<K> {

	/**
	 * maintain all children belongs to this SumTree node
	 */
	private ArrayList<CountTree<K>> children;
	
	public CountTree(){
		super();
		children = new ArrayList<CountTree<K>>();
	}
	
	public CountTree(K key) {
		this();
		this.key = key;
	}
	
	public CountTree(K[] kArray){
		this();
		add(kArray);
	}
	
	public CountTree(List<K[]> list){
		this();
		buildTree(list);
	}
	
	/**
	 * Starting point to print whole tree
	 */
	@Override
	public void printTree() {
		System.out.println(toString());
		for(CountTree<K> hst: children){
			hst.print(toString());
		}
	}

	/**
	 * Printing whole tree recursively.
	 * @param parent
	 */
	private void print(String parent){
		
		System.out.println(parent + toString());
		
		for(CountTree<K> hst: children){
			hst.print(parent + toString());
		}
	}

	@Override
	public void buildTree(List<K[]> list) {
		
		for(K[] kArray: list){
			incrCount();
			buildByCount(kArray, 0);
		}
		
	}

	@Override
	public void add(K[] kArray) {
		incrCount();
		buildByCount(kArray, 0);
		
	}

	@Override
	public Integer getResult(K[] kArray) {
		if(kArray==null || kArray.length<=0)
			return getCount();
		
		IHierarchicalStatisticTree<K> tempSumTree = new CountTree<K>(kArray[0]);
		
		//Searching tempHST
		for(CountTree<K> countTree: children){
			if(tempSumTree.equals(countTree)){
				//Start to searching accumulated value from this SumTree
				return countTree.getCount(kArray, 0);
			}
		}

		return 0;		//If not exist
	}
	
	private Integer getCount(K[] kArray, int index){
		//Stopped by out of bound or null value
		if(index > kArray.length || kArray[index]==null)
			return 0;
		
		CountTree<K> tempCountTree = new CountTree<K>(kArray[index]);
		
		if(tempCountTree.equals(this)){
			index++;						//looking for next key value
			
			if(index >= kArray.length){
				return count;				//match all key value, return result
			
			}else if(kArray[index]==null){
				return 0;					//if next key value is null, then this key doesn't exist
			
			}else{
				//searching next key value in children
				tempCountTree = new CountTree<K>(kArray[index]);
				int indexOfChildren = children.indexOf(tempCountTree);
				return (indexOfChildren >= 0) ? children.get(indexOfChildren).getCount(kArray, index) : 0;
			}
		}
		return 0;
	}
	
	private void buildByCount(K[] kArray, int index) {
		//Stopped by out of bound or null value
		if(index >= (kArray.length) || kArray[index]==null)
			return;
		
		//Search for the tempHST in children list
		CountTree<K> tempCountTree = new CountTree<K>(kArray[index]);		
		int indexOfChildren = children.indexOf(tempCountTree);
				
		if(indexOfChildren >= 0){
			tempCountTree = children.get(indexOfChildren);
			tempCountTree.incrCount();
			tempCountTree.buildByCount(kArray, ++index);
			
		}else{
			tempCountTree.incrCount();
			tempCountTree.buildByCount(kArray, ++index);
			children.add(tempCountTree);

		}
	}

	private void incrCount(){
		this.count+=1;
	}
	
	

}
