package modelo.mundo;

import com.mongodb.BasicDBObject;

public class Paciente {

    // Atributos
    private String cedula;		//Cedula del paciente
    private String nombre;		//Nombre del paciente
    private String apellido;	//Apellido del paciente
    private String historial;	//Numero del historial medico del paciente
    private String diagnostico;	//Diagnostico del paciente


    // Constructor
    public Paciente(String pCedula, String pNombre, String pApellido, String pHistorial, String pDiagnostico){
        cedula = pCedula;
        nombre = pNombre;
        apellido = pApellido;
        historial = pHistorial;
        diagnostico = pDiagnostico;
    }


    // Métodos
    public String darCedula(){
        return cedula;
    }

    public String darNombre(){
        return nombre;
    }
    
    public String darApellido(){
        return apellido;
    }
    
    public String darHistorial(){
        return historial;
    }
    
    public String darDiagnostico(){
        return diagnostico;
    }

    
    public boolean igualA(Paciente pOtroPaciente){
        boolean esIgual = false;
        if( cedula.equals( pOtroPaciente.darCedula())){
            esIgual = true;
        }
        return esIgual;
    }
 
    
    @Override
	public String toString() {
		return "Paciente [cedula=" + cedula + ", nombre=" + nombre + ", apellido=" + apellido + ", historial=" + historial + ", diagnostico=" + diagnostico + "]";
	}
 
    
/////////////////////////////////////////////////////////////////////////
//////**********************MONGO DB***************************//////////
/////////////////////////////////////////////////////////////////////////

	// Transformo un objecto que me da MongoDB a un Objecto Java
    public Paciente(BasicDBObject dBObjectPaciente) {
    	this.cedula = dBObjectPaciente.getString("cedula");
    	this.nombre = dBObjectPaciente.getString("nombre");
    	this.apellido = dBObjectPaciente.getString("apellido");
    	this.historial = dBObjectPaciente.getString("historial");
    	this.diagnostico = dBObjectPaciente.getString("diagnostico");
    }

    public BasicDBObject toDBObjectPaciente() {
        BasicDBObject dBObjectPaciente = new BasicDBObject();			//Crea una instancia de BasicDBObject
        dBObjectPaciente.append("Cedula", this.darCedula());
        dBObjectPaciente.append("Nombre", this.darNombre());
        dBObjectPaciente.append("Apellido", this.darApellido());
        dBObjectPaciente.append("Historial", this.darHistorial());
        dBObjectPaciente.append("diagnostico", this.darDiagnostico());
       

        return dBObjectPaciente;
    }


    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}