package cn.shiyu.tree.线段树;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeSet;

import static java.lang.System.in;

/**
 * 主席树
 * 非常牛逼！！！
 * https://blog.csdn.net/bestFy/article/details/78650360
 * https://www.luogu.com.cn/record/18131499
 */
public class 主席树 {
    static int[] a = new int[200010];
    static int[] b = new int[200010];
    static int[] T = new int[200010];
    static int[] sum = new int[200010 * 32];
    static int[] L = new int[200010 * 32];
    static int[] R = new int[200010 * 32];
    static int tot = 0;

    static int build(int l, int r) {
        int rt = ++tot;
        int mid = (l + r) >> 1;
        if (l < r) {
            L[rt] = build(l, mid);
            R[rt] = build(mid + 1, r);
        }
        return rt;
    }

    static int update(int pre, int l, int r, int x) {
        int rt = ++tot;
        L[rt] = L[pre];
        R[rt] = R[pre];
        sum[rt] = sum[pre] + 1;
        int mid = (l + r) >> 1;
        if (l < r) {
            if (x <= mid) {
                L[rt] = update(L[pre], l, mid, x);
            } else {
                R[rt] = update(R[pre], mid + 1, r, x);
            }
        }
        return rt;
    }

    static int query(int u, int v, int l, int r, int k) {
        if (l == r) return l;
        int x = sum[L[v]] - sum[L[u]];
        int mid = (l + r) >> 1;
        if (x >= k) return query(L[u], L[v], l, mid, k);
        else return query(R[u], R[v], mid + 1, r, k - x);
    }

    static BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    static StringTokenizer tokenizer = new StringTokenizer("");

    static String nextLine() throws IOException {// 读取下一行字符串
        return reader.readLine();
    }

    static String next() throws IOException {// 读取下一个字符串
        while (!tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {// 读取下一个int型数值
        return Integer.parseInt(next());
    }

    static double nextDouble() throws IOException {// 读取下一个double型数值
        return Double.parseDouble(next());
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(in);
        int n = nextInt();
        int q = nextInt();
        TreeSet<Integer> t = new TreeSet<Integer>();
        for (int i = 1; i <= n; i++) {
            a[i] = nextInt();
            t.add(a[i]);
        }
        int c = 1;
        for (Integer w : t) {
            b[c++] = w;
        }
        int m = t.size();
        T[0] = build(1, m);
        for (int i = 1; i <= n; i++) {
            a[i] = Arrays.binarySearch(b, 1, 1 + m, a[i]);
            T[i] = update(T[i - 1], 1, m, a[i]);
        }
        int x, y, z, en;
        while (q-- != 0) {
            x = nextInt();
            y = nextInt();
            z = nextInt();
            en = query(T[x - 1], T[y], 1, m, z);
            System.out.println(b[en]);
        }
    }
}