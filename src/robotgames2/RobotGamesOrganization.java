package robotgames2;
import enigma.core.Enigma;

public class RobotGamesOrganization {

	static Team teams[] = new Team [6];
	int week;
	String command_type; //type of module coming that from command (hd/tr/lg/ar)
	int command_quality; //quality of module that coming from command
	int module_number; //number of module that coming from command
	int robot_number; //number of robot that coming from command
	String command = null;
	static Tournament tournament = new Tournament();
	
	public enigma.console.Console cn = Enigma.getConsole("Robot Games", 100, 69, 15);
		
	
	public RobotGamesOrganization() {	//constructor of the RobotGamesOrganization class
		for(int i=0; i<teams.length; i++)
			teams[i] = new Team();	
		
		week = 1;
		
			do{			//the main operation of the game
				for(int i=5; i>=0 ; i--){					
					do{
						ClearScreen();
						Display(i);
						Command(i);
							
					}while(!(command.equals("pl")));					
				}
			} while(!(teams[0].getCredit() >= 10000 && teams[0].RobotNumber() >=6) && !(teams[1].getCredit() >= 10000 && teams[1].RobotNumber() >=6) && !(teams[2].getCredit() >= 10000 && teams[2].RobotNumber() >=6) &&
					!(teams[3].getCredit() >= 10000 && teams[3].RobotNumber() >=6) && !(teams[4].getCredit() >= 10000 && teams[4].RobotNumber() >=6) && !(teams[5].getCredit() >= 10000 && teams[5].RobotNumber() >=6));
			
			ClearScreen();
			Display(0);
			
			if(teams[0].getCredit() >= 10000 && teams[0].RobotNumber() >=6) cn.getTextWindow().output("\n\n Robot Games' Winner: Team 1");
			else if(teams[1].getCredit() >= 10000 && teams[1].RobotNumber() >=6) cn.getTextWindow().output("\n\n Robot Games' Winner: Team 2");
			else if(teams[2].getCredit() >= 10000 && teams[2].RobotNumber() >=6) cn.getTextWindow().output("\n\n Robot Games' Winner: Team 3");
			else if(teams[3].getCredit() >= 10000 && teams[3].RobotNumber() >=6) cn.getTextWindow().output("\n\n Robot Games' Winner: Team 4");
			else if(teams[4].getCredit() >= 10000 && teams[4].RobotNumber() >=6) cn.getTextWindow().output("\n\n Robot Games' Winner: Team 5");
			else if(teams[5].getCredit() >= 10000 && teams[5].RobotNumber() >=6) cn.getTextWindow().output("\n\n Robot Games' Winner: Team 6");
		}
	
	public void Display(int team_number){ //printing screen
		cn.getTextWindow().setCursorPosition(0, 0); cn.getTextWindow().output("");
		cn.getTextWindow().output("Week:" + week + " Robot/Credit:" + " T1:" + teams[0].RobotNumber() + "/" +(int) teams[0].getCredit() + " T2:" + teams[1].RobotNumber() + "/" + (int) teams[1].getCredit() 
																	+ " T3:" + teams[2].RobotNumber() + "/" +(int) teams[2].getCredit() + " T4:" + teams[3].RobotNumber() + "/" + (int) teams[3].getCredit() 
																	+ " T5:" + teams[4].RobotNumber() + "/" +(int) teams[4].getCredit() + " T6:" + teams[5].RobotNumber() + "/" + (int) teams[5].getCredit() );
		cn.getTextWindow().output("\n--- Team1: Modules ---");
		cn.getTextWindow().output("\nm01."+teams[0].PrintModules(0)+"\tm02."+teams[0].PrintModules(1)+"\tm03."+teams[0].PrintModules(2)+"\tm04."+teams[0].PrintModules(3)+"\tm05."+teams[0].PrintModules(4));
		cn.getTextWindow().output("\nm06."+teams[0].PrintModules(5)+"\tm07."+teams[0].PrintModules(6)+"\tm08."+teams[0].PrintModules(7)+"\tm09."+teams[0].PrintModules(8)+"\tm10."+teams[0].PrintModules(9));
		cn.getTextWindow().output("\nm11."+teams[0].PrintModules(10)+"\tm12."+teams[0].PrintModules(11)+"\tm13."+teams[0].PrintModules(12)+"\tm14."+teams[0].PrintModules(13)+"\tm15."+teams[0].PrintModules(14));
		cn.getTextWindow().output("\nm16."+teams[0].PrintModules(15)+"\tm17."+teams[0].PrintModules(16)+"\tm18."+teams[0].PrintModules(17)+"\tm19."+teams[0].PrintModules(18)+"\tm20."+teams[0].PrintModules(19));
		cn.getTextWindow().output("\n--- Team1: Robots ---");
		DisplayRobots(0);
		cn.getTextWindow().output("\n--- Games (Registering) ---");
		cn.getTextWindow().output("\nChess: " + tournament.games[0].prize + "\tRun: " + tournament.games[1].prize + "\tSumo: " + tournament.games[2].prize + "\tPingPong: " + tournament.games[3].prize);
		cn.getTextWindow().output("\nTeam1: " + DisplayRegistering());
		
		
	}
	
