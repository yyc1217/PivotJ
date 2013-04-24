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
	 * Print out whole tree.
	 * 
	 */
	public abstract void printTree();

	/**
	 * Build a HierarchicalStatisticTree.
	 */
	public abstract void buildTree(List<K[]> list);
	
	/**
	 * Add key elements to HierarchicalStatisticTree.
	 */
	public abstract void add(K[] list);
	
	/**
	 * Get the calculated result.
	 * @param kArray
	 * @return
	 */
	public abstract Number getResult(K[] kArray);
}
