package frsf.isi.died.app.vista.material;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javax.swing.RootPaneContainer;

import com.sun.prism.paint.Color;

import frsf.isi.died.app.controller.BusquedaController;
import frsf.isi.died.app.controller.DocumentoController;
import frsf.isi.died.app.controller.EditarController;
import frsf.isi.died.app.controller.GrafoController;
import frsf.isi.died.app.controller.LibroController;
import frsf.isi.died.app.controller.VideoController;
import frsf.isi.died.app.vista.grafo.ControlPanel;
import frsf.isi.died.app.vista.grafo.GrafoPanel;
import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import frsf.isi.died.tp.modelo.productos.Tema;

public class BusquedaPanel extends JPanel {

	private int seleccion;
	private JButton btnDocumento;
	private JButton btnDeseo;
	private JButton btnEliminar;
	private JButton btnEditar;

	private JScrollPane scrollPane;
	private JTable tabla;
	private JLabel lblTitulo;
	private JLabel lblCalificacion;
	private JLabel lblTema;
	private JLabel lblFechaPublicacionDesde;
	private JLabel lblFechaPublicacionHasta;
	private JLabel lblOrdenamiento;
	private JTextField txtTitulo;
	private JTextField txtCalificacion;
	private JComboBox comboTema;
	private JTextField txtFechaPublicacionDesde;
	private JTextField txtFechaPublicacionHasta;
	private JComboBox comboOrdenamiento;
	private JButton btnBuscar;
	private JButton btnCancelar;
	private JButton btnAsignarRelaciones;
	private JComboBox nodoInicio;
	private JComboBox nodoFin;

	private BusquedaTableModel tableModel;

	private BusquedaController controller;

	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

	public BusquedaPanel() {
		this.setLayout(new GridBagLayout());
		tableModel = new BusquedaTableModel();
	}

