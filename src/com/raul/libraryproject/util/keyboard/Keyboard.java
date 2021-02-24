package com.raul.libraryproject.util.keyboard;

import java.util.Scanner;

/**
 * Keyboard class provides two static methods for input data,
 * string text and integer number input.
 * 
 * @version 1.0.0
 * @author Raul Macedo Fuzita
 */
public class Keyboard {
	
	/* Keyboard class has a property input that is an object
	 * instance of Scanner. The access modifier public
	 * will be only accessed by the class method.
	 * Since all methods are static for quick access so the 
	 * property. */
	private static Scanner input = new Scanner(System.in);

	/**
	 * text method allows to input any type of data and returns it.
	 * 
	 * @param prompt is the message displayed before the input.
	 * @return String value.
	 */
	public static String text(String prompt) {
		// Displays message
		System.out.println(prompt);
		return input.nextLine();// gets input and returns it.
	}
	
	/**
	 * number method allows to input only integer numbers and 
	 * returns it.
	 * 
	 * @param prompt is the message displayed before the input.
	 * @return Integer value.
	 */
	public static int number(String prompt) {
		/* This variable has zero as default. It's meant
		 * to store the input value. */
		int value = 0;
		/* A Try/Catch will prevent an Exception be thrown
		 * when the input is not a integer number.
		 * If it happens the method is called recursively
		 * until a valid value. */
		try {
			// Displays message
			System.out.println(prompt);
			// Stores the input in a variable.
			value = input.nextInt();
		} catch (NumberFormatException nfe) {
			// calls the method itself.
			number(prompt);
		}
		return value;
	}
}
