package modelo.test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import modelo.mundo.Hospital;
import modelo.mundo.Paciente;
import modelo.mundo.Cama;
import modelo.mundo.Cama.Clase;
import modelo.mundo.Cama.Ubicacion;

public class HospitalTest {
    // Atributos
    private Hospital hospital;

    
    // Métodos
    private void setupEscenario1( ){
        hospital = new Hospital( );				//Crea la UCI del hospital

        // Paciente con covid
        String cedula1 = "012345678";
        String nombre1 = "Juan";
        String apellido1= "Diaz";
        String historial1= "abcd";
        String diagnostico1= "Covid";
        
        //Paciente quemado
        String cedula2 = "123456789";
        String nombre2 = "Maria";
        String apellido2 = "Perez";
        String historial2 = "efgh";
        String diagnostico2 = "Quemadura";
        
        //Paciente cirujia
        String cedula3 = "987654321";
        String nombre3 = "Leo";
        String apellido3 = "Castro"; 
        String historial3 = "zyxw";
        String diagnostico3 = "Cirujia";

        // Crea los pacientes
        Paciente p1 = new Paciente( cedula1, nombre1, apellido1, historial1, diagnostico1 );
        Paciente p2 = new Paciente( cedula2, nombre2, apellido2, historial2, diagnostico2 );
        Paciente p3 = new Paciente( cedula3, nombre3, apellido3, historial3, diagnostico3 );

        // Asigna el primer paciente en una cama Covid del area COVID
        hospital.asignarCama(Clase.COVID, Ubicacion.AREA_COVID, p1 );

        // Asigna al segundo paciente en una cama quemados de area de quemados
        hospital.asignarCama(Clase.QUEMADOS, Ubicacion.AREA_QUEMADOS, p2 );
        
        //Asigna al tercer paciente en una cama de cirujia del area de cirujia
        hospital.asignarCama(Clase.CIRUJIA,Ubicacion.AREA_CIRUJIA, p3);
    }
	
	
    @Test
    public void testAsignarCamaCovid( ){				//Verifica que la asignacion de una cama Covid haya sido correcta
        Cama camaP1;
        Paciente p;
        Paciente p1 = new Paciente( "012345678", "Juan", "Diaz", "abcd", "Covid" );
        // Configura los datos de prueba
        setupEscenario1( );
        camaP1 = hospital.buscarPaciente( p1 );
        // El paciente 1 se ubica en clase COVID
        assertEquals( Clase.COVID, camaP1.darClase( ) );
        // El paciente 1 se ubica en el area covid
        assertEquals( Ubicacion.AREA_COVID, camaP1.darUbicacion( ) );
        // La primera cama Covid es la número 1
        assertEquals( 1, camaP1.darNumero( ) );
        // El paciente debe ser el mismo
        p = camaP1.darPaciente( );
        assertTrue( p1.igualA( p ) );
    }
	
   
    @Test
    public void testAsignarCamaQuemados( ){		//Verifica que la asignacion de una cama de quemados haya sido la correcta
        Cama camaP2;
        Paciente p;
        Paciente p2 = new Paciente( "123456789", "Maria", "Perez", "efgh", "Quemadura");
        // Configura los datos de prueba
        setupEscenario1( );
        camaP2 = hospital.buscarPaciente( p2 );
        // El paciente 2 se ubica en area de quemados
        assertEquals( Clase.QUEMADOS, camaP2.darClase( ) );
        // El paciente 2 se ubica en el area de quemados
        assertEquals( Ubicacion.AREA_QUEMADOS, camaP2.darUbicacion( ) );
        // La primera cama quemados es la número 11
        assertEquals( 11, camaP2.darNumero( ) );
        // El pasajero debe ser el mismo
        p = camaP2.darPaciente( );
        assertTrue( p2.igualA( p ) );
    }
    

