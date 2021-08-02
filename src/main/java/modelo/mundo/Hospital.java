package modelo.mundo;

import java.util.ArrayList;
import java.util.Scanner;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import modelo.mundo.Cama.Clase;
import modelo.mundo.Cama.Ubicacion;


public class Hospital {
    // Constantes
    public final static int CAMAS_COVID = 7;		//Numero de camas COVID
    public final static int CAMAS_QUEMADOS = 7;		//Numero de camas Quemados
    public final static int CAMAS_CIRUJIA = 7;		//Numero de camas Cirujia


    // Atributos
    private Cama[] camasCovid;					//Camas de la clase COVID
    private Cama[] camasQuemados;				//Camas de la clase Quemados
    private Cama[] camasCirujia;				//Camas de la clase Cirujia
    
   
    // Constructores
    public Hospital( ){											//Se crea el hospital, se inicializan las listas de camas COVID, Quemados y cirujia
        //Ubicacion ubicacion;
        camasCovid = new Cama[CAMAS_COVID];						//Crea las camas COVID
        camasQuemados = new Cama[CAMAS_QUEMADOS];				//Crea las camas Quemados
        camasCirujia = new Cama[CAMAS_CIRUJIA];					//Crea las camas Cirujia

        camasCovid[ 0 ] = new Cama( 1, Clase.COVID, Ubicacion.AREA_COVID);
        camasCovid[ 1 ] = new Cama( 2, Clase.COVID, Ubicacion.AREA_COVID);
        camasCovid[ 2 ] = new Cama( 3, Clase.COVID, Ubicacion.AREA_COVID);
        camasCovid[ 3 ] = new Cama( 4, Clase.COVID, Ubicacion.AREA_COVID);
        camasCovid[ 4 ] = new Cama( 5, Clase.COVID, Ubicacion.AREA_COVID);
        camasCovid[ 5 ] = new Cama( 6, Clase.COVID, Ubicacion.AREA_COVID);
        camasCovid[ 6 ] = new Cama( 7, Clase.COVID, Ubicacion.AREA_COVID);

        camasQuemados[ 0 ] = new Cama( 11, Clase.QUEMADOS, Ubicacion.AREA_QUEMADOS);
        camasQuemados[ 1 ] = new Cama( 12, Clase.QUEMADOS, Ubicacion.AREA_QUEMADOS);
        camasQuemados[ 2 ] = new Cama( 13, Clase.QUEMADOS, Ubicacion.AREA_QUEMADOS);
        camasQuemados[ 3 ] = new Cama( 14, Clase.QUEMADOS, Ubicacion.AREA_QUEMADOS);
        camasQuemados[ 4 ] = new Cama( 15, Clase.QUEMADOS, Ubicacion.AREA_QUEMADOS);
        camasQuemados[ 5 ] = new Cama( 16, Clase.QUEMADOS, Ubicacion.AREA_QUEMADOS);
        camasQuemados[ 6 ] = new Cama( 17, Clase.QUEMADOS, Ubicacion.AREA_QUEMADOS);
        
        camasCirujia[ 0 ] = new Cama( 21, Clase.CIRUJIA, Ubicacion.AREA_CIRUJIA);
        camasCirujia[ 1 ] = new Cama( 22, Clase.CIRUJIA, Ubicacion.AREA_CIRUJIA);
        camasCirujia[ 2 ] = new Cama( 23, Clase.CIRUJIA, Ubicacion.AREA_CIRUJIA);
        camasCirujia[ 3 ] = new Cama( 24, Clase.CIRUJIA, Ubicacion.AREA_CIRUJIA);
        camasCirujia[ 4 ] = new Cama( 25, Clase.CIRUJIA, Ubicacion.AREA_CIRUJIA);
        camasCirujia[ 5 ] = new Cama( 26, Clase.CIRUJIA, Ubicacion.AREA_CIRUJIA);
        camasCirujia[ 6 ] = new Cama( 27, Clase.CIRUJIA, Ubicacion.AREA_CIRUJIA);
        

       /* for( int numCama = 1 + CAMAS_COVID, j = 1; j <= CAMAS_QUEMADOS; numCama++, j++ ){
            if( j % 6 == 1 || j % 6 == 0 )				//Camas Recien				
                ubicacion = Ubicacion.AREA_COVID;
            else if( j % 6 == 2 || j % 6 == 5 )			//Camas Recuperacion	
                ubicacion = Ubicacion.AREA_QUEMADOS;
            else
                ubicacion = Ubicacion.AREA_CIRUJIA;			//Camas terminal

            camasQuemados[ j - 1 ] = new Cama(numCama, Clase.QUEMADOS, ubicacion );
        }*/
    }

    
    ////////////////////
    /*public Hospital( int pNumero,  Ubicacion pUbicacion, boolean asignado )
    {
        numero = pNumero;
        ubicacion = pUbicacion;
        // Inicialmente no hay ningún huesped en la habitacion
        this.asignado = false;
        
        this.paciente = null;
        
    }*/
    
