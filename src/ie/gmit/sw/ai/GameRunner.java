package ie.gmit.sw.ai;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import ie.gmit.sw.ai.enemy.Enemy;
import ie.gmit.sw.ai.enemy.Enemy.SearchTypeOfEnemy;
//import ie.gmit.sw.ai.enemy.Enemy1;
//import ie.gmit.sw.ai.enemy.EnemyInterface;
import ie.gmit.sw.ai.maze.EllersAlgoMaze;
import ie.gmit.sw.ai.maze.MazeGenerator;
import ie.gmit.sw.ai.maze.Node;
import ie.gmit.sw.ai.player.Player;

//import ie.gmit.sw.ai.maze.MazeTemplate;


//THIS IS THE SAME GAMERUNNER WITH LITTLE ADJUSTMENTS

public class GameRunner implements KeyListener{
	
	private static final int MAZE_DIMENSION = 50;
	private Node[][] model;
	//private char[][]enemy;
	private GameView view;
	private int currentRow;
	private int currentCol;
	private Node goalNode;
	private Player player;
	
	public GameRunner() throws Exception{
		
		player = new Player();
		MazeGenerator m = new EllersAlgoMaze();
		m.buildTheMaze(MAZE_DIMENSION, MAZE_DIMENSION);
		model = m.getTheMaze();
		//Node goalNode = m.getingNode();
    	view = new GameView(model);
    	goalNode = m.getingNode();
    	
    	placePlayer();
		
		//Enemy e = new Enemy(5, 5);
		//MazeTemplate m = new MazeTemplate(MAZE_DIMENSION, MAZE_DIMENSION);
		//model = m.getMaze();
		//enemy = e.getEnemy();
		
    	//view = new GameView(model,enemy );
    	
    	//placePlayer();
    	
    	Dimension d = new Dimension(GameView.DEFAULT_VIEW_SIZE, GameView.DEFAULT_VIEW_SIZE);
    	view.setPreferredSize(d);
    	view.setMinimumSize(d);
    	view.setMaximumSize(d);
    	
    	JFrame f = new JFrame("GMIT - B.Sc. in Computing (Software Development)");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.addKeyListener(this);
        f.getContentPane().setLayout(new FlowLayout());
        f.add(view);
        f.setSize(1000,1000);
        f.setLocation(100,100);
        f.pack();
        f.setVisible(true);
        createEnemy();
 	
	}
	private void placePlayer()
	{   	
		boolean isValidated = false;
		while(!isValidated)
		{
			currentRow = (int) (MAZE_DIMENSION * Math.random());
			currentCol = (int) (MAZE_DIMENSION * Math.random());
			if(model[currentRow][currentCol].getNodeTypes() == ' ')
			{
				isValidated = true;
			}
		}
    	model[currentRow][currentCol].setNodeTypes('E');
    	player.setCurrentNode(model[currentRow][currentCol]);
    	updateView(); 		
	}
	
	private void updateView()
	{
		view.setCurrentRow(currentRow);
		view.setCurrentCol(currentCol);
		player.setCurrentNode(model[currentRow][currentCol]);
		
	}
	
	
	private void createEnemy() {

		for(int i = 1 ; i <= 4 ; i++)
		{
			Enemy.SearchTypeOfEnemy searching;
			if(i % 2 == 0)
			{
				searching = SearchTypeOfEnemy.ASTARALGORITHM;
			}
			else
			{
				searching = SearchTypeOfEnemy.DFSALGORITHM;
			}
				
			boolean isValid = false;
			while(!isValid)
			{
				int tempRow = (int) (MAZE_DIMENSION * Math.random());
				int tempCol = (int) (MAZE_DIMENSION * Math.random());
				if(model[tempRow][tempCol].getNodeTypes() == ' ')
				{
					isValid = true;
				}
			}
			Thread enemy = new Thread() 
			{
			    public void run() 
			    {
			        try 
			        { 
			        	System.out.println("NEW ENEMY");
			        	Enemy enemy = new Enemy(player, searching, goalNode, model);
			        	enemy.initTheHunter();        	
			        } 
			        catch(Exception e) 
			        {
			            System.out.println(e);
			        }
			    }  
			};
			enemy.start();
		}
		
	}


	/*private void placePlayer(){   	
    	currentRow = (int) (MAZE_DIMENSION * Math.random());
    	currentCol = (int) (MAZE_DIMENSION * Math.random());
    	model[currentRow][currentCol].setNodeTypes('E');
    	updateView(); 	 		
	}
	
	private void updateView(){
		view.setCurrentRow(currentRow);
		view.setCurrentCol(currentCol);
	}*/

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && currentCol < MAZE_DIMENSION - 1) {
        	if (isValidMove(currentRow, currentCol + 1)) currentCol++;   		
        }else if (e.getKeyCode() == KeyEvent.VK_LEFT && currentCol > 0) {
        	if (isValidMove(currentRow, currentCol - 1)) currentCol--;	
        }else if (e.getKeyCode() == KeyEvent.VK_UP && currentRow > 0) {
        	if (isValidMove(currentRow - 1, currentCol)) currentRow--;
        }else if (e.getKeyCode() == KeyEvent.VK_DOWN && currentRow < MAZE_DIMENSION - 1) {
        	if (isValidMove(currentRow + 1, currentCol)) currentRow++;        	  	
        }else if (e.getKeyCode() == KeyEvent.VK_Z){
        	view.toggleZoom();
        }else{
        	return;
        }
        
        updateView();       
    }
    public void keyReleased(KeyEvent e) {} //Ignore
	public void keyTyped(KeyEvent e) {} //Ignore

    
	private boolean isValidMove(int r, int c)
	{
		if (r <= model.length - 1 && c <= model[r].length - 1 && (model[r][c].getNodeTypes() == ' ' ||model[r][c].getNodeTypes() == 'C'))
		{
			model[currentRow][currentCol].setNodeTypes(' ');
			model[r][c].setNodeTypes('E');
			return true;
		}
		else if(r <= model.length - 1 && c <= model[r].length - 1 && model[r][c].getNodeTypes() == '?')
		{
			Thread help = new Thread() 
			{
			    public void run() 
			    {
			    	System.out.println("RUN");
			        try 
			        { 
			        	System.out.println("NEW THREAD WORKING");
						//EnemyInterface helper = new Enemy1(goalGoal);
						//helper.traverser(model,model[currentRow][currentCol]);
			        } 
			        catch(Exception e) 
			        {
			            System.out.println(e);
			        }
			    }  
			};
			help.start();
			return false;
		}
		else
		{
			return false; //Can't move
		}
	}
	
	
	public static void main(String[] args) throws Exception{
		new GameRunner();
	}
}