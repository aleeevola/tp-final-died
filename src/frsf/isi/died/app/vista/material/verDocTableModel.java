package frsf.isi.died.app.vista.material;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import frsf.isi.died.tp.estructuras.Nodo;


public class verDocTableModel  extends AbstractTableModel {

	private List<Nodo> documentos;
	private String[] columnas = {"Documentos"};
	
	
	@Override
	public String getColumnName(int indice) {
		return this.columnas[indice];
	}
	
	public List<Nodo> getLibros() {
		return documentos;
	}

	public void setDeseos(List<Nodo> deseos) {
		this.documentos = deseos;
	}

	@Override
	public int getRowCount() {
		return documentos.size();
	}

	@Override
	public int getColumnCount() {
		return columnas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object valor = null;
		switch (columnIndex) {
		case 0:
			valor = this.documentos.get(rowIndex).getValor();
			break;

		default:
			System.out.println("Indice fuera de rango");
			valor = "S/D";
			break;
		}
		return valor;
	}

}
