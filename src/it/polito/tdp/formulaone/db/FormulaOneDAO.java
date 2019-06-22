package it.polito.tdp.formulaone.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.formulaone.model.Archi;
import it.polito.tdp.formulaone.model.Race;
import it.polito.tdp.formulaone.model.Season;

public class FormulaOneDAO {

	public List<Season> getAllSeasons() {
		String sql = "SELECT year, url FROM seasons ORDER BY year";
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			List<Season> list = new ArrayList<>();
			while (rs.next()) {
				list.add(new Season(rs.getInt("year"), rs.getString("url")));
			}
			conn.close();
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Race> getGarePerStagione(Map<Integer,Race>idMap, int anno){
		String sql = "SELECT DISTINCT * FROM races WHERE year=?";
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			ResultSet rs = st.executeQuery();
			List<Race> list = new ArrayList<>();
			while (rs.next()) {
				Race r = new Race(rs.getInt("raceId"), rs.getInt("year"), rs.getInt("circuitId"),rs.getString("name"));
				if(r == null)
					System.out.println("ERRORE");
				idMap.put(r.getRaceId(), r);
				list.add(r);
			}
			conn.close();
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public List<Archi> getArchi(Map<Integer,Race> idMap, int anno){
		String sql = "SELECT res.raceId, res2.raceId, COUNT(distinct res.driverId) as cnt " + 
				"FROM results AS res, results AS res2, races AS ra, races AS ra2 " + 
				"WHERE ra.YEAR=? " + 
				"and ra2.YEAR=? " + 
				"AND res.raceId = ra.raceId " + 
				"AND res2.raceId = ra2.raceId " + 
				"AND res.driverId = res2.driverId " + 
				"AND res.statusId = 1 " + 
				"AND res2.statusId = 1 AND res.raceId!=res2.raceId " + 
				"GROUP BY res.raceId, res2.raceId ";
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			st.setInt(2, anno);
			ResultSet rs = st.executeQuery();
			List<Archi> list = new ArrayList<>();
			while (rs.next()) {
				Race r1 = idMap.get(rs.getInt("res.raceId"));
				Race r2 = idMap.get(rs.getInt("res2.raceId"));
				int count = rs.getInt("cnt");
				if(r1== null || r2==null)
					System.out.println("ERRORE PER ARCHI");
				Archi a = new Archi(r1,r2,count);
				list.add(a);
			}
			conn.close();
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