    ////////////////////
 
    // Métodos
    public Cama asignarCama(Clase pClase, Ubicacion pUbicacion, Paciente pPaciente){    //Asigna la cmaa en la clase y ubicacion especificados
    	Cama cama = null;									//Busca una cama libre
    	if( pClase == Clase.COVID ){
    		cama = buscarCamaCovidLibre( pUbicacion );
    	}else if( pClase == Clase.QUEMADOS ){
    		cama = buscarCamaQuemadosLibre( pUbicacion );
    	}else if( pClase == Clase.CIRUJIA ){
    		cama = buscarCamaCirujiaLibre( pUbicacion );
    	}

    	if( cama != null ){
    		cama.asignarAPaciente(pPaciente);
    	}
    	return cama;
    }

	
    public Cama buscarCamaCovidLibre(Ubicacion pUbicacion){								//Busca la siguiente cama COVID libre y tenga la ubicacion indicada
    	boolean encontrado = false;
    	Cama cama = null;
    	for( int i = 0; i < CAMAS_COVID && !encontrado; i++ ){
    		cama = camasCovid[ i ];
    		if( ! ( cama.camaAsignada( ) ) && cama.darUbicacion( ) == pUbicacion ){
    			encontrado = true;
    		}
    	}
    	if( !encontrado ){
    		cama = null;
    	}
    	return cama;
    }

    
    public Cama buscarCamaQuemadosLibre( Ubicacion pUbicacion ){					//Busca la siguiente cama Quemados que este libre y tenga la ubicacion indicada
        boolean encontrado = false;
        Cama cama = null;
        for( int i = 0; i < CAMAS_QUEMADOS && !encontrado; i++ ){
            cama = camasQuemados[ i ];
            if( ! (cama.camaAsignada()) && cama.darUbicacion() == pUbicacion){
                encontrado = true;
            }
        }
        if( !encontrado ){
            cama = null;
        }
        return cama;
    }
    
    
    public Cama buscarCamaCirujiaLibre( Ubicacion pUbicacion ){					//Busca la siguiente cama Quemados que este libre y tenga la ubicacion indicada
        boolean encontrado = false;
        Cama cama = null;
        for( int i = 0; i < CAMAS_CIRUJIA && !encontrado; i++ ){
            cama = camasCirujia[ i ];
            if( ! (cama.camaAsignada()) && cama.darUbicacion() == pUbicacion){
                encontrado = true;
            }
        }
        if( !encontrado ){
            cama = null;
        }
        return cama;
    }

     
    public Cama buscarPaciente(Paciente pPaciente){						//Busca un paciente en la UCI
        Cama cama = buscarPacienteCovid(pPaciente);						//Busca el paciente en COVID
        if( cama == null){
            cama = buscarPacienteQuemado(pPaciente);					//Si no esta en COVID, busca en quemados
        }
        if( cama == null){
            cama = buscarPacienteCirujia(pPaciente);					//Si no esta en quemados o COVID, busca en cirujia
        }
        return cama;
    }


