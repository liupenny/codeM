## 题目描述   

有一个神奇的盘子，形状为圆形。盘子上面爬着一个大象（视作一个点）。由于现实的扭曲，当大象在盘子某个直径的一端的时候，可以瞬间传送至直径的另一端。现在大象想去盘子上另外一点，问他最少需要移动多少距离。传送不计距离。

## 输入描述:

```
第一行一个整数r（1 <= r <= 1000）代表盘子的半径。
接下来两行两个整点分别代表大象所在的位置和大象目标的位置坐标。保证两个点都在圆内（可能在边界上），圆心在点(0, 0)上。
```

## 输出描述:

```
输出一个实数，代表大象最短需要移动多少距离。和标程相对或绝对相差1e-6都算正确。
```

##  示例1 

### 输入

```
1
0 1
0 -1
```

### 输出

```
0.000000000000
```

## 示例2

### 输入

```
4
3 0
-3 0
```

### 输出

```
2.000000000000
```

## 示例3

### 输入

```
100
-59 76
3 69
```

### 输出

```
62.393909959226
```
## 思路

```
// 这道题一开始想找出正确的表达式，从而确定应有的解，后来发现一直没法确定==
// 所以应该是暴力方法，360度都轮一遍


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
```