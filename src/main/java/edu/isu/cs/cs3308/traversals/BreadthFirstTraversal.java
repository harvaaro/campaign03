package edu.isu.cs.cs3308.traversals;

import edu.isu.cs.cs3308.structures.Node;
import edu.isu.cs.cs3308.structures.Tree;
import edu.isu.cs.cs3308.structures.impl.LinkedBinaryTree.BinaryTreeNode;
import edu.isu.cs.cs3308.structures.impl.LinkedQueue;

import java.util.LinkedList;

public class BreadthFirstTraversal<E> extends AbstractTraversal<E> {
	/**
	 * Constrcutor for the tree traversal
	 *
	 * @param tree tree to traverse
	 */
	public BreadthFirstTraversal(Tree<E> tree) {
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
		if (tree.root() == null) {
			return null;
		}
		else {
			return traverseFrom(tree.root());
		}
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
		tree.validate(node);

		LinkedQueue<BinaryTreeNode<E>> nodeQue = new LinkedQueue<>();
		LinkedList<Node<E>> listNode = new LinkedList<>();

		while (!nodeQue.isEmpty()) {
			BinaryTreeNode<E> tempNode = nodeQue.poll();
//			tree.validate(tempNode);
			listNode.addLast(tempNode);
			if (tempNode.getLeft() != null) {
				nodeQue.offer(tempNode.getLeft());
			}
			if (tempNode.getRight() != null) {
				nodeQue.offer(tempNode.getRight());
			}
		}
		return listNode;
	}
}
