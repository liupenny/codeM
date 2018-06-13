package WorldCup;

import java.util.Scanner;

/**
 * Created by PennyLiu on 2018/6/13.
 */

public class Solution {
    public static void main(String[] args) {
        solve();
    }

    public static void solve()
    {
        Scanner in = new Scanner(System.in);
        double[][] probability = new double[17][17];

        // 读取概率数据，角标从1-16
        for (int i = 1; i <= 16; i++) {
            for (int j = 1; j <= 16; j++) {
                probability[i][j] = in.nextDouble();
            }
        }

        //一共进行4轮，角标从1-4
        double[][] dp = new double[17][5];
        for (int i = 1; i <= 16; i++) {
            if(i % 2 == 1)
                dp[i][1] = probability[i][i+1];
            else
                dp[i][1] = probability[i][i-1];
        }

        for (int i = 2; i <= 4; i++) { //i是轮次
            int sub = 1 << i; //1左移几位就是乘以几个2，第一轮是2个人一组，第二轮是4个人，所以 i*2就是当前轮次的小组人数
            for (int j = 1; j <= 16; j++) {   // j是要计算这一轮要对16个人计算分数
                for (int k = 1; k <= 16 ; k++) {   // k是对于每个j要计算概率所需的分组
                    if( (k - 1) / sub == (j - 1) / sub)  //一个大组，比如：1,2,3,4
                    {
                        if((k - 1) / (sub/2) != (j - 1)/(sub/2)) //一个大组内两个小组，第一个小组的每个数乘以第二个小组的每个数，小组的长度是（sub/2）
                        {
                            dp[j][i] += dp[j][i-1] * dp[k][i-1] * probability[j][k]; //概率的计算
                        }
                    }
                }
            }
        }

        System.out.printf("%.10f", dp[1][4]);
        for (int i = 2; i <= 16 ; i++) {
            System.out.printf(" %.10f", dp[i][4]);
        }
    }
}
