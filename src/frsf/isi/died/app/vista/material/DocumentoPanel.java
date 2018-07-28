package frsf.isi.died.app.vista.material;

import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
import frsf.isi.died.tp.estructuras.TipoDeDato;
import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.Tema;

public class DocumentoPanel extends JDialog{
	
	private JScrollPane scrollPane;
	private JTable tabla;
	private JButton btnAgregar;
	private JButton btnTerminar;
	private JLabel lblTipo; private JComboBox cbTipo;
	private JLabel lblTitulo; private JTextField txtTitulo;
	
	private Integer seleccion = 1;
	private DocumentoTableModel tableModel;

	private DocumentoController controller;
	
	public DocumentoPanel(Frame parent, boolean modal) {
		super(parent, modal);
		this.setLayout(new GridBagLayout());
		this.setSize(500, 560);
		tableModel = new DocumentoTableModel();
	}
	
	public void construir() {
		GridBagConstraints gridConst= new GridBagConstraints();
		
		
		lblTipo = new JLabel("Tipo de dato: ");
		gridConst.gridx=0;
		gridConst.gridy=2;
		gridConst.gridheight=1;
		gridConst.gridwidth=1;
		gridConst.weighty=1.0;
		gridConst.weightx=1.0;
		gridConst.fill=GridBagConstraints.NONE;
		gridConst.anchor=GridBagConstraints.SOUTH;
		//gridConst.anchor=GridBagConstraints.CENTER;	
		this.add(lblTipo, gridConst);
		
		cbTipo = new JComboBox(TipoDeDato.values());
		gridConst.gridx=1;
		gridConst.gridheight=1;
		gridConst.gridwidth=1;
		this.add(cbTipo, gridConst);
		
		lblTitulo = new JLabel("Texto: ");
		gridConst.gridx=0;
		gridConst.gridy=3;
		gridConst.gridwidth=1;
		this.add(lblTitulo, gridConst);
		
		txtTitulo = new JTextField();
		txtTitulo.setColumns(30);
		gridConst.gridx=0;
		gridConst.gridy=4;
		gridConst.gridheight=1;
		gridConst.gridwidth=5;
		gridConst.fill = GridBagConstraints.BOTH;
		this.add(txtTitulo, gridConst);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener( e ->{
			String texto = String.valueOf(txtTitulo.getText());
			TipoDeDato tipo = (TipoDeDato) cbTipo.getSelectedItem();
			Nodo nodoNuevo = new Nodo(texto,tipo);
			Nodo nodoPadre = tableModel.getDocumentos().get(seleccion);
			controller.agregarNodo(nodoPadre, nodoNuevo);
			
		});
		gridConst.gridx=3;
		gridConst.gridy=5;
		gridConst.gridheight=1;
		gridConst.gridwidth=1;
		gridConst.fill = GridBagConstraints.NONE;
		this.add(btnAgregar, gridConst);
		
		
		btnTerminar = new JButton("Terminar");
		btnTerminar.addActionListener( e ->{
			this.setVisible(false);
		});
		gridConst.gridx=4;
		gridConst.gridy=5;
		gridConst.gridheight=1;
		gridConst.gridwidth=1;
		gridConst.fill = GridBagConstraints.NONE;
		this.add(btnTerminar, gridConst);
		
		tabla = new JTable(this.tableModel);
		tabla.setFillsViewportHeight(true);
		scrollPane= new JScrollPane(tabla);
		
		gridConst.gridx=0;
		gridConst.gridwidth=5;
		gridConst.gridheight=1;
		gridConst.gridy=0;
		//gridConst.weighty=1.0;
		gridConst.weightx=1.0;
		gridConst.fill=GridBagConstraints.BOTH;
		gridConst.anchor=GridBagConstraints.PAGE_START;		
		this.add(scrollPane, gridConst);
		
		/* @alee
		 * permite seleccionar fila tabla
		 * */
		tabla.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseReleased(MouseEvent e) {
		        int r = tabla.rowAtPoint(e.getPoint());
		        seleccion=r;
		        System.out.print(seleccion);
		        }
		    
		});
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