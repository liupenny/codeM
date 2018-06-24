package pre_compeB.elephant;

import java.text.DecimalFormat;
import java.util.Scanner;

// 这道题一开始想找出正确的表达式，从而确定应有的解，后来发现一直没法确定==
// 所以应该是暴力方法，360度都轮一遍

public class elephant {

    public static int n;
    public static int m;

    public static elephant main = new elephant();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int r = scan.nextInt(); // 半径
        //两个坐标点
        double x1 = scan.nextInt();
        double y1 = scan.nextInt();

        double x2 = scan.nextInt();
        double y2 = scan.nextInt();

        // 初步设定最短距离是直线距离，先不要开方，最后再算。
        double min = Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));

        for (double i=0; i<=360; i+= 0.01) {
            double a = Math.PI * i / 180;
            // x,y分别是这个角度对应下，直径与圆的一个交点坐标
            double x = r * Math.cos(a);
            double y = r * Math.sin(a);
            double len1 = Math.sqrt((x1-x)*(x1-x)+(y1-y)*(y1-y));

            //（-x,-y）是对应直径的另一个交点，所以直接带进去算
            double len2 = Math.sqrt((x2+x)*(x2+x)+(y2+y)*(y2+y));
            double len = len1 + len2;
            if (len < min) {
                min = len;
            }
        }

        //对小数进行格式化输出
        DecimalFormat df=new DecimalFormat("0.0000000");
        System.out.println(df.format(min));

    }

}