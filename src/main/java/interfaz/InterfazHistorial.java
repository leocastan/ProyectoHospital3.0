package interfaz;

import java.awt.Toolkit;

import javax.swing.table.DefaultTableModel;
import modelo.mundo.Cama;
import modelo.mundo.Hospital;
import modelo.mundo.Paciente;

public class InterfazHistorial extends javax.swing.JFrame {
	private Hospital hospital;


	InterfazHistorial(Hospital hospital) {

		setTitle( "Historial Pacientes Ingresados" );
		setIconImage(Toolkit.getDefaultToolkit().getImage("data/imagenes/hospitalvalles.jpg"));
		this.hospital = hospital;
		initComponents();
		iniciar();
	}

	public void iniciar() {
		Cama camas[] = new Cama[21];

		Cama camasCirugia[] = hospital.obtenerCamasCirugia();
		Cama camasCovid[] = hospital.obtenerCamasCovid();
		Cama camasQuemados[] = hospital.obtenerCamasQuemados();

		int i = 0;

		for (Cama c : camasCirugia) {
			camas[i++] = c;
		}

		for (Cama c : camasCovid) {
			camas[i++] = c;
		}

		for (Cama c : camasQuemados) {
            camas[i++] = c;
        }
        
        DefaultTableModel modelo = (DefaultTableModel) tablaHistorial.getModel();
        modelo.setRowCount(0);
        
        for (Cama cama : camas) {
            Paciente paciente = cama.darPaciente();
            String ubicacion;
            
            if (paciente == null) continue;
            
            
            if (cama.darUbicacion() == Cama.Ubicacion.AREA_CIRUGIA) {
                ubicacion = "Cirugia";
            } else if (cama.darUbicacion() == Cama.Ubicacion.AREA_COVID) {
                ubicacion = "Covid";
            } else {
                ubicacion = "Quemados";
            }
            
            modelo.addRow(new Object[] {
            		paciente.darHistorial(),
            		paciente.darCedula(),
            		paciente.darApellido(),
            		paciente.darNombre(),
            		paciente.darFechaIngreso(),
            		cama.darNumero(),
            		ubicacion
            });
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaHistorial = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tablaHistorial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Historial", "Cedula", "Apellido", "Nombre", "Fecha Ingreso", "# Cama", "Ubicacion"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaHistorial);
        if (tablaHistorial.getColumnModel().getColumnCount() > 0) {
            tablaHistorial.getColumnModel().getColumn(1).setResizable(false);
            tablaHistorial.getColumnModel().getColumn(2).setResizable(false);
            tablaHistorial.getColumnModel().getColumn(3).setResizable(false);
            tablaHistorial.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 798, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }

    
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaHistorial;
  
    
    private class HistoryItem {
        private Paciente paciente;
    }
}
