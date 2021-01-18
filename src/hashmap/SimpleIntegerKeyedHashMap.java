package hashmap;

import java.util.LinkedList;
import java.util.List;
import java.lang.Integer;

public class SimpleIntegerKeyedHashMap<E>  {
	/******* CLASS VARIABLES *************************************************************************/
	protected List<MapEntry<Integer, E>>[] buckets = null;//array of Lists, where each List holds zero or more MapEntry elements
	protected int capacity = 0;//max # of items to store in map.  Should be set in constructor
	private final static double loadFactor = 0.75;//75% load factor

	/******* GETTERS AND SETTERS *********************************************************************/
	public int getCapacity(){
		return capacity;
	}
	/**
	 * Sets the capacity to be the nearest prime number greater than the initial capacity
	 * passed in divided by the load factor.
	 * @param initialCapacity
	 */
	private void setCapacity(int initialCapacity){
		int newCapacity = (int)(Math.ceil(initialCapacity/loadFactor));
		this.capacity = calculateNextPrimeNumber(newCapacity);
	}
	private List<MapEntry<Integer, E>>[] getBuckets(){
		if (buckets == null){
			buckets = (List<MapEntry<Integer, E>>[]) new List[getCapacity()] ;
			//initialize each bucket to be an empty LinkedList
			for(int i=0; i < buckets.length; i++){
				buckets[i] = new LinkedList<MapEntry<Integer, E>>();
			}
		}
		return buckets;
	}

	/******* CONSTRUCTORS ************************************************************************/
	public SimpleIntegerKeyedHashMap(int initialCapacity) {
		setCapacity(initialCapacity);
	}
	/******* PRIMARY METHODS *********************************************************************/

	public void put(Integer key, E element) {
		int hashIndex = calculateHashIndex(key);
		List<MapEntry<Integer, E>> bucket = getBuckets()[hashIndex];
		MapEntry<Integer, E> entry = new MapEntry(key, element);
		bucket.add(entry);
	}

	public E get(Integer key) {
		E result = null;
		int hashIndex = calculateHashIndex(key);
		List<MapEntry<Integer, E>> bucket = getBuckets()[hashIndex];
		boolean isFound = false;
		int index = 0;
		MapEntry<Integer, E> entry = null;
		while(!isFound && index < bucket.size()){
			entry = bucket.get(index);
			if(key.intValue() == entry.key.intValue()){
				isFound = true;
				result = entry.value;
			}
			index++;
		}
		return result;
	}

	public E remove(Integer key) {
		E result = null;
		int hashIndex = calculateHashIndex(key);
		List<MapEntry<Integer, E>> bucket = getBuckets()[hashIndex];
		boolean isFound = false;
		int index = 0;
		MapEntry<Integer, E> entry = null;
		while(!isFound && index < bucket.size()){
			entry = bucket.get(index);
			if(key.intValue() == entry.key.intValue()){
				isFound = true;
				result = entry.value;
				bucket.remove(index);
			}
			index++;
		}
		return result;
	}
	/**
	 * Calculates and returns an int resulting from applying the following
	 * hash function to the key passed in:
	 * 
	 *  h = k mod m, where k is the key and m is the capacity of the array
	 *  used to hold the elements.
	 * @param key
	 * @return
	 */
	public int calculateHashIndex(Integer key){
		int hashIndex = key.intValue() % getCapacity();
		return hashIndex;
	}

	/******* MISC METHODS *************************************************************************/
	/**
	 * Calculates the next prime number that is greater than the value passed in.  First determines
	 * if the number passed in is already prime.  If it is, it simply returns that number.  If it isn't,
	 * it adds one to the number and tries again. 
	 * @param startingNumber
	 * @return
	 */
	public int calculateNextPrimeNumber(int startingNumber){
		int nextNumber = startingNumber;
		if(!isPrimeNumber(nextNumber)){
			nextNumber = calculateNextPrimeNumber(nextNumber +1);
		}
		return nextNumber;
	}
	/**
	 * Returns true only if the number passed is is a prime.  Otherwise, returns false. A number is prime 
	 * only if there are no numbers besides 1 and the number itself that can be divided evenly into the number.
	 * This method first determines if the number is evenly divisible by 2 (using modulus division).  If it isn't,
	 * then it checks all numbers from 3 up to half the number to see if any of them divide evenly into the 
	 * number.  Once a number is found to NOT be a prime, the result "false" is returned.
	 * @param testValue
	 * @return
	 */
	public boolean isPrimeNumber(int testValue){
		boolean isPrime = true;
		//check to see if divisible by 2
		if(testValue % 2 == 0){
			isPrime = false;
		}else{
			//check numbers starting at 3 and going up to 1/2 of the number passed in
			int currentValue = 3;
			int endValue = testValue/2;
			while(isPrime && currentValue <= endValue){
				isPrime = (testValue % currentValue != 0);
				currentValue += 2;
			}
		}
		return isPrime;
	}
	@Override
	public String toString() {
		List<MapEntry<Integer, E>> bucket = null;
		StringBuffer buf = new StringBuffer();
		for(int i = 0; i < getBuckets().length; i++){
			bucket = getBuckets()[i];
			buf.append(i);
			buf.append(": ");
			if(bucket.size() > 0){
				for (MapEntry<Integer, E> element : bucket){
					buf.append(element.value);
					buf.append(" ");
				}
			}
			buf.append("\n");//line return
		}
		return buf.toString();
	}
}
