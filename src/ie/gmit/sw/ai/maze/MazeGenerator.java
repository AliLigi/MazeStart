package ie.gmit.sw.ai.maze;

public interface MazeGenerator {

	public Node[][] getTheMaze();
	public void buildTheMaze(int rows , int cols);
	public Node getingNode();
}
