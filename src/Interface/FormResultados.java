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
import javax.swing.border.MatteBorder;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;

import Persistencia.Serializacion;

public class FormResultados extends JFrame 
{

	private JPanel contentPane;
	private JTable tabla;
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
		mostrarCarrera.setText(	m._campeonato.getCarreras().get(m._carreraSeleccionada).toString());
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(-2, 41, 509, 428);
		contentPane.add(scrollPane);
		
		tabla = new JTable();
		scrollPane.setViewportView(tabla);
		final DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Piloto");
		modelo.addColumn("Tiempo de Clasificacion");
		modelo.addColumn("Posicion Final");
		
		//Recorre la lista de Pilotos y los muestra en la tabla para agregar sus resultados en la carrera
		for (int i = 0; i < m._campeonato.getPilotos().size(); i++)
		{
			String nombrePiloto = m._campeonato.getPilotos().get(i).getNombre();
			String clasificacionPilot = "0";
			String posicionFinal = "0";
			//chequeo que el piloto este en la carera asi muestro su clasificacion y su punto
			if(m._campeonato.getCarreras().get(m._carreraSeleccionada).getResultado().contains(m._campeonato.getPilotos().get(i)))
			{
				nombrePiloto = m._campeonato.getCarreras().get(m._carreraSeleccionada).getResultado().get(i).getNombre();
				clasificacionPilot = String.valueOf(m._campeonato.getCarreras().get(m._carreraSeleccionada).getResultado().get(i).getTiempoClasificacion());
				posicionFinal = String.valueOf(m._campeonato.getCarreras().get(m._carreraSeleccionada).getResultado().get(i).getPosicionFinal());	
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
				//se entiende que en la posicion N°1 estan los tiempos de clasificacion y en la posicion N°2 los resultados
				for (int i = 0; i < m._campeonato.getPilotos().size(); i++) 
				{
					m._campeonato.getCarreras().get(m._carreraSeleccionada).agregarResultado(m._campeonato.getPilotos().get(i),Double.parseDouble(modelo.getValueAt(i, 1).toString()), Integer.parseInt(modelo.getValueAt(i, 2).toString()));
				}
				m._campeonato.getCarreras().get(m._carreraSeleccionada).ordenarResultados();
				Serializacion.guardar(m._campeonato, "dato.txt");
				//JOptionPane.showMessageDialog(null, "La carrera quedo de la siguiente forma: "+m._campeonato.getCarreras().get(m._carreraSeleccionada).imprimirPilotos());
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
}
