package pre_compeB.meeting;

import java.util.*;

/**
 * Created by PennyLiu on 2018/6/23.
 */

public class meeting{
    // 把信息记录作为数据类型，先输入所有的记录，然后挨个比较
    class roomState implements Comparable<roomState>{
        int x,y;
        String time;
        boolean isOn; //0表示进入，1表示离开

        public int compareTo(roomState p)
        {
            return time.compareTo(p.time);
        }

    }

    List<roomState> list;
    int roomRet[][];
    int n,m;

    public static void main(String[] args) {
        meeting s = new meeting();
        s.meet();
    }

    public void meet()
    {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        int k = in.nextInt();
        list = new ArrayList<>();
        roomState temp = new roomState();

        for (int i = 0; i < k; i++) {
            temp.x = in.nextInt();
            temp.y = in.nextInt();
            int z = in.nextInt();
            temp.time = in.nextLine();
            temp.isOn = ( z == 0 ? true : false );
            list.add(temp);
            temp = new roomState();
        }

        Collections.sort(list);
        int max = 0, count = 0, pos = 0;
        roomRet = new int[n+1][m+1];

        for (int i = 0; i < k; i++)
        {
            roomState tmp = list.get(i);
            if(tmp.isOn)
            {
                // 因为会有很多工程师进入会议室，此时就一直是开着的状态
                if(1 == ++roomRet[tmp.x][tmp.y])
                    count++;
            }
            else
            {
                if(0 == --roomRet[tmp.x][tmp.y])
                    count--;
            }
            if(count >= max)
            {
                max = count;
                pos = i;
            }
        }

        // 这样写会导致后面给roomRet赋值的时候，一整列同时变化。
        int[] ss = new int[m+1];
        Arrays.fill(roomRet, ss);
        // Arrays.fill(roomRet, new int[m+1]);

        // 这个二重循环更新值的时候就不会有问题
        for (int i = 0; i < n + 1 ; i++) {
            for (int j = 0; j < m + 1; j++) {
                roomRet[i][j] = 0;
            }
        }

        for (int i = 0; i <= pos; i++) {
            roomState tmp = list.get(i);
            if(tmp.isOn == true)
                ++roomRet[tmp.x][tmp.y];
            else
                --roomRet[tmp.x][tmp.y];
        }

        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < m+1; j++) {
                if(roomRet[i][j] == 0)
                    System.out.print("0");
                else
                    System.out.print("1");
            }
            System.out.println();
        }
    }

}