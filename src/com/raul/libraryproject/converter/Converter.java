package com.raul.libraryproject.converter;

import com.raul.libraryproject.util.datastructure.List;

/**
 * <p>Class Converter adds an extra layer of abstraction to use a concrete
 * class that implements Codec interface.
 * An instance that implements Codec can work well with overload. However,
 * in order to take advantage of the interface a problem can arise since
 * Codec has more than one method that has a Generic parameter.
 * During the compiling time the compiler doesn't know for instance, if a type
 * {@code List<E>} is a list or an object like {@code <E>}. An interface List
 * can also be declared without any type. The IDE will warn that the object
 * type is not checked which is easy to suppress it. The best way to handle
 * such a scenario is assign different names to each method to solve any
 * ambiguity.
 * </p>
 * 
 * Check out more about Generic types and ambiguity problems
 * <p>
 * {@link <a href="https://stackoverflow.com/questions/18460239/java-generic-method-overloading-ambiguity">
 * Java: Generic method overloading ambiguity
 * </a>}
 * </p>
 * 
 * <p><b>Created at: </b>27/11/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @version 1.0.0
 *
 */
public class Converter {
	
	// Private constructor, class meant to be call statically.
	private Converter() {}
	
	/**
	 * This method uses Generic method to define a signature type for
	 * the first and second parameter of the method.
	 * 
	 * @param <E> is a Generic type.
	 * @param <T> is a Generic type.
	 * @param e is any object type.
	 * @param c is an class that implements Codec.
	 * @return T that is the type of second Codec parameter.
	 */
	public static <E, T> T convert(E e, Codec<E, T> c) {
		return c.encode(e);
	}
	
	/**
	 * This method uses Generic method to define a signature type for
	 * the first and second parameter of the method.
	 * 
	 * @param <E> is a Generic type.
	 * @param <T> is a Generic type.
	 * @param e is any object type.
	 * @param c is an class that implements Codec.
	 * @return T[] that is the type of second Codec parameter.
	 */
	public static <E, T> T[] ArrayConvert(E[] e, Codec<E, T> c) {
		return c.encode(e);
	}
	
	/**
	 * This method uses Generic method to define a signature type for
	 * the first and second parameter of the method.
	 * 
	 * @param <E> is a Generic type.
	 * @param <T> is a Generic type.
	 * @param e is any object type.
	 * @param c is an class that implements Codec.
	 * @return {@code List<T>} that is the type of second Codec parameter.
	 */
	public static <E, T> List<T> ListConvert(List<E> e, Codec<E, T> c) {
		return c.encode(e);
	}
}
