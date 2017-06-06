package bigbox.presentation;

import java.util.*;

import bigbox.business.Store;
import bigbox.db.DAOFactory;
import bigbox.db.StoreDAO;
import bigbox.util.*;



public class BigBoxApp {

	private static StoreDAO storeDAO = null;
	
	 public static void main(String args[]) {

		// display a welcome message
		System.out.println("\t\tWelcome to the Big Box App \n");
		Scanner sc = new Scanner(System.in);
		storeDAO = DAOFactory.getStoreDAO();

		// perform 1 or more selections

		String choice = "Y";
		while (choice.equalsIgnoreCase("Y")) {
			displayMenu();

			// get the input from the user
			String operation = Validator.getString(sc, "\t\tEnter a Command:  ");

			switch (operation.toLowerCase()) {

			case "list":
				getAllStores();

				break;

			case "div":
				displayAllStoresForDiv();
				break;

			case "add":
				addStore();

				break;

			case "del":
				deleteStore();

				break;

			case "help":

				displayMenu();

				break;

			case "exit":

				exit();

				break;

			case "n":

				break;
			default:
				System.out.println("Error Input only accepts 'list', 'div', 'add', 'del', 'help', 'exit' \n");
				continue;
			}
		}
		sc.close();
	}

	private static void getAllStores () 
	{
		System.out.println("\t\t ALL THE STORE INFORMATION YOU NEED \n\t\t\t IS RIGHT BELOW \n");
	
		System.out.println("Div#\tStore#\t==Sales==\t====Name====\t======Address=====\t=City=\tState\t=Zip=");

				ArrayList<Store> allStoresArray = storeDAO.getAllStores();
				for (Store i : allStoresArray){
					System.out.println(i.getDivisionNbr()+"\t"+i.getStoreNbr()+
							"\t"+ i.getSales()+"\t"+ i.getName()+"\t"+ i.getAddress()+
							"\t"+i.getCity()+"\t"+i.getState()+"\t"+i.getZip());	
		
	  }
	}
	
	/*i = allStoresArray
	{
		System.out.println(i.getDivisionNbr()+"\n"+i.getStoreNbr()+
				"\n"+ i.getSales()+"\n"+ i.getName()+"\n"+ i.getAddress()+
				"\n"+i.getCity()+"\n"+i.getState()+"\n\t\n"+i.getZip());	
		}	
	*/
		
	
	
	
	/*	
	    // I left this in here to see the differences 	


		ArrayList<Store> stores = storeDAO.getAllStores();
		for (Store s: stores) {
			System.out.println(s.toString());
		}

	}*/

	public static void addStore() {

		Scanner sc = new Scanner(System.in);
		String divisionnumber = Validator.getDivisionNbr(sc, "Enter the division number for the store: ", 3);

		String storenumber = Validator.getStoreNbr(sc, "Enter the store number for the store: ", 5);

		double sales = Validator.getDouble(sc, "Enter the sales of the store: ");

		String name = Validator.getString(sc, "Enter the name of the store: ");

		String addresss = Validator.getString(sc, "Enter the address of the store: ");

		String city = Validator.getString(sc, "Enter the city of the store: ");

		String state = Validator.getString(sc, "Enter the state of the store: ");

		String zip = Validator.getStoreZip(sc, "Enter the zip code of the store: ", 5);

		Store store = new Store();

		store.setDivisionNbr(divisionnumber);
		store.setStoreNbr(storenumber);
		store.setSales(sales);
		store.setName(name);
		store.setAddress(addresss);
		store.setCity(city);
		store.setState(state);
		store.setZip(zip);

		storeDAO.addStore(store);
		System.out.println("store was added to the database.\n");
	}

	public static void deleteStore() {

		Scanner sc = new Scanner(System.in);
		String divisionnumber = Validator.getDivisionNbr(sc, "Enter The Division number: ", 3);

		String storenumber = Validator.getStoreNbr(sc, "Enter The Store number: ", 5);

		Store s = storeDAO.getStoreByDivionAndStoreNumber(divisionnumber,storenumber);

		storeDAO.deleteStore(s);
		System.out.println("store was deleteted from the database.\n");

	}

	public static void exit() {

		System.out.println("\t\tBye.\n");
		System.exit(0);

	}

	private static void displayAllStoresForDiv() {

		// perform 1 or more selections
		Scanner sc = new Scanner(System.in);
		String choice = "y";
		while (choice.equalsIgnoreCase("y")) {
			
			String division = Validator.getDivisionNbr(sc, "Please Enter Division Number: ", 3);



			System.out.println("Store found for division " + division);

			System.out.println("Div#\tStore#\t==Sales==\t====Name====\t======Address=====\t=City=\tState\t=Zip=");

			ArrayList<Store> allStoresArray = storeDAO.getAllStoresByDivision(division);
			for (Store i : allStoresArray){
				System.out.println(i.getDivisionNbr()+"\t"+i.getStoreNbr()+
						"\t"+ i.getSales()+"\t"+ i.getName()+"\t"+ i.getAddress()+
						"\t"+i.getCity()+"\t"+i.getState()+"\t"+i.getZip());	

			}
			// see if the user wants to continue
			choice = Validator.getString(sc, "\t\tContinue? (y/n):  ");
			System.out.println();
		}
		System.out.println("\t\tBye.\n");	
	}
	private static void displayMenu() {
		System.out.println("\n\t\t\t<<<<COMMAND MENU>>>> \n\n" + "\t\tlist\t\t- List all stores\n"
				+ "\t\tdiv\t\t- List all stores for a division\n" + "\t\tadd\t\t- Add a store \n" + "\t\tdel\t\t- Delete a store\n"
				+ "\t\thelp\t\t- Show this menu\n" + "\t\texit\t\t- Exit the the Application\n\n\n");
	}

}
