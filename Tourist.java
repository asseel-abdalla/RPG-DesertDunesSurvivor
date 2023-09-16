import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Scanner;

/** This class is the Tourist class, which is the being that the player will be.  The bedouin can strike,
 * and its unique field(s) include: backpack – a hashmap which consists of objects in the bag; money: 
 * int variable to keep count of how much money the tourist has; and the camel: in possesion of the
 * tourist.
 * @author asseel
 *
 */
public class Tourist extends Fighter implements Striker {
	public LinkedHashMap<Consumable, Integer> backpack = new LinkedHashMap<Consumable, Integer>();
	public int money;
	public Camel camel;
	private int x;
	private int y;

	// tourist constructor which receives hp and strength 
	public Tourist(int hp, int strength) {
		super(hp, strength); 
		this.x = 0;
		this.y= 0;

		// the tourist starts off with a sandwich and three dates
		backpack.put(new Consumable(this, "sandwich", 25, 0, 0), 1); // add the sandwich to the backpack
		backpack.put(new Consumable(this, "dates", 0, 5, 0), 3); // add the three dates to the backpack
		money = 50; // the tourist starts off with 50 riyals 
		camel = new Camel (100, 30, 100); // create the tourist's camel
	}

	/** This method is designed for the tourist to strike any being (the bad guys) 
	 * @param b: Being which is being struck
	 */
	public void Strike (Being b) {
		if (strength == 0)
			b.hp -= 10;
		else
			b.hp -= 10 + strength / 5; // for every 5 levels of strength, it adds one extra damage of hp on the being

		if (b.hp < 0)
			b.hp = 0;
		else if (hp < 0)
			hp = 0;

		System.out.println("NICE!!! You whacked " + b.toString() + "\nYour hp: " + hp + "\n" + b.toString() + "'s hp: " + b.hp);
	}

	// getter for the backpack and what's in it
	public LinkedHashMap<Consumable, Integer> getBackpack() {
		return backpack;
	}

