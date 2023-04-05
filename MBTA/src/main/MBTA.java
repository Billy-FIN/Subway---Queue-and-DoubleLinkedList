/**
* This class has the main method and runs the simulation of a Railway
* Known Bugs: None
*
* @author Qiuyang Wang 
* qiuyangwang@brandeis.edu 
* 3/13/2023
* COSI 21A PA1
*/

package main;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class MBTA {

	public static final int SOUTHBOUND = 1;
	public static final int NORTHBOUND = 0;
	
	static final int TIMES = 6;
	static Railway r;
	
	/**
	 * This method is the main method to read files and then run the simulation
	 * 
	 * Running time: O(n^2)
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		r = new Railway();
		initStations("redLine.txt");
		initTrains("trains.txt");
		initRiders("riders.txt");
		System.out.println("INITIATED RED LINE\n");
		System.out.println(r);
		System.out.println("BEGINNING RED LINE SIMULATION");
		runSimulation();
	}
	
	/**
	 * Runs the simulation of a Railway object for some times
	 * 
	 * Running time: O(n*m) where m is the TIMES (exact number of times 
	 * that this method will simulate) and n is the number of the input
	 */
	public static void runSimulation() {
		for (int i = 0; i < TIMES; i++) {
			System.out.println("\n ------ " + (i + 1) + " ------ \n");
			System.out.print(r.simulate());
		}
	}
	
	/**
	 * Reads a file to add trains to a Railway object
	 * 
	 * Running time: O(n)
	 * @param trainsFile
	 * @throws FileNotFoundException
	 */
	public static void initTrains(String trainsFile)  throws FileNotFoundException {
		Scanner input = new Scanner (new File(trainsFile));
		while(input.hasNextLine()) {
			String next = input.nextLine();
			if(!next.equals("")) {
				String currentStation = next;
				int direction = input.nextInt();
				r.addTrain(new Train(currentStation, direction));
			}
		}
	}
	
	/**
	 * Reads a file to add passengers to a Railway object
	 * 
	 * Running time: O(n)
	 * @param ridersFile
	 * @throws FileNotFoundException
	 */
	public static void initRiders(String ridersFile) throws FileNotFoundException {
		Scanner input = new Scanner (new File(ridersFile));
		while(input.hasNextLine()) {
			String iD = input.nextLine();
			String start = input.nextLine();
			String end = input.nextLine();
			r.addRider(new Rider(iD, start, end));
		}
	}
	
	/**
	 * Reads a file to add stations to a Railway object
	 * 
	 * Running time: O(n^2)
	 * @param stationsFile
	 * @throws FileNotFoundException
	 */
	public static void initStations(String stationsFile) throws FileNotFoundException {
		Scanner input = new Scanner (new File(stationsFile));
		while(input.hasNextLine()) {
			String name = input.nextLine();
			r.addStation(new Station(name));
		}
	}
}
