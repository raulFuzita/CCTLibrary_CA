package com.raul.libraryproject.model.dao.filestorage;

import java.io.File;

import com.raul.libraryproject.converter.Converter;
import com.raul.libraryproject.model.dao.DAO;
import com.raul.libraryproject.util.datastructure.LinkedList;
import com.raul.libraryproject.util.datastructure.List;
import com.raul.libraryproject.util.exceptionsupressor.UncheckedException;
import com.raul.libraryproject.util.filegenerator.FileGenerator;
import com.raul.libraryproject.util.fileloader.FileLoader;

/**
 * <p>
 * FileManagerDAO implements DAO. Since this class operates the basic
 * data access and has to store or retrieve the data form a file
 * it depends on a FileDrive and CodecDrive class to operates as such.
 * </p>
 * 
 * <p><b>Created at: </b>02/12/2020</p>
 * 
 * @param <E> is to define the type object that is convert from.
 * @param <T> is to define the return type object
 * 
 * @author Raul Macedo Fuzita
 * 
 * @version 1.0.0
 *
 */
public class FileManagerDAO<E, T> implements DAO<E> {

	// Attributes are protected to be access from classes in its package
	protected FileDrive<T> fileDrive;
	protected CodecDrive<E, T> codecDrive;
	protected List<E> list = new LinkedList<>();
	protected boolean readFromCache; // Works as a cache flag
	
	/**
	 * This constructor sets previously the FileDrive and CodecDrive.
	 * Both parameters takes Generic types. Keep that in mind.
	 * 
	 * @param fileDrive is a FileDrive object that presumes its attributes
	 * are assigned to the due to instances. It requires one Generic type.
	 * 
	 * @param codecDrive is a CodecDrive object that presumes its attributes
	 * are assigned to the due to instances. It requires two Generic types.
	 */
	public
	FileManagerDAO(FileDrive<T> fileDrive, CodecDrive<E, T> codecDrive) {
		this.fileDrive = fileDrive;
		this.codecDrive = codecDrive;
		this.readFromCache = true; // Sets a default value for "cache"
	}

	/**
	 * <p>This method uses internally a FileLoader to operates 
	 * reading from a file.
	 * The file content once loaded it is decoded by the Codec decoder.
	 * Since a file has been just loaded, variable readFromCache is set to
	 * {@code true} to signal it that data next retrieve operation can
	 * be retrieved from the cache to improve performance.
	 * 
	 * Be aware this method throws a unchecked Exception. It actually uses
	 * an exception suppressor from package:
	 * {@linkplain com.raul.libraryproject.util.exceptionsupressor}
	 * </p>
	 * 
	 * <p> All the exception is passed to UncheckedException, because FileLoader
	 * is an abstract class that can have a lots of different file loaders
	 * and their algorithm might throw unpredictable exceptions.
	 * </p>
	 * 
	 * @throws UncheckedException is an exception suppressor. If any exception
	 * is thrown it'll be suppressed, but by using UncheckedException 
	 * getException methods is possible to know exactly exception was thrown.
	 * 
	 */
	public void loadFile() {
		try {
			File file = fileDrive.file;
			FileLoader<T> fileLoader = fileDrive.fileLoader;
			List<T> dataList = fileLoader.loadFile(file);
			// Decoding data
			list = Converter.ListConvert(dataList, codecDrive.decoder);
//			System.out.println("File read successfully!");
			this.readFromCache = true;
		} catch (Exception e) {
			throw new UncheckedException(e);
		}
	}
	
	/**
	 * <p>
	 * This method uses internally a FileGenerator to operates 
	 * writing in a file.
	 * The file content once loaded it is encoded by the Codec encoder.
	 * Since a file has been just generated, variable readFromCache is set to
	 * {@code false} to signal it that data next retrieve operation has to
	 * be reload from a file again. It indicates that changes were made to
	 * the data.
	 * 
	 * Be aware this method throws a unchecked Exception. It actually uses
	 * an exception suppressor from package:
	 * {@linkplain com.raul.libraryproject.util.exceptionsupressor}
	 * </p>
	 * 
	 * <p> 
	 * All the exception is passed to UncheckedException, because FileGenerator
	 * is an abstract class that can have a lots of different file loaders
	 * and their algorithm might throw unpredictable exceptions.
	 * </p>
	 * 
	 * @throws UncheckedException is an exception suppressor. If any exception
	 * is thrown it'll be suppressed, but by using UncheckedException 
	 * getException methods is possible to know exactly exception was thrown.
	 * 
	 */
	public void generateFile() {
		try {
			File file = fileDrive.file;
			FileGenerator<T> fileGenerator = fileDrive.fileGenerator;
			List<T> dataList = Converter.ListConvert(list, codecDrive.encoder);
			fileGenerator.generateFile(dataList, file);
//			System.out.println("File created successfully!");
			this.readFromCache = false;
		} catch (Exception e) {
			throw new UncheckedException(e);
		}
	}

	/**
	 * Variable readFromCache will be checked to decide rather it should
	 * retrieve data from a file or "cache memory".
	 * 
	 * @param id is an Object but internally it'll be cast to {@code int}.
	 */
	@Override
	public E getById(Object id) {
		if (!readFromCache)
			loadFile();
		return this.list.get((int) id);
	}
	
	/**
	 * A new data will be added to a "cache" and any change has to be
	 * updated in the source file.
	 */
	@Override
	public void add(E entity) {
		this.list.add(entity);
		generateFile();
	}

	/**
	 * A new data will be added to a "cache" and any change has to be
	 * updated in the source file.
	 * 
	 * @param id is an Object but internally it'll be cast to {@code int}.
	 */
	@Override
	public void update(Object id, E entity) {
		this.list.set((int) id, entity);
		generateFile();
	}

	/**
	 * A new data will be removed to a "cache" and any change has to be
	 * updated in the source file.
	 */
	@Override
	public void remove(int id) {
		this.list.remove(id);
		generateFile();
	}
	
	/**
	 * A new data will be removed to a "cache" and any change has to be
	 * updated in the source file.
	 */
	@Override
	public void remove(Object id) {
		this.list.remove(id);
		generateFile();
	}

	/**
	 * Variable readFromCache will be checked to decide rather it should
	 * retrieve data from a file or "cache memory".
	 */
	@Override
	public List<E> getAll() {
		if (!readFromCache)
			loadFile();
		return this.list;
	}

	/**
	 * A new data will be added to a "cache" and any change has to be
	 * updated in the source file.
	 */
	@Override
	public void addAll(List<E> list) {
		this.list = list;
		generateFile();
	}
	
}
