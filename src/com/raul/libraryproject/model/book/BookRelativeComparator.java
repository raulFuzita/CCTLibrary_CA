package com.raul.libraryproject.model.book;

import java.util.Comparator;

/**
 * <p>Class BookRelativeComparator implements Comparator. This class is meant 
 * to work with Collection of the package 
 * com.raul.libraryproject.util.datastructure.Collections.
 * BookRelativeComparator has nested classes to sort or search a
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
 * @apiNote This class (BookRelativeComparator) overrides compare method.
 * Careful, this class might not fully work with java.util.Collections.
 *
 */
public class BookRelativeComparator {

	/**
	 * Class Title is a nested class of its outer class. This class overrides
	 * compare method to compare titles between two Book objects.
	 * The Generic type is a Book type. It is not recommended to use for 
	 * sorting algorithms since a value could be relative.
	 * 
	 * @author Raul Macedo Fuzita
	 */
	public static class Title implements Comparator<Book> {

		@Override
		public int compare(Book book1, Book book2) {
			// Calls compareStrings method and returns a value between -1 and 1.
			int firtCheck = compareStrings(book1.getTitle(), book2.getTitle());
			
			if (firtCheck != 0)
				return like(book1.getTitle(), book2.getTitle()) ? 0 : firtCheck;
			
			return firtCheck;
		}
	}
	
	/**
	 * Class Author is a nested class of its outer class. This class overrides
	 * compare method to compare authors between two Book objects.
	 * The Generic type is a Book type. It is not recommended to use for 
	 * sorting algorithms since a value could be relative.
	 * 
	 * @author Raul Macedo Fuzita
	 */
	public static class Author implements Comparator<Book> {

		@Override
		public int compare(Book book1, Book book2) {
			// Calls compareStrings method and returns a value between -1 and 1.
			int firtCheck = compareStrings(book1.getAuthor(), book2.getAuthor());
			
			if (firtCheck != 0)
				return like(book1.getAuthor(), book2.getAuthor()) ? 0 : firtCheck;
			
			return firtCheck;
		}
	}
	
	/**
	 * Class Genre is a nested class of its outer class. This class overrides
	 * compare method to compare genres between two Book objects.
	 * The Generic type is a Book type. It is not recommended to use for 
	 * sorting algorithms since a value could be relative.
	 * 
	 * @author Raul Macedo Fuzita
	 */
	public static class Genre implements Comparator<Book> {

		@Override
		public int compare(Book book1, Book book2) {
			// Calls compareStrings method and returns a value between -1 and 1.
			int firtCheck = compareStrings(book1.getGenre(), book2.getGenre());
			
			if (firtCheck != 0)
				return like(book1.getGenre(), book2.getGenre()) ? 0 : firtCheck;
			
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
