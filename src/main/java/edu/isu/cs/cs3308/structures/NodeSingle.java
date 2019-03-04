package edu.isu.cs.cs3308.structures;

/**
 * NodeSingle class needed for the List
 * based on code shown by Isaac Griffith in class
 *
 * @author Aaron Harvey
 * @param <E> any type of node single
 */
public class NodeSingle<E> {

	protected E data;
	protected NodeSingle<E> next;

	/**
	 * Constructor with data parameter
	 * @param data Whatever data the Node should store
	 */
	public NodeSingle(E data) {
		this.data = data;
	}

	/**
	 * Get what data is stored in the Node
	 * @return The data the node currently is storing
	 */
	public E getData() {
		return data;
	}

	/**
	 * Set what data should be stored in the Node
	 * @param data The data the node should be storing
	 */
	public void setData(E data) {
		this.data = data;
	}

	/**
	 * Get what Node is stored as the next in the List.
	 * @return The Node that is currently stored in the next attribute
	 */
	public NodeSingle<E> getNext() {
		return next;
	}

	/**
	 * Set what Node should be stored as the next in the List.
	 * @param next The Node that should
	 */
	public void setNext(NodeSingle<E> next) {
		this.next = next;
	}
}