	public void construir() {
		GridBagConstraints gridConst = new GridBagConstraints();

		lblTitulo = new JLabel("Titulo: ");
		gridConst.gridx = 0;
		gridConst.gridy = 0;
		this.add(lblTitulo, gridConst);

		txtTitulo = new JTextField();
		txtTitulo.setColumns(30);
		gridConst.gridx = 1;
		// gridConst.gridwidth=5;
		this.add(txtTitulo, gridConst);

		lblOrdenamiento = new JLabel("Ordenar por: ");
		gridConst.gridx = 4;
		gridConst.gridy = 0;
		this.add(lblOrdenamiento, gridConst);

		String[] Ordenamiento = new String[] { "Título", "Calificación", "Precio", "Fecha de publicación",
				"Relevancia","Page Rank" };

		comboOrdenamiento = new JComboBox(Ordenamiento);
		comboOrdenamiento.addItemListener(null);
		comboOrdenamiento.setSelectedItem(null);
		gridConst.gridx = 5;
		this.add(comboOrdenamiento, gridConst);

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(e -> {
			this.iniciarBusqueda();
			
		});
		// gridConst.gridwidth=1;
		gridConst.weightx = 1.0;
		gridConst.fill =GridBagConstraints.HORIZONTAL;
		gridConst.anchor = GridBagConstraints.LINE_START;
		gridConst.gridx = 8;
		this.add(btnBuscar, gridConst);
		
		gridConst.fill =GridBagConstraints.NONE;
		
		lblCalificacion = new JLabel(" Calificacion: ");
		gridConst.gridx = 0;
		gridConst.gridy = 1;
		gridConst.anchor = GridBagConstraints.CENTER;
		gridConst.weightx = 0.0;
		this.add(lblCalificacion, gridConst);

		txtCalificacion = new JTextField();
		txtCalificacion.setText(null);
		txtCalificacion.setColumns(5);
		gridConst.gridx = 1;
		gridConst.anchor = GridBagConstraints.WEST;
		this.add(txtCalificacion, gridConst);

		lblTema = new JLabel("Tema:");
		gridConst.gridx = 1;
		gridConst.anchor = GridBagConstraints.CENTER;
		this.add(lblTema, gridConst);
		
		comboTema = new JComboBox<Tema>(Tema.values());
		// txtTema.setColumns(5);
		comboTema.setSelectedItem(null);
		
		gridConst.gridx = 1;
		gridConst.anchor = GridBagConstraints.EAST;
		this.add(comboTema, gridConst);

		lblFechaPublicacionDesde = new JLabel("  Fecha desde: ");
		gridConst.gridx = 4;
		gridConst.anchor = GridBagConstraints.EAST;
		this.add(lblFechaPublicacionDesde, gridConst);

		txtFechaPublicacionDesde = new JTextField();
		txtFechaPublicacionDesde.setColumns(7);
		gridConst.gridx = 5;
		gridConst.anchor = GridBagConstraints.WEST;
		txtFechaPublicacionDesde.setText("01/01/2001");
		this.add(txtFechaPublicacionDesde, gridConst);

		lblFechaPublicacionHasta = new JLabel(" hasta: ");
		gridConst.gridx = 5;
		gridConst.anchor = GridBagConstraints.EAST;
		this.add(lblFechaPublicacionHasta, gridConst);

		txtFechaPublicacionHasta = new JTextField();
		txtFechaPublicacionHasta.setColumns(7);
		gridConst.anchor = GridBagConstraints.WEST;
		gridConst.gridx = 6;
		txtFechaPublicacionHasta.setText(formato.format(new Date()));
		this.add(txtFechaPublicacionHasta, gridConst);

		btnCancelar = new JButton("Cancelar");
		gridConst.gridx = 8;
		gridConst.weightx = 1.0;
		gridConst.fill = GridBagConstraints.HORIZONTAL;
		gridConst.anchor = GridBagConstraints.LINE_START;
		this.add(btnCancelar, gridConst);

		tabla = new JTable(this.tableModel);
		tabla.setFillsViewportHeight(true);
		scrollPane = new JScrollPane(tabla);

		gridConst.gridx = 0;
		gridConst.gridwidth = 7;
		gridConst.gridy = 2;
		gridConst.weighty = 1.0;
		gridConst.weightx = 1.0;
		gridConst.fill = GridBagConstraints.BOTH;
		gridConst.anchor = GridBagConstraints.PAGE_START;
		this.add(scrollPane, gridConst);

		btnAsignarRelaciones = new JButton("Asignar relaciones");
		this.btnAsignarRelaciones.addActionListener(e -> {
			JFrame f = new JFrame("Asignar relaciones");
			JPanel panel = new JPanel(new BorderLayout());
		ControlPanel panelCtrl = new ControlPanel();
		GrafoPanel panelGrafo = new GrafoPanel(tableModel.getMateriales().get(seleccion));
		GrafoController controller5 = new GrafoController(panelGrafo, panelCtrl);

		panel.add(panelGrafo , BorderLayout.CENTER);
		panel.add(panelCtrl, BorderLayout.PAGE_START);
		f.setContentPane(panel);
		f.setSize(getMaximumSize());
		f.setVisible(true);		
	});
		gridConst.gridx = 8;
		gridConst.gridy = 6;
		gridConst.anchor = GridBagConstraints.CENTER;
		this.add(btnAsignarRelaciones, gridConst);
		/*
		 * @alee botones editar deseo eliminar crear documento
		 */
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(e -> {
			EditarPanel panelEditar = new EditarPanel(new JFrame(), true);
			EditarController controller2 = new EditarController(panelEditar);
			controller2.crearPanel(tableModel.getMateriales().get(seleccion));
			this.iniciarBusqueda();
		});
		gridConst.gridx = 8;
		gridConst.gridy = 4;
		this.add(btnEditar, gridConst);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(e -> {
			System.out.println(tableModel.getMateriales().get(seleccion).getTitulo());
			int eliminar = JOptionPane.showConfirmDialog(this, "¿Desea eliminar el material: "+ tableModel.getMateriales().get(seleccion).getTitulo()+ "?", "Eliminar un material", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			System.out.println(eliminar);
			if(eliminar == 0) controller.eliminarMaterial(tableModel.getMateriales().get(seleccion));
			this.iniciarBusqueda();
		});
		gridConst.gridx = 8;
		gridConst.gridy = 5;
		this.add(btnEliminar, gridConst);

		btnDocumento = new JButton("Crear Documento");
		btnDocumento.addActionListener(e -> {
			System.out.println(tableModel.getMateriales().get(seleccion).getTitulo());

			DocumentoPanel panelDocumento = new DocumentoPanel(new JFrame(), true);
			DocumentoController controller3 = new DocumentoController(panelDocumento);
			
			controller3.crearPanel(tableModel.getMateriales().get(seleccion).getNodo());
			
			// DocumentoController controller4 = new DocumentoController(panelDocumento);
			// controller4.crearPanel();

		});
		gridConst.gridx = 8;
		gridConst.gridy = 7;
		gridConst.gridwidth = 1;
		gridConst.anchor = GridBagConstraints.CENTER;
		this.add(btnDocumento, gridConst);

		btnDeseo = new JButton("Agregar a deseos");
		btnDeseo.addActionListener(e -> {
			System.out.println(tableModel.getMateriales().get(seleccion).getTitulo());
			controller.agregarDeseo(tableModel.getMateriales().get(seleccion));
			// tableModel.getDocumentos().get(seleccion)
			// controller.iniciarDoc(tableModel.getDocumentos().get(seleccion));
		});
		gridConst.gridx = 8;
		gridConst.gridy = 8;
		gridConst.gridwidth = 1;
		gridConst.anchor = GridBagConstraints.CENTER;
		this.add(btnDeseo, gridConst);

		/*
		 * @alee permite seleccionar fila tabla
		 */
		tabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int r = tabla.rowAtPoint(e.getPoint());
				seleccion = r;
				System.out.print(seleccion);
			}

		});		
	}

	public void iniciarBusqueda() {
		String titulo = null;
		Double calificacion = null;
		String fechaPublicacionDesde = null;
		String fechaPublicacionHasta = null;
		String tema = null;
		String orden = null;

		try {
			if (!txtTitulo.getText().isEmpty())
				titulo = txtTitulo.getText();
			if (!txtCalificacion.getText().isEmpty())
				calificacion = Double.valueOf(txtCalificacion.getText());
			if (comboTema.getSelectedItem() != null)
				tema = comboTema.getSelectedItem().toString();
			if (!txtFechaPublicacionDesde.getText().isEmpty())
				fechaPublicacionDesde = txtFechaPublicacionDesde.getText();
			if (!txtFechaPublicacionHasta.getText().isEmpty())
				fechaPublicacionHasta = txtFechaPublicacionHasta.getText();
			if (comboOrdenamiento.getSelectedItem() != null) {
				orden = comboOrdenamiento.getSelectedItem().toString();
				if (orden == "Page Rank" && tema == null) JOptionPane.showMessageDialog(this, "Por favor seleccione un tema", "Error", JOptionPane.ERROR_MESSAGE);
			}
			controller.buscarMaterial(titulo, calificacion, tema, fechaPublicacionDesde, fechaPublicacionHasta, orden);

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "No se encuentran materiales", "Error", JOptionPane.ERROR_MESSAGE);
		}
	
		comboTema.setSelectedItem(null);
		
	}

	public BusquedaController getController() {
		return controller;
	}

	public void setController(BusquedaController controller) {
		this.controller = controller;
	}

	public void setListaMateriales(List<MaterialCapacitacion> materialesLista, boolean actualizar) {
		this.tableModel.setMateriales(materialesLista);
		if (actualizar)
			this.tableModel.fireTableDataChanged();
	}

}