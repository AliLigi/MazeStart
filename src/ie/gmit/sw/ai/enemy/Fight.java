package ie.gmit.sw.ai.enemy;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import ie.gmit.sw.ai.player.Player;
//import ie.gmit.sw.ai.player.Weapon;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
//import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class Fight {
	//http://www.tutorialspoint.com/java/java_multithreading.htm facilitating me with info on fuzzy logic
	//most of the code is from past projects mixed with the Internet information 
	private boolean gameFinished;
	private int DEFAULT_VIEW_SIZE;
	private static final String FCL_FILE_NAME = "fcl/FightEvaluator.fcl";
		
	public Fight(int DEFAULT_VIEW_SIZE)
	{
		this.DEFAULT_VIEW_SIZE = DEFAULT_VIEW_SIZE;
	}
		
		public void setGameFinished(boolean b) {
			this.gameFinished = gameFinished;
			
		}
		public void showHealth(Player p, Graphics2D g2)
		{
			// i tried to implement a bar that would show the life left or the health of the player
			Font font = new Font(Font.DIALOG_INPUT, Font.BOLD, 20);
	        g2.setColor(Color.black);
	        g2.fillRect(DEFAULT_VIEW_SIZE/2-100, 39, 101*2,12);
	        g2.setFont(font);
			FontMetrics f = g2.getFontMetrics(font);
	        String str = "Health";
	        g2.drawString(str, DEFAULT_VIEW_SIZE/2-f.stringWidth(str)/2, 30);
	        if(p.getPlayersHealth() > 30)
	        {
	        	g2.setColor(Color.green);
	        }
	        else 
	    	{
	        	g2.setColor(Color.red);
	    	}
	        if(p.getPlayersHealth() >= 0 )
	        {
	        	g2.fillRect(DEFAULT_VIEW_SIZE/2-100, 40, p.getPlayersHealth()*2,10);
	        }
	        else
	        {
	        	showEndGameMenu(p, g2);	
	        }
	        
	        showWeapon(p, g2);
	        if(gameFinished) 
	    	{
	        	showEndGameMenu(p, g2);	
	    	}
		}

		private void showEndGameMenu(Player p, Graphics2D g2) {
			//paints screen black and adds white text
			int finHealth = p.getPlayersHealth();
			Font font = new Font(Font.DIALOG_INPUT, Font.BOLD, 20);
			g2.setFont(font);
			g2.fillRect(0, 0, DEFAULT_VIEW_SIZE, DEFAULT_VIEW_SIZE);
	        g2.setColor(Color.WHITE);
			FontMetrics f = g2.getFontMetrics(font);
			g2.setColor(Color.black);
	        String str = ("GAME Finished");
	        g2.drawString(str, DEFAULT_VIEW_SIZE/6 + f.stringWidth(str), 200);
	        str = ("You Moved\n " + p.getMoves());
	        g2.drawString(str, DEFAULT_VIEW_SIZE/6 + f.stringWidth(str), 250);
	        str = ("\nWith " + finHealth + " health");
	        g2.drawString(str, DEFAULT_VIEW_SIZE/6 + f.stringWidth(str), 300);
		}
		
		//this displays the information i created about the Weapon in the weapon fuzzy logic.fcl
		private void showWeapon(Player p, Graphics2D g2) {
			if (p.getWeapon() > 0) 
			{		
				Font font = new Font(Font.DIALOG_INPUT, Font.BOLD, 20);
				g2.setFont(font);
				FontMetrics f = g2.getFontMetrics(font);
		        g2.setColor(Color.black);
		        
		        g2.fillRect(DEFAULT_VIEW_SIZE/4-125, 39, 50*2,12);
		        
		        String str = "Took Weapon";
		        
		        g2.drawString(str, DEFAULT_VIEW_SIZE/4-f.stringWidth(str), 30);
		        
		        if(p.getWeapon() > 3) 
	        	{
		        	g2.setColor(Color.blue);	
	        	}
		        else g2.setColor(Color.red);
		        {
		        	g2.fillRect(DEFAULT_VIEW_SIZE/4-125, 40, p.getWeapon()*10,10);
		        }
			}
			
		}

		
		//this it the old way i tried to display the information on the fuzzy logic unfortunately it didn't work so well 
		
		/*public int fight(Enemy e, int weapon) {
	        FIS fis = FIS.load(FCL_FILE_NAME,true);
	        FunctionBlock functionBlock = fis.getFunctionBlock("Health");
	        //fis.setVariable("enemy", e.getStrength());
	        fis.setVariable("victory", weapon);
	        
	        Variable victory = functionBlock.getVariable("weapon");
	        fis.evaluate();
	        Variable enemy = functionBlock.getVariable("victory");
	        enemy.getLinguisticTerms();
	        int d = (int)victory.getLatestDefuzzifiedValue();
			return d;
		}*/
	
	

}
