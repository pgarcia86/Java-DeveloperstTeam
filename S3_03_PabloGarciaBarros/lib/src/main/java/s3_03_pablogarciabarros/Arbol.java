package s3_03_pablogarciabarros;

import java.sql.Connection;

public class Arbol extends Producto{
	
	private float altura;

	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}	
	
	public Arbol(int id, int cantidad, float precio, float altura) {
		
		super(id, cantidad, precio);
		this.altura = altura;
	}
	
	public void agregarArbol(Connection conexion) {
		String insertar = "INSERT INTO arbol(id_arbol, altura, precio, cantidad, id_tipo) VALUES(" + super.getId() + "," + altura + "," + 
			super.getPrecio() +	"," + super.getCantidad() +", 1)";
		super.getQuery().insertar(conexion, insertar);
	}
	
	public void retirarArbol(Connection conexion, int idRetiro, int cantRetiro) {
		String retiro = "UPDATE arbol SET cantidad = (cantidad - " + cantRetiro + ") WHERE id_arbol = " + idRetiro;
		super.retirarProducto(conexion, idRetiro, cantRetiro);
		super.getQuery().retirar(conexion, retiro);
	}
	
	@Override
	public String toString() {
		
		return "ARBOL: " +
			"\nLa altura del Arbol es: " + this.altura +
			"\n" + super.toString() ;
	}
	
	@Override
	public void imprimirStock() {
		// TODO Auto-generated method stub
		
	}

}
