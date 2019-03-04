package edu.isu.cs.cs3308.traversals;

import edu.isu.cs.cs3308.structures.Node;
import edu.isu.cs.cs3308.structures.Tree;

import java.util.LinkedList;
import java.util.List;

public class DepthFirstTraversal<E> extends AbstractTraversal<E> {
	/**
	 * Constrcutor for the tree traversal
	 *
	 * @param tree tree to traverse
	 */
	public DepthFirstTraversal(Tree<E> tree) {
		super(tree);
	}

	/**
	 * Method which initiates the traversal of a tree from the root node. This
	 * method returns the an iterable container of nodes representing a
	 * resulting traveral of the tree.
	 *
	 * @return An iterable container of nodes representing the traversal of a
	 * tree.
	 */
	@Override
	public Iterable<Node<E>> traverse() {
		return traverseFrom(tree.root());
	}

	/**
	 * Method which initiates the traversal of a tree from the root node. This
	 * method returns the an iterable container of nodes representing a
	 * resulting traveral of the tree.
	 *
	 * @param node Root of the subtree to start the traversal at.
	 * @return An iterable container of nodes representing the traversal of a
	 * tree.
	 */
	@Override
	public Iterable<Node<E>> traverseFrom(Node<E> node) {
		return null;
	}

	/**
	 * Gets an iterable subtree from the provided node
	 *
	 * @param node Node to get subtree from
	 * @return An iterable list of nodes
	 */
	public Iterable<Node<E>> subTreeTraverse(Node<E> node) {
		LinkedList<Node<E>> list = new LinkedList<>();

		if (!tree.isEmpty()) {
			subtree(node, list);
		}

		return list;
	}

	/**
	 * Traverses a subtree with a supplied node as the root
	 *
	 * @param p node to use as root
	 * @param snapshot list of nodes to pull from
	 */
	public void subtree(Node<E> p, List<Node<E>> snapshot) {}
}
