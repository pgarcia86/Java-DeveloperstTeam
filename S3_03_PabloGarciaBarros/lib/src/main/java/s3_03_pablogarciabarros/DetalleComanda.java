package s3_03_pablogarciabarros;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DetalleComanda {

	private int idComanda;
	private int idProducto;
	private int cantidad;
	
	public DetalleComanda(int idComanda, int idProducto, int cantidad) {
		this.idComanda = idComanda;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
	}

	public int getIdComanda() {
		return idComanda;
	}

	public void setIdComanda(int idComanda) {
		this.idComanda = idComanda;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public void insertar(Connection conexion) {
		try {			
			PreparedStatement insertarValores = conexion.prepareStatement("INSERT INTO detalle_comanda (id_comanda, id_producto, cantidad) VALUES(" + this.idComanda + ", " + this.idProducto + ", " + this.cantidad + ")");
			insertarValores.executeUpdate("INSERT INTO detalle_comanda (id_comanda, id_producto, cantidad) VALUES(" + this.idComanda + ", " + this.idProducto + ", " + this.cantidad + ")");			
		}
		catch(SQLException e) {
			System.out.println("No se puede hacer la consulta - Insertar detalle comanda");
		}
	}
}
