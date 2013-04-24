

public abstract class AbstractHierarchicalStatisticTree<K> implements Comparable<K>, IHierarchicalStatisticTree<K> {	
	K key;
	Integer count;

	
	protected AbstractHierarchicalStatisticTree(){
		this.key = null;
		this.count = 0;
	}
	
	@Override
	public String toString(){
		if(this.key==null)
			return "[TOTAL:".concat(this.count.toString()) + "]";
		else
			return "[" + this.key.toString().concat(":").concat(this.count.toString()) + "]";
	}
	
	@SuppressWarnings("unchecked")
	public int compareTo(K o) {
		return ((Comparable<K>)this.key).compareTo((K)o);
	}
	
	@SuppressWarnings("unchecked")
	public boolean equals(Object obj) {
		if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        return this.key.equals(((AbstractHierarchicalStatisticTree<K>) obj).getKey());
 	}
	
//	private void buildByCount(List<K> list, int index) {
//		//Stopped by out of bound or null value
//		if(index >= (list.size()) || list.get(index)==null)
//			return;
//		
//		//Search the key in children
//		K tempKey = list.get(index);
//		int indexOfChildren = this.children.indexOf(new AbstractHierarchicalStatisticTree<K>(tempKey));
//		
//		if(indexOfChildren >= 0){
//			//If exist
//			AbstractHierarchicalStatisticTree<K> hst = this.children.get(indexOfChildren);
//			hst.incrCount();
//			hst.buildByCount(list, ++index);
//		}else{
//			//If not exist
//			AbstractHierarchicalStatisticTree<K> hst = new AbstractHierarchicalStatisticTree<K>(tempKey);
//			hst.incrCount();
//			hst.buildByCount(list, ++index);
//			this.children.add(hst);
//		}
//	
//	}
//
//	public void buildTreeByCount(List<List<K>> list) {
//		this.incrCount();
//		for(List<K> klist:list){
//			this.buildByCount(klist, 0);
//		}
//	}
//
//	private void incrCount(){
//		this.count+=1;
//	}

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

}
