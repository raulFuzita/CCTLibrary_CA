package com.raul.libraryproject.model.book;

import java.io.Serializable;

/**
 * <p>
 * Class Book implements Serializable, Cloneable, and Comparable.
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
public class Book extends BookAbstract implements Serializable, Cloneable {

	/**
	 * Serialization can represent an object in sequence bytes.
	 * Once an object is serialized it can be stored in a file
	 * and then retrieve it by deserializing it.
	 * This ID was auto-generated.
	 */
	private static final long serialVersionUID = 8674205592033151311L;
	
	
	/**
	 * If any of the properties are set, but the id, a default value
	 * is applied to each property that wasn't set by a constructor or
	 * setter methods.
	 * The nested class Builder extends {@code BookAbstract.Builder<Builder>}
	 * 
	 * <p>
	 * Default values:
	 * <ul>
	 * 	<li>title = "Unknown"</li>
	 * 	<li>author = "Unknown"</li>
	 * 	<li>genre = "Unknown"</li>
	 * 	<li>quantity = 0</li>
	 * </ul>
	 * </p>
	 * 
	 * @author Raul Macedo Fuzita
	 * 
	 * @version 1.0.0
	 */
	public static class Builder extends BookAbstract.Builder<Builder> {

		/**
		 * This parameter will assign the object id.
		 * @param id is a long primitive data type.
		 */
		public Builder(long id) {
			super(id);
		}

		/**
		 * @return Book that is an instance of Book class.
		 */
		@Override
		public Book build() {
			return new Book(this);
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
	 * 	<li>title</li>
	 * 	<li>author</li>
	 * 	<li>genre</li>
	 * 	<li>quantity</li>
	 * </ul>
	 * </p>
	 * 
	 * @param builder is a Builder type.
	 */
	private Book(Builder builder) {
		super(builder);
	}

	/*
	 * It uses the original documentation.
	 */
	public Object clone() throws CloneNotSupportedException {
		return (Book) super.clone();
	}

	/**
	 * This method returns book's id, title, and author.
	 * 
	 * @return String that is simple information about a book.
	 */
	public String simpleInformation() {
		return "Book [id=" + this.id + ", title=" + this.title 
					+ ", author=" + this.author + "]";
	}
	
	/**
	 * This method increase book's quantity by 1.
	 */
	public void increaseQuantity() {
		this.quantity++;
	}
	
	/**
	 * This method decrease book's quantity by 1.
	 */
	public void decreaseQuantity() {
		this.quantity--;
	}
	
	/**
	 * This method returns a book status, rather it's available or not.
	 * @return true if book is available. Otherwise false.
	 */
	public boolean isAvailable() {
		return this.quantity > 0 ? true : false;
	}
	
	
	/**
	 * Returns Book properties as a String value.
	 */
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author 
				+ ", genre=" + genre + ", quantity="
				+ quantity + "]";
	}
}
