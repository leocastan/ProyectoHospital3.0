package interfaz;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


@SuppressWarnings("serial")
public class PanelBotonesHospital extends JPanel implements ActionListener{	//Panel de botones de interacción con el programa del hospital.
    // Constantes
    public final static String REGISTRAR = "REGISTRAR_PACIENTE";			//Opcion de registrar
    public final static String ANULAR = "ANULAR_PACIENTE";					//Opcion anular
    public final static String BUSCAR = "BUSCAR_PACIENTE";					//Opcion buscar
    public final static String PORCENTAJE = "PORCENTAJE_OCUPACION";			//Opcion porcentaje de ocupacion
    private final static String HISTORIAL = "HISTORIAL";					//Opcion extension 1
    private final static String SALIR = "SALIR";							//Opcion extension 2


    // Atributos de interfaz
    private JButton bRegistro;						//Boton Registro de nuevo paciente
    private JButton bAnular;						//Boton anular
    private JButton bBuscarPasajero;				//Boton de busqueda
    private JButton bPorcOcupacion;					//Boton porcentaje de ocupacion
    private JButton botonOpcion1;					//Boton de extension 1
    private JButton botonOpcion2;					//Boton de extension 2
    private InterfazHospital ventana;				//Interfaz principal


    // Constructores
    public PanelBotonesHospital( InterfazHospital pVentana ){			//Creal el panel de botones; se crean y alistan los botones de la interfaz
        // Guarda la referencia a la ventana padre
        ventana = pVentana;

        // Configura propiedades visuales
        setLayout( new GridLayout( 2, 3, 8, 2 ) );
        setBorder( new EmptyBorder( 5, 5, 5, 5 ) );

        // Botón registrar
        bRegistro = new JButton( "Registrar Paciente" );
        bRegistro.setActionCommand( REGISTRAR );
        bRegistro.addActionListener( this );
        bRegistro.setPreferredSize( new Dimension( 40, 10 ) );
        add( bRegistro );

        // Botón anular registro
        bAnular = new JButton( "Dar de Alta a Paciente" );
        bAnular.setActionCommand( ANULAR );
        bAnular.addActionListener( this );
        add( bAnular );

        // Botón buscar pasajero
        bBuscarPasajero = new JButton( "Buscar Paciente" );
        bBuscarPasajero.setActionCommand( BUSCAR );
        bBuscarPasajero.addActionListener( this );
        add( bBuscarPasajero );

        // Botón porcentaje de ocupación
        bPorcOcupacion = new JButton( "Porcentaje Ocupación" );
        bPorcOcupacion.setActionCommand( PORCENTAJE );
        bPorcOcupacion.addActionListener( this );
        add( bPorcOcupacion );

        // Botones por si acaso
        botonOpcion1 = new JButton( "Historial" );
        botonOpcion1.setActionCommand( HISTORIAL );
        botonOpcion1.addActionListener( this );
        add( botonOpcion1 );
        
        
        botonOpcion2 = new JButton( "Salir" );
        botonOpcion2.setActionCommand( SALIR );
        botonOpcion2.addActionListener(this);
        add( botonOpcion2 );
    }


    // Métodos
    public void actionPerformed( ActionEvent pEvento ){ 			//Acciones de respuesta a los eventos de la interfaz
        String comando = pEvento.getActionCommand( );

        if( comando.equals( REGISTRAR ) ){
            ventana.registrarPaciente( );
        }
        else if( comando.equals( ANULAR ) ){
            ventana.eliminarPaciente( );
        }
        else if( comando.equals( BUSCAR ) ){
            ventana.buscarPaciente( );
        }
        else if( comando.equals( PORCENTAJE)){
            ventana.mostrarPorcentajeOcupacion( );
        }
        else if( comando.equals( HISTORIAL ) ){
            ventana.reqFuncOpcion1( );
        }
        else if( comando.equals( SALIR ) ){
            ventana.reqFuncOpcion2( );
        }
    }
}












