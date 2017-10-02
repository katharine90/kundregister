package Controller;
import java.util.Properties;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.sql.*;

public class SearchFunction {

	 private static Properties sqlProperties = new Properties();
	 private static String URL = "jdbc:mysql://localhost/kundregister";
	 
	 public void Search() {
		 sqlProperties.setProperty("user", "root");  // Return as array 
		 sqlProperties.setProperty("password", "");
		 
		 System.out.println("Enter search keyword: ");
		 Scanner scan = new Scanner(System.in);
		 String keyword = scan.nextLine();
		 
		 String searchRequest = "select * from products where productName like '%" + keyword +"%'";
		 
		 try {
			Connection con = DriverManager.getConnection(URL, sqlProperties);
			Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
			ResultSet result = stat.executeQuery(searchRequest);
			
			while(result.next()) {
				System.out.println("Number: [" + result.getString("Id") + "] " + "Product: " + result.getString("productName") + ", Price: " + result.getInt("price"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
	 }
	 
	 public String chooseBonus() {
		 String basic = "select userId from getpoints where totalPoints<100 group by userId";
		 String silver = "select userId from getpoints where totalPoints>100 and totalPoints<200 group by userId";
		 String gold = "select userId from getpoints where totalPoints>200 group by userId";
		 System.out.println("Select basic, silver or gold to list bonus custumer: ");
		 Scanner scan = new Scanner(System.in);
		 String userInput = scan.nextLine();
	 
			if (userInput.equals("basic")) {
				return basic;
			} else if (userInput.equals("silver")){
				return silver;
			}else if(userInput.equals("gold")) {
				return gold;
			}
			
			return null;
	 }
	 
	 public void customerBonus() {
		 sqlProperties.setProperty("user", "root");
		 sqlProperties.setProperty("password", "");
		 
		 String response;
		 try {
			Connection con = DriverManager.getConnection(URL, sqlProperties);
			Statement stat = con.createStatement();
			ResultSet result = stat.executeQuery(chooseBonus());

			
			while(result.next()) {
				response = result.getString(1);
				System.out.println(response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		 
		 
	 }
	 
	 
}
