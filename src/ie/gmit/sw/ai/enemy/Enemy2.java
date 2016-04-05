package ie.gmit.sw.ai.enemy;

import ie.gmit.sw.ai.maze.Node;
import java.util.*;

public class Enemy2 implements EnemyInterface {

	private Node[][] maze;
	private Node goal;
	private boolean stillRun = true;
	private List<Node> lastList = new ArrayList<Node>();
	private List<Node> newTypeList = new ArrayList<Node>();
	private Node lastNode = new Node(0, 0);


	private void dfsearch(Node node, int depth, int limit)
	{
		if (!stillRun || depth > limit) 
		{
			return;		
		}
		node.setVisited(true);	
		
		if (node == goal)
		{
			System.out.println("FOUND THE GOAL YOU WERE LOOKING FOR");
			goal = node;
	        stillRun = false;
			return;
		}
		
		ArrayList<Node> children = node.adjacentNodes(maze);
		
		for (Node child : children) 
		{
			//System.out.println(child.toString() + " :::: " + child.getNodeType());
			if(child.getNodeTypes() == ' '  ||  
					child.getNodeTypes() == 'G' || 
					child.getNodeTypes() == 'V'  ||  
					child.getNodeTypes() == 'E')
			{
				//System.out.println("VALID");
				if (child != null && !child.isVisited())
				{
					child.setParent(node);
					lastList.add(child);
					dfsearch(child, depth + 1, limit);
				}
			}
		}
	} 
	public void traverser(Node[][] maze, Node start) 
	{
	
		this.maze = maze;
		int limit = 2;
		
		while(stillRun)
		{
			dfsearch(start, 0, limit);
			
			if (stillRun)
			{
	      		limit++;       		
	      		unvisit();			
			}
      	}
		
		go();
	}
	
	public void go()
	{
		lastNode.setHasThePlayer(false);
		Node someOldNodes;

		Node currentNodes = goal;
		while(currentNodes != null)
		{
		
			newTypeList.add(currentNodes);
			currentNodes = currentNodes.getParent();
		}
		for(int i = newTypeList.size() -1 ; i >= 0 ; i--)
		{
			
			currentNodes = newTypeList.get(i);
			if(currentNodes != null)
			{
				
				if(currentNodes.getNodeTypes() == 'E' || currentNodes.getNodeTypes() == 'F')
				{
					if(currentNodes.getNodeTypes() == 'E')
					{
						currentNodes.setHasThePlayer(true);
					}
					lastNode = currentNodes;
					break;
				}
				currentNodes.setNodeTypes('V');
			}

			try 
			{ 
				//Simulate each expanded node
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
		
				unvisit();
	}
		
	private void unvisit()
	{
		newTypeList.clear();
		lastList.clear();
		for (int i = 0; i < maze.length; i++)
		{
			for (int j = 0; j < maze[i].length; j++)
			{
				maze[i][j].setVisited(false);
				maze[i][j].setParent(null);
				
			}
		}
	}
	public Node returnFinalNode()
	{
		return lastNode;	
	}

	public void updatingGoalNode(Node goal)
	{
		this.goal = goal;
		
	}




}
