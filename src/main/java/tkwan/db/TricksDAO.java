package tkwan.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import tkwan.model.Trick;

public class TricksDAO {
	private java.sql.Connection conn;
	final private String tableName = "Trick";

    public TricksDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }
	public List<Trick> getListOfTricks(int idUser) throws Exception{
		List<Trick> list = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tableName + " WHERE customUser IS NULL OR customUser=" + String.valueOf(idUser) + ";");
			ResultSet resultSet = ps.executeQuery();
			
			while (resultSet.next()) {
				Trick t = new Trick(resultSet.getInt("idTrick"),
									resultSet.getString("trickName"),
									resultSet.getString("trickDes"),
									resultSet.getInt("customUser") );
                list.add(t);
            }
            resultSet.close();
            ps.close();
            return list;
            
            
		} catch (Exception e) {
            throw new Exception("Failed in getting Tricks: " + e.getMessage());
		}
	}
	public void addCustomTrick(String trickName, String trickDes, int customUser) throws Exception {
		try {
			//find the next id to take
			PreparedStatement ps = conn.prepareStatement("SELECT MAX(idTrick) as maxID FROM " + tableName + ";");
			ResultSet resultSet = ps.executeQuery();
			int nextID = 0;
			while (resultSet.next()) {
				nextID = resultSet.getInt("maxID") + 1;
            }
			resultSet.close();
			
			ps = conn.prepareStatement("INSERT INTO " + tableName + " VALUES (?, ?, ?, ?);");
	        ps.setInt(1, nextID);
			ps.setString(2, trickName);
			ps.setString(3, trickDes);
	        ps.setInt(4, customUser);
	        ps.execute();  
	        
	        ps.close();
            
		} catch (Exception e) {
            throw new Exception("Failed in adding Trick: " + e.getMessage());
		}
	}
	public boolean deleteCustomTrick(int idTrick) throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tableName + " WHERE idTrick=? AND customUser IS NOT NULL;");
			ps.setInt(1, idTrick);
			ResultSet resultSet = ps.executeQuery();
			int count = 0;
			while (resultSet.next()) {
				count++;
            }
			resultSet.close();
			
			//nothing to delete
			if (count == 0) {
				return false;
			}
			
			ps = conn.prepareStatement("DELETE FROM " + tableName + " WHERE idTrick=? AND customUser IS NOT NULL;");
	        ps.setInt(1, idTrick);
	        ps.execute();  
            
	        ps.close();
	        
	        return true;
	        
		} catch (Exception e) {
            throw new Exception("Failed in deleting custom Trick: " + e.getMessage());
		}
	}
	
}
