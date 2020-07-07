package argorithm;

public class BubbleSort {

	public static void main(String[] args) {
		PrintArray pa = new PrintArray();
		int i, j, temp;
		int array[]= {2,4,5,6,7,8,10,9,1,3};
		for(i=0;i<10;i++)
		{
			for(j=0;j<9-i;j++)
			{
				if(array[j]>array[j+1]) {
					temp=array[j];
					array[j]=array[j+1];
					array[j+1]=temp;
				}
			}
		}
		pa.printArray(array);
	}

}
