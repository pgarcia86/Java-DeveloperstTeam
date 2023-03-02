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
		String queryInsertarProducto = "INSERT INTO productos(id_producto, id_tipo_producto, cantidad, precio) VALUES(" + super.getId() + ", " + "1 " + 
				", " + super.getCantidad() + "," + super.getPrecio() + ")";
		String queryInsertarFlor = "INSERT INTO flor(id_flor, color, precio, cantidad, id_tipo) VALUES(" + super.getId() + ",'" + this.color + "'," + 
			super.getPrecio() +	"," + super.getCantidad() +", 1)";
		
		super.getQuery().actualizar(conexion, queryInsertarProducto);
		super.getQuery().actualizar(conexion, queryInsertarFlor);
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
