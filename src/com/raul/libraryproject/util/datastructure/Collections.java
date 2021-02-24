package com.raul.libraryproject.util.datastructure;

import java.util.Comparator;

/**
 * <p>Class Collections is meant to use static methods to traverse a collection. 
 * This class belongs to the package:
 * com.raul.libraryproject.util.datastructure.Collections.
 * Since this class is based on java.util.Collections it doesn't or might not
 * have the same methods or even use the same algorithm to deal with 
 * data in every method.
 * </p>
 * 
 * <p><b>Created at: </b>19/11/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @version 1.0.0
 * 
 * @apiNote This class (Collections) is based on java.util.Collections. 
 * This class was created for study purpose. Although it's not 
 * java.util.Collections class it should work with any type of List that
 * implements get and size method. This List also has to be assigned by a
 * Generic type. 
 * Any method parameters should be passed accordingly to the method 
 * signature and documentation.
 *
 */
public class Collections {
	
	private Collections() {}
	
	
	/**
	 * In order to use binary search the collection must be sorted according
	 * to the Comparator rules, otherwise binary search will fail and return -1.
	 * 
	 * This method uses Generic method to parameterizes the Type array and
	 * the Comparator Type. This method is called with any kind of comparator 
	 * whose element type is a supertype of the element type of the array.
	 * 
	 * <p>
	 * Read more and see examples with 
	 * {@link <a href="https://docs.oracle.com/javase/tutorial/extra/generics/methods.html">Generic Methods</a>}
	 * </p>
	 * 
	 * The Comparator parameter, in addition to the Generic use, 
	 * also uses reflection.
	 * 
	 * @param <T> the class of the objects in the list
	 * @param list is an interface List signed by a generic type.
	 * @param key is an object type generic which will be the same type of the
	 * parameter list. Key is the wanted element to be searched in the list.
	 * @param comparator is a Comparator interface. This parameter will
	 * apply the rule that should be taken as comparison rule.
	 * @return int number. It supposed to be the index of the wanted object.
	 * It will returns the index which can be between 0 and the Collection
	 * size minus 1. If the wanted element is not found it will return 0 then.
	 */
	public static <T> int 
	binarySearch(List<? extends T> list, T key, Comparator<? super T> comparator){
		return indexedBinarySearch(list, key, comparator);
	}

	/**
	 * In order to use binary search the collection must be sorted according
	 * to the Comparator rules, otherwise binary search will fail and return -1.
	 * 
	 * This method uses Generic method to parameterizes the Type array and
	 * the Comparator Type. This method is called with any kind of comparator 
	 * whose element type is a supertype of the element type of the array.
	 * 
	 * <p>
	 * Read more and see examples with 
	 * {@link <a href="https://docs.oracle.com/javase/tutorial/extra/generics/methods.html">Generic Methods</a>}
	 * </p>
	 * 
	 * The Comparator parameter, in addition to the Generic use, 
	 * also uses reflection.
	 * 
	 * @param <T> the class of the objects in the list
	 * @param list is an interface List signed by a generic type.
	 * @param key is an object type generic which will be the same type of the
	 * parameter list. Key is the wanted element to be searched in the list.
	 * @param comparator is a Comparator interface. This parameter will
	 * apply the rule that should be taken as comparison rule.
	 * @return int number. It supposed to be the index of the wanted object.
	 * It will returns the index which can be between 0 and the Collection
	 * size minus 1. If the wanted element is not found it will return 0 then.
	 */
	private static <T> int indexedBinarySearch
	(List<? extends T> list, T key, Comparator<? super T> comparator){
		/*
		 * left variable represents the left half of the collection and right 
		 * variable the opposite.
		 */
		int left = 0, right = list.size() - 1;
		/* While statement must stop when left variable value is bigger than 
		 * right one. */
		while(left <= right) {
			/**
			 * Using Zero-fill right shift is the same as 
			 * (left + ((right - left) / 2).
			 * Zero-fill will push zeroes in from the left and letting
			 * the rightmost bits fall off.
			 * 
			 * See more about Zero-fill at:
			 * https://www.w3schools.com/java/java_operators.asp
			 * 
			 * Example: 8 in binary is 1000. If it's shifted one bit to
			 * right it'll be 0100 in binary. 0100 in decimal is 4 which
			 * is the half of 8.
			 * 
			 */
			int middle = (left + right) >>> 1;
			// Retrieve the stored object at position middle value.
			T midVal = list.get(middle);
			// Applies Comparator rule to get a value between -1 and 1.
			int cmp = comparator.compare(midVal, key);
			
			if (cmp < 0)
				left = middle + 1;
			else if (cmp > 0)
				right = middle - 1;
			else
				return middle;
		}
		/* If there is no elements in the collection returns -1 or 
		 * if object Key is not found in the collection. */
		return -1;
	}
	
	/**
	 * This method uses Generic method to parameterizes the Type array and
	 * the Comparator Type. This method is called with any kind of comparator 
	 * whose element type is a supertype of the element type of the array.
	 * 
	 * This method is not as fast as binary search. This algorithm is
	 * recommended to use with a relative comparator that might retrieve
	 * a data that match exactly or partially. It is also recommended to
	 * search for multiples relative data results.
	 * 
	 * If there is no result it should return an empty List of the assigned type.
	 * Keep in mind it'll return a list of any type or as default an LinkedList.
	 * 
	 * <p>
	 * Read more and see examples with 
	 * {@link <a href="https://docs.oracle.com/javase/tutorial/extra/generics/methods.html">Generic Methods</a>}
	 * </p>
	 * 
	 * The Comparator parameter, in addition to the Generic use, 
	 * also uses reflection.
	 * 
	 * @param <T> the class of the objects in the list
	 * @param list is an interface List signed by a generic type.
	 * @param key is an object type generic which will be the same type of the
	 * parameter list. Key is the wanted element to be searched in the list.
	 * @param comparator is a Comparator interface. This parameter will
	 * apply the rule that should be taken as comparison rule.
	 * @return {@code List<T>} that is a list assigned to a Generic type.
	 */
	public static <T> List<T> linearSearch
	(List<? extends T> list, T key, Comparator<? super T> comparator){
		// Instances a temporarily List of LinkedList
		List<T> temp = new LinkedList<>();
		
		// If there is no data it should stop and return a default List.
		if (list.size() < 1)
			return temp;
		// Traversal the main List to store each matched value
		for (T t : list) {
			if (comparator.compare(t, key) == 0)
				temp.add(t);
		}
		return temp;
	}
}
