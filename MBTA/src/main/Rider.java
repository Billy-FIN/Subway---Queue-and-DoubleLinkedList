/**
* This class represents a rider on a red line railway
* Known Bugs: None
*
* @author Qiuyang Wang 
* qiuyangwang@brandeis.edu 
* 3/13/2023
* COSI 21A PA1
*/


package main;

public class Rider {

	public String riderID;
	public String startingStation;
	public String destinationStation;
	public int direction;
	
	/**
	 * Initializes a rider with a ID, a starting station, and a destination. 
	 * The default direction is going south
	 * 
	 * Running time: O(1)
	 * @param riderID
	 * @param startingStation
	 * @param destinationStation
	 */
	public Rider(String riderID, String startingStation, String destinationStation) {
		this.riderID = riderID;
		this.startingStation = startingStation;
		this.destinationStation = destinationStation;
		this.direction = 1;
	}
	
	/**
	 * Returns the starting station of this rider
	 * 
	 * Running time: O(1)
	 * @return startingStation
	 */
	public String getStarting() {
		return startingStation;
	}
	
	/**
	 * Returns the destination station of this rider
	 * 
	 * Running time: O(1)
	 * @return destinationStation
	 */
	public String getDestination() {
		return destinationStation;
	}
	
	/**
	 * Returns the ID of this rider
	 * 
	 * Running time: O(1)
	 * @return riderID
	 */
	public String getRiderID() {
		return riderID;
	}
	
	/**
	 * Checks if the rider is going to north
	 * 
	 * Running time: O(1)
	 * @return direction == 0
	 */
	public boolean goingNorth() {	
		return direction == 0;
	}
	
	/**
	 * Swaps the rider's traveling direction
	 * 
	 * Running time: O(1)
	 */
	public void swapDirection() {
		if (this.goingNorth()) {
			direction = 1;
		} else {
			direction = 0;
		}
	}
	
	/**
	 * Returns a String representation of this rider
	 * 
	 * Running time: O(1)
	 * @return riderID + ", " + destinationStation
	 */
	@Override
	public String toString() {
		return riderID + ", " + destinationStation;
	}
	
	/**
	 * Checks if two rider objects are the same based on their ID
	 * 
	 * Running time: O(1)
	 * @return true or false
	 */
	@Override
	public boolean equals(Object s) {
		if (s instanceof Rider) {
			Rider other = (Rider) s;
			return other.riderID.equals(this.riderID);
		} else {
			return false;
		}
	}
}
