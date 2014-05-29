package Interface;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class Panel extends javax.swing.JPanel 
{
	
	public Panel()
	{
		this.setSize(300,340);
	}

	@Override
	public void paintComponent (Graphics g)
	{
		Dimension tamanio = getSize();
		ImageIcon imagenFondo = new ImageIcon(getClass().getResource("/Includes/TC2000.jpg"));
		g.drawImage(imagenFondo.getImage(),0,0,tamanio.width, tamanio.height, null);
		setOpaque(false);
		super.paintComponent(g);
	}
}
