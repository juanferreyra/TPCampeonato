package Negocio;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Fecha implements Serializable
{
	private Integer dia;
	private Integer mes;
	private Integer anio;
	
	public Fecha(String d, String m, String a)
	{
		dia=Integer.parseInt(d);
		mes=Integer.parseInt(m);
		anio=Integer.parseInt(a);
	}
	
	private Integer diasDelMes(Integer mes, Integer anio)
	{
		if (mes==2)
		{
			if (anio % 4 == 0 && anio % 100 != 0)
				return 29;
			else if (anio % 400 == 0)
				return 29;
			else
				return 28;			
		}
		else
		{
			if(mes%2!=0)
			{
				if(mes>=1 && mes<7)
				{
					return 31;
				}
				else
				{
					return 30;
				}		
			}
			else
			{
				if(mes>=1 && mes<7)
				{
					return 30;
				}
				else 
				{
					return 31;
				}
			}
		}
	}
	
	public boolean fechaValida()
	{
		if((anio>0)&& mes>=1 && mes<=12)
		{
			if((diasDelMes(mes,anio)>=dia && 1<=dia))
			{
				return true;
			}
			else
				return false;
	   }
		else
			return false;
	}
	
	public String toString()
	{
		return getDia()+"/"+getMes()+"/"+getAnio();
	}
	
	int getDia()
	{
		return  dia;	
	}
	
	int getMes()
	{
		return mes;
	}
	
	int getAnio()
	{
		return anio;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (!(obj instanceof Fecha))
			return false;
		Fecha other = (Fecha) obj;
		if (anio == null)
		{
			if (other.anio != null)
				return false;
		}
		else if (!anio.equals(other.anio))
			return false;
		if (dia == null) 
		{
			if (other.dia != null)
				return false;
		} 
		else if (!dia.equals(other.dia))
			return false;
		if (mes == null) 
		{
			if (other.mes != null)
				return false;
		} 
		else if (!mes.equals(other.mes))
			return false;
		return true;
	}

}
