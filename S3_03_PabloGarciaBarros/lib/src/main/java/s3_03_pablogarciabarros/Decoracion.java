package s3_03_pablogarciabarros;

import java.sql.Connection;

public class Decoracion extends Producto{
	
	private String material;
	
	public Decoracion(int id, int cantidad, float precio, String material) {
		super(id, cantidad, precio);
		this.material = material;
	}
	
	
	public void agregarDecoracion(Connection conexion) {
		String insertar = "INSERT INTO decoracion(id_decoracion, material, precio, cantidad, id_tipo) VALUES(" + super.getId() + ",'" + 
			this.material + "'," + super.getPrecio() + "," + super.getCantidad() +", 3)";		
		super.getQuery().insertar(conexion, insertar);
	}
	
	public void retirarDecoracion(Connection conexion, int idRetiro, int cantRetiro) {
		String retiro = "UPDATE decoracion SET cantidad = (cantidad - " + cantRetiro + ") WHERE id_decoracion = " + idRetiro;
		super.retirarProducto(conexion, idRetiro, cantRetiro);
		super.getQuery().retirar(conexion, retiro);
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

	@Override
	public void imprimirStock() {
		// TODO Auto-generated method stub
		
	}

}
