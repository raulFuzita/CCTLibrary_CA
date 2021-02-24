package com.raul.libraryproject.model.facades;

import java.util.Comparator;
import java.util.NoSuchElementException;

import com.raul.libraryproject.model.dao.DAO;
import com.raul.libraryproject.util.datastructure.List;
import com.raul.libraryproject.util.keyboard.Keyboard;

/**
 * <p>LibrarySearcher provides a limited option of functionality to search
 * an entity that has a concrete class that implements Comparator.
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
public abstract class LibrarySearcher {

	/**
	 * This method fetches an element in a List. To optimize the way
	 * a search is performed it requires a {@code List<?>} that holds
	 * {@code Comparator<? super E>} objects.
	 * 
	 * @param <E> is a Generic type
	 * @param dao is an interface {@code DAO<E>} from package: 
	 * com.raul.libraryproject.model.dao
	 * @param e is the target element. It's a Generic type.
	 * @param comparator is a {@code List<?>} that holds an interface
	 * {@code Comparator<? super E>}.
	 * @return E that is a Generic object of the same type as the parameter
	 * @throws NoSuchElementException
	 */
	public <E> E fetchEntity
	(DAO<E> dao, E e, List<? extends Comparator<? super E>> comparator) {
		// Search an element based on an object target.
		E entity = EntitySearcher.searchEntity(dao, e, comparator);
		
		/*
		 * If an element is not found a message is prompted to ask
		 * if it is desirable to leave the method algorithm by throwing
		 * a NoSuchElementException exception or return null.
		 */
		if (isEntityNull(entity)){
			System.out.println("\nNot found, please try it gain.\n");
			
			if (isExitPressed())
				throw new NoSuchElementException(
						"No entity is chosen: " 
						+ this.getClass().getName());
			else
				return null;
		}
		// If the target element is found it is returned
		return entity;
	}
	
	/**
	 * This method fetches elements in a List. To optimize the way
	 * a search is performed it requires a {@code List<?>} that holds a 
	 * {@code List<? extends Comparator<? super E>>} objects.
	 * 
	 * @param <E> is a Generic type
	 * @param dao is an interface {@code DAO<E>} from package: 
	 * com.raul.libraryproject.model.dao
	 * @param e is the target element. It's a Generic type.
	 * @param comparator is a {@code List<? extends Comparator<? super E>>} 
	 * that holds an interface
	 * {@code Comparator<? super E>}.
	 * @return {@code List<E>} that is a Generic object of the same type as 
	 * the parameter
	 * @throws NoSuchElementException
	 */
	public <E> List<E> fetchEntities
	(DAO<E> dao, E e, List<? extends Comparator<? super E>> comparator) {
		// Search elements based on an object target.
		List<E> entities = EntitySearcher.searchEntities(dao, e, comparator);
		
		/*
		 * If an element is not found a message is prompted to ask
		 * if it is desirable to leave the method algorithm by throwing
		 * a NoSuchElementException exception or return null.
		 */
		if (entities.size() < 1){
			System.out.println("\nNot found, please try it gain.\n");
			
			if (isExitPressed()) {
				throw new NoSuchElementException(
						"No entity is chosen: " 
						+ this.getClass().getName());
			}
		}
		// If the target element is found it is returned a List of results
		return entities;
	}
	
	/**
	 * This method checks rather an object is null or not.
	 * It returns a boolean value.
	 * @param o is an Object
	 * @return {@code true} if object is equal to null. Otherwise false.
	 */
	private boolean isEntityNull(Object o) {
		return o == null;
	}
	
	/**
	 * This method returns a boolean to determine rather an X key button
	 * is pressed or not. It isn't case sensitive.
	 * @return {@code true} if X key button has been pressed. Otherwise false.
	 */
	private boolean isExitPressed() {
		String keyButton = Keyboard.text(
				"# Press X to exit. Otherwise any key to continue: ");
		return keyButton.equalsIgnoreCase("X");
	}
}