    @Test
    public void testAsignarCamaCirujia( ){		//Verifica que la asignacion de una cama de cirujia haya sido la correcta
        Cama camaP3;
        Paciente p;
        Paciente p3 = new Paciente( "987654321", "Leo", "Castro", "zyxw", "Cirujia");
        // Configura los datos de prueba
        setupEscenario1( );
        camaP3 = hospital.buscarPaciente( p3 );
        // El paciente 2 se ubica en area de quemados
        assertEquals( Clase.CIRUJIA, camaP3.darClase( ) );
        // El paciente 2 se ubica en el area de quemados
        assertEquals( Ubicacion.AREA_CIRUJIA, camaP3.darUbicacion( ) );
        // La primera cama cirujia es la número 21
        assertEquals( 21, camaP3.darNumero( ) );
        // El pasajero debe ser el mismo
        p = camaP3.darPaciente( );
        assertTrue( p3.igualA( p ) );
    }
    
    
    @Test
    public void testBuscarPaciente1( ){											//Verifica la busqueda de una paciente covid que existe
        Paciente p;
        Cama c;
        Paciente p1 = new Paciente( "012345678", "Juan", "Diaz", "abcd", "Covid" );
        // Configura los datos de prueba
        setupEscenario1( );
        c = hospital.buscarPacienteCovid( p1 );
        if( c == null )
            fail( "El paciente debería existir" );
        else{
            p = c.darPaciente( );
            assertEquals( "012345678", p.darCedula( ) );
            assertEquals( "Juan", p.darNombre( ) );
            assertEquals( "Diaz", p.darApellido() );
            assertEquals( "abcd", p.darHistorial() );
        }
    }
    
    
    @Test
    public void testBuscarPaciente2( ){											//Verifica la busqueda de un paciente covid que no existe
        Cama c;
        Paciente p2 = new Paciente("Quemadura", "efgh", "Perez", "Maria", "123456789");
        // Configura los datos de prueba
        setupEscenario1( );
        c = hospital.buscarPacienteCovid( p2 );
        if( c == null )
            assertTrue( true );
        else
        {
            fail( "El paciente NO debería existir" );
        }
    }
      
    
    @Test
    public void testBuscarPaciente3( ){ 										//Verifica la busqueda de un paciente quemado que existe
        Paciente p;
        Cama c;
        Paciente p2 = new Paciente( "123456789", "Maria", "Perez", "efgh","Quemadura");
        // Configura los datos de prueba
        setupEscenario1( );
        c = hospital.buscarPacienteQuemado( p2 );
        if( c == null )
            fail( "El paciente debería existir" );
        else{
            p = c.darPaciente( );
            assertEquals( "123456789", p.darCedula( ) );
            assertEquals( "Maria", p.darNombre() );
            assertEquals( "Perez", p.darApellido() );
            assertEquals( "efgh", p.darHistorial() );
        }
    }
    
    
    @Test
    public void testBuscarPaciente4( ){											//Verifica la busqueda de una paciente quemado que no existe
        Cama c;
        Paciente p1 = new Paciente("Covid", "abcd", "Diaz", "Juan", "012345678");
        // Configura los datos de prueba
        setupEscenario1( );
        c = hospital.buscarPacienteQuemado( p1 );
        if( c == null )
            assertTrue( true );
        else{
            fail( "El paciente NO debería existir" );
        }
    }
    
    
    @Test
    public void testBuscarPaciente5( ){											//Verifica la busqueda de una paciente cirujia que existe
        Paciente p;
        Cama c;
        Paciente p1 = new Paciente( "987654321", "Leo", "Castro", "zyxw", "Cirujia" );
        // Configura los datos de prueba
        setupEscenario1( );
        c = hospital.buscarPacienteCirujia( p1 );
        if( c == null )
            fail( "El paciente debería existir" );
        else{
            p = c.darPaciente( );
            assertEquals( "987654321", p.darCedula( ) );
            assertEquals( "Leo", p.darNombre( ) );
            assertEquals( "Castro", p.darApellido() );
            assertEquals( "zyxw", p.darHistorial() );
        }
    }

    
    @Test
    public void testBuscarPaciente6( ){											//Verifica la busqueda de un paciente cirujia que no existe
        Cama c;
        Paciente p2 = new Paciente("Cirujia", "zyxw", "Castro", "Leo", "987654321" );
        // Configura los datos de prueba
        setupEscenario1( );
        c = hospital.buscarPacienteCirujia( p2 );
        if( c == null )
            assertTrue( true );
        else
        {
            fail( "El paciente NO debería existir" );
        }
    }
    
    
    @Test
    public void testBuscarCamaCovidLibre1( ){									//Busca la siguiente cama covid libre
        Cama c;
        // Configura los datos de prueba
        setupEscenario1( );
        // La siguiente cama Covid libre es la 2
        c = hospital.buscarCamaCovidLibre( Ubicacion.AREA_COVID );
        assertEquals( 2, c.darNumero( ) );
        // La siguiente cama Covid de Recien ingresados libre es la 4
        /*c = hospital.buscarCamaCovidLibre( Ubicacion.AREA_COVID );
        assertEquals( 4, c.darNumero( ) );*/
    }
    
    
    @Test
    public void testBuscarCamaQuemadosLibre1( ){								//Busca la siguiente cama quemados libre
        Cama c;
        // Configura los datos de prueba
        setupEscenario1( );
        // La siguiente cama quemados de terminal libre es la 12
        c = hospital.buscarCamaQuemadosLibre( Ubicacion.AREA_QUEMADOS);
        assertEquals(12, c.darNumero( ) );
        /*// La siguiente cama quemados de recien ingresados libre es la 9
        c = hospital.buscarCamaQuemadosLibre( Ubicacion.AREA_COVID);
        assertEquals( 9, c.darNumero( ) );

        // La siguiente cama quemadaos de recuperacion es la 10
        c = hospital.buscarCamaQuemadosLibre(Ubicacion.AREA_QUEMADOS);
        assertEquals( 12, c.darNumero( ) );*/
    }
    

