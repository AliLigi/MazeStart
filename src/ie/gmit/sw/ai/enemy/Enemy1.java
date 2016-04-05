package ie.gmit.sw.ai.enemy;

import java.util.ArrayList;
import java.util.List;

import ie.gmit.sw.ai.maze.Node;

public class Enemy1 extends ASTAR {

	//https://en.wikipedia.org/wiki/A*_search_algorithm
    //private Node goal;
	//private List<Node> closed = new ArrayList<Node>();
	private List<Node> finalList; //= new ArrayList<Node>();
	private List<Node> newList = new ArrayList<Node>();
	private Node finalNode = new Node(0, 0);
	//private PriorityQueue<Node> open = new PriorityQueue<Node>(20, (Node current, Node next)-> (current.getPathCost() + current.getHeuristic(goal)) - (next.getPathCost() + next.getHeuristic(goal)));
	
	public Enemy1(Node goal)
	{
		super(goal);
	}
	public void updatingGoalNode(Node goal)
	{
		super.updatingGoalNode(goal);
	}
	public void traverser(Node[][] maze, Node node)
	{
		super.traverser(maze, node);
		finalList = super.returnList();
		go();
	}
	public void go()
	{
		finalNode = null;
		Node someOldNodes;
		Node currentNodes = finalList.get(finalList.size()-1);
		while(currentNodes != null)
		{
			newList.add(currentNodes);
			currentNodes = currentNodes.getParent();
		}
		for(int i = newList.size() -1 ; i >= 1 ; i--)
		{
			currentNodes = newList.get(i);
			if(currentNodes != null)
			{
				if(currentNodes.getNodeTypes() == 'E' || currentNodes.getNodeTypes() == 'F')
				{
					if(currentNodes.getNodeTypes() == 'E')
					{
						currentNodes.setHasThePlayer(true);
					}
					finalNode = currentNodes;
					break;
				}
				currentNodes.setNodeTypes('V');
			}

			try 
			{ 
				//Simulate processing each expanded node
				Thread.sleep(500);
			}
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			someOldNodes = currentNodes;
			if(someOldNodes.getNodeTypes() != 'X' 
					&& someOldNodes.getNodeTypes() != 'G' 
					&& someOldNodes.getNodeTypes() != 'C' 
					&& someOldNodes.getNodeTypes() != 'F')
			{
				someOldNodes.setNodeTypes(' ');
			}
		}
		super.clearEverything();
		newList.clear();
	}
	public Node returnFinalNode()
	{
		return finalNode;
	}



}
