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
		Scanner asker = new Scanner(System.in);
		System.out.print("Type something: ");
		String input = asker.next();
		System.out.println("You typed: " + input);
	}

	/**
	 * Saves a tree to a file.
	 */
	public void save() {
		BreadthFirstTraversal<Datum> trav = new BreadthFirstTraversal<>(tree);
        trav.setCommand(new EnumerationCommand());
        trav.traverse();
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
		Scanner asker = new Scanner(System.in);
		System.out.print("Type something: ");
		String input = asker.next();

		if (Files.exists(Paths.get(input))) {
		    try {
                Files.readAllLines(Paths.get(input));
            }
		    catch (IOException ex) {
                System.out.println(ex.toString());
            }
		}
		else {
		    hardcodedTree();
        }
	}

    /**
     * Hard-coded tree matching the test.txt provided
     */
	private void hardcodedTree() {
        Datum d0 = new Datum("root");
        tree.setRoot(d0);

        Datum d1r = new Datum("furry");
        Node<Datum> n1r = tree.addRight(tree.root(),d1r);

        Datum d2al = new Datum("squeaky");
        Node<Datum> n2al = tree.addLeft(n1r,d2al);
        Datum d2ar = new Datum("aquatic");
        Node<Datum> n2ar = tree.addRight(n1r,d2ar);

        Datum d3al = new Datum("a mouse");
        Datum d3ar = new Datum("bipedal");
        Node<Datum> n3al = tree.addLeft(n2al,d3al);
        Node<Datum> n3ar = tree.addRight(n2al,d3ar);
        Datum d3bl = new Datum("legged");
        Datum d3br = new Datum("a snake");
        Node<Datum> n3bl = tree.addLeft(n2ar,d3bl);
        Node<Datum> n3br = tree.addRight(n2ar,d3br);

        Datum d4al = new Datum("reclusive");
        Datum d4ar = new Datum("a dog");
        Node<Datum> n4al = tree.addLeft(n3ar,d4al);
        Node<Datum> n4ar = tree.addRight(n3ar,d4ar);
        Datum d4bl = new Datum("shelled");
        Datum d4br = new Datum("a fish");
        Node<Datum> n4bl = tree.addLeft(n3bl,d3bl);
        Node<Datum> n4br = tree.addRight(n3bl,d3br);

        Datum d5al = new Datum("a bigfoot");
        Datum d5ar = new Datum("a human");
        Node<Datum> n5al = tree.addLeft(n4al,d5al);
        Node<Datum> n5ar = tree.addRight(n4al,d5ar);
        Datum d5bl = new Datum("a crab");
        Datum d5br = new Datum("a salamander");
        Node<Datum> n5bl = tree.addLeft(n4bl,d5bl);
        Node<Datum> n5br = tree.addRight(n4bl,d5br);
    }
}
