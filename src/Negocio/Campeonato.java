package Negocio;
import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Campeonato implements Serializable
{
	private ArrayList<Carrera> carreras; //Almacena todas las carreras del campeonato
	private ArrayList<Piloto> pilotos; //Almacena los pilotos que participan en el campeonato
	private Map<Integer,Integer> campeonatoParalelo; 
	
	
	////CONSTRUCTOR////
	public Campeonato()
	{
		carreras = new ArrayList<Carrera>();
		pilotos = new ArrayList<Piloto>();
		campeonatoParalelo = new TreeMap<Integer, Integer>();
	}


	////GETTERS Y SETTERS////
	public ArrayList<Carrera> getCarreras() 
	{
		return carreras;
	}

	public void setCarreras(ArrayList<Carrera> carreras) 
	{
		this.carreras = carreras;
	}

	public ArrayList<Piloto> getPilotos() 
	{
		return pilotos;
	}

	public void setPilotos(ArrayList<Piloto> pilotos) 
	{
		this.pilotos = pilotos;
	}

	public Map<Integer, Integer> getCampeonatoParalelo() 
	{
		return campeonatoParalelo;
	}

	public void setCampeonatoParalelo(Map<Integer, Integer> campeonatoParalelo) 
	{
		this.campeonatoParalelo = campeonatoParalelo;
	}
	
	
	////METODOS////
	
	//Agrega un piloto a la lista de pilotos
	public void agregarPiloto(String numero, String nombre)
	{
		Piloto p = new Piloto(nombre, numero);
		if(pilotos.contains(p))
			throw new InvalidParameterException();
		pilotos.add(p);
	}
	
	//Agrega una carrera a la lista de carreras
	public void agregarCarrera(String autodromo, String fecha)
	{
		Carrera c = new Carrera(fecha, autodromo);
		if(carreras.contains(c))
			throw new InvalidParameterException();
		carreras.add(c);
	}
	
	
	//Actualiza los puntos de los pilotos luego de
	//correrse una carrera.
	public void actualizarPuntaje(Carrera c)
	{
		for (int i = 0; i < c.resultado.size();i++) 
		{
			for(int j = 0; j< pilotos.size(); j++ )
			{
				if(pilotos.get(j).equals(c.resultado.get(i)))
				{
					pilotos.get(j)._puntos += c.resultado.get(i)._puntos;
				}
			}
			
		}
	}
	
	
	//Calcula el promedio de sobrepasos
	//luego de correrse una carrera
	public void calcularPromedio(Carrera c)
	{
		for (int i = 0; i < c.resultado.size();i++) 
		{
			for(int j = 0; j< pilotos.size(); j++ )
			{
				if(pilotos.get(j).equals(c.resultado.get(i)))
				{
					pilotos.get(j).setCantidadDeSobrepasos((pilotos.get(j).getCantidadDeSobrepasos() + 
							c.resultado.get(i).getCantidadDeSobrepasos()) / carrerasCorridas() );
				}
			}
			
		}
	}
	
	
	public int carrerasCorridas()
	{
		int corridas= 0;
		for (int i = 0; i < carreras.size(); i++) 
		{
			if(carreras.get(i).estaFinalizada == true)
			{
				corridas++;
			}
		}
		return corridas;
	}
	

}
