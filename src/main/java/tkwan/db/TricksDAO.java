package tkwan.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.lambda.runtime.LambdaLogger;

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
	
}
