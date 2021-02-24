package com.raul.libraryproject.util.datastructure;

import java.util.Comparator;

/**
 * <p>Interface List extends Collection of package 
 * com.raul.libraryproject.util.datastructure
 * This interface provides the basic operations for a data structure that might
 * behave such as List or maybe ArrayList.</p>
 * 
 * <p>This interface is based on Java.util List concepts. It was
 * found a good strategy to make some methods work similarly like 
 * java.util List. It was create for educational purpose. </p>
 * 
 * <p><b>Created at: </b>22/10/2020</p>
 * 
 * @author Raul Macedo Fuzita
 *
 * @version 1.0.0
 * 
 * @apiNote This interface (List) has no compatibility with the
 * original java.util package. If it's used with a Java.util collections
 * it may thrown exceptions or work unexpectedly. Be aware of it.
 *
 * @param <E> is the type parameter of this interface, List.
 * 
 */
public interface List<E> extends Collection<E> {

	/*
	 * Method's documentation is available in 
	 * Collection interface. No need to
	 * make any change on it for this method. 
	 */
	boolean add(E e);
	
	/**
	 * This method will add a collection to the end of the list.
	 * @param c collection containing elements to be added to this list
	 * @return {@code true} if this list changed as a result of the call
	 */
	public boolean addAll(Collection<? extends E> c);
	
	/**
	 * This method returns an element in the list matches
	 * the index passed as argument.
	 * 
	 * 
	 * @param index is integer value. If an non-existent
	 * index is passed it throws an exception. Otherwise
	 * an element is returned.
	 * 
	 * @return E is a type element. It can be
	 * any Object.
	 * 
	 */
	E get(int index);
	
	/**
	 * This method can replace the object of a given index
	 * to a new one. Read the parameters descriptions.
	 * 
	 * @param index is integer value. If an non-existent
	 * index is passed it throws an exception. Otherwise
	 * an element that matches the index will replaced
	 * by the second parameter.
	 * 
	 * @param e is a type of any object. This is the 
	 * parameter with that value to change the 
	 * current one in the given index.
	 * 
	 * @return E is the new element of the given index.
	 */
	E set(int index, E e);
	
	/**
	 * This method finds an element at a given index and
	 * removes it. If it's removed successfully the
	 * element removed it returned. Otherwise it should
	 * throw an exception if an index doesn't exist.
	 * Whether an exception is thrown or not depends on
	 * the implementation.
	 * 
	 * @param index is integer value. If an non-existent
	 * index is passed it throws an exception. Otherwise
	 * the element is removed.
	 * 
	 * @return E is the element removed.
	 * 
	 */
	E remove(int index);
	
	/*
	 * Method's documentation is available in 
	 * Collection interface. No need to
	 * make any change on it for this method. 
	 */
	boolean remove(Object o);
	
	/*
	 * Method's documentation is available in 
	 * Collection interface. No need to
	 * make any change on it for this method. 
	 */
	boolean contains(Object o);
	
	/**
	 * This method searches for an element in a
	 * list and returns the index of it.
	 * It starts searching from the first element
	 * in a list to the last one.
	 * 
	 * @param o is an object (element) to find
	 * in a list. Any object can be 
	 * passed as an argument.
	 * 
	 * @return Integer value that is the index
	 * of a wanted element.
	 * 
	 */
	int indexOf(Object o);
	
	/**
	 * This method searches for an element in a
	 * list and returns the index of it.
	 * It starts searching from the last element
	 * in a list to the first one.
	 * 
	 * @param o is an object (element) to find
	 * in a list. Any object can be 
	 * passed as an argument.
	 * 
	 * @return Integer value that is the index
	 * of a wanted element.
	 * 
	 */
	int lastIndexOf(Object o);
	
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
	
	/*
	 * Method's documentation is available in 
	 * Collection interface. No need to
	 * make any change on it for this method. 
	 */
	void clear();
	
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
	
	/**
	 * This method sorts a collection data. In order to sort a collection
	 * a Comparator class has to be passed as an argument. This class has to
	 * have the same type as the Collection data.
	 * 
	 * @param comparator is a Comparator type.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	default void sort(Comparator<? super E> comparator) {
		/* Class method toArray to create an Object array with 
		 * the Collection data. This Object array has the same
		 * size of the Collection.*/
		Object[] array = this.toArray();
		
		/*
		 * If for any reason the Collection and the Object array don't have
		 * the same size, this method will abort the operation without 
		 * running next steps.
		 */
		if (this.size() != array.length)
			return;
		
		/*
		 * Calls sort method of Arrays to sort the Collection based on
		 * the Comparator rules.
		 */
		Arrays.sort(array, (Comparator) comparator);
		
		/*
		 * Since Object array has the same size of the Collection an enhanced
		 * (Foreach) For is used to traversal the array and the variable 'i'
		 * will keep increasing until the enhanced For ends.
		 * Make the trick works the Collection current instance calls method
		 * set and passes 'i' variable to keep altering the next element of 
		 * the collection. The second parameter will the new value after
		 * being sorted.
		 */
		int i = 0;
		for (Object object : array)
			this.set(i++, (E) object);
	}
}
