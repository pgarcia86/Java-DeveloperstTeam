package s3_03_pablogarciabarros;

import java.sql.Connection;

public abstract class Producto {
	
	private int cantidad;
	private float precio;
	private int id;
	private QueriesSQL query;
	
	public Producto(int id, int cantidad, float precio) {
		query = new QueriesSQL();
		this.cantidad = cantidad;
		this.precio = precio;
		this.id = id;
	}


	public abstract void agregarProducto(Connection conexion);


	@Override
	public String toString() {
		return "El id es: " + this.id + 
			"\nEl precio es: " + this.precio +
			"\nHay en stock: " + this.cantidad;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public QueriesSQL getQuery() {
		return query;
	}

	public void setQuery(QueriesSQL query) {
		this.query = query;
	}
}