    public Cama buscarPacienteCovid(Paciente pPaciente){				//Busca un paciente en camas COVID
        boolean encontrado = false;
        Cama cama = null;
        for( int i = 0; i < CAMAS_COVID && !encontrado; i++ ){
            cama = camasCovid[ i ];
            if( cama.camaAsignada( ) && cama.darPaciente( ).igualA(pPaciente)){
                encontrado = true;
            }
        }
        if( !encontrado ){
            cama = null;
        }
        return cama;
    }

    
    public Cama buscarPacienteQuemado(Paciente pPaciente){		//Busca un paciente en Quemados	
        boolean encontrado = false;
        Cama cama = null;
        for( int i = 0; i < CAMAS_QUEMADOS && !encontrado; i++ ){
            cama = camasQuemados[ i ];
            if( cama.camaAsignada( ) && cama.darPaciente( ).igualA( pPaciente)){
                encontrado = true;
            }
        }
        if( !encontrado ){
            cama = null;
        }
        return cama;
    }
    

    public Cama buscarPacienteCirujia(Paciente pPaciente){		//Busca un paciente en Quemados	
        boolean encontrado = false;
        Cama cama = null;
        for( int i = 0; i < CAMAS_CIRUJIA && !encontrado; i++ ){
            cama = camasCirujia[ i ];
            if( cama.camaAsignada( ) && cama.darPaciente( ).igualA( pPaciente)){
                encontrado = true;
            }
        }
        if( !encontrado ){
            cama = null;
        }
        return cama;
    }


    public boolean desasignarCama(Paciente pPaciente){		//Desasigna la cama de un paciente
        Cama cama = buscarPaciente(pPaciente);				//Busca el paciente en la UCI
        boolean resultado = false;					
        if(cama != null ){									//Si lo encuentra desasigna cama
            cama.desasignarCama( );
            resultado = true;
        }
        return resultado;
    }


    public int contarCamasCovidOcupadas( ){					//Retorna el numero de camas COVID ocupadas
        int contador = 0;
        for( Cama camaCovid : camasCovid){
            if( camaCovid.camaAsignada( ) ){
                contador++;
            }
        }
        return contador;
    }


    public int contarCamasQuemadosOcupadas( ){			//Retorna el numero de camas Quemados ocupadas
        int contador = 0;
        for( Cama camaQuemados : camasQuemados){
            if( camaQuemados.camaAsignada( ) ){
                contador++;
            }
        }
        return contador;
    }
    
    
    public int contarCamasCirujiaOcupadas( ){			//Retorna el numero de camas Quemados ocupadas
        int contador = 0;
        for( Cama camaCirujia : camasCirujia){
            if( camaCirujia.camaAsignada( ) ){
                contador++;
            }
        }
        return contador;
    }

    
    public double calcularPorcentajeOcupacion( ){		//Calcula el porcentaje de ocupacion de la UCI
        double porcentaje;
        int totalCamas = CAMAS_COVID + CAMAS_QUEMADOS + CAMAS_CIRUJIA;
       // System.out.println(totalCamas);
        int camasOcupadas = contarCamasCovidOcupadas( ) + contarCamasQuemadosOcupadas( ) + contarCamasCirujiaOcupadas();
        porcentaje = ( double )camasOcupadas / totalCamas * 100;
       // System.out.println(porcentaje);
        return porcentaje;
    }


    public Cama[] obtenerCamasCovid( ){			//Retorna las camas Covid de la UCI
        return camasCovid;
    }


    public Cama[] obtenerCamasQuemados( ){		//Retorna las camas Quemados de la UCI
        return camasQuemados;
    }
    

    public Cama[] obtenerCamasCirujia( ){		//Retorna las camas Quemados de la UCI
        return camasCirujia;
    }

    
    
    
    /////////////////////////////////////////////////////////////////////////
    //////**********************MONGO DB***************************//////////
    /////////////////////////////////////////////////////////////////////////
    
