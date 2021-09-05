package interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import modelo.mundo.Cama;
import modelo.mundo.Cama.Clase;
import modelo.mundo.Cama.Ubicacion;
import modelo.mundo.Paciente;


@SuppressWarnings("serial")
public class DialogoDatosPaciente extends JDialog {		//Formulario para presentar los datos y el registro del paciente.
	// Constantes
	public final static String ACEPTAR = "ACEPTAR";		//Opcion aceptar


	// Atributos de Interfaz
	private InterfazHospital principal;					//Interfaz principal
	private JTextField txtCedula;						//Texto que contiene la cedula
	private JTextField txtNombre;						//Texto que contiene el nombre
	private JTextField txtClase;						//Texto que contiene la clase
	private JTextField txtUbicacion;					//Texto que contiene la ubicacion
	private JTextField txtCama;							//Texto que contiene la cama
	private JTextField txtFechaIngreso;					//Texto que contiene la fecha de ingreso


	// Constructores
	public DialogoDatosPaciente( InterfazHospital pPrincipal, Cama pCama ){ 			// Crea la ventana con los datos del pacient
		principal = pPrincipal;

		setTitle( "Datos del paciente" );
		setSize( 270, 200 );
		setDefaultCloseOperation( DISPOSE_ON_CLOSE );

		Paciente paciente = pCama.darPaciente( );
		setLayout( new BorderLayout( ) );

		// Crea el panel de datos del paciente
		JPanel panelDatosPaciente = new JPanel( );

		panelDatosPaciente.setBorder( new CompoundBorder( new TitledBorder( "Datos del paciente" ), new EmptyBorder( 3, 3, 3, 3 ) ) );
		panelDatosPaciente.setLayout( new GridLayout( 6, 2, 1, 1 ) );

		// Cédula
		JLabel etiquetaCedula = new JLabel( "Cédula: " );
		panelDatosPaciente.add( etiquetaCedula );

		txtCedula = new JTextField( paciente.darCedula( ) + "" );
		txtCedula.setEditable( false );
		panelDatosPaciente.add( txtCedula );

		// Nombre
		JLabel etiquetaNombre = new JLabel( "Nombre: " );
		panelDatosPaciente.add( etiquetaNombre );

		txtNombre = new JTextField( paciente.darNombre( ) );
		txtNombre.setColumns( 15 );
		txtNombre.setEditable( false );
		panelDatosPaciente.add( txtNombre );

		// Cama
		JLabel etiquetaCama = new JLabel( "Cama: " );
		panelDatosPaciente.add( etiquetaCama );

		txtCama = new JTextField( pCama.darNumero( ) + "" );
		txtCama.setEditable( false );
		panelDatosPaciente.add( txtCama );
		
		// Cama
		JLabel etiquetaFechaIngreso = new JLabel( "Fecha Ingreso: " );
		panelDatosPaciente.add( etiquetaFechaIngreso );

		txtFechaIngreso = new JTextField( pCama.darNumero( ) + "" );
		txtFechaIngreso.setEditable( false );
		panelDatosPaciente.add( txtFechaIngreso );
				

		// Clase
		JLabel etiquetaClase = new JLabel( "Clase: " );

		panelDatosPaciente.add( etiquetaClase );
		String sClase;
		if( pCama.darClase( ) == Clase.QUEMADOS ){
			sClase = "Quemados";
		}else  if( pCama.darClase( ) == Clase.CIRUGIA ){
			sClase = "Cirugia";
		}else {
			sClase = "Covid";
		}
		txtClase = new JTextField( sClase );
		txtClase.setEditable( false );
		panelDatosPaciente.add( txtClase );

		// Ubicación
		JLabel etiquetaUbicacion = new JLabel( "Ubicación: " );
		panelDatosPaciente.add( etiquetaUbicacion );

		String sUbicacion;
		if( pCama.darUbicacion( ) == Ubicacion.AREA_QUEMADOS ) {
			sUbicacion = "Quemados";
		} else if( pCama.darUbicacion( ) == Ubicacion.AREA_CIRUGIA ) {
			sUbicacion = "Cirugia";
		} else {
			sUbicacion = "Covid";
		}
		txtUbicacion = new JTextField( sUbicacion );
		txtUbicacion.setEditable( false );
		panelDatosPaciente.add( txtUbicacion );
		add( panelDatosPaciente, BorderLayout.CENTER );

		setModal( true );
		setLocationRelativeTo( principal );
		setResizable( false );
	}


}
