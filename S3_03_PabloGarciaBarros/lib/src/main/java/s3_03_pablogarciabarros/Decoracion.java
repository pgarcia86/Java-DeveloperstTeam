package s3_03_pablogarciabarros;

import java.sql.Connection;

//Esta es la clase Decoracion propiamente dicha. Con sus respectivos metodos. Extiende de la clase Producto porque Decoracion ES un Producto


public class Decoracion extends Producto{
	
	private String material;
	
	public Decoracion(int id, int cantidad, float precio, String material) {
		super(id, cantidad, precio);
		this.material = material;
	}
	
	
	public void agregarDecoracion(Connection conexion) {
		String insertar = "INSERT INTO decoracion(id_decoracion, material, precio, cantidad, id_tipo) VALUES(" + super.getId() + ",'" + 
			this.material + "'," + super.getPrecio() + "," + super.getCantidad() +", 3)";		
		super.getQuery().actualizar(conexion, insertar);
	}
	
	public boolean retirarDecoracion(Connection conexion, int idRetiro, int cantRetiro) {
		if(super.retirarProducto(conexion, idRetiro, cantRetiro)) {
			return super.getQuery().actualizar(conexion, "UPDATE decoracion SET cantidad = (cantidad - " + cantRetiro + ") WHERE id_decoracion = " + idRetiro);
		}
		else {
			System.out.println("No se pudo retirar la decoracion");
			return false;
		}
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
