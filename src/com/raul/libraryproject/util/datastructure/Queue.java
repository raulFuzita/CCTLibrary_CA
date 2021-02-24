package com.raul.libraryproject.util.datastructure;

/**
 * <p>Interface Queue extends Collection of package 
 * com.raul.libraryproject.util.datastructure
 * This interface provides the basic operations for a data structure that might
 * behave such as Queue or maybe Dequeue.</p>
 * 
 * <p>This interface is based on java.util Queue concepts. It was
 * found a good strategy to make some methods work similarly like 
 * java.util Queue. It was create for educational purpose. </p>
 * 
 * <p><b>Created at: </b>22/10/2020</p>
 * 
 * @author Raul Macedo Fuzita
 *
 * @version 1.0.0
 * 
 * @apiNote This interface (Queue) has no compatibility with the
 * original java.util package. If it's used with a java.util collections
 * it may thrown exceptions or work unexpectedly. Be aware of it.
 *
 * @param <E> is the type parameter of this interface, List.
 * 
 */
public interface Queue<E> extends Collection<E> {

	/*
	 * Method's documentation is available in 
	 * Collection interface. No need to
	 * make any change on it for this method. 
	 */
	boolean add(E e);
	
	/**
	 * This method removes an element from the front of a 
	 * queue and returns the removed element if it exists.
	 * It's expected that it works according to the 
	 * concept of FIFO (First-In, First-Out).
	 * 
	 * @return E is a type element. It can be
	 * any Object.
	 * 
	 */
	E remove();
	
	/**
	 * This method removes an element from the front of a 
	 * queue and returns the removed element if it exists.
	 * It's expected that it works according to the 
	 * concept of FIFO (First-In, First-Out).
	 * 
	 * @return E is a type element. It can be
	 * any Object.
	 * 
	 */
	E pull();
	
	/**
	 * This method returns the front element of a queue
	 * but the peeked element is removed.
	 * 
	 * @return E is a type element. It can be
	 * any Object.
	 */
	E peek();
	
	/*
	 * Method's documentation is available in 
	 * Collection interface. No need to
	 * make any change on it for this method. 
	 */
	int size();
	
	/*
	 * Method's documentation is available in 
	 * Collection interface. No need to
	 * make any change on it for this method. 
	 */
	boolean isEmpty();
	
	/**
	 * This method clones current instance to another
	 * one but the new instance doesn't have the 
	 * reference of the same memory space.
	 * It's an alternative since queuesMap aren't 
	 * immutable.
	 * 
	 * @return Object that is cloned.
	 * @throws CloneNotSupportedException if the 
	 * implementation doesn't implements this
	 * method properly.
	 */
	Object clone() throws CloneNotSupportedException;
	
	/*
	 * Method's documentation is available in 
	 * Collection interface. No need to
	 * make any change on it for this method. 
	 */
	Object[] toArray();
	
	/*
	 * Method's documentation is available in 
	 * Collection interface. No need to
	 * make any change on it for this method. 
	 */
	<T> T[] toArray(T[] array);
}
