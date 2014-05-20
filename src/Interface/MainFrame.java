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
import javax.swing.JTextField;

public class MainFrame implements Serializable
{
	//Almacena todos los datos del MainFrame en una variable
	private MainFrame _this; 
	private JFrame _frmCampeonatoAutomovilistico;
	protected Campeonato _campeonato;
	public JList _listCarreras; 
	public Integer _carreraSeleccionada;
	private JTextField _muestraCarreraSeleccionada;
	
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
					window._frmCampeonatoAutomovilistico.setVisible(true);
				} 
				catch (Exception e) 
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
		//Crea o Carga los datos del campeonato previamente guardados
		_campeonato = Serializacion.cargar("dato.txt");
		_listCarreras = new JList();
		_carreraSeleccionada = null;
		//Actualiza la lista de carreras para mostrarlas en JList al abrir el programa
		actualizarLista();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		

		_this = this;
				
		_frmCampeonatoAutomovilistico = new JFrame();
		_frmCampeonatoAutomovilistico.setTitle("Campeonato Automovilistico");
		_frmCampeonatoAutomovilistico.setResizable(false);
		_frmCampeonatoAutomovilistico.setBounds(100, 100, 300, 340);
		_frmCampeonatoAutomovilistico.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_frmCampeonatoAutomovilistico.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 274, 132);
		_frmCampeonatoAutomovilistico.getContentPane().add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane.setViewportView(scrollPane_1);
		
		scrollPane_1.setViewportView(_listCarreras);
		//Agrega evento para que reconozca cuando se hace click sobre una carrera
		_listCarreras.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				if(_listCarreras.getModel().getSize() > 0)
				{
					_carreraSeleccionada = _listCarreras.getSelectedIndex();
					_muestraCarreraSeleccionada.setText(_campeonato.getCarreras().get(_carreraSeleccionada).getAutodromo()+" | "+_campeonato.getCarreras().get(_carreraSeleccionada).getFecha());
					//JOptionPane.showMessageDialog(null, "Seleccionaste el evento "+_listCarreras.getSelectedValue());
				}
			}
		});
		

		JLabel lblCarreras = new JLabel("Carreras");
		lblCarreras.setForeground(new Color(102, 153, 0));
		lblCarreras.setHorizontalAlignment(SwingConstants.CENTER);
		lblCarreras.setEnabled(false);
		lblCarreras.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCarreras.setBounds(96, 11, 96, 25);
		_frmCampeonatoAutomovilistico.getContentPane().add(lblCarreras);
		
		JButton btnResultados = new JButton("Agregar Resultados");
		btnResultados.setForeground(new Color(0, 0, 0));
		btnResultados.setFont(new Font("Tahoma", Font.ITALIC, 12));
		btnResultados.setToolTipText("Permite agregar los resultados de una carrera\r\n");
		btnResultados.setBounds(78, 227, 138, 38);
		_frmCampeonatoAutomovilistico.getContentPane().add(btnResultados);
		btnResultados.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{	//Si no se selecciono ninguna carrera
				//muestra un mensaje avisandolo
				if(_carreraSeleccionada==null)
				{
					//Muestra un mensaje
					JOptionPane.showMessageDialog(null, "Primero debe seleccionar una carrera de la lista");
				}
				else
				{	//Si no hay ningun piloto cargado
					//muestra un mensaje avisandolo
					if(_campeonato.getPilotos().size() == 0)
					{
						JOptionPane.showMessageDialog(null, "No hay ningun Piloto cargado");
					}
					else
					{
						FormResultados r = new FormResultados(_this);
						r.setVisible(true);
					}
				}
			}
		});
		
		JMenuBar menuBar = new JMenuBar();
		_frmCampeonatoAutomovilistico.setJMenuBar(menuBar);
		
		//apartado para ingresar pilotos o carrera
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
		
		JMenuItem mntmEvento = new JMenuItem("Carrera");
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
		
		//apartado para ver tablas de puntos
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
		
		JMenuItem mntmRankingSobrepasos = new JMenuItem("Tabla de Sobrepasos");
		mnVer.add(mntmRankingSobrepasos);
		mntmRankingSobrepasos.addActionListener(new ActionListener() 
		{
				public void actionPerformed(ActionEvent arg0) 
				{
					TablaSobrepasos ts = new TablaSobrepasos(_this);
					ts.setVisible(true);
				}
		});

		
		JLabel lblCarreraSeleccionada = new JLabel("Carrera seleccionada:");
		lblCarreraSeleccionada.setBounds(10, 179, 108, 25);
		
		_frmCampeonatoAutomovilistico.getContentPane().add(lblCarreraSeleccionada);
		_muestraCarreraSeleccionada = new JTextField();
		_muestraCarreraSeleccionada.setToolTipText("Sobre esta carrera se puede agregar los resultados");
		_muestraCarreraSeleccionada.setEditable(false);
		_muestraCarreraSeleccionada.setBounds(119, 181, 165, 20);
		_frmCampeonatoAutomovilistico.getContentPane().add(_muestraCarreraSeleccionada);
		_muestraCarreraSeleccionada.setColumns(10);
		
	}
	
	//Se encarga de agregar al JList de Carreras que se ve en pantalla
	//las carreras que se van agregando al campeonato
	public void actualizarLista()
	{
		DefaultListModel model = new DefaultListModel(); 
		for(int i=0; i<_campeonato.getCarreras().size(); i++)
		{ 
			model.add(i,_campeonato.getCarreras().get(i).toString()); 
		}
		_listCarreras.setModel(model);
	}
}
