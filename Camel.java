/** This class is the Camel class which extends fighter (and fighter extends being). This is the ally of
 * the Tourist (player), which can help the tourist fight against the bad guys. Its unique field(s) 
 * include: sleepLevel – which depicts whether or not the camel can help fight
 * @author asseel
 *
 */
public class Camel extends Fighter{
	private int sleepLevel;
	
	// constructor to set hp, strength and sleepLevel
	public Camel(int hp, int strength, int sleepLevel) {
		super(hp, strength);
		this.sleepLevel = sleepLevel;
	}
	
	/** This method is designed for a camel to kick a different being (bedouin, scorpion or desert 
	 * mummy)
	 * @param b: being which will be kicked, losing 20 hp per kick
	 * @return string updating if the camel hit the being or not
	 */
	public void kick(Being b, Tourist t) {
		if (sleepLevel >= 30) { // if the camel is not tired, it can kick the being
			b.hp -= 20; // update the being's hp levels, subtracting 20 hp
			sleepLevel -= 15; // subtract 10p from the camel's sleepLevel because the kick is tiring for them 
			
			if (b.hp < 0) { // if the being being kicked has an hp of less than zero
				b.hp = 0; // set the being's hp to zero (as it is the min)
			}
			
			String beingName = b.getClass().getCanonicalName();
			
			if (beingName.equals("DesertMummy")) { // if it is the desert mummy
				beingName = beingName.substring(0, 6) + " " + beingName.substring(6); // format DesertMummy to Desert Mummy
			}
			
			System.out.println("Your camel came in and kicked the " + beingName + "! You made 20 damage. \n"  + "\nYour hp: " + t.hp + "\n" +  beingName + "'s hp: " + b.hp); // return update
		}
		else { // else the camel is too tired – cannot kick being b
			System.out.println("Your camel is too tired! Let them rest.\n"); // return update
		}
	}

	// getter for sleep level of camel
	public int getSleepLevel() {
		return sleepLevel;
	}

	// setter for sleep level of camel
	public void setSleepLevel(int sleepLevel) {
		this.sleepLevel = sleepLevel;
	}
	
	
	
	
	

}
