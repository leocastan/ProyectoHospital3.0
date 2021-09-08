package interfaz;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelo.mundo.*;



@SuppressWarnings("serial")
public class InterfazHospital extends JFrame{
    // Atributos
	private Hospital hospital;				//Hospital			
    
    
    //Atributos de interfaz
    private PanelImagen panelImagen;				//Panel que contiene el banner de la aplicacion
    private PanelHospital panelHospital;			//Panel que contiene el avion
    private PanelBotonesHospital panelBotones;		//Panel de botones
    private DialogoAsignacion dAsignacion;			//Dialogo de nuevo pasajero


    // Constructores
    public InterfazHospital( ){						//Crea la interfaz hospital; se crea el hospital y se presenta en una grafica su estado inicial
        setTitle( "Hospital de los Valles" );
        setIconImage(Toolkit.getDefaultToolkit().getImage("data/imagenes/hospitalvalles.jpg"));
        setSize( 580, 500);  						//Pepa 500 ancho * 500 alto
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        hospital = new Hospital( );					//Crea el hospital
        setLayout( new BorderLayout( ) );			//Configura la interfaz
        
        //Panel del banner
        panelImagen = new PanelImagen( );			
        add( panelImagen, BorderLayout.NORTH );

        //Panel del hospital
        panelHospital = new PanelHospital( hospital );
        add( panelHospital, BorderLayout.CENTER );

        // Panel de botones
        panelBotones = new PanelBotonesHospital( this );
        add( panelBotones, BorderLayout.SOUTH );

        setResizable( false );
        setLocationRelativeTo( null );
    }

    
    // Métodos
    public void registrarPaciente( ){					//Procesa el registro de un paciente
        dAsignacion = new DialogoAsignacion( this, hospital );
        dAsignacion.setVisible( true );
        actualizar( );
    }


    public void eliminarPaciente( ){ 					//Procesa la anulacion del registro dde un paciente
        // Pregunta el número de cédula

        String cedula = JOptionPane.showInputDialog( this, "Ingrese el número de cédula", "Eliminar paciente", JOptionPane.QUESTION_MESSAGE );
        
        if( cedula != null && !cedula.isEmpty( ) ) {
            Paciente paciente = new Paciente( cedula, "no importa", "no importa", "no importa", "no importa" );
            
            if( !hospital.desasignarCama( paciente ) ) {
                JOptionPane.showMessageDialog( this, "El paciente no tiene cama asignada", "Dar de alta paciente", JOptionPane.ERROR_MESSAGE );
            } else {
                JOptionPane.showMessageDialog( this, "Paciente dado de alta.", "Eliminar paciente", JOptionPane.INFORMATION_MESSAGE );
            }
        }
        
        actualizar();
    }

    public void mostrarPorcentajeOcupacion( ){			//Muestra el procentaje de ocupacion que tiene el hospital
        double porcentaje;
        porcentaje = hospital.calcularPorcentajeOcupacion( );
        DecimalFormat df = ( DecimalFormat )NumberFormat.getInstance( );
        df.applyPattern( "###.##" );
        JOptionPane.showMessageDialog( this, "El porcentaje de ocupación es " + df.format( porcentaje ) + "%", "Ocupación del hospital", JOptionPane.INFORMATION_MESSAGE );
    }

    public void buscarPaciente( ){					//Procesa la busqueda de un paciente
        // Pregunta el número de cédula
        String cedula = JOptionPane.showInputDialog( this, "Ingrese el número de cédula", "Buscar paciente", JOptionPane.QUESTION_MESSAGE );
        if( cedula != null && !cedula.isEmpty( ) ){
            Paciente paciente = new Paciente( cedula, "no importa", "no importa", "no importa", "no importa"   );

            Cama cama = hospital.buscarPaciente( paciente );

            if( cama != null ) {
                DialogoDatosPaciente vDatos = new DialogoDatosPaciente( this, cama );
                vDatos.setVisible( true );
            } else {
                JOptionPane.showMessageDialog( this, "El paciente no se encuentra registrado", "Buscar paciente", JOptionPane.INFORMATION_MESSAGE );
            }
        }
    }

    
   
    public void actualizar( ){			//Repinta la grafica del hospital
        remove( panelHospital );

        // Panel del Hospital
        panelHospital = new PanelHospital( hospital );
        add( panelHospital, BorderLayout.CENTER );
        validate( );
    }

    
    public void reqFuncOpcion1(){
        InterfazHistorial historial = new InterfazHistorial(hospital);
        historial.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        historial.setLocationRelativeTo(null);
        historial.setVisible(true);
    }


    public void reqFuncOpcion2() {
        System.exit(0);
    }


}
