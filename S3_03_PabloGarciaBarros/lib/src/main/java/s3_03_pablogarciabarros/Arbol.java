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
		String queryInsertarProducto = "INSERT INTO productos(id_producto, id_tipo_producto, cantidad, precio) VALUES(" + super.getId() + ", " + "1 " + 
				", " + super.getCantidad() + "," + super.getPrecio() + ")";
		String queryInsertarArbol = "INSERT INTO arbol(id_arbol, altura, precio, cantidad, id_tipo) VALUES(" + super.getId() + "," + this.altura + "," + 
			super.getPrecio() +	"," + super.getCantidad() +", 1)";
		super.getQuery().actualizar(conexion, queryInsertarProducto);
		super.getQuery().actualizar(conexion, queryInsertarArbol);		
	}

	
	@Override
	public String toString() {		
		return "ARBOL: " +
			"\nLa altura del Arbol es: " + this.altura +
			"\n" + super.toString() ;
	}
}
