package com.raul.libraryproject.model.dao.filestorage.factory;

import com.raul.libraryproject.model.codecs.junctionentity.json.JSONJunctionDecoder;
import com.raul.libraryproject.model.codecs.junctionentity.json.JSONJunctionEncoder;
import com.raul.libraryproject.model.codecs.junctionentity.object.ObjJunctionDecoder;
import com.raul.libraryproject.model.codecs.junctionentity.object.ObjJunctionEncoder;
import com.raul.libraryproject.model.codecs.junctionentity.text.TextJunctionDecoder;
import com.raul.libraryproject.model.codecs.junctionentity.text.TextJunctionEncoder;
import com.raul.libraryproject.model.codecs.junctionentity.xml.XMLJunctionDecoder;
import com.raul.libraryproject.model.codecs.junctionentity.xml.XMLJunctionEncoder;
import com.raul.libraryproject.model.dao.filestorage.CodecDrive;
import com.raul.libraryproject.model.options.FileType;

/**
 * <p>
 * This class name is self-explanatory. It implements a Factory Design Pattern.
 * FactoryJunctionCodec makes CodecDrive instances.
 * For further information check out the methods documentation.
 * 
 * </p>
 * 
 * <p><b>Created at: </b>02/12/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @version 1.0.0
 *
 */
public class FactoryJunctionCodec {

	/**
	 * This method will return a CodecDrive instance 
	 * according to the following types:
	 * 
	 * <ul>
	 * 		<li>JSON</li>
	 * 		<li>XML</li>
	 * 		<li>OBJECT</li>
	 * 		<li>TEXT</li>
	 * </ul>
	 * 
	 * @param fileType is an Enum FileType object
	 * @return CodecDrive instance. If it no codec is made it'll return null.
	 */
	@SuppressWarnings("rawtypes")
	public static CodecDrive make(FileType fileType) {
		
		CodecDrive codecDrive = null;
		
		switch (fileType) {
			case JSON:
				codecDrive = 
						FactoryCodec.getCodecDrive(
								new JSONJunctionEncoder(), 
								new JSONJunctionDecoder());
			break;
			
			case XML:
				codecDrive = 
						FactoryCodec.getCodecDrive(
								new XMLJunctionEncoder("junction"), 
								new XMLJunctionDecoder("junction"));
			break;
			
			case OBJECT:
				codecDrive = 
						FactoryCodec.getCodecDrive(
								new ObjJunctionEncoder(), 
								new ObjJunctionDecoder());
			break;
			
			case TEXT:
				codecDrive = 
						FactoryCodec.getCodecDrive(
								new TextJunctionEncoder(), 
								new TextJunctionDecoder());
			break;
	
			default:
				System.out.println("Inexistent Codec");
			break;
		}
		
		return codecDrive;
	}
}
