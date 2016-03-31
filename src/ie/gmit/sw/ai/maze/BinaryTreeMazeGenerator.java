package ie.gmit.sw.ai.maze;

import ie.gmit.sw.ai.maze.Node;

public class BinaryTreeMazeGenerator implements MazeGenerator 
{
	// http://weblog.jamisbuck.org/2011/2/1/maze-generation-binary-tree-algorithm
	// the simplest of the maze generation algorithms to implement,
	// It can build the entire maze by looking at only a single cell at a time,
	// fastest to run,
	// It is novel in that it can operate without any state at all,
	// needs to look at the current cell
	// it can be used to generate mazes of infinite size.

		private Node [][] maze;
		public BinaryTreeMazeGenerator() 
		{

		}

		public void buildTheMaze(int rows, int cols)
		{
			maze = new Node[rows][cols];
			init();
			
			int featureNumber = (int)((rows * cols) * 0.01);	
			addFeature('W', 'X', featureNumber);
			addFeature('?', 'X', featureNumber);
			addFeature('B', 'X', featureNumber);
			addFeature('H', 'X', featureNumber);
			
			for (int row = 1; row < maze.length -1 ; row ++)
			{
				for (int col = 1; col < maze[row].length -1; col+= 2)
				{
					int num = (int) (Math.random() * 10);
					
					if (col > 0 && (num >= 5))
					{
						maze[row][col].addPath(Node.Direction.West);
						maze[row + 1][col].setNodeTypes(' ');
						//maze[row - 1][col].setNodeType(' ');
					}
					else
					{
						maze[row][col].addPath(Node.Direction.North);
						maze[row][col].setNodeTypes(' ');
						maze[row][col + 1].setNodeTypes(' ');
						maze[row][col - 1].setNodeTypes(' ');
					}
				}
			}
		}
		
		private void init()
		{
			for (int row = 0; row < maze.length; row++)
			{
				for (int col = 0; col < maze[row].length; col++)
				{
					maze[row][col] = new Node(row, col);
					maze[row][col].setNodeTypes('X');
				}
			}
		}

		private void addFeature(char feature, char replace, int number)
		{
			int counter = 0;
			while (counter < number)
			{
				int row = (int)(maze.length * Math.random());
				int col = (int) (maze[0].length * Math.random());
				
				if (maze[row][col].getNodeTypes() != ' ')
				{
					maze[row][col].setNodeTypes(feature);
					counter++;
				}
			}
		}
		public Node[][] getTheMaze()
		{
			return this.maze;
		}

		
	
}
