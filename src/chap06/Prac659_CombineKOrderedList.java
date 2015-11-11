package chap06;
/**
 * 合并k个有序链表为1个有序链表，k个连边一共有n个元素，则时间复杂度O(n * lgk)
 */
import java.util.LinkedList;
import java.util.List;

public class Prac659_CombineKOrderedList {

	public static void main(String[] args) {
		List<Integer> a = new LinkedList<Integer>();
		a.add(1);
		a.add(2);
		System.out.println(a.get(0));
	}

}