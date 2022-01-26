package Practica1Sessio2;

import Keyboard.*;


public class JocVida {
	
	//resum
	private static void textFinal(int[] vius) {
		int v = 0, n = 0;
		boolean f = false;
		for (int i = 0; i < vius.length; i++) {
			System.out.print("Evolucio: " + (i+1) + "--> hi ha " + vius[i]);
			if (vius[i] == 1)
				System.out.println(" organisme");
			else
				System.out.println(" organismes");

			if (vius[i] > v) {
				v = vius[i];
				n = i;
				f = false;
			} else if (vius[i] == v)
				f = true;
		}
		System.out.println();
		if (f)
			System.out.println("Hi ha mes d'una evolucio amb la mateixa vida ("
								+ v + "organismes. Una d'elles es la" + (n+1));
		else {
			System.out.print("L'evolucio " + (n+1) + " es la que mes vida a tingut,"
								+ " amb un total de " + v);
			if (v == 1)
				System.out.println(" organisme");
			else
				System.out.println(" organismes");
		}
	}
	
	// legal?
	private static int demanarIntEntre(int min, int max, String name) {
		int triat;
		do {
			System.out.print(name+" [Entra un num. entre "+min+" i "+max+"]: ");
			triat=Keyboard.readInt();
		} while ( min<triat && triat>max);
		return triat;
	}
	
	
	// overkill?
	private static char demanarChar(char[] whitelist, String name) {
		char triat;
		boolean stop=false;
		
		String msg=" [Respostes permeses: ";
		for(int i=0;i<whitelist.length; i++) { 
			msg+="'"+whitelist[i]+"'"; 
			if (i!=whitelist.length-1) msg+=",";
			msg+=" ";
		}
		msg+="]: ";
		
		do {
			System.out.print(name+msg);
			triat=Keyboard.readChar();
			
			int cnt=0;
			while(!stop && cnt<whitelist.length) {
				if(triat==whitelist[cnt]) stop=true;
				else cnt++;
			}
			
		} while ( !stop );
		
		return triat;
	}
	
	
	public static void main(String args[]) {
		Taulell t;
		int files, columnes, vides_originals, cnt_partides, generacions_totals, current_gen;
		boolean partida,game_over;
		
		
		files=demanarIntEntre(1,25, "Files");
		columnes=demanarIntEntre(1,25, "Columnes");
		vides_originals=demanarIntEntre(1,files*columnes,"Vides");
		
		
		t=new Taulell(columnes, files);
		current_gen=0;
		cnt_partides=0;
		partida=true;
		
		
		while (partida) {
			game_over=false;
			t.treureVida();
			t.posarVida(vides_originals);
			t.mostraTaulell();
			System.out.println("fds");
			
			generacions_totals=demanarIntEntre(1,100,"Generacions");
			int[] resum_vides=new int[generacions_totals];
			
			while(current_gen<generacions_totals && !game_over) {
				
				game_over = t.ferGeneracio();
				
				resum_vides[current_gen]=t.getOrganismesVius();
				current_gen++;
				System.out.println("Genració "+ current_gen+1 + ":");
				t.mostraTaulell();
			}
			
			textFinal(resum_vides);
			
			char[] whitelist={'y','n','Y','N'};
			char seguir=demanarChar(whitelist,"Voleu seguir?");
			
			if(seguir=='n' || seguir=='N') 
				partida=false;
			else 
				cnt_partides++;
		}
		
		System.out.print("Has jugat un total de " + cnt_partides);
		if (cnt_partides == 1)
			System.out.println(" partida");
		else
			System.out.println(" partides");
		
	}
}
