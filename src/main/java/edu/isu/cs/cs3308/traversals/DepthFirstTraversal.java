package edu.isu.cs.cs3308.traversals;

import edu.isu.cs.cs3308.structures.Node;
import edu.isu.cs.cs3308.structures.Tree;

import java.util.LinkedList;
import java.util.List;

public class DepthFirstTraversal<E> extends AbstractTraversal<E> {
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


	public Iterable<Node<E>> subTreeTraverse(Node<E> node) {
		LinkedList<Node<E>> list = new LinkedList<>();

		if (!tree.isEmpty()) {
			subtree(node, list);
		}

		return list;
	}

	public void subtree(Node<E> p, List<Node<E>> snapshot) {}
}
