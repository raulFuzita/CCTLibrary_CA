package com.raul.libraryproject.util.datastructure;

/**
 * <p>Interface Collection extends Iterable. Its children or implementation 
 * will inherit it.
 * This interface provides the basic operations for a data structure that might 
 * behave such as List, Array, Set, and Queue.</p>
 * 
 * <p>This interface is based on java.util Collection concepts. It was
 * found a good strategy to make any implementation iterable.
 * Thus provide a minimum methods. It was create for educational
 * purpose. </p>
 * 
 * <p><b>Created at: </b>22/10/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @version 1.0.0
 * 
 * @apiNote This interface (Collection) has no compatibility with the
 * original java.util package. If it's used with a Java.util collections
 * it may thrown exceptions or work unexpectedly. Be aware of it.
 *
 * @param <E> is the type parameter of this interface, Collection.
 * 
 */
public interface Collection<E> extends Iterable<E> {

	/**
	 * This method performs an adding operation
	 * to the instance of a collection. 
	 * Any type is allowed even a null.
	 * 
	 * @param e is the type of the parameter.
	 * 
	 * @return true when an element is added.
	 */
	boolean add(E e);
	
	/**
	 * This method removes an element in the
	 * instance of a collection and returns a 
	 * boolean.
	 * 
	 * @param o is an object (element) to find
	 * in the collection. Any object can be 
	 * passed as an argument.
	 * 
	 * @return true when an element is removed.
	 * Otherwise false.
	 */
	boolean remove(Object o);
	
	/**
	 * This method finds an element in the
	 * instance of a collection and returns a 
	 * boolean.
	 * 
	 * @param o is an object (element) to find
	 * in the collection. Any object can be 
	 * passed as an argument.
	 * 
	 * @return true when an element is found.
	 * Otherwise false.
	 */
	boolean contains(Object o);
	
	/**
	 * This method returns a size but the way
	 * it works internally depends on the 
	 * class that will implement the interface.
	 * It's recommended to store the size in a
	 * separable variable to loop through it.
	 * 
	 * @return collection size in integer.
	 */
	int size();
	
	/**
	 * This method checks whether collection
	 * is empty or not.
	 * 
	 * @return false if collection is empty.
	 * Otherwise true.
	 */
	boolean isEmpty();
	
	/**
	 * This method is meant to remove all 
	 * elements (data) in a collection.
	 * What an instance of this collection
	 * will return after cleaning depends on
	 * that implementation.
	 */
	void clear();
	
	/**
	 * This method returns an Object array containing the all of the
	 * elements of this Collection.
	 * 
	 * @return Object[]
	 */
	Object[] toArray();
	
	/**
	 * <p>This method will take any type of array and return
	 * according to the Generic method type.
	 * It could be used for many purposes, an example is:
	 *  
	 * {@code Custom[] c = Collection.toArray(new Custom[size]);}
	 * </p>
	 * 
	 * <p>Converting from Object[] to another type object array is not
	 * possible. It'll require casting but in the runtime it'll
	 * throw an exception type of ClassCastException.<br>
	 * Bad example:
	 * {@code Custom[] c = (Custom[]) objectArray;}
	 * </p>
	 * 
	 * @param <T> the class of the objects in the list
	 * @param array is type Generic.
	 * @return T[] which is an array type Generic.
	 * 
	 * @throws NullPointerException if the specified array is null
	 */
	<T> T[] toArray(T[] array);
}
