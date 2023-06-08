package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

 

public class ParcelaModelo extends Conector {
	PreparedStatement pst;
	//select * from parcelas
	public ArrayList<Parcela>getParcelas(){
	ArrayList<Parcela>parcelas = new ArrayList<Parcela>();
	try {
		pst = getConexion().prepareStatement("Select * from parcelas");
		ResultSet rs =pst.executeQuery();
		while(rs.next()) {
			Parcela parcela = new Parcela();
			parcela.setId(rs.getInt("id"));
			parcela.setNumero(rs.getString("numero"));
			parcela.setM_cuadrados(rs.getInt("m_cuadrados"));
			parcela.setPrecio_dia(rs.getDouble("precio_dia"));
			parcelas.add(parcela);
			
		}
		getConexion().close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return parcelas;
	}
	public Parcela getParcela(int id) {
		Parcela parcela = new Parcela();
		try {
			pst = getConexion().prepareStatement("SELECT * FROM  parcelas where id=?");
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			rs.next();
			parcela.setId(rs.getInt("id"));
			parcela.setNumero(rs.getString("numero"));
			parcela.setM_cuadrados(rs.getInt("m_cuadrados"));
			parcela.setPrecio_dia(rs.getDouble("precio_dia"));
			getConexion().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parcela;
	}
	
	


}
