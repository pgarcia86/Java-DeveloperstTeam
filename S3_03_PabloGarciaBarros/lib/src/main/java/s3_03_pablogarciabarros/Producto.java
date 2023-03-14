package s3_03_pablogarciabarros;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Producto extends  QueriesSQL{
	
	private int cantidad;
	private float precio;
	private int id;
	private int idTipoProd;
	//private QueriesSQL query;
	
	public Producto(int id, int cantidad, float precio, int idTipoProd) {
		//query = new QueriesSQL();
		this.cantidad = cantidad;
		this.precio = precio;
		this.id = id;
		this.idTipoProd = idTipoProd;
	}


	@Override
	public String toString() {
		return "El id es: " + this.id + 
			"\nEl precio es: " + this.precio +
			"\nHay en stock: " + this.cantidad +
			"\nPertenece al grupo: " + this.idTipoProd;
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


	@Override
	public void insertar(Connection conexion) {
		try {			
			PreparedStatement insertarValores = conexion.prepareStatement("INSERT INTO producto (id_producto, id_tipo_producto, cantidad, precio) VALUES(" + this.id + ", " + this.idTipoProd + ", " + this.cantidad + ", " + precio +")");
			insertarValores.executeUpdate("INSERT INTO producto (id_producto, id_tipo_producto, cantidad, precio) VALUES(" + this.id + ", " + this.idTipoProd + ", " + this.cantidad + ", " + this.precio +")");
		}
		catch(SQLException e) {
			System.out.println("No se puede hacer la consulta - Insertar producto");
		}
	}	
	
	
	//AGREGO EL PRODUCTO A LA BASE DE DATOS, TAMBIEN ACTUALIZO LO QUE ME QUEDA CARGADO EN EL OBJETO
	public void agregar(Connection conexion, int id, int cant) {
		
		ArrayList<String>queries = new ArrayList<String>() ;
		queries.add("UPDATE producto SET cantidad = (cantidad + " + cant + ") WHERE id_producto = " + id);
		queries.add("UPDATE arbol SET cantidad = (cantidad + " + cant + ") WHERE id_arbol = " + id);
		queries.add("UPDATE flor SET cantidad = (cantidad + " + cant + ") WHERE id_flor = " + id);
		queries.add("UPDATE decoracion SET cantidad = (cantidad + " + cant + ") WHERE id_decoracion = " + id);
		
		this.cantidad += cant;
		
		try {
			for (String query : queries) {
				PreparedStatement insertarValores = conexion.prepareStatement(query);
				insertarValores.executeUpdate(query);
			}			
		}
		catch(SQLException e) {
			System.out.println("No se pudo hacer la consulta - sumarStock");
		}
	}
	
	public void retirar(Connection conexion, int id, int cant) {
		ArrayList<String>queries = new ArrayList<String>() ;
		queries.add("UPDATE producto SET cantidad = (cantidad - " + cant + ") WHERE id_producto = " + id);
		queries.add("UPDATE arbol SET cantidad = (cantidad - " + cant + ") WHERE id_arbol = " + id);
		queries.add("UPDATE flor SET cantidad = (cantidad - " + cant + ") WHERE id_flor = " + id);
		queries.add("UPDATE decoracion SET cantidad = (cantidad - " + cant + ") WHERE id_decoracion = " + id);
		
		this.cantidad -= cant;
		
		try {
			for (String query : queries) {
				PreparedStatement insertarValores = conexion.prepareStatement(query);
				insertarValores.executeUpdate(query);
			}			
		}
		catch(SQLException e) {
			System.out.println("No se pudo hacer la consulta - sumarStock");
		}
	}

	
	//TODO ESTO SON METODOS QUE SE HEREDAN DE LA CLASE QUERISQL PERO NO ESTAN IMPLEMENTADOS

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
