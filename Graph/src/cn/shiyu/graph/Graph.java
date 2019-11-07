package cn.shiyu.graph;

import java.util.Set;
import java.util.TreeMap;

public class Graph {
    public static void main(String[] args) {
        TreeMap<Integer,String> s=new TreeMap<>();
        s.put(2,"kjsdh");
        s.put(4,"szjsdh");
        s.put(7,"hjsdh");
        s.put(8,"nbjsdh");
        s.put(56,"xjsdh");
        s.put(3,"zjsdh");
        Set<Integer> integers = s.keySet();
        for (Integer integer : integers) {
            System.out.println(integer+"  "+s.get(integer));
        }
    }
}