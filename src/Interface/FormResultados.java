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
import javax.swing.JScrollBar;
import java.awt.ScrollPane;
import javax.swing.JButton;

public class FormResultados extends JFrame 
{

	private JPanel contentPane;
	private JTable tabla;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					FormResultados frame = new FormResultados(null);
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormResultados(final MainFrame m) 
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 524, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(-2, 13, 509, 456);
		contentPane.add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane.setViewportView(scrollPane_1);
		
				
				
				tabla = new JTable();
				scrollPane_1.setColumnHeaderView(tabla);
				
				JButton btnGuardar = new JButton("Guardar");
				btnGuardar.setBounds(299, 484, 89, 23);
				contentPane.add(btnGuardar);
				
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.setBounds(397, 484, 89, 23);
				contentPane.add(btnCancelar);
		
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Piloto");
		modelo.addColumn("Tiempo de Clasificacion");
		modelo.addColumn("Posicion Final");
		
		
		if(m._campeonato.getPilotos().size() == 0)
		{
			JOptionPane.showMessageDialog(null, "No hay ningun Piloto cargado");
		}
		else
		{
			//Recorre la lista de Pilotos y los muestra en la tabla con su puntaje
			for (int i = 0; i < m._campeonato.getPilotos().size(); i++) 
			{
				String nombrePiloto = m._campeonato.getPilotos().get(i).getNombre();
			
				modelo.addRow(new String[] {nombrePiloto});
			}
		
		tabla.setModel(modelo);
		dispose();	
		}
		
		
		
	}
}
