package com.raul.libraryproject.model.codecs.junctionentity.text;

import com.raul.libraryproject.converter.Codec;
import com.raul.libraryproject.model.junction.JunctionEntity;
import com.raul.libraryproject.util.datastructure.LinkedList;
import com.raul.libraryproject.util.datastructure.List;

/**
 * <p>class TextBookEncoder that implements Codec interface from package:
 * com.raul.libraryproject.converter.
 * Encoder class encodes from type JunctionEntity to String.
 * </p>
 * 
 * <p><b>Created at: </b>27/11/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @param JunctionEntity is to define the type object that is convert from
 * @param String is to define the return type object 
 * 
 * @version 1.0.0
 *
 */
public class TextJunctionEncoder implements Codec<JunctionEntity, String> {

	/**
	 * @param entity is a JunctionEntity type
	 * @return String object
	 */
	@Override
	public String encode(JunctionEntity entity) {
		return toText(entity);
	}

	/**
	 * @param books is a JunctionEntity[]
	 * @return String[]
	 */
	@Override
	public String[] encode(JunctionEntity[] junction) {
		String[] stringArray = new String[junction.length];
		for (int i = 0; i < stringArray.length; i++)
			stringArray[i] = toText(junction[i]);
		return stringArray;
	}

	/**
	 * @param junction is a {@code List<JunctionEntity>}
	 * @return {@code List<String>}
	 */
	@Override
	public List<String> encode(List<JunctionEntity> junction) {
		List<String> stringList = new LinkedList<>();
		for (JunctionEntity entity : junction)
			stringList.add(toText(entity));
		return stringList;
	}
	
	/**
	 * This method takes the JunctionEntity properties and 
	 * creates String data out of them.
	 * 
	 * @param entity is a JunctionEntity type.
	 * @return String object.
	 */
	private String toText(JunctionEntity entity) {
		String data = entity.ownerId + ";" + entity.belongId;
		return data;
	}

}
