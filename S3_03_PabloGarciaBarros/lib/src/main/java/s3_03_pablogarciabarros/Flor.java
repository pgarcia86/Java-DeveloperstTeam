package s3_03_pablogarciabarros;

import java.sql.Connection;

//Esta es la clase Flor propiamente dicha. Con sus respectivos metodos. Extiende de la clase Producto porque Flor ES un Producto


public class Flor extends Producto{
	
	private String color;
	
	public Flor(int id, int cantidad, float precio, String color) {
		
		super(id, cantidad, precio);
		this.color = color;
	}
	
	
	@Override
	public void agregarProducto(Connection conexion) {

		super.getQuery().insertarProducto(conexion, "producto", super.getId(), 2, super.getCantidad(), super.getPrecio());
		super.getQuery().insertarFlor(conexion, super.getId(), super.getCantidad(), super.getPrecio(), this.color);
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
