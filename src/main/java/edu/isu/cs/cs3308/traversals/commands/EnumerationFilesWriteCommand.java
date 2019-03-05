package edu.isu.cs.cs3308.traversals.commands;

import edu.isu.cs.cs3308.structures.Node;
import edu.isu.cs.cs3308.structures.Tree;
import edu.isu.cs.cs3308.structures.impl.LinkedBinaryTree;
import edu.isu.cs.cs3308.Datum;

import java.util.LinkedList;

/**
 * A Traversal Command that writes the output string to a list
 *
 * Based off of the code from Isaac Griffith
 *
 * @author Aaron Harvey
 */
public class EnumerationFilesWriteCommand extends TraversalCommand<Datum> {

	private LinkedList<CharSequence> saveString = new LinkedList<>();

	/**
	 * Constructs a new Command
	 */
	public EnumerationFilesWriteCommand() {

	}

	/**
	 * The appropriate format for the output should be as follows: %d:%d:%s where:
	 *
	 * The first number is the number of the parent datum object, or -1 if the parent is null
	 * The second number is the number associated with the current datum object
	 * The string is the value of the prompt for the current datum object
	 *
	 * Also note that each datum object should occupy its own line in the file
	 */
	@Override
	public void execute(Tree<Datum> tree, Node<Datum> node) {
		Datum data = node.getElement();
		int parentNum = node.getParent() == null ? -1 : node.getParent().getElement().getNumber();
		String side = "r";
		if (node.getParent() != null)
			side = node.equals(((LinkedBinaryTree<Datum>) tree).left(node.getParent())) ? "l" : "r";
		saveString.addLast(String.format("%d:%d:%s:%s%n", parentNum, data.getNumber(), side, data.getPrompt()));
	}

	/**
	 * Get the saved list of strings
	 * @return The list of strings
	 */
	public LinkedList<CharSequence> getSaveString() {
		return saveString;
	}
}
