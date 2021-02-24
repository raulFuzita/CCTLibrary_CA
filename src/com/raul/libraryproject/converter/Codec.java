package com.raul.libraryproject.converter;

import com.raul.libraryproject.util.datastructure.List;

/**
 * <p>Interface Codec is meant to abstract an encoder and a decoder.
 * This class has two parameters that should be defined
 * by the class that implements this interface.
 * </p>
 * 
 * <p><b>Created at: </b>27/11/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @param <E> is to define the type object that is convert from
 * @param <T> is to define the return type object
 * 
 * @version 1.0.0
 *
 */
public interface Codec<E, T> {
	
	/**
	 * This method encode from E Generic type to T Generic Type
	 * 
	 * @param e is a type E Generic
	 * @return T which is a type T Generic
	 */
	public T encode(E e);
	
	/**
	 * This method encode from E[] Generic type to T[] Generic Type
	 * 
	 * @param e is a type E[] Generic
	 * @return T[] which is a type T[] Generic
	 */
	public T[] encode(E[] e);
	
	/**
	 * This method encode from {@code List<E>} Generic type 
	 * to {@code List<T>} Generic Type
	 * 
	 * @param e is a type {@code List<E>} Generic
	 * @return {@code List<T>} which is a type {@code List<T>} Generic
	 */
	public List<T> encode(List<E> e);
	
}
