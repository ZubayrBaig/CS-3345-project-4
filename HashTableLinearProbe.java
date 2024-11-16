
public class HashTableLinearProbe<K,V> {

	private static class HashEntry<K,V>{
		
		K key;
		V value;
		
		boolean deleted;
		
		HashEntry(K key, V value){
			this.key = key;
			this.value = value;
			this.deleted = false;
		}
	}
	
	private HashEntry<K,V> hashtable[]; 
	
	private int tableSize;
	private int numOfValues;
	
    public HashTableLinearProbe() {
        this.tableSize = 3; 
        this.hashtable = new HashEntry[tableSize];
        this.numOfValues = 0;
    }

    private int keyToInt(K key) {
        if (key instanceof Integer) {
            return (Integer) key;
        } 
        else if (key instanceof String) {
            String string = (String) key;
            int hashValue = 0;
            for (int i = 0; i < string.length(); i++) {
                hashValue += string.charAt(i);
            }
            return hashValue;
        }
        throw new IllegalArgumentException("Key must be Integer or String");
    }
    
    private int getHashValue(K key) {
        int hashValue = keyToInt(key) % tableSize;
        if (hashValue < 0) hashValue += tableSize;
        return hashValue;
    }
	
    public boolean insert(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key or Value cannot be null");
        }
        if (find(key) != null) {
            return false; // Duplicate entry
        }

        int hashValue = getHashValue(key);
        int i = 0;
        
        while (hashtable[(hashValue + i) % tableSize] != null &&
               !hashtable[(hashValue + i) % tableSize].deleted) {
            i++;
        }
        hashtable[(hashValue + i) % tableSize] = new HashEntry<>(key, value);
        numOfValues++;

        if ((double) numOfValues / tableSize > 0.99) { 
            rehash();
        }
        return true;
    }
    
    public V find(K key) {
        int hashValue = getHashValue(key);
        int i = 0;
        
        while (hashtable[(hashValue + i) % tableSize] != null) {
            HashEntry<K, V> entry = hashtable[(hashValue + i) % tableSize];
            if (!entry.deleted && entry.key.equals(key)) {
                return entry.value;
            }
            i++;
        }
        return null;
    }
    
    public boolean delete(K key) {
        int hash = getHashValue(key);
        int i = 0;
        
        while (hashtable[(hash + i) % tableSize] != null) {
            HashEntry<K, V> entry = hashtable[(hash + i) % tableSize];
            if (!entry.deleted && entry.key.equals(key)) {
                entry.deleted = true;
                numOfValues--;
                return true;
            }
            i++;
        }
        return false;
    }
    
    private void rehash() {
        tableSize *= 2;
        HashEntry<K, V>[] oldTable = hashtable;
        hashtable = new HashEntry[tableSize];
        numOfValues = 0;

        for (HashEntry<K, V> entry : oldTable) {
            if (entry != null && !entry.deleted) {
                insert(entry.key, entry.value);
            }
        }
    }
    
    
	
	
}
