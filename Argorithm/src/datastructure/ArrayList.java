package datastructure;

public class ArrayList {
	private int size=0;//리스트의 크기
	private Object[] elementData = new Object[100];//리스트의 데이터수
	
	public boolean addLast(Object element) {//마지막에 추가
		elementData[size]=element;//인덱스에 추가
		size++;//인덱스 증가
		return true;
	}
	
	public boolean add(int index, Object element) {//중간에추가
		//중간에 데이터를 추가하기 위해서는 끝의 엘리먼트로부터 인덱스의 노드까지 뒤로 한칸씩 밀림
		for(int i= size-1;i>=index;i--) {
			elementData[i+1]=elementData[i];
		}
		elementData[index]=element;
		size++;
		return true;
	}
	
	public boolean addFirst(Object element) {//처음에 추가
		return add(0,element);//0번째에 추가해주면 같은 동작
	}
	
	public Object remove(int index) {//삭제
		Object removed= elementData[index];//삭제하기전 removed변수에 데이터저장
		for(int i = index+1;i<size-1;i++) {//삭제된 엘리먼트 다음엘리먼트부터 마지막까지 순차적으로 이동
			elementData[i-1]=elementData[i];
		}
		size--;//삭제됬으니 크기를 줄인다
		elementData[size]=null;//마지막 위치의 엘리먼트를 명시적으로 삭제
		return removed;
	}
	
	public Object removeFirst() {//처음삭제
		return remove(0);
	}
	
	public Object removeLast() {//마지막삭제
		return remove(size-1);
	}
	
	public Object get(int index)//데이터가져오기
	{
		return elementData[index];
	}
	
	public int size()//리스트의 크기
	{
		return size;
	}
	
	public Object indexOf(Object o) {//특정 값이 어느위치에있는지확인하는메소드
		for(int i=0;i<size;i++)
		{
			if(o.equals(elementData[i])) {//같으면
				return i;//리턴
			}
		}
		return -1;//없을경우
	}

	public String toString() {//프린트
		String str="[";
		for(int i=0; i<size;i++) {
			str+=elementData[i];
			if(i<size-1) {
				str+=",";
			}
		}
		return str+"]";
	}
	
	public ListIterator listIterator() {//순회를 사용하기 위한 코드
		return new ListIterator();
	}
	class ListIterator{
		private int nextIndex=0;//현재 탐색하고 있는 순서를 가르키는 인덱스 값
		
		public boolean hasNext() {//next메소드를 호출할수있는지를 체크
			return nextIndex < size();//nextIndex가 엘리먼트 숫자보다 적다면 탐색할 엘리먼트가 존재하여 true리턴
		}
		
		public Object next() {
			return elementData[nextIndex++];
		}
		
		public boolean hasPrevious() {//이전메소드를 호출해도되는지체크
			return nextIndex > 0;//0보다 크다면 이전엘리먼트가 존재
		}
		
		public Object Previous() {//이전
			return elementData[--nextIndex];
		}
		
		public void add(Object element) {//현재메소드 추가
			ArrayList.this.add(nextIndex++, element);
		}
		
		public void remove() {//현재메소드 삭제
			ArrayList.this.remove(nextIndex-1);
			nextIndex--;
		}
	}
}
