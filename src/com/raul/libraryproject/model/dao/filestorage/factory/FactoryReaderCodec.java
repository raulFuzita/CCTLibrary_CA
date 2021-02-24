package com.raul.libraryproject.model.dao.filestorage.factory;

import com.raul.libraryproject.model.codecs.reader.json.JSONReaderDecoder;
import com.raul.libraryproject.model.codecs.reader.json.JSONReaderEncoder;
import com.raul.libraryproject.model.codecs.reader.object.ObjReaderDecoder;
import com.raul.libraryproject.model.codecs.reader.object.ObjReaderEncoder;
import com.raul.libraryproject.model.codecs.reader.text.TextReaderDecoder;
import com.raul.libraryproject.model.codecs.reader.text.TextReaderEncoder;
import com.raul.libraryproject.model.codecs.reader.xml.XMLReaderDecoder;
import com.raul.libraryproject.model.codecs.reader.xml.XMLReaderEncoder;
import com.raul.libraryproject.model.dao.filestorage.CodecDrive;
import com.raul.libraryproject.model.options.FileType;

/**
 * <p>
 * This class name is self-explanatory. It implements a Factory Design Pattern.
 * FactoryReaderCodec makes CodecDrive instances.
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
public class FactoryReaderCodec {

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
								new JSONReaderEncoder(), 
								new JSONReaderDecoder());
			break;
			
			case XML:
				codecDrive = 
						FactoryCodec.getCodecDrive(
								new XMLReaderEncoder("reader"), 
								new XMLReaderDecoder("reader"));
			break;
			
			case OBJECT:
				codecDrive = 
						FactoryCodec.getCodecDrive(
								new ObjReaderEncoder(), 
								new ObjReaderDecoder());
			break;
			
			case TEXT:
				codecDrive = 
						FactoryCodec.getCodecDrive(
								new TextReaderEncoder(), 
								new TextReaderDecoder());
			break;
	
			default:
				System.out.println("Inexistent Codec");
			break;
		}
		
		return codecDrive;
	}
}