	public void DisplayRobots(int team_number){ //printing robots that the team has

		for(int i=0; i<teams[team_number].robots.length; i++){
			int which_robot = teams[team_number].WhichRobot(i);
			if (which_robot !=-1){
				cn.getTextWindow().output("\nr" + (which_robot+1) + ": " + teams[team_number].PrintRobots(which_robot, 0) + " " + teams[team_number].PrintRobots(which_robot, 1)
																+ " " + teams[team_number].PrintRobots(which_robot, 2)+ " " + teams[team_number].PrintRobots(which_robot, 3) 
																+ teams[team_number].PrintRobotsScores(team_number, which_robot));
			}
		}
	}
	
	public String DisplayRegistering(){ //printing games that attended by robots
		String str= "";
		for(int i=0; i<4; i++){
			for(int j=0; j<9; j++){
				if(tournament.games[i].participant_teams[0].participant_robots[j].robots_participation == true){
					if(i==0) str = str + "r" + (j+1) + ">c" + tournament.games[i].participant_teams[0].participant_robots[j].turn + "\t";
					else if(i==1) str = str + "r" + (j+1) + ">r" + tournament.games[i].participant_teams[0].participant_robots[j].turn + "\t";
					else if(i==2) str = str + "r" + (j+1) + ">s" + tournament.games[i].participant_teams[0].participant_robots[j].turn + "\t";
					else if(i==3) str = str + "r" + (j+1) + ">p" + tournament.games[i].participant_teams[0].participant_robots[j].turn + "\t";
				}
			}
		}
		
		return str;
	}
	
	public static int NewPrice(String type, int quality){ //calculating the price of the new module
		
		int price=1;
		
		if(type.equals("tr")) price = quality * 150; 
		else if(type.equals("hd")) price = quality * 100;
		else if(type.equals("lg")) price = quality * 50;
		else if(type.equals("ar")) price = quality * 40;
		
		return price;	
		
	}
	
	public void Buy(int team_number){ //method for "by" command
		int empty_module_number = teams[team_number].FindEmptyModule();	
		
		if (empty_module_number<20 ){
			teams[team_number].modules[empty_module_number].setType(command_type);
			teams[team_number].modules[empty_module_number].setQuality(command_quality);
			teams[team_number].modules[empty_module_number].setDurability(100);			
			teams[team_number].setCredit(teams[team_number].getCredit() - NewPrice(command_type, command_quality));		
		}	
		
	}
	
	public void SellModule(int team_number){ //method for "sl m()" command
		teams[team_number].setCredit(teams[team_number].getCredit() + (0.5 * NewPrice(teams[team_number].modules[module_number].getType(), teams[team_number].modules[module_number].getQuality()) * (teams[team_number].modules[module_number].getDurability()/100.0)));
		teams[team_number].modules[module_number].setType(" ");
		teams[team_number].modules[module_number].setQuality(0);
		teams[team_number].modules[module_number].setDurability(0);		
	}
	
