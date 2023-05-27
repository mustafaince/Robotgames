package robotgames2;

public class Team {
	public Robot robots [] = new Robot[9];
	public Module modules [] = new Module[20];
	private double Credit;
	
	public Team(){ //constructor of the Team class
		for(int i=0; i<robots.length; i++)
			robots[i] = new Robot();
		for(int i=0; i<modules.length; i++)
			modules[i] = new Module(" ", 0);
		Credit = 1500;
	}	

	public double getCredit() {
		return (int)Credit; 
		}
	public void setCredit(double d) { 
		Credit = d; 
		}

	public int FindEmptyModule(){ //to find the first empty module place in the inventory
		int empty_module_number=99;
		
		for(int i=0; i<modules.length; i++){
			if(modules[i].getType().equals(" ")){
				empty_module_number = i;
				break;
			}
		}
		return empty_module_number;
	}
	
	public int EmptyModulesNumbers(){ //how many empty module places does a team have in its inventory?
		int number = 0;
		
		for(int i=0; i<modules.length; i++){
			if(modules[i].getType().equals(" ")){
				number++;
			}
		}
		
		return number;
	}
	
	public int ModuleNumber(){  //how many modules does a team have in its inventory?
		int module_number=0;
		for(int i=0; i<modules.length; i++){
			if(modules[i].getQuality()!=0)module_number++;
		}
		return module_number;
	}
	
	public int RobotNumber(){ //how many robots does a team have in its inventory?
		int robot_number=0;
		for(int i=0; i<robots.length; i++){			
				if(robots[i].modules[0].getQuality() !=0 && robots[i].modules[1].getQuality() !=0 && robots[i].modules[2].getQuality() !=0 && robots[i].modules[3].getQuality() !=0) robot_number++;				
		}	
	return 	robot_number;
	}
	
	
	public int WhichRobot(int i){ //to find out exactly which robots a team has
		int which_robot;
			if(robots[i].modules[0].getQuality() !=0 || robots[i].modules[1].getQuality() !=0 || robots[i].modules[2].getQuality() !=0 || robots[i].modules[3].getQuality() !=0) which_robot=i;
			else which_robot=-1;
			
		return which_robot;
			
	}
	
	public String PrintRobots(int which_robot, int module){ //to print robots on the screen orderly
		String print_robots;
		if(robots[which_robot].modules[module].getQuality() == 0) print_robots="          ";
		else if (robots[which_robot].modules[module].getDurability() == 100) print_robots= robots[which_robot].modules[module].getType() + robots[which_robot].modules[module].getQuality() + "-" + robots[which_robot].modules[module].getDurability();
		else print_robots = robots[which_robot].modules[module].getType() + robots[which_robot].modules[module].getQuality() + "-" + robots[which_robot].modules[module].getDurability() + " ";
		return print_robots;
	}
	
	
	public String PrintModules(int a){ //to print modules on the screen orderly
		String print_modules;
		if(modules[a].getQuality() == 0 && modules[a].getDurability() == 0) print_modules="       ";
		else if (modules[a].getDurability() < 100)  print_modules = modules[a].getType() + modules[a].getQuality() + "-" + modules[a].getDurability() + " ";
		else print_modules = modules[a].getType() + modules[a].getQuality() + "-" + modules[a].getDurability();
		return print_modules;
	}
	
	public String PrintRobotsScores(int team_number, int which_robot){ //to print robots' score on the screen orderly
		String robot_scores;
		if(robots[which_robot].modules[0].getDurability() < 60 || robots[which_robot].modules[1].getDurability() < 60 || robots[which_robot].modules[2].getDurability() < 60 || robots[which_robot].modules[3].getDurability() < 60){
			robot_scores = "   (Ch: - \tRn: - \tSm: - \tPp: - ";
		}
		else
			robot_scores = "   (" +"Ch:" + (int)robots[which_robot].ScoreRoboChess() + "\tRn:" + (int)robots[which_robot].ScoreRoboRun() + "\tSm:" + (int)robots[which_robot].ScoreRoboSumo() + "\tPp:" +(int)robots[which_robot].ScoreRoboPingPong() + ")";
		
		return robot_scores;
	}
	
	
	}
