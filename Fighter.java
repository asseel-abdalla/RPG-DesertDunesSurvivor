/** This is the fighter class which implements the striker interface. Both the camel and tourist
 * (player) are fighters. Its unique methods include: consume, rest, move; and its unique field
 * include: hunger.
 * @author asseel
 *
 */
public class Fighter extends Being implements Striker{
	String[] fighterTalk = {"I'm too good", "Ur trash", "You've got to be kidding", "Are you even trying?", "I'm going to win!", "LOSERRRR", "HAHAHAH UR TRASH", "Don't you feel embarrased?", "How does it feel to lose?", "You have no chance!", "Just give up, you'll never win", "Aww did you get a boo boo? Wah wah", "Up your game chief", "Do you even lift bro?", "That's embarrasing for you", "I can't believe you are going to lose to a bug", "You're so trash", "Are you kidding me bro?", "I'm going to beat you!", "Fart face"};

	// constructor which receives hp and strength
	public Fighter (int hp, int strength) {
		super(hp, strength); // assigns accordingly
	}
	
	/** This method is designed for a fighter to strike a being
	 */
	public void strike(Being b) {
		b.hp -= 15; // subtract 15 from the other being's help after getting striked
	}
	
	/** This method is designed to choose a random line of trash talk that a fighter can say
	 * @return the line of trash talk
	 */
	public String talk() {
		int r = (int) (Math.random() * (20));
		return fighterTalk[r];
	}
	
}
