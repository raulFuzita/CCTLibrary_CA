package com.raul.libraryproject.model.codecs.reader.object;

import com.raul.libraryproject.converter.Codec;
import com.raul.libraryproject.model.reader.Reader;
import com.raul.libraryproject.util.datastructure.LinkedList;
import com.raul.libraryproject.util.datastructure.List;

/**
 * <p>class ObjReaderDecoder that implements Codec interface from package:
 * com.raul.libraryproject.converter.
 * Decoder class decode from type Object to Reader.
 * </p>
 * 
 * <p><b>Created at: </b>27/11/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @param Object is to define the type object that is convert from
 * @param Reader is to define the return type object 
 * 
 * @version 1.0.0
 *
 */
public class ObjReaderDecoder implements Codec<Object, Reader> {

	/**
	 * @param object is a Object object
	 * @return Reader
	 */
	@Override
	public Reader encode(Object object) {
		return (Reader) object;
	}

	/**
	 * @param objects is a Object[].
	 * @return Reader[]
	 */
	@Override
	public Reader[] encode(Object[] objects) {
		return (Reader[]) objects;
	}

	/**
	 * @param objects is a {@code List<Object>} object.
	 * @return {@code List<Reader>}
	 */
	@Override
	public List<Reader> encode(List<Object> objectList) {
		List<Reader> readers = new LinkedList<>();
		for (Object object : objectList)
			readers.add((Reader) object);
		return readers;
	}

}
