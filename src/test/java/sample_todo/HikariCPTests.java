package sample_todo;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

class HikariCPTests {

	@Test
	public void testHikariCP() throws Exception {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName("org.mariadb.jdbc.Driver");
		config.setJdbcUrl("jdbc:mariadb://localhost:3308/sample_todo");
		config.setUsername("root");
		config.setPassword("1475");
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		
		HikariDataSource ds = new HikariDataSource(config);
		Connection connection = ds.getConnection();
		
		System.out.println(connection);
		
		connection.close();
	}

}
