package edu.isu.cs.cs3308;

import edu.isu.cs.cs3308.structures.BinaryTree;
import edu.isu.cs.cs3308.structures.Node;
import edu.isu.cs.cs3308.structures.impl.BinarySearchTree;
import edu.isu.cs.cs3308.structures.impl.LinkedBinaryTree;
import edu.isu.cs.cs3308.structures.impl.LinkedBinaryTree.BinaryTreeNode;
import edu.isu.cs.cs3308.traversals.BreadthFirstTraversal;
import edu.isu.cs.cs3308.traversals.InOrderTraversal;
import edu.isu.cs.cs3308.traversals.PreOrderTraversal;
import edu.isu.cs.cs3308.traversals.TreeTraversal;
import edu.isu.cs.cs3308.traversals.commands.EnumeratedSaveCommand;
import edu.isu.cs.cs3308.traversals.commands.EnumerationCommand;
import edu.isu.cs.cs3308.traversals.commands.EnumerationFilesWriteCommand;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

/**
 * A very simple classification tree example using a BinaryTree and console
 * input.
 *
 * @author Isaac Griffith
 */
public class ClassificationTree {

	private LinkedBinaryTree<Datum> tree;

	/**
	 * Constructs a new Animal tree class which manages an underlying animal
	 * tree
	 */
	public ClassificationTree() {
		tree = new LinkedBinaryTree<>();
		load();
	}

	/**
	 * Main method which controls the identification and tree management loop.
	 */
	public void identify() {
		askQuestions(tree.root(), "");
	}

	private void askQuestions(Node<Datum> currNode, String input) {
		Scanner asker = new Scanner(System.in);

		if (input.equals("")) {
			System.out.println("Is this animal " + tree.root().getElement() + "? (Y/N) > "); //input
			input = asker.next().toUpperCase();
		}

		else if (input.equals("Y")) {
			if (tree.left(currNode) == null) {
				askQuestions(currNode, "Y");
			}
			else {
				askQuestions(tree.left(currNode), "N");
			}
		}

		else if (input.equals("N")) {
			System.out.println("I don't know any " + notAnimalString(tree.root()) +
					" animals that aren't " + tree.root().getElement());
			System.out.println("What is the new animal? > "); //input

			System.out.println("What characteristic does " + "NEW" + " have that " +
					tree.root().getElement() + "does not? > "); //input
		}
	}

	/**
	 * Get the string of the parent tree, for when an animal is not known
	 * @param currNode The current node that the identify does not know
	 * @return String of all of the parent animal descriptors
	 */
	private String notAnimalString(Node<Datum> currNode) {
		String retString = "";
		for (int i = 0; i < tree.depth(currNode); i++) {
			if (i != 0) {
				retString = ", " + retString;
			}
			retString = currNode.getElement() + retString;
		}
		return retString;
	}

	/**
	 * Saves a tree to a file.
	 */
	public void save() {
		TreeTraversal<Datum> trav = new InOrderTraversal<>(tree);
        trav.setCommand(new EnumerationCommand());
        trav.traverse();

		trav = new BreadthFirstTraversal<>(tree);
        String filename = "DELETE.txt";

//		trav.setCommand(new EnumerationFilesWriteCommand(filename));
//		trav.traverse();

		try {
			File fileOut = new File(filename);
			FileWriter fileWrite = new FileWriter(fileOut, false);
			PrintWriter writer = new PrintWriter(fileWrite);
			trav.setCommand(new EnumeratedSaveCommand(writer));
			trav.traverse();
			writer.close();
		}
		catch (IOException ex) {
			System.out.println(ex.toString());
		}
	}

	/**
	 * Loads a tree from the given file, if an exception occurs during file
	 * operations, a hardcoded basic tree will be loaded instead.
	 */
	public void load() {
//		Scanner asker = new Scanner(System.in);
//		System.out.println("Filename of Tree to open: ");
//		String input = asker.nextLine();
		String input = "test.txt";

		if (Files.exists(Paths.get(input))) {
			List<String> treeLines = new LinkedList<>();

			try {
				treeLines = Files.readAllLines(Paths.get(input));
			}
			catch (IOException ex) {
				System.out.println(ex.toString());
			}

			parsedTree(treeLines);
		}
		else {
		    hardcodedParse();
        }

//		int DEBUG = 0;
	}

	/**
	 * Parses based on a breadth first traversal tree file
	 * @param treeLines List of string lines from a tree file
	 */
	private void parsedTree(List<String> treeLines) {
		LinkedList<String> indxList = new LinkedList<>();
		LinkedList<Node<Datum>> nodeList = new LinkedList<>();

		for (String line : treeLines) {
			String[] parsed = line.split(":");
			Datum temp = new Datum(parsed[3]);

			while (!indxList.isEmpty() && !parsed[0].equals(indxList.peek())) {
				indxList.poll();
				nodeList.poll();
			}

			if (indxList.isEmpty() || !indxList.peek().equals(parsed[1])) {
				indxList.offer(parsed[1]);
			}

			if (tree.isEmpty()) {
				nodeList.offer(tree.setRoot(temp));
			}
			else {
				if (parsed[2].equals("l")) {
					nodeList.offer(tree.addLeft(nodeList.peek(), temp));
				} else {
					nodeList.offer(tree.addRight(nodeList.peek(), temp));
				}
			}
		}
	}

	/**
	 * Uses a copy and pasted version of a tree txt file, and
	 * removes any of the leaf animal nodes that start as "a ".
	 * This leaves just the descriptive nodes for the logic, but
	 * no animals predefined in the tree.
	 */
	private void hardcodedParse() {
		String rawText =
				"-1:7:r:furry\n" +
				"7:1:l:squeaky\n" +
				"7:13:r:aquatic\n" +
				"1:0:l:a mouse\n" +
				"1:5:r:bipedal\n" +
				"13:11:l:legged\n" +
				"13:14:r:a snake\n" +
				"5:3:l:reclusive\n" +
				"5:6:r:a dog\n" +
				"11:9:l:shelled\n" +
				"11:12:r:a fish\n" +
				"3:2:l:a bigfoot\n" +
				"3:4:r:a human\n" +
				"9:8:l:a crab\n" +
				"9:10:r:a salamander\n";
		String[] treeList = rawText.split("\n");
		List<String> parsedList = new LinkedList<>();

		for (String node : treeList) {
			if (node.split(":")[3].subSequence(0,2) != "a ") {
				parsedList.add(node);
			}
		}

		parsedTree(parsedList);
	}
}
