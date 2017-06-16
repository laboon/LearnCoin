package laboon;

import java.math.BigInteger;
import java.util.ArrayList;

public class Block {

    public long index;

    public long timestamp;

    public long confirmations;

    public long size;
    
    public long height;

    public BigInteger merkleRoot;

    public ArrayList<Transaction> tx;

    public long time;

    public long nonce;

    public BigInteger bits;

    public BigInteger chainwork;

    public BigInteger previousBlockHash;

    public BigInteger nextBlockHash;

}
