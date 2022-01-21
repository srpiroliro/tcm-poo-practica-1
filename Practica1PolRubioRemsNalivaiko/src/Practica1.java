// test test
import Keyboard.*;
import java.util.Random;

public class Practica1 {

	private static boolean generacio(boolean joc[][], boolean aux[][], int[] vius, int i) {
		int n, v = 0, j = 0;
	
		for (int x = 0; x < joc.length; x++) {
			for (int y = 0; y < joc[0].length; y++) {
				n = numVeins(joc,x,y);
				System.out.print(n + " ");

				if (!joc[x][y] && n == 3)
					aux[x][y] = true;
				else if (joc[x][y] && (n < 2 || n > 3))
					aux[x][y] = false;
				else
					aux[x][y] = joc[x][y];
				if (aux[x][y])
					v++;
				if(joc[x][y] == aux[x][y])
					j++;
			}
			System.out.println();
		}
		vius[i] = v;
		if(v==0 || joc.length*joc[0].length == j) {return true;}
		v = 0;
		return false;
	}
	
	public static void main(String[] args) {
		Random random = new Random();

		int d = intSense("Indica l'amplada del taulell", 5, 15);
		int h = intSense("Indica l'alcada del taulell", 5, 15);
		boolean[][] joc = totFalse(d, h);
		

		int m = intSense("Indica quants organismes vius hi vols posar", 1, d*h);

		int x,y=0;
		for (int i = 0; i < m; i++) {
			do {
				x = random.nextInt(d);
				y = random.nextInt(h);
			} while (joc[x][y]);
			joc[x][y] = true;
			System.out.println("Organisme a la posicio: " + x + "," + y);
		}
		System.out.println("Inicialment");
		System.out.println("***********");
		System.out.println(mapa(joc));

		int g = intSense("Indica quantes generacions vols", 1, 10);
		int[] vius = new int[g];

		
		int num_partides = 0;
		boolean partida = true;
		
		while(partida) {
			n++;
			int current_gen=0;
			boolean seguir=true;
			while (current_gen<g && seguir) {
				boolean[][] aux = totFalse(d, h);
				System.out.println("Evolucio: " + (current_gen + 1));
				System.out.println("***********");
	
				seguir = generacio(joc, aux, vius, current_gen);
			}
			textFinal(vius);
			
			char triat=' ';
			System.out.print("Voleu seguir jungant? [y/n]: ");
			do {
				triat=Keyboard.readChar();
			} while (triat!='y'||triat!='n');
			
			seguir=(triat=='y');
			if (seguir)  num_partides+=1;
		}
		System.out.print("Has jugat un total de " + num_partides);
		if (num_partides == 1)
			System.out.println(" partida");
		else
			System.out.println(" partides");

	}

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

	private static int intSense(String pregunta, int m, int M) {
		int r;
		do {
			System.out.println(pregunta + " [" + m + "," + M + "]");
			r = Keyboard.readInt();
		} while (r < m || r > M);
		return r;
	}

	private static boolean[][] totFalse(int d, int h) {
		boolean[][] a = new boolean[d][h];

		for (int i = 0; i < d; i++) {
			for (int j = 0; j < h; j++)
				a[i][j] = false;
		}
		return a;
	}

	private static String mapa(boolean[][] mapa) {
		// visualitza matriu
		String m = "";
		for (int i = 0; i < mapa.length; i++) {
			for (int j = 0; j < mapa[0].length; j++) {
				/*
				 * Aquests caracters potser han de ser modificats per tal de tenir una correcta
				 * impressio per consola si es canvia d'ordinador: - viu: 0x25A0 - mort: 0x25A1
				 */
				if (mapa[i][j])
					m += (char) 0x25A0;
				else
					m += (char) 0x25A1;
				m += " ";
			}
			m += "\n";
		}
		return m;
	}

	private static int numVeins(boolean[][] joc, int x, int y) {
		int n = 0;

		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (x + i < 0 || x + i >= joc.length || y + j < 0 || y + j >= joc[0].length)
					;
				else if (joc[x + i][y + j])
					n++;
			}
		}

		if (joc[x][y])
			n -= 1;

		return n;
	}
	
}
