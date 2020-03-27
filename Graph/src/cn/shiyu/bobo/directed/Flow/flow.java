package cn.shiyu.bobo.directed.Flow;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

//竞赛版本最大流
public class flow {
    private static int n, m, s, t, maxflow;
    private static TreeMap<Integer, Integer>[] graph;

    public static void main(String[] args) throws FileNotFoundException {

        Scanner sc = new Scanner(new File("g233.txt"));
        n = sc.nextInt();
        m = sc.nextInt();
        s = sc.nextInt();
        t = sc.nextInt();
        flow flow = new flow();
        int a, b, c;
        graph = new TreeMap[n + 1];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new TreeMap<Integer, Integer>();
        }
        for (int i = 0; i < m; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            graph[a].put(b, c);
            graph[b].put(a,0);
        }
        while (true) {
            ArrayList<Integer> augPath = getAugPath();
            if (augPath.size() == 0) break;
            int f = Integer.MAX_VALUE;


            for (int i = 1; i < augPath.size(); i++) {
                int v = augPath.get(i - 1);
                int w = augPath.get(i);
                f = Math.min(f, graph[v].get(w));
            }

            maxflow += f;

            for (int i = 1; i < augPath.size(); i++) {
                int v = augPath.get(i - 1);
                int w = augPath.get(i);

                graph[v].put(w, graph[v].get(w) - f);
                graph[w].put(v, graph[w].get(v) == null ? f : graph[w].get(v) + f);

            }
        }
        System.out.println(maxflow);
    }

    private static ArrayList<Integer> getAugPath() {
        Queue<Integer> q = new LinkedList<Integer>();
        int[] pre = new int[n + 1];
        Arrays.fill(pre, -1);

        q.add(s);
        pre[s] = s;

        while (!q.isEmpty()) {
            Integer cur = q.poll();
            if (cur == t) break;
            for (Integer next : graph[cur].keySet()) {
                if (pre[next] == -1 && graph[cur].get(next) > 0) {
                    pre[next] = cur;
                    q.add(next);
                }
            }
        }
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (pre[t] == -1) return res;
        int cur = t;
        while (cur != s) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);
        return res;
    }


}
