package ie.gmit.sw.ai;


import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;


public class Weapon {
	
	  public boolean fight(Weapon weapon, Enemy enemy){
		
		FIS fis = FIS.load("fcl/WeaponFuzzyLogic.fcl", true); //Load and parse the FCL
		
		 FunctionBlock functionBlock = fis.getFunctionBlock("Weapon");
		 JFuzzyChart.get().chart(functionBlock);
		
		
		 fis.setVariable("weapon", weapon.getPower());
		 fis.setVariable("enemy", enemy.getStrength());
		 fis.evaluate();
		 double victory = fis.getVariable("victory").getValue();
		 this.getLifeForce() = this.getLifeForce() * victory;
		//System.out.println(fis.getVariable("risk").getValue()); //Output end result
	}

	private double getPower() {
		// TODO Auto-generated method stub
		return 0;
	}
	  
	 
}
