import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.Vector;

public class main {

	public static void main(String[] args) {
		
		int[][] cost_matrix = {
				{0,0,0,6,1,0,0,0,0,0},
				{5,0,2,0,0,0,0,0,0,0},
				{9,3,0,0,0,0,0,0,0,0},
				{0,0,1,0,2,0,0,0,0,0},
				{6,0,0,0,0,2,0,0,0,0},
				{0,0,0,7,0,0,0,0,0,0},
				{0,0,0,0,2,0,0,0,0,0},
				{0,9,0,0,0,0,0,0,0,0},
				{0,0,0,5,0,0,0,0,0,0},
				{0,0,0,0,0,8,7,0,0,0}
		};
		
		int[] heuristic_vector = {5,7,3,4,6,8,5,0,0,0}; //A,B,C,D,E,H,J,G1,G2,G3
		
		Vector<Integer> goal = new Vector<Integer>(); //Holds all the integer values of goal nodes
		Vector<Node> nodes = new Vector<Node>(); //Holds all the nodes
		
		//Instantiating all of the nodes
		int children = 0;
		for (int l = 0; l < heuristic_vector.length; l++) {
			nodes.add(new Node(l, heuristic_vector));
			children = 0;
		}
		
		//Linking all the nodes together
		for (int l = 0; l < heuristic_vector.length; l++) {//Heuristic vector must be same length as cost matrix
			for (int j = 0; j < heuristic_vector.length; j++) {
				
				if (cost_matrix[j][l] != 0) {
					nodes.elementAt(l).children.addElement(nodes.elementAt(j));
					children++;
				}
			}
			children = 0;
		}
		
		// Identifying the Goal States and saving them in a new vector
		for (int i = 0; i < heuristic_vector.length; i++) {
			if (heuristic_vector[i] == 0) {
				goal.add(i);
			}
		}

		//This priority queue is my "Frontier." It holds Path objects, and sorts them based on the
		//f(n) = g(n) + h(n) function
		PriorityQueue<Path> pq = new PriorityQueue<>(10, new Comparator<Path>() {

			/**
			 * I override the Comparator's compare method, so my priority queue can sort itself
			 * based on path length, and heuristic. 
			 */
			//Five Marks for writing the core of the A* search algorithm correctly and calculating the heuristics function.
			@Override
			public int compare(Path o1, Path o2) {
				int o1Weight = (o1.costFunction(o1)); // g(n) + h(n)
				int o2Weight = (o2.costFunction(o2));
				
				return o1Weight - o2Weight;
			}
		});
	
		//Instantiating two paths, a parent path for each loop, and a current (child) path
		Path currPath = new Path(nodes.elementAt(0), cost_matrix);	
		Path parentPath = new Path(nodes.elementAt(0), cost_matrix);
		
		pq.add(parentPath);
		
		//Setting up loop conditions
		boolean goalReached = false;
		int cycles = 0;
		
		//Before the A star algorithm starts, I will see if the current node is a goal node first
		for (int k = 0; k < goal.size(); k++) {
			if (parentPath.visited.peek().number == (goal.elementAt(k))) {
				goalReached = true;
			}
		}
		
		// A Star Search
		//Five Marks for writing the core of the A* search algorithm correctly and calculating the heuristics function.
		while(goalReached == false || cycles > 100){//Assuming the answer will not exceed 100 cycles
			
			//Expand the highest priority path from priority queue (The lowest f(n) path)
			parentPath = new Path(pq.element().visited, pq.element().totalCost, pq.element().path, cost_matrix);
			System.out.println("Expanding Path: " + pq.element().path);
			System.out.println("Cost: " + (pq.element().totalCost + pq.element().visited.peek().h));
			
			pq.remove(); // Removing it from the priority queue.
			
			//here I expand each child node of the parent path, and add them into the priority queue
			//One mark for writing a correct loop condition that checks if there are still open nodes to explore.
			for (int j = 0; j < parentPath.visited.peek().children.size(); j++) {			
				currPath = new Path(parentPath.visited, parentPath.totalCost, parentPath.path, cost_matrix); //Copies path to new path
				currPath.add(j);//One mark for writing the code that updates the visited node list correctly.
				pq.add(currPath); //then adds it to the frontier
			}
			
			cycles++;//One mark for calculating the execution time in cycles and printing out the results.
			
			//Goal Checking
			//If the parent path is equivalent to a goal element, it will end the loop
			//Two marks for writing the code that checks if the current state is a goal state.
			for (int k = 0; k < goal.size(); k++) {
				if (parentPath.visited.peek().number == (goal.elementAt(k))) {
					goalReached = true;
				}
			}
		}
		
		// Print the cheapest path, the goal state and the number of cycles
		if (goalReached == true) {
			System.out.println("\n" + "----------------------------------------");
			System.out.println("Goal Node Reached: " + parentPath.visited.peek().letter);
			System.out.println("Path Taken: " + parentPath.path);
			System.out.println("Cycles: " + cycles);//One mark for calculating the execution time in cycles and printing out the results.
			System.out.println("Cost: " + (parentPath.totalCost + parentPath.visited.peek().h));
		}
		
		else {
			System.out.println("Cycles unexpectedly exceeded 100. Change cycle limit, or reassess problem");
		}	
	}
}
