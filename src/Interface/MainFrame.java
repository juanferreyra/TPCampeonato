package Interface;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JList;
import javax.swing.JLabel;

import java.awt.Font;
import java.io.Serializable;

import javax.swing.SwingConstants;

import Negocio.Campeonato;
import Persistencia.Serializacion;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JToolBar;
import javax.swing.JSeparator;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.ScrollPaneConstants;
import java.awt.Toolkit;

public class MainFrame implements Serializable
{

	private JFrame frmCampeonatoAutomovilistico;
	protected Campeonato _campeonato;
	//Almacena todos los datos del MainFrame en una variable
	private MainFrame _this; 
	private FormAgrClasificacion _agrClasificacion;
	private FormAgrFinalizacion _agrFinalizacion;
	public JList _listCarreras; 
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
					MainFrame window = new MainFrame();
					window.frmCampeonatoAutomovilistico.setVisible(true);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	
	}

	/**
	 * Create the application.
	 */
	public MainFrame() 
	{
		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} 
		catch (Exception e) 
		{
			System.out.println("No encuentro en look and feel");
			e.printStackTrace();
		}
		
		_campeonato = new Campeonato();
		_agrClasificacion = new FormAgrClasificacion();
		_agrFinalizacion = new FormAgrFinalizacion();
		_listCarreras = new JList();

		//Carga los datos del campeonato previamente guardados
		_campeonato = Serializacion.cargar("dato.txt");
		//Actualiza la lista de carreras para mostrarlas al abrir el programa
		actualizarLista();
		
		initialize();
		//Guarda los datos del programa en un archivo de texto
		//para que no se pierdan al cerrar el programa.
		Serializacion.guardar(_campeonato, "dato.txt");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{

		_this = this;
				
		frmCampeonatoAutomovilistico = new JFrame();
		frmCampeonatoAutomovilistico.setTitle("Campeonato Automovilistico");
		frmCampeonatoAutomovilistico.setResizable(false);
		frmCampeonatoAutomovilistico.setBounds(100, 100, 300, 334);
		frmCampeonatoAutomovilistico.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCampeonatoAutomovilistico.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 132, 274, 132);
		frmCampeonatoAutomovilistico.getContentPane().add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane.setViewportView(scrollPane_1);
		
		
		scrollPane_1.setViewportView(_listCarreras);
		//Agrega evento paar que reconozca cuando se hace click sobre una carrera
		_listCarreras.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				JOptionPane.showMessageDialog(null, "Hiciste click en la carrera "+_listCarreras.getSelectedValue());
			}
		});
		
		
		JLabel lblCarreras = new JLabel("Carreras");
		lblCarreras.setForeground(new Color(102, 153, 0));
		lblCarreras.setHorizontalAlignment(SwingConstants.CENTER);
		lblCarreras.setEnabled(false);
		lblCarreras.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCarreras.setBounds(99, 96, 96, 25);
		frmCampeonatoAutomovilistico.getContentPane().add(lblCarreras);
		
		JButton btnFinalizacion = new JButton("Finalizacion");
		btnFinalizacion.setBounds(155, 46, 119, 23);
		frmCampeonatoAutomovilistico.getContentPane().add(btnFinalizacion);
		btnFinalizacion.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				_agrFinalizacion.setVisible(true);
				//TODO:deberia hacer que ingrese los resultados finales de la carrera
			}
		});
		
		JButton btnClasificacion = new JButton("Clasificacion");
		btnClasificacion.setBounds(10, 46, 124, 23);
		frmCampeonatoAutomovilistico.getContentPane().add(btnClasificacion);
		
		JLabel lblResultados = new JLabel("Resultados");
		lblResultados.setBackground(Color.WHITE);
		lblResultados.setForeground(Color.BLACK);
		lblResultados.setHorizontalAlignment(SwingConstants.CENTER);
		lblResultados.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblResultados.setEnabled(false);
		lblResultados.setBounds(19, 0, 265, 25);
		frmCampeonatoAutomovilistico.getContentPane().add(lblResultados);
		btnClasificacion.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{

				

				_agrClasificacion.setVisible(true);

				//TODO:deberia hacer que ingrese los resultados y tiempos de clasificacion
			}
		});
		
		JMenuBar menuBar = new JMenuBar();
		frmCampeonatoAutomovilistico.setJMenuBar(menuBar);
		
		JMenu mnIngresar = new JMenu("Ingresar");
		menuBar.add(mnIngresar);
		
		JMenuItem mntmPiloto = new JMenuItem("Piloto");
		mnIngresar.add(mntmPiloto);
		mntmPiloto.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{	
				//Se abre la ventana de carga de pilotos
				CargaPiloto p = new CargaPiloto(_this);
				
				p.setVisible(true);
			}
		});
		
		JMenuItem mntmEvento = new JMenuItem("Evento");
		mnIngresar.add(mntmEvento);
		mntmEvento.addActionListener(new ActionListener() 
		{
				public void actionPerformed(ActionEvent arg0) 
				{
					//Se abre la ventana de carga de carreras
					CargaCarrera c = new CargaCarrera(_this);
					c.setVisible(true);
				}
		});
		
		JMenu mnVer = new JMenu("Ver");
		menuBar.add(mnVer);
		
		JMenuItem mntmTablaPuntuaciones = new JMenuItem("Tabla de Puntuaciones");
		mnVer.add(mntmTablaPuntuaciones);
		mntmTablaPuntuaciones.addActionListener(new ActionListener() 
		{
				public void actionPerformed(ActionEvent arg0) 
				{
					TablaPuntos tp = new TablaPuntos(_this);
					tp.setVisible(true);
				}
		});
		
		JMenuItem mntmRankingSobrepasos = new JMenuItem("Ranking de Sobrepasos");
		mnVer.add(mntmRankingSobrepasos);
		mntmRankingSobrepasos.addActionListener(new ActionListener() 
		{
				public void actionPerformed(ActionEvent arg0) 
				{
					TablaSobrepasos ts = new TablaSobrepasos(_this);
					ts.setVisible(true);
				}
		});
	}
	
	//Se encarga de agregar al JList de Carreras que se ve en pantalla
	//las carreras que se van agregando al campeonato
	public void actualizarLista()
	{
		DefaultListModel model = new DefaultListModel(); 
		for(int i=0; i<_campeonato.getCarreras().size(); i++)
		{ 
			model.addElement(_campeonato.getCarreras().get(i).getAutodromo()); 
		}
		_listCarreras.setModel(model);
	}
}
