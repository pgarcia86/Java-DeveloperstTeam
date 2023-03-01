package s3_03_pablogarciabarros;

import java.sql.Connection;

//Esta es la clase Flor propiamente dicha. Con sus respectivos metodos. Extiende de la clase Producto porque Flor ES un Producto


public class Flor extends Producto{
	
	private String color;
	
	public Flor(int id, int cantidad, float precio, String color) {
		
		super(id, cantidad, precio);
		this.color = color;
	}
	
	public void agregarFlor(Connection conexion) {
		String insertar = "INSERT INTO flor(id_flor, color, precio, cantidad, id_tipo) VALUES(" + super.getId() + ",'" + 
			this.color + "'," + super.getPrecio() + "," + super.getCantidad() +", 2)";
		super.getQuery().actualizar(conexion, insertar);
	}
	
	public boolean retirarFlor(Connection conexion, int idRetiro, int cantRetiro) {
		if(super.retirarProducto(conexion, idRetiro, cantRetiro)) {
			return super.getQuery().actualizar(conexion, "UPDATE flor SET cantidad = (cantidad - " + cantRetiro + ") WHERE id_flor = " + idRetiro);
		}
		else {
			System.out.println("No se pudo retirar la flor");
			return false;
		}
	}

	@Override
	public String toString() {
		return "FLOR: " +
				"\nEl color de la Flor es: " + this.color +
				"\n" + super.toString() ;
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
