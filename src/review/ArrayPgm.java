
package review;


import java.awt.Polygon;
import java.util.Arrays;

public class ArrayPgm {

	
	public static void main(String[] args) {
		int small = 9999999;
		int large = -1;
		int[] array = new int[12];
		
		//populate the array
		for (int i = 0; i < array.length; i++) {
			array[i] = (int)(Math.random()*100+1);			
		}
		
		//print out the array
		System.out.println(Arrays.toString(array));
		
		//find largest and smallest
		for (int n : array) {
			if (n < small) small = n;
			if (n > large) large = n;
		}
		
		System.out.println(small + " - " + large);
		
		
		
	}

}
