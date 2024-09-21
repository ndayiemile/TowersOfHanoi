// =============================================================================
/**
 * Print a variety of basic 2D patterns to the console.
 *
 * @author sfkaplan@amherst.edu
 */
public class Towers {
// =============================================================================


    // =========================================================================
    /**
     * The program's entry point.
     *
     * @param args The command-line arguments provided by the user.
     */
    public static void main (String[] args) {

	// If an incorrect number of arguments was provided on the command-line,
	// then print the correct usage and exit.
	if (args.length != 1) {
	    showUsageAndExit();
	}

	// Grab the argument.
	int rings = 0;
	try {
	    rings = Integer.parseInt(args[0]);
	} catch (NumberFormatException e) {
	    showUsageAndExit();
	}
	if (rings < 0) {
	    showUsageAndExit();
	}

	int[][] towers = initialize(rings);
	solve(towers);
    } // main()
    // =========================================================================



    // =========================================================================
    public static void solve (int[][] towers) {

	int numRings = towers[0].length;
	print(towers);
	doSolve(towers, 0, 2, 1, numRings);

    } // solve()
    // =========================================================================



    // =========================================================================
	//To move n disks from A to C
    public static void doSolve (int[][] towers, int from, int to, int other, int n) {
		if(n>0){
			// 1st: move n-1 Ring from A to B
			doSolve(towers, from, other, to, n-1);
			// 2nd: move (Nth/Largest disk/Bottom Ring at A) from A to C
			towers = moveOneRing(towers,from,to);
			// 3rd: move n-1 Ring from B to C
			doSolve(towers, other, to, from, n-1);
		}
	
    } // doSolve()
    // =========================================================================

	//Method to get the top most non-empty position on the tower and its Ring
	public static int[] getTopRingIndex(int[] tower){
		int[] top = {0,0};
		for (int index = tower.length-1; index >= 0; index--) {
			if(tower[index] != 0){
				top[0] = index;
				top[1] = tower[index];
				break;
			}
		}
		return top;
	}

	//Method to moveOneRing from A to C
	public static int[][] moveOneRing(int[][] towers, int from, int to){
		int fromTop = getTopRingIndex(towers[from])[0];
			int toTop[] = getTopRingIndex(towers[to]);
			int toTopNext = toTop[0] == 0 && toTop[1] == 0 ? 0 : toTop[0]+1;
			towers[to][toTopNext] = towers[from][fromTop];
			towers[from][fromTop] = 0; 
			print(towers);
			return towers;
	}
    // =========================================================================
    public static int[][] initialize (int n) {

	int[][] towers = new int[3][n];
	for (int i = 0; i < n; i += 1) {
	    towers[0][i] = n - i;
	}

	return towers;

    } // initialize()
    // =========================================================================



    // =========================================================================
    public static void print (int[][] towers) {

	int rings = towers[0].length;
	for (int row = rings - 1; row >= 0; row -= 1) {
	    for (int tower = 0; tower < 3; tower += 1) {
		printRing(towers[tower][row], rings);
		System.out.print("\t");
	    }
	    System.out.println();
	}
	System.out.println();

    }
    // =========================================================================



    // =========================================================================
    public static void printRing (int size, int rings) {

	int spaces = rings - size;
	printChar(' ', spaces);
	printChar('=', size);
	printChar('|', 1);
	printChar('=', size);
	printChar(' ', spaces);

    }
    // =========================================================================



    // =========================================================================
    public static void printChar (char c, int reps) {

	for (int i = 0; i < reps; i += 1) {
	    System.out.print(c);
	}

    }
    // =========================================================================



    // =========================================================================
    /**
     * Print the correct form for running this program and exit with an error
     * code.
     */
    public static void showUsageAndExit () {

	System.err.println("USAGE: java Towers <number of rings>");
	System.exit(1);
	
    } // showUsageAndExit()
    // =========================================================================



// =============================================================================
} // class Towers
// =============================================================================
