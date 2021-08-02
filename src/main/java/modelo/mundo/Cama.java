package modelo.mundo;


public class Cama {
	
    // Enumeraciones
    public enum Clase {
        COVID,				//Clases COVID
        QUEMADOS,			//Clases Quemados
        CIRUJIA				//Clases Cirujia
    }

    public enum Ubicacion {
        AREA_COVID,				//Ubicacion fase terminal
        AREA_QUEMADOS,			//Ubicacion fase recuperacion
        AREA_CIRUJIA				//Ubicacion recien ingresados
    }


    // Atributos
    private int numero;				//Numero de cama
    private Clase clase;			//Clase de cama
    private Ubicacion ubicacion; 	//Ubicacion de la cama
    private Paciente paciente;		//Paciente asignado a la cama*/


    // Constructor
    public Cama( int pNumero, Clase pClase, Ubicacion pUbicacion ){
        numero = pNumero;
        clase = pClase;
        ubicacion = pUbicacion;
        paciente = null;	//Inicialmente no hay pacientes
    }


    // Métodos
    public void asignarAPaciente( Paciente pPaciente ){			//Asigna cama a paciente
        paciente = pPaciente;
    }


    public void desasignarCama( ){								//Desasigna la cama del paciente "x", la cama vuelve a quedar libre
        paciente = null;
    }


    public boolean camaAsignada( ){								//Indica si la cama esta asiganda, retorna "true" si esta asignada, de lo contrario false
        boolean asignada = true;
        if( null == paciente ){
            asignada = false;
        }
        return asignada;
    }


    public boolean camaAsignadaPaciente(Paciente pPaciente){	//Indica si la cama esta asignadda al paciente recibido como parametro; retorna true si ocupa, false de lo contrario
    	boolean asignadaPaciente = false;
    	if( null == paciente ){
    		asignadaPaciente = false;
    	} else if( paciente.igualA(pPaciente)){
    		asignadaPaciente = true;
    	}
    	return asignadaPaciente;
    }

 
    public int darNumero( ){										//Retorna el numero de la cama
        return numero;
    }

    
    public Clase darClase( ){										//Retorna la clases de la cama
        return clase;
    }


    public Ubicacion darUbicacion( ){								//Retorna la ubicacion de la cama
        return ubicacion;
    }


    public Paciente darPaciente(){								//Retorna el paciente asignado a la cama
        return paciente;
    }
}

