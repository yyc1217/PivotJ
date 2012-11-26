import java.util.List;


public interface IHierarchicalStatisticTree<K> {
	/**
	 * build tree by accumulating (the last element in a array)
	 * @param list
	 */
	public abstract void buildTreeBySum(List<K[]> list);
	public abstract void addBySum(K[] list);
	public abstract Integer getSum(K[] kArray);
	
	/**
	 * build tree by counting record
	 * @param list
	 */
	public abstract void buildTreeByCount(List<List<K>> list);
	public abstract void addByCount(K[] list);
	
	/**
	 * print all nodes in tree
	 */
	public abstract void printTree();
}
