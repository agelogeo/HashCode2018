import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class main {
	
	public static int R; // Rows of grid
    public static int C; // Columns of grid
    public static int F; // Vehicles in the fleet
    public static int N; // Total of rides
    public static int B; // Bonus per ride
    public static int T; // Time Steps
    
    
    public static ArrayList<Ride> rides;
    public static ArrayList<Vehicle> vehicles;
    public static ArrayList<String> outputList;
    
    public static int score = 0;
    

	public static void main(String[] args) throws FileNotFoundException, IOException{
		
		

	        outputList = new ArrayList<>();
	        
	        // Read input file.
	        //String name = "a_example"; // The name of the imput file (without .in extension)        
	        //String name = "b_should_be_easy"; // The name of the imput file (without .in extension)        
	        String name = "c_no_hurry"; // The name of the imput file (without .in extension)        
	        //String name = "d_metropolis"; // The name of the imput file (without .in extension)        
	        //String name = "e_high_bonus"; // The name of the imput file (without .in extension)        
	        BufferedReader fr = new BufferedReader(new FileReader(name + ".in"));
	        
	        String line, firstLine;
	        firstLine = fr.readLine();
	        
	        String[] vars;
	        vars = firstLine.split(" ");

	        R = Integer.valueOf(vars[0]);
	        C = Integer.valueOf(vars[1]);
	        F = Integer.valueOf(vars[2]);
	        N = Integer.valueOf(vars[3]);
	        B = Integer.valueOf(vars[4]);
	        T = Integer.valueOf(vars[5]);

	        rides = new ArrayList<Ride>();

	        int i = 0;
	        int j;
	        while ((line = fr.readLine()) != null) {
	        	vars = line.split(" ");
	        	rides.add(new Ride(
	        			Integer.valueOf(vars[0]),
    					Integer.valueOf(vars[1]),
						Integer.valueOf(vars[2]),
						Integer.valueOf(vars[3]),
						Integer.valueOf(vars[4]),
						Integer.valueOf(vars[5]),
						Math.abs(Integer.valueOf(vars[0]) - Integer.valueOf(vars[2])) + Math.abs(Integer.valueOf(vars[1]) - Integer.valueOf(vars[3]))));
	            i++;
	        }
	        
	        System.out.println("Scan OK!");
	        System.out.println("[R="+R+",C="+C+",F="+F+",N="+N+",B="+B+",T="+T+"]");
	        for(int ii=0;ii<rides.size();ii++)
	        	System.out.println(rides.get(ii));
	        System.out.println("--------------------------------------------------");
	        // START SOLUTION -----------------------------------------------------------------
	        
	        initiliazeVehicles();
	        
	        for(int clock=0; clock < T; clock++){
	        	
	        	for(int vehicle=0; vehicle<F ; vehicle++){	  
	        		if(!allrideschecked()){
		        		if(isVehicleAvailable(vehicle,clock)){
		        			getBestRide(vehicle,clock);
		        		}
	        		}
	        	}
	        }
	        
	        for(int v=0;v<F;v++){
	        	String templine = String.valueOf(vehicles.get(v).getTotal_rides());
	        	for(int r=0;r<vehicles.get(v).getTotal_rides();r++){
	        		templine += " "+vehicles.get(v).getRides_taken().get(r);
	        	}
	        	System.out.println(templine);
	        	
	        }
	        
	     // Create output file.
	        try (PrintWriter output = new PrintWriter(name + ".out", "UTF-8")) {
	        	for(int v=0;v<F;v++){
		        	String templine = String.valueOf(vehicles.get(v).getTotal_rides());
		        	for(int r=0;r<vehicles.get(v).getTotal_rides();r++){
		        		templine += " "+vehicles.get(v).getRides_taken().get(r);
		        	}
		        	output.println(templine);
		        	
		        }
	        }
	        System.out.println("FINISHED");

	}
	
	
	
	
	public static void initiliazeVehicles(){
		vehicles = new ArrayList<Vehicle>();
		for(int i=0;i<F;i++){
			// id, isOccupied , ride_id , finish time , x , y
			vehicles.add(new Vehicle(i,false,-1,0,0,0));
		}
				
	}
	
	public static boolean isVehicleAvailable(int i,int clock){
		// 0 is occupied
		if(vehicles.get(i).isOccupied){
			if(vehicles.get(i).getFinish_time() == clock ){
				vehicles.get(i).setOccupied(false);
				vehicles.get(i).setFinish_time(0);	
				rides.get(vehicles.get(i).getRide_id()).setChecked(true);
				return true;
			}
			return false;
		}
		// 1 is FREE
		else {
			return true;
		}
	}
	
	 
	public static void getBestRide(int vehicle,int clock){
		ArrayList<Integer> total_distances = new ArrayList<Integer>();
		int min = 0;
		boolean flag = true;
		for(int i=0;i<rides.size();i++){
			
				int before_reach=Math.abs( rides.get(i).getA() - vehicles.get(vehicle).getX_pos() ) + Math.abs( rides.get(i).getB() - vehicles.get(vehicle).getY_pos() );
				
				total_distances.add(before_reach+rides.get(i).getDistance());
			if(!rides.get(i).isChecked()){	
				if(flag) {
					min=i;
					flag=false;
				}
				else{
					if(before_reach+rides.get(i).getDistance() < total_distances.get(min))
						min=i;
							
				}
				//System.out.println("Clock : "+clock+" | Vehicle : "+vehicle+" | Ride : "+i+" | before _reach : "+before_reach+" | ride_distance : "+rides.get(i).getDistance()+" | Total : "+(before_reach+rides.get(i).getDistance())+" Min : "+min);
			}
			
		}
		System.out.println("Clock : "+clock+" | Vehicle : "+vehicle+" | Ride : "+min+" Total : "+total_distances.get(min));
		
		/*if(clock%5000<100)
			System.out.println("Clock now : "+clock);*/
		
		rides.get(min).setChecked(true);
		vehicles.get(vehicle).setOccupied(true);
		vehicles.get(vehicle).setFinish_time(clock+total_distances.get(min));
		vehicles.get(vehicle).setX_pos(rides.get(min).getA());
		vehicles.get(vehicle).setY_pos(rides.get(min).getB());
		vehicles.get(vehicle).setRide_id(min);
		vehicles.get(vehicle).setTotal_rides(vehicles.get(vehicle).getTotal_rides()+1);
		vehicles.get(vehicle).getRides_taken().add(min);
		
		if(allrideschecked())
			return;
	}

	public static boolean allrideschecked(){
		for(int i=0;i<rides.size();i++){
			if(!rides.get(i).isChecked())
				return false;
		}
		return true;
	}
}
