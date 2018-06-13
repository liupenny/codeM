package score;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by PennyLiu on 2018/6/13.
 */
public class Solution {
    public static void main(String[] args) {
        Solution t = new Solution();
        t.caseM4_Answer();
    }

    //因为最后要对所有人分数进行排序，所以定义一个结构体比较方便
    class Person implements Comparable<Person>{
        int index;  //初始标号
        double score;  //每一个c对应下的成绩

        // 从大到小排序
        @Override
        public int compareTo(Person p)
        {
            if (Double.doubleToLongBits(this.score) > Double.doubleToLongBits(p.score))
                return -1;
            else if (Double.doubleToLongBits(this.score) == Double.doubleToLongBits(p.score))
                return 0;
            else
                return 1;
        }
    }

    public void caseM4_Answer()
    {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), m = in.nextInt(), k = in.nextInt(), C = in.nextInt();

        int[] weight = new int[m]; //m轮比赛的权重
        long  weightSum = 0;  //m轮比赛的权重和
        for (int i = 0; i < m; i++) {
            weight[i] = in.nextInt();
            weightSum += weight[i];
        }

        int[][] score = new int[n][m];
        int lostN = 0, lostM = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                score[i][j] = in.nextInt();
                if(score[i][j] == -1)
                {
                    lostN = i;  //缺失的数据是第i个人的
                    lostM = j;  //缺失的数据是第j轮比赛的
                }
            }
        }

        Person[] people = new Person[n];  //对象数组中每个对象也要初始化
        for (int i = 0; i < people.length; i++) {
            people[i] = new Person();
        }

        int[] ans = new int[n];  //每一轮比赛算完的结果
        Arrays.fill(ans, -1);

        for (int c = 0; c <= C; c++) {  //从0到C模拟缺失的分数
            for (int i = 0; i < n; i++) { //重新给当前的人赋值
                people[i].index = i;
                people[i].score = 0.0;
            }

            score[lostN][lostM] = c;

            for (int j = 0; j < m; j++) { // 当前C下每个人的总分
                int max = -1;
                for (int i = 0; i < n; i++) {
                    max = Math.max(max, score[i][j]); //每一轮的最高分
                }
                for (int i = 0; i < n; i++) {
                    if(max > 0) //当max>0的时候计算
                    {
                        people[i].score += 1.0 * score[i][j] / max * (1.0 * weight[j] / weightSum);
                    }
                }
            }

            Arrays.sort(people);  //按照这一个C的情况进行分数排序

            for (int i = 0; i < n; i++) {
                if(i < k)
                {
                    if(ans[people[i].index] == -1)
                    {
                        ans[people[i].index] = 1;
                    }
                    else if(ans[people[i].index] == 1)
                    {
                        ans[people[i].index] = 1;
                    }
                    else
                    {
                        ans[people[i].index] = 3;
                    }
                }
                else
                {
                    if(ans[people[i].index] == -1)
                    {
                        ans[people[i].index] = 2;
                    }
                    else if(ans[people[i].index] == 1)
                    {
                        ans[people[i].index] = 3;
                    }
                }
            }

            // 只对成绩在k周围的比较一下，把所有成绩相同，都是第k名的结果设为3
            if(k < n && people[k-1].score == people[k].score)
            {
                for (int i = 0; i < n; i++) {
                    if (people[i].score == people[k-1].score)
                        ans[people[i].index] = 3;
                }
            }
        }
        // 打印结果
        for (int i = 0; i < n; i++) {
            System.out.println(ans[i]);
        }
    }
}
