package com.raul.libraryproject.model.codecs.junctionentity.text;

import com.raul.libraryproject.converter.Codec;
import com.raul.libraryproject.model.junction.JunctionEntity;
import com.raul.libraryproject.util.datastructure.LinkedList;
import com.raul.libraryproject.util.datastructure.List;

/**
 * <p>class TextBookDecoder that implements Codec interface from package:
 * com.raul.libraryproject.converter.
 * Decoder class decode from type String to JunctionEntity.
 * </p>
 * 
 * <p><b>Created at: </b>27/11/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @param String is to define the type object that is convert from
 * @param JunctionEntity is to define the return type object 
 * 
 * @version 1.0.0
 *
 */
public class TextJunctionDecoder implements Codec<String, JunctionEntity> {

	// Regular Expression that each valid data must match
	private String pattern = "[0-9]+;[0-9]+";
	
	/**
	 * @param string is a String object
	 * @return JunctionEntity
	 */
	@Override
	public JunctionEntity encode(String string) {
		return textTo(string);
	}

	/**
	 * @param strings is a String[].
	 * @return JunctionEntity[]
	 */
	@Override
	public JunctionEntity[] encode(String[] strings) {
		JunctionEntity[] junction = new JunctionEntity[strings.length];
		for (int i = 0; i < strings.length; i++)
			junction[i] = textTo(strings[i]);
		return junction;
	}

	/**
	 * @param strings is a {@code List<String>} object.
	 * @return {@code List<JunctionEntity>}
	 */
	@Override
	public List<JunctionEntity> encode(List<String> strings) {
		List<JunctionEntity> junction = new LinkedList<>();
		for (String string : strings) {
			String[] splitedString = string.split("\n");
			for (int i = 0; i < splitedString.length; i++) {
				JunctionEntity entity = textTo(splitedString[i]);
				if (entity != null)
					junction.add(entity);
			}
		}
		return junction;
	}
	
	/**
	 * This method convert Text data to primitive or String object.
	 * Then the values are used to instantiate a JunctionEntity object.
	 * 
	 * @param string is a String object
	 * @return JunctionEntity
	 */
	private JunctionEntity textTo(String string) {
		if (string.matches(pattern)) {
			String[] data = string.split(";");
			Long ownerId = Long.parseLong(data[0]);
			Long belongId = Long.parseLong(data[1]);
			return new JunctionEntity(ownerId, belongId);
		}
		return null;
	}

}
