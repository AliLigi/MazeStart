package ie.gmit.sw.ai.maze;
import java.util.*;
import ie.gmit.sw.ai.maze.Node;

public class RecursiveBacktrakingAlgo implements MazeGenerator{
	
	  // http://weblog.jamisbuck.org/2010/12/27/maze-generation-recursive-backtracking
	  // https://en.wikipedia.org/wiki/Maze_generation_algorithm
	  // Recursive backtracker algorithm
	  // Unlike Eller's algorithm
	  // This algorithm is a randomized version of the depth-first search algorithm
	  // A maze can be generated by starting with a predetermined arrangement of cells (most commonly a rectangular grid but other arrangements are possible) with wall sites between them.
	  // I didn't get this algorithm working properly yet if i have  got time i will return at the end
	
		private Random r = new Random();
		private Node[][] maze;
		private Set<Node> startCell = new HashSet<Node>();
		private Set<Node> unvisitNode = new HashSet<Node>();
		
		
		public RecursiveBacktrakingAlgo(Node[][] maze) {
			super();
			this.maze = maze;
			//buildTheMaze();
		}
		
		// Initializing each Node
		// Starting  maze in grid pattern
		private void init(){
			int rowCounter = 0; int colCounter =0;
			int row = 0; int col = 0;
			for (row = 0; row < maze.length; row++){
				rowCounter++;
				for (col = 0; col < maze[row].length; col++){
					maze[row][col] = new Node(col, row);
					maze[row][col].setRow(row); maze[row][col].setCol(col);
					colCounter++;
					if(row < 1 || col < 1 
							|| row > maze.length-1 
							|| col > maze[0].length-1) maze[row][col].setNodeType(NodeTp.wall);
					else if(colCounter % 2 == 0 && rowCounter % 2 == 0)  {
						maze[row][col].setNodeType(NodeTp.floor);
						maze[row][col].setStartingCell(true);
						startCell.add(maze[row][col]);
						colCounter = 0;
					} else {
						maze[row][col].setNodeType(NodeTp.wall);
					}
				}
			}
		}
		
		public void buildTheMaze(int rows, int cols) {
			init();
			unvisitNode = startCell;
			Stack<Node> nodeStack = new Stack<Node>();
			List<Node> unvisitedNeighbours = new ArrayList<Node>();
			int randIndex = r.nextInt(startCell.size());
			int i = 0;
			// Initial Node
			Node currentNode;
			Node startingNode = new Node(cols, rows);
			
			for(Node cell : startCell) {
				if (i == randIndex) startingNode = cell;
				else i++;
			}
			currentNode = startingNode;
			do {
				currentNode.setVisited(true);
				
				List<Node> adjacentNodes = currentNode.getAdjacentCells(maze);
				// Get list of unvisited cell
				for (Node node : adjacentNodes) {
					if(!node.isVisited()) {
						unvisitedNeighbours.add(node);
					}
				}
				if (unvisitedNeighbours.size() > 0) {
					int nextCellIndex = r.nextInt(unvisitedNeighbours.size());
					Node nextNode = unvisitedNeighbours.get(nextCellIndex);
					nodeStack.push(currentNode);
					
					Node wallNode = getWall(currentNode, nextNode);
					wallNode.setNodeType(NodeTp.floor);
					unvisitNode.remove(currentNode);
					currentNode = nextNode;
					unvisitedNeighbours.clear();
				} else if (!nodeStack.isEmpty()) {
					currentNode = nodeStack.pop();
				}
			}
			while (currentNode != startingNode && !nodeStack.isEmpty());
		}
		
		private Node getWall(Node n1, Node n2) {
			Node wall;
			
			if (n1.getRow() == n2.getRow()) {
				if (n1.getCol() < n2.getCol())
					wall = maze[n2.getRow()][n2.getCol()-1];
				else 
					wall = maze[n2.getRow()][n2.getCol()+1];
				
			} else {
				if (n1.getRow() < n2.getRow()) 
					wall = maze[n2.getRow()-1][n2.getCol()];
				else
					wall = maze[n2.getRow()+1][n2.getCol()];
			}
			return wall;
		}

		public Node[][] getTheMaze() {
			// TODO Auto-generated method stub
			return null;
		}

	
	 }

