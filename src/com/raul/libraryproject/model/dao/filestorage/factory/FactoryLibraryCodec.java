package com.raul.libraryproject.model.dao.filestorage.factory;

import com.raul.libraryproject.model.dao.filestorage.CodecDrive;
import com.raul.libraryproject.model.options.FileType;

/**
 * <p>
 * This class name is self-explanatory. It implements a Factory Design Pattern.
 * FactoryLibraryCodec makes CodecDrive instances.
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
public abstract class FactoryLibraryCodec {

	/**
	 * This method returns a CodecDrive object.
	 * It is expected that the String entityName is the class name it's
	 * wanted to instance. The file extension is defined by the 
	 * Enum FileType object.
	 * 
	 * Possibles accept values for method make:
	 * 
	 * <ul>
	 * 		<li>JSON</li>
	 * 		<li>XML</li>
	 * 		<li>OBJECT</li>
	 * 		<li>TEXT</li>
	 * </ul>
	 * 
	 * <p>
	 * If none of the options above matches it'll return a null.
	 * </p>
	 * 
	 * @param entityName is a String object.
	 * @param fileType is an Enum FileType object.
	 * @return CodecDrive object.
	 */
	@SuppressWarnings("rawtypes")
	public static CodecDrive make(String entityName, FileType fileType) {
		
		switch (entityName) {
			case "Book":
				return FactoryBookCodec.make(fileType);
				
			case "Reader":
				return FactoryReaderCodec.make(fileType);
				
			case "Junction":
				return FactoryJunctionCodec.make(fileType);
				
			default:
				System.out.println("Non-existent library codec");
		}
		return null;
	}
}
