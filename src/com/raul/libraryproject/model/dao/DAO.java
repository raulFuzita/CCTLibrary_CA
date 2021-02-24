package com.raul.libraryproject.model.dao;

import com.raul.libraryproject.util.datastructure.List;

/**
 * <p>Interface DAO (Data Access Object) isolates the application 
 * (business layer) from the persistence layer.
 * DAO hides the complexity that involves in performing CRUD
 * (Create, Read, Update Delete).
 * </p>
 * 
 * <p><b>Created at: </b>30/11/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @param E is to define the type of the main object
 * 
 * @version 1.0.0
 *
 */
public interface DAO<E> {

	/**
	 * This method returns the Object assign to the DAO Generic type.
	 * @param id is an Object type.
	 * @return E that is a Generic type.
	 */
	public E getById(Object id);
	
	/**
	 * This method adds an object the same type of the DAO Generic type.
	 * @param entity is a Generic type.
	 */
	public void add(E entity);
	
	/**
	 * This method updates an object the same type of the DAO Generic type.
	 * The object position is defined by the ID.
	 * @param id is an Object type.
	 * @param entity is a Generic type.
	 */
	public void update(Object id, E entity);
	
	/**
	 * This method removes an object by an ID type integer.
	 * @param id is an primitive type integer.
	 */
	public void remove(int id);
	
	/**
	 * This method removes an object by an ID type Object.
	 * @param id is an Object type.
	 */
	public void remove(Object id);
	
	/**
	 * This method returns all elements of a List 
	 * the same type of the DAO Generic type.
	 * @return {@code List<E>} is a List Generic type.
	 */
	public List<E> getAll();
	
	/**
	 * This method adds a whole list.
	 * @param list is a List Generic type ({@code List<E>}).
	 */
	public void addAll(List<E> list);
}
