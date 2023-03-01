package s3_03_pablogarciabarros;

public class ProductoraFabricas {
	
	public static FabricaAbstracta getFabrica(int opcion) {
		switch(opcion) {
			case 1:
				return new FabricaArbol();
			case 2:
				return new FabricaFlor();
			case 3:
				return new FabricaDecoracion();
			default:
				return null;					
		}
	} 
}
