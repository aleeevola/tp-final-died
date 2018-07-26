package frsf.isi.died.tp.modelo.productos;

public enum Relevancia {
ALTA, 
MEDIA, 
BAJA;
	

	public int comparar(Relevancia r) {
		
		if(this == r) return 0;
		else {
			if((this == ALTA) && (r == MEDIA || r == BAJA)) return 1;
			else {
				if( (this == MEDIA) && (r == BAJA) ) return 1;
				else {
					return -1;
				}
			}
		}
		
		
	}
}