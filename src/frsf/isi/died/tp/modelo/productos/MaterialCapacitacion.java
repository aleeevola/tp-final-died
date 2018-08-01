/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frsf.isi.died.tp.modelo.productos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import frsf.isi.died.app.dao.util.CsvRecord;
import frsf.isi.died.tp.estructuras.Nodo;
import frsf.isi.died.tp.estructuras.TipoDeDato;
import frsf.isi.died.tp.util.Ordenable;

/**
 * Representa de manera abstracta los materiales de capacitación
 * 
 * @author mdominguez
 */
public abstract class MaterialCapacitacion implements Ordenable,Comparable<MaterialCapacitacion>, CsvRecord{
	
	
	protected Integer id;
	protected String titulo;
	protected Double costo;
	protected Integer calificacion;
	protected Relevancia relevancia;
	protected String fechaPublicacion;
	protected Tema tema;
	protected Double pageRank;
	
	public String valor;
	
	public Nodo nodo=new Nodo(valor,TipoDeDato.TITULO);
	
	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		
	public Nodo getNodo() {
		valor=this.getTitulo();
		nodo.setValor(valor);
		return nodo;
	}

	
	public Integer getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Integer calificacion) {
		this.calificacion = calificacion;
	}
	
	public String getFechaPublicacion() {
	return fechaPublicacion;
	}
	
	public Tema getTema() {
	return tema;
	}
	
	public void setTema(Tema tema) {
		this.tema = tema;
	}

	
public void setFechaPublicacion(String fechaPublicacion) {
	this.fechaPublicacion = fechaPublicacion;
}


	public Relevancia getRelevancia() {
	return relevancia;
}

public void setRelevancia(Relevancia relevancia) {
	this.relevancia = relevancia;
}
	
	
	/**
	 * Constructor por defecto
	 */
	public MaterialCapacitacion() {
		this(0,"en desarrollo",0.0,null);
	}

	/**
	 * Constructor que recibe como argumento un ID y un Titulo
	 * 
	 * @param id
	 * @param titulo
	 */
	public MaterialCapacitacion(Integer id, String titulo) {
		this(id,titulo,0.0,null);
	}

	/**
	 * Constructor que recibe como argumento un ID y un costo
	 * 
	 * @param id
	 * @param titulo
	 * @param tema 
	 */
	public MaterialCapacitacion(Integer id,String titulo, Double costo, Tema tema) {
		this.id =id;
		this.titulo = titulo;
		this.costo = costo;
		this.fechaPublicacion= formato.format(new Date());
		
		this.calificacion=0;
		this.relevancia=Relevancia.BAJA;
		this.tema=tema;
		this.pageRank=1.0;

	}

	public Integer comparaFecha(String fecha) {
		int anioValue = this.getFechaPublicacion().charAt(6)*1000 + this.getFechaPublicacion().charAt(7)*100 + this.getFechaPublicacion().charAt(8)*10 +this.getFechaPublicacion().charAt(9);
		int mesValue = this.getFechaPublicacion().charAt(3)*10 + this.getFechaPublicacion().charAt(4);
		int diaValue = this.getFechaPublicacion().charAt(0)*10 + this.getFechaPublicacion().charAt(1);
		int otroAnio = fecha.charAt(6)*1000 + fecha.charAt(7)*100 + fecha.charAt(8)*10 + fecha.charAt(9);
		int otroMes = fecha.charAt(3)*10 + fecha.charAt(4);
		int otroDia = fecha.charAt(0)*10 + fecha.charAt(1);
		
		
		if(anioValue>otroAnio) {
			return 1;
		}
		else {
			if(anioValue<otroAnio) {
				return -1;
			}
			else {
				if(mesValue>otroMes) {
					return 1;
				}
				else {
					if(mesValue<otroMes) {
						return -1;
					}
					else {
						if(diaValue>otroDia) {
							return 1;
						}
						else {
							if(diaValue<otroDia) {
								return -1;
							}
							else {
								return 0;
								}
						}
					}
				}
			}
		}
		
	}
	
	
	
	public String getTitulo() {
		return titulo;
	}

	public Double getCosto() {
		return costo;
	}

	public Integer comparaPageRank(MaterialCapacitacion mat) {
		if(this.getPageRank()>mat.getPageRank()) return -1;
		else return 1;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}
	

	/**
	 * El precio de un material se define según el tipo del material y toma como
	 * base el costo del mismo
	 * 
	 * @return
	 */
	public abstract Double precio();
	
	/**
	 * Retorna verdadero si es una instancia de libro, falso en caso contrario
	 * @return
	 */
	public abstract Boolean esLibro();

	/**
	 * Retorna verdadero si es una instancia de video, falso en caso contrario
	 * @return
	 */
	public abstract Boolean esVideo();
	

	/**
	 * Retorna un valor numerico que será utilizado para ordenar los elementos
	 * @return
	 */
	@Override
	public final int valor() {
		return this.precio().intValue();
	}

	/**
	 * el método toString imprime el titulo, y el precio de un libro
	 * usando el formato : [Titulo: <titulo> ; Precio: <precio> ]
	 */
	@Override
	public String toString() {
		return "[Titulo " +this.titulo+"; PRECIO: "+this.precio()+"]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MaterialCapacitacion other = (MaterialCapacitacion) obj;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}

	@Override
	public int compareTo(MaterialCapacitacion o) {
		int aux = this.titulo.compareTo(o.titulo);
		if(aux==0) {
			aux = this.precio().compareTo(o.precio());
		}
		return aux;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setPageRank(Double pageRank) {
		this.pageRank = pageRank;
	}
	
	public Double getPageRank() {
		return this.pageRank;
	}
	
	
	
	
}
