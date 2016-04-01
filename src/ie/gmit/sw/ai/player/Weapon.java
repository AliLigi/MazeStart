package ie.gmit.sw.ai.player;


import java.util.concurrent.ThreadLocalRandom;

//import ie.gmit.sw.ai.enemy.Enemy;



public class Weapon {
	private int victory;
	// Randomize condition of found weapon
	public Weapon() {
		int d = ThreadLocalRandom.current().nextInt(3, 8 + 1);
		this.victory = d;

	}
	
	public int getEnemy() {
		return victory;
	}
	
	public void setEnemy(int v) {
		this.victory = v;
	}
}

	  
	 

