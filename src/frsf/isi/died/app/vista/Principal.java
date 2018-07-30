package frsf.isi.died.app.vista;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import frsf.isi.died.app.controller.TiposAcciones;
import frsf.isi.died.app.controller.MenuController;

public class Principal {
	public static void main(String[] args) {
	    javax.swing.SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	          createAndShowGUI();
	        }
	    });
	}

	 private static void createAndShowGUI() {
		 JFrame f = new JFrame();
		 MenuController controller = new MenuController(f);
				    
	        JMenuBar menuBar;
	        JMenu menu;
	        JMenuItem menuItem;

	        menuBar = new JMenuBar();

	        menu = new JMenu("Nuevo");
	        menuBar.add(menu);
	        
	        menuItem = new JMenuItem("Nuevo Libro");
	        menuItem.addActionListener(e -> controller.showView(TiposAcciones.ABM_LIBROS));
	        menu.add(menuItem);

	        menuItem = new JMenuItem("Nuevo Video");
	        menuItem.addActionListener(e->controller.showView(TiposAcciones.ABM_VIDEOS));
	        menu.add(menuItem);
	       /* 
	        menuItem = new JMenuItem("Nuevo Documento");
	        menuItem.addActionListener(e->controller.showView(TiposAcciones.A_DOCUMENTO));
	        menu.add(menuItem);*/
	        
	        menu.addSeparator();
	        menuItem = new JMenuItem("Salir");
	        menuItem.addActionListener(e->System.exit(99));
	        menu.add(menuItem);

	        menuBar.add(menu);
	        
	        menu = new JMenu("Buscar");
	        menuBar.add(menu);
	        
	        menuItem = new JMenuItem("Buscar Material");
	        menuItem.addActionListener(e -> controller.showView(TiposAcciones.BUSCAR_MAT));
	        menu.add(menuItem);
	        menuBar.add(menu);
	        
	        menuItem = new JMenuItem("Buscar Camino");
	        menuItem.addActionListener(e -> controller.showView(TiposAcciones.VER_GRAFO));
	        menu.add(menuItem);
	        menuBar.add(menu);
	        
	        menuItem = new JMenuItem("Buscar Documentos");
	        menuItem.addActionListener(e -> controller.showView(TiposAcciones.VER_DOCUMENTOS));
	        menu.add(menuItem);
	        
	        menu = new JMenu("Ver");
	        menuBar.add(menu);
	        
	        menuItem = new JMenuItem("Lista de deseos");
	        menuItem.addActionListener(e -> controller.showView(TiposAcciones.VER_DESEOS));
	        menu.add(menuItem);
	        
	        
	        
	       
	        
	        f.setJMenuBar(menuBar);
	        
	        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        f.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
	        f.pack();
	        f.setVisible(true);
	    }

}
