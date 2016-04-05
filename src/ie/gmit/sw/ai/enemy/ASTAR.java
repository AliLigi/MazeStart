package ie.gmit.sw.ai.enemy;

import java.util.ArrayList;
import java.util.PriorityQueue;

import ie.gmit.sw.ai.maze.Node;
//import java.util.ArrayList;
import java.util.List;
//import java.util.PriorityQueue;


public class ASTAR implements EnemyInterface {

	//implementing the interface in the Astar
	private Node goal;
	private List<Node> closed = new ArrayList<Node>();
	private List<Node> finalList = new ArrayList<Node>();
	private List<Node> allTheNodes = new ArrayList<Node>();
	private PriorityQueue<Node> open = new PriorityQueue<Node>(20, (Node current, Node next)-> 
	(current.getPathCost() + current.getHeuristic(goal)) - (next.getPathCost() + next.getHeuristic(goal)));
	
	public ASTAR(Node goal)
	{
		updatingGoalNode(goal);
	}
	public void traverser(Node[][] maze, Node node)
	{	
        maze = maze.clone();
		open.offer(node);
		node.setPathCost(0);			
		while(!open.isEmpty())
		{
		
			node = open.poll();		
			closed.add(node);
			node.setVisited(true);	
			
			
			if (node.equals(goal))
			{
				break;
			}
			//Process adjacent nodes
			ArrayList<Node> children = node.adjacentNodes(maze);
			for (Node child : children) 
			{
				allTheNodes.add(child);
				if(child.getNodeTypes() != 'X' 
						&& child.getNodeTypes() != 'W' 
						&& child.getNodeTypes() != 'B' 
						&& child.getNodeTypes() != 'H'
						&& child.getNodeTypes() != '?')
				{
					int score = node.getPathCost() + 1 + child.getHeuristic(goal);
					int existing = child.getPathCost() + child.getHeuristic(goal);
					if ((open.contains(child) || closed.contains(child)) && existing < score)
					{
						continue;
					}
					else
					{
						open.remove(child);
						closed.remove(child);
						child.setParent(node);
						finalList.add(child);
						child.setPathCost(node.getPathCost() + 1);
						open.add(child);
					}
				}		
			}
		}
	}
	public void clearEverything()
	{
		for(Node n : allTheNodes)
		{
			n.setParent(null);
			n.setVisited(false);
		}
		closed.clear();
		finalList.clear();
		open.clear();
	}
	public List<Node> returnList()
	{
		return finalList;
	}
	/*@Override
	public Node returnFinalNode() {
		// TODO Auto-generated method stub
		return null;
	}*/
	
	@Override
	public void updatingGoalNode(Node goal) {
		// TODO Auto-generated method stub
		this.goal = goal;
	}


}
