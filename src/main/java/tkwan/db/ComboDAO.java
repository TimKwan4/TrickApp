package tkwan.db;

import java.sql.PreparedStatement;

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
	
	public void deleteCombo(int comboFrom, int comboInto) throws Exception{
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM " + tableName + " WHERE comboFrom=? AND comboInto=?;");
	        ps.setInt(1, comboFrom);
			ps.setInt(2, comboInto);
	        ps.execute();  
            
		} catch (Exception e) {
            throw new Exception("Failed in adding combo: " + e.getMessage());
		}
	}
}
