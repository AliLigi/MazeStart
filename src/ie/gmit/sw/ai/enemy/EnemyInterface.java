package ie.gmit.sw.ai.enemy;

import ie.gmit.sw.ai.maze.Node;

public interface EnemyInterface {

	public void traverser(Node[][] maze, Node start);
	public void updatingGoalNode(Node goal);
}
