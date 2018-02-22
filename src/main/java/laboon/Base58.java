package laboon;

import java.math.BigInteger;

public class Base58 {

    private static final long PREFIX = 0;

    /**
     * Possible characters in base58.  These include all lowercase and capital 
     * letters, plus decimal digits, EXCEPT 0 (zero), O (uppercase o), I 
     * (uppercase i), and l (lowercase L).  This is to prevent confusion about
     * the characters (e.g. mistaking a l for an I or a 0 for a O) when writing
     * them down.  
     * Also, note that since there is no 0, "1" is 0 in base58.
     */
    private static final char[] VALID_CHARS = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz".toCharArray();
    
    private Base58() {/* INTENTIONALLY LEFT BLANK */}

    /**
     * Get the base58-encoded string for a given array of bytes.
     * 
     * @param byteArr bytes to encode
     * @return encoded string
     */
    public static String encode(byte[] byteArr) {

		// Return empty string if nothing passed in	
		if (byteArr == null || byteArr.length == 0)
		    return "";
	
		return encode(new BigInteger(1, byteArr));
    }
    
    /**
     * Get the base58-encoded string for a BigInteger.
     * 
     * @param toEncode integer to encode
     * @return encoded string
     */
    public static String encode(BigInteger toEncode) {
    	
    	StringBuilder encodedString = new StringBuilder();
    	BigInteger x = toEncode;
    	
    	// While x is greater than 0
    	while (x.compareTo(BigInteger.valueOf(0)) == 1) {
    		
    		// Divide current value of x by 58
    		BigInteger[] divResult = x.divideAndRemainder(BigInteger.valueOf(58));
    		
    		// Value of x is now the value of the truncated quotient
    		x = divResult[0];
    		
    		// Use the value of remainder to select the next character
    		BigInteger remainder = divResult[1];
    		encodedString.append(VALID_CHARS[remainder.intValue()]);
    	}
    	
    	// Add the first valid char for each leading zero bytes
    	byte[] byteArr = toEncode.toByteArray();
		for (int i = 0; i < byteArr.length; i++)
			encodedString.append(byteArr[i] == 0 ? VALID_CHARS[0] : "");
		
		// Reversed list of characters is the base58 encoded string
    	return encodedString.reverse().toString();
    }
    
    /**
     * Get the decoded integer from a base58-encoded string.
     * 
     * @param toDecode string to decode
     * @return decoded BigInteger
     */
    public static BigInteger decode(String toDecode) throws IllegalArgumentException {
    	
    	// Return empty bytes if nothing passed in	
    	if (toDecode.length() == 0)
    		return BigInteger.valueOf(0);
    	
    	// Convert each character to base58 byte sequence
    	byte[] indices = new byte[toDecode.length()];
    	for (int i = 0; i < toDecode.length(); i++) {
    		char current = toDecode.charAt(i);
    		int index = indexOfChar(current);
    		
    		if (index == -1)
    			throw new IllegalArgumentException("Cannot base58-decode bad address.");
    		indices[i] = (byte) index;
    	}
    	
    	// Decode by addition of remainder and multiplication by 58, excluding zeros
    	BigInteger x = BigInteger.valueOf(0);
    	for (int i = 0; i < indices.length; i++) {
    		if (indices[i] != 0) {
    			x = x.add(BigInteger.valueOf(indices[i]));
    			if (i != indices.length - 1)
					x = x.multiply(BigInteger.valueOf(58));    			
    		}
    	}
    	
    	return x;
    }
    
    /*
     * Get the index of the valid character.
     */
    private static int indexOfChar(char val) {
    	for (int i = 0; i < VALID_CHARS.length; i++)
    		if (VALID_CHARS[i] == val)
    			return i;    		
    	return -1;
    }

    /**
     * Get the checksum for a given prefix + data 
     * This is done via double-SHA256 hashing, and taking the
     * first four bytes
     * 
     * @param data - all data (including prefix)
     * @return four bytes to be end-concatenated
     */
    public static String checksum(String data) {
    	return "check";
    }
    
    public static void main(String[] args) {
    	BigInteger testBI = BigInteger.valueOf(3471844090L);
    	System.out.println("Encode test #1 : " + (Base58.encode(testBI).equals("16Ho7Hs") ? "Correct" : "Incorrect"));
    	
    	byte[] testbytes = "Hello World".getBytes();
    	System.out.println("Encode test #2 : " + (Base58.encode(testbytes).equals("JxF12TrwUP45BMd") ? "Correct" : "Incorrect"));

    	String encodedStr = "16Ho7Hs";
    	System.out.println("Decode test #1 : " + (Base58.decode(encodedStr).equals(BigInteger.valueOf(3471844090L)) ? "Correct" : "Incorrect"));
    }

}
