package s3_03_pablogarciabarros;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//Esta es la clase Arbol propiamente dicha. Con sus respectivos metodos. Extiende de la clase Producto porque Arbol ES un Producto

public class Arbol extends Producto{
	
	private float altura;
	
	public Arbol(int id, int cantidad, float precio, int idTipoProd, float altura) {
		
		super(id, cantidad, precio, idTipoProd);
		this.altura = altura;
	}

	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}	
	
	//METODO SOBREESCRITO
	@Override
	public void insertar(Connection conexion) {	
		
		super.insertar(conexion);
		
		try {			
			PreparedStatement insertarValores = conexion.prepareStatement("INSERT INTO arbol (id_arbol, altura, precio, cantidad, id_tipo) VALUES(" + super.getId() + ", '" + this.altura + "', " + super.getPrecio() + ", " + super.getCantidad() +", 1)");
			insertarValores.executeUpdate("INSERT INTO arbol (id_arbol, altura, precio, cantidad, id_tipo) VALUES(" + super.getId() + ", '" + this.altura + "', " + super.getPrecio() + ", " + super.getCantidad() +", 1)");
		}
		catch(SQLException e) {
			System.out.println("No se puede hacer la consulta - Insertar arbol");
		}
	}

	
	@Override
	public String toString() {		
		return "ARBOL: " +
			"\nLa altura del Arbol es: " + this.altura +
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
