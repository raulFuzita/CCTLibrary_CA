package com.raul.libraryproject.model.reader;

import java.time.LocalDate;
import java.util.Comparator;

/**
 * <p>Class ReaderComparator implements Comparator. This class is meant to work
 * with Collection of the package 
 * com.raul.libraryproject.util.datastructure.Collections.
 * ReaderComparator has nested classes to sort or search a
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
 * @apiNote This class (ReaderComparator) overrides compare method.
 * Careful, this class might not fully work with java.util.Collections.
 *
 */
public class ReaderComparator {
	
	/**
	 * Class ID is a nested class of its outer class. This class overrides
	 * compare method to compare IDs between two Reader objects.
	 * The Generic type is a Reader type.
	 * 
	 * @author Raul Macedo Fuzita
	 */
	public static class ID implements Comparator<Reader> {

		@Override
		public int compare(Reader reader1, Reader reader2) {
			/*
			 * The easiest way to deal with integer comparison is to subtract
			 * the first element by the second one. If the second one is
			 * bigger than the first it's expected a negative number.
			 * If the second element is smaller the result should be a positive
			 * number. If they are equal the result will be zero.
			 */
			return (int) (reader1.getId() - reader2.getId());
		}
		
	}

	/**
	 * Class Name is a nested class of its outer class. This class overrides
	 * compare method to compare names between two Reader objects.
	 * The Generic type is a Reader type.
	 * 
	 * @author Raul Macedo Fuzita
	 */
	public static class Name implements Comparator<Reader> {

		@Override
		public int compare(Reader reader1, Reader reader2) {
			// Calls compareStrings method and returns a value between -1 and 1.
			return compareStrings(reader1.getName(), reader2.getName());
		}
		
	}
	
	/**
	 * Class Surname is a nested class of its outer class. This class overrides
	 * compare method to compare surnames between two Reader objects.
	 * The Generic type is a Reader type.
	 * 
	 * @author Raul Macedo Fuzita
	 */
	public static class Surname implements Comparator<Reader> {

		@Override
		public int compare(Reader reader1, Reader reader2) {
			// Calls compareStrings method and returns a value between -1 and 1.
			return compareStrings(reader1.getSurname(), reader2.getSurname());
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
	 * Class Birthday is a nested class of its outer class. This class overrides
	 * compare method to compare birthdays between two Reader objects.
	 * The Generic type is a Reader type.
	 * 
	 * @author Raul Macedo Fuzita
	 */
	public static class Birthday implements Comparator<Reader> {

		@Override
		public int compare(Reader reader1, Reader reader2) {
			/* Calls compareLocalDates method and 
			 * returns a value between -1 and 1. */
			return compareLocalDates(
					reader1.getBirthdate(), 
					reader2.getBirthdate());
		}
		
	}
	
	/**
	 * This method uses compareTo internally to compare two LocalDates.
	 * If the result is bigger than zero it returns 1. If the result
	 * is smaller than zero it returns -1. Otherwise it returns 0.
	 * 
	 * @param localDate1 is a LocalDate type.
	 * @param localDate2 is a LocalDate type.
	 * @return integer number between -1 and 1.
	 */
	private static int 
	compareLocalDates(LocalDate localDate1, LocalDate localDate2) {
		if (localDate1.compareTo(localDate2) > 0)
			return 1;
		else if (localDate1.compareTo(localDate2) < 0)
			return -1;
		return 0;
	}
	
}
