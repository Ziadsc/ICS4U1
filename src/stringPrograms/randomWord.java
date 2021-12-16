package stringPrograms;

public class randomWord {
    public static void main(String[] args) {
    	String word = "";
    	int wordLength = 6;
    	int vowelAmount = 2;
    	
    	char[] constants = {'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'z'};
    	char[] vowels = {'a', 'e', 'i', 'o', 'u', 'y'};
    	int[] vowelPos = new int[vowelAmount];
    	
    	vowelPos[0] = (int) (Math.random() * 5);
    	
    	for (int i = 1; i < vowelAmount; i++) {
    		vowelPos[i] = (int) (Math.random() * 5);
    		for (int j = 0; j < i; j++) {
    			while (vowelPos[j] == vowelPos[i])
        			vowelPos[i] = (int) (Math.random() * 5);
    		}
    	}
    	
    	
    	for (int i = 0; i < wordLength; i++) {
    		boolean vowel = false;
    		for (int j = 0; j < vowelAmount; j++) {
    			if (i == vowelPos[j]) {
    				word = word + vowels[(int) (Math.random() * vowels.length-1)];
    				vowel = true;
    				break;
    			}
    		}
    		
    		if (!vowel)
    			word = word + constants[(int) (Math.random() * constants.length-1)];
    	}
    	
    	System.out.print(word);
    }
}
