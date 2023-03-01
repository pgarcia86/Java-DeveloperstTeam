package s3_03_pablogarciabarros;

import java.sql.Connection;

//Esta es la clase Arbol propiamente dicha. Con sus respectivos metodos. Extiende de la clase Producto porque Arbol ES un Producto

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
		super.getQuery().actualizar(conexion, insertar);
	}
	
	public boolean retirarArbol(Connection conexion, int idRetiro, int cantRetiro) {
		if(super.retirarProducto(conexion, idRetiro, cantRetiro)) {
			return super.getQuery().actualizar(conexion, "UPDATE arbol SET cantidad = (cantidad - " + cantRetiro + ") WHERE id_arbol = " + idRetiro);
		}
		else {
			System.out.println("No se pudo retirar el arbol");
			return false;
		}
	}
	
	@Override
	public String toString() {		
		return "ARBOL: " +
			"\nLa altura del Arbol es: " + this.altura +
			"\n" + super.toString() ;
	}
}
