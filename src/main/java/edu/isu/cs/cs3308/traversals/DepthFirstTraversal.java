package edu.isu.cs.cs3308.traversals;

import edu.isu.cs.cs3308.structures.Node;
import java.util.List;

public class DepthFirstTraversal<E> extends AbstractTraversal<E> {
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
		return super.traverse();
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
		return super.traverseFrom(node);
	}


	public void subtree(List<Node<E>> snapshot, Node<E> p){

	}
}
