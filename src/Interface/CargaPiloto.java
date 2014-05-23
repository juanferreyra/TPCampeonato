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

public class CargaPiloto extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textNombre;
	private JTextField textNumero;
	public String nombre;
	public String numero;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CargaPiloto dialog = new CargaPiloto(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CargaPiloto(final MainFrame m) {
		setTitle("Ingrese un Piloto");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setBounds(42, 77, 70, 14);
		contentPanel.add(lblNombre);
		
		JLabel lblNumero = new JLabel("Numero:");
		lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumero.setBounds(42, 147, 70, 14);
		contentPanel.add(lblNumero);
		
		textNombre = new JTextField();
		textNombre.setBounds(154, 76, 219, 20);
		contentPanel.add(textNombre);
		textNombre.setColumns(10);
		
		textNumero = new JTextField();
		textNumero.setBounds(154, 146, 219, 20);
		contentPanel.add(textNumero);
		textNumero.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Ok");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) 
					{
						if(faltanDatos(textNombre.getText(), textNumero.getText()))
						{
							JOptionPane.showMessageDialog(null, "Debe ingresar todos los datos solicitados");
						}
						else if(!esNumero(textNumero.getText()))
						{
							JOptionPane.showMessageDialog(null, "El numero de piloto es erroneo");
						}
						//Agrega un piloto al campeonato
						else
						{
							m._campeonato.agregarPiloto(textNumero.getText(), textNombre.getText());
							//Guarda el piloto en el archivo de datos
							Serializacion.guardar(m._campeonato, "dato.txt");
							dispose();
						}
					}
				});
				okButton.setActionCommand("Ok");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) 
					{
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancelar");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	//Indica si hay algun dato del piloto que no se haya ingresado
		private boolean faltanDatos(String nombre, String numero)
		{
			return (nombre.length() < 1 || numero.length() < 1);
		}
		
		
		//Indica si el numero de piloto es valido
		private boolean esNumero(String numero)
		{
			for (int i = 0; i < numero.length(); i++) 
			{
				if(numero.charAt(i) < 48 || numero.charAt(i) > 57 )
					return false;
			}
			
			return true;
		}
}
