package Persistencia;

import java.awt.List;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;



public class Serializacion 
{
	//Recibe una lista y un archivo para guardar los datos 
	//de dicha lista en dicho archivo
	public static void guardar(ArrayList lista1, String archivo)
	{
		
		try 
		{
			FileOutputStream fos = new FileOutputStream(archivo);
			ObjectOutputStream out = new ObjectOutputStream(fos);
			
			out.writeObject(lista1);
			
			out.close();
		}
		catch (Exception e) 
		{	
			e.printStackTrace();
		}
			
	}
	
	//Extrae los datos que tiene el archivo pasado como parametro,
	//y los devuelve como una objeto ArrayList
	public static ArrayList cargar(String archivo)
	{
		try
		{
			FileInputStream fis = new FileInputStream(archivo);
			ObjectInputStream ois = new ObjectInputStream(fis);	
			
			return (ArrayList)ois.readObject();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static void main(String[] args) 
	{
		ArrayList<Integer> l = new ArrayList<Integer>();
		l.add(1);
		l.add(2);
		l.add(4);
		
		guardar(l, "dato.txt");
		ArrayList<Integer> a = cargar("dato.txt");
		
		for(int i = 0; i< a.size(); ++i)
			System.out.println(a.get(i));
	}
	

}
