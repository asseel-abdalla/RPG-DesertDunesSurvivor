/** This is the desert mummy class: the big boss! This is the last bad guy that the tourist (player)
 * will fight before reaching the goal and finding the tour group. Its unique method(s) include:
 * throwSand
 * @author asseel
 *
 */
public class DesertMummy extends Being implements Striker{
	private String [] mummyTalk = {"Bro I got sand on my side and you still losing" , "Ur TRASHHH" , "This mad easy" , "Aww you thought you were gonna meet up with your wee wee friends?" , "HAHAHAHA" , "Are you serious rn" , "Damn bro ur so weak" , "Did you get a paper cut?" , "Aww did you get a boo boo?" , "Just give up" , "Bro..." , "This is light work" , "Lol are you trying" , "This is so funny" , "Aww are you ok?" , "You're never going to beat me!" , "You good? I don't care" , "Weakling" , "You're never going to win", "You thought you had a chance??? HAHAHAH"};

	// constructor which receives hp and strength
	public DesertMummy(int hp, int strength) {
		super(hp, strength); // assigns accordingly
	}

	/** This method is designed for the desert mummy to throw sand and the tourist and their camel
	 * @param t: Tourist (player) which will be receiving damage
	 */
	public void throwSand (Tourist t) {
		t.hp -= 30; // the tourist will lose 30 hp
		if (t.camel != null) { // if the tourist has a camel
			t.camel.hp -= 15; // the camel will lose 15 hp
			System.out.println("OH NO!! The desert mummy hit you and made 15 damage!\nAfter he hits you with the biggest sand dune ever, he says '" + talk(mummyTalk) + "'"
					+ "\n\n\nYour hp: " + t.hp + "\nCamel's hp: " + t.camel.hp + "\nDesert's mummy hp: " + hp);
		}
		else {
			System.out.println("OH NO!! The desert mummy hit you and made 15 damage!\nAfter he hits you with the biggest sand dune ever, he says '" + talk(mummyTalk) + "'"
					+ "\n\n\nYour hp: " + t.hp + "\nDesert's mummy hp: " + hp);
		}
		

	}

	/** This method is implemented from the Striker interface. Strike(Being b) is designed to strike 
	 * another being. Not only can the mummy throw sand, but it can also strike – making it the big boss!
	 * @param b: the being which will be receiving damage (player)
	 */
	public void strike(Being b) {
		if (hp < 0) { // if the mummy's hp is negative
			hp = 0; // set to zero (as it is the min)
		}
		b.hp -= 15; // the being will lose 15 hp
		System.out.print("OH NO!! The desert mummy hit you and made 15 damage!\nHe laughs and says '" + talk(mummyTalk) + "'"
				+ "\n\nYour hp: " + b.hp + "\nDesert's mummy hp: " + hp);

	}

}
