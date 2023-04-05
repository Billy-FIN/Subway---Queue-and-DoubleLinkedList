/**
* This class provides the implementation of a red line railway
* Known Bugs: None
*
* @author Qiuyang Wang 
* qiuyangwang@brandeis.edu 
* 3/13/2023
* COSI 21A PA1
*/

package main;

public class Railway {

	public DoubleLinkedList<Station> railway;
	public String[] stationNames;
	public int stationNum = 0;
	
	/**
	 * Initializes a railway object contains an empty 
	 * list of stations and corresponding names
	 * 
	 * Running time: O(1)
	 */
	public Railway() {
		this.stationNames = new String[18];
		this.railway = new DoubleLinkedList<Station>();
	}
	
	/**
	 * Adds a station to this railway object
	 * 
	 * Running time: O(n)
	 * @param s
	 */
	public void addStation(Station s) {
		railway.insert(s);
		stationNames[stationNum] = s.name;
		stationNum++;
	}
	
	/**
	 * Adds a passenger to this railway object
	 * 
	 * Running time: O(n^2) worst case
	 * @param r
	 */
	public void addRider(Rider r) {
		this.setRiderDirection(r);
		if(railway.get(new Station(r.getStarting())) != null) {			//if the rider's starting station exists
			railway.get(new Station(r.getStarting())).addRider(r);
		}
	}
	
	/**
	 * Adds a train to this railway object
	 * 
	 * Running time: O(n^2) or O(n^3) worst case
	 * @param t
	 */
	public void addTrain(Train t) {
		if(railway.get(new Station(t.currentStation)) != null) {		//if the current station of the train exists	
			railway.get(new Station(t.currentStation)).addTrain(t);
		}
	}
	
	/**
	 * Sets the traveling direction for the passenger
	 * 
	 * Running time: O(1) because the length of the stationName
	 * is fixed for red line railway
	 * @param r
	 */
	public void setRiderDirection(Rider r) {
		int start = -1;
		int destination = -1;
		for (int i = 0; i < stationNames.length; i++) {
			if (r.getStarting().equals(stationNames[i])) {
				start = i;
			} 
			if (r.getDestination().equals(stationNames[i])) {
				destination = i;
			}
		}
		if(start > destination && destination != -1 && start != -1) {		//the rider is going to north
			r.swapDirection();
		}
	}
	
	/**
	 * Simulates the red line railway and log the events
	 * 
	 * Running time: O(n) because stationNum is fixed for red line railway
	 * @return output
	 */
	public String simulate() {
		int num = 0;
		Node<Station> curr = railway.getFirst();
		String output = "";
		while(num < stationNum) {
			Station currStation = curr.getData();
			output += currStation + "\n\n";
			if(currStation.northBoundTrains.size() != 0 && !currStation.stationName().equals("Alewife")) {		//board trains to north
				Train train = currStation.northBoardTrain();
				output += curr.getPrev().getData().addTrain(train) + train + "\n";
			}
			if(currStation.southBoundTrains.size() != 0 && !currStation.stationName().equals("Braintree")) {		//board trains to south
				Train train = currStation.southBoardTrain();
				output += curr.getNext().getData().addTrain(train) + train + "\n";
			} 
			if(currStation.stationName().equals("Alewife") && currStation.northBoundTrains.size() != 0) {		//swap direction at Alewife
				currStation.moveTrainNorthToSouth(); 
			} else if (currStation.stationName().equals("Braintree") && currStation.southBoundTrains.size() != 0) {		//swap direction at Braintree
				currStation.moveTrainSouthToNorth();
			}
			curr = curr.getNext();
			num++;
		}
		return output;
	}
	
	/**
	 * Returns a String representation of this railway
	 * 
	 * Running time: O(n)
	 * @return output
	 */
	@Override
	public String toString() {
		String output = "";
		Node<Station> curr = railway.getFirst();
		while(curr.getNext() != null) {
			output += curr.getData().toString() + "\n\n";
			curr = curr.getNext();
		}
		output += curr.getData().toString() + "\n";
		return output;
	}
}
