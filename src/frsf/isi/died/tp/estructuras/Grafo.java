/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frsf.isi.died.tp.estructuras;

/**
 *
 * @author mdominguez
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

public class Grafo<T> {

	protected List<Arista<T>> aristas;
	protected List<Vertice<T>> vertices;

	/**
	 * 
	 */
	public Grafo(){
		this.aristas = new ArrayList<Arista<T>>();
		this.vertices = new ArrayList<Vertice<T>>();
	}

	/**
	 * @param nodo
	 */
	public void addNodo(T nodo){
		this.addNodo(new Vertice<T>(nodo));
	}
	
	/**
	 * @param nodo
	 */
	public void addNodo(Vertice<T> nodo){
		this.vertices.add(nodo);
	}
	

	public void deletNodo(int i){
		this.vertices.remove(i);
	}

	
	public List<Vertice<T>> getVertices() {
		return vertices;
	}

	/**
	 * @param n1
	 * @param n2
	 */
	public void conectar(T n1,T n2){
		this.conectar(getNodo(n1), getNodo(n2), 0.0);
	}

        /**
	 * @param nodo1
	 * @param nodo2
	 */
	public Arista<T> conectar(Vertice<T> nodo1,Vertice<T> nodo2){
            Arista<T> arista = new Arista<T>(nodo1,nodo2,0.0);
            this.aristas.add(arista);
            return arista;
	}
        
	/**
	 * @param n1
	 * @param n2
	 * @param valor
	 */
	public void conectar(T n1,T n2,Number valor){
		this.conectar(getNodo(n1), getNodo(n2), valor);
	}

	/**
	 * @param nodo1
	 * @param nodo2
	 * @param valor
	 */
	public void conectar(Vertice<T> nodo1,Vertice<T> nodo2,Number valor){
		this.aristas.add(new Arista<T>(nodo1,nodo2,valor));
	}
	
	/**
	 * @param valor
	 * @return
	 */
	public Vertice<T> getNodo(T valor){
		return this.vertices.get(this.vertices.indexOf(new Vertice<T>(valor)));
	}

	/**
	 * @param valor
	 * @return
	 */
	public List<T> getAdyacentes(T valor){ 
		Vertice<T> unNodo = this.getNodo(valor);
		List<T> salida = new ArrayList<T>();
		for(Arista<T> enlace : this.aristas){
			if( enlace.getInicio().equals(unNodo)){
				salida.add(enlace.getFin().getValor());
			}
		}
		return salida;
	}
	

	/**
	 * @param unNodo
	 * @return
	 */
	protected List<Vertice<T>> getAdyacentes(Vertice<T> unNodo){ 
		List<Vertice<T>> salida = new ArrayList<Vertice<T>>();
		for(Arista<T> enlace : this.aristas){
			if( enlace.getInicio().equals(unNodo)){
				salida.add(enlace.getFin());
			}
		}
		return salida;
	}
                
	/**
	 * 
	 */
	public void imprimirAristas(){
		System.out.println(this.aristas.toString());
	}

	/**
	 * @param vertice
	 * @return
	 */
	public Integer gradoEntrada(T v){
		Vertice<T> vertice = this.getNodo(v);
		Integer res =0;
		for(Arista<T> arista : this.aristas){
			if(arista.getFin().equals(vertice)) ++res;
		}
		return res;
	}

	/**
	 * @param vertice
	 * @return
	 */
	public Integer gradoSalida(T v){
		Vertice<T> vertice = this.getNodo(v);
		Integer res =0;
		for(Arista<T> arista : this.aristas){
			if(arista.getInicio().equals(vertice)) ++res;
		}
		return res;
	}
        
	public Integer gradoSalida(Vertice<T> v){
		Integer res =0;
		for(Arista<T> arista : this.aristas){
			if(arista.getInicio().equals(v)) ++res;
		}
		return res;
	}
	/**
	 * @param v1
	 * @param v2
	 * @return
	 */
	public boolean esAdyacente(Vertice<T> v1,Vertice<T> v2){
            List<Vertice<T>> ady = this.getAdyacentes(v1);
            for(Vertice<T> unAdy : ady){
                if(unAdy.equals(v2)) return true;
            }
            return false;
    }
        
    public Boolean esVacio(){
    	if(this.vertices!= null && !this.vertices.isEmpty()) return false;
    	return true;
    }
    
    public List<T> listaVertices(){
    	List<T> lista = new ArrayList<>();
    	this.vertices.forEach(v -> lista.add(v.getValor()));
    	return lista;
    }

    public T primerVerticeGradoK(T v,Integer gradoK) {
    	HashSet<Vertice> visitados= new HashSet<>();
    	Vertice<T> vertice = this.getNodo(v);
		Queue<Vertice<T>> visitar= new LinkedList<>();
		visitar.add(vertice);
		while(!visitar.isEmpty()) {
			Vertice<T> actual= visitar.poll();
			visitados.add(actual);
			System.out.println("actual.getValor(): "+actual.getValor()+"  | gradoSalida(actual.getValor()):"+gradoSalida(actual.getValor()));
			if(gradoSalida(actual.getValor()).equals(gradoK)) return actual.getValor();
			List<Vertice<T>> adyacentes = this.getAdyacentes(actual);
			for(Vertice<T> unAdy:adyacentes) {
				if(!visitados.contains(unAdy)) visitar.add(unAdy);
			}
		}
		return null;
    }


    
    public boolean existeCamino(T v) {
		Vertice<T> vertice = this.getNodo(v);
    	return true;
    }
    
    
    /**
     * @param n1
     * @param n2
     * @param valor
     */
    public List<T> buscarCaminoNSaltos(T n1,T n2,Integer saltos){
		Vertice<T> origen = this.getNodo(n1);
		Vertice<T> destino= this.getNodo(n2);
        return this.buscarCaminoNSaltos(origen, destino, saltos, new HashSet<Vertice>());
         
    }
    private List<T> buscarCaminoNSaltos(Vertice<T> n1,Vertice<T> n2,Integer saltos,HashSet<Vertice> visitados){
        ArrayList<T> resultado = new ArrayList<>();
        if(saltos==0) return new ArrayList<>(); //retorno vacio
        if(esAdyacente(n1, n2)&& saltos ==1) {
            resultado.add(n1.getValor());
            resultado.add(n2.getValor());
            return resultado;
        }else{
            List<Vertice<T>> adyacentes=null;
            if(!visitados.contains(n1)){
                adyacentes = getAdyacentes(n1);
                visitados.add(n1);
                for(Vertice<T> unAdy : adyacentes){
                    List<T> resultado2 = buscarCaminoNSaltos(unAdy,n2, saltos-1,visitados);
                    if(!resultado2.isEmpty()) {
                        resultado.add(n1.getValor());
                        resultado.addAll(resultado2);
                        return resultado; // termino encontre un camino
                    }                    
                }
                visitados.remove(n1);
            }                        
            resultado.clear();
        }
        return resultado;
    }

    public Double calcularPageRank(T v) {
    	Vertice<T> vertice = this.getNodo(v);
    	return calcularPageRank(vertice);
    }
    
    public Double calcularPageRank(Vertice<T> vertice) {
    	double p = 1, d = 0.5;
    	for(Vertice<T> v: this.vertices) {
    		if(!this.buscarCaminoNSaltos(v,vertice,1,new HashSet<Vertice>()).isEmpty())
    				p+= this.calcularPageRank(v)/this.gradoSalida(v);
    	}
    	if (p==1) 
    		return 1-d;
    	
    	return (1-d+d*p);
    }

}

