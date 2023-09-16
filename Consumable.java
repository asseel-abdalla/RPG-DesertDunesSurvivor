/** This class consists of every consumable, or in other words the foods and drinks the player can 
 * consume to increase health and/or strength. Each different consumable will have a different 
 * increase of health and/or strength. This class does not implement or extend any class
 * @author asseel
 *
 */
public class Consumable {
	private String name;
	private Tourist owner; 
	private int strengthBoost;
	private int hpBoost;
	private int price;

	// consumable constructor which receives the tourist owner, name of the consumable, quantity, strength boost of any, hp boost if any, and price
	public Consumable (Tourist owner, String name, int strengthBoost, int hpBoost, int price) {
		this.owner = owner;
		this.name = name;
		this.strengthBoost = strengthBoost;
		this.hpBoost = hpBoost;
		this.price = price;
	}

	/** This method is designed for the Tourist (player) to consume the consumable
	 * @return String updating player's stats 
	 */
	public String consume () {
		if (owner.backpack.getOrDefault(this, 0) - 1 >= 0) { // if the tourist has the consumable they would like to consume
			owner.hp += hpBoost; // add the hp boost (if applicable)
			owner.strength += strengthBoost; // add the strength boost (if applicable)
			owner.backpack.replace(this,owner.backpack.get(this) - 1); // decrease the quantity

			String result = "You've consumed " + name + ".";
			
			if (hpBoost > 0) { // if the consumable provided an hp boost
				result += " You gained " + hpBoost + " hp."; // update result
			}
		
			if (strengthBoost > 0) { // if the consumable provided a strength boost
				result += " You gained " + strengthBoost + " strength."; // update result
			}
	
			if (owner.hp > 100) { // if the hp boost has made the tourist have hp greater than 100
				owner.hp = 100; // set it to 100 (as it is the max)
			}
			
			if (owner.strength > 100) { // if the strength boost has made the tourist have a strength level greater than 100
				owner.strength = 100; // set it to 100 (as it is the max)
			}
			
			return result;
		}
		else { // else the tourist does not have enough of the consumable they would like to consume
			return "\nYou do not have enough quantity to consume " + name + ".";
		}
		
	}
	
	// getter for name 
	public String getName () {
		return name;
	}
	
	// getter for price
	public int getPrice() {
		return price;
	}

	// setter for owner of consumable
	public void setOwner(Tourist owner) {
		this.owner = owner;
	}
	
	


}
