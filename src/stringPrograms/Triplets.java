package stringPrograms;

public class Triplets {
	public static void main(String[] args) {
		String input = "the dog was happpy on saturday with his balll";
		char tempRepeat = ' ';
		int repeatAmount = 0;
		String repeats = "", word = "";
		boolean wordPassesVibeCheck = false;
		
		for (int i=0; i < input.length(); i++) {
			if (input.charAt(i) == ' ') {
				if (wordPassesVibeCheck) {
					repeats = repeats + word + " ";
					wordPassesVibeCheck = false;
				}
				word = "";
				repeatAmount = 0;
				tempRepeat = ' ';
			} else if (input.charAt(i) == tempRepeat) {
				repeatAmount++;
				if (repeatAmount == 3) {
					wordPassesVibeCheck = true;
				}
				word = word + input.charAt(i);
			} else {
				tempRepeat = input.charAt(i);
				word = word + input.charAt(i);
				repeatAmount = 1;
			}
			
			if (i >= input.length()-1) {
				if (wordPassesVibeCheck) {
					repeats = repeats + word + " ";
					wordPassesVibeCheck = false;
				}
				word = "";
				repeatAmount = 0;
				tempRepeat = ' ';
			}
		}
		
		System.out.println(repeats);
	}
}
