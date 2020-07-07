package argorithm;

public class InsertionSort {
	public static void main(String[] args) {
		PrintArray pa = new PrintArray();
		int i, j, temp;
		int array[]= {2,4,5,6,7,8,10,9,1,3};
		for(i=0;i<9;i++) {
			j=i;
			while(array[j]>array[j+1])
			{
				temp=array[j];
				array[j]=array[j+1];
				array[j+1]=temp;
				j--;
			}
		}
		pa.printArray(array);
	}
}