	public void BuildNewRobotNewestModule(int team_number){ //method for "++ r..." command when a team wants to build a robot with the newest modules 
		int max_durability = -1;
		int max_module_number = -1;

			if (command_type.equals("tr")){
			for(int i=0; i<20; i++){
				if(teams[team_number].modules[i].getDurability() > max_durability && teams[team_number].modules[i].getType().equals("tr") && teams[team_number].modules[i].getQuality() == command_quality){
					max_durability= teams[team_number].modules[i].getDurability();
					max_module_number = i;
				}
			}
				teams[team_number].robots[robot_number].modules[0].setQuality(teams[team_number].modules[max_module_number].getQuality());
				teams[team_number].robots[robot_number].modules[0].setDurability(teams[team_number].modules[max_module_number].getDurability());
			}
			else if(command_type.equals("hd")){
			for(int i=0; i<20; i++){
				if(teams[team_number].modules[i].getDurability() > max_durability && teams[team_number].modules[i].getType().equals("hd") && teams[team_number].modules[i].getQuality() == command_quality){
					max_durability= teams[team_number].modules[i].getDurability();
					max_module_number = i;
				}
			}
				teams[team_number].robots[robot_number].modules[1].setQuality(teams[team_number].modules[max_module_number].getQuality());
				teams[team_number].robots[robot_number].modules[1].setDurability(teams[team_number].modules[max_module_number].getDurability());
			}
			else if (command_type.equals("lg")){
			for(int i=0; i<20; i++){
				if(teams[team_number].modules[i].getDurability() > max_durability && teams[team_number].modules[i].getType().equals("lg") && teams[team_number].modules[i].getQuality() == command_quality){
					max_durability= teams[team_number].modules[i].getDurability();
					max_module_number = i;
				}
			}
				teams[team_number].robots[robot_number].modules[2].setQuality(teams[team_number].modules[max_module_number].getQuality());
				teams[team_number].robots[robot_number].modules[2].setDurability(teams[team_number].modules[max_module_number].getDurability());
			}
			else if (command_type.equals("ar")){
			for(int i=0; i<20; i++){
				if(teams[team_number].modules[i].getDurability() > max_durability && teams[team_number].modules[i].getType().equals("ar") && teams[team_number].modules[i].getQuality() == command_quality){
					max_durability= teams[team_number].modules[i].getDurability();
					max_module_number = i;
				}
			}
				teams[team_number].robots[robot_number].modules[3].setQuality(teams[team_number].modules[max_module_number].getQuality());
				teams[team_number].robots[robot_number].modules[3].setDurability(teams[team_number].modules[max_module_number].getDurability());
			}
			
			teams[team_number].modules[max_module_number].setType(" ");
			teams[team_number].modules[max_module_number].setQuality(0);
			teams[team_number].modules[max_module_number].setDurability(0);	
		
	}
	
	public void BuildNewRobotUsingModule(int team_number){ //method for "++ r..." command when a team wants to build a robot with entering the number of the modules
		if(teams[team_number].modules[module_number].getType().equals("tr")){
			teams[team_number].robots[robot_number].modules[0].setQuality(teams[team_number].modules[module_number].getQuality());
			teams[team_number].robots[robot_number].modules[0].setDurability(teams[team_number].modules[module_number].getDurability());
		}
		else if (teams[team_number].modules[module_number].getType().equals("hd")){
			teams[team_number].robots[robot_number].modules[1].setQuality(teams[team_number].modules[module_number].getQuality());
			teams[team_number].robots[robot_number].modules[1].setDurability(teams[team_number].modules[module_number].getDurability());
		}
		else if (teams[team_number].modules[module_number].getType().equals("lg")){
			teams[team_number].robots[robot_number].modules[2].setQuality(teams[team_number].modules[module_number].getQuality());
			teams[team_number].robots[robot_number].modules[2].setDurability(teams[team_number].modules[module_number].getDurability());
		}
		else if (teams[team_number].modules[module_number].getType().equals("ar")){
			teams[team_number].robots[robot_number].modules[3].setQuality(teams[team_number].modules[module_number].getQuality());
			teams[team_number].robots[robot_number].modules[3].setDurability(teams[team_number].modules[module_number].getDurability());
		}		
		teams[team_number].modules[module_number].setType(" ");
		teams[team_number].modules[module_number].setQuality(0);
		teams[team_number].modules[module_number].setDurability(0);	
	}
	
	public void SellRobot(int team_number){ //method for "sl r()" command
		for(int i=0; i<4; i++){
			teams[team_number].setCredit(teams[team_number].getCredit() + (0.5 * NewPrice(teams[team_number].robots[robot_number].modules[i].getType(), teams[team_number].robots[robot_number].modules[i].getQuality()) * (teams[team_number].robots[robot_number].modules[i].getDurability()/100.0)));	
			teams[team_number].robots[robot_number].modules[i].setQuality(0);
			teams[team_number].robots[robot_number].modules[i].setDurability(0);
		}
	}
	