    @Test
    public void testBuscarCamaCirujiaLibre1( ){		//Busca la siguiente cama quemados libre
        Cama c;
        // Configura los datos de prueba
        setupEscenario1( );
        // La siguiente cama quemados de terminal libre es la 22
        c = hospital.buscarCamaCirujiaLibre( Ubicacion.AREA_CIRUJIA);
        assertEquals(22, c.darNumero( ) );
        // La siguiente cama quemados de recien ingresados libre es la 9
        /*c = hospital.buscarCamaQuemadosLibre( Ubicacion.AREA_COVID);
        assertEquals( 9, c.darNumero( ) );*/

        /*// La siguiente cama quemadaos de recuperacion es la 10
        c = hospital.buscarCamaQuemadosLibre(Ubicacion.AREA_QUEMADOS);
        assertEquals( 12, c.darNumero( ) );*/
    }


    @Test
    public void testCalcularPorcentajeOcupacion1( ){			//Prueba el porcentaje de ocupacion
        double porcentajeEsperado, porcentaje;
        Paciente p4 = new Paciente( "234567890", "Mia", "Marin", "ijkl", "Quemadura" );
        Paciente p5 = new Paciente( "345678901", "Pedro", "Pluas", "mnop", "Covid" );
        Paciente p6 = new Paciente( "456789012", "Ale", "Aguirre", "qrst", "Cirujia" );
        // Configura los datos de prueba
        setupEscenario1( );
        // inicialmente el porcentaje de ocupación es igual a 3*100/21/////ojo/////////
        porcentajeEsperado = 14.285714285714285;
        porcentaje = hospital.calcularPorcentajeOcupacion( );
        assertEquals( porcentajeEsperado, porcentaje, 0 );
        // Asigno otros tres pacientes
        hospital.asignarCama( Clase.QUEMADOS, Ubicacion.AREA_QUEMADOS, p4 );
        hospital.asignarCama( Clase.COVID, Ubicacion.AREA_COVID, p5 );
        hospital.asignarCama( Clase.CIRUJIA, Ubicacion.AREA_CIRUJIA, p6 );
        // Ahora el porcentaje es 6*100/21 ///////ojo///////////
        porcentajeEsperado = 28.57142857142857;
        porcentaje = hospital.calcularPorcentajeOcupacion( );
        assertEquals( porcentajeEsperado, porcentaje, 0 );
    }

    
    @Test
    public void testContarCamasCovidOcupadas1( ){		//Cuenta las camas covid ocupadas
        Cama c;
        // Configura los datos de prueba
        setupEscenario1( );
        Paciente p3 = new Paciente( "234567890", "Mia", "Marin", "ijkl", "Covid" );
        Paciente p4 = new Paciente( "345678901", "Pedro", "Pluas", "mnop", "Covid" );
        // Inicialmente las camas covid ocupadas son 1
        assertEquals( 1, hospital.contarCamasCovidOcupadas( ) );
        // Asigno otros dos pacientes
        c = hospital.asignarCama( Clase.COVID, Ubicacion.AREA_COVID, p3 );
        if( c == null )
            fail( "Debió asignar alguna cama 1" );
        c = hospital.asignarCama( Clase.COVID, Ubicacion.AREA_COVID, p4 );
        if( c == null )
            fail( "Debió asignar alguna cama 2" );
        // Ahora el numero de camas ocupadas es 3
        assertEquals( 3, hospital.contarCamasCovidOcupadas( ) );
    }
    

