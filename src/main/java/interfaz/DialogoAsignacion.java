package interfaz;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import modelo.mundo.Cama;
import modelo.mundo.Cama.Clase;
import modelo.mundo.Cama.Ubicacion;
import modelo.mundo.Hospital;
import modelo.mundo.Paciente;


@SuppressWarnings("serial")
public class DialogoAsignacion extends JDialog implements ActionListener{			//Formularrio para la asignacion de camas

	// Constantes
	public final static String ACEPTAR = "ACEPTAR";									//Opcion Aceptar
	public final static String CANCELAR = "CANCELAR";								//Opcion Cancelar
	private final static String CLASE_COVID = "Clase covid";						//Opcion para la clase Covid
	private final static String CLASE_QUEMADOS = "Clase quemados";					//Opcion para la clase Quemados
	private final static String CLASE_CIRUGIA = "Clase cirugia";					//Opcion para la clase Quemados
	private final static String UBICACION_AREA_COVID = "Covid";						//Opcion para la ubicacion en ventana
	private final static String UBICACION_AREA_QUEMADOS = "Quemados";				//Opcion para la ubicacion en pasillo
	private final static String UBICACION_AREA_CIRUGIA = "Cirugia";					//Opcion para la ubicacion en el centro


	// Atributos
    private Hospital hospital;						//Hospital


    // Atributos de interfaz
    private InterfazHospital principal;				//Interfaz principal
    private JPanel panelBotones;					//Panel con los botones
    private JPanel panelDatos;						//Panel con los datos
    private JButton botonAceptar;					//Boton aceptar
    private JButton botonCancelar;					//Boton cancelar
	private JComboBox cbClase;						//Combo de seleccion de la clase
	private JComboBox cbUbicacion;					//Combo de seleccion de la ubicacion
    private JTextField txtCedula;					//Texto de ingreso de la cedula
    private JTextField txtNombre;					//Texto de ingreso del nombre
    private JTextField txtApellido;					//Texto de ingreso del apellido
    private JTextField txtHistorial;				//Texto de ingreso del historial
    private JTextField txtFechaIngreso;				//Texto de ingreso del diagnostico

    


    // Constructores
    public DialogoAsignacion( InterfazHospital pPrincipal, Hospital pHospital ){  //Crea el formulario de la asignacion de camas
        // Guarda la referencia a la ventana padre
        principal = pPrincipal;

        // Guarda la referencia al avión
        hospital = pHospital;

        setTitle( "Registro de paciente" );
        setSize( 300, 350 );
        setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );

        setLayout( new BorderLayout( ) );

        JPanel panelAux = new JPanel( );
        panelAux.setBorder( new EmptyBorder( 10, 10, 10, 10 ) );
        panelAux.setLayout( new BorderLayout( ) );
        add( panelAux, BorderLayout.CENTER );

        // Crea el panel de datos
        inicializarPanelDatos( );
        panelAux.add( panelDatos, BorderLayout.CENTER );

        // Crea el panel de botones
        inicializarPanelBotones( );
        panelAux.add( panelBotones, BorderLayout.SOUTH );

