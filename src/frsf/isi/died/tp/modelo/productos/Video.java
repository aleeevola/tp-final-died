/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frsf.isi.died.tp.modelo.productos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mdominguez
 */
public class Video extends MaterialCapacitacion {

    private Integer duracionEnSegundos;
    private static final Double _VALOR_SEGUNDO = 0.15;

    public Video() {
        super();
    }

    public Video(Integer id,String titulo){
        super(id,titulo);
        this.duracionEnSegundos=0;
    }
    
    public Video(Integer id,String titulo, Double costo) {
        super(id,titulo, costo);
        this.duracionEnSegundos = 0;
    }

    public Video(Integer id,String titulo, Double costo, Integer duracion) {
        super(id,titulo, costo);
        this.duracionEnSegundos = duracion;
    }

    @Override
    public Double precio() {
        return costo + (duracionEnSegundos * _VALOR_SEGUNDO);
    }

    public Integer getDuracionEnSegundos() {
        return duracionEnSegundos;
    }

    public void setDuracionEnSegundos(Integer duracionEnSegundos) {
        this.duracionEnSegundos = duracionEnSegundos;
    }

	@Override
	public Boolean esLibro() {
		return false;
	}

	@Override
	public Boolean esVideo() {
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Video && super.equals(obj) ;
	}
	
	@Override
	public List<String> asCsvRow() {
		List<String> lista = new ArrayList<String>();
		lista.add(this.id+"");
		lista.add("\""+this.titulo.toString()+"\"");
		lista.add(this.costo.toString());
		lista.add(this.duracionEnSegundos.toString());
		return lista;
	}
	

	@Override
	public void loadFromStringRow(List<String> datos) {
		this.id =Integer.valueOf(datos.get(0));
		this.titulo = datos.get(1);
		this.costo =Double.valueOf(datos.get(2));
		this.duracionEnSegundos =Integer.valueOf(datos.get(3));
	}


}