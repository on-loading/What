package sort;

/**
 * @author LZing
 * 
 * */

public class Sort {
	/**Sort.java中包含常见的各种排序算法：
	 * quickSort()   快速排序
	 * heapSort()  堆排序
	 * insertSort() 插入排序
	 * */

	
	
	
	
	/**  交换元素
	 *   @param a 数组  
	 *   @param i 数组中要交换的下标之一
	 *   @param j 数组中要交换的下标之一
	 * */
	public  void swap(int a[],int i,int j){
        int t=a[i];
        a[i]=a[j];
        a[j]=t;
    }
	
	
	
	
	/** 插入排序 时间复杂度O（n^2）
	 * */
	public void  insertSort(int a[]) {
		for(int i=1;i<a.length;i++){  //从第二个数开始遍历
			int temp=a[i];  //暂存当前要插入的数
			int j=i;
			for(;j>0;j--){
				if(temp<a[j-1]){   //如果比前一个数小，则要插入的数前移，前一个数后移
					a[j]=a[j-1];
				}
				else {
					break;  //成功找到插入的位置，直接跳出
				}
			}
			a[j]=temp;  //在合适的位置插入
		}
		
	}
	
	
	/**  快速排序  时间复杂度O(nlog n)
	 *   @param a 数组  
	 *   @param left 数组起始下标（包含）
	 *   @param right 数组结束下标（不包含）
	 *   
	 * */
	public void quickSort(int[] a ,int left,int right){
		int len=right-left;
		if(len<=1)  //如果只有一个元素，直接返回
			return ;
		if(len==2){   //如果有两个元素则比较交换之后返回
			if(a[left]>a[right-1])
				swap(a, left, right-1);
			return ;
		}
		
		//只有元素个数在3个以上时才进行quickSort
		int pivot=findPivot(a, left, right);  //枢纽元
		int i=left,j=right-1;
		for(;;){
			while(a[++i]<pivot);
			while(a[--j]>pivot);
			if(i>=j)  //如果i>=j,说明已经遍历完
				break;
			swap(a, i, j);		
		}
		swap(a, i, right-2);   //把枢纽元和i所在位置的元素交换
		
		//分别对i的左部和右部元素进行quickSort
		quickSort(a, left, i);
		quickSort(a, i, right);
	}
	
	/** 三值中数分割法选择枢纽元
	 * 首选对A[left],A[right-1],A[center]进行从小到大排序
	 * 经过该操作后，三元素的最大者被放到了A[right-1]
	 * 最小者被放到了A[left],A[center]中的元素大小居中
	 * 这样就可以作为枢纽元。由于A[right-1]大于枢纽元
	 * 我们可以把枢纽元放到A[right-2],并在分割阶段将左右的遍历索引i和j分别置为left,right-2.
	 * 此时A[left]比枢纽元小，不用担心j越界；同时枢纽元存储在A[right-2],将为i提供一个警戒标记
	 */
	
	/**三值中数分割法
	 * @param a 数组  
	 * @param left 数组起始下标（包含）
	 * @param right 数组结束下标（不包含）
	 * */
	private int findPivot(int[] a,int left,int right){
		//选出枢纽元并把枢纽元置于right-2处
		int center=left+(right-left)/2;
		if(a[center]<a[left])
			swap(a, left, center);
		if(a[center]>a[right-1])
			swap(a, center, right-1);
		if(a[center]<a[left])
			swap(a, center, left);
		swap(a, center, right-2);
		return a[right-2];
	}
	
	
	
	
	
	
    /**
     * 堆排序  时间复杂度O(nlog n)
     * @param a  要排序的数组
     * 主要思想是通过建一个最大堆（降序排序的时候建最小堆），得到的堆根元素最大，
     * 把根各最后一个元素互换，重新调整最大堆，循环此过程直到全部排序完成
     * */
	public void heapSort(int[] a){
		buildHeap(a);
		//建好最大堆之后，把最大堆的根节点和当前最后一个节点（由a.length-i确定）替换
		for(int i=0;i<a.length;i++){
			swap(a, 0, a.length-1-i);
			adjustHeap(a, 0, a.length-1-i);
		}
		
	}
     
	/**
	 * 建最大堆
	 * 从最后一个叶节点的父节点开始
	 * */
	public void buildHeap(int[] a){
		//从第一个非叶节点开始调整逐步建堆
		for(int i=a.length/2-1;i>=0;i--){
			adjustHeap(a, i, a.length);
		}
	}
	/**
	 * 在一定的基础上调整最大堆
	 * @param  a  数组
	 * @param i    要调整的父节点，向下调整
	 * @param len  数组的最大长度
	 * 下标为i的节点，其子节点分别为2*i+1和2*i+2
	 * */
    public void adjustHeap(int[] a,int i,int len){
    	int temp,j;
    	temp=a[i];
    	for(j=2*i+1;j<len;j=2*j+1){   //
    		if(j<len-1 && a[j]<a[j+1])
    			j++;   //记录子节点中较大的一个
    		if(j<len && temp>a[j])
    			break;  //父节点最大，不用调整
    		//如果不是父节点最大
    		a[i]=a[j];  //父节点取最大值
    		i=j;  //对以j为父节点的子树进行调整
    		
    	}
    	a[i]=temp;
    }
}
