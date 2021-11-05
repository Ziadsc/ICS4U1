package review;

/* See also:
 * http://quarkphysics.ca/ICS3U1/unit3/char2int.html
 * and this (second part of the page)
 * http://quarkphysics.ca/ICS3U1/unit3/wrappers.html
 */
public class CharacterDemo {

	public static void main(String[] args) {
		char letter = 'A';
		
		System.out.println(letter);		
		letter++;
		System.out.println(letter);
		
		//make the alphabet		
		for (int i = 0; i < 26; i++) {
			char c = (char) (i + 65); //65 is A.  97 = a
			System.out.print(c);
			
		}

		//letter to int works fine
		letter = 'a';
		int n = letter;
		System.out.println("\n" + n);
		
		//separate a 2 digit int
		int x = 82;
		String s = "" + x;
		int n1 = s.charAt(0) - '0';
		int n2 = s.charAt(1) - '0';
		
		System.out.println(n1);
		System.out.println(n2);
		
		//the 10th letter is 
		char c = (char)('A'-1 + 10);
		System.out.println(c);
	}

}
