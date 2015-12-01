package chap04_DivideAndConquer;

import java.util.Arrays;

/***
 * 股票最大收益问题
 * @author xiuzhu 151008
 * 比较顺利。写自己的方法(getMaxProfit_MyWay)时,有两处马虎变量写错了。总体顺利。
 */
public class Text4_1_StockMaxProfit {

	/**
	 * Use my own way. O(n)
	 * 思路：从后往前扫描。
	 * @param prices Stock prices
	 * @return Max stock profit
	 */
	public StockMaxProfitBean getMaxProfit_MyWay(int[] prices){
		//assume the prices length is at least 3.
		if(prices.length < 3){
			System.out.println("prices length must >= 3");
			return null;
		}
		
		StockMaxProfitBean sbean = new StockMaxProfitBean();
		int lastMaxPricelDay = prices.length - 1;
		int lastMaxPricelDayPrice = prices[prices.length - 1];
		int lastMaxProfit = Integer.MIN_VALUE;
		int lastMostProfitableDay = -1;
		int lastMostProfitableDayPrice = 0;
		
		for (int i = prices.length - 2; i >= 0; i--) {
			int tempProfit = lastMaxPricelDayPrice - prices[i];
			if(tempProfit > lastMaxProfit){
				lastMostProfitableDay = i;
				lastMostProfitableDayPrice = prices[i];
				lastMaxProfit = tempProfit;
				sbean.sellDay = lastMaxPricelDay;
				sbean.sellDayPrice = lastMaxPricelDayPrice;
				sbean.maxProfit = lastMaxProfit;
			}
			if(prices[i] > lastMaxPricelDayPrice){
				lastMaxPricelDayPrice = prices[i];
				lastMaxPricelDay = i;
			}
		}
		sbean.buyDay = lastMostProfitableDay;
		sbean.buyDayPrice = lastMostProfitableDayPrice;
		return sbean;
	}
	
	/**
	 * Use MaxSubArray introduced in book. O(n*lgn)
	 * @param prices Stock prices
	 * @return Max stock profit
	 */
	public StockMaxProfitBean getMaxProfit_MaxSubArray(int[] prices){
		//assume the prices length is at least 3.
		if(prices.length < 3){
			System.out.println("prices length must >= 3");
			return null;
		}
		
		//transform origin array to stockChangeArray. Then leverage MaxSubArray algorithm over the stockChangeArray to get the max profit.
		int[] stockChangeArray = new int[prices.length - 1];
		for (int i = 0; i < stockChangeArray.length; i++) {
			stockChangeArray[i] =  prices[i+1] - prices[i];
		}
		MaxSubArray_Recursive m = new MaxSubArray_Recursive();
		MaxSubArrayBean mbean = m.findMaxSubbArray(stockChangeArray);
		StockMaxProfitBean sbean = new StockMaxProfitBean();
		sbean.buyDay = mbean.startIndex;
		sbean.buyDayPrice = prices[sbean.buyDay];
		sbean.sellDay = mbean.endIndex + 1;
		sbean.sellDayPrice = prices[sbean.sellDay];
		sbean.maxProfit = mbean.sumValue;
		return sbean;
	}
	
	public static void main(String[] args) {
		Text4_1_StockMaxProfit t = new Text4_1_StockMaxProfit();
		int[] a1 = {100,113,110,85,105,102,86,63,81,101,94,106,101,79,94,90,97};	//price list
		int[] a2 = {10,11,7,10,6};
		int[] a3 = {100,80,79,79};
		System.out.println("prices: " + Arrays.toString(a1));
		System.out.println(t.getMaxProfit_MaxSubArray(a1));
		System.out.println(t.getMaxProfit_MyWay(a1));
		System.out.println("prices: " + Arrays.toString(a2));
		System.out.println(t.getMaxProfit_MaxSubArray(a2));
		System.out.println(t.getMaxProfit_MyWay(a2));
		System.out.println("prices: " + Arrays.toString(a3));
		System.out.println(t.getMaxProfit_MaxSubArray(a3));
		System.out.println(t.getMaxProfit_MyWay(a3));
	}

}

class StockMaxProfitBean{
	int buyDay;	//start from 0.
	int buyDayPrice;
	int sellDay;
	int sellDayPrice;
	int maxProfit;
	
	public String toString(){
		return "buyDay: " + buyDay + " buyDayPrice: " + buyDayPrice + " sellDay: " + sellDay + " sellDayPrice: " + sellDayPrice + " maxProfit: " + maxProfit;
	}
}