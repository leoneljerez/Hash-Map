package hashmap;

public class MapEntry<K, V> {
	/*********** CLASS VARIABLES ***************/
	public K key = null;
	public V value = null;
	
	/********** CONSTRUCTOR **************/
	public MapEntry(K key, V value){
		this.key = key;
		this.value = value;
	}
}
