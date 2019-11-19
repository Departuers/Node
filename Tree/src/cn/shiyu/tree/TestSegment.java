package cn.shiyu.tree;

public class TestSegment {
    public static void main(String[] args) {
        Integer[] nums = {3, 1, 41, -3, 51, -1, 31, 12};
        SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, Integer::sum);
        System.out.println(segmentTree);
    }
}
