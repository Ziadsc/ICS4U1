package object_lesson;

public class CarTest {

	public static void main(String[] args) {
		//Car car1 = new Car(); //illegal
		Car car1 = new Car("Jaguar"); 
		Car car2 = new Car("Toyota", 5000);
		
		//car2.make = "Porsche"; //bad bad bad
		//fixed using "final"
		
		System.out.println(car1.toString());		
		System.out.println(car2.toString());
		
		car1.drive(-200);
		System.out.println(car1.toString());
		//car1.odometer = 1; // won't work because odometer is private
		int x = car1.getOdometer();
		System.out.println(x);
	}

}
