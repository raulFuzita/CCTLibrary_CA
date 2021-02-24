package com.raul.libraryproject.model.facades;

import java.util.Comparator;
import java.util.NoSuchElementException;

import com.raul.libraryproject.model.book.Book;
import com.raul.libraryproject.model.book.BookComparator;
import com.raul.libraryproject.model.book.BookRelativeComparator;
import com.raul.libraryproject.model.dao.DAO;
import com.raul.libraryproject.util.datastructure.LinkedList;
import com.raul.libraryproject.util.datastructure.List;
import com.raul.libraryproject.util.keyboard.Keyboard;

/**
 * <p>BookSearcher provides a limited option of functionality to search
 * a Book that has a concrete class that implements Comparator.
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
public class BookSearcher extends LibrarySearcher {

	private DAO<Book> bookDAO;

	/**
	 * The class constructor requires a DAO from package:
	 * com.raul.libraryproject.model.dao
	 * @param bookDAO is a {@code DAO<Book>}
	 */
	public BookSearcher(DAO<Book> bookDAO) {
		this.bookDAO = bookDAO;
	}
	
	/**
	 * This method will prompt a message asking to input a value to search
	 * in the Book {@code DAO<Book>}.
	 * It'll return an instance of Book, if anything is found an unchecked
	 * exception is thrown.
	 * 
	 * @return Book instance. Otherwise throws an unchecked exception
	 * @throws NoSuchElementException
	 */
	public Book fetchBook() {
		
		List<Comparator<Book>> bookComparators = new LinkedList<>();
		Book book = null;
		
		String findBook = Keyboard
					.text("# Search a book by an ID, title, author or genre: ");
		
		int id = 0;
		/* If input contains only numbers a Comparator to filter 
		 * ID will be applied.*/
		if (findBook.matches("[0-9]+")) {
			id = Integer.parseInt(findBook);
			bookComparators.add(new BookComparator.ID());
		}
		// Sets all rules for a comparison.
		bookComparators.add(new BookComparator.Title());
		bookComparators.add(new BookComparator.Author());
		bookComparators.add(new BookComparator.Genre());
		// Relative values. It works like LIKE in SQL Database
		bookComparators.add(new BookRelativeComparator.Title());
		bookComparators.add(new BookRelativeComparator.Author());
		bookComparators.add(new BookRelativeComparator.Genre());
		book = new Book.Builder(id)
				.setTitle(findBook)
				.setAuthor(findBook)
				.build();
			
		book = fetchEntity(this.bookDAO, book, bookComparators);
		// Call method fetchBook recursively while result is equal to null 
		if (book == null)
			return fetchBook();
			
		return book;
	}
	
	/**
	 * This method will prompt a message asking to input a value to search
	 * in the Book {@code DAO<Book>}.
	 * It'll return a {@code List<Book>}, if anything is found an unchecked
	 * exception is thrown.
	 * 
	 * @return {@code List<Book>}. Otherwise throws an unchecked exception
	 * @throws NoSuchElementException
	 */
	public List<Book> fetchBooks() {
		
		List<Comparator<Book>> bookComparators = new LinkedList<>();
		Book book = null;
		
		String findBook = Keyboard
					.text("# Search a book by an ID, title, author or genre: ");
		
		int id = 0;
		/* If input contains only numbers a Comparator to filter 
		 * ID will be applied.*/
		if (findBook.matches("[0-9]+")) {
			id = Integer.parseInt(findBook);
			bookComparators.add(new BookComparator.ID());
		}
		// Sets all rules for a comparison.
		// Relative values. It works like LIKE in SQL Database
		bookComparators.add(new BookRelativeComparator.Title());
		bookComparators.add(new BookRelativeComparator.Author());
		bookComparators.add(new BookRelativeComparator.Genre());
		book = new Book.Builder(id)
				.setTitle(findBook)
				.setAuthor(findBook)
				.setGenre(findBook)
				.build();
			
		List<Book> listResult = 
							fetchEntities(this.bookDAO, book, bookComparators);
		
		// Call method fetchBooks recursively while result is less than 1.
		if (listResult.size() < 1)
			return fetchBooks();
			
		return listResult;
	}
}
