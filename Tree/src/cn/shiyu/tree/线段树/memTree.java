package cn.shiyu.tree.线段树;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

import static java.lang.System.in;

/**
 * 主席树
 * https://blog.csdn.net/chenxiaoran666/article/details/81501461
 * https://blog.csdn.net/WilliamSun0122/article/details/77885781?depth_1-utm_source=distribute.pc_relevant.none-task&utm_source=distribute.pc_relevant.none-task
 *
 * 节点式:还不错https://blog.csdn.net/a_forever_dream/article/details/80450549
 * 好文:https://blog.csdn.net/zearot/article/details/48299459
 * 节点式: https://blog.csdn.net/Frocean/article/details/80888718?depth_1-utm_source=distribute.wap_relevant.none-task&utm_source=distribute.wap_relevant.none-task
 * 数组式:https://blog.csdn.net/ModestCoder_/article/details/90107874
 */
public class memTree {
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
    static int[] a = new int[100004];
    static int[] b = new int[100004];
    static int[] lc = new int[100004*20];
    static int[] rc = new int[100004*20];
    static int[] sum = new int[100004*20];
    static int[] rt = new int[100004*20];
    static int sz = 0, p = 0;

    static void build(int rt, int l, int r) {
        rt = ++sz;
        sum[rt] = 0;
        if (l == r) return;
        int mid = (l + r) >> 1;
        build(lc[rt], l, mid);
        build(rc[rt], mid + 1, r);
    }

    static int update(int o, int l, int r) {
        int oo = ++sz;
        lc[oo] = lc[o];
        rc[oo] = rc[o];
        sum[oo] = sum[o] + 1;
        if (l == r) return oo;
        int mid = (l + r) >> 1;
        if (mid >= p) lc[oo] = update(lc[oo], l, mid);
        else rc[oo] = update(rc[oo], mid + 1, r);
        return oo;
    }

    static int query(int u, int v, int l, int r, int k) {
        int mid = (l + r) >> 1;
        int x = sum[lc[v]] - sum[lc[u]];
        if (l == r) return l;
        if (x >= k) return query(lc[u], lc[v], l, mid, k);
        else return query(rc[u], rc[v], mid + 1, r, k - x);
    }

    public static void main(String[] args) throws IOException {
        TreeSet<Integer> set = new TreeSet<Integer>();
        int n, m;
        n = nextInt();
        m = nextInt();
        for (int i = 1; i <= n; i++) {
            a[i] = nextInt();
            set.add(a[i]);
        }
        int ee = 0;
        for (Integer w : set) {
            b[++ee] = w;
        }//离散化放到b数组里面
        int q = set.size();
        build(rt[0], 1, q);
        for (int i = 1; i <= n; i++) {
            p = Arrays.binarySearch(b, 1, 1 + set.size(), a[i]);
            rt[i] = update(rt[i - 1], 1, q);
        }
        int l, r, k;
        for (int i = 0; i < m; i++) {
            l = nextInt();
            r = nextInt();
            k = nextInt();
            int c = query(rt[l - 1], rt[r], 1, q, k);
            System.out.println(b[c]);
        }
    }
}
