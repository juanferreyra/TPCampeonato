package Negocio;

public class Piloto 
{
	private String nombre;
	private int numero;
	private int carrerasCorridas;
	private int cantidadDeSobrepasos;
	
	////CONSTRUCTOR////
	public Piloto(String nombre, int numero)
	{
		this.nombre = nombre;
		this.numero = numero;
		carrerasCorridas = 0;
		cantidadDeSobrepasos = 0;
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


	public int getNumero() 
	{
		return numero;
	}

	public void setNumero(int numero) 
	{
		this.numero = numero;
	}


	public int getCarrerasCorridas() 
	{
		return carrerasCorridas;
	}

	public void setCarrerasCorridas(int carrerasCorridas) 
	{
		this.carrerasCorridas = carrerasCorridas;
	}


	public int getCantidadDeSobrepasos() 
	{	
		return cantidadDeSobrepasos;
	}

	public void setCantidadDeSobrepasos(int cantidadDeSobrepasos) 
	{
		this.cantidadDeSobrepasos = cantidadDeSobrepasos;
	}
	
	
	
	
	
}
