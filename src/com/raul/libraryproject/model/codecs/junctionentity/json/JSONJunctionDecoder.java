package com.raul.libraryproject.model.codecs.junctionentity.json;

import org.json.JSONArray;
import org.json.JSONObject;

import com.raul.libraryproject.converter.Codec;
import com.raul.libraryproject.model.junction.JunctionEntity;
import com.raul.libraryproject.util.datastructure.LinkedList;
import com.raul.libraryproject.util.datastructure.List;

/**
 * <p>class JSONJunctionDecoder that implements Codec interface from package:
 * com.raul.libraryproject.converter.
 * Decoder class decode from type JSONObject to JunctionEntity.
 * </p>
 * 
 * <p><b>Created at: </b>27/11/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @param JSONObject is to define the type object that is convert from
 * @param JunctionEntity is to define the return type object 
 * 
 * @version 1.0.0
 *
 */
public class JSONJunctionDecoder implements Codec<JSONObject, JunctionEntity> {
	// Sets the object root for a JSON file.
	private String rootObject = "object";
	// Simple constructor
	public JSONJunctionDecoder() {}
	
	/**
	 * This constructor sets the root object name.
	 * If the argument doesn't match the requirements
	 * the value won't change and the value will
	 * remain the default one.
	 * @param rootObject is a String object.
	 */
	public JSONJunctionDecoder(String rootObject) {
		super();
		setRootObject(rootObject);
	}

	/**
	 * This method returns root object name.
	 * @return rootObject that is a String object.
	 */
	public String getRootObject() {
		return rootObject;
	}

	/**
	 * The argument parameter must match the following rules:
	 * Valid range: a-z A-Z 0-9 and underscore.
	 * If the argument doean't match the rule the root object name
	 * will remain the default one or the previous valid one.
	 * 
	 * @param rootObject is a String object.
	 * @return true if the change has been applied. Otherwise false.
	 */
	public boolean setRootObject(String rootObject) {
		if (rootObject.matches("[a-zA-Z0-9_]+")) {
			this.rootObject = rootObject;
			return true;
		}
		return false;
	}

	/**
	 * @param jsonObj is a JSONObject object
	 * @return JunctionEntity
	 */
	@Override
	public JunctionEntity encode(JSONObject jsonObj) {
		JSONArray jsonArray = jsonObj.getJSONArray(rootObject);
		if (jsonArray.length() <= 0)
			return null;
		return JSONTo((JSONObject) jsonArray.get(0));
	}

	/**
	 * @param jsonObjs is a JSONObject[].
	 * @return JunctionEntity[]
	 */
	@Override
	public JunctionEntity[] encode(JSONObject[] jsonObjs) {
		JunctionEntity[] junction = new JunctionEntity[jsonObjs.length];
		for (int i = 0; i < jsonObjs.length; i++) {
			JSONArray jsonArray = jsonObjs[i].getJSONArray(rootObject);
			if (jsonArray.length() <= 0)
				continue;
			junction[i] = JSONTo((JSONObject) jsonArray.get(i));
		}
		return junction;
	}

	/**
	 * @param jsonObjs is a {@code List<JSONObject>} object.
	 * @return {@code List<JunctionEntity>}
	 */
	@Override
	public List<JunctionEntity> encode(List<JSONObject> jsonObjs) {
		List<JunctionEntity> junction = new LinkedList<>();
		for (JSONObject jsonObj : jsonObjs) {
			JSONArray jsonArray = jsonObj.getJSONArray(rootObject);
			int jsonArraySize = jsonArray.length();
			for (int i = 0; i < jsonArraySize; i++) {
				JunctionEntity entity = JSONTo((JSONObject) jsonArray.get(i));
				junction.add(entity);
			}
		}
		return junction;
	}
	
	/**
	 * This method convert JSON data to primitive or String object.
	 * Then the values are used to instantiate a JunctionEntity object.
	 * 
	 * @param jsonObj is a JSONObject object
	 * @return JunctionEntity
	 */
	protected JunctionEntity JSONTo(JSONObject jsonObj) {
		long ownerId = Long.parseLong(jsonObj.get("ownerId").toString());
		long belongId = Integer.parseInt(jsonObj.get("belongId").toString());
		return new JunctionEntity(ownerId, belongId);
	}

}
