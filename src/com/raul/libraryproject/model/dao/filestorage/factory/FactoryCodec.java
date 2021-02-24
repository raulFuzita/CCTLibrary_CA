package com.raul.libraryproject.model.dao.filestorage.factory;

import com.raul.libraryproject.converter.Codec;
import com.raul.libraryproject.model.dao.filestorage.CodecDrive;

/**
 * <p>
 * This class name is self-explanatory. It implements a Factory Design Pattern.
 * FactoryCodec makes CodecDrive instances.
 * For further information check out the methods documentation.
 * 
 * </p>
 * 
 * <p><b>Created at: </b>02/12/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @version 1.0.0
 *
 */
public abstract class FactoryCodec {
	
	/**
	 * Wraps Codec encoder and decoder in one class.
	 * Second parameter has its two Generic types the opposite
	 * of first parameter. The decoder does the reverse process.
	 * 
	 * @param <E> is to define the type object that is convert from.
	 * @param <T> is to define the return type object
	 * @param encoder is a Codec with two Generic types
	 * @param decoder is a Codec with two Generic types
	 * @return {@code CodecDrive<E, T>} object.
	 * 
	 */
	public static <E, T> CodecDrive<E, T> 
	getCodecDrive(Codec<E, T> encoder, Codec<T, E> decoder){
		return new CodecDrive<E, T>(encoder, decoder);
	}
}
