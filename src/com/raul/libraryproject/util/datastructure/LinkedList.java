package com.raul.libraryproject.util.datastructure;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * <p>CLass LinkedList implements Collection and some interfaces such as
 * List and Queue of package com.raul.libraryproject.util.datastructure.
 * It also implements Cloneable from java.lang.Object and
 * Serializable from java.io package.
 * This class uses internal Linked nodes to create a linear data structure.
 * Although a linked list is not based on an array it does have size limit.
 * Some of the implementation returns an integer as size or uses an index
 * to find an element. The size limit to hold data is 2³¹-1 which is
 * the size of a MAX_VALUE, java.lang.integer package.
 * </p>
 * 
 * <p>This class is based on java.util LinkedList concepts and iterable 
 * implementation on GeeksforGeeks, Java - Implementing Iterator and 
 * Iterable Interface, available at:
 * www.geeksforgeeks.org/java-implementing-iterator-and-iterable-interface/
 * 
 * It was found a good strategy to make some methods work similarly like 
 * java.util LinkedList. This class was create for educational purpose.</p>
 * 
 * <p>Each method and algorithm were carefully analyzed and implemented.
 * As it said this class is based on java.util.LinkedList. However, java.util
 * uses a vary complex and extensive algorithm. java.util also implements 
 * many design patterns to support such diversity of implementations. 
 * One of them is the Adapter Pattern, but it was a bit too much for a simple 
 * data structure in this project. Although its simplicity I dived in the 
 * java.util code and documentation to abstract the class and extract what is
 * the most expect behave, flexibility, and elegance of a LinkedList.
 * </p>
 * 
 * <p><b>Created at: </b>22/10/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @version 1.0.0
 * 
 * @apiNote This class (LinkedList) has no compatibility with the
 * original java.util package. If it's used with a Java.util collections
 * it may thrown exceptions or work unexpectedly. Be aware of it.
 *
 * @param <E> is the type parameter of this interface, List.
 */
