package s3_03_pablogarciabarros;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueriesConsulta {
	
	public QueriesConsulta() {}
	
 	
	//ESTE METODO ME DEVUELVE TRUE SI ENCUENTRA EL PRODUCTO
 	public static boolean buscar(Connection conexion, int id) {		
			ResultSet resultado = null;
		try {
			Statement consulta = conexion.createStatement();
			resultado = consulta.executeQuery("SELECT * FROM producto WHERE id_producto = " + id);
			while(resultado.next()) {
				if(resultado != null) {
					return true;
				}
			}
		}
		catch(SQLException e) {
			System.out.println("No se puede buscar el producto");
		}
		return false;
	}
	
 	//ESTE METODO DE DEVUELVE EL ID DEL PRODUCTO BUSCADO
	public static int getId(Connection conexion, int idBuscado) {
		int id = 0;
		ResultSet resultado = null;
		try {
			Statement consulta = conexion.createStatement();
			resultado = consulta.executeQuery("SELECT id_producto FROM producto WHERE id_producto = " + idBuscado);
			while(resultado.next()) {
				id = resultado.getInt("id_producto");
			}
		}
		catch(SQLException e) {
			System.out.println("No se puede hacer la consulta -- getPrecio");
		}
		return id;
	}
 	
	//ESTE METODO ME DEVUELVE EL PRECIO DEL PRODUCTO BUSCADO
	public static float getPrecio(Connection conexion, int id) {
		float precio = 0f;
		ResultSet resultado = null;
		try {
			Statement consulta = conexion.createStatement();
			resultado = consulta.executeQuery("SELECT precio FROM producto WHERE id_producto = " + id);
			while(resultado.next()) {
				precio = resultado.getFloat("precio");
			}
		}
		catch(SQLException e) {
			System.out.println("No se puede hacer la consulta -- getPrecio");
		}
		return precio;
	}

	//ESTE METODO ME DEVUELVE LA CANTIDAD DEL PRODUCTO BUSCADO
	public static int getCantidad(Connection conexion, int id) {
		int cantidad = 0;
		ResultSet resultado = null;
		try {
			Statement consulta = conexion.createStatement();
			resultado = consulta.executeQuery("SELECT cantidad FROM producto WHERE id_producto = " + id);
			while(resultado.next()) {
				cantidad = resultado.getInt("cantidad");
			}
		}
		catch(SQLException e) {
			System.out.println("No se puede hacer la consulta -- getCantidad");
		}
		return cantidad;
	}
	
	//ESTE METODO ME DEVUELVE EL TIPO DE PRODUCTO DEL PRODUCTO BUSCADO
	public static int getTipoProducto(Connection conexion, int id) {
		
		int idTipoProducto = 0;
		try {
			Statement consulta = conexion.createStatement();
			ResultSet resultado = consulta.executeQuery("SELECT id_tipo_producto FROM producto WHERE id_producto = " + id);
			while(resultado.next()) {
				idTipoProducto = resultado.getInt("id_tipo_producto");
			}
		}
		catch(SQLException e){
			System.out.println("No se puede hacer la consulta - buscarTipoProducto");
		}
		
		return idTipoProducto;
	}
	
	//ESTE METODO ME DEVUELVE EL PRECIO TOTAL DE LA COMANDA QUE LE PASO POR ID
	public static float precioComanda(Connection conexion, int idComanda) {
		ResultSet resultado = null;	
		try {
			Statement consulta = conexion.createStatement();
			resultado = consulta.executeQuery("SELECT SUM(dc.cantidad * p.precio) AS 'Precio Comanda' FROM producto AS p "
				+ "INNER JOIN detalle_comanda AS dc ON p.id_producto = dc.id_producto INNER JOIN comandas AS c ON "
				+ "c.id_comanda = dc.id_comanda WHERE c.id_comanda = " + idComanda);
			while(resultado.next()) {
				return resultado.getFloat("Precio Comanda");
			}
		} catch (SQLException e) {
			System.out.println("No se puede hacer la consulta - precioComanda");
		}
		return -1;
	}
	
	//ESTE METODO DEVUELVE EL PRECIO FINAL DE LAS VENTAS
	public static float calcularTotalVentas(Connection conexion) {
		ResultSet resultado = null;
		try {
			Statement consulta = conexion.createStatement();
			resultado = consulta.executeQuery("SELECT SUM(dc.cantidad * p.precio) AS 'Ventas Total' FROM detalle_comanda AS dc "
					+ "INNER JOIN producto AS p ON dc.id_producto = p.id_producto");
			while(resultado.next()) {
				return resultado.getFloat("Ventas Total");
			}
		} catch (SQLException e) {
			System.out.println("No se puede hacer la consulta - calcularTotalVentas");
		}
		return -1;		
	}
	
	//ESTE METODO IMPRIME POR PANTALLA LAS COMANDAS EN LAS FECHAS ESPECIFICADAS
	public static void buscarComandasFecha(Connection conexion, String comienzo, String fin){
		ResultSet resultado = null;
		try {
			Statement consulta = conexion.createStatement();
			resultado = consulta.executeQuery("SELECT c.id_comanda, c.dia, SUM(p.precio * dc.cantidad) AS 'Precio Comanda'FROM comandas AS c " +
					"INNER JOIN detalle_comanda AS dc ON dc.id_comanda = c.id_comanda " + 
					"INNER JOIN producto AS p ON p.id_producto = dc.id_producto " + 
					"WHERE c.Dia >= '" + comienzo + "' AND c.Dia <= '" + fin + "'" + 
				    "GROUP BY c.id_comanda ORDER BY c.dia");
			while(resultado.next()) {
				System.out.println("ID COMANDA: " + resultado.getInt("id_comanda") + " - FECHA: " + resultado.getDate("Dia") + " - VALOR: " 
					+ resultado.getFloat("Precio Comanda"));
			}
		} catch (SQLException e) {
			System.out.println("No se puede hacer la consulta - buscarComandasFecha");
		}		
	}
	
	//ESTE METODO DEVUELVE EL PRECIO TOTAL DEL STOCK
	public static float calcularValorTotalStock(Connection conexion) {
		ResultSet resultado = null;
		try {
			Statement consulta = conexion.createStatement();
			resultado = consulta.executeQuery("SELECT SUM(p.cantidad * p.precio) AS 'Valor Stock Total' FROM producto AS p");
			while(resultado.next()) {
				return resultado.getFloat("Valor Stock Total");
			}
		} catch (SQLException e) {
			System.out.println("No se puede hacer la consulta - calcularTotalVentas");
		}
		return -1;
	}
	
	//DEVUELVE EL STOCK DEL PRODUCTO QUE RECIBE POR PARAMETRO
	public static int getStock(Connection conexion, int id) {
		ResultSet resultado = null;	
		try {
			Statement consulta = conexion.createStatement();
			resultado = consulta.executeQuery("SELECT SUM(cantidad) AS cantidad FROM producto WHERE id_producto = " + id);
			while(resultado.next()) {
				return resultado.getInt("cantidad");
			}
		} 
		catch (SQLException e) {
			System.out.println("No se puede hacer la consulta - imprimirStock");
		}
		return -1;
	} 	
	
	//DEVUELVE EL STOCK DEL TIPO DE PRODUCTO QUE RECIBE POR PARAMETRO
	public static int getStockCategoria(Connection conexion, int tipoProducto) {
		ResultSet resultado = null;	
		try {
			Statement consulta = conexion.createStatement();
			resultado = consulta.executeQuery("SELECT SUM(cantidad) AS cantidad FROM producto WHERE id_tipo_producto = " + tipoProducto + " GROUP BY id_tipo_producto");
			while(resultado.next()) {
				return resultado.getInt("cantidad");
			}
		} catch (SQLException e) {
			System.out.println("No se puede hacer la consulta - imprimirStockCategoria");
		}
		return -1;
	}
	
	//DEVUELVE EL STOCK TOTAL DE PRODUCTOS
	public static int getStockTotal(Connection conexion) {
		ResultSet resultado = null;	
		try {
			Statement consulta = conexion.createStatement();
			resultado = consulta.executeQuery("SELECT SUM(cantidad) AS cantidad FROM producto GROUP BY p.id_producto");
			while(resultado.next()) {
				return resultado.getInt("cantidad");
			}
		} catch (SQLException e) {
			System.out.println("No se puede hacer la consulta - imprimirStockTotal");
		}
		return -1;
	}
}
