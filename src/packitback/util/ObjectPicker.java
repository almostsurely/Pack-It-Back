package packitback.util;

import java.util.HashSet;
import java.util.Random;

/**
 * This class wraps a HashSet Collection, and give extra functionality to pulling
 * objects out at random.  Each object is assigned a double as a weight.  The 
 * higher the weight, the more likely the chance the object has to being picked
 * at random.
 * For example, if three objects A, B, and C are entered into the Picker with a
 * weight of 1.0, and one object D is entered with a weight of 3.0.  D has a 
 * 50% chance of being selected, while A, B, and C each have a 16.67% chance of
 * being pulled out. 
 * 
 * @author 		James
 * @version 	1.1, 8/14/2013
 * @param <T> 	This is the generic object type stored in the ObjectPicker.
 */
public class ObjectPicker<T> {
	
	//FIELDS
	Random rnd = new Random();
	HashSet<Bucket<T>> buckets = new HashSet<Bucket<T>>();
	
	//CONSTRUCTOR
	
	//METHODS
	/**
	 * Wraps an object in a bucket, adding a weight to it.
	 * 
	 * @param t			the object of type T added to the bucket
	 * @param weight	the weighted value to test against when picking an object
	 */
	public void add(T t, double weight){
		buckets.add(new Bucket<T>(t, weight));
	}
	
	/**
	 * Similar to above method, but with a default weight of 1.0
	 * 
	 * @param t	the object of type T added to the bucket
	 */
	public void add(T t){
		buckets.add(new Bucket<T>(t, 1.0));
	}
	
	/**
	 * This method selects a bucket at random from the Hashset.
	 * 
	 * @return returns a bucket with an object inside
	 */
	public Bucket<T> pickBucket(){
		
		double totalWeight = 0.0;
		
		for (Bucket<T> b : buckets){
			totalWeight += b.getWeight();
		}
		
		double r = rnd.nextDouble() * totalWeight;
		
		double i = 0.0;
		for (Bucket<T> b : buckets){
			i += b.getWeight();
			if (r <= i){
				return b;
			}
		}
		
		return null;
	}
	
	/**
	 * This method returns an object picked at random, based on its weighted value.
	 * 
	 * @return	a random object picked from the bucket list
	 */
	public T pickObject(){
		
		return pickBucket().getObject();
		
	}
	
	
	/**
	 * Similar to the above pullObject, but this method also removes the object from
	 * the Hashset
	 * 
	 * @return a random object picked from the bucket list
	 */
	public T pullObject(){
		
		Bucket<T> pick = pickBucket();
		
		T obj = pick.getObject();
		
		buckets.remove(pick);
		
		return obj;
	}
	
	//BUCKET CLASS
	/**
	 * This is an Object wrapper class.  It stores the weighted value without having
	 * to deal with difficult things like reference arrays.
	 * 
	 * @author James Dozier
	 *
	 * @param <U> Object type that will always be the same as T from the parent
	 * class
	 */
	private class Bucket<U>{
		
		//Fields
		U obj;
		double weight;
		
		//Constructor
		public Bucket(U obj, double weight){
			this.obj = obj;
			this.weight = weight;
		}
		
		public U getObject(){
			return obj;
		}
		
		public double getWeight(){
			return weight;
		}
		
	}
}
