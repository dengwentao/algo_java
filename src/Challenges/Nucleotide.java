package Challenges;
import java.util.*;

/**
 * Created by wedeng on 2/20/15.
 * The nucleotide type takes on one of the values A, C, G, T, or the special value ε. Given a stream function
 nucleotide next(stream *s);
 which produces the next value in a stream of nucleotides (or which produces ε if there are no more elements in the
 stream), implement an on-line algorithm to find sequences of values and to print them along with some surrounding
 context. Specifically, given a target sequence T and two numbers x and y, print the x preceding stream values, the
 target, then the y succeeding stream values. Do this for each occurrence of the target.
 Be aware that:
 • Either (or both) of x and y may be zero (indicated that no preceding or succeeding context should be printed),
 but T will not be empty.
 • The end-of-stream marker ε will not appear in T.
 • If the stream produces fewer than x nucleotides before T, or fewer than y nucleotides after T, print as many as
 there are.
 • Targets may overlap in the stream, and each should be treated as a distinct match.
E.g., Suppose the stream produces
 AAGTACGTGCAGTGAGTAGTAGACCTGACGTAGACCGATATAAGTAGCTAε
 Your algorithm, when run with x = 5, T = AGTA, and y = 7, should find following results:
 A AGTA CGTGCAG
 CAGTG AGTA GTAGACC
 TGAGT AGTA GACCTGA
 ATATA AGTA GCTA
 */

class InvalidInputException extends Exception {
    public InvalidInputException(String msg) {
        super(msg);
    }
}

//rolling hash of a sliding window
class RollingHash {
    Queue<Character> queue;     //the sliding window
    int size;                   //size of the window
    long hashValue = 0L;        //rolling hash value of current window
    static final int base = 4;  //there're 4 kinds of chars only
    final long mod = Long.MAX_VALUE >> 2;   //avoid overflow
    long pow; //highest order

    public RollingHash(int size) {
        this.size = size;
        queue = new LinkedList<Character>();
        pow = (long) Math.pow(base, size-1);
    }

    //map char types to 0-3.
    static public int charVal(char c) throws InvalidInputException {
        switch(c) {
            case 'A':
                return 0;
            case 'C':
                return 1;
            case 'G':
                return 2;
            case 'T':
                return 3;
        }
        throw new InvalidInputException("Invalid input character found: " + c);
    }

    //input a char, and pop a char if queue is full. Hash value is updated accordingly.
    //return the popped up char. null if nothing to pop out.
    public Character input(char c) throws InvalidInputException {
        Character kickOut = null;
        if(queue.size()==size) {
            kickOut = queue.poll();
            hashValue -= charVal(kickOut) * pow;
        }
        queue.offer(c);
        hashValue = hashValue * base % mod + charVal(c);
        //System.out.format("input %c, pop %c, hash=%d\n", c, kickOut, hashValue);
        return kickOut;
    }

    //return current hash value in the window.
    public long hashValue() {
        return hashValue;
    }

    //clear the rolling hash to the initial state
    public void clear() {
        queue.clear();
        hashValue = 0L;
    }

    //return char sequence in the window
    public String getString() {
        StringBuilder sb = new StringBuilder();
        for(char c: queue)
            sb.append(c);
        return sb.toString();
    }
}

public class Nucleotide {
    RollingHash body;   //rolling hash window for pattern match
    Queue<Character> head; //hold previous x chars
    Queue<Character> tail; //hold y chars behind
    long patternHash; //rolling hash value of the pattern
    int x;
    int y;

    public Nucleotide(int x, int y, String pattern) throws InvalidInputException {
        if(x<0 || y<0 || pattern.isEmpty())
            throw new InvalidInputException(String.format("Invalid input parameters: x=%d, y=%d, pattern=%s", x, y, pattern));
        this.x = x;
        this.y = y;
        head = new LinkedList<Character>();
        tail = new LinkedList<Character>();
        body = new RollingHash(pattern.length());
        for(int i=0; i<pattern.length(); i++)
            body.input(pattern.charAt(i));
        patternHash = body.hashValue();
        //System.out.format("Pattern is %s, hash is %d\n", body.getString(), patternHash);
        body.clear();
    }

    //shift the window head+body+tail right 1 position
    //return true if there's a match.
    public boolean shift(char c) throws InvalidInputException {
        tail.offer(c);
        if(tail.size() > y)
            return inputBody();
        return false;
    }

    //pop one char from tail and append it to body. It's caller's responsibility to avoid empty tail.
    //return true if there's a match.
    public boolean inputBody() throws InvalidInputException {
        Character in = body.input(tail.poll());
        if(in != null) {
            head.offer(in);
            if(head.size() > x)
                head.poll();
        }
        return patternHash == body.hashValue();
    }

    //get current char sequence in head+body+tail
    public String getString() {
        StringBuilder sb = new StringBuilder();
        for(char c: head)
            sb.append(c);
        sb.append(body.getString());
        for(char c: tail)
            sb.append(c);
        return sb.toString();
    }

    public static void main(String[] args) {
        String input = "AAGTACGTGCAGTGAGTAGTAGACCTGACGTAGACCGATATAAGTAGCTAε";
        String pattern = "AGTA";
        int x=5, y=7;

        try {
            Nucleotide nucl = new Nucleotide(x, y, pattern);

            for(int i=0; i<input.length() && input.charAt(i)!='ε'; i++) {
                //System.out.print(input.charAt(i));
                if(nucl.shift(input.charAt(i)))
                    System.out.println(nucl.getString());
            }

            //make sure all tail chars are inputted to the body
            for(int i=0; i<y; i++) {
                if(nucl.inputBody())
                    System.out.println(nucl.getString());
            }
        }
        catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }
}

