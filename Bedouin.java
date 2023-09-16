/** This class has the code of the Bedouin, one of the three "bad guys." The bedouin can poison, 
 * and its unique methods include: stealCamel(). The bedouin can only come out at night.
 * @author asseel
 *
 */
public class Bedouin extends Being implements Poisoner {

	// constructor
	public Bedouin (int hp, int strength) {
		super(hp, strength); // assigns hp and strength
	}

	String[] bedouinTalk = {"You are going to die!", "Tootface", "Beat that!", "You should just give up, I'm going to win!", "You have no chance, foreigner.", "You're trash", "Put up some fight at least...", "Weakling!!! MWAHAHAH", "Are you even trying?", "Just give up now!", "You are no competition for me...", "That was weak. Lol", "You have no chance against me!", "It's harder fighting a baby", "HAHAH UR SO BAD", "Wow... you're weak", "At least try", "Nah are you serious right now? Ur trash", "Loser", "Do you even lift bro"};

	/** This method is designed for a bedouin to poison another being, in this case the player
	 * @param b: being which will be poisoned
	 */
	public void poison(Being b) {
		b.hp -= (int)(.3 * strength); // poison damage will be 1/3 of the bedouin's strength
		
		if (b.hp < 0) { // if the being has hp less than zero
			b.hp = 0; // set being's hp to zero (as it is the min)
		}
		
		if (hp < 0) { // if the bedouin has hp less than zero
			hp = 0; // set bedouin's hp to zero (as it is the min)
		}
		
		System.out.println("\nThe bedouin brings out his potion and POISONS YOU! \nHe says '" + talk(bedouinTalk) + "'\nYour hp: " + b.hp + "\nBedouin's hp: " + hp);
	}

	/** This method is designed for a bedouin to steal a tourist's camel
	 * @param t: Tourist (player) to access its camel
	 */
	public void stealCamel(Tourist t) {
		int r = (int) (Math.random() * 5 + 1); // random number between 1 and 3
		if (r == 1 ) { // one fifth of a chance for a bedouin to steal your camel
			t.camel = null; // steal the camel
			System.out.println("Oh no! The bedouin stole your camel!! \nHe laughs and says '" + talk(bedouinTalk) + "'");
		}
	}


}
