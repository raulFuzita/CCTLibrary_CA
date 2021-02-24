package com.raul.libraryproject.model.facades;

import java.util.Map;

import com.raul.libraryproject.model.junction.JunctionEntity;
import com.raul.libraryproject.util.datastructure.LinkedList;
import com.raul.libraryproject.util.datastructure.Queue;

/**
 * <p>ReaderSearcher provides a limited option of functionality to access
 * a queue data. It'll be done by combining queue features with a Map.
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
public class QueueManager {

	private Map<Long, Queue<JunctionEntity>> queueMap;
	
	/**
	 * This facade requires a Map to store all queues indexed by 
	 * the owner ID of JunctionEntity class from package:
	 * com.raul.libraryproject.model.junction
	 * @param queueMap is a {@code Map<Long, Queue<JunctionEntity>>}
	 */
	public QueueManager(Map<Long, Queue<JunctionEntity>> queueMap) {
		this.queueMap = queueMap;
	}

	/**
	 * This method takes a JunctionEntity class as argument.
	 * It'll check if a queue already exist to add a JunctionEntity
	 * object. If it doesn't a queue is instantiated and added to it
	 * a JunctionEntity object. The owner Id of the object will be used
	 * as key for the Map.
	 * @param entity is a JunctionEntity
	 */
	public void enqueue(JunctionEntity entity) {
		
		long ownerId = entity.ownerId;
		
		// Key doesn't exist for this ID.
		if (!queueMap.containsKey(ownerId)) {
			// Instance of Queue that implements LinkedList
			Queue<JunctionEntity> queue = new LinkedList<>();
			// Add JunctionEntity object
			queue.add(entity);
			// Put the queue in the Map
			queueMap.put(ownerId, queue);
			return;
		}
		// Retrieves the queue that has the wanted Id.
		Queue<JunctionEntity> queue = queueMap.get(ownerId);
		// Add JunctionEntity object to the existent queue.
		queue.add(entity);
	}
	
	/**
	 * This method pulls a wanted JunctionEntity object
	 * from a queue. If the owner Id doesn't match any key
	 * in the Map it is returned a null value.
	 * @param ownerId is a wrapper box Long.
	 * @return JunctionEntity object. Otherwise null.
	 */
	public JunctionEntity dequeue(Long ownerId) {
		
		if (!queueMap.containsKey(ownerId))
			return null;
		
		Queue<JunctionEntity> queue = queueMap.get(ownerId);
		return queue.pull();
	}
	
	/**
	 * This method doesn't pull a JunctionEntity object
	 * but it doesn't return a wanted object without 
	 * removing it from the queue as dequeue does.
	 * @param ownerId is a wrapper box Long.
	 * @return JunctionEntity object. Otherwise null.
	 */
	public JunctionEntity peek(Long ownerId) {
		
		if (!queueMap.containsKey(ownerId))
			return null;
		
		Queue<JunctionEntity> queue = queueMap.get(ownerId);
		return queue.peek();
	}
	
	/**
	 * This method returns a Map of {@code Map<Long, Queue<JunctionEntity>>}
	 * @return {@code Map<Long, Queue<JunctionEntity>>}
	 */
	public Map<Long, Queue<JunctionEntity>> getQueueMap() {
		return queueMap;
	}
	
	/**
	 * This method sets a Map with the following types:
	 * {@code Map<Long, Queue<JunctionEntity>>}
	 * @param queueMap is a {@code Map<Long, Queue<JunctionEntity>>}
	 */
	public void setQueueMap(Map<Long, Queue<JunctionEntity>> queueMap) {
		this.queueMap = queueMap;
	}
}
