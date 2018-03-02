
public class Ride {
	
	 int a; // Start row
     int b; // Start col
     int x; // Finish row
     int y; // Finish col
     int s; // Earliest start
     int f; // Latest finish
     int distance;
     boolean checked = false;
     
     
     
     
	public Ride() {
		super();
	}

	


	public boolean isChecked() {
		return checked;
	}




	public void setChecked(boolean checked) {
		this.checked = checked;
	}




	public Ride(int a, int b, int x, int y, int s, int f,int distance) {
		super();
		this.a = a;
		this.b = b;
		this.x = x;
		this.y = y;
		this.s = s;
		this.f = f;
		this.distance=distance;
	}



	public int getDistance() {
		return distance;
	}



	public void setDistance(int distance) {
		this.distance = distance;
	}



	public int getA() {
		return a;
	}



	public void setA(int a) {
		this.a = a;
	}



	public int getB() {
		return b;
	}



	public void setB(int b) {
		this.b = b;
	}



	public int getX() {
		return x;
	}



	public void setX(int x) {
		this.x = x;
	}



	public int getY() {
		return y;
	}



	public void setY(int y) {
		this.y = y;
	}



	public int getS() {
		return s;
	}



	public void setS(int s) {
		this.s = s;
	}



	public int getF() {
		return f;
	}



	public void setF(int f) {
		this.f = f;
	}



	@Override
	public String toString() {
		return "Ride [a=" + a + ", b=" + b + ", x=" + x + ", y=" + y + ", s=" + s + ", f=" + f + ", distance="
				+ distance + "]";
	}



	
	
	
    
    

}
