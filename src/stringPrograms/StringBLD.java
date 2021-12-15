package stringPrograms;

public class StringBLD {
	public static void main(String[] args) {
		String input = "the dog was happpy on saturday with his balll";
		StringBuilder inputBuilt = new StringBuilder(input);
		
		inputBuilt.append(", zamn");
		inputBuilt.replace(0, 3, "then, the");
		inputBuilt.delete(18, 25);
		
		System.out.print(inputBuilt.toString());
	}
}
