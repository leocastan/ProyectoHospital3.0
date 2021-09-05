package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import modelo.mundo.Cama;
import modelo.mundo.Cama.Clase;


@SuppressWarnings("serial")
public class CamaGrafica extends JPanel	{					//Representacion grafica de una cama en el hospital
    // Atributos de Interfaz
    private ImageIcon imagen;								//Imagen de la cama

    
    // Constructores
    public CamaGrafica( Cama pCama )	{					//Crea la representacion grafica de la cama; la imagen de la cama distingue si esta libre u ocupada
        super( new BorderLayout( ) );
        JLabel lCama = new JLabel( );
        if( pCama.darClase( ) == Clase.COVID && pCama.camaAsignada( ) ){
            imagen = new ImageIcon( "data/imagenes/cama-covid-asig.png" );
            lCama.setForeground( Color.BLACK );
        }
        else if( pCama.darClase( ) == Clase.COVID && !pCama.camaAsignada( ) ){
            imagen = new ImageIcon( "data/imagenes/cama-covid-libre.png" );
            lCama.setForeground( Color.BLACK );
        }
        else if( pCama.darClase( ) == Clase.QUEMADOS && pCama.camaAsignada( ) ){
            imagen = new ImageIcon( "data/imagenes/cama-quemados-asig.png" );
            lCama.setForeground( Color.BLACK );
        }
        else if( pCama.darClase( ) == Clase.QUEMADOS && !pCama.camaAsignada( ) ){
            imagen = new ImageIcon( "data/imagenes/cama-quemados-libre.png" );
            lCama.setForeground( Color.BLACK );
        }
        else if( pCama.darClase( ) == Clase.CIRUGIA && !pCama.camaAsignada( ) ){
            imagen = new ImageIcon( "data/imagenes/cama-cirugia-libre.png" );
            lCama.setForeground( Color.BLACK );
        }
        else if( pCama.darClase( ) == Clase.CIRUGIA && pCama.camaAsignada( ) ){
            imagen = new ImageIcon( "data/imagenes/cama-cirugia-asig.png" );
            lCama.setForeground( Color.BLACK );
        }
        setPreferredSize( new Dimension( imagen.getIconWidth( ), imagen.getIconHeight( ) ) );
        setOpaque( false );
        lCama.setPreferredSize( new Dimension( imagen.getIconWidth( ), imagen.getIconHeight( ) ) );
        lCama.setText( "" + pCama.darNumero( ) );
        lCama.setHorizontalAlignment( SwingConstants.CENTER );
        lCama.setVerticalAlignment( SwingConstants.CENTER );
        add( lCama );
    }

    
    
    // Métodos
    public void paint( Graphics pGrafica ){ 				//Dibuja la imagen de la cama
        pGrafica.drawImage( imagen.getImage( ), 0, 0, null, null );
        super.paint( pGrafica );
    }
}


