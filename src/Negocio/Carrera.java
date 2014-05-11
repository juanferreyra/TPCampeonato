package Negocio;

import java.io.Serializable;
import java.util.ArrayList;

public class Carrera implements Serializable
{
	private String fecha;
	private String autodromo;
	protected ArrayList<Piloto> resultado; //Almacena los pilotos con los resultados de la carrera.
	protected boolean estaFinalizada;//Indica si finalizo la carrera
	
	
	////CONSTRUCTOR////
	public Carrera(String fecha, String autodromo)
	{
		this.fecha = fecha;
		this.autodromo = autodromo;
		resultado = new ArrayList<Piloto>();
		estaFinalizada = false;
	}

	
	////GETTERS Y SETTERS////
	public String getFecha()
	{
		return fecha;
	}

	public void setFecha(String fecha) 
	{
		this.fecha = fecha;
	}

	public String getAutodromo() 
	{
		return autodromo;
	}

	public void setAutodromo(String autodromo) 
	{
		this.autodromo = autodromo;
	}

	
	
	////METODOS////
	
	public boolean equals(Carrera otra)
	{
		return (this.fecha.equals(otra.fecha) && this.autodromo.equals(otra.autodromo));
	}
	
	
	//Toma un piloto con su tiempo de clasificacion y
	//lo guarda una copia de el en resultado
	//en el orden de mejor a peor clasificado
	public void agregarClasificacion(Piloto p, double tiempo)
	{
			
			Piloto copia = new Piloto(p.getNombre(), p.getNumero());
			copia.tiempoClasificacion = tiempo;
			resultado.add(copia);
		
	}
	
	//Toma un piloto con su posicion final en la carrera,
	// y lo guarda una copia de el en resultado.
	public void agregarPosicionFinal(Piloto p, int posicion)
	{
		//Primero se fija si el array de resultados ya contiene una copia de el piloto
		//para no guardarlo duplicado.
		if(resultado.contains(p))
		{
			for (int i = 0; i< resultado.size(); i++)
			{
				if(resultado.get(i).equals(p) )
				{
					resultado.get(i).PosicionFinal= posicion;
				}
			}
			
		}
		else
		{	
			Piloto copia = new Piloto(p.getNombre(), p.getNumero());
			copia.PosicionFinal = posicion;
			resultado.add(copia);
		}
		
	}
	
	
	public Piloto mejorClasificado()
	
	{
		return resultado.get(0);
	}
	
	
	public void calcularPuntos() 
	{
		mejorClasificado().puntos += 5;
		for(int i = 0; i < resultado.size(); i++)
		{
			resultado.get(i).puntos = resultado.size()/i+1;
		}
	}
	
	
	public void calcularSobrepasos()
	{
		for (int i = 0; i < resultado.size(); i++) 
		{
			//Si el tiempo de clasificacion menos la posicion final es positiva
			if((i - resultado.get(i).PosicionFinal) > 0 )
			{
				//La cant. de sobrepasos es el tiempo de clasificacion menos la posicion final
				resultado.get(i).setCantidadDeSobrepasos(i - resultado.get(i).PosicionFinal);
			}	
			
		}
	}
	
	public void CarreraCorrida()
	{
		estaFinalizada = true;
	}

}
