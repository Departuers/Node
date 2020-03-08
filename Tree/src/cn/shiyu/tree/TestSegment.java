package cn.shiyu.tree;

public class TestSegment {
    public static void main(String[] args) {
        Integer[] nums = {3, 1, 41, 3, 51, -1, 31, 12};
        SegmentTree<Integer> segmentTree = new SegmentTree<Integer>(nums, new Merger<Integer>() {
            @Override
            public Integer merge(Integer a, Integer b) {
                return Math.max(a, b);
            }
        });
        System.out.println(segmentTree);
        segmentTree.set(0, 0, nums.length - 1, 2, 23);
        System.out.println(segmentTree.query(0, 2));
    }
}
