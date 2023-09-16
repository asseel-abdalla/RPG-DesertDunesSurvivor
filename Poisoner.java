/** This is an interface which forces any class which implements Poisoner to write the code of the 
 * poison method. The Bedouin and Scorpion method will implement this interface.
 * @author asseel
 *
 */
public interface Poisoner {
	public void poison (Being b); // method which will poison the bedouin/scorpion's opponent
}
