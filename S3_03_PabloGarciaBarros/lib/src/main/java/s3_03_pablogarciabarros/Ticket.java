package s3_03_pablogarciabarros;

import java.sql.Connection;

//Esta es la clase Ticket propiamente dicha. Con sus respectivos metodos.

public class Ticket {
	
	private int id;
	private String fecha;
	private int anyo;
	private int mes;
	private int dia;
	private QueriesSQL query;
	
	public Ticket(int id, String fecha) {// int anyo, int mes, int dia) {
		this.id = id;
		/*
		this.anyo = anyo;
		this.mes = mes;
		this.dia = dia;
		*/
		this.fecha = fecha;
		this.query = new QueriesSQL();
	}
	
	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public void insertarTicket(Connection conexion, String query) {		
		this.query.actualizar(conexion, query);		
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getAnyo() {
		return anyo;
	}


	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}


	public int getMes() {
		return mes;
	}


	public void setMes(int mes) {
		this.mes = mes;
	}


	public int getDia() {
		return dia;
	}


	public void setDia(int dia) {
		this.dia = dia;
	}
}
