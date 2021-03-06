/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frsf.isi.died.tp.modelo.productos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author mdominguez
 */
public class Libro extends MaterialCapacitacion {
	private Double precioCompra;
	private Integer paginas;

	public Libro() {
	}

	public Libro(Integer id, String titulo) {
		super(id, titulo);
		this.precioCompra = 0.0;
		this.paginas = 0;
	}

	public Libro(Integer id, String titulo, Double costo, Double precioCompra, Integer paginas, Tema tema) {
		super(id, titulo, costo, tema);
		this.precioCompra = precioCompra;
		this.paginas = paginas;
	}

	public Double getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(Double precioCompra) {
		this.precioCompra = precioCompra;
	}

	public Integer getPaginas() {
		return paginas;
	}

	public void setPaginas(Integer paginas) {
		this.paginas = paginas;
	}

	private Double factorPagina() {
		return 1.0 + (0.03 * (this.paginas / 150));
	}

	@Override
	public Double precio() {
		return this.costo + (this.precioCompra * this.factorPagina());
	}

	@Override
	public Boolean esLibro() {
		return true;
	}

	@Override
	public Boolean esVideo() {
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Libro && super.equals(obj) ;
	}

	@Override
	public List<String> asCsvRow() {
		List<String> lista = new ArrayList<String>();
		lista.add(this.id+"");
		lista.add(this.getTitulo());   ///esto puede joder
		lista.add(this.costo.toString());
		lista.add(this.paginas.toString());
		lista.add(this.precioCompra.toString());
		
		lista.add(this.getCalificacion().toString());
		lista.add(this.getRelevancia().toString());
		lista.add(this.getTema().toString());
		lista.add(this.getFechaPublicacion().toString());
		return lista;
	}

	@Override
	public void loadFromStringRow(List<String> datos) {
		this.id =Integer.valueOf(datos.get(0));
		//this.titulo = datos.get(1);
		this.titulo = String.valueOf(datos.get(1));
		this.costo =Double.valueOf(datos.get(2));
		this.paginas =Integer.valueOf(datos.get(3));
		this.precioCompra =Double.valueOf(datos.get(4));

		this.calificacion=Integer.valueOf(datos.get(5));
		this.relevancia=Relevancia.valueOf(datos.get(6));
		this.tema=Tema.valueOf(datos.get(7));
		this.fechaPublicacion=String.valueOf(datos.get(8));
	}

	
}