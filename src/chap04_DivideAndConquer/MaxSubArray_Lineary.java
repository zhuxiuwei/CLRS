package chap04_DivideAndConquer;

import java.util.Arrays;

/**
 * 习题4.1-5： 非递归的、线性时间的最大子数组算法。 151006
 * @author xiuzhu
 * 注意点： 逻辑总是搞不清楚，花费了大半天时间。。。如果手写的话一定有问题！！！！！  
 * 【经验】：
 * 	1. Test case很重要！比如bug1和bug2就是新的test case发现的。
 *	2. 很多时候写算法不要想的太简单想当然，在纸上简单画画（bug2）
 * 【Bug1】: 到达src数组的边界值后，没有清算winPoint和losePoint，导致最大子数组出现在尾巴的时候出现问题。
 * 【Bug2】: 得到了老sum、一轮损失值一轮挽回值后，计算新sum的算法之前想的太简单。
 * 【Bug2'】: 如果有多轮损失值和挽回值，也比较复杂，这块时间非的最长。
 *
 */
public class MaxSubArray_Lineary {

	public MaxSubArrayBean findMaxSubbArray(int[] src){
		if(src.length < 2){
			System.out.println("Error: Array length must be >= 2!");
			return null;
		}
		
		MaxSubArrayBean bean = new MaxSubArrayBean(0,0,src[0]);
		
		int sum = src[0], start = 0, end = 0, winPointStartIndex = 0, total = 0;
		int losePoint = 0, winPoint = 0; 	//“损失值”和“挽回值”
		int lastWinPoint = 0;
		boolean setWinPointStartIndex = true;
		for(int i = 1; i < src.length ; i++){
			
			if(sum < 0 && sum < src[i]){	//针对sum还为负的情况
				start = i;
				end = i;
				sum = src[i];
				if(bean.sumValue < sum)
					bean = new MaxSubArrayBean(start, end, sum);	//update return bean
				continue;
			}
			
			//以下的时候sum>=0了
			if(src[i] < 0){
				setWinPointStartIndex = true;
				if(winPoint > 0){	//各计算了一轮“损失值”和“挽回值”，遇到新的一轮“损失值”时，开始“清算”上一轮的战果
					//【---BUG2 FIX---】清算老sum，结合损失值和挽回值计算新sum的算法的fix。
					if(sum + winPoint + losePoint >=0 ){
						total = sum + losePoint + winPoint;
						end = i - 1;
						if(total >= sum & total >= lastWinPoint){	//total最大,可以扩大最大子数组的范围（end index）。
							sum = total;
							losePoint = src[i];	//losePoint重新记数
							winPoint = 0;	//winPoint重新记数
							if(bean.sumValue <= sum)
								bean = new MaxSubArrayBean(start, end, sum);	//update return bean
						}else if(lastWinPoint >= total & lastWinPoint >= sum){	//lastWinPoint最大，抛弃老的sum，从winPoint开始计算新的最大子数组。
							start = winPointStartIndex;
							sum = lastWinPoint;
							losePoint = src[i];	//losePoint重新记数
							winPoint = 0;	//winPoint重新记数
							if(bean.sumValue <= sum)
								bean = new MaxSubArrayBean(start, end, sum);	//update return bean
						}else{	//依旧是老的sum最大, 当前的winPoint依然有可能有利可图（不是一定的）
							losePoint += src[i];
						}
						
					}else{	//sum小于losePoint，则当前losePoint一定不可用了。
						//从当前winpoint作为最大子数组的开始，开始新的记数 
						sum = winPoint;
						losePoint = src[i];
						winPoint = 0;	
						start = winPointStartIndex;
						end = i - 1;
						if(bean.sumValue <= sum)
							bean = new MaxSubArrayBean(start, end, sum);	//update return bean
					}
					
					
					//【BUG2】以下清算时的算法写的不对，想的太简单。应该在纸上画画，把各种情况想周全。
//					if(sum < winPoint){	// winpoint能自立门户，建立新门户抛弃老sum,开始计入新的子数组。
//						end = i - 1;
//						start = winPointStartIndex;
//						sum = winPoint;
//						losePoint = src[i];
//						winPoint = 0;
//						setWinPointStartIndex = true;
//					}else{	//winpoint不够格“自立门户”，看看winpoint能否超过losePoint,以对老sum做出贡献。
//						if(winPoint + losePoint > 0){	//能做贡献，则把这一段都并入新子数组范畴内。
//							end = i - 1;
//							sum += (winPoint + losePoint);
//							losePoint = src[i];
//							winPoint = 0;
//							setWinPointStartIndex = true;
//						}else{
//							losePoint += src[i];	//计入“损失值”	【---BUG1 FIX---】   -- test case a(7)
//						}
//					}
				}else
					losePoint += src[i];	//计入“损失值”
			}
			else{
				if(losePoint == 0){	//还没有损失值，直接计入Sum
					sum += src[i];		
					end = i;
				}
				else{
					//有损失值，则计入挽回值
					if(setWinPointStartIndex){
						winPointStartIndex = i;
						lastWinPoint = 0;
						setWinPointStartIndex = false;
					}
					lastWinPoint += src[i];
					winPoint += src[i];
				}
				
			}
			
			//【---BUG1 FIX---】  -- test case a(7)
			if(i == src.length - 1){	//151007 bug1 fix。解决在到了数组末尾，还在计算损失值和挽回值，但是没有来得及计入最终SUM的情况。
				if(losePoint != 0 && winPoint != 0){
					//数组结束，做最后一轮的清算
					total = sum + losePoint + winPoint;
					if(total >= sum & total >= lastWinPoint){	//total最大,可以扩大最大子数组的范围（end index）。
						end = i;
						sum = total;
					}else if(sum >= total & sum >= lastWinPoint){	//依旧是老的sum最大
						//do nothing;	//保持现状，等待程序退出。
					}else{	//lastWinPoint最大，抛弃老的sum，从winPoint开始计算新的最大子数组。
						end = i;
						sum = lastWinPoint;
						start = winPointStartIndex;
					}
					if(bean.sumValue < sum)
						bean = new MaxSubArrayBean(start, end, sum);	//update return bean
				}
			}
		}
		
		System.out.println(bean);
		return bean;
	}
	
	public static void main(String[] args) {
		MaxSubArray_Lineary m = new MaxSubArray_Lineary();
		for(int[] testData: TestData.MaxSubArrayTestData()){
			System.out.println("Test: " + Arrays.toString(testData));
			m.findMaxSubbArray(testData);
		}
	}

}
