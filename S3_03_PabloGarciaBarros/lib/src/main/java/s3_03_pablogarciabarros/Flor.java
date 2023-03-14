package s3_03_pablogarciabarros;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//Esta es la clase Flor propiamente dicha. Con sus respectivos metodos. Extiende de la clase Producto porque Flor ES un Producto


public class Flor extends Producto{
	
	private String color;
	
	public Flor(int id, int cantidad, float precio, int idTipoProd, String color) {
		
		super(id, cantidad, precio, idTipoProd);
		this.color = color;
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	
	//METODO SOBREESCRITO
	@Override
	public void insertar(Connection conexion) {
		
		super.insertar(conexion);		
		try {			
			PreparedStatement insertarValores = conexion.prepareStatement("INSERT INTO flor (id_flor, color, precio, cantidad, id_tipo) VALUES(" + super.getId() + ", '" + this.color + "', " + super.getPrecio() + ", " + super.getCantidad() +", 2)");
			insertarValores.executeUpdate("INSERT INTO flor (id_flor, color, precio, cantidad, id_tipo) VALUES(" + super.getId() + ", '" + this.color + "', " + super.getPrecio() + ", " + super.getCantidad() +", 2)");			
		}
		catch(SQLException e) {
			System.out.println("No se puede hacer la consulta - Insertar producto");
		}		
	}
	
	


	@Override
	public String toString() {
		return "FLOR: " +
				"\nEl color de la Flor es: " + this.color +
				"\n" + super.toString() ;
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
