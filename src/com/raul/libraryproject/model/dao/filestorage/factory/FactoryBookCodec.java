package com.raul.libraryproject.model.dao.filestorage.factory;

import com.raul.libraryproject.model.codecs.book.json.JSONBookDecoder;
import com.raul.libraryproject.model.codecs.book.json.JSONBookEncoder;
import com.raul.libraryproject.model.codecs.book.object.ObjBookDecoder;
import com.raul.libraryproject.model.codecs.book.object.ObjBookEncoder;
import com.raul.libraryproject.model.codecs.book.text.TextBookDecoder;
import com.raul.libraryproject.model.codecs.book.text.TextBookEncoder;
import com.raul.libraryproject.model.codecs.book.xml.XMLBookDecoder;
import com.raul.libraryproject.model.codecs.book.xml.XMLBookEncoder;
import com.raul.libraryproject.model.dao.filestorage.CodecDrive;
import com.raul.libraryproject.model.options.FileType;

/**
 * <p>
 * This class name is self-explanatory. It implements a Factory Design Pattern.
 * FactoryBookCodec makes CodecDrive instances.
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
public class FactoryBookCodec {

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
								new JSONBookEncoder(), 
								new JSONBookDecoder());
			break;
			
			case XML:
				codecDrive = 
						FactoryCodec.getCodecDrive(
								new XMLBookEncoder("book"), 
								new XMLBookDecoder("book"));
			break;
			
			case OBJECT:
				codecDrive = 
						FactoryCodec.getCodecDrive(
								new ObjBookEncoder(), 
								new ObjBookDecoder());
			break;
			
			case TEXT:
				codecDrive = 
						FactoryCodec.getCodecDrive(
								new TextBookEncoder(), 
								new TextBookDecoder());
			break;
	
			default:
				System.out.println("Inexistent Codec");
			break;
		}
		
		return codecDrive;
	}

}
