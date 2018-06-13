package cola;

import java.util.Scanner;

/**
 * Created by PennyLiu on 2018/6/13.
 */
public class Solution {

    public static void main(String[] args) {
        caseM2_Answer();

    }

    static void caseM2_Answer() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), m = in.nextInt(), k = in.nextInt();

        long maxE = Long.MIN_VALUE;
        int pos = 0;

        for (int i = 1; i <= k; i++) {
            int a = in.nextInt(), b = in.nextInt();
            long E = 1L * m * a + 1L * (n - m) * b;
            if(E >= maxE)
            {
                maxE = E;
                pos = i;
            }
        }

        // 格式化输出，解决空格问题
        for (int i = 1; i <= k; i++) {
            System.out.printf("%d%c", i == pos ? n : 0, i == k ? '\n' : ' ');
        }
    }
}
