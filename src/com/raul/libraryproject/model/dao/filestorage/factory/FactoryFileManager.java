package com.raul.libraryproject.model.dao.filestorage.factory;

import com.raul.libraryproject.model.dao.filestorage.CodecDrive;
import com.raul.libraryproject.model.dao.filestorage.FileDrive;
import com.raul.libraryproject.model.dao.filestorage.FileManagerDAO;
import com.raul.libraryproject.model.options.FileType;

/**
 * <p>
 * This class name is self-explanatory. It implements a Factory Design Pattern.
 * FactoryFileDrive makes FileDrive instances.
 * For further information check out the methods documentation.
 * </p>
 * 
 * <p><b>Created at: </b>02/12/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @version 1.0.0
 *
 */
public abstract class FactoryFileManager { 
	
	/**
	 * The purpose of FactoryFileManager invokes make is to 
	 * return an instance of FileManagerDAO. Since this instance
	 * will have different type for its generic types the 
	 * type of the instance is suppressed.
	 * 
	 * @param entityName is a String object.
	 * @param fileType is an Enum FileType object.
	 * @param path is a String object.
	 * @return {@code FileManagerDAO}.
	 */
	@SuppressWarnings({ "unchecked" })
	public static <E, T> FileManagerDAO<E, T> 
	make(String entityName, FileType fileType, String path) {

		FileDrive<T> fileDrive = FactoryFileDrive.make(path, fileType);
		CodecDrive<E, T> codecDrive = FactoryLibraryCodec.make(entityName, fileType);
		return getFileManager(fileDrive, codecDrive);
	}
	
	/**
	 * This method returns a {@code FileManagerDAO<E, T>} instance
	 * by passing a {@code FileDrive<T>} and {@code CodecDrive<E, T>}
	 * object.
	 * 
	 * @param <E> is to define the type object that is convert from.
	 * @param <T> is to define the return type object
	 * @param fileDrive is a {@code FileDrive<T>} object.
	 * @param codecDrive is a {@code CodecDrive<E, T>} object.
	 * @return
	 */
	private static <E, T> FileManagerDAO<E, T> 
	getFileManager (FileDrive<T> fileDrive, CodecDrive<E, T> codecDrive){
		return new FileManagerDAO<E, T>(fileDrive, codecDrive);
	}
}
