package com.raul.libraryproject.util.datastructure;

import java.util.Comparator;

/**
 * <p>Class Arrays is meant to use static methods to change an array, 
 * replace it, sort it and so on. This class belongs to the package:
 * com.raul.libraryproject.util.datastructure.Arrays.
 * Since this class is based on java.util.Arrays it doesn't or might not
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
 * @apiNote This class (Arrays) is based on java.util.Arrays. This class
 * was created for study purpose. Although it's not java.util.Arrays class
 * it should work with any type of array. Any method parameters should be
 * passed accordingly to the method signature and documentation.
 *
 */
public class Arrays {

	private Arrays() {}
	
	/**
	 * This method encapsulates a merge sort method. To satisfy the method
	 * Array of Type and Comparator instance class has to have the same type.
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
	 * @param array is a type T. It means any type of an Object array.
	 * @param comparator is the Comparator instance class.
	 */
	public static <T> void sort(T[] array, Comparator<? super T> comparator) {
		// Class mergeSort method
		mergeSort(array, comparator);
	}
	
	/**
	 * This method uses recursion to break down an array until it's not
	 * able to break any smaller.
	 * If a passed array is null it'll abort the method immediately.
	 * System.arraycopy is called to create each halves of the array.
	 * They're all created in a new space in memory.
	 * Method merge is called to apply the Comparator rules to
	 * sort the array data.
	 * The array passed as argument is sorted since its reference is
	 * kept.
	 * Using Generic method, Generic array, Comparator with reflection
	 * and Generic type makes it able to work if any kind of array
	 * and Comparator.
	 * 
	 * @param <T> the class of the objects in the list
	 * @param array is a type T Generic. It means any type of an Object array.
	 * @param comparator is the Comparator instance class.
	 */
	@SuppressWarnings("unchecked")
	private static <T> void 
	mergeSort(T[] array, Comparator<? super T> comparator) {
		// If array is equal to null stop the sorting process.
		if (array == null)
			return;
		/* Checks if the array has more than one data to start 
		 * the sorting process. */
		if (array.length > 1) {
			
			// Gets low side size. It's half the size.
			int lowSize = array.length >>> 1;
			/*
			 * Creates an array of the Type of the main array.
			 * It's not possible to create an array of Generic.
			 * The array class has to be an array of Object and
			 * casted to type Generic.
			 */
			T[] low = (T[]) new Object[lowSize];
			// Create a copy of main array to the low side.
			System.arraycopy(array, 0, low, 0, lowSize);
			// Calls method mergeSort recursively
			mergeSort(low, comparator);
			
			// Gets high side size. It's half the size.
			int highSize = array.length >>> 1;
			/*
			 * Creates an array of the Type of the main array.
			 * It's not possible to create an array of Generic.
			 * The array class has to be an array of Object and
			 * casted to type Generic.
			 */
			T[] high = (T[]) new Object[highSize];
			/* Create a copy of main array to the high side.
			 * The second half has to start where the first half
			 * ends.*/
			System.arraycopy(array, lowSize, high, 0, highSize);
			// Calls method mergeSort recursively
			mergeSort(high, comparator);
			
			/* Each time mergeSort is called recursively it also calls
			 * method merge to apply the Comparator rules.*/
			merge(low, high, array, comparator);
		}
	}
	
	/**
	 * This method will replace the values of the main array with the 
	 * values of each half of the main array. Comparator will applies
	 * the rule to sort the algorithm.
	 * Using Generic method, Generic array, Comparator with reflection
	 * and Generic type makes it able to work if any kind of array
	 * and Comparator.
	 * 
	 * @param <T> the class of the objects in the list
	 * @param low is type T Generic. It's the first half of 
	 * the current recursion.
	 * @param high is type T Generic. It's the second half of 
	 * the current recursion.
	 * @param array is a type T Generic. It means any type of an Object array.
	 * @param comparator comparator is the Comparator instance class.
	 */
	private static <T> void 
	merge(T[] low, T[] high, T[] array, Comparator<? super T> comparator) {
		
		int lowCount = 0; // A counter for the low[]
		int highCount = 0; // A counter for the high[]
		int arrayCount = 0; // A counter for the main array.
		
		/*
		 * It'll repeats while low counter and high counter is 
		 * smaller than low array size and high one.
		 * This While statement will apply the Comparator rule
		 * and based on the result each main array index will
		 * store either low array value or high one.
		 */
		while(lowCount < low.length && highCount < high.length) {
			
			// Whichever reaches its array size While is ended.
			if(comparator.compare(low[lowCount], high[highCount]) < 0) {
				array[arrayCount] = low[lowCount];
				lowCount++;				
			} else {
				array[arrayCount] = high[highCount];
				highCount++;
			}
			// Keep traversing the main array
			arrayCount++;
		}
		
		// Copy remaining elements of low[] if any
		while (lowCount < low.length) {
			array[arrayCount] = low[lowCount];
			lowCount++;
			arrayCount++;
		}
		// Copy remaining elements of high[] if any
		while(highCount < high.length) {
			array[arrayCount] = high[highCount];
			highCount++;
			arrayCount++;
		}
	}
}
