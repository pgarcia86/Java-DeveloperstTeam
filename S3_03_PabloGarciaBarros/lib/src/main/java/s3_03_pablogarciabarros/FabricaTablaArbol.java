package s3_03_pablogarciabarros;


//FabricaTabla es analoga a FabricaArbol
public class FabricaTablaArbol extends FabricaAbstractaTabla{

	@Override
	Tabla nuevaTabla() {
		System.out.println("--------------SE ESTA CREANDO LA TABLA ARBOL-----------");
		System.out.println();
		
		return new TablaArbol();
	}
}
