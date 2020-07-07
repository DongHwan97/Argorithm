package argorithm;

public class SelectionSort {

	public static void main(String[] args) {
		PrintArray pa = new PrintArray();
		int i ,j ,min, index = 0, temp;
		int array[]= {2,4,5,6,7,8,10,9,1,3};
		for(i=0;i<10;i++)
		{
			min=9999;
			for(j=i;j<10;j++)
			{
				if(min>array[j])
				{
					min=array[j];
					index=j;
				}
			}
			temp=array[i];
			array[i]=array[index];
			array[index]=temp;
		}
		pa.printArray(array);
	}

}
