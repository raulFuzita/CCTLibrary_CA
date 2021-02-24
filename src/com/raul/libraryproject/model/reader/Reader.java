package com.raul.libraryproject.model.reader;

import java.io.Serializable;

/**
 * <p>Class Book implements Serializable, Cloneable, and Comparable.
 * This class is meant to be a DTO (Data Transfer Object) class.
 * According to Robert C. Martin, book Clean Code, page 100, published
 * by Prentice Hall publishing company a DTO class should public variables
 * and no functions that doesn't provide any benefit.
 * This class implements clone method to provide a object clone that
 * resides in a different memory space.
 * </p>
 * 
 * <p><b>Created at: </b>17/11/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @version 1.0.0
 *
 */
public class Reader extends ReaderAbstract implements Serializable, Cloneable {

	/**
	 * Serialization can represent an object in sequence bytes.
	 * Once an object is serialized it can be stored in a file
	 * and then retrieve it by deserializing it.
	 * This ID was auto-generated.
	 */
	private static final long serialVersionUID = -6237440450677818403L;
	
	/**
	 * This nested class will be the constructor builder class.
	 * The nested class Builder extends {@code BookAbstract.Builder<Builder>}
	 *
	 * @param <T> is a Generic type that assigns it to the 
	 * nested class Builder.
	 * 
	 * @author Raul Macedo Fuzita
	 * 
	 * @version 1.0.0
	 */
	public static class Builder extends ReaderAbstract.Builder<Builder> {

		/**
		 * This parameter will assign the object id.
		 * @param id is a long primitive data type.
		 */
		public Builder(long id) {
			super(id);
		}

		/**
		 * @return Reader that is an instance of Reader class.
		 */
		@Override
		public Reader build() {
			return new Reader(this);
		}

		/**
		 * @return Builder that is a Builder instance which
		 * points to the current instance.
		 */
		@Override
		protected Builder self() {
			return this;
		}

	}
	
	/**
	 * A constructor to set all properties by passing a Builder instance.
	 * 
	 * <p>
	 * Sets properties:
	 * <ul>
	 * 	<li>id</li>
	 * 	<li>name</li>
	 * 	<li>surname</li>
	 * 	<li>birthdate</li>
	 * 	<li>address</li>
	 * </ul>
	 * </p>
	 * 
	 * @param builder is a Builder type.
	 */
	private Reader (Builder builder) {
		super(builder);
	}

	/*
	 * It uses the original documentation.
	 */
	public Object clone() throws CloneNotSupportedException {
		return (Reader) super.clone();
	}
	
	/**
	 * This method returns a reader full name.
	 * @return String that is a reader full name.
	 */
	public String getFullname() {
		return this.name + " " + this.surname;
	}

	/**
	 * Returns Book properties as a String value.
	 */
	@Override
	public String toString() {
		return "Reader [id=" + id + ", name=" + name 
				+ ", surname=" + surname + ", birthdate=" + birthdate
				+ ", address=" + address + "]";
	}
	
}
