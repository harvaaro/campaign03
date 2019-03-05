package edu.isu.cs.cs3308;

import edu.isu.cs.cs3308.structures.BinaryTree;
import edu.isu.cs.cs3308.structures.Node;
import edu.isu.cs.cs3308.structures.impl.BinarySearchTree;
import edu.isu.cs.cs3308.structures.impl.LinkedBinaryTree;
import edu.isu.cs.cs3308.structures.impl.LinkedBinaryTree.BinaryTreeNode;
import edu.isu.cs.cs3308.structures.impl.LinkedQueue;
import edu.isu.cs.cs3308.traversals.BreadthFirstTraversal;
import edu.isu.cs.cs3308.traversals.InOrderTraversal;
import edu.isu.cs.cs3308.traversals.PreOrderTraversal;
import edu.isu.cs.cs3308.traversals.TreeTraversal;
import edu.isu.cs.cs3308.traversals.commands.EnumeratedSaveCommand;
import edu.isu.cs.cs3308.traversals.commands.EnumerationCommand;
import edu.isu.cs.cs3308.traversals.commands.EnumerationFilesWriteCommand;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
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
		//TODO
		Scanner asker = new Scanner(System.in);
		System.out.println("Hello there type your name: ");
		String input = asker.nextLine();
		System.out.println("Your name is: " + input);
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
			PrintWriter writer = new PrintWriter(fileOut);
			trav.setCommand(new EnumeratedSaveCommand(writer));
			trav.traverse();
			writer.close();
		}
		catch (FileNotFoundException ex) {
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
		String input = "t";

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

    /**
     * Hard-coded tree matching the test.txt provided
     */
	private void hardcodedTree() {
        Datum d1r = new Datum("furry");
        Node<Datum> n1r = tree.setRoot(d1r);

        Datum d2al = new Datum("squeaky");
        Node<Datum> n2al = tree.addLeft(n1r,d2al);
        Datum d2ar = new Datum("aquatic");
        Node<Datum> n2ar = tree.addRight(n1r,d2ar);

//        Datum d3al = new Datum("a mouse");
//        Node<Datum> n3al = tree.addLeft(n2al,d3al);
        Datum d3ar = new Datum("bipedal");
        Node<Datum> n3ar = tree.addRight(n2al,d3ar);
        Datum d3bl = new Datum("legged");
        Node<Datum> n3bl = tree.addLeft(n2ar,d3bl);
//        Datum d3br = new Datum("a snake");
//        Node<Datum> n3br = tree.addRight(n2ar,d3br);

        Datum d4al = new Datum("reclusive");
        Node<Datum> n4al = tree.addLeft(n3ar,d4al);
//        Datum d4ar = new Datum("a dog");
//        Node<Datum> n4ar = tree.addRight(n3ar,d4ar);
        Datum d4bl = new Datum("shelled");
        Node<Datum> n4bl = tree.addLeft(n3bl,d3bl);
//        Datum d4br = new Datum("a fish");
//        Node<Datum> n4br = tree.addRight(n3bl,d3br);

//        Datum d5al = new Datum("a bigfoot");
//        Node<Datum> n5al = tree.addLeft(n4al,d5al);
//        Datum d5ar = new Datum("a human");
//        Node<Datum> n5ar = tree.addRight(n4al,d5ar);
//        Datum d5bl = new Datum("a crab");
//        Node<Datum> n5bl = tree.addLeft(n4bl,d5bl);
//        Datum d5br = new Datum("a salamander");
//        Node<Datum> n5br = tree.addRight(n4bl,d5br);
    }
}
