package com.raul.libraryproject.model.dao.filestorage;

import com.raul.libraryproject.converter.Codec;

/**
 * <p>
 * The purpose of this class, CodecDrive, is to wrap Codecs to be passed 
 * as one argument.
 * Sine a data can be encoded it also should be able to be decoded.
 * 
 * This technique is according the Argument Object design.
 * 
 * "When a function seems to need more than two or three arguments, it is
 * likely that some of those arguments ought to be wrapped into a class of
 * their own."
 * Clean Code - A Handbook of Agile Software Craftsmanship, Robert C. Martin,
 * Function Arguments, page 43.
 * 
 * This class works as a DTO (Data Transfer Object) class.
 * According to Robert C. Martin, book Clean Code, page 100, published
 * by Prentice Hall publishing company a DTO class should public variables
 * and no functions that doesn't provide any benefit.
 * </p>
 * 
 * <p><b>Created at: </b>02/12/2020</p>
 * 
 * @param <E> is to define the type object that is convert from.
 * @param <T> is to define the return type object
 * 
 * @author Raul Macedo Fuzita
 * 
 * @version 1.0.0
 *
 */
public class CodecDrive<E, T> {

	public Codec<E, T> encoder;
	public Codec<T, E> decoder;
	
	/**
	 * Wraps Codec encoder and decoder in one class.
	 * Second parameter has its two Generic types the opposite
	 * of first parameter. The decoder does the reverse process.
	 * 
	 * @param encoder is a Codec with two Generic types
	 * @param decoer is a Codec with two Generic types
	 */
	public CodecDrive(Codec<E, T> encoder, Codec<T, E> decoder) {
		this.encoder = encoder;
		this.decoder = decoder;
	}

}
