package com.raul.libraryproject.model.book;

import java.util.Comparator;

/**
 * <p>Class BookComparator implements Comparator. This class is meant to work
 * with Collection of the package 
 * com.raul.libraryproject.util.datastructure.Collections.
 * BookComparator has nested classes to sort or search a
 * value in different ways. All methods are static so that it doesn't need
 * an instance of the class.
 * </p>
 * 
 * <p><b>Created at: </b>18/11/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @version 1.0.0
 * 
 * @apiNote This class (BookComparator) overrides compare method.
 * Careful, this class might not fully work with java.util.Collections.
 *
 */
public class BookComparator {
	
	/**
	 * Class ID is a nested class of its outer class. This class overrides
	 * compare method to compare IDs between to Book objects.
	 * The Generic type is a Book type.
	 * 
	 * @author Raul Macedo Fuzita
	 */
	public static class ID implements Comparator<Book> {

		@Override
		public int compare(Book book1, Book book2) {
			/*
			 * The easiest way to deal with integer comparison is to subtract
			 * the first element by the second one. If the second one is
			 * bigger than the first it's expected a negative number.
			 * If the second element is smaller the result should be a positive
			 * number. If they are equal the result will be zero.
			 */
			return (int) (book1.getId() - book2.getId());
		}
		
	}
	
	/**
	 * Class Quantity is a nested class of its outer class. This class overrides
	 * compare method to compare years between two Book objects.
	 * The Generic type is a Book type.
	 * 
	 * @author Raul Macedo Fuzita
	 */
	public static class Quantity implements Comparator<Book> {

		@Override
		public int compare(Book book1, Book book2) {
			/*
			 * The easiest way to deal with integer comparison is to subtract
			 * the first element by the second one. If the second one is
			 * bigger than the first it's expected a negative number.
			 * If the second element is smaller the result should be a positive
			 * number. If they are equal the result will be zero.
			 */
			return (int) (book1.getQuantity() - book2.getQuantity());
		}
		
	}

	/**
	 * Class Title is a nested class of its outer class. This class overrides
	 * compare method to compare titles between two Book objects.
	 * The Generic type is a Book type.
	 * 
	 * @author Raul Macedo Fuzita
	 */
	public static class Title implements Comparator<Book> {

		@Override
		public int compare(Book book1, Book book2) {
			// Calls compareStrings method and returns a value between -1 and 1.
			return compareStrings(book1.getTitle(), book2.getTitle());
		}
	}
	
	/**
	 * Class Author is a nested class of its outer class. This class overrides
	 * compare method to compare authors between two Book objects.
	 * The Generic type is a Book type.
	 * 
	 * @author Raul Macedo Fuzita
	 */
	public static class Author implements Comparator<Book> {

		@Override
		public int compare(Book book1, Book book2) {
			// Calls compareStrings method and returns a value between -1 and 1.
			return compareStrings(book1.getAuthor(), book2.getAuthor());
		}
	}
	
	/**
	 * Class Genre is a nested class of its outer class. This class overrides
	 * compare method to compare genres between two Book objects.
	 * The Generic type is a Book type.
	 * 
	 * @author Raul Macedo Fuzita
	 */
	public static class Genre implements Comparator<Book> {

		@Override
		public int compare(Book book1, Book book2) {
			// Calls compareStrings method and returns a value between -1 and 1.
			return compareStrings(book1.getGenre(), book2.getGenre());
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

}
