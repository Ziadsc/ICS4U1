package object_lesson;

/* This does a selection sort. It also is broken up into separate methods */
public class ArraySorting {

	static int numbers[] = new int[15];
	
	public static void main(String[] args) {
		
		fillArray();
		printArray();
		System.out.println("----------------------");
		
		sortArray();
	
		printArray();
	}

	//fill array with random numbers
	static void fillArray() {
		for (int i=0;i<numbers.length;i++) {
			numbers[i] = (int)(Math.random()*1000);
		}
	}
	
	static void printArray() {
		for(int x=0;x<(numbers.length);x++) {
			System.out.print(numbers[x] + " ");
		}
		System.out.println();
	}
	
	
	static void sortArray() {
		int temp = 0;
		//sort.  i will represent the numbers that are already sorted
		for(int i=0;i<numbers.length;i++) {
			int small = i;
			//Find the location of the smallest number 
			//in the rest of the array
			for (int j=i; j<numbers.length;j++) {
				if (numbers[j]<numbers[small]) {
					small = j;					
				}
			}
			
			//Now switch the current number with the smallest number 
			temp = numbers[i];
			numbers[i] = numbers[small];
			numbers[small] = temp;
			
		}

	}
}