    public static void main(String[] args) {
		ArrayList<Paciente> pacientes = new ArrayList<Paciente>();

		Scanner entrada = new Scanner (System.in);
		System.out.println("SISTEMA DE INGRESO DE PACIENTES A LA UCI");
		System.out.print("CEDULA: ");
		String cedula = entrada.nextLine(); 
		System.out.print("NOMBRE: ");
		String nombre = entrada.nextLine();
		System.out.print("APELLIDO: ");
		String apellido = entrada.nextLine();
		System.out.print("HISTORIAL MEDICO: ");
		String historial = entrada.nextLine();
		System.out.print("DIAGNOSTICO: ");
		String diagnostico = entrada.nextLine();

	
		pacientes.add(new Paciente(cedula, nombre, apellido, historial, diagnostico));
		/*pacientes.add(new Paciente("123456789", "Maria", "Perez", "efgh", "Quemadura"));
		pacientes.add(new Paciente("987654321", "Leo", "Castro", "ijkl", "Cirujia"));
		pacientes.add(new Paciente("012345677", "Pablo", "Aguirre", "mnño", "Covid"));
		pacientes.add(new Paciente("123456787", "Pedro", "Marin", "pqrs", "Quemadura"));
		pacientes.add(new Paciente("987654327", "Joan", "Chase", "tuvw", "Cirujia"));
		pacientes.add(new Paciente("012345676", "Freddy", "Cordova", "xyza", "Covid"));
		pacientes.add(new Paciente("123456786", "Ricardo", "Mejia", "zyxw", "Quemadura"));
		pacientes.add(new Paciente("987654326", "Jenniffer", "Montufar", "vuts", "Cirujia"));*/


		try {
			MongoClient mongoClient = new MongoClient("localhost", 27017);	// Conexion al servidor de MongoDB pasando el host y el puerto
			System.out.println("Conexion exitosa");
			DB db = mongoClient.getDB("Hospital");							//Conexion a la base de datos "Hospital"
			DBCollection collection = db.getCollection("Pacientes");     	//Se obtiene coleccion para trabajar con ella



			//CRUD
			// PASO 4.1: "CREATE" -> Metemos los objetos pacientes (o documentos en Mongo) en la coleccion Paciente
			for (Paciente pac : pacientes) {
				collection.insert(pac.toDBObjectPaciente());
			}
			// PASO 4.2.1: "READ" -> Leemos todos los documentos de la base de datos
			int numDocumentos = (int) collection.getCount();
			System.out.println("Número de documentos en la colección Pacientes: " + numDocumentos + "\n");
			// Busco todos los documentos de la colección y los imprimo
			DBCursor cursor = collection.find();
			try {
				while (cursor.hasNext()) {
					System.out.println(cursor.next().toString());
				}
			} finally {
				cursor.close();
			}

			 // PASO 4.2.2: "READ" -> Hacemos una Query con condiciones y lo pasamos a un objeto Java
		      System.out.println("\nPacientes que tienen diagnostico Covid");
		      DBObject query = new BasicDBObject("Diagnostico", new BasicDBObject("$regex", "Covid"));
		      cursor = collection.find(query);
		      try {
		        while (cursor.hasNext()) {
		          Paciente paciente = new Paciente((BasicDBObject) cursor.next());
		          System.out.println(paciente.toString());
		        }
		      } finally {
		        cursor.close();
		      }
			
		      // PASO 4.3: "UPDATE" -> Actualizamos el apellido de los pacientes
		      DBObject find = new BasicDBObject("apellido", new BasicDBObject("$gt", "Perez"));
		      DBObject updated = new BasicDBObject().append("$inc", new BasicDBObject().append("apellido", "Chasiguano"));
		      collection.update(find, updated, false, true);

		      // PASO 4.4: "DELETE" -> Borramos todos los pacientes que tengan quemaduras
		      DBObject findDoc = new BasicDBObject("diagnostico", "Quemadura");
		      collection.remove(findDoc);

		    // PASO FINAL: Cerrar la conexion
		      mongoClient.close();		
			
	    }catch (Exception ex) {
	        	System.out.println("Error al conectar al Server MongoDB"  + ex.getMessage());
	    }
	}

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}