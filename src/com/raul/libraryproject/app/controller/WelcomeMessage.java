package com.raul.libraryproject.app.controller;

import com.raul.libraryproject.util.keyboard.Keyboard;

/**
 * <p>
 * WelcomeMessage only displays a welcome message
 * </p>
 * 
 * <p><b>Created at: </b>04/12/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @version 1.0.0
 *
 */
public class WelcomeMessage {

	public WelcomeMessage() {

		System.out.println("\n************ LIBRARY SYSTEM *************");
		System.out.println("*                                       *");
		System.out.println("* Welcome to Raul's Library System      *");
		System.out.println("* This system is able to manage book    *");
		System.out.println("* loans.                                *");
		System.out.println("*                                       *");
		System.out.println("* Library System Vertion: 1.0.0         *");
		System.out.println("* Launch Date: 04/12/2020               *");
		System.out.println("* Author: Raul Macedo Fuzita            *");
		System.out.println("* Email: raul.fuzita@gmail.com          *");
		System.out.println("*                                       *");
		System.out.println("*****************************************");
		
		Keyboard.text("# Press any key to contitnue: ");
	}
}
