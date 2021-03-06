###字典树
什么是Trie
https://mp.weixin.qq.com/s?__biz=MzUyNjQxNjYyMg==&mid=2247484383&idx=1&sn=bc555e97f42b56aa8697ec71d92dc79d&chksm=fa0e6c5ecd79e548a10d493979b58cb3530eab4c83533d09c463da67d870a24a1acddef4dad4&scene=21#wechat_redirect
```
字典                      Trie
如果有n个条目              查询每个条目的时间复杂度
使用树结构                 和一共多少条目无关
查询时间复杂度为O(logn)     时间复杂度为O(W)
如果有100万个条目(2^20)     w为查询单词的长度
log n大概是20

Trie 树，也叫“字典树”。顾名思义，它是一个树形结构。它是一种专门处理字符串匹配的数据结构，用来解决在一组字符串集合中快速查找某个字符串的问题。
此外 Trie 树也称前缀树（因为某节点的后代存在共同的前缀，比如pan是panda的前缀）。
它的key都为字符串，能做到高效查询和插入，时间复杂度为O(k)，k为字符串长度，缺点是如果大量字符串没有共同前缀时很耗内存。
它的核心思想就是通过最大限度地减少无谓的字符串比较，使得查询高效率，即「用空间换时间」，再利用共同前缀来提高查询效率。

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