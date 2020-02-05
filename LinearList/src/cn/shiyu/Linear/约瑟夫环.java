package cn.shiyu.Linear;

/**
 * http://ddrv.cn/a/80843
 * “约瑟夫环”是一个数学的应用问题：一群猴子排成一圈
 * 按1,2,…,n依次编号。然后从第1只开始数，数到第m只,
 * 把它踢出圈，从它后面再开始数， 再数到第m只，
 * 在把它踢出去…，如此不停的进行下去，
 * 直到最后只剩下一只猴子为止，那只猴子就叫做大王。
 * 要求编程模拟此过程，输入m、n, 输出最后那个大王的编号。
 * 如：n=6，m=3
 */
public class 约瑟夫环 {
    public static void main(String[] args) {
        YueSeFu(6,3);
    }

    /**
     * @param n 猴子数量
     * @param m 每次数几次,
     */
    public static void YueSeFu(int n, int m) {
        int r = 3;
        for (int i = 2; i <= n; i++) {
            r = (r + m) % i;
        }
        System.out.println(r + 1);
    }
}
