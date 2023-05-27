package robotgames2;

public class Module {
	
	private String type ; //module's type(hd/tr/lg/ar)
	private int quality ; //module's quality)
	private int durability; //module's durability
	
	public Module(String type_, int quality_){ //constructor of the Module class
		type= type_;
		quality = quality_;		
	}
	
	//get and set methods of attributes
	
	public String getType() { 
		return type; 
		}
	public void setType(String type) {
		this.type = type; 
		}
	public int getQuality() { 
		return quality;
		}
	public void setQuality(int quality) { 
		this.quality = quality;
		}
	public int getDurability() { 
		return durability; 
		}
	public void setDurability(int durability) { 
		this.durability = durability;
		}	
	


}
