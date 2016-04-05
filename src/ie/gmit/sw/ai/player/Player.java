package ie.gmit.sw.ai.player;

import ie.gmit.sw.ai.maze.Node;
import net.sourceforge.jFuzzyLogic.FIS;

public class Player {

	private Node currentNode;
	private int weapon;
	private boolean Armed;
	private int healthLife = 100;
	private int playerMoves = 1;
	private String fileName = "FCL/WeaponFuzzyLogic.fcl";
	private FIS fis = FIS.load(fileName,true);

	public int getMoves()
	{
		return playerMoves;
	}
	
	public Node getCurrentNode() {
		return currentNode;
	}
	
	public void setCurrentNode(Node currentNode) {
		this.currentNode = currentNode;
		playerMoves++;
	}
	
	//public void addWeapon() {
	//	weapon = new Weapon();
	//}
	
	public int getWeapon() {
		return weapon;
	}
	public void setWeapon(int weapon) {
		this.weapon = weapon;
	}

	public boolean Armed() {
		return Armed;
	}
	public void setArmed(boolean Armed) {
		this.Armed = Armed;
	}

	public int getPlayersHealth() {
		return healthLife;
		
	}
	
	public void setPlayersHealth(int healthLife) {
		this.healthLife = healthLife;
	}

}
