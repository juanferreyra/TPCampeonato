package UnitTest;

import static org.junit.Assert.*;
import org.junit.Test;
import Negocio.Carrera;
import Negocio.Fecha;
import Negocio.Piloto;

public class CarreraTest 
{

	@Test
	public void testEquals() 
	{
		Carrera c1 = new Carrera(new Fecha("24","08","1992"), "La Plata");
		Carrera c2 = new Carrera(new Fecha("24","08","1992"), "La Plata");
		Carrera c3 = new Carrera(new Fecha("25","08","1992"), "La Plata");
		Carrera c4 = new Carrera(new Fecha("10","05","2013"), "Posadas");
		Carrera c5 = new Carrera(new Fecha("24","08","1992"), "San Luis");
			
		assertEquals(true, c1.equals(c2));
		assertEquals(true, c2.equals(c1));
		assertEquals(false, c1.equals(c3));
		assertEquals(false, c4.equals(c2));
		assertEquals(false, c5.equals(c2));
	
	}
	
	@Test
	public void testAgregarResultado()
	{
		Carrera c = new Carrera(new Fecha("05","06","2001"), "Bariloche");
		
		Piloto p1 = new Piloto("Francisco", "1");
		Piloto p2 = new Piloto("Maximiliano", "2");
		Piloto p3 = new Piloto("Juan", "3");
		
		c.agregarResultado(p1, 2.1, 1);
		assertEquals(true, c.getResultado().contains(p1));
		assertEquals(false, c.getResultado().contains(p2));
		assertEquals(false, c.getResultado().contains(p3));
		
	}

}
