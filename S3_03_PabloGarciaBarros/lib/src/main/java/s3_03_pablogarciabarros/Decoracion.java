package s3_03_pablogarciabarros;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//Esta es la clase Decoracion propiamente dicha. Con sus respectivos metodos. Extiende de la clase Producto porque Decoracion ES un Producto


public class Decoracion extends Producto{
	
	private String material;
	
	public Decoracion(int id, int cantidad, float precio, int idTipoProd, String material) {
		super(id, cantidad, precio, idTipoProd);
		this.material = material;
	}	

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}
	
	
	//METODO SOBREESCRITO
	@Override
	public void insertar(Connection conexion) {
		
		super.insertar(conexion);
		
		try {			
			PreparedStatement insertarValores = conexion.prepareStatement("INSERT INTO decoracion (id_decoracion, material, precio, cantidad, id_tipo) VALUES(" + super.getId() + ", '" + this.material + "', " + super.getPrecio() + ", " + super.getCantidad() +", 3)");
			insertarValores.executeUpdate("INSERT INTO decoracion (id_decoracion, material, precio, cantidad, id_tipo) VALUES(" + super.getId() + ", '" + material + "', " + super.getPrecio() + ", " + super.getCantidad() +", 3)");
		}
		catch(SQLException e) {
			System.out.println("No se puede hacer la consulta - Insertar producto");
		}		
	}

	
	

	@Override
	public String toString() {
		return "DECORACION: " +
				"\nEl material de la decoracion es: " + this.material +
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
