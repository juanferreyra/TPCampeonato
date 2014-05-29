package Negocio;
import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Campeonato implements Serializable
{
	//Almacena todas las carreras del campeonato
	private ArrayList<Carrera> carreras;
	//Almacena los pilotos que participan en el campeonato
	private ArrayList<Piloto> pilotos; 
	
	
	
	////CONSTRUCTOR////
	public Campeonato()
	{
		carreras = new ArrayList<Carrera>();
		pilotos = new ArrayList<Piloto>();
		
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
	public void agregarCarrera(String autodromo, Fecha fecha)
	{
		Carrera c = new Carrera(fecha, autodromo);
		if(carreras.contains(c))
			throw new InvalidParameterException();
		carreras.add(c);
	}
	
	
	//Actualiza los puntos y los sobrepasos de los pilotos luego de
	//correrse una carrera.
	public void actualizarPuntaje(Carrera c)
	{
		for (int i = 0; i < c.pilotosParticipantes();i++) 
		{
			for(int j = 0; j< pilotos.size(); j++ )
			{
				if(pilotos.get(j).equals(c.participante(i)))
				{
					pilotos.get(j).set_puntos(
							pilotos.get(j).get_puntos() + c.participante(i).get_puntos());
					actualizarPromedio(c, i, j);
				}
			}
			
		}
	}

	
	//Actualiza el promedio de sobrepasos de un piloto
	private void actualizarPromedio(Carrera c, int i, int j) 
	{
		pilotos.get(j).setCantidadDeSobrepasos((pilotos.get(j).getCantidadDeSobrepasos() + 
				c.participante(i).getCantidadDeSobrepasos()) / carrerasCorridas() );
	}
	
	
	
	
	
	public int carrerasCorridas()
	{
		int corridas= 0;
		for (int i = 0; i < carreras.size(); i++) 
		{
			if(carreras.get(i).iscarreraFinalizada())
			{
				corridas++;
			}
		}
		return corridas;
	}
	

}
