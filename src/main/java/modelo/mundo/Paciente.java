package modelo.mundo;

import com.mongodb.BasicDBObject;

public class Paciente {

    // Atributos
    private String cedula;		//Cedula del paciente
    private String nombre;		//Nombre del paciente
    private String apellido;	//Apellido del paciente
    private String historial;	//Numero del historial medico del paciente
    private String fechaIngreso;	//FechaIngreso del paciente


    // Constructor
    public Paciente(String pCedula, String pNombre, String pApellido, String pHistorial, String pFechaIngreso){
        cedula = pCedula;
        nombre = pNombre;
        apellido = pApellido;
        historial = pHistorial;
        fechaIngreso = pFechaIngreso;
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
    
    public String darFechaIngreso(){
        return fechaIngreso;
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
		return "Paciente [cedula=" + cedula + ", nombre=" + nombre + ", apellido=" + apellido + ", historial=" + historial + ", diagnostico=" + fechaIngreso + "]";
	}
 


}