public class LinkedList<E> implements 
	List<E>, Queue<E>, Cloneable, java.io.Serializable {
	
	/**
	 * Serialization can represent an object in sequence bytes.
	 * Once an object is serialized it can be stored in a file
	 * and then retrieve it by deserializing it.
	 * This ID was auto-generated.
	 */
	private static final long serialVersionUID = -4886675139233058986L;
	
	/*
	 * transient is a variables modifier used in serialization. 
	 * At the time of serialization, if we don’t want to save 
	 * value of a particular variable in a file, then we use 
	 * transient keyword. When JVM comes across transient keyword, 
	 * it ignores original value of the variable and save default 
	 * value of that variable data type.
	 * 
	 * GeeksforGeeks, transient keyword in Java, 03/10/2017.
	 * Available at: www.geeksforgeeks.org/transient-keyword-java/
	 */
	transient Node<E> first;
	transient Node<E> last;
	
	// Keep LinkedList size
	transient int size = 0;

	/**
	 * This class represent a Doubly Linked List. Its structure is 
	 * an item property for field, a previous node reference, and
	 * next node reference. 
	 * 
	 * @author Raul Macedo Fuzita
	 *
	 * @param <E> is the type parameter of this interface, List.
	 */
	private static class Node<E> {
		E item; // field for an object
		Node<E> next; // next node, it could also be null
		Node<E> prev; // previous node, it could also be null.
		
		/**
		 * 
		 * @param prev is a Node
		 * @param item field for storing any object
		 * @param next is a Node
		 */
		public Node(Node<E> prev, E item, Node<E> next) {
			this.item = item;
			this.next = next;
			this.prev = prev;
		}
	}
	
	// https://www.geeksforgeeks.org/clone-method-in-java-2/
	/**
	 * This method clones the instance of this class and
	 * return a clone that doesn't the same memory space
	 * reference.
	 * If you want to manipulate a data from one of the
	 * implementation without making any change to the
	 * original instance, consider using the clone method.
	 * 
	 * @return Object is a cloned object.
	 * 
	 * @throws CloneNotSupportedException if object is not
	 * clonable.
	 * 
	 */
	public Object clone() throws CloneNotSupportedException {
		/*
		 * Every class that implements clone() should call super.clone() 
		 * to obtain the cloned object reference.
		 * 
		 * GeekforGeeks, Clone() method in Java, 13/05/2019
		 * available at: www.geeksforgeeks.org/clone-method-in-java-2/
		 */
		return super.clone();
	}
	
	/**
	 * This method instantiates internally a class ListIterator.
	 * LinkedList implements iterator and works with enhanced-for
	 * known as Foreach.
	 * 
	 * @return Iterator<E>
	 */
	@Override
	public Iterator<E> iterator() {
		return new ListIterator();
	}
	
	// https://www.geeksforgeeks.org/java-implementing-iterator-and-iterable-interface/
	
	/**
	 * ListIterator is a inner class in LinkedList. This class
	 * implements the interface Iterator with a generic type.
	 * Methods hasNext and next are implemented.
	 * Internally, ListIterator uses a Node to reach every next
	 * node until gets to the limit. When an enhanced for or
	 * iterator is traversing ListIterator by 'next' method
	 * it returns the object filed of each node.
	 * 
	 * @author Raul Macedo Fuzita
	 *
	 */
	private class ListIterator implements Iterator<E> {

		private Node<E> node;
		private int nextIndex; // counter
		
		/**
		 * The constructor initializes the node property.
		 */
		public ListIterator() {
			node = first;
		}
		
		/* It has an inherited documentation. See iterator
		 * hasNext description. */
		@Override
		public boolean hasNext() {
			// Safe guard for the loop
			return nextIndex < size;
		}

		/* It has an inherited documentation. See iterator
		 * next description. */
		@Override
		public E next() {
			// grabs current object field
			E item = node.item;
			node = node.next; // looks for next
			nextIndex++; // increment counter
			return item; // returns object field
		}
		
		/**
		 * This method removes an element during the traversal.
		 * This method is a hook method. Therefore optional.
		 * 
		 * @throws UnsupportedOperationException if method is
		 * not implemented.
		 */
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	/*
	 * Method's documentation is available in 
	 * Queue interface. No need to
	 * make any change on it for this method. 
	 */
	@Override
	public E remove() {
		/* final non-access modifier prevents the object
		 * be change in a thread, works like an unmodifiable.
		 * first is the first node of the LinkedList, it's
		 * the head.
		 * */
		final Node<E> f = first;
		// If it's equal to null throws an exception.
		if (f == null)
			throw new NoSuchElementException();
		/*
		 * At this point the method is broke down in 
		 * another method. A method should do only one
		 * thing as it is suggested in the book Clean
		 * Code by Robert C. Martin. It also improves
		 * readability of the code.
		 */
		return unlinkFirst(f);
	}

	/*
	 * Method's documentation is available in 
	 * Queue interface. No need to
	 * make any change on it for this method. 
	 */
	@Override
	public E pull() {
		/* final non-access modifier prevents the object
		 * be change in a thread, works like an unmodifiable.
		 * first is the first node of the LinkedList, it's
		 * the head.
		 * */
		final Node<E> f = first;
		/**
		 * if Node object of instance first is different
		 * to null a ternary operator returns an object
		 * by unlinkFirst method or null.
		 */
		return f != null ? unlinkFirst(f) : null;
	}
	
	/**
	 * This method is reusable in other methods. The idea of 
	 * having a separated method goes against what the 
	 * Clean Code book suggest by Robert C. Martin.
	 * It provides a better way to maintain the code.
	 * 
	 * @param f represents the first node of LinkedList
	 * 
	 * @return object that is in the node.
	 * 
	 */
	private E unlinkFirst(Node<E> f) {
		/* final non-access modifier prevents the object
		 * be changed in a thread, it works like an unmodifiable.
		 * */
		final E item = f.item;
		final Node<E> next = f.next;
		// Reset values of item and next.
		f.item = null;
		f.next = null;
		// assigning next value to first.
		first = next;
		/* If next is equal to null last is "reset". */
		if (next == null) 
			last = null;
		// Otherwise previous reference is "reset".
		else
			next.prev = null;
		// Element removed size has to decrease.
		size--;
		// Returns the removed element
		return item;
	}

	/**
	 * This method returns the front element of a queue
	 * but the peeked element is removed.
	 * 
	 * @return E is a type element. It can be
	 * any Object.
	 */
	@Override
	public E peek() {
		// If object is null returns false.
		Objects.nonNull(first);
		return first.item;
	}

	/*
	 * Method's documentation is available in 
	 * Collection interface. No need to
	 * make any change on it for this method. 
	 */
	@Override
	public boolean add(E e) {
		linkElement(e);
		return false;
	}
	
	@Override
	public boolean addAll(Collection<? extends E> c) {
		return addAll(size, c);
	}
	
	/**
	 * This method will add a collection to the end of the list.
	 * @param index is point where the collection should be insert.
	 * @param c collection containing elements to be added to this list
	 * @return {@code true} if this list changed as a result of the call
	 */
	@SuppressWarnings("unchecked")
	public boolean addAll(int index, Collection<? extends E> c) {
		checkElementIndex(index);
		
		Object[] a = c.toArray();
        int numNew = a.length;
        if (numNew == 0)
            return false;

        for (Object o : a)
        	linkElement((E) o);
        
        return true;
	}
	/**
	 * This method append a data to the LinkedList.
	 * 
	 * @param e is the type of the parameter.
	 * 
	 */
	void linkElement(E e) {
		// Keeps the last reference
		final Node<E> l = last;
		/* Instances a new node that points to the last node and 
		 * has a null value for the next one.*/
		final Node<E> newNode = new Node<>(l, e, null);
		// The last node is assigned by the new node.
		last = newNode;
		/* If last node is null the first node will be 
		 * assigned by the new node as first data in
		 * the LinkedList. Otherwise, since it's assumed that
		 * there is one or more data in the linkedList the
		 * next node of the "l" that points to last is assigned
		 * by the new node.*/
		if (l == null)
			first = newNode;
		else
			l.next = newNode;
		/* To update the size of LinkedList, property
		 * size is incremented by one.*/
		size++;
	}

	/*
	 * Method's documentation is available in 
	 * Collection interface. No need to
	 * make any change on it for this method. 
	 */
	@Override
	public E get(int index) {
		/* get, set and remove by index uses the same method checkElementIndex
		 * to find an element by index. */
		checkElementIndex(index);
		// When a node is returned then it's returns its item
		return (getNode(index)).item;
	}

	/*
	 * Method's documentation is available in 
	 * Collection interface. No need to
	 * make any change on it for this method. 
	 */
	@Override
	public E set(int index, E e) {
		/* get, set and remove by index uses the same method checkElementIndex
		 * to find an element by index. */
		checkElementIndex(index);
		/* Gets node, assigns to it a new value and
		 * also returns it. */
		return getNode(index).item = e;
	}

	/*
	 * Method's documentation is available in 
	 * Collection interface. No need to
	 * make any change on it for this method. 
	 */
	@Override
	public E remove(int index) {
		/* get, set and remove by index uses the same method checkElementIndex
		 * to find an element by index. */
		checkElementIndex(index);
		// The unlinked node is returned after being deleted.
		return unlink(getNode(index));
	}

	/**
	 * This method checks if an element index exists.
	 * If an index doesn't exist it throws an Exception.
	 * 
	 * @param index is an integer value.
	 * 
	 * @throws IndexOutOfBoundsException
	 * 
	 */
	private void checkElementIndex(int index) {
		
		/**
		 * java.util LinkedList has a method that contains the IF statement.
		 * As I said, I woudln't only based my code on but also explain
		 * why I agree with such a decision.
		 * 
		 * This technique is known as encapsulated conditional.
		 * Clean Code, A Handbook of Agile Software Craftsmanship, 
		 * 2009, Robert C. Martin.
		 */
		if (!isElementIndex(index))
			throw new IndexOutOfBoundsException();
	}

	/**
	 * This method checks if the given index is bigger or 
	 * equal to zero, and small or equal to LinkedList size.
	 * 
	 * @param index is an integer value.
	 * @return true if the given index is in the range.
	 */
	private boolean isElementIndex(int index) {
		// Ternary operator
		return index >= 0 && index <= size;
	}
	
	/**
	 * This method finds an element based on an index and returns
	 * the wanted element if it exists.
	 * This method has to be used internally and with a guard to not
	 * throw an Exception.
	 * 
	 * @param index is an integer value.
	 * @return Node of wanted element.
	 */
	private Node<E> getNode(int index) {
		/* Instantiates a Node object and assigned it with the first
		 * Node. Then a For loop statement traversal it while
		 * the For initialization variable is less than the given index.
		 * To keep it shorter and simple each next node is reassigned to
		 * the same Node instance.*/
		Node<E> node = first;
		for (int i = 0; i < index; i++)
			node = node.next;
		// Returns the node
		return node;
	}

	/*
	 * Method's documentation is available in 
	 * Collection interface. No need to
	 * make any change on it for this method. 
	 */
	@Override
	public boolean remove(Object o) {
		/* Checks for a null value can be a bit unusual but since
		 * LinkedList allows any time of data even null. The algorithm
		 * has to be able to handle whatever is the data in any
		 * scenario. */
		if (o == null) {
			/* Many people when learn about For loop statement They learn
			 * that for loop has an integer value for initialization, 
			 * an integer value for comparison and an increment step.
			 * Actually, For loop statement is much more powerful.
			 * If a While loop statement is used a temporary Node instance
			 * has to be created and then it is reassigned with the
			 * next Node. Since a For statement has an initialization.
			 * It can be used to easily initialize a temporary Node
			 * compare and keep retrieving the next Node. */
			for (Node<E> node = first; node != null; node = node.next) {
				// If item field is equal to null this node is unlinked.
				if (node.item == null) {
					unlink(node);
					return true;
				}
			}
		} else {
			for (Node<E> node = first; node != null; node = node.next) {
				/* I'm glad Object java class has as default a method to
				 * compare objects. In this case, if the wanted object is
				 * equal to the object in the current node, the node
				 * is unlinked to the LinkedList. */
				if (o.equals(node.item)) {
					unlink(node);
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * This private method unlinks nodes in a LinkedList.
	 * 
	 * @param node is an object of Node {@literal <E>}
	 * @return E that is any type of object. It depends on 
	 * the instance type.
	 */
	private E unlink(Node<E> node) {
		
		/* final non-access modifier prevents the object
		 * be changed in a thread, it works like an unmodifiable. */
		
		// Stores the item into a temporary object to returns it later on.
		final E item = node.item;
		final Node<E> prev = node.prev; // Gets previous node of a given node.
		final Node<E> next = node.next; // Gets next node of a given node.
		
		/* If previous node is equal to null the next reference is shifted
		 * to the first node of LinkedList. Otherwise the next node
		 * is shifted to the next node of previous one, and the given previous
		 * node is assigned to null. */
		if (prev == null) {
			first = next;
		} else {
			prev.next = next;
			node.prev = null;
		}
		
		/* If next node is equal to null the previous reference is shifted
		 * to the last node of LinkedList. Otherwise the previous node
		 * is shifted to the previous node of next one, and the given next
		 * node is assigned to null. */
		if (next == null) {
			last = prev;
		} else {
			next.prev = prev;
			node.next = null;
		}
		// Cleans the stored data.
		node.item = null;
		// Decrements LinkedList's size by one.
		size--;
		// Returns the unlinked data.
		return item;
	}

	/*
	 * Method's documentation is available in 
	 * Collection interface. No need to
	 * make any change on it for this method. 
	 */
	@Override
	public boolean contains(Object o) {
		/* Calls indexOf method to returns an index.
		 * If an index is bigger or equal to zero
		 * a boolean value is return. */
		return indexOf(o) >= 0;
	}

	/*
	 * Method's documentation is available in 
	 * Collection interface. No need to
	 * make any change on it for this method. 
	 */
	@Override
	public int indexOf(Object o) {
		// A counter is used to track indexes.
		int index = 0;
		/* Checks for a null value can be a bit unusual but since
		 * LinkedList allows any time of data even null. The algorithm
		 * has to be able to handle whatever is the data in any
		 * scenario. */
		if (o == null) {
			/* Many people when learn about For loop statement They learn
			 * that for loop has an integer value for initialization, 
			 * an integer value for comparison and an increment step.
			 * Actually, For loop statement is much more powerful.
			 * If a While loop statement is used a temporary Node instance
			 * has to be created and then it is reassigned with the
			 * next Node. Since a For statement has an initialization.
			 * It can be used to easily initialize a temporary Node
			 * compare and keep retrieving the next Node. */
			for (Node<E> node = first; node != null; node = node.next) {
				// If a null is found the current index value is returned
				if (node.item == null)
					return index;
				// If nothing is equal. The variable index keeps incrementing.
				index++;
			}
		} else {
			/* I'm glad Object java class has as default a method to
			 * compare objects. In this case, if the wanted object is
			 * equal to the object in the current node, the node
			 * is unlinked to the LinkedList. */
			for (Node<E> node = first; node != null; node = node.next) {
				/* If a given object is equal to the Node item field
				 * It returns the current index value.*/
				if (o.equals(node.item))
					return index;
				// If nothing is equal. The variable index keeps incrementing.
				index++;
			}
		}
		/* If any objects are found in the instance of linkedList. 
		 * It returns -1. */
		return -1;
	}

	/*
	 * Method's documentation is available in 
	 * Collection interface. No need to
	 * make any change on it for this method. 
	 */
	@Override
	public int lastIndexOf(Object o) {
		// A counter is used to track indexes backwards.
		int index = size;
		/* Checks for a null value can be a bit unusual but since
		 * LinkedList allows any time of data even null. The algorithm
		 * has to be able to handle whatever is the data in any
		 * scenario. */
		if (o == null) {
			/* Many people when learn about For loop statement They learn
			 * that for loop has an integer value for initialization, 
			 * an integer value for comparison and an increment step.
			 * Actually, For loop statement is much more powerful.
			 * If a While loop statement is used a temporary Node instance
			 * has to be created and then it is reassigned with the
			 * next Node. Since a For statement has an initialization.
			 * It can be used to easily initialize a temporary Node
			 * compare and keep retrieving the next Node. */
			for (Node<E> node = last; node != null; node = node.prev) {
				/* Before return any index it has to be decremented 
				 * since index was assigned with size value at the
				 * beginning. */
				index--;
				// If a null is found the current index value is returned
				if (node.item == null)
					return index;
			}
		} else {
			/* I'm glad Object java class has as default a method to
			 * compare objects. In this case, if the wanted object is
			 * equal to the object in the current node, the node
			 * is unlinked to the LinkedList. */
			for (Node<E> node = last; node != null; node = node.prev) {
				/* Before return any index it has to be decremented 
				 * since index was assigned with size value at the
				 * beginning. */
				index--;
				/* If a given object is equal to the Node item field
				 * It returns the current index value.*/
				if (o.equals(node.item))
					return index;
			}
		}
		/* If any objects are found in the instance of linkedList. 
		 * It returns -1. */
		return -1;
	}
	
	/*
	 * Method's documentation is available in 
	 * Collection interface. No need to
	 * make any change on it for this method. 
	 */
	@Override
	public int size() {
		// LinkedList size property
		return size;
	}

	/*
	 * Method's documentation is available in 
	 * Collection interface. No need to
	 * make any change on it for this method. 
	 */
	@Override
	public boolean isEmpty() {
		// if size is equal to zero it returns true. Otherwise false.
		return size == 0;
	}

	/*
	 * Method's documentation is available in 
	 * Collection interface. No need to
	 * make any change on it for this method. 
	 */
	@Override
	public void clear() {
		/**
		 * Here I used the same technique as in java.util.Linkedlist.
		 * A For loop statement is not really necessary to use the 
		 * last parameter. To be honest it's quite common in a dynamic
		 * variables such as in PHP, javascript, and so on.
		 * In this scenario every next node has to be stored
		 * in a new instance in order to assign null to every
		 * node fields.
		 * The reason behind of set null to them in java.util.LinkedList
		 * is to give a little help to Garbage Collector since we never
		 * know when it's going visit that memory space to clean it up.
		 */
		for (Node<E> node = first; node != null;) {
			Node<E> next = node.next;
			node.item = null;
			node.prev = null;
			node.next = null;
			node = next;
		}
		/* This technique is quite common, as well. last node is
		 * set to null and consequently, it sets a null value to
		 * first node, as well. */
		first = last = null;
		/* Since no more node are reference to each other
		 * and there is no more data stored in the LinkedList either.
		 * The LinkedList size is set to zero. */
		size = 0;
	}

	/*
	 * Method's documentation is available in 
	 * Collection interface. No need to
	 * make any change on it for this method. 
	 */
	@Override
	public Object[] toArray() {
		Object[] objects = new Object[size];
		int i = 0;
		for (Node<E> node = first; node != null; node = node.next)
			objects[i++] = node.item;
		return objects;
	}

	/*
	 * Method's documentation is available in 
	 * Collection interface. No need to
	 * make any change on it for this method. 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] array) {
		// Assigns an array of Object with a Generic array.
		Object[] objects = array;
		// 'i' is a counter to traversal the array of Object.
		int i = 0;
		/**
		 * Traversing each element of the collection and assigning each
		 * index of the array of Object with the value of current
		 * Node item.
		 */
		for (Node<E> node = first; node != null; node = node.next)
			objects[i++] = node.item;
		
		/**
		 * Clear the last index if array size is bigger than collection size.
		 */
		if (array.length > size)
			array[size] = null;
		/* Return an array of Generic type. The type is according to the 
		 * type passed as parameter. */
		return (T[]) objects;
	}

}
