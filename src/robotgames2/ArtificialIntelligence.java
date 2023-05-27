package robotgames2;

public class ArtificialIntelligence {

	static String getCommand(int team_number)
	{	String command = "";
		boolean flag = false;
	
		
		for(int z=1; z<8; z++){
			if(z==1){	
			
				for(int i=0; i<20; i++){ //is there any module that has a durability lower than 60?
					if(RobotGamesOrganization.teams[team_number].modules[i].getDurability()<60 && RobotGamesOrganization.teams[team_number].modules[i].getDurability()!=0){
						command = "sl m" + twodigits(i+1);
						flag = true;
						break;
					}				
				}			
			}
			
			else if(z==2){ //if a team has 4 different modules(tr/hd/lg/ar) in its inventory, a robot can be created
				boolean[] control_inventory = new boolean[4];
				boolean[] control_command = new boolean[4];
				for(int i=0; i<4; i++) control_inventory[i]=false;
				for(int i=0; i<4; i++) control_command[i]=false;
				
				for(int i=0; i<20; i++){ //is there 4 different types of modules?
					if(RobotGamesOrganization.teams[team_number].modules[i].getType().equals("tr")) control_inventory[0] = true;
					else if(RobotGamesOrganization.teams[team_number].modules[i].getType().equals("hd")) control_inventory[1] = true;
					else if(RobotGamesOrganization.teams[team_number].modules[i].getType().equals("lg")) control_inventory[2] = true;
					else if(RobotGamesOrganization.teams[team_number].modules[i].getType().equals("ar")) control_inventory[3] = true;
				}
				
				if(control_inventory[0] == true && control_inventory[1] == true && control_inventory[2] == true && control_inventory[3] == true && RobotGamesOrganization.teams[team_number].RobotNumber() != 9){
					for(int i=0; i<9; i++){
						if(RobotGamesOrganization.teams[team_number].WhichRobot(i) == -1){
							command = "++ r" + (i+1) + " =";
							for(int m=0; m<20; m++){
								for(int j=6; j>0; j--){
									if(RobotGamesOrganization.teams[team_number].modules[m].getType().equals("tr") && RobotGamesOrganization.teams[team_number].modules[m].getQuality() == j && control_command[0] == false){
										command += " tr" + j;
										control_command[0] = true;
									}
									else if(RobotGamesOrganization.teams[team_number].modules[m].getType().equals("hd") && RobotGamesOrganization.teams[team_number].modules[m].getQuality() == j && control_command[1] == false){
										command += " hd" + j;
										control_command[1] = true;
									}
									else if(RobotGamesOrganization.teams[team_number].modules[m].getType().equals("lg") && RobotGamesOrganization.teams[team_number].modules[m].getQuality() == j && control_command[2] == false){
										command += " lg" + j;
										control_command[2] = true;
									}
									else if(RobotGamesOrganization.teams[team_number].modules[m].getType().equals("ar") && RobotGamesOrganization.teams[team_number].modules[m].getQuality() == j && control_command[3] == false){
										command += " ar" + j;
										control_command[3] = true;
									}
									else if(control_command[0]==true && control_command[1]==true && control_command[2]==true && control_command[3]==true){
										flag = true;
										break;
									}						
								}
								if(flag==true) break;
							}
							if(flag==true)break;							
						}
					}
				}
			}
			
			else if(z==3){ //if a team hasn't 4 different modules(tr/hd/lg/ar) in its inventory, team gets that module
				boolean[] control_inventory = new boolean[4];
				for(int i=0; i<4; i++) control_inventory[i]=false;
				
				for(int i=0; i<20; i++){ //is there 4 different types of modules?
					if(RobotGamesOrganization.teams[team_number].modules[i].getType().equals("tr")) control_inventory[0] = true;
					else if(RobotGamesOrganization.teams[team_number].modules[i].getType().equals("hd")) control_inventory[1] = true;
					else if(RobotGamesOrganization.teams[team_number].modules[i].getType().equals("lg")) control_inventory[2] = true;
					else if(RobotGamesOrganization.teams[team_number].modules[i].getType().equals("ar")) control_inventory[3] = true;
				}
				
				if(RobotGamesOrganization.teams[team_number].EmptyModulesNumbers() != 0 && (RobotGamesOrganization.teams[team_number].getCredit()>200 || RobotGamesOrganization.teams[team_number].RobotNumber() == 0) && 
						(control_inventory[0] == false || control_inventory[1] == false || control_inventory[2] == false || control_inventory[3] == false)){
					int random;
					if(RobotGamesOrganization.teams[team_number].getCredit()<=1000) random = (int)(Math.random() * 1) + 1;
					else if (RobotGamesOrganization.teams[team_number].getCredit()>1000 && RobotGamesOrganization.teams[team_number].getCredit()<=2000) random = (int)(Math.random() * 1) + 3;
					else random = (int)(Math.random() * 1) + 5;
					
					if(control_inventory[0] == false && RobotGamesOrganization.teams[team_number].getCredit() >= RobotGamesOrganization.NewPrice("tr", random)){
						command="by tr" + random;
						flag = true;
					}
					else if(control_inventory[1] == false && RobotGamesOrganization.teams[team_number].getCredit() >= RobotGamesOrganization.NewPrice("hd", random)){
						command="by hd" + random;
						flag = true;
					}
					else if(control_inventory[2] == false && RobotGamesOrganization.teams[team_number].getCredit() >= RobotGamesOrganization.NewPrice("lg", random)){
						command="by lg" + random;
						flag = true;
					}
					else if(control_inventory[3] == false && RobotGamesOrganization.teams[team_number].getCredit() >= RobotGamesOrganization.NewPrice("ar", random)){
						command="by ar" + random;
						flag = true;
					}
				}
			}
			
			else if(z==4){ //is there any module in robot that has a durability lower than 60?
				if(RobotGamesOrganization.teams[team_number].RobotNumber() != 0){
					for(int i=0; i<9; i++){
						for(int j=0; j<4; j++){
							if(i == RobotGamesOrganization.teams[team_number].WhichRobot(i) && (RobotGamesOrganization.teams[team_number].robots[i].modules[0].getDurability()<60 ||
								RobotGamesOrganization.teams[team_number].robots[i].modules[1].getDurability()<60 || RobotGamesOrganization.teams[team_number].robots[i].modules[2].getDurability()<60 ||
								RobotGamesOrganization.teams[team_number].robots[i].modules[3].getDurability()<60)){
								command = "sl r" + (i+1);
								flag = true;
								break;							
							}							
						}
						if(flag == true) break;
					}
				}				
			}
			
			else if(z==5){ //registering game
				if(RobotGamesOrganization.teams[team_number].RobotNumber() != 0 && RobotGamesOrganization.teams[team_number].RobotNumber() != RegisterRobotNumber(team_number)){
					
					for(int i=0; i<9; i++){
						if(i == RobotGamesOrganization.teams[team_number].WhichRobot(i) && RobotGamesOrganization.tournament.games[0].participant_teams[team_number].participant_robots[i].robots_participation == false && RobotGamesOrganization.tournament.games[1].participant_teams[team_number].participant_robots[i].robots_participation == false &&
								RobotGamesOrganization.tournament.games[2].participant_teams[team_number].participant_robots[i].robots_participation == false && RobotGamesOrganization.tournament.games[3].participant_teams[team_number].participant_robots[i].robots_participation == false){

							int type= (int) (Math.random() * 5) + 0;				

							if(type == 0){
								command = "rg r" + (i+1) + " > c" + RobotGamesOrganization.tournament.games[0].participant_teams[team_number].teamturn;
								command = "rg r" + (i+1) + " > c" + RobotGamesOrganization.tournament.games[0].participant_teams[team_number].teamturn++;
								flag = true;
								break;
							}
							else if(type == 1){
								command = "rg r" + (i+1) + " > r" + RobotGamesOrganization.tournament.games[1].participant_teams[team_number].teamturn;
								RobotGamesOrganization.tournament.games[1].participant_teams[team_number].teamturn++;
								flag = true;
								break;
							}
							else if(type == 2){
								command = "rg r" + (i+1) + " > s" + RobotGamesOrganization.tournament.games[2].participant_teams[team_number].teamturn;
								RobotGamesOrganization.tournament.games[2].participant_teams[team_number].teamturn++;
								flag = true;
								break;
							}
							else if(type == 3){
								command = "rg r" + (i+1) + " > p" + RobotGamesOrganization.tournament.games[3].participant_teams[team_number].teamturn;
								RobotGamesOrganization.tournament.games[3].participant_teams[team_number].teamturn++;
								flag = true;
								break;
							}						
						}						
					}
				}
			}
			
			else if(z==6){ //if the inventory is full, the team sells the module that has least quality
				if(RobotGamesOrganization.teams[team_number].EmptyModulesNumbers() == 0 || RobotGamesOrganization.teams[team_number].getCredit() <= 100 && RobotGamesOrganization.teams[team_number].RobotNumber() == 0 && 
						RobotGamesOrganization.teams[team_number].RobotNumber() < 3){
					for(int i=1; i<=6; i++){
						for(int j=0; j<20; j++){
							if(RobotGamesOrganization.teams[team_number].modules[j].getQuality()==i){
								command = "sl m" + twodigits(j+1);
								flag = true;
								break;
							}
						}
						if(flag == true) break;
					}
				}
			}		
			
			
			else if(z==7){
				command = "pl";
			}			
			if(flag == true) break;			
		}		
		return command;
	}

	
	public static int RegisterRobotNumber(int team_number){
		int count=0;
		
		for(int i=0; i<4; i++){
				for(int k=0; k<9; k++){
					if(RobotGamesOrganization.tournament.games[i].participant_teams[team_number].participant_robots[k].robots_participation == true) count++;					
				}
			}				
		return count;		
	}
	
	public static String twodigits(int input){ //to write modules in type 2 digits 
		String str = input + "";
		
		if(str.length() == 1)
			return "0" + str;
		
		return str;
	}
}

