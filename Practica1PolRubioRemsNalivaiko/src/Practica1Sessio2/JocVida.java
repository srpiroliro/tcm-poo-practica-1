package Practica1Sessio2;

import Keyboard.*;


public class JocVida {
	
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
			
			generacions_totals=demanarIntEntre(1,100,"Generacions");
			int[] resum_vides=new int[generacions_totals];
			
			while(current_gen<generacions_totals && !game_over) {
				
				// ...
				
				resum_vides[current_gen]=t.getOrganismesVius();
				current_gen++;
			}
			
			char[] whitelist={'y','n','Y','N'};
			char seguir=demanarChar(whitelist,"Voleu seguir?");
			/*
			En cas de ser demanarChar() massa, substituir per aixo.
			
			do {
				System.out.println("Voleu fer-ne un altre? [y/n]: ");
				seguir=Keyboard.readChar();
			} while ( !(seguir=='y' || seguir=='Y' || seguir=='n' || seguir=='N') );
			*/
			
			if(seguir=='n' || seguir=='N') partida=false;
			else cnt_partides++;
		}
		
		// resum
	}
}
