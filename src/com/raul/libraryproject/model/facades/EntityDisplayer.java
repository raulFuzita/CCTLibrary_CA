package com.raul.libraryproject.model.facades;

import java.util.Comparator;

import com.raul.libraryproject.model.dao.DAO;
import com.raul.libraryproject.util.datastructure.Arrays;
import com.raul.libraryproject.util.datastructure.List;

/**
 * <p>EntityDisplayer provides a limited option of functionality to display
 * one or more entities.
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
public class EntityDisplayer {

	/**
	 * This method traversals a {@code DAO<E>} from package:
	 * com.raul.libraryproject.model.dao and print each element 
	 * stored in the DAO.
	 * 
	 * @param <E> is a Generic type
	 * @param dao is an interface {@code DAO<E>} from package: 
	 * com.raul.libraryproject.model.dao
	 * @param comparator is a {@code Comparator<? super E>}.
	 */
	@SuppressWarnings("unchecked")
	public <E> void displayEntity(DAO<E> dao, Comparator<? super E> comparator) {
		
		List<E> list = dao.getAll();
		// Converts a list to a Generic array.
		E[] entity = (E[]) list.toArray(new Object[list.size()]);
		/* Invokes Arrays from package: 
		 * com.raul.libraryproject.util.datastructure to sort the array*/
		Arrays.sort(entity, comparator);
		// print elements if they exist.
		for (E e : entity)
			System.out.println(e);
	}
}
