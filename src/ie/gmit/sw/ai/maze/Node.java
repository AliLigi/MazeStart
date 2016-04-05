package ie.gmit.sw.ai.maze;

import java.awt.Color;
import java.util.*;
public class Node 
{
	//http://www.tutorialspoint.com/java/java_multithreading.htmusing this website to help me with the threading 
	public enum Direction {North, South, East, West};
	private Node parent;
	private boolean startingCell = false;
	private Color color = Color.BLUE;
	private Direction[] paths = null;
	private Set<Node> nodeSet;
	public boolean visit=  false;
	private boolean isStarted = false;
	public boolean goal;
	private int row = -1;
	private int col = -1;
	private int dist;
	private char nodeType;
	private NodeTp nodeTp;
	private boolean isGoalNode = false;
	private boolean hasThePlayer = false;
	
	public boolean isStart() {
		return isStarted;
	}
	
	public boolean isHasThePlayer() {
		return hasThePlayer;
	}
	public void setHasThePlayer(boolean hasThePlayer) {
		this.hasThePlayer = hasThePlayer;
	}

	public List<Node> getAdjacentCells(Node[][] maze) {
		List<Node> adjacentNodes = new ArrayList<Node>();
		
		if(row-1 > 0) adjacentNodes.add(maze[row-2][col]); // Node Above
		if(row+1 < maze.length-1) adjacentNodes.add(maze[row+2][col]); // Node Below
		if(col-1 > 0) adjacentNodes.add(maze[row][col-2]); // Node to left
		if(col+1 < maze[0].length-1) adjacentNodes.add(maze[row][col+2]); // Node to right
		return adjacentNodes; 
	}
	
	public boolean isGoalNodeEND() {
		return isGoalNode;
	}

	public void setGoalNodeEND(boolean isGoalNode) {
		this.isGoalNode = isGoalNode;
	}
	
	public NodeTp getNodeType() {
		return nodeTp;
	}
	
	public void setNodeType(NodeTp nodeTp) {
		this.nodeTp = nodeTp;
	}
	public boolean isStartingCell() {
		return startingCell;
	}
	
	public void setStartingCell(boolean startingCell) {
		this.startingCell = startingCell;
	}
	
	public void setStart(boolean isStart) {
		this.isStarted = isStart;
	}

	public Node(int row, int col) 
	{
		this.row = row;
		this.col = col;
	}

	public Set<Node> getNodeSets() 
	{
		return nodeSet;
	}
	public void NewSet()
	{
		nodeSet = new HashSet<Node>();
	}
	public void setNodeSets(Set<Node> nodeSet) 
	{
		this.nodeSet = nodeSet;
	}
	public void addNodeToSets(Node n)
	{
		this.nodeSet.add(n);
	}
	public void mergeSets(Set<Node> mSet)
	{
		this.nodeSet.addAll(mSet);
	}
	public void setRow(int row) {
		this.row = row;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public char getNodeTypes() {
		return nodeType;
	}

	public void setNodeTypes(char nodeType) {
		this.nodeType = nodeType;
	}

	public boolean hasDirection(Direction direction){	
		for (int i = 0; i < paths.length; i++) {
			if (paths[i] == direction) return true;
		}
		return false;
	}
	
	public Node[] children(Node[][] maze){		
		java.util.List<Node> children = new java.util.ArrayList<Node>();
				
		if (row > 0 && maze[row - 1][col].hasDirection(Direction.South)) children.add(maze[row - 1][col]); //Add North
		if (row < maze.length - 1 && maze[row + 1][col].hasDirection(Direction.North)) children.add(maze[row + 1][col]); //Add South
		if (col > 0 && maze[row][col - 1].hasDirection(Direction.East)) children.add(maze[row][col - 1]); //Add West
		if (col < maze[row].length - 1 && maze[row][col + 1].hasDirection(Direction.West)) children.add(maze[row][col + 1]); //Add East
		
		return (Node[]) children.toArray(new Node[children.size()]);
	}

	public ArrayList<Node> adjacentNodes(Node[][] maze)
	{
		ArrayList<Node> adjacent = new ArrayList<Node>();
		//System.out.println("ROW " + row + " COL " + col);
		if (row-1 > 0) adjacent.add(maze[row - 1][col]); //Add North
		if (row+1 < maze.length) adjacent.add(maze[row + 1][col]); //Add South
		if (col-1 > 0) adjacent.add(maze[row][col - 1]); //Add West
		if (col+1 < maze[row].length) adjacent.add(maze[row][col + 1]); //Add East
		
		return adjacent;
	}
	
	public ArrayList<Node> adjacentNodesFirst(Node[][] maze) {
		
		ArrayList<Node> adjacents = new ArrayList<Node>();
		
		if (row-1 > 0) adjacents.add(maze[row-2][col]); // Add North
		if (row+1 < maze.length - 1) adjacents.add(maze[row + 2][col]); //Add South
		if (col-1> 0) adjacents.add(maze[row][col-2]); // Add West
		if (col+1 < maze[row].length - 1) adjacents.add(maze[row][col + 2]); //Add East

		return adjacents;
	
}
	
	public Direction[] getPaths() {
		return paths;
	}

	public void addPath(Direction direction) {
		int index = 0;
		if (paths == null){
			paths = new Direction[index + 1];		
		}else{
			index = paths.length;
			Direction[] temp = new Direction[index + 1];
			for (int i = 0; i < paths.length; i++) temp[i] = paths[i];
			paths = temp;
		}
		
		paths[index] = direction;
	}

	public boolean isVisited() {
		return visit;
	}

	public void setVisited(boolean visited) {
		this.color = Color.pink;
		this.visit = visited;
	}

	public boolean isGoalNode() {
		return goal;
	}

	public void setGoalNode(boolean goal) {
		this.goal = goal;
	}
	
	public int getHeuristic(Node goal){
		double x1 = this.col;
		double y1 = this.row;
		double x2 = goal.getCol();
		double y2 = goal.getRow();
		double d = 1;
		return (int)(d * Math.abs(x1 - x2) - Math.abs(y1-y2));
	}
	
	public int getPathCost() {
		return dist;
	}

	public void setPathCost(int distance) {
		this.dist = distance;
	}

	public String toString() {
		return "[" + row + "/" + col + "]";
	}

	
}


