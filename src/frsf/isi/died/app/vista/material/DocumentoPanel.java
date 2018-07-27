package frsf.isi.died.app.vista.material;

import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import frsf.isi.died.app.controller.DocumentoController;
import frsf.isi.died.app.controller.LibroController;
import frsf.isi.died.tp.estructuras.Nodo;
import frsf.isi.died.tp.modelo.productos.Libro;

public class DocumentoPanel extends JDialog{
	
	private JScrollPane scrollPane;
	private JTable tabla;

	private DocumentoTableModel tableModel;

	private DocumentoController controller;
	
	public DocumentoPanel(Frame parent, boolean modal) {
		super(parent, modal);
		this.setLayout(new GridBagLayout());
		this.setSize(600, 400);
		tableModel = new DocumentoTableModel();
	}
	
	public void construir() {
		GridBagConstraints gridConst= new GridBagConstraints();
		
		tabla = new JTable(this.tableModel);
		tabla.setFillsViewportHeight(true);
		scrollPane= new JScrollPane(tabla);
		
		gridConst.gridx=0;
		gridConst.gridwidth=7;	
		gridConst.gridy=2;
		gridConst.weighty=1.0;
		gridConst.weightx=1.0;
		gridConst.fill=GridBagConstraints.BOTH;
		gridConst.anchor=GridBagConstraints.PAGE_START;		
		this.add(scrollPane, gridConst);
	}

	public DocumentoController getController() {
		return controller;
	}

	public void setController(DocumentoController controller) {
		this.controller = controller;
	}
	
	public void setListaDocumento(List<Nodo> librosLista,boolean actualizar) {
		this.tableModel.setDocumento(librosLista);
		if(actualizar) this.tableModel.fireTableDataChanged();
	}
	}