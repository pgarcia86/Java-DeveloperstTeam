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
	
	@Override
	public void agregarProducto(Connection conexion) {	
		
		super.getQuery().insertarProducto(conexion, "producto", super.getId(), 1, super.getCantidad(), super.getPrecio());
		super.getQuery().insertarArbol(conexion, super.getId(), super.getCantidad(), super.getPrecio(), this.altura);
	}

	
	@Override
	public String toString() {		
		return "ARBOL: " +
			"\nLa altura del Arbol es: " + this.altura +
			"\n" + super.toString() ;
	}
}
