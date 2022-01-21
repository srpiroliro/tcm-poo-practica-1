// test test
import Keyboard.*;
import java.util.Random;



public class Practica1 {

	private static void generecio(boolean joc[][], boolean aux[][]) {
		for (int x = 0; x < joc.length; x++) {
			for (int y = 0; y < joc[0].length; y++) {
				n = numVeins(joc, {joc.length, joc[0].length}, {x,y});
				System.out.print(n + " ");{

				if (!joc[x][y] && n == 3)
					aux[x][y] = true;
				else if (joc[x][y] && (n < 2 || n > 3))
					aux[x][y] = false;
				else
					aux[x][y] = joc[x][y];
				if (aux[x][y])
					v++;
			}
			System.out.println();
		}
		vius[i] = v;
		if(v==0) {e = false; i = g;}
		v = 0;
		System.out.println();
	}
	
	public static void main(String[] args) {
		int j, k, n, v = 0;
		boolean f = false, e = true;
		Random random = new Random();

		int d = intSense("Indica l'amplada del taulell", 5, 15);
		int h = intSense("Indica l'alçada del taulell", 5, 15);

		boolean[][] joc = totFalse(d, h);
		

		int m = intSense("Indica quants organismes vius hi vols posar", 1, d * d);

		for (int i = 0; i < m; i++) {
			do {
				j = random.nextInt(d);
				k = random.nextInt(d);
			} while (joc[j][k]);
			joc[j][k] = true;
			System.out.println("Organisme a la posicio: " + j + "," + k);
		}
		System.out.println("Inicialment");
		System.out.println("***********");
		System.out.println(mapa(joc));

		int g = intSense("Indica quantes generacions vols", 1, 10);
		int[] vius = new int[g];

		for (int i = 0; i < g; i++) {
			
<<<<<<< HEAD
			boolean[][] aux = totFalse(d);
=======
			boolean[][] aux = totFalse(d, h);
>>>>>>> 361787776e473bd90158d2fd0bcf96838fad9190
			
			System.out.println("Evolucio: " + (i + 1));
			System.out.println("***********");

			generacio();
			
			
			joc = aux;
			System.out.println(mapa(joc));
		}

		if (e) {
			v = 0;
			n = 0;
			for (int i = 0; i < g; i++) {
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
									+ v + "organismes. Una d'elles ï¿½s la" + (n+1));
			else
				System.out.println("L'evolucio " + (n+1) + " es la que mes vida a tingut,"
									+ " amb un total de " + v + " organismes");
		}
		else System.out.println("La vida s'ha acabat");

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

	private static int numVeins(boolean[][] joc, int d, int x, int y) {
		int n = 0;

		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (x + i < 0 || x + i > d - 1 || y + j < 0 || y + j > d - 1)
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
