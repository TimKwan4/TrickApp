package tkwan.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
	private java.sql.Connection conn;
	final private String tableName = "User";

    public UserDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }
    
	public int createUser(String name) throws Exception {
		try {
			//figure out if the name already exists
			PreparedStatement ps = conn.prepareStatement("SELECT name FROM " + tableName + " WHERE name=?;");
			ps.setString(1,name);
			ResultSet resultSet = ps.executeQuery();
			int count = 0;
			while (resultSet.next()) {
				count++;
            }
			//if count is > 0 it already exists
			if (count > 0) {
				throw new Exception("User already exists");
			}
			
			//find the next id to take
			ps = conn.prepareStatement("SELECT MAX(idUser) as maxID FROM " + tableName + ";");
			resultSet = ps.executeQuery();
			int nextID = 0;
			while (resultSet.next()) {
				nextID = resultSet.getInt("maxID") + 1;
            }
			resultSet.close();
			
			ps = conn.prepareStatement("INSERT INTO " + tableName + " VALUES (?, ?);");
	        ps.setInt(1, nextID);
			ps.setString(2, name);
	        ps.execute();  
	        
	        ps.close();
            return nextID;
		} catch (Exception e) {
            throw new Exception("Failed in creating User: " + e.getMessage());
		}
	}
	
}

