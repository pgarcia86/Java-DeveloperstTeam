package s3_03_pablogarciabarros;

import java.sql.Connection;

public class FabricaTablaTipoProducto extends FabricaAbstractaTabla{

	@Override
	Tabla nuevaTabla() {

		System.out.println("--------------SE ESTA CREANDO LA TABLA TIPO PRODUCTO-----------");
		System.out.println();
		
		return new TablaTipoProducto();
	}
	
	Connection insertarTabla() {
		return null;
	}
}
