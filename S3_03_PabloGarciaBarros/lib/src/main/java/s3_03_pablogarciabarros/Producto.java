package s3_03_pablogarciabarros;

import java.sql.Connection;

public class Producto {
	
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

	
	public void agregarProducto(Connection conexion, int tipoProd) {			
		String queryInsertar = "INSERT INTO productos(id_producto, id_tipo_producto, cantidad, precio) VALUES(" + this.id + ", " + tipoProd + 
			", " + this.cantidad + "," + this.precio + ")";
		query.actualizar(conexion, queryInsertar);
	}
	

	public boolean retirarProducto(Connection conexion, int idRetiro, int cantRetiro) {
		int stock = obtenerStock(conexion, idRetiro);
		String queryRetiroProducto = "UPDATE productos SET cantidad = (cantidad - " + cantRetiro + ") WHERE id_producto = " + idRetiro;
		if(cantRetiro <= stock) {
			return query.actualizar(conexion, queryRetiroProducto);			
		}
		else {
			System.out.println("No se puede retirar mas de lo que hay en stock");
			return false;
		}
	}
	
	public int obtenerStock(Connection conexion, int id) {
		String queryCantidad = "SELECT cantidad FROM productos WHERE id_producto = " + id;
		int cantidad = query.cantidad(conexion, queryCantidad);	
		return cantidad;		
	}
	
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
