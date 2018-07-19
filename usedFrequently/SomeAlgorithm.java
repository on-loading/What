package anything;

import java.util.ArrayList;

public class SomeAlgorithm {
	
	
	/**  Multiply two long number ,with the return type of String
	 *   @param s1 Input as a string
	 *   @param s2 Input as a string
	 *   
	 **/
	
	public String longNumberMUL(String s1,String s2){
		long c[]=new long[s1.length()+s2.length()-1];
		long[] result=new long[c.length];
		for(int i=0;i<s1.length();i++)   //两个乘数每位互相两两相乘，结果累加后保存到c[]中
			for(int j=0;j<s2.length();j++){
				c[i+j]+=(int)(s1.charAt(i)-'0')*(int)(s2.charAt(j)-'0');
			}
		result[c.length-1]=c[c.length-1]%10;
		for(int i=c.length-2;i>=0;i--){
			c[i]+=c[i+1]/10;    //向前一位的进位
			result[i]=c[i]%10;   //取余的当前位的最终结果
		}
		StringBuffer MULResult=new StringBuffer();
		if(c[0]>10)  //判断是否还需向前再进一位
			MULResult.append(c[0]/10);
		for(long x:result)
			MULResult.append(x);
		return MULResult.toString();
	}

    public boolean oddNumber(int n){
    	//ArrayList<Integer> numArrayList=new ArrayList<Integer>();
    	if(n==1)
    		return false;
    	for(int i=2;i<n;i++){
    			if(n%i==0)
    				return false;
    		
    	}
    	return true;
    }
    public void  testOdd(int n) {
    	int count=0;
    	for(int i=1;i<=n;i++){
    		if(oddNumber(i)){
    			System.out.print(i+" ");
    			count++;
    			if(count!=0 && count%10==0)
    				System.out.print("\n");
    		}
    		
    	}
		
	}
    
    /**  Match the substring with the KMP algorithm.If a match is found,return the start position in the original string,else return -1 
     * @param origin   The original String
     * @param target   The substring that wants to match
     * 
     * */
    
    public int subString_KMP(String origin,String target){
    	int i=0,j=0;
    	int[] next=getNextArray(target);
    	int len=next.length;
    	while(i<origin.length() && j<target.length()){
    		if(origin.charAt(i)==target.charAt(j)){
    			i++;
    			j++;
    			if(j==len){  //如果最后一个字符也匹配成功，即找到字串
    				return i-len;   //返回字串开始的位置
    			}
    		}
    		else{
    			if(j==0)  
    				i++;
    			j=next[j];  //根据next[]数组确定下一次开始比较的位置
    		}
    	}
    	
    	return -1;
    }
    
    /** Get the nextArray of a string for KMP
     * */
    public int[] getNextArray(String s){
    	/* 主要思路是根据next[i-1]和s[i]、s[next[i-1]]得出next[i]
    	 * 
    	 * */
    	int len=s.length();
    	int[] next=new int[len];
    	for(int i=2;i<len;i++){   //next[0]和next[1]为0，所以从i=2开始遍历
    		if(s.charAt(i-1)==s.charAt(next[i-1]))   //如果s[i]=s[next[i-1]]，在原有的基础上加1
    			next[i]=next[i-1]+1;
    	}
    	
    	return next;
    }
    
    /** Longest common String
     * 
     * */
    public int LCS(String s1,String s2){
    	if(s1.charAt(0)==s2.charAt(0)){
    		//return Math.max(LCS(s1.substring(1),), LCS())
    	}
    	return 0;



    }
	/** 实现任意10进制到任意进制的整数转换，这里只做到20进制之前
	 *  @param origin 原10进制数
	 *  @param target 目标进制
	 * */
	public  String conversionNum(int origin,int target){
		if(origin==0)
			return "0";
		String sym="0123456789ABCDEFGHIJ";    //加长这个sym就能做到20以上的进制转换。
		int negative=0;
		if(origin<0) {
			negative = 1;
			origin=Math.abs(origin);
		}
		StringBuffer buffer=new StringBuffer();
		while(origin!=0){
			int temp=origin%target;
			buffer.append(sym.charAt(temp));
			origin/=target;
		}
		if(negative==1)
			buffer.append('-');
		return buffer.reverse().toString();
	}

}
