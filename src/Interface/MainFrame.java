package Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;
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

public class MainFrame implements Serializable
{

	private JFrame frmCampeonatoAutomovilistico;
	protected Campeonato _campeonato;
	private MainFrame _this;
	//private FormAgrClasificacion _agrClasificacion;
	//private FormAgrFinalizacion _agrFinalizacion;

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
	//	_agrClasificacion = new FormAgrClasificacion();
		//_agrFinalizacion = new FormAgrFinalizacion();
		Serializacion.cargar("dato.txt");
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
		frmCampeonatoAutomovilistico.setBounds(100, 100, 378, 334);
		frmCampeonatoAutomovilistico.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCampeonatoAutomovilistico.getContentPane().setLayout(null);
		
		JList listCarreras = new JList();
		listCarreras.setBounds(276, 23, 96, 262);
		frmCampeonatoAutomovilistico.getContentPane().add(listCarreras);
		
		
		
		JLabel lblCarreras = new JLabel("Carreras");
		lblCarreras.setHorizontalAlignment(SwingConstants.CENTER);
		lblCarreras.setEnabled(false);
		lblCarreras.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCarreras.setBounds(276, -2, 86, 25);
		frmCampeonatoAutomovilistico.getContentPane().add(lblCarreras);
		
		JButton btnFinalizacion = new JButton("Finalizacion");
		btnFinalizacion.setBounds(147, 112, 119, 23);
		frmCampeonatoAutomovilistico.getContentPane().add(btnFinalizacion);
		btnFinalizacion.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
			//	_agrFinalizacion.setVisible(true);
				//TODO:deberia hacer que ingrese los resultados finales de la carrera
			}
		});
		
		JButton btnClasificacion = new JButton("Clasificacion");
		btnClasificacion.setBounds(10, 112, 124, 23);
		frmCampeonatoAutomovilistico.getContentPane().add(btnClasificacion);
		btnClasificacion.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
			//	_agrClasificacion.setVisible(true);
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
		
		JMenuItem mntmTablaPuntuaciones = new JMenuItem("Tabla de puntuaciones");
		mnVer.add(mntmTablaPuntuaciones);
		
		JMenuItem mntmRankingSobrepasos = new JMenuItem("Ranking por sobrepasos");
		mnVer.add(mntmRankingSobrepasos);
	}
}
