package com.raul.libraryproject.model.reader;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>Class ReaderAbstract implements Serializable. It's an abstract class that
 * implements the Builder abstract pattern.
 * Although this class can't be instantiated since it's an abstract class,
 * the super constructor can be used by a concrete class (child class)
 * to instantiate its values by passing a Builder as a constructor parameter.
 * </p>
 * 
 * <p>This design pattern was chosen because ReaderAbstract actually
 * is designed to abstract its child which will need many parameters
 * to instantiate an object. According to Joshua Bloch, Effective Java
 * Third Edition, page 10. If a class needs many constructor parameters
 * a Builder pattern should be used instead of the 
 * old fashion Telescope pattern.
 * </p>
 * 
 * <p><b>Created at: </b>30/11/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @version 1.0.0
 *
 */
public abstract class ReaderAbstract implements Serializable {

	/**
	 * Serialization can represent an object in sequence bytes.
	 * Once an object is serialized it can be stored in a file
	 * and then retrieve it by deserializing it.
	 * This ID was auto-generated.
	 */
	private static final long serialVersionUID = 4441703128487000990L;
	
	protected long id;
	protected String name;
	protected String surname;
	protected LocalDate birthdate;
	protected String address;
	
	/**
	 * This nested class will be the constructor builder class.
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
		
		private String name;
		private String surname;
		private LocalDate birthdate;
		private String address;
		
		/**
		 * This parameter will assign the object id.
		 * @param id is a long primitive data type.
		 */
		public Builder(long id) {
			this.id = id;
		}
		
		/**
		 * This method assigns the object name
		 * @param name is a String object
		 * @return an instance of the class.
		 */
		public T setName(String name) {
			this.name = name;	
			return self();
		}

		/**
		 * This method assigns the object surname
		 * @param surname is a String object
		 * @return an instance of the class.
		 */
		public T setSurname(String surname) {
			this.surname = surname;		
			return self();
		}

		/**
		 * This method assigns the object birthdate
		 * @param birthdate is a LocalDate object
		 * @return an instance of the class.
		 */
		public T setBirthdate(LocalDate birthdate) {
			this.birthdate = birthdate;		
			return self();
		}

		/**
		 * This method assigns the object address
		 * @param address is a String object
		 * @return an instance of the class.
		 */
		public T setAddress(String address) {
			this.address = address;		
			return self();
		}
		
		/**
		 * This method must be overridden in the concrete class (child class).
		 * @return an instance of the class.
		 */
		abstract ReaderAbstract build();
		
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
	 * 	<li>name</li>
	 * 	<li>surname</li>
	 * 	<li>birthdate</li>
	 * 	<li>address</li>
	 * </ul>
	 * </p>
	 * 
	 * @param builder is a Builder type.
	 */
	ReaderAbstract (Builder<?> builder){
		id 				= builder.id;
		name 			= builder.name;
		surname 		= builder.surname;
		birthdate 		= builder.birthdate;
		address 		= builder.address;
	}
	
	/**
	 * This method returns the object id. 
	 * @return id that is a long primitive data type.
	 */
	public long getId() {
		return id;
	}

	/**
	 * This method returns the object name.
	 * @return name that is a String object.
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method reassign a new value for the object name.
	 * @param name is a String object.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method returns the object surname.
	 * @return surname that is a String object.
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * This method reassign a new value for the object surname.
	 * @param surname is a String object.
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * This method returns the object birthdate.
	 * @return birthdate that is a LocalDate object.
	 */
	public LocalDate getBirthdate() {
		return birthdate;
	}

	/**
	 * This method reassign a new value for the object birthdate.
	 * @param birthdate is a LocalDate object.
	 */
	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * This method reassign a new value for the object address.
	 * @param address is a String object.
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * This method reassign a new value for the object address.
	 * @param address is a String object.
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Returns ReaderAbstract properties as a String value.
	 */
	@Override
	public String toString() {
		return "ReaderAbstract [id=" + id + ", name=" + name 
				+ ", surname=" + surname + ", birthdate=" + birthdate
				+ ", address=" + address + "]";
	}
}