	public void DivideRobot(int team_number, int empty_module_number ){ //method for "-- r()" command

		for(int i=0; i<4; i++){
		teams[team_number].modules[empty_module_number].setType(teams[team_number].robots[robot_number].modules[i].getType());
		teams[team_number].modules[empty_module_number].setQuality(teams[team_number].robots[robot_number].modules[i].getQuality());
		teams[team_number].modules[empty_module_number].setDurability(teams[team_number].robots[robot_number].modules[i].getDurability());
		teams[team_number].robots[robot_number].modules[i].setQuality(0);
		teams[team_number].robots[robot_number].modules[i].setDurability(0);
		teams[team_number].robots[robot_number].modules[i].setType(" ");	
		}		
	}
	
	public void ChangeModule(int team_number){ //method for "ch" command
		int a=-1;
		int durability_;
		int quality_;
		
		if(teams[team_number].modules[module_number].getType().equals("tr")) a=0;
		else if(teams[team_number].modules[module_number].getType().equals("hd")) a=1;
		else if(teams[team_number].modules[module_number].getType().equals("lg")) a=2;
		else if(teams[team_number].modules[module_number].getType().equals("ar")) a=3;

		
		durability_= teams[team_number].modules[module_number].getDurability();
		quality_ = teams[team_number].modules[module_number].getQuality();		
		
		teams[team_number].modules[module_number].setDurability(teams[team_number].robots[robot_number].modules[a].getDurability());
		teams[team_number].modules[module_number].setQuality(teams[team_number].robots[robot_number].modules[a].getQuality());
		
		teams[team_number].robots[robot_number].modules[a].setDurability(durability_);
		teams[team_number].robots[robot_number].modules[a].setQuality(quality_);	
	}
	
	public void ListRobots(int which_team){ //listing a team's robots / method for "ls" command
	cn.getTextWindow().output("\n--- Team" + (which_team +1)+": Robots ---");
	DisplayRobots(which_team);
	cn.readLine();
	}
	
	public void RegisterRobots(int team_number, int game_number, int which_robot){ //registering a robot to the game / method for "rg" command
		tournament.games[game_number].participant_teams[team_number].team_participation = true;
		tournament.games[game_number].participant_teams[team_number].participant_robots[robot_number].robots_participation = true;
		tournament.games[game_number].participant_teams[team_number].participant_robots[robot_number].turn = which_robot;
		
	}
	
	public void PlayGames(){ //method for "pl" command
		
		for(int i=0; i<4; i++){
			for(int j=0; j<6; j++){
				if(tournament.games[i].participant_teams[j].team_participation == true)	tournament.games[i].count_team++;
			}
		}
		
			
		for(int i=0; i<4; i++){		
			for(int j=0; j<6; j++){				
				if(tournament.games[i].participant_teams[j].team_participation == true){
					for(int k=0; k<9; k++){
						if(tournament.games[i].participant_teams[j].participant_robots[k].robots_participation == true){							
							if(i==0) tournament.games[0].participant_teams[j].teamscore += teams[j].robots[k].ScoreRoboChess()/(Math.pow(tournament.games[0].participant_teams[j].participant_robots[k].turn, 2));
							else if(i==1) tournament.games[1].participant_teams[j].teamscore += teams[j].robots[k].ScoreRoboRun()/(Math.pow(tournament.games[1].participant_teams[j].participant_robots[k].turn, 2));
							else if(i==2) tournament.games[2].participant_teams[j].teamscore += teams[j].robots[k].ScoreRoboSumo()/(Math.pow(tournament.games[2].participant_teams[j].participant_robots[k].turn, 2));
							else if(i==3) tournament.games[3].participant_teams[j].teamscore += teams[j].robots[k].ScoreRoboPingPong()/(Math.pow(tournament.games[3].participant_teams[j].participant_robots[k].turn, 2));
						}					
					}	
				}				
			}
		}
		
		for(int i=0; i<4; i++){
			tournament.games[i].prize = tournament.games[i].prize + (tournament.games[i].count_team * 25);
			for(int j=0; j<6; j++){
				if(tournament.games[i].participant_teams[j].team_participation == true){
						
					double random = (Math.random() * (1.050 - 0.950)) + 0.950;
					tournament.games[i].participant_teams[j].team_random = random;
					tournament.games[i].participant_teams[j].teamscore = tournament.games[i].participant_teams[j].teamscore * random;
				}
				
			}
		}
		
		for(int i=0; i<4; i++){
			if(tournament.games[i].count_team != 0){
				for(int j=0; j<6; j++){
					if(tournament.games[i].participant_teams[j].team_participation == true){
						if(tournament.games[i].participant_teams[j].teamscore > tournament.games[i].winner_score){
							tournament.games[i].winner_score = tournament.games[i].participant_teams[j].teamscore;
							tournament.games[i].winner_team = j;
						}
					}
				}
				teams[tournament.games[i].winner_team].setCredit(teams[tournament.games[i].winner_team].getCredit() + tournament.games[i].prize);
			}			
		}
		
		week++;		
		DisplayGameResults();
		CalculateWeekPeriod();
		cn.readLine();
		UpdateDurabilities();
		Reset();
	}
	
