package robotgames2;

public class Tournament { //all games
	Games [] games = new Games[4];
	
	public Tournament(){ //constructor of the Tournament class
		for(int i=0; i<games.length; i++)
			games[i] = new Games();
		
		games[0].prize = 200; //RoboChess
		games[1].prize = 200; //RoboRun
		games[2].prize = 250; //RoboSumo
		games[3].prize = 250; //RoboPingPong
	}	
	
	public class Games { 
		ParticipantTeams[] participant_teams = new ParticipantTeams[6];
		int prize;
		int count_team; //number of teams that participating in the game
		double winner_score; //a game's winner's score
		int winner_team; //a game's winning team
		
		public Games(){ //constructor of the Games class
			for(int i=0; i<participant_teams.length; i++)
				participant_teams[i] = new ParticipantTeams();
			count_team = 0;
			winner_score = 0;
						
		}
		
		public class ParticipantTeams {
			ParticipantRobots[] participant_robots = new ParticipantRobots[9];
			double teamscore; //a team's score on the one game
			boolean team_participation; //the participation of teams in the game
			double team_random; //random number
			boolean weekperiod; 
			int teamturn; //to determine which order a team will participate in the game (for AI)
			
			public ParticipantTeams(){ //constructor of the ParticipantTeams class
				for(int i=0; i<participant_robots.length; i++)
					participant_robots[i] = new ParticipantRobots();	
				
				team_participation = false;
				teamscore=0;
				weekperiod=false;
				teamturn=1;
				}		
			
			public class ParticipantRobots {
				boolean robots_participation;//the participation of robots in the game
				int turn; //which order the robot will participate in the game
				
				public ParticipantRobots(){ //constructor of the ParticipantRobots class
					robots_participation=false;
					turn=0;
					}
				}			
			}	
		}
	

}
