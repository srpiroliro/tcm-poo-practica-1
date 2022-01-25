package Practica1Sessio2;
import java.util.Random;

public class Taulell {
	
	// Atributs
	private boolean[][] taulell_joc;
	public final int FILES;
	public final int COLUMNES;
	int organismes_vius;
	
	public Taulell(int n, int m) {
		COLUMNES=n;
		FILES=m;
		taulell_joc=new boolean[m][n];
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
		// Al ser valor de N sempre correcte, sempre hi haura lloc per N vides i per tant no cal comprovacio.
		for(int i=0; i<N; i++) {
			int x,y;
			do {
				Random rand=new Random();
				y=rand.nextInt(FILES);
				x=rand.nextInt(COLUMNES);  // es podria canviar [y] per [0].
			} while (taulell_joc[y][x]);
			
			taulell_joc[y][x]=true;
		}
	}
	
	public void mostraTaulell() {
		String m="";
		for (int y = 0; y<taulell_joc.length; y++) {
			for (int x = 0; x<taulell_joc[y].length; x++) {
				/*
				* Aquests caracters potser han de ser modificats per tal de tenir una correcta
				* impressio per consola si es canvia d'ordinador: - viu: 0x25A0 - mort: 0x25A1
				*/
				if (taulell_joc[y][x])
					m += (char) 0x25A0;
				else
					m += (char) 0x25A1;
				m += " ";
			}
			m += "\n";
		}
		
		System.out.println(m);
	}
	
	public boolean ferGeneracio() {
		// ???: num. de vides x generacio? 
		// this.organismes_vius=0
		// ...
		// this.organismes_vius++
		
		
		boolean[][] aux=new boolean[FILES][COLUMNES];
		int igualtats=0;
		
		for (int y=0; y<taulell_joc.length; y++) {
			for (int x=0; x<taulell_joc[0].length; x++) {
				int num_veines=quantesVeines(y,x);
				
				
				if (!taulell_joc[x][y] && num_veines==3)
					aux[x][y]=true;
				else if (num_veines==2)
					aux[x][y]=taulell_joc[x][y];
				// per defecte les demes caselles son false i no cal "matar" a cap.
					
				
				if(taulell_joc[x][y]==aux[x][y])
					igualtats++;
			}
		}
		
		return( igualtats!=FILES*COLUMNES );
	}
	
	private int quantesVeines(int fil, int col) {
		int veines=0;
		for (int y=fil-1; y<=fil+1; y++) {
			if (y>=0 && y<taulell_joc.length) {
				for (int x=col-1; x<=col+1; x++) {
					if (x>=0 && x<taulell_joc[y].length) {
						if (taulell_joc[y][x] && x!=col && y!=fil) {
							veines++;
						}
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
