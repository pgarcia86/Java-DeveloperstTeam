package s3_03_pablogarciabarros;

import java.sql.Connection;

public class Flor extends Producto{
	
	private String color;
	
	public Flor(int id, int cantidad, float precio, String color) {
		
		super(id, cantidad, precio);
		this.color = color;
	}
	
	public void agregarFlor(Connection conexion) {
		String insertar = "INSERT INTO flor(id_flor, color, precio, cantidad, id_tipo) VALUES(" + super.getId() + ",'" + 
			this.color + "'," + super.getPrecio() + "," + super.getCantidad() +", 2)";
		super.getQuery().insertar(conexion, insertar);
	}
	
	public void retirarFlor(Connection conexion, int idRetiro, int cantRetiro) {
		String retiro = "UPDATE flor SET cantidad = (cantidad - " + cantRetiro + ") WHERE id_flor = " + idRetiro;
		super.retirarProducto(conexion, idRetiro, cantRetiro);
		super.getQuery().retirar(conexion, retiro);
	}

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

	@Override
	public void imprimirStock() {
		// TODO Auto-generated method stub
		
	}
}
