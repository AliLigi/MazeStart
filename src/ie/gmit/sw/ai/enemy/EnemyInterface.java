package ie.gmit.sw.ai.enemy;

import ie.gmit.sw.ai.maze.Node;

public interface EnemyInterface {
	// created an interface that creates the traverser
	//then updating the goal node
	public void traverser(Node[][] maze, Node start);
	public void updatingGoalNode(Node goal);
}
