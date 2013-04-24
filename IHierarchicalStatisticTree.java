import java.util.List;


public interface IHierarchicalStatisticTree<K> {
//	/**
//	 * build tree by accumulating (the last element in a array)
//	 * @param list
//	 */
//	public abstract void buildTreeBySum(List<K[]> list);
//	public abstract void addBySum(K[] list);
//	public abstract Integer getSum(K[] kArray);
	
//	/**
//	 * build tree by counting record
//	 * @param list
//	 */
//	public abstract void buildTreeByCount(List<List<K>> list);
//	public abstract void addByCount(K[] list);
	
	/**
	 * general的方法
	 * 
	 */
	public abstract void printTree();
	public abstract void buildTree(List<K[]> list);
	public abstract void add(K[] list);
	public abstract java.lang.Number getResult(K[] kArray);
}
