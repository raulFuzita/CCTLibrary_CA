package com.raul.libraryproject.util.processors;

/**
 * <p>Class NullProcessor implements the design pattern NullObject.
 * If bytes should remain without any changes. Consider to implement
 * this class. This class can also be used as a default processor
 * when there is no implementation of processor.
 * </p>
 * 
 * <p><b>Created at: </b>23/11/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @version 1.0.0
 *
 */
public class NullProcessor implements Processor {

	/*
	 * Method's documentation is available in 
	 * Processor interface. No need to
	 * make any change on it for this method. 
	 */
	@Override
	public byte[] process(byte[] bytes) {
		return bytes;
	}

}
