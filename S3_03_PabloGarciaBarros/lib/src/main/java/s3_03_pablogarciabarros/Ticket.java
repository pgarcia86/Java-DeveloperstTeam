package s3_03_pablogarciabarros;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ticket extends QueriesSQL{
	
	private int id;
	private String fecha;
	private QueriesSQL query;
	
	public Ticket(int id, String fecha) {
		this.id = id;
		this.fecha = fecha;
		//this.query = new QueriesSQL();
	}
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFecha() {
		return fecha;
	}
	
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public void insertarTicket(Connection conexion) {
		
		try {			
			PreparedStatement insertarValores = conexion.prepareStatement("INSERT INTO comandas (id_comanda, Dia) VALUES(" + id + ", '" + fecha + "')");
			insertarValores.executeUpdate("INSERT INTO comandas (id_comanda, Dia) VALUES(" + id + ", '" + fecha + "')");
		}
		catch(SQLException e) {
			System.out.println("No se puede hacer la consulta - insertarTicket");
		}			
	}
	

	@Override
	public void insertar(Connection conexion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertarTipoProducto(Connection conexion, int idTipoProducto, String descripcion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Connection borrarBD(Connection conexion, String nombreBD) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Connection crearBD(Connection conexion, String nombreBD) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Connection usarBD(Connection conexion, String nombreBD) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Connection crearTabla(Connection conexion) {
		// TODO Auto-generated method stub
		return null;
	}
}
