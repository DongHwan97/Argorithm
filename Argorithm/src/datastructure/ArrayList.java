package datastructure;

public class ArrayList {
	private int size=0;//����Ʈ�� ũ��
	private Object[] elementData = new Object[100];//����Ʈ�� �����ͼ�
	
	public boolean addLast(Object element) {//�������� �߰�
		elementData[size]=element;//�ε����� �߰�
		size++;//�ε��� ����
		return true;
	}
	
	public boolean add(int index, Object element) {//�߰����߰�
		//�߰��� �����͸� �߰��ϱ� ���ؼ��� ���� ������Ʈ�κ��� �ε����� ������ �ڷ� ��ĭ�� �и�
		for(int i= size-1;i>=index;i--) {
			elementData[i+1]=elementData[i];
		}
		elementData[index]=element;
		size++;
		return true;
	}
	
	public boolean addFirst(Object element) {//ó���� �߰�
		return add(0,element);//0��°�� �߰����ָ� ���� ����
	}
	
	public Object remove(int index) {//����
		Object removed= elementData[index];//�����ϱ��� removed������ ����������
		for(int i = index+1;i<size-1;i++) {//������ ������Ʈ ����������Ʈ���� ���������� ���������� �̵�
			elementData[i-1]=elementData[i];
		}
		size--;//���������� ũ�⸦ ���δ�
		elementData[size]=null;//������ ��ġ�� ������Ʈ�� ��������� ����
		return removed;
	}
	
	public Object removeFirst() {//ó������
		return remove(0);
	}
	
	public Object removeLast() {//����������
		return remove(size-1);
	}
	
	public Object get(int index)//�����Ͱ�������
	{
		return elementData[index];
	}
	
	public int size()//����Ʈ�� ũ��
	{
		return size;
	}
	
	public Object indexOf(Object o) {//Ư�� ���� �����ġ���ִ���Ȯ���ϴ¸޼ҵ�
		for(int i=0;i<size;i++)
		{
			if(o.equals(elementData[i])) {//������
				return i;//����
			}
		}
		return -1;//�������
	}

	public String toString() {//����Ʈ
		String str="[";
		for(int i=0; i<size;i++) {
			str+=elementData[i];
			if(i<size-1) {
				str+=",";
			}
		}
		return str+"]";
	}
	
	public ListIterator listIterator() {//��ȸ�� ����ϱ� ���� �ڵ�
		return new ListIterator();
	}
	class ListIterator{
		private int nextIndex=0;//���� Ž���ϰ� �ִ� ������ ����Ű�� �ε��� ��
		
		public boolean hasNext() {//next�޼ҵ带 ȣ���Ҽ��ִ����� üũ
			return nextIndex < size();//nextIndex�� ������Ʈ ���ں��� ���ٸ� Ž���� ������Ʈ�� �����Ͽ� true����
		}
		
		public Object next() {
			return elementData[nextIndex++];
		}
		
		public boolean hasPrevious() {//�����޼ҵ带 ȣ���ص��Ǵ���üũ
			return nextIndex > 0;//0���� ũ�ٸ� ����������Ʈ�� ����
		}
		
		public Object Previous() {//����
			return elementData[--nextIndex];
		}
		
		public void add(Object element) {//����޼ҵ� �߰�
			ArrayList.this.add(nextIndex++, element);
		}
		
		public void remove() {//����޼ҵ� ����
			ArrayList.this.remove(nextIndex-1);
			nextIndex--;
		}
	}
}
