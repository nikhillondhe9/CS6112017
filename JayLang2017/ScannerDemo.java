/**
 * @author Christelle
 *
 */
public class ScannerDemo {

	private static String file1 = "/Users/nikhillondhe/Desktop/Fall/CS6112017/JayLang2017/prog2.jay";
	private static int tokenCounter = 1;

	public static void main(String args[]) {

		if(args.length != 0) {
			file1 = args[0];
		}

		TokenStream ts = new TokenStream(file1);
		System.out.println(file1);

		while (!ts.isEndofFile()) {
			Token t = ts.nextToken();
			System.out.println(String.format("Token %d - Type: %s - Value: %s", tokenCounter, t.getType(), t.getValue()));
			tokenCounter++;
		}
	}
}
