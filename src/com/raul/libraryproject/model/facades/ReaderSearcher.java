package com.raul.libraryproject.model.facades;

import java.util.Comparator;
import java.util.NoSuchElementException;

import com.raul.libraryproject.model.dao.DAO;
import com.raul.libraryproject.model.reader.Reader;
import com.raul.libraryproject.model.reader.ReaderComparator;
import com.raul.libraryproject.model.reader.ReaderRelativeComparator;
import com.raul.libraryproject.util.datastructure.LinkedList;
import com.raul.libraryproject.util.datastructure.List;
import com.raul.libraryproject.util.keyboard.Keyboard;

/**
 * <p>
 * ReaderSearcher provides a limited option of functionality to search
 * a Reader that has a concrete class that implements Comparator.
 * This class is a Facade design pattern.
 * </p>
 * 
 * <p><b>Created at: </b>03/12/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @version 1.0.0
 *
 */
public class ReaderSearcher extends LibrarySearcher {

	private DAO<Reader> readerDAO;

	/**
	 * The class constructor requires a DAO from package:
	 * com.raul.libraryproject.model.dao
	 * @param readerDAO is a {@code DAO<Reader>}
	 */
	public ReaderSearcher(DAO<Reader> readerDAO) {
		this.readerDAO = readerDAO;
	}
	
	/**
	 * This method will prompt a message asking to input a value to search
	 * in the Reader {@code DAO<Reader>}.
	 * It'll return an instance of Reader, if anything is found an unchecked
	 * exception is thrown.
	 * 
	 * @return Reader instance. Otherwise throws an unchecked exception
	 * @throws NoSuchElementException
	 */
	public Reader fetchReader() {
		
		List<Comparator<Reader>> readerComparators = new LinkedList<>();
		
		String findReader = Keyboard.text("# Search a reader by an ID or name: ");
		
		int id = 0;
		/* If input contains only numbers a Comparator to filter 
		 * ID will be applied.*/
		if (findReader.matches("[0-9]+"))
			id = Integer.parseInt(findReader);
		// Sets all rules for a comparison.
		readerComparators.add(new ReaderComparator.ID());
		readerComparators.add(new ReaderComparator.Name());
		readerComparators.add(new ReaderComparator.Surname());
		
		Reader reader = new Reader.Builder(id)
				.setName(findReader)
				.setSurname(findReader)
				.build();
		
		reader = fetchEntity(this.readerDAO, reader, readerComparators);
		// Call method fetchBook recursively while result is equal to null 
		if (reader == null)
			return fetchReader();
		
		return reader;
	}
	
	/**
	 * This method will prompt a message asking to input a value to search
	 * in the Reader {@code DAO<Reader>}.
	 * It'll return a {@code List<Reader>}, if anything is found an unchecked
	 * exception is thrown.
	 * 
	 * @return {@code List<Reader>}. Otherwise throws an unchecked exception
	 * @throws NoSuchElementException
	 */
	public List<Reader> fetchReaders() {
		
		List<Comparator<Reader>> readerComparators = new LinkedList<>();
		
		String findReader = Keyboard.text("# Search a reader by an ID or name: ");
		
		int id = 0;
		/* If input contains only numbers a Comparator to filter 
		 * ID will be applied.*/
		if (findReader.matches("[0-9]+"))
			id = Integer.parseInt(findReader);
		// Sets all rules for a comparison.
		// Relative values. It works like LIKE in SQL Database
		readerComparators.add(new ReaderComparator.ID());
		readerComparators.add(new ReaderRelativeComparator.Name());
		readerComparators.add(new ReaderRelativeComparator.Surname());
		
		Reader reader = new Reader.Builder(id)
				.setName(findReader)
				.setSurname(findReader)
				.build();
		
		List<Reader> listResult = 
					fetchEntities(this.readerDAO, reader, readerComparators);
		// Call method fetchBooks recursively while result is less than 1.
		if (listResult.size() < 1)
			return fetchReaders();
		
		return listResult;
	}
	
}
