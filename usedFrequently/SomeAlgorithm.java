package anything;

public class SomeAlgorithm {
	
	
	/*
	 * 实现两个长整数相乘 
	 * 
	 * **/
	public String longNumberMUL(String s1,String s2){
		int c[]=new int[s1.length()+s2.length()-1];
		int[] result=new int[c.length];
		for(int i=0;i<s1.length();i++)
			for(int j=0;j<s2.length();j++){
				c[i+j]+=(int)(s1.charAt(i)-'0')*(int)(s2.charAt(j)-'0');
			}
		result[c.length-1]=c[c.length-1]%10;
		for(int i=c.length-2;i>=0;i--){
			c[i]+=c[i+1]/10;
			result[i]=c[i]%10;
		}
		StringBuffer MULResult=new StringBuffer();
		if(c[0]>10)
			MULResult.append(c[0]/10);
		for(int x:result)
			MULResult.append(x);
		return MULResult.toString();
	}

    
}
