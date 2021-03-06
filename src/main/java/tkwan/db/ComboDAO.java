package tkwan.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import tkwan.model.Combo;

public class ComboDAO {
	private java.sql.Connection conn;
	final private String tableName = "Combo";

    public ComboDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }
	public void addCombo(int comboFrom, int comboInto) throws Exception{
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO " + tableName + " VALUES (?, ?);");
	        ps.setInt(1, comboFrom);
			ps.setInt(2, comboInto);
	        ps.execute();  
            
		} catch (Exception e) {
            throw new Exception("Failed in adding combo: " + e.getMessage());
		}
	}
	
	public boolean deleteCombo(int comboFrom, int comboInto) throws Exception{
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tableName + " WHERE comboFrom=? AND comboInto=?;");
			ps.setInt(1, comboFrom);
			ps.setInt(2, comboInto);
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
			
			ps = conn.prepareStatement("DELETE FROM " + tableName + " WHERE comboFrom=? AND comboInto=?;");
	        ps.setInt(1, comboFrom);
			ps.setInt(2, comboInto);
	        ps.execute();  
            
	        ps.close();
	        
	        return true;
	        
		} catch (Exception e) {
            throw new Exception("Failed in deleting combo: " + e.getMessage());
		}
	}
	public List<Combo> getListOfCombos() throws Exception {
		List<Combo> list = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tableName + ";");
			ResultSet resultSet = ps.executeQuery();
			
			while (resultSet.next()) {
				Combo c = new Combo(resultSet.getInt("comboFrom"), resultSet.getInt("comboInto"));
                list.add(c);
            }
            resultSet.close();
            ps.close();
            return list;
            
            
		} catch (Exception e) {
            throw new Exception("Failed in getting Combos: " + e.getMessage());
		}
	}
}
