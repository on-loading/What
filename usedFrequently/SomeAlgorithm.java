package anything;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import sun.awt.image.ImageWatched;
import sun.reflect.generics.tree.Tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

//二叉树节点
class TreeNode{
	int val;
	TreeNode left;
	TreeNode right;
	public TreeNode(int val){this.val=val;}
}

//链表节点
class LinkNode {
	int var;
	LinkNode next;

}


public class SomeAlgorithm {

	public SomeAlgorithm(){

	}
	public SomeAlgorithm(String message){
		System.out.println("SomeAlgorithm被实例化，信息："+message);
	}


	/**  任意长整数相乘
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

	/**
	 * 输出链表
	 * @param root 根节点
	 * */
	public void outputLink(LinkNode root){
		if(root==null)
			System.out.println("empty");
		else {
			System.out.print(root.var);
			LinkNode p=root.next;
			while(p!=null){
				System.out.print("->"+p.var);
				p=p.next;
			}
		}
		System.out.println();
	}
	/**
	 * 链表反转算法
	 * */
	public LinkNode linkReverse(LinkNode head){
		LinkNode after =head.next;
		if(after ==null)
			return head;
		LinkNode pre=null;
		while(after !=null) {
			head.next = pre;
			pre= head;
			head = after;
			after = after.next;
		}
		head.next=pre;
		return head;
	}



	/**
	 * 欧几里得算法求最大公因子
	 * 辗转相除法，用大的去除以小的，然后把除数和余数继续操作，直到余数为0
	 * */
	public  int euclideanAlg(int num1,int num2){
		if(num1<num2) {
			int temp = num1;
			num1 = num2;
			num2 = temp;
		}
		while(num1%num2!=0){
			int remainder=num1%num2;
			num1=num2;
			num2=remainder;
		}
		return num2;
	}

	/**二叉树三种非递归遍历*/

	/**
	 * 非递归前序遍历
	 * 先把root进栈，push
	 * 对于栈顶元素，先pop并visited，然后把右节点进栈，然后左节点进栈
	 * 此时左节点成为栈顶元素，继续pop并visited进行上述循环操作，直到栈为空
	 * */
	public LinkedList<Integer> preorderTraverse(TreeNode root){
		if(root==null)
			return null;
		LinkedList<Integer> resultOrder=new LinkedList<>();  //遍历的结果保存在一个list里面，也可以用一个queue直接保存节点
		Stack<TreeNode> stack=new Stack<>();
		stack.push(root);
		while(!stack.isEmpty() ){
			TreeNode top=stack.pop();//弹出栈顶元素
			resultOrder.add(top.val);//visited
			if(top.right!=null)//右节点进栈
				stack.push(top.right);
			if(top.left!=null)//左节点进栈
				stack.push(top.left);
		}
		return resultOrder;
	}

	/**
	 * 中序非递归遍历二叉树
	 * 把当前节点进栈，令左节点成为当前节点，循环直到左节点为null；
	 * 此时把栈顶元素弹出，是最左端的叶节点，visited，然后对此节点的右节点继续上述操作；
	 * 循环此过程直到栈为空
	 * */
	public LinkedList<Integer> inorderTraverse(TreeNode root){
		if(root==null)
			return null;
		LinkedList<Integer> resultOrder=new LinkedList<>();
		Stack<TreeNode> stack=new Stack<>();
		TreeNode current=root;
		while(current!=null || !stack.isEmpty()) {
			while (current != null) {
				stack.push(current);
				current = current.left; //一直走到最后一个左节点，也就是最左叶节点
			}
			if (!stack.isEmpty()) {
				current = stack.pop();  //弹出
				resultOrder.add(current.val);//先visited当前的最左叶节点
				current = current.right;//再对右子树进行上述操作
			}
		}

		return resultOrder;
	}

