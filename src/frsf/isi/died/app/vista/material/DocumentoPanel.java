package frsf.isi.died.app.vista.material;

import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import frsf.isi.died.app.controller.DocumentoController;
import frsf.isi.died.app.controller.EditarController;
import frsf.isi.died.app.controller.LibroController;
import frsf.isi.died.tp.estructuras.Nodo;
import frsf.isi.died.tp.modelo.productos.Libro;

public class DocumentoPanel extends JDialog{
	
	private JScrollPane scrollPane;
	private JTable tabla;
	private JButton btnAgregar;
	
	
	private DocumentoTableModel tableModel;

	private DocumentoController controller;
	
	public DocumentoPanel(Frame parent, boolean modal) {
		super(parent, modal);
		this.setLayout(new GridBagLayout());
		this.setSize(400, 700);
		tableModel = new DocumentoTableModel();
	}
	
	public void construir(Nodo nodo) {
		GridBagConstraints gridConst= new GridBagConstraints();
		
		tabla = new JTable(this.tableModel);
		tabla.setFillsViewportHeight(true);
		scrollPane= new JScrollPane(tabla);
		
		gridConst.gridx=0;
		gridConst.gridwidth=5;
		gridConst.gridheight=7;
		gridConst.gridy=0;
		gridConst.weighty=1.0;
		gridConst.weightx=1.0;
		gridConst.fill=GridBagConstraints.BOTH;
		gridConst.anchor=GridBagConstraints.PAGE_START;		
		this.add(scrollPane, gridConst);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener( e ->{
			
		});
		gridConst.gridx=2;
		gridConst.gridy=8;
		gridConst.gridwidth=1;
		gridConst.fill=GridBagConstraints.NONE;
		gridConst.anchor=GridBagConstraints.CENTER;	
		this.add(btnAgregar, gridConst);
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