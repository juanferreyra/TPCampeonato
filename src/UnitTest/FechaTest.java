package UnitTest;

import static org.junit.Assert.*;
import org.junit.Test;
import Negocio.Fecha;

public class FechaTest 
{
	@Test
	public void fechaValidaAnioBisiesto()
	{
		Fecha fech = new Fecha("29","02","2008");
		Fecha fech2 = new Fecha("29","02","2009");
		
		assertEquals(true,fech.fechaValida());
		assertEquals(false,fech2.fechaValida());
	}
	
	@Test
	public void mesTreintaDias()
	{
		Fecha fec = new Fecha("30","04","2014");
		Fecha fec2 = new Fecha("31","04","2014");
		
		assertEquals(true,fec.fechaValida());
		assertEquals(false,fec2.fechaValida());
	}
	
	@Test
	public void mesTreintaYUnDias()
	{
		Fecha fec = new Fecha("30","05","2014");
		Fecha fec2 = new Fecha("31","05","2014");
		
		assertEquals(true,fec.fechaValida());
		assertEquals(true,fec2.fechaValida());
	}
	
	@Test
	public void fechasRaras()
	{
		Fecha fec = new Fecha("230","435","2014");
		Fecha fec2 = new Fecha("30","02","2030");
		Fecha fec3 = new Fecha("40","05","2214");
		Fecha fec4 = new Fecha("30","13","2014");

		assertEquals(false,fec.fechaValida());
		assertEquals(false,fec2.fechaValida());
		assertEquals(false,fec3.fechaValida());
		assertEquals(false,fec4.fechaValida());
	}

}
