package object_lesson;

class Movie {
	/* global variables */ 
	
	//static variables
	static String storename = "Jumbo video";
	
	//instance variables
	String title;
	//String genre;
	int year = 2020;  //default value
	int rating; //0-5
	
	//constructor
	
	Movie(String t, int year, int rating) {		
		title = t;
		this.year = year;
		
		if (rating > 5 || rating < 0) rating = 1;
		this.rating = rating;
	}
}
