###字典树
什么是Trie
```
字典                      Trie
如果有n个条目              查询每个条目的时间复杂度
使用树结构                 和一共多少条目无关
查询时间复杂度为O(logn)     时间复杂度为O(W)
如果有100万个条目(2^20)     w为查询单词的长度
logn大概是20

视同于，书籍单词统计，有什么单词，有几个

推演思路->
语言情景不同
每个节点有26个指向下个节点的指针，
Node{
char c
Node next[26]
}

每个节点都有若干指向下一个节点的指针

Node{
char c
Map<char,Node> next
}
一个字符到另一个节点的映射

其实来到节点之前就知道要找什么了
查询，直接找就行

Node{
Map<char,Node> next
}
但在英语中
一个单词可能是另一个单词的前缀
比如cat cats复数,保存一个boolean判断是不是单词结尾

最终结论
Node{
boolean isWord
Map<char,Node> next
}

压缩字典树