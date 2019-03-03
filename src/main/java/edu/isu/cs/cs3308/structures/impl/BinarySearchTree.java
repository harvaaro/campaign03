package edu.isu.cs.cs3308.structures.impl;

import edu.isu.cs.cs3308.structures.BinaryTree;
import edu.isu.cs.cs3308.structures.Node;

public class BinarySearchTree<E> implements BinaryTree<E> {
	/**
	 * Returns the left child of the provided node.
	 *
	 * @param p The parent node of whom the left child is desired.
	 * @return The left child of the provided node, can be null if no such child
	 * exists.
	 * @throws IllegalArgumentException If the provided node is invalid.
	 */
	@Override
	public Node<E> left(Node<E> p) throws IllegalArgumentException {
		return null;
	}

	/**
	 * Returns the right child of the provided node.
	 *
	 * @param p The parent node of whom the right child is desired.
	 * @return The right child of the provided node, can be null if no such
	 * child exists.
	 * @throws IllegalArgumentException If the provided node is invalid.
	 */
	@Override
	public Node<E> right(Node<E> p) throws IllegalArgumentException {
		return null;
	}

	/**
	 * Returns the sibling node of the provided node, if such a sibling exists.
	 * That is, if the right node is provided the left node will be returned
	 * from the same parent.
	 *
	 * @param p The node of whom a sibling is requested.
	 * @return The sibling of the provided node, or null if no such sibling
	 * exists.
	 * @throws IllegalArgumentException If the provided node is invalid.
	 */
	@Override
	public Node<E> sibling(Node<E> p) throws IllegalArgumentException {
		return null;
	}

	/**
	 * Adds the provided element as a new node to the left side of the provided
	 * node.
	 *
	 * @param p       The node to which the element is to be added as the left child.
	 * @param element Element to be added
	 * @return The newly created left child of the provided node
	 * @throws IllegalArgumentException If the provided node is invalid, if the
	 *                                  provided element is null, or if the provided node already has a left
	 *                                  child.
	 */
	@Override
	public Node<E> addLeft(Node<E> p, E element) throws IllegalArgumentException {
		return null;
	}

	/**
	 * Adds the provided element as a new node to the right side of the provided
	 * node.
	 *
	 * @param p       The node to which the element is to be added as the right child.
	 * @param element Element to be added
	 * @return The newly created right child of the provided node
	 * @throws IllegalArgumentException If the provided node is invalid, if the
	 *                                  provided element is null, or if the provided node already has a right
	 *                                  child.
	 */
	@Override
	public Node<E> addRight(Node<E> p, E element) throws IllegalArgumentException {
		return null;
	}
}
