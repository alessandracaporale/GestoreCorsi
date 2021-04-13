package it.polito.tdp.corsi.db;

import it.polito.tdp.corsi.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.SQLException;
import java.util.*;
import java.sql.*;
//import com.sun.tools.javac.util.List;
//import it.polito.tdp.corsi.model.Studente;


public class CorsoDAO {
	//PATTERN
	
	public List<Corso> getCorsiByPeriodo (Integer periodo) {
		//per ogni tabella costruiamo una classe che modelli la mia tabella
		//in questo caso avremo la classe Corso e la classe Studente
		
		String sql="SELECT * "
				+ "FROM corso "
				+ "WHERE pd = ?";
		
		List<Corso> result = new ArrayList<Corso>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, periodo);
			ResultSet s = st.executeQuery();
			
			while (s.next()) {
				Corso c = new Corso (s.getString("codins"), s.getInt("crediti"), s.getString("nome"), s.getInt("pd"));
				result.add(c);
			}
			conn.close();
			s.close();
			st.close();
		}
		catch (SQLException ee) {
			throw new RuntimeException(ee);
		}
		return result;
	}

	public Map<Corso, Integer> getIscrittiByPeriodo (Integer periodo) {
		String sql=" SELECT c.codins, c.nome, c. crediti, c.pd, COUNT(*) as tot "
				+ "FROM corso.c, iscrizione i "
				+ "WHERE c.codins = i.codins AND c.pd = ? "
				+ "GROUP BY c.codins, c.nome, c.crediti, c.pd ";
		Map<Corso, Integer> result = new HashMap<Corso, Integer>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, periodo);
			ResultSet s = st.executeQuery();
			
			while (s.next()) {
				Corso c = new Corso (s.getString("codins"), s.getInt("crediti"), s.getString("nome"), s.getInt("pd"));
				Integer n = s.getInt("tot");
				result.put(c, n);
			}
			conn.close();
			s.close();
			st.close();
		}
		catch (SQLException ee) {
			throw new RuntimeException(ee);
		}
		return result;
	}
	
	
	
	
	
	
	public List<Studente> getStudentiByCorso (Corso corso) {
		String sql="SELECT s.matricola, s.cognome, s.nome, s.CDS "
				+ "FROM studente s, iscrizione i "
				+"WHERE s.matricola = i.matricola"
				+ "AND i.codins = ?";
		
		List<Studente> result = new LinkedList<Studente> ();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(0, corso.getCodins());
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Studente s = new Studente (rs.getInt("matricola"), rs.getString("nome"), rs.getString("cognome"), rs.getString("CDS"));
				result.add(s);
				
			}
			rs.close();
			st.close();
			conn.close();
			
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return result;
	}
	
	public Map<String, Integer> getDivisioneStudenti(Corso corso) {
		String sql="SELECT s.CDS, COUNT(*) AS tot "
				+ "FROM studente s, iscrizione i "
				+"WHERE s.matricola = i.matricola ND i.codins = ? AND s.cds <> '' "
				+ "AND i.codins = ?";
		
		Map<String, Integer> result = new HashMap<String, Integer> ();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodins());
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				result.put(rs.getString("CDS"), rs.getInt("tot"));
			}
			conn.close();
			rs.close();
			st.close();
		}
		catch (SQLException ee) {
			throw new RuntimeException(ee);
		}
		return result;
	}

	public boolean esisteCorso(Corso corso) {
		String sql ="SELECT * FROM corso where codins = ?";
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(0, corso.getCodins());
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				rs.close();
				st.close();
				conn.close();
				return true;
			}
			else {
				rs.close();
				st.close();
				conn.close();
				return false;
			}
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
