import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/** Welcome to my RPG! This is the driver class which brings all these classes together to create the 
 * Desert Dunes Survivor game! You are a tourist looking to survive the desert after losing your safari group
 * @author asseel
 *
 */
public class DesertDunesDriver{	
	public static void main(String[] args) {
		boolean playAgain = true;

		//start a new game and play again if user accepts prompt
		do { 
			String[][] map = setup(); //set up map 
			Tourist player = new Tourist (100, 0); // initialize tourist (player)
			player.camel = new Camel (100, 20, 100); // initialize tourist's camel
			play(player, map);
			playAgain = promptPlayAgain();
		} 
		while(playAgain);

	}

	/** This method is designed to setup the map whilst creating a map key
	 * @return map with correct setup for start of a game
	 */
	public static String[][] setup() {
		System.out.println("WELCOME TO ASSEEL'S RPG: DESERT DUNES SURVIVOR! \nYou are a tourist on a desert safari in Saudi Arabia when a huge sandstorm comes and you lose the safari group. \nYou only have a backpack: with 50 riyals, a sandwich, and three dates, and you have your camel to help you travel! You must find your group to survive and fight the scorpions, the desert bedouins, and the ULTIMATE DESERT MUMMY! \nProtect yourself and your camel, unless you will be moving a lot slower and will not have any help fighting!\n"
				+ "MAP KEY: "
				+ "\nGOAL: " + Colours.GREEN + "X" + Colours.RESET 
				+ "\nPLAYER (YOU): " + Colours.BLUE + "i" + Colours.RESET
				+ "\nSCORPION: " + Colours.RED + "&" + Colours.RESET 
				+ "\nDESERT BEDOUIN: " + Colours.MAGENTA + "@" + Colours.RESET
				+ "\nDESERT MUMMY: " + Colours.CYAN + "%" + Colours.RESET
				+ "\nSAND DUNE (You cannot step on a sand dune): " + Colours.YELLOW + "#" + Colours.RESET
				+ "\nSHOP: " + Colours.GREEN +  "$" + Colours.RESET + "\n\n") ;

		HashMap<String, String> mapKey = new HashMap<String, String>(); // create a hash map for a dictionary of which being is what symbol on the map

		// adding definitions for each being type (plus sand dunes and the goal)
		mapKey.put(Bedouin.class.getSimpleName(), Colours.MAGENTA + "@" + Colours.RESET);
		mapKey.put(Tourist.class.getSimpleName(), Colours.BLUE + "i" + Colours.RESET);
		mapKey.put(DesertMummy.class.getSimpleName(), Colours.CYAN + "%" + Colours.RESET);
		mapKey.put(Scorpion.class.getSimpleName(), Colours.RED + "&" + Colours.RESET);
		mapKey.put(Shop.class.getSimpleName(), Colours.GREEN +  "$" + Colours.RESET);
		mapKey.put("SandDune", Colours.YELLOW + "#" + Colours.RESET);
		mapKey.put("Goal", Colours.GREEN + "X" + Colours.RESET);

		String[][] map = makeMap(mapKey);

		printMap(map); // print the map in a nice 20 * 20 square
		return map;

	}

	/** This method carries the code to play the game, helping the tourist move and make sure that
	 * the game is still going if the player is not dead yet (in other words with more than 0 hp
	 * @param player: access to the player (Tourist type) which helps to move them across the map
	 * @param map: the actual map to make changes as the player makes its way through it
	 */
	public static void play(Tourist player, String[][] map) {
		Scanner in = new Scanner(System.in);
		while (player.hp > 0 ) { // while the player is not dead yet
			System.out.print("\n\nMake your move (W, A, S, D, or B to consume something from your backpack): ");
			String move = in.next(); // receive a W, A, S, or D, for movement

			if (player.camel != null) // if the tourist has a camel
				player.move(move, map, true); // move the player, acknowledging that the player should move two steps as they have a camel
			else // else the tourist has no camel
				player.move(move, map, false); // move the player normally

			
			if (player.getX() == 19 && player.getY() == 19) { // if the player is at the end of the map (goal)
				break; // end game
			}
		}
	}

	/** This method is designed to make a random map given the map key and updates the map accordingly
	 * @param mapKey: hashmap filled with different symbols for different beings and objects
	 * @return map with random objects filled throughout
	 */
	public static String[][] makeMap(HashMap<String, String> mapKey) {
		String[][] map = new String [20][20];
		HashMap<String, Integer> requiredCountMap = new HashMap<String, Integer>(); // hashmap which will define each being or object and how much should be in the map

		// adding several objects and beings to the hashmap definition
		requiredCountMap.put(Scorpion.class.getSimpleName(), 7); //seven scorpions 
		requiredCountMap.put(Bedouin.class.getSimpleName(), 4); //four bedouins
		requiredCountMap.put("Shop", 6); // six shops
		requiredCountMap.put("SandDune", 50); // 50 sand dunes

		for (Map.Entry<String, Integer> entry : requiredCountMap.entrySet()) { // for each loop which loops through each entry (or definition) and places it randomly in the map
			String beingClass = entry.getKey();
			int requiredCount = entry.getValue();
			int currentCount = 0; // counter which will keep track how many have been added to the map so far

			while (currentCount < requiredCount) { // while the map does not have enough of the needed amount of the being or object
				int x = 1 + (int)(Math.random() * ((19))); // find random x coordinates on the 20 * 20 box (giving a bit of a buffer for the player to be able to move at first)
				int y = 1 + (int)(Math.random() * ((19))); // find random y coordinates on the 20 * 20 box (giving a bit of a buffer for the player to be able to move at first)

				String key = mapKey.get(beingClass); // get the corresponding symbol of the being or object focused on 
				map[x][y] =  "[" + key + "]"; // place in map
				currentCount++; 
			}
		}

		map[0][0] = "[" + mapKey.get(Tourist.class.getSimpleName()) + "]"; // the tourist will always begin at coordinates (0,0)
		map[map.length - 1][map.length - 1] = "[" + mapKey.get("Goal") + "]"; // the goal (tourist group) will always be at (19, 19)
		map[map.length - 2][map.length - 1] = "[" + mapKey.get(DesertMummy.class.getSimpleName()) + "]"; // the desert mummy will always be on the edge of the map, the step right before the goal
		map[map.length - 1][map.length - 2] = "[" + mapKey.get("SandDune") + "]"; // there must be a dune to block off the goal from the other side, forcing the tourist to fight the desert mummy
		map[map.length - 3][map.length - 1] = null;

		// cleaning up the map
		for (int i = 0; i < map.length; i++) { // nested for loop to go through each coordinates on the map
			for (int j = 0; j < map[i].length; j++)
				if (map[i][j] == null) { // if there is nothing in the map after placing all objects
					map[i][j] = "[ ]"; // clean up: instead of null, set it as an empty box
				}
		}

		return map;
	}

	/** This method is designed to neatly print out the map in a 20 * 20 square
	 * @param map: string array of the map that has been created previously
	 */
	public static void printMap (String[][] map) {
		System.out.print("\n");
		for (int i = 0; i < map.length; i++) { // nested for loop to go through each coordinates on the map
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j]); // print the object in the coordinates focused
			}
			System.out.print("\n"); // after the row has been fully out printed, go to the next line to create a square-shaped map
		}
	}

	/** This method is designed to prompt player to play again
	 */
	public static boolean promptPlayAgain() {
		System.out.println("\nPress enter to play again!");
		
		// reads the keyboard to spin when enter is pressed
		try
		{
			System.in.read();
			return true;
		}  
		catch(Exception e)
		{}
		return false;  

	}

}

