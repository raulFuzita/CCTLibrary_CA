package com.raul.libraryproject.model.book;

import java.io.Serializable;

/**
 * <p>Class BookAbstract implements Serializable. It's an abstract class that
 * implements the Builder abstract pattern.
 * Although this class can't be instantiated since it's an abstract class,
 * the super constructor can be used by a concrete class (child class)
 * to instantiate its values by passing a Builder as a constructor parameter.
 * </p>
 * 
 * <p>This design pattern was chosen because BookAbstract actually
 * is designed to abstract its child which will need many parameters
 * to instantiate an object. According to Joshua Bloch, Effective Java
 * Third Edition, page 10. If a class needs many constructor parameters
 * a Builder pattern should be used instead of 
 * the old fashion Telescope pattern.
 * </p>
 * 
 * <p><b>Created at: </b>30/11/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @version 1.0.0
 *
 */
public abstract class BookAbstract implements Serializable {

	/**
	 * Serialization can represent an object in sequence bytes.
	 * Once an object is serialized it can be stored in a file
	 * and then retrieve it by deserializing it.
	 * This ID was auto-generated.
	 */
	private static final long serialVersionUID = 3346033912058878161L;
	
	protected long id;
	protected String title;
	protected String author;
	protected String genre;
	protected int quantity;
	
	/**
	 * If any of the properties are set, but the id, a default value
	 * is applied to each property that wasn't set by a constructor or
	 * setter methods.
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
	 * @param <T> is a Generic type that assigns it to the 
	 * nested class Builder.
	 * 
	 * @author Raul Macedo Fuzita
	 * 
	 * @version 1.0.0
	 */
	abstract static class Builder<T extends Builder<T>> {
		
		private final long id;
		
		private String title		= "Unknown";
		private String author		= "Unknown";
		private String genre		= "Unknown";
		private int quantity		= 0;
		
		/**
		 * This parameter will assign the object id.
		 * @param id is a long primitive data type.
		 */
		public Builder(long id) {
			this.id = id;
		}
		
		/**
		 * This method assigns the object title
		 * @param title is a String object
		 * @return an instance of the class.
		 */
		public T setTitle(String title) {
			this.title = title;
			return self();
		}

		/**
		 * This method assigns the object author
		 * @param author is a String object
		 * @return an instance of the class.
		 */
		public T setAuthor(String author) {
			this.author = author;
			return self();
		}

		/**
		 * This method assigns the object genre
		 * @param genre is a String object
		 * @return an instance of the class.
		 */
		public T setGenre(String genre) {
			this.genre = genre;
			return self();
		}

		/**
		 * This method assigns the object quantity
		 * @param genre is an integer primitive data type.
		 * @return an instance of the class.
		 */
		public T setQuantity(int quantity) {
			this.quantity = quantity;
			return self();
		}

		/**
		 * This method must be overridden in the concrete class (child class).
		 * @return an instance of the class.
		 */
		abstract BookAbstract build();
		
		/**
		 * Subclasses must override this method to return "this"
		 * @return an instance of the class.
		 */
		protected abstract T self();
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
	BookAbstract (Builder<?> builder){
		id 			= builder.id;
		title 		= builder.title;
		author 		= builder.author;
		genre 		= builder.genre;
		quantity 	= builder.quantity;
	}

	
	/**
	 * This method returns the object id. 
	 * @return id that is a long primitive data type.
	 */
	public long getId() {
		return id;
	}

	/**
	 * This method returns the object title.
	 * @return title that is a String object.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * This method reassign a new value for the object title.
	 * @param title is a String object.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * This method returns the object title.
	 * @return title that is a String object.
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * This method reassign a new value for the object author.
	 * @param author is a String object.
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * This method returns the object genre.
	 * @return genre that is a String object.
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * This method reassign a new value for the object genre.
	 * @param genre is a String object.
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * This method returns the object quantity. 
	 * @return quantity that is an integer primitive data type.
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * This method reassign a new value for the object quantity.
	 * @param quantity is an integer primitive data type.
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Returns BookAbstract properties as a String value.
	 */
	@Override
	public String toString() {
		return "BookAbstract [id=" + id + ", title=" + title 
				+ ", author=" + author + ", genre=" + genre 
				+ ", quantity=" + quantity + "]";
	}
}
