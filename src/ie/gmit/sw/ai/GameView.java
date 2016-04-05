package ie.gmit.sw.ai;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;

import ie.gmit.sw.ai.enemy.Fight;
import ie.gmit.sw.ai.maze.Node;
import ie.gmit.sw.ai.player.Player;


public class GameView extends JPanel implements ActionListener{
	
	//most of the code was already done in the sample maze game we received 
	//I didn't get the time to add the enemies and the weapons to the game due to the time 
	//I made a mistake thinking we had to give the project the next day..only realizing this 2 hrs before the deadline.
	private static final long serialVersionUID = 1L;
	public static final int DEFAULT_VIEW_SIZE = 800;	
	private static final int IMAGE_COUNT = 10;
	private int cellspan = 5;	
	private int cellpadding = 2;
	private Node maze[][];
	private BufferedImage[] images;
	private int enemy_state = 5;
	private Timer timer;
	private int currentRow;
	private int currentCol;
	private boolean zoomOut = false;
	private int imageIndex = -1;
	private Fight fight = new Fight(DEFAULT_VIEW_SIZE);
	private int size = DEFAULT_VIEW_SIZE/cellspan;
	private Player player;
	
	
	public GameView(Node[][] maze) throws Exception
	{
		init();
		this.maze = maze;
		setBackground(Color.LIGHT_GRAY);
		setDoubleBuffered(true);
		timer = new Timer(300, this);
		timer.start();
	}
	
	public void setCurrentRow(int row) 
	{
		if (row < cellpadding)
		{
			currentRow = cellpadding;
		}
		else if (row > (maze.length - 1) - cellpadding)
		{
			currentRow = (maze.length - 1) - cellpadding;
		}
		else
		{
			currentRow = row;
		}
	}

	public void setCurrentCol(int col)
	{
		if (col < cellpadding)
		{
			currentCol = cellpadding;
		}
		else if (col > (maze[currentRow].length - 1) - cellpadding)
		{
			currentCol = (maze[currentRow].length - 1) - cellpadding;
		}
		else
		{
			currentCol = col;
		}
	}
	
	public void defineMapColor(int row, int col, Graphics2D g2)
	{
		int x1 = col * size;
		int y1 = row * size;
		char ch = maze[row][col].getNodeTypes();
		
   		if (ch == 'X')
		{        			
   			g2.setColor(Color.GREEN);
			g2.fillRect(x1, y1, size, size);
		}
		else if (ch == ' ')
		{
			g2.setColor(Color.BLUE);
			g2.fillRect(x1, y1, size, size);
		}
		else if (ch == 'W')
		{
			g2.setColor(Color.BLACK);
			g2.fillRect(x1, y1, size, size);
		}
		else if (ch == '?')
		{
			g2.setColor(Color.LIGHT_GRAY);
			g2.fillRect(x1, y1, size, size);
		}
		else if (ch == 'B')
		{
			g2.setColor(Color.RED);
			g2.fillRect(x1, y1, size, size);
		}
		else if (ch == 'H')
		{
			g2.setColor(Color.WHITE);
			g2.fillRect(x1, y1, size, size);
		}
		else if (ch == 'E')
		{
			g2.setColor(Color.ORANGE);
			g2.fillRect(x1, y1, size, size);  			
		}
		else if (ch == 'G')
		{
			g2.setColor(Color.YELLOW);
			g2.fillRect(x1, y1, size, size);  			
		}
		else if (ch == 'V')
		{
			g2.setColor(Color.PINK);
			g2.fillRect(x1, y1, size, size);    			
		}
		else if (ch == 'C')
		{
			g2.setColor(Color.DARK_GRAY);
			g2.fillRect(x1, y1, size, size);   			
		}
		else
		{
			g2.setColor(Color.LIGHT_GRAY);
			g2.fillRect(x1, y1, size, size);
		}
	}

	public void paintComponent(Graphics g)
	{
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
              
        cellspan = zoomOut ? maze.length : 5;         
        //final int size = DEFAULT_VIEW_SIZE/cellspan;
        g2.drawRect(0, 0, GameView.DEFAULT_VIEW_SIZE, GameView.DEFAULT_VIEW_SIZE);
        
        for(int row = 0; row < cellspan; row++) 
        {
        	for (int col = 0; col < cellspan; col++)
        	{  
        		size = DEFAULT_VIEW_SIZE/cellspan;
        		int x1 = col * size;
        		int y1 = row * size;
        		
        		char ch = 'X';
       		
        		if (zoomOut)
        		{
        			ch = maze[row][col].getNodeTypes();
        			size = DEFAULT_VIEW_SIZE/cellspan;
        			defineMapColor(row, col, g2);
        		}
        		else
        		{
        			ch = maze[currentRow - cellpadding + row][currentCol - cellpadding + col].getNodeTypes();
        		}
        		
        		
        		if (ch == 'X')
        		{        			
        			imageIndex = 0;;
        		}
        		else if (ch == ' ')
        		{
        			imageIndex = 7;;
        		}
        		else if (ch == 'W')
        		{
        			imageIndex = 1;;
        		}
        		else if (ch == '?')
        		{
        			imageIndex = 2;;
        		}
        		else if (ch == 'B')
        		{
        			imageIndex = 3;;
        		}
        		else if (ch == 'H')
        		{
        			imageIndex = 4;;
        		}
        		else if (ch == 'E')
        		{
        			imageIndex = enemy_state;;       			
        		}
        		else if (ch == 'G')
        		{
        			imageIndex = 7;;
        		}
        		else if (ch == 'V')
        		{
        			imageIndex = 8;;
        		}
        		else
        		{
        			imageIndex = -1;
        		}
        		
        		if (imageIndex >= 0)
        		{
        			g2.drawImage(images[imageIndex], x1, y1, null);
        		}
        		else
        		{
        			g2.setColor(Color.LIGHT_GRAY);
        			g2.fillRect(x1, y1, size, size);
        		}      		
        	}
        }
	}
	
	public void triggerTheEndScreen() {
		fight.setGameFinished(true);
		
	}
	
	public void addingPlayer(Player player) {
		this.player = player;
		
	}
	
	public void toggleZoom()
	{
		zoomOut = !zoomOut;		
	}

	public void actionPerformed(ActionEvent e) 
	{	
		if (enemy_state < 0 || enemy_state == 5)
		{
			enemy_state = 6;
		}
		else
		{
			enemy_state = 5;
		}
		
		this.repaint();
	}
	
	private void init() throws Exception{
		images = new BufferedImage[IMAGE_COUNT];
		images[0] = ImageIO.read(new java.io.File("resources/hedge.png"));
		images[1] = ImageIO.read(new java.io.File("resources/sword.png"));		
		images[2] = ImageIO.read(new java.io.File("resources/help.png"));
		images[3] = ImageIO.read(new java.io.File("resources/bomb.png"));
		images[4] = ImageIO.read(new java.io.File("resources/h_bomb.png"));
		images[5] = ImageIO.read(new java.io.File("resources/spider_down.png"));
		images[6] = ImageIO.read(new java.io.File("resources/spider_up.png"));
		//images[7] = ImageIO.read(new java.io.File("resources/spidermanUp.png"));
		//images[8] = ImageIO.read(new java.io.File("resources/spiderManDown.png"));
		
	}
	
}