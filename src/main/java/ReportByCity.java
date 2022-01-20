//Print out record data for Boston, New York, London, Paris, and Tokyo
//using a PreparedStatement.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 


public class ReportByCity {

	private static String[] cities = { "Boston", "New York", "London", "Paris", "Tokyo" };

	public static void main(String[] args) {
		String URL = "jdbc:mysql://frodo.bentley.edu:3306/world";
		String username = "harry";
		String password = "harry";

		String name, code, district;
		int population;

		ResultSet result = null;

		// make connection, create statement, execute SQL
		//try with resources will close connection at end of try block
		try (Connection con = DriverManager.getConnection(URL, username,
				password)) 
		{
			double fees = 0;

			//create PreparedStatement
			PreparedStatement pstmt = con
					.prepareStatement("select * from city where name=?;");

			//execute query once for each city in cities array
			for (int i = 0; i < cities.length; i++) {
				pstmt.setString(1, cities[i]);
				result = pstmt.executeQuery();
				
				//read result set; create and print object
				while (result.next()) {
					name = result.getString("Name");
					code = result.getString("CountryCode");
					district = result.getString("District");
					population = result.getInt("Population");
					City city = new City(name, code, district, population);			
					System.out.println(city);
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
