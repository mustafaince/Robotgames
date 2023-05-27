package robotgames2;

public class Robot {
	public Module modules []  = new Module [4];
	
	public Robot(){ //constructor of the Robotclass
		modules[0] = new Module("tr", 0);
		modules[1] = new Module("hd", 0);
		modules[2] = new Module("lg", 0);
		modules[3] = new Module("ar", 0);
	}
	
	public double TorsoForce(){
		return (100 + (modules[0].getQuality() * 80));
	}
	
	public double LegForce(){
		return (100 + (modules[2].getQuality() *80));
	}
	public double Weight(){
		return (100 +  (modules[0].getQuality() * 10)) + (20 + (modules[1].getQuality() * 1)) + (80 + (modules[2].getQuality() * 4)) + (40 + (modules[3].getQuality() * 2));
	}
	
	public double Force(){
		return TorsoForce() + LegForce();
	}
	
	public double Intelligence(){
		return (100 + (modules[1].getQuality() * 160));
	}
	
	public double Skill(){
		return (100 + (modules[3].getQuality() * 200));
	}
	
	public double Speed(){
		return (250.0 * LegForce()) / Weight();
	}
	
	public double ScoreRoboChess(){
		return Intelligence() * ((double)modules[1].getDurability()/100);
	}
	
	public double ScoreRoboRun(){
		return Speed() * ((double)modules[2].getDurability()/100);
	}
	
	public double ScoreRoboSumo(){
		return ((TorsoForce() *((double)modules[0].getDurability()/100)) * 0.7) + ((LegForce() * ((double)modules[2].getDurability()/100)) * 0.3);
	}
	
	public double ScoreRoboPingPong(){
		return ((Skill() * ((double)modules[3].getDurability()/100)) * 0.6) + ((Intelligence() * ((double)modules[1].getDurability()/100)) * 0.2) + ((Speed() * ((double)modules[2].getDurability()/100)) * 0.2);
	}

}
