/** This is an interface which forces any class which implements Striker to write the code of the strike
 * method. The fighter class will implement Striker so the camel, tourist and desert mummy all have the 
 * strike method. 
 * @author asseel
 *
 */
public interface Striker {
	public void strike (Being b); // being will call it and strike another being - parameter of other being
}
