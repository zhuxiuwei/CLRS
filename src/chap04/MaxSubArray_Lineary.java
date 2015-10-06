package chap04;
/**
 * 习题4.1-5： 非递归的、线性时间的最大子数组算法。 151006
 * @author xiuzhu
 * 注意点： 思路不清晰，写了差不多1小时才运行正确。如果手写的话一定有问题！！！！！
 *
 */
public class MaxSubArray_Lineary {

	public MaxSubArrayBean findMaxSubbArray(int[] src){
		MaxSubArrayBean rest = new MaxSubArrayBean(-1,-1,-1);
		
		if(src.length < 2){
			System.out.println("Error: Array length must be >= 2!");
			return null;
		}
		
		int sum = src[0], start = 0, end = 0, winPointStartIndex = 0;
		int losePoint = 0, winPoint = 0; 	//“损失值”和“挽回值”
		boolean setWinPointStartIndex = true;
		for(int i = 1; i < src.length ; i++){
			if(sum < 0 && sum < src[i]){	//针对sum还为负的情况
				start = i;
				end = i;
				sum = src[i];
				continue;
			}
			
			//以下的时候sum>=0了
			if(src[i] < 0){
				if(winPoint > 0){	//各计算了一轮“损失值”和“挽回值”，遇到新的一轮“损失值”时，开始“清算”上一轮的战果、
					if(winPoint > sum){	//winpoint部分大于老Sum，建立新门户抛弃老sum,开始计入新的子数组。
						end = i - 1;
						start = winPointStartIndex;
						sum = winPoint;
						losePoint = src[i];
						winPoint = 0;
						setWinPointStartIndex = true;
					}else{	//winpoint不够格“自立门户”，看看winpoint能否超过losePoint,以对老sum做出贡献。
						if(winPoint + losePoint > 0){	//能做贡献，则把这一段都并入新子数组范畴内。
							end = i - 1;
							sum += (winPoint + losePoint);
							losePoint = src[i];
							winPoint = 0;
							setWinPointStartIndex = true;
						}
					}
				}else
					losePoint += src[i];	//计入“损失值”
			}
			else{
				if(losePoint == 0){	//还没有挽回值，直接计入Sum
					sum += src[i];		
					end = i;
				}
				else{
					//有损失值值，则计入挽回值
					if(setWinPointStartIndex){
						winPointStartIndex = i;
						setWinPointStartIndex = false;
					}
					winPoint += src[i];
				}
				
			}
		}
		
		rest = new MaxSubArrayBean(start, end, sum);
		System.out.println(rest);
		return rest;
	}
	
	public static void main(String[] args) {
		int[] a1 = {};
		int[] a2 = {1};
		int[] a3 = {1, -4, 3, -4};
		int[] a4 = {13,-3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
		int[] a5 = {-5, -3, -1, -1, -1};
		int[] a6 = {-1, -2, -3};
		
		MaxSubArray_Lineary m = new MaxSubArray_Lineary();
		m.findMaxSubbArray(a1);
		m.findMaxSubbArray(a2);
		m.findMaxSubbArray(a3);
		m.findMaxSubbArray(a4);
		m.findMaxSubbArray(a5);
		m.findMaxSubbArray(a6);
	}

}
