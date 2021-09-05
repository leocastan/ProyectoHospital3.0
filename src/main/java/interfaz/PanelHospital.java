package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.mundo.*;

@SuppressWarnings("serial")
public class PanelHospital extends JPanel {				//Panel que dibuja el hospital
    // Atributos
    private Hospital hospital;							//Hospital del modelo del mundo


    // Atributos de interfaz
    private ImageIcon imagen;							//Imagen del hospital


    // Constructores
    public PanelHospital( Hospital pHospital ){			//Crea el panel del hospital; Se dibujan el hospital y todas las camas
        super( new BorderLayout( ) );
        hospital = pHospital;
        imagen = new ImageIcon( "data/imagenes/hospital.png" );  //Imagen del centro data/imagenes/avion.png
        setPreferredSize( new Dimension( imagen.getIconWidth( ), imagen.getIconHeight( ) ) );
        setOpaque( false );

        // Adiciona las imágenes de las camas COVID
        JPanel panelCovid = new JPanel( );
        dibujarCamasCovid( panelCovid );
        add( panelCovid, BorderLayout.NORTH );
        
        // Adiciona las imágenes de las camas Cirujia
        JPanel panelCirugia = new JPanel( );
        dibujarCamasCirugia( panelCirugia );
        add( panelCirugia, BorderLayout.SOUTH );

        // Adiciona las imágenes de las camas Quemados
        JPanel panelQuemados = new JPanel( );
        dibujarCamasQuemados( panelQuemados );
        add( panelQuemados, BorderLayout.CENTER );
        
    }

    

    // Métodos
    public void paint( Graphics pGrafica ){						//Dibuja la grafica del Hospital
        pGrafica.drawImage( imagen.getImage( ), 0, 0, null, null );
        super.paint( pGrafica );
    }


    private void dibujarCamasCovid( JPanel pPanel ){								//Crea las graficas de las camas COVID del hospital
        pPanel.setLayout( new GridLayout( Hospital.CAMAS_COVID / 3, 1, 8, 10 ) );  
        pPanel.setBorder( new EmptyBorder( 20, 210, 20, 210 ) ); 					//Espacio bacio superior, izquierda, inferior, derecha

        Cama[] covid = hospital.obtenerCamasCovid( );
        Cama cama;

        for( int i = 0; i < covid.length; i++ ){
            cama = covid[ i ];
            CamaGrafica camaG = new CamaGrafica( cama );
            pPanel.add( camaG );
            if( i % 4 == 1 ){
                pPanel.add( new JLabel( " " ) );
            }
        }
        pPanel.setOpaque( false );
    }

    private void dibujarCamasQuemados( JPanel pPanel ){			//Crea las graficas de las camas de quemados del hospital
        pPanel.setLayout( new GridLayout( Hospital.CAMAS_QUEMADOS / 3, 5, 8, 10 ) );  //6, 7, 5, 4
        pPanel.setBorder( new EmptyBorder(10, 210, 20,210) );   

        Cama[] quemados = hospital.obtenerCamasQuemados( );
        Cama cama;

        for( int i = 0; i < quemados.length; i++ ){
            cama = quemados[ i ];

            CamaGrafica camaG = new CamaGrafica( cama );
            pPanel.add( camaG );
            if( i % 4 == 1 ){
                pPanel.add( new JLabel( " " ) );
            }
        }
        pPanel.setOpaque( false );
    }
    
    private void dibujarCamasCirugia( JPanel pPanel ){			//Crea las graficas de las camas de cirugias del hospital
        pPanel.setLayout( new GridLayout( Hospital.CAMAS_CIRUGIA / 3, 5, 8, 10 ) );  //10, 5, 8, 10 
        pPanel.setBorder( new EmptyBorder( 10, 210, 20, 210 ) );  //0, 200, 100, 200

        Cama[] cirugia = hospital.obtenerCamasCirugia( );
        Cama cama;

        for( int i = 0; i < cirugia.length; i++ ){
            cama = cirugia[ i ];

            CamaGrafica camaG = new CamaGrafica( cama );
            pPanel.add( camaG );
            if( i % 4 == 1 ){
                pPanel.add( new JLabel( " " ) );
            }
        }
        pPanel.setOpaque( false );
    }
}
	


