package edu.isu.cs.cs3308.traversals;

import edu.isu.cs.cs3308.structures.Tree;
import edu.isu.cs.cs3308.traversals.commands.TraversalCommand;

public abstract class AbstractTraversal<E> implements TreeTraversal<E> {
	public Tree<E> tree;
	public TraversalCommand command;

	/**
	 * Constructor for the tree traversal
	 *
	 * @param tree tree to traverse
	 */
	public AbstractTraversal(Tree<E> tree) {
		if (tree == null) {
			throw new IllegalArgumentException("Tree is null");
		}
		this.tree = tree;
	}

	/**
	 * Sets the executable command to the provided value.
	 *
	 * @param cmd The new executable command
	 */
	@Override
	public void setCommand(TraversalCommand cmd) {
		this.command = cmd;
	}
}
