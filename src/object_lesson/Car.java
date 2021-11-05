package object_lesson;

class Car {
	
	//-- instance variables --
	final String make;		//final = once set, cannot be changed
	private int odometer = 12;	//private = cannot access outside this class
	// all cars have gone this far (12 km)
	
	//-- constructors --
	Car(String make){
		this.make = make;
	}
	Car(String make, int odo){
		this.make = make; 
		odometer = odo;
	}
	//prevent anyone from accessing this
	private Car(){
		make = "unknown";
	}
	
	//-- instance methods (no "static") --
	// apply to each object individually
	
	void drive(int distance) {
		this.odometer += Math.abs(distance);
	}
	
	int getOdometer() { return odometer; }
	
	void setOdometer(int n) {
		//check for master password
		//then reset odometer to n
	}
	
	@Override
	public String toString() {
		//return one line of text
		String s = "Make=" + make + "\tOdometer=" + odometer;
		return s;
	}
}