        setModal( true );
        setLocationRelativeTo( principal );
        setResizable( false );
    }

    

    // Métodos
    public void registrar( ){			//Procesa el registro de un paciente. Recoge los datos del paciente, de la clase y ubicacion en la que desea la cama y procesa su registro
    	Clase clase;
    	Ubicacion ubicacion;
    	String nombre;
    	String cedula;
    	String apellido;
    	String historial;
    	String fechaIngreso;
    	Paciente paciente;
    	

    	nombre = txtNombre.getText( );
    	cedula = txtCedula.getText( );
    	apellido = txtApellido.getText( );
    	historial = txtHistorial.getText( );
    	fechaIngreso = txtFechaIngreso.getText( );

    	
    	
    	

    	if( cedula == null || cedula.equals( "" ) ){
    		JOptionPane.showMessageDialog( this, "La cédula es requerida", "Registro", JOptionPane.ERROR_MESSAGE );
    	} else{
    		if( nombre == null || nombre.equals( "" ) ){
    			JOptionPane.showMessageDialog( this, "El nombre es requerido", "Registro", JOptionPane.ERROR_MESSAGE );
    		}else{
    			if( apellido == null || apellido.equals( "" ) ){
    				JOptionPane.showMessageDialog( this, "El apellido es requerido", "Registro", JOptionPane.ERROR_MESSAGE );
    			}else{
    				if( historial == null || historial.equals( "" ) ){
    					JOptionPane.showMessageDialog( this, "El hisotrial es requerido", "Registro", JOptionPane.ERROR_MESSAGE );
    				}else{
    					if( fechaIngreso == null || fechaIngreso.equals( "" ) ){
    						JOptionPane.showMessageDialog( this, "La fecha de ingreso es requerida", "Registro", JOptionPane.ERROR_MESSAGE );
    					} else {
    						// Crea al paciente
    						paciente = new Paciente( cedula, nombre, apellido, historial, fechaIngreso );

    						// Verifica que no este ya el paciente registrado
    						Cama cama = hospital.buscarPaciente( paciente );

    						if( cama != null ){
    							JOptionPane.showMessageDialog( this, "El paciente ya tiene una cama asignada", "Registro", JOptionPane.ERROR_MESSAGE );
    						} else{
    							// Registra al paciente
    							String sClase = ( String )cbClase.getSelectedItem( );
    							if( sClase.equals( CLASE_COVID ) ){
    								clase = Clase.COVID;
    							}else if ( sClase.equals( CLASE_CIRUGIA ) ) {
    								clase = Clase.CIRUGIA;
    							}else{
    								clase = Clase.QUEMADOS;
    							}

    							String sUbicacion = ( String )cbUbicacion.getSelectedItem( );
    							if( sUbicacion.equals( UBICACION_AREA_COVID ) ){
    								ubicacion = Ubicacion.AREA_COVID;
    							}
    							else if( sUbicacion.equals( UBICACION_AREA_QUEMADOS ) )
    							{
    								ubicacion = Ubicacion.AREA_QUEMADOS;
    							} else {
    								ubicacion = Ubicacion.AREA_CIRUGIA;
    							}

    							cama = hospital.asignarCama( clase, ubicacion, paciente );

    							if( cama == null ){
    								JOptionPane.showMessageDialog( this, "No hay camas disponibles con dichas características", "Registro", JOptionPane.ERROR_MESSAGE );
    							}else{
    								principal.actualizar( );
    								dispose( );
    							}
    						}
    					}
    				}
    			}
    		}
    	}
    }



    public void inicializarPanelDatos( ){					//Inicializa el panel con los datos del paciente
        panelDatos = new JPanel( );
        panelDatos.setLayout( new GridLayout( 8, 1, 1, 6 ) );
        panelDatos.setBorder( BorderFactory.createTitledBorder( "Datos del paciente" ) );

        // Cédula
        JPanel panelCedula = new JPanel( );
        panelCedula.setLayout( new FlowLayout( FlowLayout.RIGHT, 5, 0 ) );
        JLabel etiquetaCedula = new JLabel( "Cédula " );
        txtCedula = new JTextField( );
        txtCedula.setColumns( 14 );
        panelCedula.add( etiquetaCedula );
        panelCedula.add( txtCedula );
        panelDatos.add( panelCedula );

        // Nombre
        JPanel panelNombre = new JPanel( );
        panelNombre.setLayout( new FlowLayout( FlowLayout.RIGHT, 5, 0 ) );
        JLabel etiquetaNombre = new JLabel( "Nombre " );
        txtNombre = new JTextField( );
        txtNombre.setColumns( 15 );
        panelNombre.add( etiquetaNombre );
        panelNombre.add( txtNombre );
        panelDatos.add( panelNombre );
        
     // Apellido
        JPanel panelApellido = new JPanel( );
        panelNombre.setLayout( new FlowLayout( FlowLayout.RIGHT, 5, 0 ) );
        JLabel etiquetaApellido = new JLabel( "            Apellido" );
        txtApellido = new JTextField( );
        txtApellido.setColumns( 15 );
        panelApellido.add( etiquetaApellido );
        panelApellido.add( txtApellido );
        panelDatos.add( panelApellido );

     // Historial
        JPanel panelHistorial = new JPanel( );
        panelHistorial.setLayout( new FlowLayout( FlowLayout.RIGHT, 5, 0 ) );
        JLabel etiquetaHistorial = new JLabel( "Historial " );
        txtHistorial = new JTextField( );
        txtHistorial.setColumns( 15 );
        panelHistorial.add( etiquetaHistorial );
        panelHistorial.add( txtHistorial );
        panelDatos.add( panelHistorial );
        
     // Fecha de Ingreso
        JPanel panelFechaIngreso = new JPanel( );
        panelFechaIngreso.setLayout( new FlowLayout( FlowLayout.RIGHT, 5, 0 ) );
        JLabel etiquetaFechaIngreso = new JLabel( "F. Ingreso:  " );
        txtFechaIngreso = new JTextField( );
        txtFechaIngreso.setColumns( 15 );
        panelFechaIngreso.add( etiquetaFechaIngreso );
        panelFechaIngreso.add( txtFechaIngreso );
        panelDatos.add( panelFechaIngreso );
        
        // Ubicación en el hospital
        JPanel pUbicacion = new JPanel( new FlowLayout( FlowLayout.RIGHT ) );
        pUbicacion.setPreferredSize( new Dimension( 250, 30 ) );
        JLabel lUbicacion = new JLabel( "Ubicación " );
        cbUbicacion = new JComboBox( );
        cbUbicacion.setAlignmentX( Component.LEFT_ALIGNMENT );
        cbUbicacion.setPreferredSize( txtCedula.getPreferredSize( ) );
        cbUbicacion.addActionListener( this );
        pUbicacion.add( lUbicacion );
        pUbicacion.add( cbUbicacion );

        // Clase de cama
        JPanel pClase = new JPanel( new FlowLayout( FlowLayout.RIGHT ) );
        pClase.setPreferredSize( new Dimension( 250, 30 ) );
        JLabel lClase = new JLabel( "Clase " );
        cbClase = new JComboBox( );
        cbClase.setAlignmentX( Component.LEFT_ALIGNMENT );
        cbClase.addActionListener( this );
        cbClase.addItem( CLASE_QUEMADOS );
        cbClase.addItem( CLASE_COVID );
        cbClase.addItem( CLASE_CIRUGIA );
        cbClase.setPreferredSize( txtCedula.getPreferredSize( ) );
        pClase.add( lClase );
        pClase.add( cbClase );

        panelDatos.add( pClase );
        panelDatos.add( pUbicacion );
    }


    public void inicializarPanelBotones( ){				//Inicializa el panel con los botones
        panelBotones = new JPanel( );
        panelBotones.setLayout( new GridLayout( 1, 2, 8, 1 ) );

        // Aceptar
        botonAceptar = new JButton( );
        botonAceptar.setText( "Aceptar" );
        botonAceptar.setActionCommand( ACEPTAR );
        botonAceptar.addActionListener( this );
        panelBotones.add( botonAceptar );

        // Cancelar
        botonCancelar = new JButton( );
        botonCancelar.setText( "Cancelar" );
        botonCancelar.setActionCommand( CANCELAR );
        botonCancelar.addActionListener( this );
        panelBotones.add( botonCancelar );
    }


    public void actionPerformed( ActionEvent pEvento ){ 				//Acciones de respuesta a los eventos de la interfaz
        String comando = pEvento.getActionCommand( );

        if( comando.equals( ACEPTAR ) ){
            registrar( );
        } else if( comando.equals( CANCELAR ) ) {
            principal.actualizar( );
            dispose( );
        } else {
            if( pEvento.getSource( ) == cbClase ){
                String sClase = ( String )cbClase.getSelectedItem( );

                if( sClase.equals( CLASE_QUEMADOS ) ){
                    cbUbicacion.removeAllItems( );
                    cbUbicacion.addItem( UBICACION_AREA_QUEMADOS);
                } else if ( sClase.equals( CLASE_CIRUGIA ) ){
                    cbUbicacion.removeAllItems( );
                    cbUbicacion.addItem( UBICACION_AREA_CIRUGIA );
                } else {
                    cbUbicacion.removeAllItems( );
                    cbUbicacion.addItem( UBICACION_AREA_COVID );
                }
                cbUbicacion.validate( );
            }
        }
    }
}




















