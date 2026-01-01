import java.util.*;

public class DP{

   //either make a class which is static becuase main func needs an ref to run or execute a func which is to be provided so either create an object or make every function as 'public static' 
    
    public static int fib(int n){
        if (n==0 || n==1 ) {
            return n;
        }
        return fib(n-1) + fib(n-2);
    }

    public static int fibmemoization(int n, int dp[]){
        if (n==0 || n==1 ) {
            return n;
        }

        if(dp[n] != 0) return dp[n];

        dp[n] = fibmemoization(n-1, dp) + fibmemoization(n-2, dp);
        return dp[n];
    }

    public static int fibTabulation(int n, int dp[]) {
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    public static int catalan(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            ans += catalan(i)*catalan(n-i-1);
        }
        return ans;
    }

    public static int catalanmemo(int n, int dp[]) {//fastest
        if (n == 0 || n == 1) {
            return 1;
        }

        if (dp[n] != -1) {
            return dp[n];
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            ans += catalanmemo(i, dp)*catalanmemo(n-i-1, dp);
        }
        return dp[n] = ans;
    }

    public static int catalanTab(int n) {
    
        int dp[] = new int[n+1];

        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j]*dp[i-j-1];
            }
        }

        return dp[n];
    
    }
    
public static void main(String[] args) {
    // System.out.println(fib(9));
    // int dp[] = new int[90];
    // System.out.println(fibmemoization(8, dp));
    // System.out.println(fibTabulation(9, dp));
    int dp[] = new int[30+1];
    Arrays.fill(dp, -1);
    long start1 = System.nanoTime();
    System.out.println(catalanTab(30));
    long end1 = System.nanoTime();
    System.out.println("Time: " + (end1 - start1) / 1_000_000.0 + " ms");
    
    long start2 = System.nanoTime();
    System.out.println(catalanmemo(30, dp));
    long end2 = System.nanoTime();
    System.out.println("Time: " + (end2 - start2) / 1_000_000.0 + " ms");

    // System.out.println(catalanTab(20));
}
}