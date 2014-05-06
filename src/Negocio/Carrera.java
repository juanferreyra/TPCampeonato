package Negocio;

public class Carrera 
{
	private String fecha;
	private String autodromo;
	private Resultado resultado;
	
	
	////CONSTRUCTOR////
	public Carrera(String fecha, String autodromo)
	{
		this.fecha = fecha;
		this.autodromo = autodromo;
		resultado = new Resultado();
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


	public Resultado getResultado() 
	{
		return resultado;
	}

	public void setResultado(Resultado resultado) 
	{
		this.resultado = resultado;
	}
	
	
	
	

}
