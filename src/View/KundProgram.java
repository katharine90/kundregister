package View;
import java.util.Scanner;

import Controller.CRUD;
import Controller.SearchFunction;
import Model.ShoppingCardBean;
import Model.mainBean;

// http://geti.dcc.ufrj.br/cursos/fes_2008_1/javatutorial/jdbc/basics/retrieving.html
public class KundProgram {

	public static void main(String[] args) {
		CRUD crud = new CRUD();
		if (crud.login()) {

// INSERT:
			ShoppingCardBean shoppingbean = new ShoppingCardBean();
			System.out.println("Select a number to add a item to your shopping cart");
// READ:
			crud.viewProducts();
			Scanner scan = new Scanner(System.in);
			int number = scan.nextInt();

			shoppingbean.setUserId(crud.toString());
			shoppingbean.setProductId(number);
			crud.shop(shoppingbean);

			System.out.println("Your current shoppingbag: ");
			crud.viewShoppingbag(shoppingbean);
			System.out.println("Do you want to keepon shopping? Enter yes or no");
			Scanner yesorno = new Scanner(System.in);
			String userinp = yesorno.nextLine();
			
			while(userinp.equals("yes")) {
				crud.viewProducts();
				Scanner sca = new Scanner(System.in);
				int num = sca.nextInt();

				shoppingbean.setUserId(crud.toString());
				shoppingbean.setProductId(num);
				crud.shop(shoppingbean);

				System.out.println("Your current shoppingbag: ");
				crud.viewShoppingbag(shoppingbean);
				
				System.out.println("Do you want to keepon shopping? Enter yes or no");
				yesorno = new Scanner(System.in);
				userinp = yesorno.nextLine();
			}
			
			System.out.println(
					"Do you want to remova a item? Pick a number that you would like to delete from you shopping cart");
			int deleteNumber = scan.nextInt();
			if (deleteNumber != 0) {

// DELETE: FIXA FELET!!!
				ShoppingCardBean deletebean = new ShoppingCardBean();
				deletebean.setProductId(deleteNumber);
				crud.deleteProdukt(deletebean);
				if (crud.deleteProdukt(deletebean) == true) {
					System.out.println("Deleted 1 row");
				} else {
					System.out.println("No rows affected");
				}
			}
			System.out.println("Your current shopping bag: ");
			crud.viewShoppingbag(shoppingbean);
			
			System.out.println("Would you like to search for a item? Enter a keyword: ");

// SEARCH:
			 SearchFunction search = new SearchFunction();
			 search.Search();
			 System.out.println("Would you like to view a list of our bonus customers?");
			 search.customerBonus();
			 
			 System.out.println("Would you like to uppdate your shipping adress?");
			 Scanner sc = new Scanner(System.in);
			 String answer = sc.nextLine();
			 if (answer.equals("yes")) {
				System.out.println("Enter new shipping adress");
				Scanner sca = new Scanner(System.in);
				String newAdress = sca.nextLine();
// UPPDATE:
				 mainBean mainBean = new mainBean();
				 mainBean.setAdress(newAdress);
				 mainBean.setUsername(crud.toString());
				 crud.updateCustomerInfo(mainBean);

			} else {

			}

		} else {
			System.out.println("Failed logg in");
		}

	}

}