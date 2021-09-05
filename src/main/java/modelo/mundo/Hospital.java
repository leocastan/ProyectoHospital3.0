package modelo.mundo;

import java.util.List;

import conexionMongoDb.HospitalDao;
import modelo.mundo.Cama.Clase;
import modelo.mundo.Cama.Ubicacion;


public class Hospital {
    private HospitalDao hospitalDao;
    
    // Constantes
    public final static int CAMAS_COVID = 7;		//Numero de camas COVID
    public final static int CAMAS_QUEMADOS = 7;		//Numero de camas Quemados
    public final static int CAMAS_CIRUGIA = 7;		//Numero de camas Cirujia


    // Atributos
    private Cama[] camasCovid;					//Camas de la clase COVID
    private Cama[] camasQuemados;				//Camas de la clase Quemados
    private Cama[] camasCirugia;				//Camas de la clase Cirujia
   
    // Constructores
    public Hospital( ) {										// Se crea el hospital, se inicializan las listas de camas COVID, Quemados y cirujia
       hospitalDao = HospitalDao.getInstance();
        
        //Ubicacion ubicacion;
        camasCovid = new Cama[CAMAS_COVID];						// Crea las camas COVID
        camasQuemados = new Cama[CAMAS_QUEMADOS];				// Crea las camas Quemados
        camasCirugia = new Cama[CAMAS_CIRUGIA];					// Crea las camas Cirujia

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
        
        camasCirugia[ 0 ] = new Cama( 21, Clase.CIRUGIA, Ubicacion.AREA_CIRUGIA);
        camasCirugia[ 1 ] = new Cama( 22, Clase.CIRUGIA, Ubicacion.AREA_CIRUGIA);
        camasCirugia[ 2 ] = new Cama( 23, Clase.CIRUGIA, Ubicacion.AREA_CIRUGIA);
        camasCirugia[ 3 ] = new Cama( 24, Clase.CIRUGIA, Ubicacion.AREA_CIRUGIA);
        camasCirugia[ 4 ] = new Cama( 25, Clase.CIRUGIA, Ubicacion.AREA_CIRUGIA);
        camasCirugia[ 5 ] = new Cama( 26, Clase.CIRUGIA, Ubicacion.AREA_CIRUGIA);
        camasCirugia[ 6 ] = new Cama( 27, Clase.CIRUGIA, Ubicacion.AREA_CIRUGIA);
        
        cargarDatosBD();
    }
    
    private void cargarDatosBD() {
        Cama camas[] = new Cama[21];
        
        int i = 0;
        
        for (Cama c : camasCirugia) {
            camas[i++] = c;
        }
        
        for (Cama c : camasCovid) {
            camas[i++] = c;
        }
        
        for (Cama c : camasQuemados) {
            camas[i++] = c;
        }
        
        hospitalDao.guardarCamas(camas);
        
        List<Cama> camasBD = hospitalDao.obtenerCamas();
        int indiceQuemados = 0;
        int indiceCirugia = 0;
        int indiceCovid = 0;
        
        for (Cama cama : camasBD) {
            if (cama.darPaciente() == null) continue;
            
            if (cama.darClase() == Cama.Clase.QUEMADOS) {
                camasQuemados[indiceQuemados++] = cama;
            } else if (cama.darClase() == Cama.Clase.CIRUGIA) {
                camasCirugia[indiceCirugia++] = cama;
            } else {
                camasCovid[indiceCovid++] = cama;
            }
        }
    }

    
    // Métodos
    public Cama asignarCama(Clase pClase, Ubicacion pUbicacion, Paciente pPaciente){    //Asigna la cmaa en la clase y ubicacion especificados
    	Cama cama = null;									//Busca una cama libre
        
    	if( pClase == Clase.COVID ) {
    		cama = buscarCamaCovidLibre( pUbicacion );
    	} else if( pClase == Clase.QUEMADOS ) {
    		cama = buscarCamaQuemadosLibre( pUbicacion );
    	} else if( pClase == Clase.CIRUGIA ) {
    		cama = buscarCamaCirugiaLibre( pUbicacion );
    	}

    	if( cama != null ){
    		cama.asignarAPaciente(pPaciente);
            hospitalDao.guardarCama(cama);
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
    
    
    public Cama buscarCamaCirugiaLibre( Ubicacion pUbicacion ){					//Busca la siguiente cama Quemados que este libre y tenga la ubicacion indicada
        boolean encontrado = false;
        Cama cama = null;
        for( int i = 0; i < CAMAS_CIRUGIA && !encontrado; i++ ){
            cama = camasCirugia[ i ];
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
            cama = buscarPacienteCirugia(pPaciente);					//Si no esta en quemados o COVID, busca en cirujia
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
    

    public Cama buscarPacienteCirugia(Paciente pPaciente){		//Busca un paciente en Quemados	
        boolean encontrado = false;
        Cama cama = null;
        for( int i = 0; i < CAMAS_CIRUGIA && !encontrado; i++ ){
            cama = camasCirugia[ i ];
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
            hospitalDao.guardarCama(cama);
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
    
    
    public int contarCamasCirugiaOcupadas( ){			//Retorna el numero de camas Quemados ocupadas
        int contador = 0;
        for( Cama camaCirugia : camasCirugia){
            if( camaCirugia.camaAsignada( ) ){
                contador++;
            }
        }
        return contador;
    }

    
    public double calcularPorcentajeOcupacion( ){		//Calcula el porcentaje de ocupacion de la UCI
        double porcentaje;
        int totalCamas = CAMAS_COVID + CAMAS_QUEMADOS + CAMAS_CIRUGIA;
       // System.out.println(totalCamas);
        int camasOcupadas = contarCamasCovidOcupadas( ) + contarCamasQuemadosOcupadas( ) + contarCamasCirugiaOcupadas();
        porcentaje = ( double )camasOcupadas *100 / totalCamas;
       // System.out.println(porcentaje);
        return porcentaje;
    }


    public Cama[] obtenerCamasCovid( ){			//Retorna las camas Covid de la UCI
        return camasCovid;
    }


    public Cama[] obtenerCamasQuemados( ){		//Retorna las camas Quemados de la UCI
        return camasQuemados;
    }
    

    public Cama[] obtenerCamasCirugia( ){		//Retorna las camas Quemados de la UCI
        return camasCirugia;
    }

      
    public String metodo1( ) { 				//Metodo para la extension 1
        return "Desea Salir";
    }


    public String salir( ){ 				//Metodo para la extension 2
        return "Desaea Guardar";
    }

}