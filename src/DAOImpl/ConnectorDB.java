package DAOImpl;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/** Class create pool of connections to database */

public class ConnectorDB {

	private static ConnectorDB dataSource;
	private ComboPooledDataSource cpds;

	private ConnectorDB() throws PropertyVetoException {
		ResourceBundle resource = ResourceBundle.getBundle("Resources/database");
		String url = resource.getString("url") + "?" + "useUnicode=" + resource.getString("useUnicode") + "&"
				+ "characterEncoding=" + resource.getString("characterEncoding");
		String driver = resource.getString("driver");
		String user = resource.getString("user");
		String password = resource.getString("password");
		cpds = new ComboPooledDataSource();
		cpds.setDriverClass(driver);
		cpds.setJdbcUrl(url);
		cpds.setUser(user);
		cpds.setPassword(password);
		cpds.setMinPoolSize(5);
		cpds.setAcquireIncrement(5);
		cpds.setMaxPoolSize(15);
		cpds.setMaxStatements(180);
	}

	public static synchronized ConnectorDB getInstance() throws PropertyVetoException {
		if (dataSource == null) {
			dataSource = new ConnectorDB();
		}
		return dataSource;
	}

	public Connection getConnection() throws SQLException {
		Connection cn = this.cpds.getConnection();
		return cn;
	}
}
