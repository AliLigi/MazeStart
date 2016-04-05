package ie.gmit.sw.ai.player;


import java.util.concurrent.ThreadLocalRandom;





public class Weapon {
	
	//no time to finalize my weapon class
	private int victory;
	private int weapon;
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
	public int getWeapon() {
		return weapon;
	}
	public void setWeapon(int weapon) {
		this.weapon = weapon;
	}
}

	  
	 

