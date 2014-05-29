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
	public void testAgregarResultadoNuevoPiloto()
	{
		Carrera c = new Carrera(new Fecha("05","06","2001"), "Bariloche");
		
		Piloto p1 = new Piloto("Francisco", "1");
		Piloto p2 = new Piloto("Maximiliano", "2");
		Piloto p3 = new Piloto("Juan", "3");
		
		c.agregarResultado(p1, 2.1, 1);
		
		assertTrue(c.getResultado().contains(p1));
		
		assertEquals(1, c.getResultado().get(0).getPosicionFinal(), 0);
		
		assertFalse(c.getResultado().contains(p2));
		
		c.agregarResultado(p2, 8.6, 2);
		
		assertTrue(c.getResultado().contains(p2));
		
		assertEquals(8.6, c.getResultado().get(1).getTiempoClasificacion(), 0.01);
		assertFalse(c.getResultado().contains(p3));
		
	}
	
	@Test
	public void tetAgregarResultadoPilotoExistente()
	{
		Carrera c = new Carrera(new Fecha("05","06","2001"), "Bariloche");
		
		Piloto p1 = new Piloto("Francisco", "1");
		
		c.agregarResultado(p1, 11.5, 1);
		
		assertEquals(11.5, c.getResultado().get(0).getTiempoClasificacion(), 0.01);
		assertEquals(1, c.getResultado().get(0).getPosicionFinal(), 0);
		
		c.agregarResultado(p1, 5.8, 2);
		
		assertEquals(5.8, c.getResultado().get(0).getTiempoClasificacion(), 0.01);
		assertEquals(2, c.getResultado().get(0).getPosicionFinal(), 0);

		c.agregarResultado(p1, 0.1, 2);
		
		assertEquals(0.1, c.getResultado().get(0).getTiempoClasificacion(), 0.01);
		assertEquals(2, c.getResultado().get(0).getPosicionFinal(), 0);

		
	}
	
	
	//@Test
	public void testOrdenarResultados()
	{
		Carrera c = new Carrera(new Fecha("25","10","2010"), "San Juan");
		
		Piloto p1 = new Piloto("Jorge", "125");
		Piloto p2 = new Piloto("Claudio", "84");
		Piloto p3 = new Piloto("Sergio", "55");
		Piloto p4 = new Piloto("Federico", "47");
		
		c.agregarResultado(p1, 1.2, 4);
		c.agregarResultado(p2, 0.8, 1);
		c.agregarResultado(p3, 21, 2);
		c.agregarResultado(p4, 11.5, 3);
		
		
		assertEquals(p2, c.getResultado().get(0));
		assertEquals(p1, c.getResultado().get(1));
		assertEquals(p4, c.getResultado().get(2));
		assertEquals(p3, c.getResultado().get(3));
	}
	
	
	@Test
	public void testCalcularPuntos()
	{
		
		Carrera c = new Carrera(new Fecha("25","10","2010"), "San Juan");
		
		Piloto p1 = new Piloto("Jorge", "125");
		Piloto p2 = new Piloto("Claudio", "84");
		Piloto p3 = new Piloto("Sergio", "55");
		
		c.agregarResultado(p1, 5.8, 1); /*resultado(0)*/
		c.agregarResultado(p2, 8.6, 2); /*resultado(2)*/
		c.agregarResultado(p3, 22, 3); /*resultado(1)*/
		
		c.calcularPuntos();
		
		
		assertEquals(8, c.getResultado().get(0)/*piloto p1*/.get_puntos(),0);
		assertEquals(1, c.getResultado().get(1)/*piloto p2*/.get_puntos(),0);
		assertEquals(1, c.getResultado().get(2)/*piloto p3*/.get_puntos(),0);
		
		
		Piloto p4 = new Piloto("Marcelo", "12365");
		Piloto p5 = new Piloto("David", "12");
		Piloto p6 = new Piloto("Alejandro", "12354");
		Piloto p7 = new Piloto("Marcos", "4545");
		
		
		c.agregarResultado(p4, 86.99, 7); /*resultado(5)*/
		c.agregarResultado(p5, 42.6, 5); /*resultado(3)*/
		c.agregarResultado(p6, 51, 4); /*resultado(4)*/
		c.agregarResultado(p7, 99, 6); /*resultado(6)*/

		c.calcularPuntos();
		
		
		assertEquals(3, c.getResultado().get(1)/*piloto p2*/.get_puntos(),0);
		assertEquals(1, c.getResultado().get(3)/*piloto p5*/.get_puntos(),0);
		assertEquals(1, c.getResultado().get(4)/*piloto p6*/.get_puntos(),0);
		assertEquals(1, c.getResultado().get(5)/*piloto p4*/.get_puntos(),0);
		assertEquals(1, c.getResultado().get(6)/*piloto p7*/.get_puntos(),0);
		assertEquals(12, c.getResultado().get(0)/*piloto p1*/.get_puntos(),0);

	}
	
	
	
	@Test 
	public void testCalcularSobrepasosClasifOrdenadas()
	{
		/* Con el tiempo de clasificasion 
		agregado en orden creciente*/
		Carrera c = new Carrera(new Fecha("25","10","2010"), "San Juan");
		
		Piloto p1 = new Piloto("Jorge", "125");
		Piloto p2 = new Piloto("Claudio", "84");
		Piloto p3 = new Piloto("Sergio", "55");
		
		c.agregarResultado(p1, 5.4, 1); /*resultado(0)*/
		c.agregarResultado(p2, 10.8, 2); /*resultado(1)*/
		
		c.calcularSobrepasos();
		
		assertEquals(0, c.getResultado().get(0)/*piloto p1*/.getCantidadDeSobrepasos(), 0);
		assertEquals(0, c.getResultado().get(1)/*piloto p2*/.getCantidadDeSobrepasos(), 0);
		
		c.agregarResultado(p1, 5.4 , 3);
		c.agregarResultado(p3, 15.2, 1); /*resultado(2)*/
		
		c.calcularSobrepasos();
		
		assertEquals(2, c.getResultado().get(2)/*piloto p3*/.getCantidadDeSobrepasos(), 0);		
		
	}
	
	
	
	@Test
	public void testCalcularSobrepasosClasifDesordenada()
	{
		/* Con el tiempo de clasificacion agregado de manera desordenada*/
		Carrera c = new Carrera(new Fecha("25","10","2010"), "San Juan");
		
		Piloto p1 = new Piloto("Jorge", "125");
		Piloto p2= new Piloto("Claudio", "84");
		Piloto p3 = new Piloto("Sergio", "55");
		Piloto p4 = new Piloto("Federico", "47");
		
		c.agregarResultado(p1, 25.3, 2); /*resultado(2)*/
		c.agregarResultado(p2, 10.8, 4); /*resultado(1)*/
		c.agregarResultado(p3, 5.3, 1); /* resultado(0)*/
		c.agregarResultado(p4, 30.1, 3); /*resultado(3)*/
		
		c.calcularSobrepasos();

		assertEquals(0, c.getResultado().get(0)/*piloto p3*/.getCantidadDeSobrepasos(), 0);
		assertEquals(0, c.getResultado().get(1)/*piloto p2*/.getCantidadDeSobrepasos(), 0);
		assertEquals(1, c.getResultado().get(2)/*piloto p1*/.getCantidadDeSobrepasos(), 0);
		assertEquals(1, c.getResultado().get(3)/*piloto p4*/.getCantidadDeSobrepasos(), 0);

		
		
	}
}
