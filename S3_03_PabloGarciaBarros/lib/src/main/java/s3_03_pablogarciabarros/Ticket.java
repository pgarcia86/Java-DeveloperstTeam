package s3_03_pablogarciabarros;

import java.sql.Connection;

public class Ticket {
	
	private int id;
	private String fecha;
	private QueriesSQL query;
	
	public Ticket(int id, String fecha) {
		this.id = id;
		this.fecha = fecha;
		this.query = new QueriesSQL();
	}
	
	public void insertarTicket(Connection conexion, int id, String fecha) {
		
		query.insertarTicket(conexion, id, fecha);
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
}
