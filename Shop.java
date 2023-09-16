import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/** This is the Shop class, which is where the tourist (player) can go to buy food to boost things such
 * as hp and strength. This class does not extend or implement any other class/interface.
 * @author asseel
 *
 */
public class Shop {
	public LinkedHashMap<Consumable, Integer> stock;

	// shop constructor which generates the stock
	public Shop() {
		generateStock();
	}

	/** This method is designed to help a tourist purchase a consumable from a shop. It checks if the 
	 * shop has the consumable in stock, and if the tourist has enough money to purchase it
	 * @param c: Consumable type that the tourist (player) would like to purchase
	 * @param t: Tourist (player) who would like to purchase Consumable c
	 */
	public void buy (Consumable c, Tourist t) {
		int availableStock = stock.getOrDefault(c, 0);
		
		if (t.money - c.getPrice() < 0) { // if the tourist (player) does not have enough money to purchase the consumable they selected
			System.out.println("Uh oh! You do not have enough money to buy " + c.getName().toLowerCase() + ". Would you like to buy another product?");
		}

		if (availableStock > 0) { // if the shop has the consumable desired in stock
			if (t.backpack.containsKey(c)) { // if the tourist already has the consumable in their backpack
				t.backpack.replace(c, t.backpack.get(c) + 1); // update the quantity in the backpack
				stock.replace(c, availableStock - 1); // subtract one quantity from the total consumable quantity of the shop's stock
			}
			else {
				t.backpack.put(c, 1); // else put the consumable in the backpack normally 
				stock.replace(c, availableStock - 1); // update stock by subtracting 1 from the previously available stock
			}
			
			t.money -= c.getPrice(); // subtract the price from the tourist's money
			c.setOwner(t); // set the consumable's owner to the tourist who purchased it 

			System.out.println(c.getName() + " has been added to you backpack. You now have " + t.money + " riyals.");
			
		}
		else { // else the shop does not have the consumable desired
			System.out.println("This shop does not have " + c.getName().toLowerCase() + ". Please choose from the quantity list.");
		}

	}

	/** This method is designed to randomly generate the stock in a shop
	 * @return hashmap of stock with the consumable and the corresponding price
	 */
	public void generateStock() {
		stock = new LinkedHashMap<Consumable, Integer>();

		// lines 59 to 64 are the different consumables that will be available in each shop – initializing each
		Consumable goldenShawarma = new Consumable(null, "Golden Shawarma", 100, 100, 25);
		Consumable dates = new Consumable(null, "Dates", 5, 0, 2);
		Consumable blueCheese = new Consumable(null, "Blue Cheese", 0, 25, 10);
		Consumable mangoes = new Consumable(null, "Mangoes", 10, 0, 5);
		Consumable shawarma = new Consumable(null, "Shawarma", 30, 0, 10);
		Consumable blueberries = new Consumable(null, "Blueberries", 0, 5, 5);

		// adding the different consumables to the shop's stock, generating them at random quantities (some shops can have zero of a specific consumable!)
		stock.put(shawarma, (int)(Math.random() * 3 + 1)); 
		stock.put(dates, (int)(Math.random() * 10 + 1)); 
		stock.put(blueCheese, (int)(Math.random() * 3 + 1)); 
		stock.put(mangoes, (int)(Math.random() * 5 + 1)); 
		stock.put(goldenShawarma, (int)(Math.random() * 2 + 1)); 
		stock.put(blueberries, (int)(Math.random() * 5 + 1)); 

		
	}

}


