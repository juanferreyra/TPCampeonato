package Interface;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Persistencia.Serializacion;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CargaCarrera extends JDialog 
{

	private final JPanel contentPanel = new JPanel();
	private JTextField textAutodromo;
	private JTextField textDia;
	private JTextField textMes;
	private JTextField textAnio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		try 
		{
			CargaCarrera dialog = new CargaCarrera(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CargaCarrera(final MainFrame m) 
	{
		setTitle("Ingrese una Carrera");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Autodromo:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(42, 77, 87, 14);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Fecha:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(42, 147, 81, 14);
		contentPanel.add(lblNewLabel_1);
		
		textAutodromo = new JTextField();
		textAutodromo.setBounds(139, 76, 219, 20);
		contentPanel.add(textAutodromo);
		textAutodromo.setColumns(10);
		
		textDia = new JTextField();
		textDia.setBounds(152, 146, 25, 20);
		contentPanel.add(textDia);
		textDia.setColumns(10);
		
		JLabel lblddmmaaaa = new JLabel("(dd/mm/aaaa)");
		lblddmmaaaa.setBounds(340, 149, 84, 14);
		contentPanel.add(lblddmmaaaa);
		
		textMes = new JTextField();
		textMes.setColumns(10);
		textMes.setBounds(213, 146, 25, 20);
		contentPanel.add(textMes);
		
		textAnio = new JTextField();
		textAnio.setColumns(10);
		textAnio.setBounds(267, 146, 49, 20);
		contentPanel.add(textAnio);
		
		JLabel label = new JLabel("/");
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(183, 149, 17, 14);
		contentPanel.add(label);
		
		JLabel label_1 = new JLabel("/");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_1.setBounds(248, 149, 17, 14);
		contentPanel.add(label_1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Guardar");
				okButton.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent arg0) 
					{
						
						String fecha = textDia.getText() + "/" +  textMes.getText() +"/" +  textAnio.getText();
						
						
						if(faltanDatos(textAutodromo.getText(), textDia.getText(), textMes.getText(), textAnio.getText()))
						{
							JOptionPane.showMessageDialog(null, "Debe ingresar todos los datos solicitados");
						}
						else if(!fechaValida(fecha))
						{
							JOptionPane.showMessageDialog(null, "Debe ingresar una fecha valida");
						}
						//Agrega la carrera al campeonato
						else
						{
							m._campeonato.agregarCarrera(textAutodromo.getText(), fecha);
							m.actualizarLista();
							//Guarda la carrera en el archivo de datos
							Serializacion.guardar(m._campeonato, "dato.txt");
							textDia.setText("");
							textMes.setText("");
							textAnio.setText("");
							textAutodromo.setText("");
							textAutodromo.requestFocus();
							dispose();
						}
							
					}
				});
				okButton.setActionCommand("Guardar");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cerrar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) 
					{
						dispose();
					}
				});
				cancelButton.setActionCommand("Cerrar");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	//Corrobora que la fecha ingresada sea valida
	private static boolean fechaValida(String time) 
	{
		return time.matches("([0-2][1-9]|3[0-1])/(0[1-9]|1[0-2])/[0-9][0-9][0-9][0-9]");
	}
	
	//Indica si hay algun dato de la carrera que no se haya ingresado
	private boolean faltanDatos(String autodromo, String dia, String mes, String anio)
	{
		return (autodromo.length() < 1 || dia.length() < 1
				|| mes.length() < 1 || anio.length() <1);
	}
}
