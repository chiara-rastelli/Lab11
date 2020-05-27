package it.polito.tdp.rivers.db;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.River;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RiversDAO {

	public void getAllRivers(Map<Integer, River> parametro) {
		
		final String sql = "SELECT id, name FROM river";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				if(!parametro.containsKey(res.getInt("id")))
					parametro.put(res.getInt("id"), new River(res.getInt("id"), res.getString("name")));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
	}
	
	public List<Flow> getFlow(Map<Integer, River> riverMap) {
		
		
		final String sql = "SELECT * " + 
				"FROM flow";

		List<Flow> flows = new LinkedList<Flow>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				River temp = riverMap.get(res.getInt("river")); 
				flows.add(new Flow(res.getDate("day").toLocalDate(), res.getFloat("flow"), temp));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return flows;
	}
	
		public List<Flow> getInfoDatoFiume(River fiume) {
		
		
		final String sql = "SELECT * " + 
				"FROM flow " + 
				"WHERE river = ?";

		List<Flow> flows = new LinkedList<Flow>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, fiume.getId());
			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				flows.add(new Flow(res.getDate("day").toLocalDate(), res.getFloat("flow"), fiume));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return flows;
	}
	
}
