package s3_03_pablogarciabarros;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//En esta clase dejo todas las consultas correspondietes a la base de datos - Aqui solo hay SQL manejando los objetos 

public class QueriesSQL {
	
	
	public boolean insertarProducto(Connection conexion, String nombreTabla, int id, int tipo_prod, int cant, float precio) {
		try {			
			PreparedStatement insertarValores = conexion.prepareStatement("INSERT INTO " + nombreTabla + " (id_" + nombreTabla + ", id_tipo_producto, cantidad, precio) VALUES(" + id + ", " + tipo_prod + ", " + cant + ", " + precio +")");
			insertarValores.executeUpdate("INSERT INTO " + nombreTabla + " (id_" + nombreTabla + ", id_tipo_producto, cantidad, precio) VALUES(" + id + ", " + tipo_prod + ", " + cant + ", " + precio +")");
			return true;
		}
		catch(SQLException e) {
			System.out.println("No se puede hacer la consulta - Insertar producto");
			return false;
		}
	}
	
	
	public boolean insertarArbol(Connection conexion, int id, int cant, float precio, float altura) {
		try {			
			PreparedStatement insertarValores = conexion.prepareStatement("INSERT INTO arbol (id_arbol, altura, precio, cantidad, id_tipo) VALUES(" + id + ", " + altura + ", " + precio + ", " + cant +", 1)");
			insertarValores.executeUpdate("INSERT INTO arbol (id_arbol, altura, precio, cantidad, id_tipo) VALUES(" + id + ", " + altura + ", " + precio + ", " + cant +", 1)");
			return true;
		}
		catch(SQLException e) {
			System.out.println("No se puede hacer la consulta - Insertar producto");
			return false;
		}
	}
	
	
	public boolean insertarFlor(Connection conexion, int id, int cant, float precio, String color) {
		try {			
			PreparedStatement insertarValores = conexion.prepareStatement("INSERT INTO flor (id_flor, color, precio, cantidad, id_tipo) VALUES(" + id + ", '" + color + "', " + precio + ", " + cant +", 2)");
			insertarValores.executeUpdate("INSERT INTO flor (id_flor, color, precio, cantidad, id_tipo) VALUES(" + id + ", '" + color + "', " + precio + ", " + cant +", 2)");
			return true;
		}
		catch(SQLException e) {
			System.out.println("No se puede hacer la consulta - Insertar producto");
			return false;
		}
	}
	
	
	public boolean insertarDecoracion(Connection conexion, int id, int cant, float precio, String material) {
		try {			
			PreparedStatement insertarValores = conexion.prepareStatement("INSERT INTO decoracion (id_decoracion, material, precio, cantidad, id_tipo) VALUES(" + id + ", '" + material + "', " + precio + ", " + cant +", 3)");
			insertarValores.executeUpdate("INSERT INTO decoracion (id_decoracion, material, precio, cantidad, id_tipo) VALUES(" + id + ", '" + material + "', " + precio + ", " + cant +", 3)");
			return true;
		}
		catch(SQLException e) {
			System.out.println("No se puede hacer la consulta - Insertar producto");
			return false;
		}
	}
	
	
	public void insertarTipoProducto(Connection conexion, int idTipoProducto, String descripcion) {
		try {			
			PreparedStatement insertarValores = conexion.prepareStatement("INSERT INTO tipo_producto (id_tipo_prodcuto, descripcion) VALUES(" + idTipoProducto + ", '" + descripcion + "')");
			insertarValores.executeUpdate("INSERT INTO tipo_producto (id_tipo_prodcuto, descripcion) VALUES(" + idTipoProducto + ", '" + descripcion + "')");
		}
		catch(SQLException e) {
			System.out.println("No se puede hacer la consulta - insertarTipoProducto");
		}
	}
	
	
	public void insertarTicket(Connection conexion, int id, String fecha) {
		
		try {			
			PreparedStatement insertarValores = conexion.prepareStatement("INSERT INTO comandas (id_comanda, Dia) VALUES(" + id + ", '" + fecha + "')");
			insertarValores.executeUpdate("INSERT INTO comandas (id_comanda, Dia) VALUES(" + id + ", '" + fecha + "')");
		}
		catch(SQLException e) {
			System.out.println("No se puede hacer la consulta - insertarTicket");
		}	
		
	}
	
	
	public int getStock(Connection conexion, int id) {			
		int cantidad = 0;		
		try {
			Statement consulta = conexion.createStatement();
			ResultSet resultado = consulta.executeQuery("SELECT cantidad FROM producto WHERE id_producto = " + id
					);
			while(resultado.next()) {
				return resultado.getInt(1);
			}			
		}
		catch(SQLException e) {
			System.out.println("No se puede hacer la consulta");
		}		
		return cantidad;
	}
	
	
	public int getIdProducto(Connection conexion, int id) {			
		int cantidad = 0;		
		try {
			Statement consulta = conexion.createStatement();
			ResultSet resultado = consulta.executeQuery("SELECT id_producto FROM producto WHERE id_producto = " + id);
			while(resultado.next()) {
				return resultado.getInt(1);
			}			
		}
		catch(SQLException e) {
			System.out.println("No se puede hacer la consulta - get");
		}		
		return cantidad;
	}
	
	
	public int getIdTipoProducto(Connection conexion, int id) {
		int cantidad = 0;		
		try {
			Statement consulta = conexion.createStatement();
			ResultSet resultado = consulta.executeQuery("SELECT id_tipo_producto FROM producto WHERE id_producto = " + id);
			while(resultado.next()) {
				return resultado.getInt(1);
			}			
		}
		catch(SQLException e) {
			System.out.println("No se puede hacer la consulta - getid tipo producto");
		}		
		return cantidad;
		
	}
	
	
	public Connection ejecutar(Connection conexion, String query) {
		try {
			conexion.createStatement().execute(query);
		}
		catch(SQLException e) {
			System.out.println("ERROR");
		}
		return conexion;
	}
	
	
	public boolean sumarStock(Connection conexion, int id, int cant, String nombreTabla) {
				
		try {
			PreparedStatement insertarValores = conexion.prepareStatement("UPDATE " + nombreTabla + " SET cantidad = (cantidad + " + cant + ") WHERE id_" + nombreTabla + " = " + id);
			insertarValores.executeUpdate("UPDATE " + nombreTabla + " SET cantidad = (cantidad + " + cant + ") WHERE id_" + nombreTabla + " = " + id);
			return true;
		}
		catch(SQLException e) {
			System.out.println("No se pudo hacer la consulta - sumarStock");
			return false;
		}
	}

	
	public boolean restarStock(Connection conexion, int id, int cant, String nombreTabla) {
		
		try {
			PreparedStatement insertarValores = conexion.prepareStatement("UPDATE " + nombreTabla + " SET cantidad = (cantidad - " + cant + ") WHERE id_" + nombreTabla +" = " + id);
			insertarValores.executeUpdate("UPDATE " + nombreTabla + " SET cantidad = (cantidad - " + cant + ") WHERE id_" + nombreTabla +" = " + id);
			return true;
		}
		catch(SQLException e) {
			System.out.println("No se pudo hacer la consulta - restarStock");
			return false;
		}
		
	}
	
	
	public boolean insertarDetalleComanda(Connection conexion, int idComanda, int id, int cant) {
		try {
			PreparedStatement insertarValores = conexion.prepareStatement("INSERT INTO detalle_comanda(id_comanda, id_producto, cantidad) VALUES(" + 
				idComanda + ", " + id + ", " + cant + ")");
			insertarValores.executeUpdate("INSERT INTO detalle_comanda(id_comanda, id_producto, cantidad) VALUES(" + 
					idComanda + ", " + id + ", " + cant + ")");
			return true;
		}
		catch(SQLException e) {
			System.out.println("No se puede insertar el detalle en la comanda");
			return false;
		}
		
	}
	
	
	public int imprimirStockArboles(Connection conexion) {
		
		ResultSet resultado = null;
		
		try {
			Statement consulta = conexion.createStatement();
			resultado = consulta.executeQuery("SELECT SUM(cantidad) AS 'Cantidad de Arboles' FROM arbol");
			while(resultado.next()) {
				return resultado.getInt("Cantidad de Arboles");
			}
		} catch (SQLException e) {
			System.out.println("No se puede hacer la consulta - imprimirStockArboles");
		}		
		return 0;
	}
	
	
	public int imprimirStockFlores(Connection conexion) {
		ResultSet resultado = null;
		
		try {
			Statement consulta = conexion.createStatement();
			resultado = consulta.executeQuery("SELECT SUM(cantidad) AS 'Cantidad de Flores' FROM flor");
			while(resultado.next()) {
				return resultado.getInt("Cantidad de Flores");
			}
		} catch (SQLException e) {
			System.out.println("No se puede hacer la consulta - imprimirStockFlores");
		}		
		return 0;
	}
	
	
	public int imprimirStockDecoracion(Connection conexion) {
		ResultSet resultado = null;
		
		try {
			Statement consulta = conexion.createStatement();
			resultado = consulta.executeQuery("SELECT SUM(cantidad) AS 'Cantidad de Decoraciones' FROM decoracion");
			while(resultado.next()) {
				return resultado.getInt("Cantidad de Decoraciones");
			}
		} catch (SQLException e) {
			System.out.println("No se puede hacer la consulta - imprimirStockDecoracion");
		}		
		return 0;
	}
	
	
	public int imprimirStockTotal(Connection conexion) {
		ResultSet resultado = null;
		
		try {
			Statement consulta = conexion.createStatement();
			resultado = consulta.executeQuery("SELECT SUM(cantidad) AS 'Cantidad de Productos' FROM producto");
			while(resultado.next()) {
				return resultado.getInt("Cantidad de Productos");
			}
		} catch (SQLException e) {
			System.out.println("No se puede hacer la consulta - imprimirStockDecoracion");
		}		
		return 0;
	}

	
	public double calcularTotalVentas(Connection conexion) {
		ResultSet resultado = null;
		int totalVenta = 0;
		
		try {
			Statement consulta = conexion.createStatement();
			resultado = consulta.executeQuery("SELECT SUM(p.precio * dc.cantidad) AS 'Total ganado' FROM producto " +
					"AS p INNER JOIN detalle_comanda AS dc ON p.id_producto = dc.id_producto");
			while(resultado.next()) {
				return resultado.getDouble("Total ganado");
			}
		} catch (SQLException e) {
			System.out.println("No se puede hacer la consulta - calcularTotalVentas");
		}		
		return totalVenta;
	}

	
	public ResultSet buscarComandasFecha(Connection conexion, String comienzo, String fin) {
	
		ResultSet resultado = null;
		
		try {
			Statement consulta = conexion.createStatement();
			resultado = consulta.executeQuery("SELECT * FROM comandas AS c WHERE c.Dia >= '" +  comienzo +	
					"' AND c.Dia <= '" + fin + "'");
		} catch (SQLException e) {
			System.out.println("No se puede hacer la consulta");
		}		
		return resultado;
	}
	
	
	public double calcularValorTotalStock(Connection conexion) {
		
		ResultSet resultado = null;
		try {
			Statement consulta = conexion.createStatement();
			resultado = consulta.executeQuery("SELECT SUM(p.precio * p.cantidad) AS 'Total valor stock' FROM producto AS p");
			while(resultado.next()) {
				return resultado.getDouble(1);
			}			
		}
		catch(SQLException e) {
			System.out.println("No se puede hacer la consulta");
		}
		return 0;
	}
	

