package interfaz;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelImagen extends JPanel{ 									//Panel que contiene la imagen con el banner
    // Constantes
    public final static String RUTA_IMAGEN = "./data/imagenes/banner.jpg";	//Ruta de la imagen del banner


    // Atributos de la interfaz
    private JLabel labImagen;												//Etiqueta de la imagen


    // Constructores
    public PanelImagen( ){ 													//Construye el panel con el banner
        labImagen = new JLabel( );
        labImagen.setIcon( new ImageIcon( RUTA_IMAGEN ) );
        add( labImagen );
    }
}
