package Negocio;

import java.io.Serializable;

public class Piloto implements Serializable
{
	private String nombre;
	private String numero;
	private int cantidadDeSobrepasos;
	protected int puntos;
	protected double tiempoClasificacion;///*CREO QUE ESTA VARIABLE NO SIRVE PARA NADA*////
	protected Integer PosicionFinal;
	
	////CONSTRUCTOR////
	public Piloto(String nombre, String numero)
	{
		this.nombre = nombre;
		this.numero = numero;
		this.puntos = 0;
		this.cantidadDeSobrepasos = 0;
	}

	////GETTERS Y SETTERS////
	public String getNombre() 
	{
		return nombre;
	}

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	public String getNumero() 
	{
		return numero;
	}

	public void setNumero(String numero) 
	{
		this.numero = numero;
	}

	public int getCantidadDeSobrepasos() 
	{	
		return cantidadDeSobrepasos;
	}

	public void setCantidadDeSobrepasos(int cantidadDeSobrepasos) 
	{
		this.cantidadDeSobrepasos = cantidadDeSobrepasos;
	}
	
	
	////METODOS////
	
	
	public boolean equals(Piloto otro) 
	{
		return (this.nombre.equals(otro.nombre) && this.numero == otro.numero);
	}
	
	
	
	
}
