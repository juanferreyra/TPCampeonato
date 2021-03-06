package Negocio;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Carrera implements Serializable
{
	private Fecha fecha;
	private String autodromo;
	private ArrayList<Piloto> resultado; //Almacena los pilotos con los resultados de la carrera.
	private boolean carreraFinalizada;//Indica si finalizo la carrera
	
	
	////CONSTRUCTOR////
	public Carrera(Fecha fecha, String autodromo)
	{
		this.fecha = fecha;
		this.autodromo = autodromo;
		resultado = new ArrayList<Piloto>();
		carreraFinalizada = false;
	}

	////GETTERS Y SETTERS////
	public Fecha getFecha()
	{
		return fecha;
	}

	public void setFecha(Fecha fecha) 
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
	
	
	public boolean iscarreraFinalizada() 
	{
		return carreraFinalizada;
	}

	public void setcarreraFinalizada(boolean estaFinalizada) 
	{
		this.carreraFinalizada = estaFinalizada;
	}

	
	public ArrayList<Piloto> getResultado() 
	{
		return resultado;
	}

	public void setResultado(ArrayList<Piloto> resultado) 
	{
		this.resultado = resultado;
	}
	
	////METODOS////
	@Override
	public boolean equals(Object otra)
	{
		if(otra instanceof Carrera)
			return (fecha.equals(((Carrera) otra).getFecha()) 
					&& autodromo.equals(((Carrera) otra).getAutodromo()));
		else
			return false;
	}
	
	public String toString()
	{
		return "AUTODROMO: " + autodromo + "  -  " + "FECHA: " + fecha.toString();
	}
	
	//Toma un piloto con su posicion final en la carrera, y su
	// tiempo de clasificacion y guarda una copia de el en la lista "resultado".
	public void agregarResultado(Piloto p, double clasificacion, int posicion)
	{
		//Se fija si el array de resultados ya contiene una copia de el piloto
		//para no guardarlo duplicado.
		if(resultado.contains(p))
		{
			for (int i = 0; i< resultado.size(); i++)
			{
				if(resultado.get(i).equals(p))
				{
					resultado.get(i).setTiempoClasificacion(clasificacion);
					resultado.get(i).setPosicionFinal(posicion);
				}
			}
		}
		//Copia el piloto el la lista de resultados de la carrera
		else
		{	
			Piloto copia = new Piloto(p.getNombre(), p.getNumero());
			copia.setTiempoClasificacion(clasificacion);
			copia.setPosicionFinal(posicion);			
			resultado.add(copia);
		}
	}
	
	//Ordena el array de resultados teniendo en cuenta
	//el tiempo de clasificacion de menor a mayor tiempo
	private void ordenarResultados()
	{
		for (int i = resultado.size()-1; i > 0; i--) 
		{
			for (int j = 0; j < i; j++) 
			{
				if(resultado.get(j).getTiempoClasificacion() > resultado.get(j+1).getTiempoClasificacion())
				{
					Piloto aux = resultado.get(j);
					resultado.set(j, resultado.get(j+1));
					resultado.set(j+1 ,aux);
				}
			}
		}
	}
	
	
	public Piloto mejorClasificado()
	{
		return resultado.get(0);
	}
	
	//Calcula los puntos que cada piloto obtiene al finalizar la carrera
	//dependiendo de la posicion en la que finalizo 
	public void calcularPuntos() 
	{
		//Ordena los resultados para conocer como clasificaron
		ordenarResultados();
		
		for(int i = 0; i < resultado.size(); i++)
		{
			int posicionFinal = resultado.get(i).getPosicionFinal();
			resultado.get(i).set_puntos(resultado.size()/(posicionFinal));
		}
		
		//Al piloto que clasifico primero se le otorgan 5 puntos adicionales
		mejorClasificado().set_puntos(mejorClasificado().get_puntos() + 5);
	}
	
	//Calcula los sobrepasos de cada piloto que participo en la carrera
	public void calcularSobrepasos()
	{
		ordenarResultados();
		for (int i = 0; i < resultado.size(); i++) 
		{
			//Si el tiempo de clasificacion menos la posicion final es positiva
			if(((i + 1) - resultado.get(i).getPosicionFinal()) > 0 )
			{
				//La cant. de sobrepasos es el tiempo de clasificacion menos la posicion final
				resultado.get(i).setCantidadDeSobrepasos((i+1) - resultado.get(i).getPosicionFinal());
			}
		}
	}
	
	//Devuelve la cantidad de pilotos que participaron de la carrera
	public int pilotosParticipantes()
	{
		return resultado.size();
	}
	
	//Recibe in indice y devuelve el piloto que se
	//encuentra en dicho indice en el arreglo de pilotos
	public Piloto participante(int indice)
	{
		return resultado.get(indice);
	}
	
	
}
