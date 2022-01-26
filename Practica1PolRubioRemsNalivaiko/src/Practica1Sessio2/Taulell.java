package Practica1Sessio2;
import java.util.Random;

public class Taulell {
	
	// Atributs
	private boolean[][] taulell_joc;
	public final int FILES;
	public final int COLUMNES;
	int organismes_vius;
	
	public Taulell(int n, int m) {
		FILES=m;
		COLUMNES=n;
		taulell_joc=new boolean[FILES][COLUMNES];
	}
	
	public void treureVida() {
		organismes_vius=0;
		
		for (int y=0; y<taulell_joc.length; y++) {
			for (int x=0; x<taulell_joc[y].length; x++) { // es podria canviar [y] per [0].
				taulell_joc[y][x]=false;
			}
		}
	}
	
	public void posarVida(int N) {
		System.out.println("\nGenerant vides...\n\nFila - Columna");
		for(int i=0; i<N; i++) {
			int x,y;
			do {
				Random rand=new Random();
				y=rand.nextInt(FILES);
				x=rand.nextInt(COLUMNES);
			} while (taulell_joc[y][x]);
			
			taulell_joc[y][x]=true;
			System.out.println("("+y+", "+x+")");
		}
		System.out.println("");
	}
	
	public void mostraTaulell() {
		String m="";
		for (int y = 0; y<taulell_joc.length; y++) {
			for (int x = 0; x<taulell_joc[y].length; x++) {
				if (taulell_joc[y][x]) m+="X"; //"■";
				else m+="-"; //"□";
				m+=" ";
			}
			m+="\n";
		}
		System.out.print(m);
	}
	
	public boolean ferGeneracio() {
		boolean[][] aux=new boolean[FILES][COLUMNES];
		int igualtats=0;
		
		organismes_vius=0;
		
		for (int y=0; y<taulell_joc.length; y++) {
			for (int x=0; x<taulell_joc[0].length; x++) {
				int num_veines=quantesVeines(y,x);			
				if (num_veines==3)
					aux[y][x]=true;
				else if (num_veines==2)
					aux[y][x]=taulell_joc[y][x];
				// per defecte les demes caselles son false i no cal "matar" a cap.

				if(taulell_joc[y][x]==aux[y][x]) igualtats++;
				if(aux[y][x])  organismes_vius++;
			}
		}
		copiar(aux);
		
		return( igualtats!=FILES*COLUMNES );
	}
	
	private int quantesVeines(int fil, int col) {
		int veines=0;
		
		for(int y=fil-1; y<(fil+2); y++) {
			if(y>=0 && y<taulell_joc.length) {
				for(int x=col-1; x<(col+2); x++) {
					if(x>=0 && x<taulell_joc[0].length) {
						if (taulell_joc[y][x] && !(x==col && y==fil)) veines++;
					}
				}
			}
		}

		return veines;
	}
	
	private void copiar(boolean[][] origen) {
		// origen.length==taulell_joc.length && origen[y].length==taulell_joc[y].length ???
		// en cas contrari, cal fer una comprovacio de si tenen la mateixa .length
		
		for (int y = 0; y<origen.length; y++) {
			for (int x = 0; x<origen[y].length; x++) {
				taulell_joc[y][x]=origen[y][x];
			}
		}
	}
	
	
	public int getFiles() {return(FILES);}
	public int getColumnes() {return(COLUMNES);}
	public int getOrganismesVius() {return(organismes_vius);}
}
