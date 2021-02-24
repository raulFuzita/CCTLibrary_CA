package com.raul.libraryproject.model.facades;

import java.util.Comparator;
import java.util.NoSuchElementException;

import com.raul.libraryproject.model.dao.DAO;
import com.raul.libraryproject.util.datastructure.Arrays;
import com.raul.libraryproject.util.datastructure.Collections;
import com.raul.libraryproject.util.datastructure.LinkedList;
import com.raul.libraryproject.util.datastructure.List;

/**
 * <p>EntitySearcher provides a limited option of functionality to search
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
public abstract class EntitySearcher {

	/**
	 * This method traversals a {@code DAO<E>} from package:
	 * com.raul.libraryproject.model.dao to return a wanted element.
	 * 
	 * @param <E> is a Generic type
	 * @param dao is an interface {@code DAO<E>} from package: 
	 * com.raul.libraryproject.model.dao
	 * @param e is the target element. It's a Generic type.
	 * @param comparator is a {@code Comparator<? super E>}.
	 * @return {@code E} that is a Generic object of the same type as 
	 * the parameter
	 */
	@SuppressWarnings("unchecked")
	public static <E> E 
	searchEntity(DAO<E> dao, E e, Comparator<? super E> comparator) {
		
		List<E> list = dao.getAll();
		// Converts a list to a Generic array.
		E[] entity = (E[]) list.toArray(new Object[list.size()]);
		/* Invokes Arrays from package: 
		 * com.raul.libraryproject.util.datastructure to sort the array*/
		Arrays.sort(entity, comparator);
		list.clear(); // Clear variable list for the next operation.
		/*
		 * Each value of the sorted array is stored back to the list
		 */
		for (E entityTemp : entity)
			list.add(entityTemp);
		// gets an index bigger than -1 if element exist. Otherwise zero.
		int index = Collections.binarySearch(list, e, comparator);
		// If index is bigger than -1 the element is returned.
		if (index > -1)
			return list.get(index);
		// If no element is found it throws NoSuchElementException.
		return null;
	}
	
	/**
	 * This method traversals a {@code DAO<E>} from package:
	 * com.raul.libraryproject.model.dao to return a list of wanted elements.
	 * To optimize the way a search is performed it requires a {@code List<?>} 
	 * that holds a {@code List<? extends Comparator<? super E>>} objects.
	 * 
	 * @param <E> is a Generic type
	 * @param dao is an interface {@code DAO<E>} from package: 
	 * com.raul.libraryproject.model.dao
	 * @param e is the target element. It's a Generic type.
	 * @param comparatorList is a {@code List<? extends Comparator<? super E>>} 
	 * that holds an interface
	 * {@code Comparator<? super E>}.
	 * @return {@code E} that is a Generic object of the same type as 
	 * the parameter
	 * @throws NoSuchElementException
	 */
	@SuppressWarnings("unchecked")
	public static <E> E searchEntity
	(DAO<E> dao, E e, List<? extends Comparator<? super E>> comparatorList) {
		
		List<E> list = dao.getAll();
		// Converts a list to a Generic array.
		E[] entity = (E[]) list.toArray(new Object[list.size()]);
		/*
		 * The first element of {@code List<? extends Comparator<? super E>>}
		 * is applied to search for the target element. If anything is found
		 * the next "rule" is applied to search for the target by a different
		 * comparable parameter.
		 */
		for (Comparator<? super E> comparator : comparatorList) {
			Arrays.sort(entity, comparator);
			list.clear(); // Clear variable list for the next operation.
			/*
			 * Each value of the sorted array is stored back to the list
			 */
			for (E entityTemp : entity)
				list.add(entityTemp);
			// If index is bigger than -1 the element is returned.
			int index = Collections.binarySearch(list, e, comparator);
			// If index is bigger than -1 the element is returned.
			if (index > -1)
				return list.get(index);
		}
		// If no element is found it throws NoSuchElementException.
		return null;
	}
	
	/**
	 * This method traversals a {@code DAO<E>} from package:
	 * com.raul.libraryproject.model.dao to return a List of wanted elements
	 * 
	 * @param <E> is a Generic type
	 * @param dao is an interface {@code DAO<E>} from package: 
	 * com.raul.libraryproject.model.dao
	 * @param e is the target element. It's a Generic type.
	 * @param comparator is a {@code Comparator<? super E>}.
	 * @return {@code List<E>} that is a Generic object list of the same type as 
	 * the parameter
	 * @throws NoSuchElementException
	 */
	public static <E> List<E> 
	searchEntities(DAO<E> dao, E e, Comparator<? super E> comparator) {
		
		List<E> list = dao.getAll();
		/*
		 * In this case a linear search will work better than a binarySerach
		 * since many results are returned and the values can be target based on
		 * relative rules. It works similarly to a LIKE parameter in a
		 * SQL database.
		 */
		List<E> listTemp = Collections.linearSearch(list, e, comparator);
		// If any result has matched the rule it is returned.
		if (listTemp.size() > 0)
			return listTemp;
		// If nothing is found it return an empty list
		return new LinkedList<>();
	}
	
	/**
	 * This method traversals a {@code DAO<E>} from package:
	 * com.raul.libraryproject.model.dao to return a List of wanted elements
	 * 
	 * @param <E> is a Generic type
	 * @param dao is an interface {@code DAO<E>} from package: 
	 * com.raul.libraryproject.model.dao
	 * @param e is the target element. It's a Generic type.
	 * @param comparatorList is a {@code List<? extends Comparator<? super E>>} 
	 * that holds an interface
	 * {@code Comparator<? super E>}.
	 * @return {@code List<E>} that is a Generic object list of the same type as 
	 * the parameter
	 */
	public static <E> List<E> searchEntities
	(DAO<E> dao, E e, List<? extends Comparator<? super E>> comparatorList) {
		
		List<E> list = dao.getAll();
		/*
		 * The first element of {@code List<? extends Comparator<? super E>>}
		 * is applied to search for the target element. If anything is found
		 * the next "rule" is applied to search for the target by a different
		 * comparable parameter.
		 */
		for (Comparator<? super E> comparator : comparatorList) {
			/*
			 * In this case a linear search will work better than a binarySerach
			 * since many results are returned and the values can be target based on
			 * relative rules. It works similarly to a LIKE parameter in a
			 * SQL database.
			 */
			List<E> listTemp = Collections.linearSearch(list, e, comparator);
			// If any result has matched the rule it is returned.
			if (listTemp.size() > 0)
				return listTemp;
		}
		// If nothing is found it return an empty list
		return new LinkedList<>();
	}
}
