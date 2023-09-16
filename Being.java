/** This class is the abstract Being class. It has 2 fields: hp and strength, and one method that 
 * each children will have: talk() for trash talking that will be used for fighting scenes. 
 * @author asseel
 *
 */
public abstract class Being {
	public int hp;
	public int strength;

	// Being constructor receiving hp and strength 
	public Being (int hp, int strength) {	
		this.hp = hp;
		this.strength = strength;
	}

	/** This method is designed to choose a random line of trash talk that a being can say
	 * @return the line of trash talk
	 */
	public String talk(String[] talk) { // method of talk which each being will have for trash talking after hitting its opponent
		int r = (int) (Math.random() * (20)); // finds random number to use as index of trash talk line in array of lines
		return talk[r];
	}; 

}