	/** This method is designed to help the tourist (player) move along the map
	 * @param direction: string with W, A, S, or D
	 * @param map: 2d array of the map (used to move the player's icon across the board)
	 * @param hasCamel: boolean to see if the tourist has a camel – if true then they can move two squares at a time
	 */
	public void move(String direction, String[][] map, boolean hasCamel) {
		Scanner in = new Scanner (System.in);
		int num_rows = map.length;
		int num_cols = map[0].length;
		int prevX = x;
		int prevY = y;
		int step = 1;

		if (hasCamel) // if the tourist has a camel
			step = 2; // they are allowed to move two boxes at a time

		switch (direction) { // switch statement which receives direction to help move the player the direction they have put in 

		// when the player enters a "w"
		case "w": 
		case "W":
			if (x - step <= 0) {
				step = 1;
			}
			// if going up will not be out of bounds of the map, and if the box that they will step in is not a sand dune
			if (x - step >= 0) {
				if (!(map[x - 1][y].equals("[ ]"))) {
					step = 1;
				}
				
				if (map[x - step][y].equals("[" + Colours.YELLOW + "#" + Colours.RESET + "]")) {
					System.out.println("Invalid move!");
					break;
				}
				x -= step; // change the player's coordinates to move up
				checkActions(map); // check to see if the box they landed in is one of the objects
				map[prevX][prevY] = "[ ]"; // set the box that the player was previously in to a default empty box
				break;
			}
			else {
				System.out.println("Invalid move!");
				break;
			}

			// when the player enters an "a"
		case "a":
		case "A": 
			if (y + step <= 0) {
				step = 1;
			}
			// if going left will not be out of bonds of the map, and if the box that they will step in is not a sand dune
			if (y - step >= 0) {
				if (!(map[x][y - 1].equals("[ ]"))) {
					step = 1;
				}
				if (map[x][y - step].equals("[" + Colours.YELLOW + "#" + Colours.RESET + "]")) {
					System.out.println("Invalid move!");
					break;
				}
				y -= step; // change the player's coordinates to move left
				checkActions(map); // check to see if the box they landed in is one of the objects
				map[prevX][prevY] = "[ ]"; // set the box that the player was previously in to a default empty box
				break;
			}
			else {
				System.out.println("Invalid move!");
				break;
			}

			// when the player enters a "s"
		case "s":
		case "S":
			if (x + step >= num_rows - 1) {
				step = 1;
			}
			if ((x + step < num_rows)) { // if going down will not be out of bounds of the map, and if the box that they will step in is not a sand dune

				if (!(map[x + 1][y].equals("[ ]"))) {
					step = 1;
				}
				if (map[x + step][y].equals("[" + Colours.YELLOW + "#" + Colours.RESET + "]")) {
					System.out.println("Invalid move!");
					break;
				}
				x += step; // change the player's coordinates to move down
				checkActions(map); // check to see if the box they landed in is one of the objects
				map[prevX][prevY] = "[ ]"; // set the box that the player was previously in to a default empty box
				break;
			}
			else {
				System.out.println("Invalid move!");
				break;
			}

		// when the player enters a "d"
		case "d":
		case "D":
			if (y + step >= num_cols - 1) {
				step = 1;
			}
			// if going right will not be out of bounds of the map, and if the box that they will step into is not a sand dune
			if ((y + step < num_cols)) {
				if (!(map[x][y + 1].equals("[ ]"))) {
					step = 1;
				}
				if (map[x][y + step].equals("[" + Colours.YELLOW + "#" + Colours.RESET + "]")) {
					System.out.println("Invalid move!");
					break;
				}
				y += step; // change the player's coordinates to move right
				checkActions(map); // check to see if the box they landed in is one of the objects
				map[prevX][prevY] = "[ ]"; // set the box that the player was previously in to a default empty box
				break;
			}
			else if (y + step > num_cols) {
				y = num_cols - 1;
			}
			else {
				System.out.println("Invalid move!");
				break;
			}

		case "B":
		case "b":
			System.out.println("This is what is in your backpack: ");
			int cnt = 0;
			for (HashMap.Entry<Consumable, Integer> item: this.backpack.entrySet()) {
				cnt++;
				Consumable consumable = item.getKey();
				int quantity = item.getValue();
				System.out.print("\n\t" + cnt + ". " + consumable.getName() + " (quantity: " + quantity + ")");
			}

			System.out.print("\n\nPlease enter the corresponding number of the product you would like to consume: ");
			int product = in.nextInt();

			Consumable consumable = (Consumable)(backpack.keySet().toArray()[product - 1]);
			System.out.println(consumable.consume());
			break;


		// if the user did not enter a W, A, S, or D
		default:
			if (map[x][y].equals("[" + Colours.GREEN +  "$" + Colours.RESET + "]")) { // if the player is at a shop, do not throw "invalid move" as it is trying to scan the keyboard when it should not
				break;
			}
			System.out.print("Invalid move! \n");
		}

		map[x][y] = "[" + Colours.BLUE + "i" + Colours.RESET + "]"; // update the map to put the user's icon in the new coordinates
		DesertDunesDriver.printMap(map); // print updated map

	}

