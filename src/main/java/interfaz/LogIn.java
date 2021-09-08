package interfaz;

import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class LogIn extends JFrame {
	
	//Atributos de interfaz
	private JTextField txtUsuario;
	private JPasswordField pfClave;
	private ImageIcon imagen;
	private String nombre;
	
    // Constructores
    public LogIn( ){
    	setResizable(false);
    	setIconImage(Toolkit.getDefaultToolkit().getImage("data/imagenes/hospitalvalles.jpg"));
    	getContentPane().setBackground(new Color(102, 255, 204));
    	setBackground(new Color(51, 255, 204));					//Crea la interfaz hospital; se crea el hospital y se presenta en una grafica su estado inicial
        setTitle( "Hospital de los Valles" );
        setSize( 400, 277);
        getContentPane().setLayout(null);   
               
        JLabel lblNewLabel = new JLabel("SISTEMA DE ACCESO");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel.setBounds(98, 20, 206, 19);
        getContentPane().add(lblNewLabel);
        
        JLabel lblUciHospitalDe = new JLabel("UCI HOSPITAL DE LOS VALLES");
        lblUciHospitalDe.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblUciHospitalDe.setBounds(61, 57, 284, 19);
        getContentPane().add(lblUciHospitalDe);
        
        JLabel lblNewLabel_1 = new JLabel("USUARIO: ");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_1.setBounds(41, 109, 72, 13);
        getContentPane().add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("CONTRASEÑA:");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setBounds(20, 149, 93, 13);
        getContentPane().add(lblNewLabel_2);
        
        txtUsuario = new JTextField();
        txtUsuario.setBounds(156, 107, 175, 19);
        getContentPane().add(txtUsuario);
        txtUsuario.setColumns(10);
        
        JButton btnIngresar = new JButton("INGRESAR");
        btnIngresar.addActionListener(new ActionListener() {
        	//Permite crear usuario y clave para dar acceso al sistema
        	public void actionPerformed(ActionEvent e) {
        		char[] clave = pfClave.getPassword();
        		String claveFinal = new String(clave);
        		
        		if(txtUsuario.getText().equals("CastroL")&& claveFinal.equals("123") 
        				|| txtUsuario.getText().equals("UshiñaA")&& claveFinal.equals("123") 
        				|| txtUsuario.getText().equals("FalconiA")&& claveFinal.equals("123")) {
        			dispose();
        			JOptionPane.showMessageDialog(null,  "Bienvenido al Sistema UCI","INGRESASTE", JOptionPane.INFORMATION_MESSAGE);
        			InterfazHospital p = new InterfazHospital();
        			p.setVisible(true);

        		}else {
        			JOptionPane.showMessageDialog(null, "Usuario o Contraseña incorrectos", "ERROR", JOptionPane.ERROR_MESSAGE );
        			txtUsuario.setText("");
        			pfClave.setText("");
        			txtUsuario.requestFocus();
        		}
        		
        		
        		
        	}
        });
        btnIngresar.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnIngresar.setBounds(136, 186, 107, 32);
        getContentPane().add(btnIngresar);
        
        pfClave = new JPasswordField();
        pfClave.setBounds(156, 147, 175, 19);
        getContentPane().add(pfClave);
        
        
    }
    
    

    
    
    public static void main( String[] pArgs ){			//Ejecuta la aplicacion
        try
        {
            // Unifica la interfaz para Mac y para Windows.
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );

            LogIn acceso = new LogIn();
            acceso.setVisible (true);
            acceso.setResizable(false);
            acceso.setLocationRelativeTo(null);

        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }
    }
}
