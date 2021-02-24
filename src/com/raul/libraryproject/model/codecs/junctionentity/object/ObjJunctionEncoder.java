package com.raul.libraryproject.model.codecs.junctionentity.object;

import com.raul.libraryproject.converter.Codec;
import com.raul.libraryproject.model.junction.JunctionEntity;
import com.raul.libraryproject.util.datastructure.LinkedList;
import com.raul.libraryproject.util.datastructure.List;

/**
 * <p>class ObjJunctionEncoder that implements Codec interface from package:
 * com.raul.libraryproject.converter.
 * Encoder class encodes from type JunctionEntity to Object.
 * </p>
 * 
 * <p><b>Created at: </b>27/11/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @param JunctionEntity is to define the type object that is convert from
 * @param Object is to define the return type object 
 * 
 * @version 1.0.0
 *
 */
public class ObjJunctionEncoder implements Codec<JunctionEntity, Object> {

	/**
	 * @param entity is a JunctionEntity type
	 * @return Object object
	 */
	@Override
	public Object encode(JunctionEntity entity) {
		return entity;
	}

	/**
	 * @param junction is a JunctionEntity[]
	 * @return Object[]
	 */
	@Override
	public Object[] encode(JunctionEntity[] junction) {
		return junction;
	}

	/**
	 * @param junction is a {@code List<JunctionEntity>}
	 * @return {@code List<Object>}
	 */
	@Override
	public List<Object> encode(List<JunctionEntity> junction) {
		List<Object> objectList = new LinkedList<>();
		for (JunctionEntity entity : junction)
			objectList.add(entity);
		return objectList;
	}

}
