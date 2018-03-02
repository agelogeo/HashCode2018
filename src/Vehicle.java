import java.util.ArrayList;

public class Vehicle {

	int id;	
	boolean isOccupied;
	int ride_id;
	int finish_time;
	int x_pos,y_pos;
	
	int total_rides ;
	ArrayList<Integer> rides_taken ;
	
	
	
	public int getTotal_rides() {
		return total_rides;
	}
	public void setTotal_rides(int total_rides) {
		this.total_rides = total_rides;
	}
	public ArrayList<Integer> getRides_taken() {
		return rides_taken;
	}
	public void setRides_taken(ArrayList<Integer> rides_taken) {
		this.rides_taken = rides_taken;
	}
	public int getX_pos() {
		return x_pos;
	}
	public void setX_pos(int x_pos) {
		this.x_pos = x_pos;
	}
	public int getY_pos() {
		return y_pos;
	}
	public void setY_pos(int y_pos) {
		this.y_pos = y_pos;
	}
	public Vehicle(int id, boolean isOccupied, int ride_id, int finish_time, int x_pos, int y_pos) {
		super();
		this.id = id;
		this.isOccupied = isOccupied;
		this.ride_id = ride_id;
		this.finish_time = finish_time;
		this.x_pos = x_pos;
		this.y_pos = y_pos;
		this.rides_taken= new ArrayList<Integer>();
		this.total_rides =0;
	}
	public int getRide_id() {
		return ride_id;
	}
	public void setRide_id(int ride_id) {
		this.ride_id = ride_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isOccupied() {
		return isOccupied;
	}
	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}
	public int getFinish_time() {
		return finish_time;
	}
	public void setFinish_time(int finish_time) {
		this.finish_time = finish_time;
	}
	
	
	
	
	
}