	public double getPrecioComanda(Connection conexion, int id) {
						
		ResultSet resultado = null;
		try {
			Statement consulta = conexion.createStatement();
			resultado = consulta.executeQuery("SELECT SUM(p.precio * dc.cantidad) AS 'Precio final' FROM producto " +
					"AS p INNER JOIN detalle_comanda AS dc ON p.id_producto = dc.id_producto INNER JOIN comandas AS c ON " +
					"dc.id_comanda = c.id_comanda WHERE c.id_comanda = " + id);
			while(resultado.next()) {
				return resultado.getDouble(1);
			}			
		}
		catch(SQLException e) {
			System.out.println("No se puede hacer la consulta - getPrecioComanda");
		}
		return 0;
		
	}
	

  	public Connection crearDB(Connection conexion, String borrarDB, String query, String usarDB) {		
		try {
			conexion.createStatement().execute(borrarDB);
			conexion.createStatement().execute(query);			
			conexion.createStatement().execute(usarDB);
		}
		catch(SQLException e) {
			System.out.println("No se puede crear la BD");
		}
		return conexion;
	}
	
	
	//Recibe por parametro los valores y se conecta al servicio SQL
	public Connection conectar(String hostname, String puerto, String nombreDB, String usuario, String password) {		
		Connection conexion = null;
		String url = "jdbc:mysql://" + hostname + ":" + puerto + "/" + nombreDB + "?useSSL=false";	
		try {
			conexion = DriverManager.getConnection(url, usuario, password);
			System.out.println("Conectado al servicio");
		}
		catch(SQLException e) {
			System.out.println("No se pudo conectar al servicio");
		}		
		return conexion;
	}
	
	
	public void crearTabla(Connection conexion, String query) {
		try {
			conexion.prepareStatement(query).execute();
		}
		catch(SQLException e) {
			System.out.println("No se pude crear la tabla");
		}
	}

	
	public ResultSet ejecutarQuery(Connection conexion, String query) {
		
		ResultSet resultado = null;
		
		try {
			Statement consulta = conexion.createStatement();
			resultado = consulta.executeQuery(query);
		} catch (SQLException e) {
			System.out.println("No se puede hacer la consulta");
		}		
		return resultado;		
	}
}
