package ie.gmit.sw.ai.player;

import ie.gmit.sw.ai.maze.Node;

public class Player {

	private Node currentNode;
	private Weapon weapon;
	private int victory = 100;
	
	//public Player(Node currentNode) {
	//	this.currentNode = currentNode;
	//}
	
	public Node getCurrentNode() {
		return currentNode;
	}
	
	public void setCurrentNode(Node currentNode) {
		this.currentNode = currentNode;
	}
	
	public void addWeapon() {
		weapon = new Weapon();
	}
	
	public int getEnemy() {
		if (weapon!= null)	return weapon.getEnemy();
		else return 0;
	}
	
	public void setEnemy(int strenght) {
		if (weapon != null) weapon.setEnemy(strenght);
	}

	public int getVictory() {
		return victory;
	}

	public void setVictory(int victory) {
		this.victory = victory;
	}

}
