package com.raul.libraryproject.util.processors;

/**
 * <p>A byte processor. Any object converted to an array of byte
 * can be processed. Whatever implementation to encode a data
 * it is recommended to have a decode process.
 * </p>
 * 
 * <p><b>Created at: </b>23/11/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @version 1.0.0
 *
 */
public interface Processor {

	/**
	 * This method takes an array of byte, process it and
	 * then return an array of byte again.
	 * 
	 * @param bytes is an array of bytes.
	 * @return byte[] is an array of bytes.
	 */
	public byte[] process(byte[] bytes);
}
