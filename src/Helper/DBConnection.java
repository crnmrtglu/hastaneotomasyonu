package Helper;
import java.sql.*;
public class DBConnection {

	Connection connection=null;
	
	public DBConnection() {}
	public Connection connDb() {
		try {
			this.connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/kullanicilar?user=root&password=ceren74");
			return connection;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
}
