package ie.gmit.sw.ai.enemy;

//import java.util.*;
import ie.gmit.sw.ai.maze.Node;
import ie.gmit.sw.ai.player.Player;
;

public class Enemy  {
	
		public enum SearchTypeOfEnemy {ASTARALGORITHM, DFSALGORITHM};
		private boolean isLiving = true;
		private Player player;
		private SearchTypeOfEnemy searching;
		private Node currentPosition;
		private Node goalNode;
		private Node[][] maze;
		private EnemyInterface theHunter = null;
		public Enemy(Player player, SearchTypeOfEnemy search, Node startNode, Node[][] maze)
		{
			setPlayer(player);
			goalNode = player.getCurrentNode();
			setSearchType(search);
			setCurrentPosition(startNode);
			this.maze = maze;
		}
		
		public Node getCurrentPosition() {
			return currentPosition;
		}
		public void setCurrentPosition(Node currentPos) {
			this.currentPosition = currentPos;
		}
		public boolean isLiving() 
		{
			return isLiving;
		}
		public void setLife(boolean isLiving) 
		{
			this.isLiving = isLiving;
		}
		public Player getPlayer() 
		{
			return player;
		}
		public void setPlayer(Player playerPosition) 
		{
			this.player = playerPosition;
		}
		public SearchTypeOfEnemy getSearchType() 
		{
			return searching;
		}
		public void setSearchType(SearchTypeOfEnemy searchType) 
		{
			this.searching = searchType;
		}
		public void initTheHunter()
		{
			if(searching == SearchTypeOfEnemy.ASTARALGORITHM)
			{
				theHunter = new Enemy1(player.getCurrentNode());
			}
			else if(searching == SearchTypeOfEnemy.DFSALGORITHM)
			{
				theHunter = new Enemy2();
			}
			hunt();
		}
		public void hunt()
		{
			theHunter.traverser(maze, currentPosition);
			if(goalNode.getNodeTypes() == 'E')
			{
				updatePlayerPosition();
				//hunt();
			}
			System.out.println("HUNTING FINISHED");
		}
		public void updatePlayerPosition()
		{
			theHunter.updatingGoalNode(player.getCurrentNode());
		}
	}


	
/* 
	This is another way i tried to do the enemy 
	private Player player;
	private SearchType search;
	private Node goalNode;
	//private Node goal;
	EnemyInterface hunt = null;
	private List<Node> closing = new ArrayList<Node>();
	private List<Node> theFinalList = new ArrayList<Node>();
	private PriorityQueue<Node> opening = new PriorityQueue<Node>(20, (Node current, Node next)-> (current.getPathCost() + current.getHeuristic(goal)) - (next.getPathCost() + next.getHeuristic(goalNode)));
	
	public Enemy(Player player, SearchType search, Node goalNode, Node[][] model)
	{
		this.goalNode = goalNode;
	}
	
	public void initHunter()
	{
		if(search == SearchType.ENEMY)
		{
			hunt = new Enemy(player.getCurrentNode());
		}
		else if(search == SearchType.ENEMY2)
		{
			hunt = new Enemy2();
		}
		hunt();
	}
	public void hunt()
	{
		hunt.traverse(maze, currentPos);
		if(goalNode.getNodeTypes() == 'E')
		{
			updatePlayerPos();
			hunt();
		}
		System.out.println("HUNTING OVER");
	}
	public void traverser(Node[][] maze, Node node)
	{
        long time = System.currentTimeMillis();
        
		
    	   	
		opening.offer(node);
		node.setPathCost(0);			
		while(!opening.isEmpty())
		{
			//System.out.println("LOOKING");
			node = opening.poll();		
			closing.add(node);
			node.setVisited(true);	
			//System.out.println(node.toString());
			
			if (node.isGoalNode())
			{
				System.out.println(goalNode);
				System.out.println("goalNode founds");
		        time = System.currentTimeMillis() - time; //Stop the clock
				break;
			}
			//Process adjacent nodes
			ArrayList<Node> children = node.adjacentNodes(maze);
			for (Node child : children) 
			{
				//System.out.println(child.toString() + " :::: " + child.getNodeType());
				if(child.getNodeTypes() != 'X' && child.getNodeTypes() != 'W' && child.getNodeTypes() != 'B' && child.getNodeTypes() != 'H'&& child.getNodeTypes() != '?')//(child.getNodeType() == ' '  ||  child.getNodeType() == 'G' ||  child.getNodeType() == 'V' ||  child.getNodeType() == 'E')
				{
					int score = node.getPathCost() + 1 + child.getHeuristic(goalNode);
					int existing = child.getPathCost() + child.getHeuristic(goalNode);
					if ((opening.contains(child) || closing.contains(child)) && existing < score)
					{
						continue;
					}
					else
					{
						opening.remove(child);
						closing.remove(child);
						child.setParent(node);
						theFinalList.add(child);
						child.setPathCost(node.getPathCost() + 1);
						opening.add(child);
					}
				}
			}	
		}
		
		move();
	}
	
	public void move()
	{
		List<Node> newList = new ArrayList<Node>();
		System.out.println("Move Faster");
		Node oldNode;
		Node curNode = theFinalList.get(theFinalList.size()-1);
		while(curNode != null)
		{
			newList.add(curNode);
			curNode = curNode.getParent();
		}
		for(int i = newList.size() -1 ; i >= 1 ; i--)
		{
			curNode = newList.get(i);
			if(curNode != null)
			{
				curNode.setNodeTypes('V');
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
			oldNode = curNode;
			if(oldNode.getNodeTypes() != 'X')
			{
				oldNode.setNodeTypes(' ');
			}
		}
	}

	public double getStrength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateGoalNode(Node goal) {
		// TODO Auto-generated method stub
		
	}
}*/
