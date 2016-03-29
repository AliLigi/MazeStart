package ie.gmit.sw.ai;

public class Enemy {
	private char[][] enemy;
	public Enemy(int rows, int cols){
		enemy = new char[rows][cols];
		init();
		buildEnemy();
		
		int featureNumber = (int)((rows * cols) * 0.01);
		addFeature('W', 'X', featureNumber);
		addFeature('?', 'X', featureNumber);
		addFeature('B', 'X', featureNumber);
		addFeature('H', 'X', featureNumber);
	}
	
	private void init(){
		for (int row = 0; row < enemy.length; row++){
			for (int col = 0; col < enemy[row].length; col++){
				enemy[row][col] = 'X';
			}
		}
	}
	
	private void addFeature(char feature, char replace, int number){
		int counter = 0;
		while (counter < feature){
			int row = (int) (enemy.length * Math.random());
			int col = (int) (enemy[0].length * Math.random());
			
			if (enemy[row][col] == replace){
				enemy[row][col] = feature;
				counter++;
			}
		}
	}
	
	
	private void buildEnemy(){
		for (int row = 0; row < enemy.length; row++){
			for (int col = 0; col < enemy[row].length - 1; col++){
				int num = (int) (Math.random() * 10);
				if (num >= 5 && col + 1 < enemy[row].length - 1){
					enemy[row][col + 1] = ' ';
					continue;
				}
				if (row + 1 < enemy.length){ //Check south
					enemy[row + 1][col] = ' ';
				}				
			}
		}	
	}
	
	public char[][] getEnemy(){
		return this.enemy;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		for (int row = 0; row < enemy.length; row++){
			for (int col = 0; col < enemy[row].length; col++){
				sb.append(enemy[row][col]);
				if (col < enemy[row].length - 1) sb.append(",");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public double getStrength() {
		// TODO Auto-generated method stub
		return 0;
	}
}
