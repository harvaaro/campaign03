package edu.isu.cs.cs3308.structures.impl;

import com.google.common.collect.Iterables;
import edu.isu.cs.cs3308.structures.BinaryTree;
import edu.isu.cs.cs3308.structures.Node;
import edu.isu.cs.cs3308.structures.Tree;

import java.util.LinkedList;

/**
 * Linked Binary Tree implemented from Binary Tree and Tree
 *
 * @author Aaron Harvey
 * @param <E> The type of data to be stored in this binary tree.
 */
public class LinkedBinaryTree<E> implements BinaryTree<E>, Tree<E> {

	protected BinaryTreeNode<E> root = null;
	private int size = 0;

	//region BinaryTreeNode_Related

	/**
	 * A Node to be used with binary trees, to know what the
	 * parent, left and right nodes are. And to store the data element
	 *
	 * @param <E> element type for the node
	 */
	public static class BinaryTreeNode<E> implements Node<E> {
		private E element;
		private BinaryTreeNode<E> parent;
		private BinaryTreeNode<E> left;
		private BinaryTreeNode<E> right;

		public BinaryTreeNode(E element, BinaryTreeNode<E> parent,
							  BinaryTreeNode<E> left, BinaryTreeNode<E> right) {
			this.element = element;
			this.parent = parent;
			this.left = left;
			this.right = right;
		}

		/**
		 * @return The element contained in this node.
		 */
		@Override
		public E getElement() {
			return element;
		}

		/**
		 * Sets the new value of this node to the provided one. Thows an
		 * IllegalArgumentException if the provided value is null.
		 *
		 * @param element New value to be contained in this node.
		 */
		@Override
		public void setElement(E element) throws IllegalArgumentException {
			if (element == null) {
				throw new IllegalArgumentException("Element provided was null");
			}
			else {
				this.element = element;
			}
		}

		/**
		 * @return The parent node of this class. Can be null.
		 */
		@Override
		public BinaryTreeNode<E> getParent() {
			return parent;
		}

		/**
		 * Sets the Parent node to the supplied value
		 *
		 * @param parent Node to set as this nodes parent
		 */
		public void setParent(BinaryTreeNode<E> parent) {
			this.parent = parent;
		}

		/**
		 * @return The left node of this class. Can be null.
		 */
		public BinaryTreeNode<E> getLeft() {
			return left;
		}

		/**
		 * Sets the Left node to the supplied value
		 *
		 * @param left Node to set as this nodes left
		 */
		public void setLeft(BinaryTreeNode<E> left) {
			this.left = left;
		}

		/**
		 * @return The left node of this class. Can be null.
		 */
		public BinaryTreeNode<E> getRight() {
			return right;
		}

		/**
		 * Sets the Right node to the supplied value
		 *
		 * @param right Node to set as this nodes right
		 */
		public void setRight(BinaryTreeNode<E> right) {
			this.right = right;
		}
	}

	/**
	 * Cronstructor for a BinaryTreeNode
	 *
	 * @param element data for the node
	 * @param parent parent of the node
	 * @param left child left node
	 * @param right child right node
	 * @return The newly created node.
	 */
	public BinaryTreeNode<E> createNode(E element, BinaryTreeNode<E> parent,
										BinaryTreeNode<E> left, BinaryTreeNode<E> right) {
		itemValid(element);
		return new BinaryTreeNode<>(element, parent, left, right);
	}

	//endregion BinaryTreeNode_Related