	public void CalculateWeekPeriod(){ //if a team participates in min. 2 different game types in a 3-week period, it gets additional 150 credits
		
		for(int i=0; i<4; i++){
			for(int j=0; j<6; j++){
				if(tournament.games[i].participant_teams[j].team_participation) tournament.games[i].participant_teams[j].weekperiod = true;
			}
		}
		
		if((week - 1) % 3 == 0){
			cn.getTextWindow().output("\n");
			for(int j=0; j<6; j++){
				int countgameperiod=0;
				for(int i=0; i<4; i++){
					if(tournament.games[i].participant_teams[j].weekperiod == true) countgameperiod++;
				}
				
				if(countgameperiod>=2){
					teams[j].setCredit(teams[j].getCredit() + 150);
					cn.getTextWindow().output("\nTeam " + (j+1) + " got additional 150 credits");
				}
			}
			
		}

	}
	
	public void DisplayGameResults(){ //printing game results on the screen
		cn.getTextWindow().output("\n---Games (Results)---");
		for(int i=0; i<4; i++){
			if(i==0) cn.getTextWindow().output("\n\n---RoboChess: " + tournament.games[i].prize + " (" + tournament.games[i].count_team + " teams)");
			else if(i==1) cn.getTextWindow().output("\n\n---RoboRun: "+ tournament.games[i].prize + " (" + tournament.games[i].count_team + " teams)");
			else if(i==2) cn.getTextWindow().output("\n\n---RoboSumo: "+ tournament.games[i].prize + " (" + tournament.games[i].count_team + " teams)");
			else if(i==3) cn.getTextWindow().output("\n\n---RoboPingPong: "+ tournament.games[i].prize + " (" + tournament.games[i].count_team + " teams)");			
			for(int j=0; j<6; j++){				
				if(tournament.games[i].participant_teams[j].team_participation == true){
					for(int m=1; m<10; m++){
						for(int k=0; k<9; k++){
							if(tournament.games[i].participant_teams[j].participant_robots[k].robots_participation == true && tournament.games[i].participant_teams[j].participant_robots[k].turn == m){
								cn.getTextWindow().output("\nt" + (j+1) + "-r" + (k+1) + ":" + teams[j].PrintRobots(k, 0) + " " + teams[j].PrintRobots(k, 1)
															+ " " + teams[j].PrintRobots(k, 2)+ " " + teams[j].PrintRobots(k, 3) + teams[j].PrintRobotsScores(j, k));							
							}
						}										
					}				
				}				
			}
			
			for(int j=0; j<6; j++){				
				if(tournament.games[i].participant_teams[j].team_participation == true){
					String str = " (";
					cn.getTextWindow().output("\nt" + (j+1) + " score=");
					for(int m=1; m<10; m++){
						for(int k=0; k<9; k++){
							if(tournament.games[i].participant_teams[j].participant_robots[k].robots_participation == true && tournament.games[i].participant_teams[j].participant_robots[k].turn == m){								
								if(i==0){
									if(m==1) str += (int) teams[j].robots[k].ScoreRoboChess();
									else if(str.equals(" (")) str += (int) teams[j].robots[k].ScoreRoboChess() + "/" + (int) (Math.pow(2, tournament.games[0].participant_teams[j].participant_robots[k].turn));
									else str += " + " + (int) teams[j].robots[k].ScoreRoboChess() + "/" + (int) (Math.pow(2, tournament.games[0].participant_teams[j].participant_robots[k].turn));
								}
								else if(i==1){
									if(m==1) str += (int) teams[j].robots[k].ScoreRoboRun();
									else if(str.equals(" (")) str += (int) teams[j].robots[k].ScoreRoboRun() + "/" + (int) (Math.pow(2, tournament.games[1].participant_teams[j].participant_robots[k].turn));
									else str += " + " + (int) teams[j].robots[k].ScoreRoboRun() + "/" + (int) (Math.pow(2, tournament.games[1].participant_teams[j].participant_robots[k].turn));									
								}
								else if(i==2){
									if(m==1) str += (int) teams[j].robots[k].ScoreRoboSumo();
									else if(str.equals(" (")) str += (int) teams[j].robots[k].ScoreRoboSumo() + "/" + (int)(Math.pow(2, tournament.games[2].participant_teams[j].participant_robots[k].turn));
									else str += " + " +(int)teams[j].robots[k].ScoreRoboSumo() + "/" + (int) (Math.pow(2, tournament.games[2].participant_teams[j].participant_robots[k].turn));
								}
								else if(i==3){
									if(m==1) str += (int) teams[j].robots[k].ScoreRoboPingPong();
									else if(str.equals(" (")) str += (int) teams[j].robots[k].ScoreRoboPingPong() + "/" + (int) (Math.pow(2, tournament.games[3].participant_teams[j].participant_robots[k].turn));
									else str += " + " +(int)teams[j].robots[k].ScoreRoboPingPong() + "/" + (int)(Math.pow(2, tournament.games[3].participant_teams[j].participant_robots[k].turn));									
								}
							}
							
						}
					}
					String random_ = "" + tournament.games[i].participant_teams[j].team_random;
					String writingscore = "" + tournament.games[i].participant_teams[j].teamscore;
					str += ")" + " * " + random_.substring(0, 5) + " = " + writingscore.substring(0, 8);
					cn.getTextWindow().output(str);				
				}				
			}
			if(tournament.games[i].count_team == 0){
				cn.getTextWindow().output("\nWinner: No winner. Prize transferred to the next week");
				if(i==0) tournament.games[0].prize = 200 + tournament.games[0].prize;
				else if(i==1) tournament.games[1].prize = 200 + tournament.games[1].prize;
				else if(i==2) tournament.games[2].prize = 250 + tournament.games[2].prize;
				else if(i==3) tournament.games[3].prize = 250 + tournament.games[3].prize;
			}
			else{
				cn.getTextWindow().output("\nWinner: Team" + (tournament.games[i].winner_team + 1));
				if(i==0) tournament.games[0].prize = 200;
				else if(i==1) tournament.games[1].prize = 200;
				else if(i==2) tournament.games[2].prize = 250;
				else if(i==3) tournament.games[3].prize = 250;
			}
		}		
	}
	
