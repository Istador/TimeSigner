
public class Verifier {
	public static void main(String[] args) {
		if (args.length == 1) {
			de.blackpinguin.timesigner.Main.verify(args[0]);
		}
		else {
		  System.out.println("usage: java Verifier <xml-string>");
		}
	}

}
