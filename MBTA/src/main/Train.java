/**
* This class represents a train in a red line railway
* Known Bugs: None
*
* @author Qiuyang Wang 
* qiuyangwang@brandeis.edu 
* 3/13/2023
* COSI 21A PA1
*/
package main;

public class Train {

	public static final int TOTAL_PASSENGERS = 10;
	public Rider[] passengers;
	public int passengerIndex;
	public int direction;
	public String currentStation;
	
	/**
	 * Initializes an empty train
	 * 
	 * Running time: O(1)
	 * @param currentStation
	 * @param direction
	 */
	public Train(String currentStation, int direction) {
		this.direction = direction;
		this.currentStation = currentStation;
		this.passengerIndex = 0;
		this.passengers = new Rider[TOTAL_PASSENGERS];
	}
	
	/**
	 * Checks if the train is going to north
	 * 
	 * Running time: O(1)
	 * @return direction == 0
	 */
	public boolean goingNorth() {
		return direction == 0;
	}
	
	/**
	 * Swaps the traveling direction of this train
	 * 
	 * Running time: O(1)
	 */
	public void swapDirection() {
		if (this.goingNorth()) {
			this.direction = 1;
		} else {
			this.direction = 0;
		}
	}
	
	/**
	 * Returns all the riders' String representation on this train
	 * 
	 * Running time: O(n)
	 * @return output or ""
	 */
	public String currentPassengers() {
		if (passengerIndex == 0) {
			return "";
		}
		String output = "";
		for (int i = 0; i < passengerIndex; i++) {
			output += passengers[i].toString() + "\n";
		}
		return output;
	}
	
	/**
	 * Adds a rider to this train
	 * 
	 * Running time: O(1)
	 * @param r
	 * @return true or false
	 */
	public boolean addPassenger(Rider r) {
		if(r.getStarting().equals(currentStation) && r.goingNorth() == this.goingNorth() && this.hasSpaceForPassengers()) {
			passengers[passengerIndex] = r;
			passengerIndex++;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Checks if there is more space for passengers on this train
	 * 
	 * Running time: O(1)
	 * @return passengerIndex < TOTAL_PASSENGERS
	 */
	public boolean hasSpaceForPassengers() {
		return passengerIndex < TOTAL_PASSENGERS;
	}
	
	/**
	 * Disembarks passengers when this train comes to the right station
	 * 
	 * Running time: O(n^2) worst case
	 * @return output
	 */
	public String disembarkPassengers() {
		if (passengerIndex == 0) {
			return "";
		}
		String output = "";
		for (int i = 0; i < passengerIndex; i++) {
			if (passengers[i].getDestination().equals(currentStation)) {
				output += passengers[i].getRiderID() + "\n";
				for (int j = i; j < passengerIndex - 1; j++) {			//a dynamic array that arranges the array if someone is disembarked 
					passengers[j] = passengers[j + 1];
				}
				passengers[passengerIndex - 1] = null;
				passengerIndex--;
			} 
		}
		return output;
	}
	
	/**
	 * Updates the current station of the train
	 * 
	 * Running time: O(1)
	 * @param s
	 */
	public void updateStation(String s) {
		currentStation = s;
	}
	
	/**
	 * Obtains the current station of the train
	 * 
	 * Running time: O(1)
	 * @return currentStation
	 */
	public String getStation() {
		return currentStation;
	}
	
	/**
	 * Returns a String representation of this train
	 * 
	 * Running time: O(n)
	 * @return output
	 */
	@Override
	public String toString() {
		String output = "";
		if (this.goingNorth()) {
			output += "Direction: Northbound\n";
		} else {
			output += "Direction: Southbound\n";
		}
		output += "Passengers: \n" + currentPassengers() +"Current station: " + currentStation + "\n";
		return output;
	}
}
