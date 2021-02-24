package com.raul.libraryproject.model.codecs.junctionentity.json;

import org.json.JSONObject;

import com.raul.libraryproject.converter.Codec;
import com.raul.libraryproject.model.junction.JunctionEntity;
import com.raul.libraryproject.util.datastructure.LinkedList;
import com.raul.libraryproject.util.datastructure.List;

/**
 * <p>class JSONJunctionEncoder that implements Codec interface from package:
 * com.raul.libraryproject.converter.
 * Encoder class encodes from type JSONObject to JSONObject.
 * </p>
 * 
 * <p><b>Created at: </b>27/11/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @param JunctionEntity is to define the type object that is convert from
 * @param JSONObject is to define the return type object 
 * 
 * @version 1.0.0
 *
 */
public class JSONJunctionEncoder implements Codec<JunctionEntity, JSONObject> {

	/**
	 * @param book is a JunctionEntity type
	 * @return JSONObject object
	 */
	@Override
	public JSONObject encode(JunctionEntity entity) {
		return toJSON(entity);
	}

	/**
	 * @param books is a JSONObject[]
	 * @return JSONObject[]
	 */
	@Override
	public JSONObject[] encode(JunctionEntity[] junction) {
		JSONObject[] jsonObj = new JSONObject[junction.length];
		for (int i = 0; i < junction.length; i++)
			jsonObj[i] = toJSON(junction[i]);
		return jsonObj;
	}

	/**
	 * @param books is a {@code List<JunctionEntity>}
	 * @return {@code List<JSONObject>}
	 */
	@Override
	public List<JSONObject> encode(List<JunctionEntity> junction) {
		List<JSONObject> jsonObjList = new LinkedList<>();
		for (JunctionEntity entity : junction)
			jsonObjList.add(toJSON(entity));
		return jsonObjList;
	}

	/**
	 * This method takes the JunctionEntity properties and 
	 * creates JSON data out of them.
	 * 
	 * @param entity is a JunctionEntity type.
	 * @return JSONObject object.
	 */
	protected JSONObject toJSON(JunctionEntity entity) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("ownerId", entity.ownerId);
		jsonObj.put("belongId", entity.belongId);
		return jsonObj;
	}
	
}
