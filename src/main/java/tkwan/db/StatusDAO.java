package tkwan.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tkwan.model.Status;

public class StatusDAO {
	private java.sql.Connection conn;
	final private String tableName = "Status";

    public StatusDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }
    
    public Status updateStatus(int idUser, int idTrick, int status) throws Exception{
    	Status statusObj;
    	//check if the status entry already exists
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tableName + " WHERE user=? AND trick=?;");
		ps.setInt(1, idUser);
		ps.setInt(2, idTrick);
		ResultSet resultSet = ps.executeQuery();
		int count = 0;
		while (resultSet.next()) {
			count++;
        }
		resultSet.close();

    	//if it doesn't, add
		if (count == 0) {
			ps = conn.prepareStatement("INSERT INTO " + tableName + " VALUES (?, ?, ?);");
			ps.setInt(1, idUser);
			ps.setInt(2, idTrick);
			ps.setInt(3, status);
			ps.execute();
			statusObj = new Status(idUser,idTrick,status);
		}
		
    	//else alter
		else {
			ps = conn.prepareStatement("UPDATE " + tableName + " SET status=? WHERE user=? AND trick=?;");
			ps.setInt(1, status);
			ps.setInt(2, idUser);
			ps.setInt(3, idTrick);
			ps.execute();
			statusObj = new Status(idUser,idTrick,status);
		}
		
		ps.close();
		
		return statusObj;
    }

	public List<Status> getListOfStatus(int idUser) throws Exception {
		try {
			ArrayList<Status> list = new ArrayList<Status>();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tableName + " WHERE user=?;");
			ps.setInt(1, idUser);
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()) {
				Status s = new Status(resultSet.getInt("user"), resultSet.getInt("trick"), resultSet.getInt("status"));
				list.add(s);
			}
			return list;
		}catch (Exception e){
			throw new Exception("Failed in getting list of status: " + e.getMessage());
		}
	}
}
