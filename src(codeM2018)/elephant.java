import java.util.Scanner;

/**
 * Created by PennyLiu on 2018/6/23.
 */

public class elephant{

    public static void main(String[] args) {
        double ans = minDis();
        System.out.println(ans);
    }

    public static double minDis() {
        Scanner in = new Scanner(System.in);
        int r = in.nextInt();
        int[] pos = new int[2];
        int[] des = new int[2];
        for (int i = 0; i < pos.length; i++) {
            pos[i] = in.nextInt();
        }
        for (int i = 0; i < des.length; i++) {
            des[i] = in.nextInt();
        }

        double dis1, dis2, dis;
        if(pos[0] == -des[0] && pos[1] == -des[1])
        {
            return getDis(pos[0],pos[1],des[0],des[1]);
        }
        else
        {
            double a = des[0] - pos[0], b = des[1] - pos[1];
            double c = Math.pow(pos[0],2) + Math.pow(pos[1],2) + Math.pow(des[0],2) + Math.pow(des[1],2) + Math.pow(r,2);


            double x = Math.sqrt( (Math.pow(a,2) * Math.pow(r,2)) / (Math.pow(a,2) + Math.pow(b,2)) );
            double y = Math.sqrt( Math.pow(r,2) - Math.pow(x,2) );
            dis1 = getDis(pos[0], pos[1], x, y) + getDis(des[0], des[1], -x, -y);
            dis2 = getDis(pos[0], pos[1], -x, -y) + getDis(des[0], des[1], x, y);
        }

        dis = Math.min(dis1,dis2);
        return dis;
    }

    public static double getDis(double x1, double y1, double x2, double y2)
    {
        double tmpDisx = x1 - x2, tmpDisy = y1 - y2;
        double tmpDis = Math.pow(tmpDisx,2.0) + Math.pow(tmpDisy,2.0);
        double dis = Math.sqrt(tmpDis);
        return dis;
    }
}