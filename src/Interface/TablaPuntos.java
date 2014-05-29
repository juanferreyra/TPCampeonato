package Interface;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class TablaPuntos extends JFrame 
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
					TablaPuntos frame = new TablaPuntos(null);
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
	public TablaPuntos(final MainFrame m) 
	{
		setTitle("Tabla de Puntaciones");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		modelo.addColumn("Puntos");
		
		//Si no hay ningun piloto ingresado muestra un mensaje de error
		if(m._campeonato.getPilotos().size() == 0)
		{
			JOptionPane.showMessageDialog(null, "No hay ningun Piloto cargado");
		}
		else
		{
			//Recorre la lista de Pilotos y los muestra en la tabla con su respectivo puntaje
			for (int i = 0; i < m._campeonato.getPilotos().size(); i++) 
			{
				String nombrePiloto = m._campeonato.getPilotos().get(i).getNombre();
				String puntosPiloto = Integer.toString(m._campeonato.getPilotos().get(i).get_puntos());
			
				modelo.addRow(new String[] {nombrePiloto,puntosPiloto});
			}
		
			tabla.setModel(modelo);
			dispose();	
		}
	}
}
