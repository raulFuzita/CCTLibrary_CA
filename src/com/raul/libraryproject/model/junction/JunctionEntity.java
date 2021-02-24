 package com.raul.libraryproject.model.junction;

import java.io.Serializable;

/**
 * <p>Class JunctionEntity implements Serializable, Cloneable, and Comparable.
 * This class is meant to be a DTO (Data Transfer Object) class.
 * According to Robert C. Martin, book Clean Code, page 100, published
 * by Prentice Hall publishing company a DTO class should public variables
 * and no functions that doesn't provide any benefit.
 * This class implements clone method to provide a object clone that
 * resides in a different memory space.
 * </p>
 * 
 * <p><b>Created at: </b>29/11/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @version 1.0.0
 *
 */
public class JunctionEntity implements Serializable, Cloneable {

	/**
	 * Serialization can represent an object in sequence bytes.
	 * Once an object is serialized it can be stored in a file
	 * and then retrieve it by deserializing it.
	 * This ID was auto-generated.
	 */
	private static final long serialVersionUID = 699818582844092540L;
	
	public long ownerId;
	public long belongId;
	
	/**
	 * This constructor presume that all those two parameters will be
	 * solved.
	 * 
	 * @param ownerId
	 * @param belongId
	 */
	public JunctionEntity(long ownerId, long belongId) {
		super();
		this.ownerId = ownerId;
		this.belongId = belongId;
	}
	
	/*
	 * It uses the original documentation.
	 */
	public Object clone() throws CloneNotSupportedException {
		return (JunctionEntity) super.clone();
	}

	@Override
	public String toString() {
		return "JunctionEntity [ownerId=" + ownerId + ", belongId=" + belongId + "]";
	}
}
