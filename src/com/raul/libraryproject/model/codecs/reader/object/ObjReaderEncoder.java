package com.raul.libraryproject.model.codecs.reader.object;

import com.raul.libraryproject.converter.Codec;
import com.raul.libraryproject.model.reader.Reader;
import com.raul.libraryproject.util.datastructure.LinkedList;
import com.raul.libraryproject.util.datastructure.List;

/**
 * <p>class ObjReaderEncoder that implements Codec interface from package:
 * com.raul.libraryproject.converter.
 * Encoder class encodes from type Reader to Object.
 * </p>
 * 
 * <p><b>Created at: </b>27/11/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @param Reader is to define the type object that is convert from
 * @param Object is to define the return type object 
 * 
 * @version 1.0.0
 *
 */
public class ObjReaderEncoder implements Codec<Reader, Object> {

	/**
	 * @param reader is a Reader type
	 * @return Object object
	 */
	@Override
	public Object encode(Reader reader) {
		return reader;
	}

	/**
	 * @param readers is a Reader[]
	 * @return Object[]
	 */
	@Override
	public Object[] encode(Reader[] readers) {
		return readers;
	}

	/**
	 * @param readers is a {@code List<Reader>}
	 * @return {@code List<Object>}
	 */
	@Override
	public List<Object> encode(List<Reader> readers) {
		List<Object> objectList = new LinkedList<>();
		for (Reader reader : readers)
			objectList.add(reader);
		return objectList;
	}

}
