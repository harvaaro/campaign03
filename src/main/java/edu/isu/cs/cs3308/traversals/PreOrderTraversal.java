package edu.isu.cs.cs3308.traversals;

import edu.isu.cs.cs3308.structures.Node;
import edu.isu.cs.cs3308.structures.Tree;

import java.util.List;

public class PreOrderTraversal<E> extends DepthFirstTraversal<E> {
	public PreOrderTraversal(Tree<E> tree) {
		super(tree);
	}

	@Override
	public void subtree(Node<E> p, List<Node<E>> snapshot) {
		super.subtree(p, snapshot);
	}
}