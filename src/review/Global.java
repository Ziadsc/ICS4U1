package review;

public class Global {
	//here x is global: any method in this class can access it
	static int x = 5;
	
	public static void main(String[] args) {		
		
		//x is LOCAL to this method
		int x = -10;
		x++;		
		//printme(x, 99);
		
		//access global x:
		//if it's static use the class name
		Global.x = 77;
		//if it's not static use "this"
		//this.x = 77;
		
		printx();
	}

	static void printx() {
		System.out.println(x);
	}
	
	
	
	
	static void printme(int animal, int z) {
		System.out.println("***"+ animal +"***");
	}

}