	//region BinaryTree_Overrides

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
		BinaryTreeNode<E> btn = (BinaryTreeNode<E>)validate(p);
		return btn.getLeft();
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
		BinaryTreeNode<E> btn = (BinaryTreeNode<E>)validate(p);
		return btn.getRight();
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
		BinaryTreeNode<E> btn = (BinaryTreeNode<E>)validate(p);
		if (btn.getParent() == null) {
			return null;
		}
		else {
			BinaryTreeNode<E> tempParent = btn.getParent();
			BinaryTreeNode<E> tempLeft = tempParent.getLeft();
			BinaryTreeNode<E> tempRight = tempParent.getRight();

			if (btn == tempLeft) {
				return tempRight;
			}
			else {
				return tempLeft;
			}
		}
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
		itemValid(element);
		BinaryTreeNode<E> btn = (BinaryTreeNode<E>)validate(p);
		if (btn.getLeft() != null) {
			throw new IllegalArgumentException("Left is occupied");
		}
		else {
			btn.setLeft(createNode(element,btn,null,null));
			size++;
			return btn.getLeft();
		}
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
		itemValid(element);
		BinaryTreeNode<E> btn = (BinaryTreeNode<E>)validate(p);
		if (btn.getRight() != null) {
			throw new IllegalArgumentException("Left is occupied");
		}
		else {
			btn.setRight(createNode(element,btn,null,null));
			size++;
			return btn.getRight();
		}
	}

	//endregion BinaryTree_Overrides

	//region Tree_Overrides

	/**
	 * @return The root node of this tree or null if the Tree is empty.
	 */
	@Override
	public Node<E> root() {
		return root;
	}

	/**
	 * Sets the tree's root node to the provided item, by creating a new node
	 * (unless the given item is the same as the current root's item). Note that
	 * this must also reset the size of the tree to the correct value if the
	 * current node is replaced.
	 *
	 * @param item New item for the root node.
	 * @return The new root node.
	 */
	@Override
	public Node<E> setRoot(E item) {
		if (item == null) {
			root = null;
			size = 0;
		}
		else {
			if (isEmpty() || item != root().getElement()) {
				root = createNode(item, null, null, null);
				size = 1;
			}
		}
		return root;
	}

	/**
	 * Returns the parent node of the node provided, or null if the node is also
	 * the root of the tree.
	 *
	 * @param p Node whose parent is being requested.
	 * @return The parent of the provided node, or null if the provided node is
	 * the root.
	 * @throws IllegalArgumentException If the node is invalid
	 */
	@Override
	public Node<E> parent(Node<E> p) throws IllegalArgumentException {
		nodeValid(p);
		if (isRoot(p)) {
			return null;
		}
		else {
			return p.getParent();
		}
	}

	/**
	 * Returns an iterable collection of the children attached to the provided
	 * node.
	 *
	 * @param p The node whose children are requested.
	 * @return An iterable collection of the children attached to the provided
	 * node.
	 * @throws IllegalArgumentException If the provided node is invalid
	 */
	@Override
	public Iterable<Node<E>> children(Node<E> p) throws IllegalArgumentException {
		BinaryTreeNode<E> btn = (BinaryTreeNode<E>)validate(p);
		LinkedList<Node<E>> childList = new LinkedList<>();

		if (btn.getLeft() != null) {
			childList.addLast(btn.getLeft());
		}
		if (btn.getRight() != null) {
			childList.addLast(btn.getRight());
		}
		return childList;
	}

	/**
	 * Returns the number of children currently attached to the provided node.
	 *
	 * researched the size of iterables here
	 * https://stackoverflow.com/questions/11598977/get-size-of-an-iterable-in-java
	 *
	 * @param p Node whose number of children is requested.
	 * @return The number of children attached to the provided node.
	 * @throws IllegalArgumentException If the node is invalid.
	 */
	@Override
	public int numChildren(Node<E> p) throws IllegalArgumentException {
		return Iterables.size(children(p));
	}

	/**
	 * Tests whether the node is an internal node or not. That is whether the
	 * node has children.
	 *
	 * @param p The node to test.
	 * @return True if the node is an internal node, false otherwise.
	 * @throws IllegalArgumentException If the node is invalid.
	 */
	@Override
	public boolean isInternal(Node<E> p) throws IllegalArgumentException {
		return (numChildren(p) > 0);
	}

	/**
	 * Tests whether the node is an external node of the tree. That is whether
	 * the node has no children and thus is a leaf of the tree.
	 *
	 * @param p The node to test.
	 * @return True if the node is a leaf node, false otherwise.
	 * @throws IllegalArgumentException If the node is invalid
	 */
	@Override
	public boolean isExternal(Node<E> p) throws IllegalArgumentException {
		return (numChildren(p) == 0);
	}

	/**
	 * Tests whether this node is the root node of the tree. That is that the
	 * provided node has children but not parent.
	 *
	 * @param p Node to test.
	 * @return True if the node is the root of the tree.
	 * @throws IllegalArgumentException If the provided node is invalid.
	 */
	@Override
	public boolean isRoot(Node<E> p) throws IllegalArgumentException {
		nodeValid(p);
		return (p == root());
	}

	/**
	 * Inserts the item into the tree under the provided node. If the provided
	 * node is null the item becomes the new root of the tree, beware.
	 *
	 * @param item Item to be inserted into the tree.
	 * @param p    The parent node of the tree, if null the item becomes the new
	 *             root so beaware.
	 * @return True if the item was able to be inserted, false otherwise (for
	 * example the item was null)
	 * @throws IllegalArgumentException if the provided parent node is invalid,
	 *                                  or the provided value is null.
	 */
	@Override
	public Node<E> insert(E item, Node<E> p) {
		itemValid(item);
		/*FIXME: I had p == null || in this condition too, because of the comment
		 * above but removed it because the testInsert_4 would make that invalid */
		if (isEmpty()) {
			return setRoot(item);
		}
		else {
			nodeValid(p);
			if (isExternal(p)) {
				return addLeft(p,item);
			}
			else {
				if (left(p) == null) {
					return addLeft(p, item);
				}
				if (right(p) == null) {
					return addRight(p, item);
				}
			}
		}
		throw new IllegalArgumentException("Cannot insert node");
	}

	/**
	 * Removes the given item from the provided parent node.
	 *
	 * @param item Item to be removed from the list of children of the provided
	 *             node.
	 * @param p    Parent node.
	 * @return true if the item was removed, false otherwise.
	 * @throws IllegalArgumentException If the provided parent node is not valid
	 *                                  or the value is null.
	 */
	@Override
	public boolean remove(E item, Node<E> p) throws IllegalArgumentException {
		/*FIXME: had to do this because of testRemove_3 and 4, but the comment
		 * above expects it to be an IllegalArgumentException */
		if (item == null || p == null) {
			return false;
		}

		itemValid(item);
		BinaryTreeNode<E> btn = (BinaryTreeNode<E>)validate(p);

		if (isExternal(btn)) {
			return false;
		}
		else {
			if (btn.getLeft() != null && item == btn.getLeft().getElement()) {
				BinaryTreeNode<E> removeChild = btn.getLeft();
				removeChild.setParent(null);
				btn.setLeft(null);
				size--;
				return true;
			}
			else {
				BinaryTreeNode<E> removeChild = btn.getRight();
				removeChild.setParent(null);
				btn.setRight(null);
				size--;
				return true;
			}
		}
	}

	/**
	 * @return The number of nodes currently in the tree.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * @return true if the tree contains no nodes (that is the root = null),
	 * false otherwise.
	 */
	@Override
	public boolean isEmpty() {
		return (root() == null);
	}

	/**
	 * Updates the value of the node to the provided value. Throws an
	 * IllegalArgumentException if the value is null, the node is null, or the
	 * node is not in this Tree.
	 *
	 * @param node    Node whose value is to be updated.
	 * @param element New value for the node.
	 * @throws IllegalArgumentException If the provided node is invalid, or the
	 *                                  element value is null.
	 */
	@Override
	public E set(Node<E> node, E element) throws IllegalArgumentException {
		itemValid(element);
		nodeValid(node);
		if (!inThisTree(node)) {
			throw new IllegalArgumentException("Node not found in this tree");
		}
		E oldElem = node.getElement();
		node.setElement(element);

		//FIXME: not sure what to return here but figured the old value
		return oldElem;
	}

	/**
	 * Validates that the provided node is not null, is of a subtype of Node
	 * supported by the implementing tree class, and is currently in this tree.
	 * If these conditions are not met then an IllegalArgumentException is
	 * thrown.
	 *
	 * @param p The node to be validated.
	 * @return A node of the expected type specific to the implementing tree.
	 * @throws IllegalArgumentException Thrown if the provided node is null, not
	 *                                  in the current tree, or is not of a type supported by the current tree.
	 */
	@Override
	public Node<E> validate(Node<E> p) throws IllegalArgumentException {
		nodeValid(p);
		if (!inThisTree(p)) {
			throw new IllegalArgumentException("Node is not in this tree");
		}
		return p;
	}

	/**
	 * Calculates the depth of the given node in the current tree.
	 *
	 * @param node Node whose depth is to be calculated
	 * @return Depth of the node in the tree.
	 * @throws IllegalArgumentException If the provided node is invalid
	 */
	@Override
	public int depth(Node<E> node) throws IllegalArgumentException {
		if (isRoot(node)) {
			return 0;
		}
		else {
			return 1 + depth(parent(node));
		}
	}

	/**
	 * Recusively calculates the size of a subtree rooted at the provided node.
	 *
	 * @param node Node whose subtree size is to be calculated
	 * @return Size of the subtree (excluding the root)
	 * @throws IllegalArgumentException If the provided node is invalid.
	 */
	@Override
	public int subTreeSize(Node<E> node) throws IllegalArgumentException {
		BinaryTreeNode<E> btn = (BinaryTreeNode<E>)validate(node);
		int subSize = 0;
		for (Node<E> child : children(btn)) {
			subSize = Math.max(subSize, 1 + subTreeSize(child));
		}
		return subSize;
	}

	/**
	 * Checks if the provided node is the last child of it's parent node. Note
	 * that the root node always returns true.
	 *
	 * @param node Node to check.
	 * @return True if the node is the last child of it's parent node or is the
	 * root, false otherwise.
	 * @throws IllegalArgumentException If the provided node is not valid.
	 */
	@Override
	public boolean isLastChild(Node<E> node) throws IllegalArgumentException {
		BinaryTreeNode<E> btn = (BinaryTreeNode<E>)validate(node);
		return (isRoot(btn) || sibling(btn) == null || children(btn) == null);
	}

	//endregion Tree_Overrides

	//region Custom_Methods

	/**
	 * Recursively checks to see if the provided node
	 * is within the current tree by comparing the root
	 *
	 * @param curr The node to check for in the tree
	 * @return True if in the tree, False if not in the tree
	 */
	private boolean inThisTree(Node<E> curr) {
		if (isRoot(curr)) {
			return true;
		}
		else if (isRoot(parent(curr))) {
			return true;
		}
		else {
			return inThisTree(parent(curr));
		}
	}

	/**
	 * Validates a given node to be not null and of a Node subtype
	 *
	 * @param node Node to check
	 * @throws IllegalArgumentException if node is null or wrong type
	 */
	private void nodeValid(Node<E> node) throws IllegalArgumentException {
		if (node == null) {
			throw new IllegalArgumentException("Node is null");
		}
		if (!(node instanceof BinaryTreeNode)) {
			throw new IllegalArgumentException("Node is not a valid type");
		}
	}

	/**
	 * Validates a provided element is not null
	 *
	 * @param item Element to check
	 * @throws IllegalArgumentException if element is null
	 */
	private void itemValid(E item) throws IllegalArgumentException {
		if (item == null) {
			throw new IllegalArgumentException("Element is null");
		}
	}

	//endregion Custom_Methods

}
