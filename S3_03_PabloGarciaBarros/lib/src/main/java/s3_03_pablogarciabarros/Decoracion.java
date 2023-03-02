package s3_03_pablogarciabarros;

import java.sql.Connection;

//Esta es la clase Decoracion propiamente dicha. Con sus respectivos metodos. Extiende de la clase Producto porque Decoracion ES un Producto


public class Decoracion extends Producto{
	
	private String material;
	
	public Decoracion(int id, int cantidad, float precio, String material) {
		super(id, cantidad, precio);
		this.material = material;
	}
	
	
	@Override
	public void agregarProducto(Connection conexion) {
		String queryInsertarProducto = "INSERT INTO producto(id_producto, id_tipo_producto, cantidad, precio) VALUES(" + super.getId() + ", " + "3 " + 
				", " + super.getCantidad() + "," + super.getPrecio() + ")";
		String queryInsertarDecoracion = "INSERT INTO decoracion(id_Decoracion, material, precio, cantidad, id_tipo) VALUES(" + super.getId() + ",'" + this.material + "'," + 
			super.getPrecio() +	"," + super.getCantidad() +", 3)";
		super.getQuery().actualizar(conexion, queryInsertarProducto);
		super.getQuery().actualizar(conexion, queryInsertarDecoracion);
	}
	

	@Override
	public String toString() {
		return "DECORACION: " +
				"\nEl material de la decoracion es: " + this.material +
				"\n" + super.toString() ;
	}
	
	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}
}
