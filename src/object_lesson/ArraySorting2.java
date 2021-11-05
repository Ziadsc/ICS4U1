package object_lesson;

public class ArraySorting2 {

	public static void main(String[] args) {

		int temp;
		int numbers[] = new int[15];

		//fill array with random numbers
		for (int i=0;i<numbers.length;i++) {
			numbers[i] = (int)(Math.random()*1000);
			System.out.print(numbers[i] + " ");
		}

		System.out.println("\n----------------------");

		//sort.  i will represent the numbers that are already sorted
		for(int i=0;i<numbers.length;i++) {
			temp = i;
			for (int j=i;j<numbers.length;j++) {
				//This switches every number that is smaller than i to the first position.
				//Not what we want to do!
				//				if (numbers[i]<numbers[j]) {
				//					temp=numbers[i];
				//					numbers[i]=numbers[j];
				//					numbers[j]=temp;
				//				}
				
				
				//numbers[temp] will be the smallest number so far
				if (numbers[temp] > numbers[j]) {
					temp=j;
				}
			}
			int temp2 = numbers[i];
			numbers[i] = numbers[temp];
			numbers[temp] = temp2;
			
			//debug print				
			for(int x=0;x<(numbers.length);x++) {
				System.out.print(numbers[x] + " ");
			}
			System.out.println();			

		}


		//print again
		System.out.println("\n----------------------");
		for(int i=0;i<(numbers.length);i++) {
			System.out.print(numbers[i] + " ");

		}

	}
}
