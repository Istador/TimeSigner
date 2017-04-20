
public class Signer {
	public static void main(String[] args) {
		if (args.length == 1) {
			de.blackpinguin.timesigner.Main.sign(args[0]);
		}
		else {
		  System.out.println("usage: java Signer <sha512hex>");
		}
	}
}
