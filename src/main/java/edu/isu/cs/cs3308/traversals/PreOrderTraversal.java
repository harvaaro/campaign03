package edu.isu.cs.cs3308.traversals;

import edu.isu.cs.cs3308.structures.Node;
import edu.isu.cs.cs3308.structures.Tree;
import edu.isu.cs.cs3308.structures.impl.LinkedBinaryTree.BinaryTreeNode;

import java.util.List;

public class PreOrderTraversal<E> extends DepthFirstTraversal<E> {
	/**
	 * Constructor for the tree traversal
	 *
	 * @param tree tree to traverse
	 */
	public PreOrderTraversal(Tree<E> tree) {
		super(tree);
	}

	/**
	 * Traverses a subtree with a supplied node as the root
	 *
	 * @param p node to use as root
	 * @param snapshot list of nodes to pull from
	 */
	@Override
	public void subtree(Node<E> p, List<Node<E>> snapshot) {
		if (snapshot == null) {
			throw new IllegalArgumentException("List is null");
		}
		BinaryTreeNode<E> btn = (BinaryTreeNode<E>)tree.validate(p);

		snapshot.add(btn);
		if (command != null) {
			command.execute(tree, btn);
		}

		if (btn.getLeft() != null) {
			subtree(btn.getLeft(), snapshot);
		}
		if (btn.getRight() != null) {
			subtree(btn.getRight(), snapshot);
		}
	}
}