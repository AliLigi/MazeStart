package ie.gmit.sw.ai.enemy;

//import ie.gmit.sw.ai.player.Weapon;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
//import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class Fight {
	  private static final String FCL_FILE_NAME = "fcl/FightEvaluator.fcl";
		
		public Fight() {
			super();
		}
		
		public int fight(Enemy e, int weapon) {
	        FIS fis = FIS.load(FCL_FILE_NAME,true);
	        FunctionBlock functionBlock = fis.getFunctionBlock("Weapon");
	        //fis.setVariable("enemy", e.getStrength());
	        fis.setVariable("victory", weapon);
	        
	        Variable victory = functionBlock.getVariable("victory");
	        fis.evaluate();
	        Variable enemy = functionBlock.getVariable("enemy");
	        enemy.getLinguisticTerms();
	        int d = (int)victory.getLatestDefuzzifiedValue();
			return d;
		}
	
	 /* public boolean fight(Weapon weapon, Enemy enemy){
		
		FIS fis = FIS.load("fcl/WeaponFuzzyLogic.fcl", true); //Load and parse the FCL
		
		 FunctionBlock functionBlock = fis.getFunctionBlock("Weapon");
		 JFuzzyChart.get().chart(functionBlock);
		
		
		 fis.setVariable("weapon", weapon.getPower());
		 fis.setVariable("enemy", enemy.getStrength());
		 fis.evaluate();
		 double victory = fis.getVariable("victory").getValue();
		 //this.getLifeForce() = this.getLifeForce() * victory;
		//System.out.println(fis.getVariable("victory").getValue()); //Output end result
		return true;
	}*/

}
