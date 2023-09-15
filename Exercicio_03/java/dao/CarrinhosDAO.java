package dao;

import model.Carrinhos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class CarrinhosDAO extends DAO {	
	public CarrinhosDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	
	public boolean insert(Carrinhos carrinhos) {
		boolean status = false;
		try {
			String sql = "INSERT INTO produto (descricao, preco, quantidade, datafabricacao, datavalidade) "
		               + "VALUES ('" + carrinhos.getDescricao() + "', "
		               + carrinhos.getPreco() + ", " + carrinhos.getQuantidade() + ", ?, ?);";
			PreparedStatement st = conexao.prepareStatement(sql);
		    st.setTimestamp(1, Timestamp.valueOf(carrinhos.getData_Fabri()));
			st.setDate(2, Date.valueOf(carrinhos.getData_Valid()));
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Carrinhos get(int id) {
		Carrinhos carrinhos = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM produto WHERE id="+id;
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	carrinhos = new Carrinhos(rs.getInt("id"), rs.getString("descricao"), (float)rs.getDouble("preco"), 
	                				   rs.getInt("quantidade"), 
	        			               rs.getTimestamp("datafabricacao").toLocalDateTime(),
	        			               rs.getDate("datavalidade").toLocalDate());
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return carrinhos;
	}
	
	
	public List<Carrinhos> get() {
		return get("");
	}

	
	public List<Carrinhos> getOrderByID() {
		return get("id");		
	}
	
	
	public List<Carrinhos> getOrderByDescricao() {
		return get("descricao");		
	}
	
	
	public List<Carrinhos> getOrderByPreco() {
		return get("preco");		
	}
	
	
	private List<Carrinhos> get(String orderBy) {
		List<Carrinhos> produtos = new ArrayList<Carrinhos>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM produto" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Carrinhos p = new Carrinhos(rs.getInt("id"), rs.getString("descricao"), (float)rs.getDouble("preco"), 
	        			                rs.getInt("quantidade"),
	        			                rs.getTimestamp("datafabricacao").toLocalDateTime(),
	        			                rs.getDate("datavalidade").toLocalDate());
	        	carrinhos.add(p);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return produtos;
	}
	
	
	public boolean update(Carrinhos carrinhos) {
		boolean status = false;
		try {  
			String sql = "UPDATE produto SET descricao = '" + carrinhos.getDescricao() + "', "
					   + "preco = " + carrinhos.getPreco() + ", " 
					   + "quantidade = " + carrinhos.getQuantidade() + ","
					   + "datafabricacao = ?, " 
					   + "datavalidade = ? WHERE id = " + carrinhos.getId();
			PreparedStatement st = conexao.prepareStatement(sql);
		    st.setTimestamp(1, Timestamp.valueOf(carrinhos.getData_Fabri()));
			st.setDate(2, Date.valueOf(carrinhos.getData_Valid()));
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public boolean delete(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM produto WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}