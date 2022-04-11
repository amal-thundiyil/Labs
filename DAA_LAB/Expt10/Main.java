import static java.lang.Math.*;
import java.util.*;
import java.io.*;

public class Main {
    static Scanner fs;
    static PrintWriter out;
    static char sampleString[], pattern[];
    static void init() {
        fs = new Scanner(System.in);
        out = new PrintWriter(System.out);
    }

    static int hash() {
        int ans = 0;
        return 0;   
    }
    static int mod = 1_000_000_007;
    static int h(char x, int i, int len) {
        return ((int)pow(len, i)*(sampleString[i]-'a'+1));
    }
    public static void main(String args[]) {
        init();
        sampleString = fs.next().toCharArray();
        int n = sampleString.length;
        pattern = fs.next().toCharArray();
        int m = pattern.length;
        long h_b = 0;
        long h_a = 0;
        for(int i = 0; i < m ; i++) {
            h_b += ((int)pow(m, i)*(pattern[i]-'a'+1));
            h_b %= mod;
            h_a += ((int)pow(m, i)*(sampleString[i]-'a'+1));
            h_a %= mod;
        }
        for(int i = 0 ; i < n - m; i++) {
            if(h_b == h_a) {
                boolean eq = true;
                for(int j = 0 ; j < m ; j++) {
                    eq &= sampleString[i+j] == pattern[j];
                }
                if(eq) {
                    out.println("String matched: " + i);
                }
            }
            else {
                h_a -= sampleString[i]-'a'+1;
                h_a /= m;
                h_a += ((int)pow(m, m-1)*(sampleString[i+m]-'a'+1));
                h_a %= mod;
            }
        }
        out.println("Hash Value of matched: " + h_b);
        out.println("Sample String: " + Arrays.toString(sampleString));
        out.println("Pattern: " + Arrays.toString(pattern));
        out.close();
    }
}