	public void Reset(){ //renewal of games at the end of the week
		
		for(int i=0; i<4; i++){
			tournament.games[i].winner_score = 0;
			tournament.games[i].count_team = 0;
				for(int j=0; j<6; j++){
					tournament.games[i].participant_teams[j].teamscore = 0;
					tournament.games[i].participant_teams[j].team_participation = false;
					tournament.games[i].participant_teams[j].teamturn = 1;
					for(int k=0; k<9; k++){
						tournament.games[i].participant_teams[j].participant_robots[k].robots_participation = false;
						tournament.games[i].participant_teams[j].participant_robots[k].turn = 0;					
				}
			}
		}
		
		if((week - 1) % 3 == 0){
			for(int i=0; i<4; i++){
				for(int j=0; j<6; j++){
					tournament.games[i].participant_teams[j].weekperiod = false;
				}
			}
			
		}
	}
	
	public void UpdateDurabilities(){ //deacreasing the durability of modules
		
		for(int i=0; i<6; i++){
			for(int j=0; j<20; j++){
				if(teams[i].modules[j].getQuality() != 0 && teams[i].modules[j].getDurability() !=0) teams[i].modules[j].setDurability(teams[i].modules[j].getDurability() - 2);						
				}
			
			for(int k=0; k<9; k++){
				int which_robot = teams[i].WhichRobot(k);
				if (which_robot !=-1){
					for(int module=0; module<4; module++){
						if(teams[i].robots[k].modules[module].getDurability() != 0) teams[i].robots[k].modules[module].setDurability(teams[i].robots[k].modules[module].getDurability() - 2);						
					}
				}						
			}		
		}
		
		for(int i=0; i<4; i++){ //extra 2 units for every game participated
			for(int j=0; j<6; j++){
				for(int k=0; k<9; k++){					
					if(tournament.games[i].participant_teams[j].participant_robots[k].robots_participation == true){
						for(int module=0; module<4; module++){
							if(teams[j].robots[k].modules[module].getDurability() != 0) teams[j].robots[k].modules[module].setDurability(teams[j].robots[k].modules[module].getDurability() - 2);
						}
					}
				}
			}
		}
	}
	
	
	