    @Test
    public void testContarCamasQuemadosOcupadas1( ){			//Cuenta las camas quemados ocupadas
        // Configura los datos de prueba
        setupEscenario1( );
        Paciente p3 = new Paciente( "234567890", "Mia", "Marin", "ijkl", "Quemadura");
        Paciente p4 = new Paciente( "345678901", "Pedro", "Pluas", "mnop", "Quemadura");
        // Inicialmente las camas quemados ocupadas son 1
        assertEquals( 1, hospital.contarCamasQuemadosOcupadas( ) );
        // Asigno otros dos pasajeros
        hospital.asignarCama( Clase.QUEMADOS, Ubicacion.AREA_QUEMADOS, p3 );
        hospital.asignarCama( Clase.QUEMADOS, Ubicacion.AREA_QUEMADOS, p4 );
        // Ahora el numero de sillas ocupadas es 3
        assertEquals( 3, hospital.contarCamasQuemadosOcupadas( ) );
    }
    
    
    @Test
    public void testContarCamasCirujiaOcupadas1( ){			//Cuenta las camas cirujia ocupadas
        // Configura los datos de prueba
        setupEscenario1( );
        Paciente p3 = new Paciente( "234567890", "Mia", "Marin", "ijkl", "Cirujia");
        Paciente p4 = new Paciente( "345678901", "Pedro", "Pluas", "mnop","Cirujia");
        // Inicialmente las camas cirujia ocupadas es 1
        assertEquals( 1, hospital.contarCamasCirujiaOcupadas( ) );
        // Asigno otros dos pasajeros
        hospital.asignarCama( Clase.CIRUJIA, Ubicacion.AREA_CIRUJIA, p3 );
        hospital.asignarCama( Clase.CIRUJIA, Ubicacion.AREA_CIRUJIA, p4 );
        // Ahora el numero de camas ocupadas es 3////ojo////
        assertEquals( 1, hospital.contarCamasQuemadosOcupadas( ) );
    }
    
    
    @Test
    public void testDesasignarCama1( ){			//Verificar la designacion de camas
        Cama c;
        Paciente p1 = new Paciente("Covid", "abcd", "Diaz", "Juan", "012345678" );
        // Configura los datos de prueba
        setupEscenario1( );
        hospital.desasignarCama( p1 );
        // Ya el paciente no debe estar en el hospital
        c = hospital.buscarPaciente( p1 );
        if( c == null )
            assertTrue( true );
        else
            fail( "El paciente no debería estar" );
    }
    
    
    
}