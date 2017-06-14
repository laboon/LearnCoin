/**
 * This class will provide a way to generate SHA-256 values given a String.
 * It is a utility class meaning that we cannot instantiate it and will
 * only contain static methods.
 * Since SHA-256 is a pure, stateless method (i.e., no side effects and 
 * return value is only dependent on input value), this is best way to express
 * it.
 */

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha {

    /**
     * Do not allow instantiation
     */
    
    private Sha() {
	// DELIBERATELY LEFT BLANK
    }

    private static MessageDigest _md;
    
    static {
	try {
	    _md = MessageDigest.getInstance("SHA-256");
	} catch (NoSuchAlgorithmException nsaex) {
	    System.out.println("No such algorithm SHA-256!");
	    // TODO - fix this
	    System.exit(1);
	}
    }

    public byte[] sha256(String toHash) {
	return _md.digest(toHash.getBytes(StandardCharsets.UTF_8));
    }

    public String sha256AsString(String toHash) {
	byte[] hashBytes = this.sha256(toHash);
	StringBuilder sb = new StringBuilder();
	for (byte b : hashBytes) {
	    sb.append(String.format("%02x", b));
	}
	return sb.toString();
    }
    
}
