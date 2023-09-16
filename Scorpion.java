/** This class has the code of the Scorpion, one of the three "bad guys." The scorpion is a being
 * as well as a poisoner
 * @author asseel
 *
 */
public class Scorpion extends Being implements Poisoner{
	private String[] scorpionTalk = {"Are you serious bro I'm literally a bug", "Ur trash", "You've got to be kidding", "Are you even trying?", "Am I actually going to poison you to death?", "LOSERRRR", "HAHAHAH UR TRASH", "Don't you feel embarrased?", "Are you even trying?", "You have no chance!", "Just give up, you'll never win", "Aww did you get a boo boo? Wah wah", "Up your game chief", "Do you even lift bro?", "That's embarrasing for you", "I can't believe you are going to lose to a bug", "You're so trash", "Are you kidding me bro?", "I'm going to beat you!", "Fart face"};
	
	// constructor which receives hp and strength 
	public Scorpion(int hp, int strength) {
		super(hp, strength); // assigns hp and strength
	}
	
	/** This method is designed to poison another being, in this case the Tourist (player)
	 * @param b: being who is getting poisoned
	 */
	public void poison(Being b) {
		if (hp < 0) { // if the scorpion has negative hp
			hp = 0; // set it to zero (as it is the min)
		}
		b.hp -= 5; // a poison removes 5 hp from the other being's health
		System.out.println("\nThe scorpion stings you with its tail and poisons you!!!" + "\nThe scorpion says to you '" + talk(scorpionTalk) + "'\nThat must've hurt. \nYour hp: " + b.hp + "\nScorpion's hp: " + hp);
	}

}
