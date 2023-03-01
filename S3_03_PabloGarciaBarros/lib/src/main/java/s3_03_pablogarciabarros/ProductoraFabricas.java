package s3_03_pablogarciabarros;

//Desde esta clase inicio una instancia de la fabrica que necesite dependiendo la opcion que entre por pantalla


public class ProductoraFabricas {
	
	public static FabricaAbstracta getFabrica(int opcion) {
		switch(opcion) {
			case 1:
				return new FabricaArbol();		//Si instancio un arbol, creo una instancia de FabricaArbol
			case 2:
				return new FabricaFlor();		//Si instancio una flor, creo una instancia de FabricaFlor
			case 3:
				return new FabricaDecoracion(); //Si instancio una decoracon, creo una instancia de FabricaDecoracion
			case 4:
				return new FabricaTickets();	//Si instancio un ticket, creo una instancia de FabricaTickets
			case 5: 
				return new FabricaConexion();	//Si instancio una conexion, creo una instancia de FabricaConexion
			default:
				return null;					
		}
	} 
}
