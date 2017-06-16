package laboon;

public class Base58 {

    private static final long PREFIX = 0;

    /**
     * Possible characters in base58.  These include all lowercase and capital 
     * letters, plus decimal digits, EXCEPT 0 (zero), O (uppercase o), I 
     * (uppercase i), and l (lowercase L).  This is to prevent confusion about
     * the characters (e.g. mistaking a l for an I or a 0 for a O) when writing
     * them down.  
     * Also ,note that since there is no 0, "1" is 0 in base58.
     */

    private static final char[] CHARS = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz".toCharArray();
    
    private Base58() {
	// INTENTIONALLY LEFT BLANK
    }

    public static String encodeBase58(byte[] byteArr) {

	// Return empty string if nothing passed in

	if (byteArr == null || byteArr.length == 0) {
	    return "";
	}

	// Count leading 0's and save this number

	int numZeros = 0;
	while (numZeros < 
    }
    
    public static String encodeBase58(BigInteger toEncode) {
	return encodeBase58(toEncode.toByteArray());
    }

    /**
     * Get the checksum for a given prefix + data 
     * This is done via double-SHA256 hashing, and taking the
     * first four bytes
     * @param data - all data (including prefix)
     * @return four bytes to be end-concatenaed
     */
    public static String checksum(String data) {
	return "check";
    }
    
    public static encodeBase58Check(BigInteger toEncode) {
	return prefix + encodeBase58(toEncode) + checksum(prefix, toEncode);
    }

    
}
