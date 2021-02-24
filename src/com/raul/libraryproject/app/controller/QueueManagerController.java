package com.raul.libraryproject.app.controller;

import java.util.Map;

import com.raul.libraryproject.model.book.Book;
import com.raul.libraryproject.model.dao.DAO;
import com.raul.libraryproject.model.facades.EntitySearcher;
import com.raul.libraryproject.model.facades.QueueManager;
import com.raul.libraryproject.model.junction.JunctionEntity;
import com.raul.libraryproject.model.reader.Reader;
import com.raul.libraryproject.model.reader.ReaderComparator;
import com.raul.libraryproject.util.datastructure.Queue;

/**
 * <p>
 * QueueManagerController controls all waiting lists.
 * </p>
 * 
 * <p><b>Created at: </b>03/12/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @version 1.0.0
 *
 */
public class QueueManagerController {

	private Map<Long, Queue<JunctionEntity>> queuesMap = null;
	private DAO<Reader> readerDAO = null;
	private JunctionEntity entity = null;

	/**
	 * Constructor requires {@code Map<Long, Queue<JunctionEntity>>} to 
	 * check in the queue map if the argument Book exist in the map.
	 * If it exist the queue pull the record that matches the Book Id.
	 * {@code DAO<Reader>} object will be used against a Reader Id
	 * to determine if a reader is waiting to borrow the book.
	 * 
	 * @param queuesMap is a {@code Map<Long, Queue<JunctionEntity>>}
	 * @param readerDAO is a {@code DAO<Reader>}
	 */
	public QueueManagerController
	(Map<Long, Queue<JunctionEntity>> queuesMap, DAO<Reader> readerDAO) {
		this.queuesMap = queuesMap;
		this.readerDAO = readerDAO;
	}

	/**
	 * Checks in the queue map if the argument Book exist in the map.
	 * If it exist the queue pull the record that matches the Book Id.
	 * 
	 * Returns the reader in queue waiting for the book be available to
	 * borrow.
	 * 
	 * @param book is a Book object
	 * @return Reader or null if a reader is not found.
	 */
	public Reader checkQueue(Book book) {
		QueueManager qm = new QueueManager(queuesMap);
		entity = qm.dequeue(book.getId());
		
		if (entity == null)
			return null;
		
		Reader reader = new Reader.Builder(entity.belongId).build();
		return EntitySearcher.searchEntity(
							this.readerDAO, reader, new ReaderComparator.ID());
	}

	/**
	 * Returns a JunctionEntity object
	 * @return JunctionEntity
	 */
	public JunctionEntity getEntity() {
		return entity;
	}
	
}
