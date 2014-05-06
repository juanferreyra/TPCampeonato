package Negocio;
import java.util.Map;
import java.util.TreeMap;

public class Resultado 
{
	private Map <Piloto, Integer> clasificacion;
	private Map <Piloto, Integer> posicionFinal;
	
	////CONSTRUCTOR////
	public Resultado()
	{
		clasificacion = new TreeMap<Piloto, Integer>();
		posicionFinal = new TreeMap<Piloto, Integer>();
	}

	
	////GETTERS Y SETTERS////
	public Map<Piloto, Integer> getClasificacion() 
	{
		return clasificacion;
	}

	public void setClasificacion(Map<Piloto, Integer> clasificacion) 
	{
		this.clasificacion = clasificacion;
	}

	public Map<Piloto, Integer> getPosicionFinal() 
	{
		return posicionFinal;
	}

	public void setPosicionFinal(Map<Piloto, Integer> posicionFinal) 
	{
		this.posicionFinal = posicionFinal;
	}
	
	
	

}
