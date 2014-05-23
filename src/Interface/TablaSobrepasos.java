package Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class TablaSobrepasos extends JFrame {

	private JPanel contentPane;
	private JTable tabla;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TablaSobrepasos frame = new TablaSobrepasos(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TablaSobrepasos(final MainFrame m) {
		setTitle("Campeonato Paralelo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 11, 403, 240);
		contentPane.add(scrollPane);
		
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportView(scrollPane1);

		
		
		 tabla = new JTable();
		scrollPane1.setViewportView(tabla);
		
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Piloto");
		modelo.addColumn("Promedio de sobrepasos");
		
		//Si no hay ningun piloto ingresado muestra un mensaje de error
		if(m._campeonato.getPilotos().size() == 0)
		{
			JOptionPane.showMessageDialog(null, "No hay ningun Piloto cargado");
		}
		else
		{
			//Recorre la lista de Pilotos y los muestra en la tabla con su respectivo promedio de sobrepasos
			for (int i = 0; i < m._campeonato.getPilotos().size(); i++) 
			{
				String nombrePiloto = m._campeonato.getPilotos().get(i).getNombre();
				String sobrepasosPiloto = Integer.toString(m._campeonato.getPilotos().get(i).getCantidadDeSobrepasos());
			
				modelo.addRow(new String[] {nombrePiloto,sobrepasosPiloto});
			}
		
			tabla.setModel(modelo);
			dispose();	
		}
	}
	
}


