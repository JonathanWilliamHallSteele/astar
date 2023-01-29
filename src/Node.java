import java.util.Vector;

/**
 * Nodes are used to create a linked tree, which is used for search algorithms.
 * Each node has a letter, a heuristic (h), and up to 3 children
 * @author Jon Steele
 * @email jonwhsteele@gmail.com
 *
 */
public class Node{

	String letter; //Letter value of this node
	int number; //Number value of this Node
	int numberOfChildren; //Number of children
	int h; //Heuristic Value
	Vector<Node> children;

	/**
	 * Constructor, used to instantiate a Node. Uses a letter value, its heuristic, and its number of children.
	 * @param value
	 * @param heuristic_vector
	 * @param numberOfChildren
	 */
	public Node(int value, int[] heuristic_vector) {

		letter = numberToLetter(value);
		number = value;
		h = heuristic_vector[value];
		children = new Vector<Node>();

	}
	
	static private int letterToNumber(String l) {

		switch(l) {
			case "A":
				return 0;
			case "B":
				return 1;
			case "C":
				return 2;
			case "D":
				return 3;
			case "E":
				return 4;
			case "H":
				return 5;
			case "J":
				return 6;
			case "G1":
				return 7;
			case "G2":
				return 8;
			case "G3":
				return 9;
			default: 
				return 10;
				}
	}
		
		static private String numberToLetter(int l) {

			switch(l) {
				case 0:
					return "A";
				case 1:
					return "B";
				case 2:
					return "C";
				case 3:
					return "D";
				case 4:
					return "E";
				case 5:
					return "H";
				case 6:
					return "J";
				case 7:
					return "G1";
				case 8:
					return "G2";
				case 9:
					return "G3";
				default: 
					return null;
			
			}
		}
}
