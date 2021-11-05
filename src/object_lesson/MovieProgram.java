package object_lesson;

import java.util.ArrayList;

public class MovieProgram {

	//global variables
	static ArrayList<Movie> movieList = new ArrayList<Movie>();
	
	public static void main(String[] args) {
		
		String store = Movie.storename;
		
		Movie m = new Movie("The Avengers", 2015,5);	//"new" makes an object
		m.year = 2000; //should we be allowed to change the values?		
		movieList.add(m);
		
		m = new Movie("Jurassic Park", 1997,4);
		movieList.add(m);
		movieList.add( new Movie("Gone with the wind",1937,3) );
		
		printList();	
	}
	
	static void printList() {
		for (int i = 0; i < movieList.size(); i++) {
			Movie movie = movieList.get(i);
			//System.out.println(movie.title + "\t" + movie.year + " stars=" + movie.rating);
			String s = String.format("| %-15.7s | %4d | %d*%n", movie.title, movie.year, movie.rating );
			System.out.print(s);
		}
		
		System.out.printf("%020.4f", Math.PI * 1000);
		System.out.printf("%n%.4f", 0.9191919191);
	}
	
	static void printList2() {
		for (Movie movie : movieList) { // a for each loop
			
			System.out.println(movie.title + "\t" + 
			movie.year + " stars=" + movie.rating);			
		}
	}
}






