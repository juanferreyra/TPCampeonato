package Persistencia;

import java.awt.List;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.TreeMap;

import Negocio.Campeonato;



public class Serializacion 
{
	//Recibe una objeto Campeonato y un archivo para guardar los datos 
	//de dicho objeto en dicho archivo
	public static void guardar(Campeonato campeonato, String archivo)
	{
		
		try 
		{
			FileOutputStream fos = new FileOutputStream(archivo);
			ObjectOutputStream out = new ObjectOutputStream(fos);
			
			out.writeObject(campeonato);
			
			out.close();
		}
		catch (Exception e) 
		{	
			e.printStackTrace();
		}
			
	}
	
	//Extrae los datos que tiene el archivo pasado como parametro,
	//y los devuelve como una objeto Campeonato
	public static Campeonato cargar(String archivo)
	{
		try
		{
			FileInputStream fis = new FileInputStream(archivo);
			ObjectInputStream ois = new ObjectInputStream(fis);	
			
			return (Campeonato)ois.readObject();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static void main(String[] args) 
	{
		
		Campeonato c1 = new Campeonato();
		
		c1.agregarPiloto(1, "Fran");
		c1.agregarPiloto(2, "Juan");
		c1.agregarPiloto(3, "Santi");
		c1.agregarCarrera("San Miguel", "10");
		c1.agregarCarrera("Polvorines", "11");
		c1.agregarCarrera("Malvinas Arg.", "12");
		
		
		guardar(c1, "dato.txt");
		Campeonato a = cargar("dato.txt");
		
		for (int i = 0; i < a.getPilotos().size(); i++) 
		{
			System.out.println(a.getPilotos().get(i).getNombre());
			
		}
		
		
	}
	

}