package modelo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class ReservaModelo extends Conector{
	PreparedStatement pst;
/*
	"INSERT INTO reservas(nombre_usuario, apellido_usuario, dni_usuario, numero_usuarios, inicio_reserva, fin_reserva,  luz, id_parcela) "
			+ "VALUES (?,?,?,?,?,?,?,?)"
			
	"DELETE FROM reservas WHERE id = ?"
		*/
		
	public boolean insertarReserva(Reserva reserva) {
		try {
			pst = getConexion().prepareStatement("INSERT INTO reservas(nombre_usuario,apellido_usuario,dni_usuario,numero_usuarios,inicio_reserva,fin_reserva,luz,id_parcela)VALUES(?,?,?,?,?,?,?,?)");
			pst.setString(1, reserva.getNombre_usuario());
			pst.setString(2, reserva.getApellido_usuario());
			pst.setString(3, reserva.getDni_usuario());
			pst.setInt(4, reserva.getNumero_usuarios());
			pst.setDate(5, new Date(reserva.getInicio_reserva().getTime()));
			pst.setDate(6, new Date(reserva.getFin_reserva().getTime()));
			pst.setBoolean(7, false);
			pst.setInt(8,reserva.getParcela().getId());
			pst.execute();
			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
public Reserva getReserva(int id) {
	Reserva reserva = new Reserva();
	ReservaModelo rm = new ReservaModelo();
	ParcelaModelo pm = new ParcelaModelo();
	try {
		pst = getConexion().prepareStatement("SELECT * FROM reservas where id=?");
		pst.setInt(1, id);
		ResultSet rs = pst.executeQuery();
		rs.next();
		reserva.setId(rs.getInt("id"));
		reserva.setNombre_usuario(rs.getString("nombre_usuario"));
		reserva.setApellido_usuario(rs.getString("apellido_usuario"));
		reserva.setInicio_reserva(rs.getDate("inicio_reserva"));
		reserva.setFin_reserva(rs.getDate("fin_reserva"));
		reserva.setFecha_reserva(rs.getDate("fecha_reserva"));
		reserva.setLuz(false);
		reserva.setParcela(pm.getParcela(rs.getInt("id_parcela")));
		getConexion().close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return reserva;
}
public boolean cancelarReserva(int id) {
	try {
		pst = getConexion().prepareStatement("DELETE FROM reservas WHERE id =?");
		pst.setInt(1,id);
		pst.execute();
		return true;
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return false;
	
}
public int getId() {
	int id=0;
	try {
		pst = getConexion().prepareStatement("SELECT max(id)from reservas");
		ResultSet rs = pst.executeQuery();
		rs.next();
		id=rs.getInt(1);
		
		return id;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return id;
}

}
