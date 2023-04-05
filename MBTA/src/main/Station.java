/**
* This class represents a station in a red line railway
* Known Bugs: None
*
* @author Qiuyang Wang 
* qiuyangwang@brandeis.edu 
* 3/13/2023
* COSI 21A PA1
*/

package main;

public class Station {

	public Queue<Rider> northBoundRiders;
	public Queue<Rider> southBoundRiders;
	public Queue<Train> northBoundTrains;
	public Queue<Train> southBoundTrains;
	public String name;
	
	/**
	 * Initializes a station without trains and riders
	 * 
	 * Running time: O(1)
	 * @param name
	 */
	public Station(String name) {
		this.name = name;
		this.northBoundRiders = new Queue<Rider>(20);
		this.southBoundRiders = new Queue<Rider>(20);
		this.northBoundTrains = new Queue<Train>(20);
		this.southBoundTrains = new Queue<Train>(20);
	}
	
	/**
	 * Adds a rider to this station if his or her starting station
	 * is this station
	 * 
	 * Running time: O(n)
	 * @param r
	 * @return true or false
	 */
	public boolean addRider(Rider r) { 
		if (r.getStarting().equals(name)) {
			if (r.goingNorth()) {
				northBoundRiders.enqueue(r);
			} else {
				southBoundRiders.enqueue(r);
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Adds a train to this station if its current station
	 * is this station and move it to the queue
	 * 
	 * Running time: O(n^2) worst case
	 * @param t
	 * @return output
	 */
	public String addTrain(Train t) {
		t.updateStation(name);
		String output = name + " Disembarking Passengers: \n";
		output += t.disembarkPassengers();			//disembark riders if there is any
		if(t.goingNorth()) {
			northBoundTrains.enqueue(t);
		} else {
			southBoundTrains.enqueue(t);
		}
		return output;
	}
	
	/**
	 * Prepares a southbound Train of passengers and then board it
	 * 
	 * Running time: O(n)
	 * @return train or null
	 */
	public Train southBoardTrain() {
		if (southBoundTrains.size() == 0) {
			return null;			
		}
		Train train = southBoundTrains.front();
		while(train.hasSpaceForPassengers() && southBoundRiders.size() > 0) {		//add as many as possible riders to the train
			train.addPassenger(southBoundRiders.front());
			southBoundRiders.dequeue();
		}
		southBoundTrains.dequeue();
		return train;
	}
	
	/**
	 * Prepares a northbound Train of passengers and then board it
	 * 
	 * Running time: O(n)
	 * @return train or null
	 */
	public Train northBoardTrain() {
		if (northBoundTrains.size() == 0) {
			return null;			
		}
		Train train = northBoundTrains.front();
		while(train.hasSpaceForPassengers() && northBoundRiders.size() > 0) {		//add as many as possible riders to the train
			train.addPassenger(northBoundRiders.front());
			northBoundRiders.dequeue();
		}
		northBoundTrains.dequeue();
		return train;
	}
	
	/**
	 * Changes the direction of the first waiting northbound Train 
	 * and moves it to the southbound queue
	 * 
	 * Running time: O(n)
	 */
	public void moveTrainNorthToSouth() {
		if(northBoundTrains.size() != 0) {
			Train train = northBoundTrains.front();
			northBoundTrains.dequeue();
			train.swapDirection();
			southBoundTrains.enqueue(train);
		}
	}
	
	/**
	 * Changes the direction of the first waiting southbound Train 
	 * and moves it to the northbound queue
	 * 
	 * Running time: O(n)
	 */
	public void moveTrainSouthToNorth() {
		if(southBoundTrains.size() != 0) {
			Train train = southBoundTrains.front();
			southBoundTrains.dequeue();
			train.swapDirection();
			northBoundTrains.enqueue(train);
		}
	}
	
	/**
	 * Returns a String representation of this station
	 * 
	 * Running time: O(1)
	 * @return output
	 */
	@Override
	public String toString() {
		String output = "Station: " + name + "\n";
		output += northBoundTrains.size() + " north-bound trains waiting\n";
		output += southBoundTrains.size() + " south-bound trains waiting\n";
		output += northBoundRiders.size() + " north-bound passengers waiting\n";
		output += southBoundRiders.size() + " south-bound passengers waiting";
		return output;
	}
	
	/**
	 * Obtains the name of this station
	 * 
	 * Running time: O(1)
	 * @return name
	 */
	public String stationName() {
		return name;
	}
	
	/**
	 * Checks if two station objects are the same based on their name
	 * 
	 * Running time: O(1)
	 * @return true or false
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Station) {
			Station other = (Station) o;
			return other.name.equals(name);
		} else {
			return false;
		}
	}
}
