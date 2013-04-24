import java.util.ArrayList;
import java.util.List;


public class SumTree<K> extends AbstractHierarchicalStatisticTree<K> implements IHierarchicalStatisticTree<K> {

	/**
	 * maintain all children belongs to this SumTree node
	 */
	private ArrayList<SumTree<K>> children;

	//Constructors
	public SumTree(){
		super();
		children = new ArrayList<SumTree<K>>();
	}
	
	public SumTree(K key) {
		this();
		this.key = key;
	}
	
	public SumTree(K[] kArray){
		this();
		add(kArray);
	}
	
	public SumTree(List<K[]> list){
		this();
		buildTree(list);
	}

	//Build methods
	@Override
	public void buildTree(List<K[]> list) {
		for(K[] kArray: list){
			int value = (Integer) getValue(kArray);
			incrSum(value);
			buildBySum(kArray, 0, value);
		}
	}

	/**
	 * Building a SumTree by accumulating value recursively.
	 * @param kArray
	 * @param index
	 * @param num
	 */
	private void buildBySum(K[] kArray, int index, int value) {
		if(index >= (kArray.length-1) || kArray[index]==null)
			return;
		
		//Search for the tempHST in children list
		SumTree<K> tempSumTree = new SumTree<K>(kArray[index]);		
		int indexOfChildren = children.indexOf(tempSumTree);
				
		if(indexOfChildren >= 0){
			tempSumTree = children.get(indexOfChildren);
			tempSumTree.incrSum(value);
			tempSumTree.buildBySum(kArray, ++index, value);
		}else{
			tempSumTree.incrSum(value);
			tempSumTree.buildBySum(kArray, ++index, value);
			children.add(tempSumTree);
		}
	}
	
	@Override
	public void add(K[] kArray) {
		Integer value = (Integer) getValue(kArray);
		incrSum(value);
		buildBySum(kArray, 0, value);
	}

	@Override
	public Integer getResult(K[] kArray) {
		if(kArray==null || kArray.length<=0)
			return getCount();
		
		SumTree<K> tempSumTree = new SumTree<K>(kArray[0]);
		
		for(SumTree<K> sumTree: children){			//Searching tempHST
			if(tempSumTree.equals(sumTree)){
				return sumTree.getSum(kArray, 0);	//Start to searching accumulated value from this SumTree
			}
		}
		return 0;		//If not exist
	}
	
	/**
	 * Increasing count
	 * @param value
	 */
	private void incrSum(int value){
		count += value;
	}

	private Integer getSum(K[] kArray, int index){
		if(index >= kArray.length || kArray[index]==null)
			return 0;
		
		SumTree<K> tempSumTree = new SumTree<K>(kArray[index]);
		
		if(tempSumTree.equals(this)){
			index++;						//looking for next key value
			if(index >= kArray.length){
				return count;				//match all key value, return result
			}else if(kArray[index]==null){
				return 0;					//if next key value is null, then this key doesn't exist
			}else{
				//searching next key value in children
				tempSumTree = new SumTree<K>(kArray[index]);
				int indexOfChildren = children.indexOf(tempSumTree);
				return (indexOfChildren >= 0) ? children.get(indexOfChildren).getSum(kArray, index) : 0;
			}
		}
		return 0;
	}
	
	/**
	 * Starting point to print whole tree
	 */
	@Override
	public void printTree() {
		System.out.println(toString());
		for(SumTree<K> hst: children){
			hst.print(toString());
		}
	}

	/**
	 * Printing whole tree recursively.
	 * @param parent
	 */
	private void print(String parent){
		System.out.println(parent + toString());
		for(SumTree<K> hst: children){
			hst.print(parent + toString());
		}
	}
	
	/**
	 * Getting the value we want to accumulate.
	 * To Override this method for custom requirement.
	 * @param kArray
	 * @return
	 */
	private Integer getValue(K[] kArray){
		return (Integer) kArray[kArray.length-1];
	}
}
