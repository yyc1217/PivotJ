import java.util.List;



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