	/**
	 * 后序非递归遍历二叉树
	 * 从根节点开始，不断左子节点入栈直到null，
	 * 此时看上一个遍历的节点lastVisited，如果上一个遍历的节点是右子节点，则把栈顶元素pop并visited；
	 * 如果lastVisited不是右子节点，则以右子节点为root进行后序遍历。
	 * 和中序的区别是要加一个lastVisited变量标记上一个访问的节点
	 * */
	public LinkedList<Integer> postorderTraverse(TreeNode root){
		if(root==null)
			return null;
		LinkedList<Integer> resultOrder=new LinkedList<>();
		Stack<TreeNode> stack=new Stack<>();
		TreeNode current=root;
		TreeNode lastVisited=null;
		while(current!=null || !stack.isEmpty()){
			while(current!=null){  //一直遍历左节点直到null
				stack.push(current);
				current=current.left;
			}
			if(!stack.isEmpty()){
				//如果上一次不是访问右子节点，则先访问右子节点
				if(lastVisited!=stack.peek().right){
					current=stack.peek().right;
					lastVisited=current;  //此行代码的作用是到叶节点时，子节点为null，把null赋给lastVisited；没有这句会死循环
				}
				//如果上一次是访问的右子节点，则直接把当前节点pop并visited
				else {
					lastVisited=stack.pop();  //设置lastVisited
					resultOrder.add(lastVisited.val);
				}
			}
		}

		return resultOrder;
	}


	/**
	 * 生成一棵完全二叉树
	 * 主要利用队列先进先出的特点
	 * @param a 把数组中的元素按顺序一层一层建树
	 * */
	public TreeNode generalTree(int [] a){
		if(a.length==0)
			return null;
		TreeNode root=new TreeNode(a[0]);
		Queue<TreeNode> queue=new LinkedBlockingQueue<>();
		queue.add(root);
		int i=1;
		while(!queue.isEmpty() && i<a.length){
			TreeNode node=queue.poll();
			node.left=new TreeNode(a[i]);
			queue.add(node.left);
			i++;
			if(i<a.length){
				node.right=new TreeNode(a[i]);
				queue.add(node.right);
				i++;
			}
		}
		return root;
	}

	/**
	 * z字型遍历二叉树
	 * @param root 根节点
	 * */
     public void traverseTreeByZ(TreeNode root){
     	if(root==null)
     		return;
     	LinkedBlockingQueue<TreeNode> queue=new LinkedBlockingQueue<>();
     	TreeNode last=root;
     	TreeNode nextLast=null;
     	TreeNode cur;
     	queue.add(root);
     	while(!queue.isEmpty()){
     		cur=queue.poll();
     		System.out.print(cur.val+" ");

     		if(cur.left!=null){
     			queue.add(cur.left);
     			nextLast=cur.left;
			}
			if(cur.right!=null){
				queue.add(cur.right);
				nextLast=cur.right;
			}
			if(cur==last) {
				System.out.println();
				last = nextLast;
			}
		}
	 }

	 /**
	  * 判断链表是有环，主要通过快慢指针的追赶判断
	  * @param head 链表头部
	  * @return boolean
	  * */
	 public boolean isLinkLoop(LinkNode head){
	 	LinkNode slowPointer=head;  //慢指针，每次走一步
	 	LinkNode fastPointer=head;  //快指针，每次走两步
	    while (slowPointer!=null && fastPointer!=null){
	    	slowPointer=slowPointer.next;
	    	fastPointer=fastPointer.next.next;
	    	if(slowPointer==fastPointer)
	    		return true;
		}
	 	return false;
	 }

	 /**
	  * 寻找环入口
	  * @param head 链表头部
	  * @return 碰撞节点
	  * */
	 /*public*/

	 /**
	  * 统计1-n中数字x出现的次数
	  * */
	 public int countOfX(int n,int x){
	 	if(x<0 || x>9 || n<=0)
			try {
			 throw new Exception("x必须在【0-9】之间且n大于0");
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		int count=0;
	 	int current=n;
	 	String nString=String.valueOf(n);
	 	int len=nString.length(); //计算总共有多少位
		 for(int i=1;i<=len;i++){
		 	if(x==0 && i==len)  //如果求0出现的次数，最高位不能是0，值遍历到最高位的下一位
		 		break;
		 	current/=10;   //当前系数
            int exp=(int)Math.pow(10,i);//当前10的幂次级
		 	int base=current*exp/10;  //  基础值，exp/10等于10的i-1次幂
		 	int iValue=(int)(nString.charAt(len-i)-'0'); //第i位的值
			 if(x<iValue)   //小于第i位的值
			 	count+=base+(int)Math.pow(10,i-1);
			 else if(x>iValue)  //大于第i位的值
			 	count+=base;
			 else    //等于第i位
			 	count+=base+Integer.valueOf(nString.substring(len-i+1))+1;
		 }
		 return count;
	 }

}
