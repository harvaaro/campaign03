package edu.isu.cs.cs3308.traversals;

import edu.isu.cs.cs3308.structures.Node;
import edu.isu.cs.cs3308.structures.Tree;

import java.util.List;

public class PostOrderTraversal<E> extends DepthFirstTraversal<E> {
	/**
	 * Constrcutor for the tree traversal
	 *
	 * @param tree tree to traverse
	 */
	public PostOrderTraversal(Tree<E> tree) {
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
		super.subtree(p, snapshot);
	}
}