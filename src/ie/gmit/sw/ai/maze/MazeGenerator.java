package ie.gmit.sw.ai.maze;

public interface MazeGenerator {

	//an interface needed to build the maze
	public Node[][] getTheMaze();
	public void buildTheMaze(int rows , int cols);
	public Node getingNode();
}
