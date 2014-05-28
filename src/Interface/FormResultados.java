package Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import Negocio.Carrera;
import Negocio.Piloto;
import Persistencia.Serializacion;

//Frame en el cual se ingresan los resultados de una carrera
@SuppressWarnings("serial")
public class FormResultados extends JFrame 
{

	private JPanel contentPane;
	private JTable tabla;
	//Muestra el nombre de la carrera a la cual 
	//se le estan agregando los resultados
	private JTextField mostrarCarrera;

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
		//Carrera seleccionada para cargar sus resultados
		final Carrera carreraSeleccionada = m._campeonato.getCarreras().get(m._carreraSeleccionada);
		carreraSeleccionada.setcarreraFinalizada(true);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 524, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblCarrera = new JLabel("Carrera:");
		lblCarrera.setBounds(10, 11, 48, 23);
		contentPane.add(lblCarrera);
		
		mostrarCarrera = new JTextField();
		mostrarCarrera.setEditable(false);
		mostrarCarrera.setBounds(57, 12, 441, 20);
		contentPane.add(mostrarCarrera);
		mostrarCarrera.setColumns(10);
		mostrarCarrera.setText(	carreraSeleccionada.toString());
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(-2, 41, 509, 428);
		contentPane.add(scrollPane);
		
		tabla = new JTable();
		scrollPane.setViewportView(tabla);
		final DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Piloto");
		modelo.addColumn("Tiempo de Clasificacion");
		modelo.addColumn("Posicion Final");
		
		//Recorre la lista de Pilotos del campeonato y los muestra en la tabla 
		//para agregar sus resultados en la carrera
		for (int i = 0; i < m._campeonato.getPilotos().size(); i++)
		{
			String nombrePiloto =  m._campeonato.getPilotos().get(i).getNombre();
			String clasificacionPilot= "0.0";
			String posicionFinal = "0";
			//Chequeo que el piloto este en la carera asi muestro su clasificacion y sus puntos
			if(carreraSeleccionada.getResultado().contains(m._campeonato.getPilotos().get(i)))
			{
				 nombrePiloto = carreraSeleccionada.getResultado().get(i).getNombre();
				 clasificacionPilot = String.valueOf(carreraSeleccionada.getResultado().get(i).getTiempoClasificacion());
				 posicionFinal = String.valueOf(carreraSeleccionada.getResultado().get(i).getPosicionFinal());	
			}
			modelo.addRow(new String[] {nombrePiloto,clasificacionPilot,posicionFinal});
		}
		
		tabla.setModel(modelo);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(299, 484, 89, 23);
		contentPane.add(btnGuardar);
		btnGuardar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				//Agrega los resultados de todos los pilotos a la carrera en cuestion
				for (int i = 0; i < m._campeonato.getPilotos().size(); i++) 
				{
					if(!esNumero(modelo.getValueAt(i,1).toString()) || !esNumero(modelo.getValueAt(i, 2).toString()))
					{
						JOptionPane.showMessageDialog(null, "Por Favor! Ingrese los campos en su formato correcto",
		                        "Error", JOptionPane.ERROR_MESSAGE);
					}
					
					Piloto piloto = m._campeonato.getPilotos().get(i);
					Double tiempoClasificacion = Double.parseDouble(modelo.getValueAt(i, 1).toString());
					Integer posicionFinal = Integer.parseInt(modelo.getValueAt(i, 2).toString());
					
					carreraSeleccionada.agregarResultado(piloto ,tiempoClasificacion, posicionFinal);
				}
			
				//Calcula los puntos obtenidos en la carrera seleccionda
				//segun las posiciones finales de los pilotos
				carreraSeleccionada.calcularPuntos();
				//Calcula los sobrepasos de cada piloto en la carrera seleccionada
				carreraSeleccionada.calcularSobrepasos();
				//Actualiza el puntaje global de campeonato
				m._campeonato.actualizarPuntaje(carreraSeleccionada);
				//Guarda los resultados en el archivo de datos
				Serializacion.guardar(m._campeonato, "dato.txt");
				dispose();
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(397, 484, 89, 23);
		contentPane.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				dispose();
			}
		});
	}
	
	private static boolean esNumero(String numero)
	{
		try 
		{
			Integer.parseInt(numero);
			return true;
		} 
		catch (NumberFormatException nfe)
		{
			try
			{
				Double.parseDouble(numero);
				return true;
			}
			catch (NumberFormatException nfe2)
			{
				return false;
			}
		}
	}
	
}
