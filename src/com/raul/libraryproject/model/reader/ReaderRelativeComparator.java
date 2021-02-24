package com.raul.libraryproject.model.reader;

import java.util.Comparator;

/**
 * <p>Class ReaderRelativeComparator implements Comparator. This class is meant 
 * to work with Collection of the package
 * com.raul.libraryproject.util.datastructure.Collections.
 * ReaderRelativeComparator has nested classes to search a
 * value in different ways. All methods are static so that it doesn't need
 * an instance of the class. It is not recommended to use for sorting
 * algorithms since a value could be relative.
 * </p>
 * 
 * <p><b>Created at: </b>03/12/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @version 1.0.0
 * 
 * @apiNote This class (ReaderRelativeComparator) overrides compare method.
 * Careful, this class might not fully work with java.util.Collections.
 *
 */
public class ReaderRelativeComparator {

	/**
	 * Class Name is a nested class of its outer class. This class overrides
	 * compare method to compare names between two Reader objects.
	 * The Generic type is a Reader type. It is not recommended to use for 
	 * sorting algorithms since a value could be relative.
	 * 
	 * 
	 * @author Raul Macedo Fuzita
	 */
	public static class Name implements Comparator<Reader> {

		@Override
		public int compare(Reader reader1, Reader reader2) {
			// Calls compareStrings method and returns a value between -1 and 1.
			int firtCheck = compareStrings(reader1.getName(), reader2.getName());
			
			if (firtCheck != 0)
				return like(reader1.getName(), reader2.getName()) ? 0 : firtCheck;
				
			return firtCheck;
		}
		
	}
	
	/**
	 * Class Surname is a nested class of its outer class. This class overrides
	 * compare method to compare surnames between two Reader objects.
	 * The Generic type is a Reader type. It is not recommended to use for 
	 * sorting algorithms since a value could be relative.
	 * 
	 * @author Raul Macedo Fuzita
	 */
	public static class Surname implements Comparator<Reader> {

		@Override
		public int compare(Reader reader1, Reader reader2) {
			// Calls compareStrings method and returns a value between -1 and 1.
			int firtCheck = 
					compareStrings(reader1.getSurname(), reader2.getSurname());
			
			if (firtCheck != 0) {
				return like(reader1.getSurname(), reader2.getSurname()) 
						? 0 : firtCheck;
			}
			return firtCheck;
		}
		
	}
	
	/**
	 * This method uses compareTo internally to compare two Strings.
	 * If the result is bigger than zero it returns 1. If the result
	 * is smaller than zero it returns -1. Otherwise it returns 0.
	 * 
	 * @param s1 is a String type.
	 * @param s2 is a String type.
	 * @return integer number between -1 and 1.
	 */
	private static int compareStrings(String s1, String s2) {
		if (s1.compareTo(s2) > 0)
			return 1;
		else if (s1.compareTo(s2) < 0)
			return -1;
		return 0;
	}
	
	/**
	 * This method checks if the second argument contains in the
	 * first argument.
	 * @param arg0 is the argument to look in
	 * @param arg1 it the argument that might contain in arg0
	 * @return true if contains arg1 in arg0.
	 */
	private static boolean like(String s1, String s2) {
		s1 = s1.toLowerCase();
		s2 = s2.toLowerCase();
		return s1.contains(s2);
	}
}
