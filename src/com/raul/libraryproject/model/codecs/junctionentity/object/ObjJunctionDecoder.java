package com.raul.libraryproject.model.codecs.junctionentity.object;

import com.raul.libraryproject.converter.Codec;
import com.raul.libraryproject.model.junction.JunctionEntity;
import com.raul.libraryproject.util.datastructure.LinkedList;
import com.raul.libraryproject.util.datastructure.List;

/**
 * <p>class ObjJunctionDecoder that implements Codec interface from package:
 * com.raul.libraryproject.converter.
 * Decoder class decode from type Object to JunctionEntity.
 * </p>
 * 
 * <p><b>Created at: </b>27/11/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @param Object is to define the type object that is convert from
 * @param JunctionEntity is to define the return type object 
 * 
 * @version 1.0.0
 *
 */
public class ObjJunctionDecoder implements Codec<Object, JunctionEntity> {

	/**
	 * @param object is a Object object
	 * @return JunctionEntity
	 */
	@Override
	public JunctionEntity encode(Object object) {
		return (JunctionEntity) object;
	}

	/**
	 * @param objects is a Object[].
	 * @return JunctionEntity[]
	 */
	@Override
	public JunctionEntity[] encode(Object[] objects) {
		return (JunctionEntity[]) objects;
	}

	/**
	 * @param objects is a {@code List<Object>} object.
	 * @return {@code List<JunctionEntity>}
	 */
	@Override
	public List<JunctionEntity> encode(List<Object> objects) {
		List<JunctionEntity> junction = new LinkedList<>();
		for (Object object : objects)
			junction.add((JunctionEntity) object);
		return junction;
	}

}
