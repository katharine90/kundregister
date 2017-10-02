package Controller;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

import Model.ShoppingCardBean;
import Model.mainBean;


public class CRUD{
	 private static Properties sqlProperties = new Properties();
	 private static String URL = "jdbc:mysql://localhost/kundregister";
	 private static String sqlusername;


	public static boolean login() {    
		 sqlProperties.setProperty("user", "root");
		 sqlProperties.setProperty("password", "");
		 
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter username: " );
			sqlusername = scan.nextLine();
			System.out.println("Enter password: " );
			String sqlpass = scan.nextLine();
			
		 String login = "SELECT * FROM account WHERE usernameID = ? AND password= ?";
		 try {
				Connection con = DriverManager.getConnection(URL, sqlProperties);
				PreparedStatement stat = con.prepareStatement(login);
				
			stat.setString(1, sqlusername);
			stat.setString(2, sqlpass);
			ResultSet result = stat.executeQuery();
			
			if (result.next()) {
				ShoppingCardBean bean = new ShoppingCardBean();
				System.out.println("Welcome " + sqlusername + "\n" + "You are logged in and ready to shop");	

				return true;
			}else {
				return false;
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} 
	 }
	 
	public String toString() {
		return sqlusername;
	}
	 
	 public void shop(ShoppingCardBean bean) {   //Create, insert     // Behöver login
		 sqlProperties.setProperty("user", "root");
		 sqlProperties.setProperty("password", "");
		 ResultSet result = null;
		 
		 String insert = "INSERT INTO shoppingcart(userId, productid) VALUES(?, ?)";
		try {
			 Connection con = DriverManager.getConnection(URL, sqlProperties);
			 PreparedStatement stat = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			 
			 stat.setString(1, bean.getUserId());
			 stat.setInt(2, bean.getProductId());		
			 stat.executeUpdate();
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}		 
	 }
	 
	 public void updateCustomerInfo(mainBean bean) {   //Uppdate   // Behöver login
		 sqlProperties.setProperty("user", "root");
		 sqlProperties.setProperty("password", "");
		 
		 String uppdateAdress = "UPDATE customer SET shippingadress=? WHERE username=?";
		 
		 try {
			Connection con = DriverManager.getConnection(URL, sqlProperties);
			PreparedStatement stat = con.prepareStatement(uppdateAdress);
			
			stat.setString(1, bean.getAdress());
			stat.setString(2, bean.getUsername());
			stat.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	 }
	 
	 public boolean deleteProdukt(ShoppingCardBean bean) {  //Delete     behöver login
		 sqlProperties.setProperty("user", "root");
		 sqlProperties.setProperty("password", "");
		 ResultSet result = null;
		 
		 String delete = "DELETE FROM shoppingcart WHERE productid=?";
		try {
			 Connection con = DriverManager.getConnection(URL, sqlProperties);
			 PreparedStatement stat = con.prepareStatement(delete);
			 
			 stat.setInt(1, bean.getProductId());
			 int affected = stat.executeUpdate();
			 
			 if (affected == 1) {
//				System.out.println("Deleted 1 row");
				return true;
			}else {
				return false;
			}
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		 
	 }
	 
	 public void viewProducts() {  //Read, Stored Procedure
	sqlProperties.setProperty("user", "root");
		 sqlProperties.setProperty("password", "");
		
		 String viewProductTable = "{call readData}";
		 
		 try {
			Connection con = DriverManager.getConnection(URL, sqlProperties);
			CallableStatement statement = con.prepareCall(viewProductTable);
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
				System.out.println("Nummer: [" + result.getInt("id") + "] Produkt: " + result.getString("productName") + " Pris:" +
			result.getInt("price"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
	 }
	 
	 public void viewShoppingbag(ShoppingCardBean bean) {  //Read, Stored Procedure
	     sqlProperties.setProperty("user", "root");
		 sqlProperties.setProperty("password", "");
		
		 String shoppingBag = "select shoppingcart.userId, products.id, products.productName"
		 		+ " from shoppingcart join products on products.id = shoppingcart.productid where userId = '" + toString() + "'";
		 
		 try {
			Connection con = DriverManager.getConnection(URL, sqlProperties);
			Statement stat = con.createStatement();
			ResultSet result = stat.executeQuery(shoppingBag);
			
			while (result.next()) {
				System.out.println("Item : " + result.getString("productName") + ", number: [" +result.getString("Id") +"]");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
	 }


	 
	 

}
