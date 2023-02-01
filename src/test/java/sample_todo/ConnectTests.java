package sample_todo;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ConnectTests {

	@Test
	public void testConnection() throws Exception {

		Class.forName("org.mariadb.jdbc.Driver");
		
		Connection connection = DriverManager.getConnection(
				"jdbc:mariadb://localhost:3308/sample_todo",
				"root",
				"1475" );
		
		Assertions.assertNotNull(connection);
		
		connection.close();
		//fail("Not yet implemented");
	}

}
