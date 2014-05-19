package Negocio;

import java.io.Serializable;

public class Piloto implements Serializable
{
	private String _nombre;
	private String _numero;
	private int _cantidadDeSobrepasos;
	public Integer _puntos;
	private double _tiempoClasificacion;
	private Integer _posicionFinal;
	
	////CONSTRUCTOR////
	public Piloto(String nombre, String numero)
	{
		_nombre = nombre;
		_numero = numero;
		_puntos = 0;
		_cantidadDeSobrepasos = 0;
		_tiempoClasificacion = 0;
		
	}

	////GETTERS Y SETTERS////
	public String getNombre() 
	{
		return _nombre;
	}

	public String getNumero() 
	{
		return _numero;
	}

	public int getCantidadDeSobrepasos() 
	{	
		return _cantidadDeSobrepasos;
	}
	
	public double getTiempoClasificacion()
	{
		return _tiempoClasificacion;
	}

	public Integer getPosicionFinal() 
	{
		return _posicionFinal;
	}

	public void setNombre(String nombre) 
	{
		_nombre = nombre;
	}
	
	public void setNumero(String numero) 
	{
		_numero = numero;
	}
	
	public void setCantidadDeSobrepasos(int cantidadDeSobrepasos) 
	{
		_cantidadDeSobrepasos = cantidadDeSobrepasos;
	}
	
	public void setTiempoClasificacion(double clasificacion) 
	{
		_tiempoClasificacion = clasificacion;	
	}
	
	public void setPosicionFinal(Integer posicionFinal)
	{
		_posicionFinal = posicionFinal;
	}
	
	////METODOS////
	@Override 
	public boolean equals(Object otro) 
	{
		boolean result = false;
		if(otro instanceof Piloto)
			result = (_nombre.equals(((Piloto) otro).getNombre()) && _numero.equals(((Piloto) otro).getNumero()));
		return result;
	}

	public String toString()
	{
		return "|| Nombre: "+_nombre+" Clasif: "+_tiempoClasificacion+" PosFinal: "+_posicionFinal;
	}
}
