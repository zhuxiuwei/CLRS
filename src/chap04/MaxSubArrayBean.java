package chap04;
/**
 * 最大子数组的返回值Bean - 最大子数组在原数组中的开始（startIndex），结束（endIndex）和这些值的和（sumValue）。
 * @author xiuzhu
 *
 */
public class MaxSubArrayBean {
	public int startIndex;
	public int endIndex;
	public int sumValue;
	
	public String toString(){
		return "startIndex: " + startIndex + ", endIndex: " + endIndex + ", sumValue: " + sumValue;
	}
}
