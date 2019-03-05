package edu.isu.cs.cs3308;

import java.util.Scanner;

/**
 * Driver for the classification program
 *
 * @author Isaac Griffith
 */
public class Driver {

    /**
     * Runs the program
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ClassificationTree tree = new ClassificationTree();
        //FIXME: set it to N for now to test the save
        String more = "N";
        while (more.equals("Y")) {
            System.out.println("Do you have another animal to identify? (Y/N) > ");
            Scanner in = new Scanner(System.in);
            more = in.next().toUpperCase();

            if (more.equals("Y")) {
                tree.identify();
            }
        }
        tree.save();
    }
}
