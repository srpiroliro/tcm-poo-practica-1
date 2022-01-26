package Practica1Sessio2;

import Keyboard.*;


public class JocVida {
	
	//resum
	private static void textFinal(int[] vius) {
		boolean dead=false;
		int cnt=0;
		// [0]=index, [1]=valor, [2]=cnt
		int[] max_vida= {0, 0, 0};
		
		while(cnt<vius.length && !dead) {
			System.out.println("Evolucio "+(cnt+1)+": organisme"+( (vius[cnt]!=1) ? "s":"" )+" "+vius[cnt]+" vius.");
			if(vius[cnt]==0) dead=true;
			else if(vius[cnt]>max_vida[1]) {
				max_vida[0]=cnt;
				max_vida[1]=vius[cnt];
				max_vida[2]=1;
			} else if(vius[cnt]==max_vida[1]) max_vida[2]++;
			
			cnt++;
		}
		
		String msg="";
		if(max_vida[2]>1) msg+="Hi ha mes d'una evolucio amb la mateixa vida ("+max_vida[2]+" evolucions amb "+max_vida[1]+" organismes). Com per exemple l'evolucio "+(max_vida[0]+1);
		else msg+="Record: L'evolucio "+(max_vida[0]+1)+" es la que mes vida a tingut amb un total de "+max_vida[1]+" organismes";
		System.out.println(msg+"\n");
	}
	
	private static int demanarIntEntre(int min, int max, String name) {
		int triat;
		do {
			System.out.print(name+" [Entra un num. entre "+min+" i "+max+"]: ");
			triat=Keyboard.readInt();
		} while ( min<triat && triat>max);
		return triat;
	}
	
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
		cnt_partides=0;
		partida=true;
		
		
		while (partida) {
			game_over=false;
			t.treureVida();
			t.posarVida(vides_originals);
			t.mostraTaulell();
			
			System.out.println("");
			
			generacions_totals=demanarIntEntre(1,100,"Generacions");
			current_gen=0;
			int[] resum_vides=new int[generacions_totals];
			
			System.out.println("\n\nComencem a jugar!\n");
			while(current_gen<generacions_totals && !game_over) {
				game_over=!(t.ferGeneracio() && t.getOrganismesVius()>0);
				int num_vides=t.getOrganismesVius();
				
				System.out.println("Genració "+ (current_gen+1) + ":");
				t.mostraTaulell();
				System.out.println("");
								
				resum_vides[current_gen]=num_vides;
				current_gen++;
			}
			
			textFinal(resum_vides);
			
			char[] whitelist={'y','n','Y','N'};
			char seguir=demanarChar(whitelist,"Voleu seguir?");
			
			if(seguir=='n' || seguir=='N') partida=false;
			else cnt_partides++;
		}
		
		System.out.println(cnt_partides + " partid"+( (cnt_partides!=1) ? "es":"a")+" jugad"+( (cnt_partides!=1) ? "es":"a")+"\n\nAdeu.");
	}
}
