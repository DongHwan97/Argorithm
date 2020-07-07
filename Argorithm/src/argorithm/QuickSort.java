package argorithm;

public class QuickSort {
	
	static int[] data= {3,4,5,6,7,8,9,10,1,2};
	public static void quicksort(int data[], int start, int end) {
		
		if(start>=end) {
			return;
		}
		int key=start;
		int i=start+1;
		int j=end;
		int temp;
		
		while(i<=j) {
			while(data[i]<=data[key])
			{
				i++;
			}
			while(data[j]>=data[key]&&j>start) 
			{
				j--;
			}
			if(i>j) {
				temp=data[j];
				data[j]=data[key];
				data[key]=temp;
			}
			else {
				temp=data[j];
				data[j]=data[i];
				data[i]=temp;
			}
		}
		quicksort(data,start,j-1);
		quicksort(data,j+1,end);
	}
	public static void main(String[] args) {
		int number = 10;
		PrintArray pa = new PrintArray();
		quicksort(data,0,number-1);
		pa.printArray(data);
	}

}
