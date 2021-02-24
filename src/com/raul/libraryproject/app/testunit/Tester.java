package com.raul.libraryproject.app.testunit;
import java.time.LocalDate;

import com.raul.libraryproject.model.book.Book;
import com.raul.libraryproject.model.book.BookComparator;
import com.raul.libraryproject.model.dao.DAO;
import com.raul.libraryproject.model.dao.filestorage.factory.FactoryFileManager;
import com.raul.libraryproject.model.junction.JunctionEntity;
import com.raul.libraryproject.model.options.FileType;
import com.raul.libraryproject.model.reader.Reader;
import com.raul.libraryproject.model.reader.ReaderComparator;
import com.raul.libraryproject.util.datastructure.Collections;
import com.raul.libraryproject.util.datastructure.LinkedList;
import com.raul.libraryproject.util.datastructure.List;

/**
 * After I was done testing pieces of algorithm, experimenting
 * design patterns, swapping code that produces the same result
 * but each one with pros and cons. I decided to keep this class
 * although it looks a messy. I think it's cool when I look back
 * and see how the strategy grew and changed over time.
 * 
 * THIS CLASS IS NOT PART OF THE FINAL PROJECT, BUT A SAMPLE
 * OF WHAT WAS MY SKETCH.
 * 
 * @author Raul Macedo Fuzita
 * 
 *
 */
public class Tester {
	
	List<Book> books = new LinkedList<>();
	List<Reader> readers = new LinkedList<>();
	List<JunctionEntity> junctionEnt = new LinkedList<>();
	Book book1 = null;
	Book book2 = null;
	Reader reader1 = null;
	Reader reader2 = null;
	JunctionEntity junction = null;

	public static void main(String[] args) {
		new Tester();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Tester() {
		
		Book[] bookArray = new Book[8];
		
		
		
		bookArray[0] = new Book.Builder(1)
				.setTitle("Effective Java")
				.setAuthor("Joshua Bloch")
				.setGenre("Programming Language")
				.setQuantity(1).build();
		
		bookArray[1] = new Book.Builder(2)
				.setTitle("Clean Code")
				.setAuthor("Robert C. Martin")
				.setGenre("Programming Language")
				.setQuantity(1).build();
		
		bookArray[2] = new Book.Builder(3)
				.setTitle("Clean Architecture")
				.setAuthor("Robert C. Martin")
				.setGenre("Programming Language")
				.setQuantity(2).build();
		
		bookArray[3] = new Book.Builder(4)
				.setTitle("The Murder of Roger Ackroyd")
				.setAuthor("Agatha Christie")
				.setGenre("Detective Novel")
				.setQuantity(3).build();
		
		bookArray[4] = new Book.Builder(5)
				.setTitle("The Picture Of Dorian Gray")
				.setAuthor("Oscar Wilde")
				.setGenre("Philosophical Novel")
				.setQuantity(1).build();
		
		
		bookArray[5] = new Book.Builder(6)
				.setTitle("The Karamazov Brothers")
				.setAuthor("Dostoevsky")
				.setGenre("Philosophical Novel")
				.setQuantity(3).build();
		
		bookArray[6] = new Book.Builder(7)
				.setTitle("Rich Dad Poor Dad")
				.setAuthor("Robert T. Kiyosaki")
				.setGenre("Self-help")
				.setQuantity(1).build();
		
		bookArray[7] = new Book.Builder(8)
				.setTitle("Programmer's Introduction to Mathematics")
				.setAuthor("Jeremy Kun")
				.setGenre("Mathematics")
				.setQuantity(1).build();
		
		for (Book book : bookArray) {
			books.add(book);
		}
		
		
		String address1 = "20, Parnel Street, Dublin, Ireland";
		String address2 = "5, O'Connel Street, Dublin, Ireland";
		String address3 = "15, Temple Bar, Dublin, Ireland";
		String address4 = "32, Kilmacud, Dublin, Ireland";
		
		reader1 = new Reader.Builder(1)
				.setName("John")
				.setSurname("Doe")
				.setBirthdate(LocalDate.parse("1986-02-10"))
				.setAddress(address1).build();
		
		reader2 = new Reader.Builder(2)
				.setName("Peter")
				.setSurname("Smith")
				.setBirthdate(LocalDate.parse("1982-05-14"))
				.setAddress(address2).build();
		
		Reader reader3 = new Reader.Builder(3)
				.setName("Anne")
				.setSurname("White")
				.setBirthdate(LocalDate.parse("1990-12-02"))
				.setAddress(address3).build();
		
		Reader reader4 = new Reader.Builder(4)
				.setName("Jackie")
				.setSurname("Murray")
				.setBirthdate(LocalDate.parse("1988-08-07"))
				.setAddress(address4).build();
		
		readers.add(reader1);
		readers.add(reader2);
		readers.add(reader3);
		readers.add(reader4);
		
		junction = new JunctionEntity(1, 1);
		junctionEnt.add(new JunctionEntity(1, 1));
		junctionEnt.add(new JunctionEntity(1, 2));
		// NullPointerException: problem if there is no data in a object file
		// EOFException: if there is object file but no data inside
		
		// FileNotFoundException
		// ClassCastException
		// JSONException: A JSONObject text must begin with '{' at 0 [character 1 line 1]
		
		FileType ft = FileType.TEXT;
		DAO dao = FactoryFileManager.make("BOOK", ft, "books");
		testWriteFileStorageDAO(books, dao);
		testReadFileStorageDAO(dao);
//		try {
//			DAO dao = FactoryFileManager.make("READER", ft, "readers");
//			testWriteFileStorageDAO(readers, dao);
//			testReadFileStorageDAO(dao);
//		} catch (UncheckedException e) {
//			System.out.println(e.getException().getClass());
//			
//			System.out.println((e.getException() instanceof NullPointerException));
//		}
		

	}
	
	public <E> void testReadFileStorageDAO(DAO<E> dao){

		List<E> list = dao.getAll();
		for (E e : list) {
			System.out.println(e);
		}
	}
	
	public <E> void testWriteFileStorageDAO(List<E> list, DAO<E> dao){
		dao.addAll(list);
	}
	
	public void testdataStructure() {
		
//		Object[] bookArray = books.toArray();
		
		
//		Book[] books3 = new Book[bookArray.length];
//		System.arraycopy(bookArray, 0, books3, 0, bookArray.length);
		
//		Book[] books4 = books.toArray(new Book[books.size()]);
//		
//		for (Book b : books4) {
//			System.out.println(b);
//		}
		
//		for (Book b : books3) {
//			System.out.println(b);
//		}
//		
//		Arrays.sort(books3, new BookComparator.Genre());
//		
//		System.out.println("Sort ");
//		for (Book b : books3) {
//			System.out.println(b);
//		}
		
//		Comparator<Book> c = new BookComparator.Title();
//		
//		books.sort(c);
//		
//		for (Book b : books) {
//			System.out.println(b);
//		}
		
		// TESTS
		int index = Collections.binarySearch(books, book1, new BookComparator.Title());
		System.out.println("Book index: " + index);
		System.out.println(books.get(index));
		
		int index2 = Collections.binarySearch(readers, reader1, new ReaderComparator.Surname());
		System.out.println("Reader index: " + index2);
		System.out.println(readers.get(index2));
	}
	
}
		
