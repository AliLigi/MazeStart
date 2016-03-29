package ie.gmit.sw.ai;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;


public class GameView extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	public static final int DEFAULT_VIEW_SIZE = 800;	
	private static final int IMAGE_COUNT = 7;
	private int cellspan = 5;	
	private int cellpadding = 2;
	private char[][] maze;
	private char[][] enemy;
	private BufferedImage[] images;
	private int enemy_state = 5;
	private int eny_state = 4;
	private Timer timer;
	private int currentRow;
	private int currentCol;
	private boolean zoomOut = false;
	private int imageIndex = -1;
	
	public GameView(char[][] maze, char[][] enemy) throws Exception{
		init();
		this.maze = maze;
		this.enemy = enemy;
		setBackground(Color.LIGHT_GRAY);
		setDoubleBuffered(true);
		timer = new Timer(300, this);
		timer.start();
	}
	
	public void setCurrentRow(int row) {
		if (row < cellpadding){
			currentRow = cellpadding;
		}else if (row > (maze.length - 1) - cellpadding){
			currentRow = (maze.length - 1) - cellpadding;
		}else{
			currentRow = row;
		}
	}

	public void setCurrentCol(int col) {
		if (col < cellpadding){
			currentCol = cellpadding;
		}else if (col > (maze[currentRow].length - 1) - cellpadding){
			currentCol = (maze[currentRow].length - 1) - cellpadding;
		}else{
			currentCol = col;
		}
	}
	public void setCurrentR(int row) {
		if (row < cellpadding){
			currentRow = cellpadding;
		}else if (row > (enemy.length - 1) - cellpadding){
			currentRow = (enemy.length - 1) - cellpadding;
		}else{
			currentRow = row;
		}
	}

	public void setCurrentC(int col) {
		if (col < cellpadding){
			currentCol = cellpadding;
		}else if (col > (enemy[currentRow].length - 1) - cellpadding){
			currentCol = (enemy[currentRow].length - 1) - cellpadding;
		}else{
			currentCol = col;
		}
	}

	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
              
        cellspan = zoomOut ? maze.length : 5;         
        final int size = DEFAULT_VIEW_SIZE/cellspan;
        g2.drawRect(0, 0, GameView.DEFAULT_VIEW_SIZE, GameView.DEFAULT_VIEW_SIZE);
        
        for(int row = 0; row < cellspan; row++) {
        	for (int col = 0; col < cellspan; col++){  
        		int x1 = col * size;
        		int y1 = row * size;
        		
        		char ch = 'X';
        		
       		
        		if (zoomOut){
        			ch = maze[row][col];
        			ch = enemy[row][col];
        			if (row == currentRow && col == currentCol){
        				g2.setColor(Color.YELLOW);
        				g2.fillRect(x1, y1, size, size);
        				continue;
        			}
        		}else{
        			ch = maze[currentRow - cellpadding + row][currentCol - cellpadding + col];
        			ch = enemy[currentRow - cellpadding + row][currentCol - cellpadding + col];
        		}
        		
        		
        		if (ch == 'X'){        			
        			imageIndex = 0;;
        		}else if (ch == 'W'){
        			imageIndex = 1;;
        		}else if (ch == '?'){
        			imageIndex = 2;;
        		}else if (ch == 'B'){
        			imageIndex = 3;;
        		}else if (ch == 'H'){
        			imageIndex = eny_state;;
        		}else if (ch == 'E'){
        			imageIndex = enemy_state;;       			
        		}else{
        			imageIndex = -1;
        		}
        		
        		if (imageIndex >= 0){
        			g2.drawImage(images[imageIndex], x1, y1, null);
        		}else{
        			g2.setColor(Color.LIGHT_GRAY);
        			g2.fillRect(x1, y1, size, size);
        		}      		
        	}
        }
	}
	
	public void toggleZoom(){
		zoomOut = !zoomOut;		
	}

	public void actionPerform(ActionEvent e) {	
		if (eny_state < 0 || eny_state == 5){
			eny_state = 6;
		}else{
			eny_state = 5;
		}
		this.repaint();
	}
	
	public void actionPerformed(ActionEvent e) {	
		if (enemy_state < 0 || enemy_state == 5){
			enemy_state = 6;
		}else{
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
	}
}