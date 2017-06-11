第11章习题答案
=
#### 11.1-1 考虑由一个长度为m的直接寻址表T表示的动态集合S。给出一个查找S的最大元素的算法过程。所给的过程在最坏情况下的运行时间是什么？  
答：从数组（直接寻址表T[0...m-1]）中最后一个元素开始，向前查找第一个不为空的。最坏情况是数组里只有第一个位置存储了最小的元素，O(n)。  

#### 11.1-2 位向量是一种仅包含0和1的数组。长度为m的位向量所占空间要比包含m个指针的数组少得多。
请说明如何用一个位向量来表示一个包含不同元素（无卫星数据）的动态集合。字典操作的运行时间应该是O(1)。  
答：0表示对应index的元素不存在，1表示存在。适合用来表示不包含卫星数据的数字元素。  

#### 11.1-3 说明如何实现一个直接寻址表，使各元素的关键字都不必相同，且各元素可以有卫星数据。所有三种字典操作(INSERT,DELETE和SEARCH)的时间应为O(1)。  
答：代码见[DirectAddressTable](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap11_HashTables/DirectAddressTable.java)  
有__三个注意点__，有点意思。  

#### 11.3-1 假设我们希望查找一个长度为n的链表，其中每一个元素都包含一个关键字k和一个散列值h(k)。每一个关键字都是长字符串。在表中查找具有给定关键字的元素时，如何利用各元素中的散列值。  
答：对链表中每一个元素，先比较该元素与待查关键字的散列值。若一样，在比较字符串值。__（感觉和Java的HashSet处理思路一样！先比较hashcode，再比较equals）__  

#### 11.3-2 假设一个长度为r的字符串被散列到m个槽中，方法是将其视为一个以128为基数的数，然后应用除法方法。很容易把数m表示为一个32位的机器字，但对长度为r的字符串，
由于它被当做以128为基数的数来处理，就要占用若干个机器字。假设应用除法来计算一个字符串的散列值，那么如何才能除了该串本身占用的空间外，只利用常数个机器字？  
答：其实考察的是，如何快速高效地生成string的hashcode。
原始算法为：字符串第i位的散列码=(ASCII码乘上128^i)，最终散列码为各位的散列码加和。这里可能有整数溢出。以下2种处理方案：  
1. 让最终散列码自由溢出；  这种肯定__低效__，如果字符串很长的话，那么超过一定长度时溢出是一定的，这样多次128乘法是没有必要的。  
2. 让每个char的散列码都mod(m)，最后加和还mod(m)。  然后最后结果超过m时自由溢出。感觉也__不好__。  
3. 采用__字符串分组__的思想。以128为基数，当字符串长度达到一定长度时一定会导致32位整数溢出，设这个长度为MAX。那么对于一个给定的字符串，当字符串长度不超过MAX时，就按照各位正常的基数加和来计算哈希值；
当字符串长度超过了MAX时，把__字符串分组__，每组视为一个字符串，一组的ASCII码为这组内所有字符串ASCII码之和。  

#### 11.3-3 考虑除法方法的另一种版本，其中h(k)=k mod m，m=2^p-1，k为按基数2^p表示的字符串。
证明：如果串x可由串y通过其自身的置换排列导出，则x和y具有相同的散列值。给出一个应用例子，其中这一特性在散列函数中是不希望出现的。
答：不证明了。__这个题目本身挺值得注意的。说明散列值的设计是很需要注意的__。  

#### 11.3-4 考虑一个大小为m=1000的散列表和一个对应的散列函数h(k)=floor(m(kA mod 1))，A=(sqrt(5)-1)/2。计算关键字61、62、63、64和65被映射到的位置。  
答：A=0.6180339887  
61: 700  
62: 318  
63: 936  
64: 554  
65: 172  

#### 11.4-1 开放寻址法。Consider inserting the keys 10,22,31,4,15,28,17,88,59 into a hash table of length m=11 using open addressing with the auxiliary hash function h′(k)=k. Illustrate the result of inserting these keys using linear probing, using quadratic probing with c1=1 and c2=3, and using double hashing h1(k)=k and h2(k)=1+(kmod(m+1)).  
答：见图：  ![](https://github.com/zhuxiuwei/CLRS/blob/master/Images/11.4-1.png)  

#### 11.4-2 支持删除的开放定址哈希 。Write pseudocode for HASH-DELETE as outlined in the text, and modify HASHINSERT to handle the special value DELETED.  
答：代码见[HashTable_OpenAddressing](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap11_HashTables/HashTable_OpenAddressing.java)  