	/** This method is designed to check if the box the player landed on after moving has an object. If it does,
	 * it will throw scenarios accordingly
	 * @param map: 2d string array of the map to access what is in the coordinates
	 */
	public void checkActions (String map[][]) {
		Scanner in = new Scanner (System.in);
		if (map[x][y].equals("[" + Colours.MAGENTA + "@" + Colours.RESET + "]")) { // if the player landed on a box occupied by a bedouin's symbol
			int r = (int) (40 + Math.random() * (10)); // random number between 40 and 50 for the bedouin's strength (which will affect the damage it can cause)
			Bedouin bedouin = new Bedouin (100, r);

			System.out.print("Uh oh! You ran into the desert bedouin. FIGHT HIM!!!"
					+ "\n\nFIGHTING CONTROLS:"
					+ "\n1 for strike"
					+ "\n2 to ask for your camel's help!\nYOUR CHOICE: ");

			int choice = in.nextInt();

			if (choice != 1 && choice != 2) {
				System.out.print("Please enter a valid number!");
				choice = in.nextInt();
			}

			while (choice == 1 || choice == 2) { // while loop which loops through if choice is 1 or 2 (to keep on fighting the bedouin)
				if (choice == 1) { // if the user decided to strike the bedouin
					strike(bedouin); 
					bedouin.poison(this); // bedouin will poison the user after the tourist strikes the,
					if (camel != null) { // if the tourist has a camel
						camel.setSleepLevel(camel.getSleepLevel() + 2); // add 2 to the camel's sleep level as them not fighting allows for them to rest
					}
				}

				if (choice == 2) { // if the user asked for the camel's help
					if (camel == null) { // if the user does not have a camel
						System.out.println("You do not have a camel to help you fight! Please enter a different number: ");
						choice = in.nextInt();
						
					}
					else {
						camel.kick(bedouin, this); // the camel will kick the bedouin
						bedouin.stealCamel(this); // the bedouin will attempt to steal the user's camel
					}
				}

				if (bedouin.hp <= 0) { // if the user kills the bedouin
					System.out.println("\nNICE!!! You killed the desert bedouin. Keep it up!");
					break; 
				}
				
				if (hp <= 0) { // if the user dies
					hp = 0;
					System.out.println("Oh no! You died to the desert bedouin.");
					break; // done fighting
				}
				
				System.out.print("\nYOUR CHOICE: ");
				choice = in.nextInt();

				if (choice != 1 && choice != 2) { // if the user entered a number other than 1 or 2
					System.out.print("Please enter a valid number! \nYOUR CHOICE:");
					choice = in.nextInt();
				}

			}
		}
		else if (map[x][y].equals("[" + Colours.GREEN +  "$" + Colours.RESET + "]")) { // else if the player landed on a box occupied by a shop's symbol
			Shop shop = new Shop(); // create shop

			System.out.print("You're at a shop! Would you like to see what is in stock? (Please enter yes or no): ");
			String ans = in.next().toUpperCase();
			boolean interested = false;

			if (!(ans.equals("YES") && !(ans.equals("NO")))) { // if the user entered an invalid answer
				System.out.println("Type in yes or no, please!");
				ans = in.next().toUpperCase();
			}

			if (ans.equals("YES")) { // if the user has 
				interested = true; // they are interested in buying
			}

			while (interested) { // while the user is interested in buying
				System.out.print("Welcome! These are the products on sale: ");

				LinkedHashMap<Consumable, Integer> stock = shop.stock; // set hashmap stock to the shop's stock
				int cnt = 0;
				
				for (HashMap.Entry<Consumable, Integer> name: stock.entrySet()) { // loop through each entry in stock
					cnt++;
					Consumable consumable = name.getKey(); // set consumable to the entry in the stock hashmap
					int quantity = stock.get(consumable); // get the value corresponding to the key of consumable for the quantity value
					System.out.print("\n\t" + cnt + ". " + consumable.getName() + " (stock: " + quantity + ") for " + consumable.getPrice() + " riyals");
				}

				System.out.print("\nPlease enter the corresponding number of the product you would like to buy: ");
				int product = in.nextInt();

				shop.buy((Consumable)shop.stock.keySet().toArray()[product - 1], this); // buy the product chosen
				
				interested = false; // default set interest to false
				
				System.out.print("Would you like to buy something else? (Please type yes or no): ");
				ans = in.next().toUpperCase();

				if (ans.equals("YES")) { // if the user is interested in buying something else
					interested = true; // set interest to true
				}
				
				if (ans.equals("NO")) { // if the user is not interested in buying something else
					System.out.println("Thank you, come again soon!"); 
					break; // leave shop
				}
				
				if (!(ans.equals("YES") && !(ans.equals("NO")))) { // if the user inputs an invalid answer
					System.out.println("Type in yes or no, please!");
					ans = in.next().toUpperCase();
				}
			}

		}
		else if (map[x][y].equals("[" + Colours.RED + "&" + Colours.RESET + "]")) { // else if the player landed on a box occupied by a scorpion
			System.out.print("Gosh darn it! You just walked into a colony of scorpions and ruined one scorpion's house. Uh oh... he seems a bit mad. HE WANTS TO FIGHT!");
			int r = (int) (Math.random() * 10); // random number between 0 and 10
			Scorpion scorpion = new Scorpion (100, r);
			
			System.out.print("\n\nFIGHTING CONTROLS:"
					+ "\n1 for strike"
					+ "\n2 to ask for your camel's help!\n\nYOUR CHOICE: ");
			int choice = in.nextInt();

			if (choice != 1 && choice != 2) { // if user enters anything other than a 1 or 2
				System.out.print("Please enter a valid number! \nYOUR CHOICE: ");
				choice = in.nextInt();
			}

			while (choice == 1 || choice == 2) { // while the user enters a valid number
				if (hp <= 0) { // if the tourist has died
					hp = 0;
					System.out.println("\nOH NO!!! The scorpion poisoned you to death.");
					break;
				}
				
				if (choice == 1) { // if the user chose to strike a scorpion
					strike(scorpion);
					scorpion.poison(this);
				}
				else if (choice == 2) { // if the user chose to ask their camel for help
					if (camel == null) { // if the user does not have a camel
						System.out.println("You can't ask your camel for help if you don't have a camel!");
					}
					else { // else the camel can kick the scorpion
						camel.kick(scorpion, this);
					}
				}

				if (scorpion.hp <= 0) { // if the scorpion has died
					scorpion.hp = 0;
					System.out.print("\nNICE!!! You killed the scorpion. Keep it up!");
					break; // done fighting
				}
				
				if (hp <= 0) { // if the user dies
					hp = 0;
					System.out.println("\nOh no! You died to the desert bedouin.");
					break;
				}

				System.out.print("\n\nYOUR CHOICE: ");
				choice = in.nextInt();

				if (choice != 1 && choice != 2) { // if the user enters an invalid number
					System.out.print("Please enter a valid number! \nYOUR CHOICE: ");
					choice = in.nextInt();
				}
			}
		}
		else if (map[x][y].equals("[" + Colours.CYAN + "%" + Colours.RESET + "]")) { // else if the user lands on a box occupied by the desert mummy
			DesertMummy mummy = new DesertMummy (100, 100);
			System.out.print("Just as you were about to reunite with your safari group... a DESERT MUMMY comes in your way! His sandcastle broke down and is very mad. FIGHT HIM!!!");
			System.out.print("\nFIGHTING CONTROLS:"
					+ "\n1 for strike"
					+ "\n2 to ask for your camel's help!\nYOUR CHOICE: ");
			int choice = in.nextInt(); 

			if (choice != 1 && choice != 2) { // if the user does not enter a 1 or 2
				System.out.print("Please enter a valid number! \nYOUR CHOICE: ");
				choice = in.nextInt();
			}

			int cnt = 0; // counter used to see when the mummy does their ultimate sand dune fight
			while (choice == 1 || choice == 2) {

				if (hp <= 0) { // if the tourist has died
					hp = 0;
					System.out.println("OH NO!!! The desert mummy killed you.");
					break;
				}
				
				if (choice == 1) { // if the user decided to strike the mummy
					cnt++;
					strike(mummy);
					mummy.strike(this);
				}
				else if (choice == 2) { // else if the user decided to ask their camel for help
					if (camel == null) { // if they do not have a camel
						System.out.println("You can't ask your camel for help if you don't have a camel! Please enter a different number: ");
						choice = in.nextInt();
					}
					else { // else they do have a camel
						cnt++;
						camel.kick(mummy, this);
					}
				}

				if (cnt % 4 == 0) { // every four times the mummy will throw sand which will
					mummy.throwSand(this);
					if (camel != null) // if the tourist has a camel
						System.out.println("OH NO!!! This mummy threw a huge sand dune at you and you camel! BE CAREFUL!!!\nYour hp: " + hp + "\nCamel's hp: " + camel.hp + "\nMummy's hp: " + mummy.hp);
					else // else the tourist does not have a camel
						System.out.println("OH NO!!! This mummy threw a huge sand dune at you! BE CAREFUL!!!\\nYour hp: \n" + hp + "\nMummy's hp: " + mummy.hp);

				}

				if (mummy.hp <= 0) { // if the mummy dies
					System.out.println("\nNICE!!! You killed the desert mummy. You killed the bad guys! Now run to your safari group.");
					break; // done fighting
				}
				
				if (hp <= 0) { // if the user dies
					hp = 0; // set hp to zero (as it is the min)
					System.out.println("\nOh no! You died to the desert bedouin.");
					break; // done fighting
				}
				
				System.out.print("\n\nYOUR CHOICE: ");
				choice = in.nextInt();

				if (choice != 1 && choice != 2) { //if the user enters an invalid number
					System.out.print("Please enter a valid number! \nYOUR CHOICE: ");
					choice = in.nextInt();
				}

			}
		}
		else if (map[x][y].equals("[" + Colours.GREEN + "X" + Colours.RESET + "]")) { // else if the box the tourist lands on is occupied by the goal position
			System.out.print("HIP HIP HOORAY!!! You successfully reunited with your safari group and beat the three bad guys! Well done... but don't get lost in the desert ever again!\n");
		}
	}


	// getters for x and y coordinates of tourist
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
