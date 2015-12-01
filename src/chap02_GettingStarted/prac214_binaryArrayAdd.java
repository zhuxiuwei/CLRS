package chap02_GettingStarted;

import java.util.Arrays;

/**
 * 
 * @author xiuzhu
 *
 */
public class prac214_binaryArrayAdd {

	/**
	 * 两个n位二进制整数相加
	 * @param src1
	 * @param src2
	 * @return	相加的结果，一个n+1位的二进制整数
	 */
	public byte[] binaryAdd(byte src1[], byte src2[]) throws UnsupportedOperationException{
		if(!checkSrc(src1, src2)){
			throw new UnsupportedOperationException("参数不合法。");
		}
		
		byte[] res = new byte[src1.length + 1];
		
		boolean jinwei = false;
		
		for(int i = src1.length - 1; i >= 0; i --){
			if(!jinwei){
				res[i + 1] = (byte)(src1[i] ^ src2[i]);
				if(src1[i] == 1 & src2[i] == 1)
					jinwei = true;
			}else{
				res[i + 1] = (byte)((src1[i] ^ src2[i]) ^ 1);
				if(src1[i] == 0 && src2[i] == 0)
					jinwei = false;
			}
		}
		if(jinwei)
			res[0] = 1;
		
		return res;
	}
	
	/**
	 * 检查参数合法：两个数组长度相同，且都是0 1 组成。
	 * @param src1
	 * @param src2
	 * @return true if parameters are valid
	 */
	private boolean checkSrc(byte src1[], byte src2[]){
		if(src1.length != src2.length){
			return false;
		}
		for(int i = 0; i < src1.length; i ++){
			if( (src1[i] != 0 && src1[i] != 1) || (src2[i] != 0 && src2[i] != 1) ){
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		prac214_binaryArrayAdd p = new prac214_binaryArrayAdd();
		
		byte src1_a[] = {1};
		byte src2_a[] = {};
		byte src1_b[] = {1,2,3};
		byte src2_b[] = {3,4,5};
		byte src1_c[] = {1,0,0,1,1,0,1};
		byte src2_c[] = {0,0,1,0,1,0,1};
		byte src1_d[] = {1,1,0,1,1,0,1};
		byte src2_d[] = {1,1,1,0,1,0,0};
		
		//System.out.println(Arrays.toString(p.binaryAdd(src1_a,src2_a)));
		//System.out.println(Arrays.toString(p.binaryAdd(src1_b,src2_b)));
		System.out.println(Arrays.toString(p.binaryAdd(src1_c,src2_c)));
		System.out.println(Arrays.toString(p.binaryAdd(src1_d,src2_d)));
	}

}