	public void Command(int team_number){
		
		cn.getTextWindow().output("\n \nCommand > ");
		
		if(team_number != 0) command = ArtificialIntelligence.getCommand(team_number); //if it is not first team's turn, artificial intelligence plays
		else command=cn.readLine(); //otherwise; taking comand
		
		command = command.toLowerCase();
		command = command.trim();		
		String [] words = command.split(" ");
		
		boolean flag = true;
		
		//"by" command
		if(words[0].equals("by") && (words[1].substring(0, 2).equals("hd") || words[1].substring(0, 2).equals("tr") || words[1].substring(0, 2).equals("lg") || words[1].substring(0, 2).equals("ar")) && words[1].length() == 3 &&
				(words[1].charAt(2) == '1' || words[1].charAt(2) == '2' || words[1].charAt(2) == '3' || words[1].charAt(2) == '4' || words[1].charAt(2) == '5' || words[1].charAt(2) == '6') && 
				words.length == 2 && words[1].length() == 3){
			
				command_type = words[1].substring(0, 2);
				command_quality = Integer.parseInt(words[1].substring(2));
				if(teams[team_number].getCredit() >= NewPrice(command_type, command_quality)) Buy(team_number); //is the team's credit enough?
				else flag=false;
		}
		//"sl" command
		else if (words[0].equals("sl") && words.length == 2 ){			
			if(words[1].charAt(0) == 'm' && words[1].length() == 3){ //sell module
				module_number = Integer.parseInt(words[1].substring(1)) -1;
				if(module_number>=0 && module_number<20){
				SellModule(team_number);
				}
				else flag=false;				
			}
			else if(words[1].charAt(0) == 'r' && words[1].length() == 2){ //sell robot
				robot_number = Integer.parseInt(words[1].substring(1)) - 1;				
				if(robot_number>=0 && robot_number<9){
				SellRobot(team_number);
				}
				else flag=false;
			}			
			else flag=false;			
		}
		
		//"++" command
		else if(words[0].equals("++") && words.length == 7 && words[2].equals("=") && words[1].length() == 2 && words[3].length()==3 && words[4].length()==3 && words[5].length()==3 && words[1].charAt(0) == 'r'){
				robot_number = Integer.parseInt(words[1].substring(1)) -1;
				if(robot_number>=0 && robot_number<9 ){
					boolean[] control = new boolean[4];
					boolean control_inventory = true;
					for(int i=0; i<4; i++) control[i]=false;
					
					for(int i=3; i<7; i++){
						if(words[i].substring(0, 1).equals("m") && module_number>=0 && module_number<20){ //when a team wants to build a robot with entering the number of the modules
							module_number= Integer.parseInt(words[i].substring(1)) -1;
							if(teams[team_number].modules[module_number].getType().equals("tr")) control[0] = true;
							else if (teams[team_number].modules[module_number].getType().equals("hd")) control[1] = true;
							else if (teams[team_number].modules[module_number].getType().equals("lg")) control[2] = true;
							else if (teams[team_number].modules[module_number].getType().equals("ar")) control[3] = true;							
						}
						else{ //when a team wants to build a robot with the newest modules 
							command_type = words[i].substring(0, 2);
							command_quality = Integer.parseInt(words[i].substring(2));
							if(command_type.equals("tr")) control[0] = true;
							else if(command_type.equals("hd")) control[1] = true;
							else if(command_type.equals("lg")) control[2] = true;
							else if(command_type.equals("ar")) control[3] = true;
							
							for(int j=0; j<20; j++){
								if(teams[team_number].modules[j].getType().equals(command_type) && teams[team_number].modules[j].getQuality() == command_quality && control_inventory == true) break;						
								else if(j==19) control_inventory=false;
							}
						}
					}
					//is there 4 different type for building a robot?
					if(control[0] == true && control[1] == true && control[2] == true && control[3] == true && teams[team_number].robots[robot_number].modules[0].getQuality() == 0 && control_inventory==true){
						for(int i=3; i<7; i++){
							if(words[i].substring(0, 1).equals("m")) {
								module_number= Integer.parseInt(words[i].substring(1)) -1;
								BuildNewRobotUsingModule(team_number);	
							}
							else {
								command_type = words[i].substring(0, 2);
								command_quality = Integer.parseInt(words[i].substring(2));
								BuildNewRobotNewestModule(team_number);						
							}
						}				
					}
					else flag=false;
				}
				else flag=false;				
		}	
		
		//"--" command
		else if(words[0].equals("--") && words.length == 2 && words[1].length() == 2){
			robot_number = Integer.parseInt(words[1].substring(1)) -1;
			int empty_module_number = teams[team_number].FindEmptyModule();
			int numberofemptymodules = teams[team_number].EmptyModulesNumbers();
			
			
			if(robot_number>=0 && robot_number<9 && teams[team_number].robots[robot_number].modules[0].getQuality() != 0 && empty_module_number<20 && numberofemptymodules >=4) DivideRobot(team_number, empty_module_number );
			else flag=false;		
				
		}
		
		//"ch" command
		else if(words[0].equals("ch") &&  words.length == 3 && words[1].length() == 2 && words[2].length() == 3){
			robot_number = Integer.parseInt(words[1].substring(1)) - 1;
			module_number= Integer.parseInt(words[2].substring(1)) - 1;
			if(words[1].charAt(0) == 'r' && words[2].charAt(0) == 'm' && robot_number>=0 && robot_number<9 && module_number>=0 && module_number<20){
				for(int i=0; i<4; i++){
					if (teams[team_number].robots[robot_number].modules[i].getQuality() == 0 || teams[team_number].modules[module_number].getQuality()==0) flag = false;
				}
				if(flag == true) ChangeModule(team_number);
			}
			else flag=false;
		}
		
		//"ls" command
		else if (words[0].equals("ls") && words.length == 2 && words[1].length() == 1){
			int which_team = Integer.parseInt(words[1])-1;
			if(which_team>=0 && which_team<6) ListRobots(which_team);
			else flag=false;
		}
		
		//"rg" command
		else if(words[0].equals("rg") && words.length == 4 && words[1].length() == 2 && words[3].length() == 2 && words[2].equals(">")){
			robot_number = Integer.parseInt(words[1].substring(1)) -1;			
			char game = words[3].charAt(0);
				int game_number=0;
				if(game == 'c') game_number = 0;
				else if(game == 'r') game_number = 1;
				else if(game == 's') game_number = 2;
				else if(game == 'p') game_number = 3;
				else flag=false;
			int which_robot = Integer.parseInt(words[3].substring(1));
			
			if(robot_number>=0 && robot_number<9 && which_robot>=1 && which_robot<=9){ //is there any other robot from the same team that participating in the game with the same turn
				for(int i=0; i<9; i++) if(tournament.games[game_number].participant_teams[team_number].participant_robots[i].turn == which_robot) flag = false;				
				if(teams[team_number].robots[robot_number].modules[0].getDurability()>=60 && teams[team_number].robots[robot_number].modules[1].getDurability()>=60 && teams[team_number].robots[robot_number].modules[2].getDurability()>=60 && teams[team_number].robots[robot_number].modules[3].getDurability()>=60 && 
					(tournament.games[0].participant_teams[team_number].participant_robots[robot_number].robots_participation != true &&  tournament.games[1].participant_teams[team_number].participant_robots[robot_number].robots_participation != true && 
					tournament.games[2].participant_teams[team_number].participant_robots[robot_number].robots_participation != true && tournament.games[3].participant_teams[team_number].participant_robots[robot_number].robots_participation != true) && flag == true){				
						RegisterRobots(team_number, game_number, which_robot);		
				}
				else flag=false;
			}
			else flag=false;
		}
		
		//"pl" command
		else if(words[0].equals("pl") && words.length == 1){
			if(team_number == 0) PlayGames(); //if it is not first team's turn, a team's turn passes 
		}
		
		else flag=false;
		
		if(flag==false && team_number == 0){
			cn.getTextWindow().output("\nerror");	
			command=cn.readLine();
		}
	}

	
	public void ClearScreen(){
		for(int i=-20; i < 150; i++){
			cn.getTextWindow().setCursorPosition(0, i); cn.getTextWindow().output("                                                                                          ");
		}
	}

}
