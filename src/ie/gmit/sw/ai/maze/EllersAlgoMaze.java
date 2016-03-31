package ie.gmit.sw.ai.maze;

import java.util.*;
public class EllersAlgoMaze implements MazeGenerator{

	// http://weblog.jamisbuck.org/2010/12/29/maze-generation-eller-s-algorithm
	// http://www.neocomputer.org/projects/eller.html
	// Eller's algorithm for maze generation
	// requires memory proportional to the size of a single row
	// you can generate "bottomless" mazes with it
	// Eller's algorithm creates 'perfect' mazes, having only a single path between any two cells, one row at a time
	// here is a simple algo 
	
		private Node[][] maze;
		private Random randNumber = new Random();
		private Set<Set<Node>> tempSets = new HashSet<Set<Node>>();
		
		public Node[][] getTheMaze() 
		{
			return this.maze;
		}
		private void init()
		{
			for (int row = 0; row < maze.length; row ++)
			{
				for (int col = 0; col < maze[row].length; col++)
				{
					maze[row][col] = new Node(row, col);
					maze[row][col].setNodeTypes('X');
				}
			}
		}
		public void buildTheMaze(int rows, int cols)
		{
			maze = new Node[rows][cols];
			
			init();
			
			for (int row = 1; row < maze.length - 1; row ++)
			{
				for (int col = 2; col < maze[row].length - 2; col++)
				{
					//all columns in 1st row & 1st set
					if(row == 1)
					{
						maze[row][col].NewSet();
						maze[row][col].addNodeToSets(maze[row][col]);
					}
					//in the set on row above
					else if (row > 1 && onTheSet(maze[row][col]))
					{
						maze[row][col].NewSet();
						maze[row][col].addNodeToSets(maze[row][col]);
					}
					//random add nodes to set if not 
					//already on set
					if ((randNumber.nextInt(2)) == 1)
					{
						if(maze[row][col].getNodeSets() != null)
						{
							if(!(maze[row][col].getNodeSets().contains(maze[row][col + 1])))
							{
								maze[row][col].addNodeToSets(maze[row][col + 1]);
								maze[row][col].addNodeToSets(maze[row][col + 2]);
								maze[row][col + 1].setNodeTypes(' ');
								maze[row][col + 2].setNodeTypes(' ');
							}
						}
					}
					if(maze[row][col].getNodeSets() != null)
					{
						tempSets.add(maze[row][col].getNodeSets());
					}
				}
			}
			doTheVerticals(tempSets);
		}
		private void doTheVerticals(Set<Set<Node>> tempSet)
		{
			for(Set<Node> nodeSet : tempSet)
			{
				int vertCount = 0;
				while(vertCount == 0)
				{
					System.out.println(vertCount);
					for(Node n : nodeSet)
					{
						if(randNumber.nextInt(2) == 1)
						{
							maze[n.getRow() - 1][n.getCol()].setNodeTypes(' ');
							vertCount++;
							break;
						}
					}
				}
			}
		}
		
		private boolean onTheSet(Node n)
		{
			boolean available = true;
			for (int row = 1; row < maze.length - 1; row ++)
			{
				for (int col = 1; col < maze[row].length - 1; col++)
				{
					if(maze[row][col].getNodeSets() != null)
					{
						if(maze[row][col].getNodeSets().contains(n))
						{
							available = false;
						}
					}
				}
			}
			return available;
		}
	}

