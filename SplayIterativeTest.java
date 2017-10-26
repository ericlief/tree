
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author mambo
 *
 */
public class SplayIterativeTest {

    public static void main(String[] args) {

	Scanner sc = new Scanner(System.in);
	SplayIterative tree = null;
	final long startTime = System.currentTimeMillis();
	int n = 0;
	String file = "out-10000.csv";
	try (BufferedWriter out = new BufferedWriter(new FileWriter(file))) {
	    System.out.println();

	    while (sc.hasNext()) {
		if (sc.hasNext("#")) {
		    sc.next();
		    tree = new SplayIterative();
		    if (sc.hasNextInt())
			n = sc.nextInt();
		    System.out.println("Starting new tree of size" + n);
		    sc.nextLine();

		    while (sc.hasNext("I") || sc.hasNext("F")) {
			if (sc.hasNext("I")) {
			    sc.next();
			    if (sc.hasNextInt()) {
				int key = sc.nextInt();
				tree.insert(key);
			    }
			} else if (sc.hasNext("F")) {
			    sc.next();
			    if (sc.hasNextInt()) {
				int key = sc.nextInt();
				// System.out.println("for " + key);
				tree.find(key);
			    }
			}
		    }
		    Double aveCumPathLength = tree.getAveCumPathLength();
		    System.out.println("Cumulative path length for " + n + ": " + aveCumPathLength);
		    out.write(n + "," + aveCumPathLength + "\n");
		    final long time = System.currentTimeMillis() - startTime;
		    System.out.println("Time: " + time);
		}
	    }
	    final long time = System.currentTimeMillis() - startTime;
	    System.out.println("Total time: " + time);
	    out.close();
	} catch (IOException e) {
	    System.err.println("Error writing output");
	    e.printStackTrace();

	}
    }
}

// Path pathOut =
// Paths.get(System.getProperty("user.home")).resolve(fileOut);
// try (BufferedWriter out = new BufferedWriter(new FileWriter(file))) {

// BufferedWriter out = Files.newBufferedWriter(pathOut,
// StandardOpenOption.WRITE,
// StandardOpenOption.CREATE);
// out.write(aveCumPathLength);
// out.newLine();

// String fileOut = "out.txt";
// Path pathOut = Paths.get(System.getProperty("user.home")).resolve(fileOut);
// try (BufferedWriter out = Files.newBufferedWriter(pathOut,
// StandardOpenOption.WRITE,
// StandardOpenOption.CREATE)) {
//
// /*
// * int j = 0; for (int n = 1000; n < 1000000; n += 3000) {
// * out.write("%d\t%d\n", n, paths[j]); j++;
// */
// int j = 0;
// for (int n = 1000; n <= 1000000; n += 3000) {
// out.write(n + "\t");
// out.write(Double.toString(totalPathLength[j]));
// out.newLine();
// j++;
//
// }
// } catch (IOException e) {
// System.err.println("Error writing to file");
// e.printStackTrace();
// }
// }
// }

/*
 * s.insert(5); s.insert(4);
 * 
 * s.insert(10); //s.find(2);
 * 
 * //s.find(10); s.insert(12); s.find(4); //s.insert(20); //s.insert(50);
 * //s.insert(5); //s.insert(2); //s.insert(1); //s.find(12); s.inorder();
 * s.preorder(); s.postorder();
 */

/**
 * public static void main(String[] args) { Path file =
 * Paths.get(System.getProperty("user.home")).resolve("data.txt");
 * 
 * try (BufferedReader in = new BufferedReader(Files.newBufferedReader(file,
 * Charset.forName("US-ASCII")))) { try (Scanner sc = new Scanner(in)) {
 * 
 * String line = null; SplayTree tree = null; //int finds; //int avePath; //int
 * N = 0; ; int[] paths = new int[333]; //while ((line = in.readLine()) != null)
 * { int i = 0; while (sc.hasNext()) {
 * 
 * if (sc.hasNext("#")) { int finds = 0; tree = new SplayTree();
 * 
 * //avePath = 0; sc.nextLine(); //N = sc.nextInt();
 * 
 * while (sc.hasNext("I") || sc.hasNext("F")) { if (sc.hasNext("I")) {
 * sc.next(); int key = sc.nextInt(); tree.insert(key); sc.nextLine();
 * 
 * } else if (sc.hasNext("F")) { sc.next(); int key = sc.nextInt();
 * tree.find(key); finds++; sc.nextLine();
 * 
 * } }
 * 
 * int cumPath = tree.getPathLength(); int avePath = cumPath / finds; paths[i] =
 * avePath; i++;
 * 
 * }
 * 
 * }
 * 
 * for (i = 1000; i < 1000000; i += 3000) { System.out.printf("%d\t%d\n", i,
 * paths[i]); } } catch (NoSuchElementException e) { System.err.println("Problem
 * parsing input"); e.printStackTrace(); }
 * 
 * } catch (IOException e) { System.err.println("Error reading from file");
 * e.printStackTrace(); } }
 * 
 * }
 **/
// public class SplayIterativeTest {
//
//

// public static void main(String[] args) {
//
//
//
//
//
// }
////
// SplayIterative t = new SplayIterative();
// int[] keys = { 20, 5, 2, 25, 55, 1, -22, 0 };
// for (int i = 0; i < keys.length; i++) {
// t.insert(keys[i]);
// t.inorder();
// t.preorder();
// t.postorder();
//
// }
// for (int i = 0; i < keys.length; i++) {
// System.out.println(t.find(keys[i]));
// }
//
// }
//
// }
