import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Stack;

/**
 * Path is used to track each path that is being explored
 * @author Jon Steele 
 * @email jonwhsteele@gmail.com
 *
 */
public class Path{
	
	int[][] cost_matrix;
	
	//Each path has 3 important variables:
	
	String path = ""; //String form of path
	Stack<Node> visited; //Stack containing each node traveled to
	int totalCost; //The cost of the path up until its last node
	
	/**
	 * Constructor for creating a path from a starting node
	 * @param startingNode
	 */
	public Path(Node startingNode, int[][] cost_matrix) { 
		this.path = startingNode.letter;
		this.visited = new Stack<Node>();
		this.visited.push(startingNode);
		this.totalCost = 0;
		this.cost_matrix = cost_matrix;
	}
	
	/**
	 * Constructor for creating a path using each individual variable
	 * @param parentPath
	 * @param parentCost
	 * @param path
	 * @param cost_matrix 
	 */
	public Path(Stack<Node> parentPath, int parentCost, String path, int[][] cost_matrix) {
		this.visited = (Stack<Node>) parentPath.clone();
		this.totalCost = parentCost;
		this.path = path;
		this.cost_matrix = cost_matrix;
	}
	
	/**
	 * Constructor for cloning a path instance
	 * @param path
	 */
	public Path(Path path) {
		this.visited = (Stack<Node>) path.visited.clone();
		this.totalCost = path.totalCost;
		this.path = path.path;
		this.cost_matrix = path.cost_matrix;
	}
	
	/**
	 * Add is used to add a node to the path. childIndex refers to one of the children nodes of this path
	 * @param childIndex
	 */
	public void add(int childIndex) {
		Node child = visited.peek().children.elementAt(childIndex); //getting the child, based on childIndex
		this.totalCost = this.totalCost + cost(this.visited.peek().letter, child.letter); //Updating totalCost, now with the child Node's path cost
		this.visited.push(child); //One mark for writing the code that updates the visited node list correctly.
		path = path + " --> " + child.letter; //Updating the path string
	}
	
	/**
	 * This method converts a node's letter into a number. The converted number is later used to access cost values inside of the cost array.
	 * @param l
	 * @return returns an integer value of this Letter
	 */
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
	
	/**
	 * Cost function. Input two letters, and it will return the cost of travel between the two. If 0, the path doesn't exist.
	 * @param start -- start node
	 * @param end -- end node
	 * @return
	 * the cost of travel between the two nodes
	 */
	public int cost(String start, String end) {
		
		int s = letterToNumber(start);
		int e = letterToNumber(end);
				
		return cost_matrix[e][s];
	}

	/**
	 * This function returns the heuristic + total cost of this path.
	 * f(n) = g(n) + h(n)
	 * @param path
	 * @return
	 */
	public int costFunction(Path path) {
		return (path.totalCost + path.visited.peek().h);
	}
	
	/**
	 * Used to print this path for user viewing
	 */
	@Override
	public String toString() {
		return path;
	}
}