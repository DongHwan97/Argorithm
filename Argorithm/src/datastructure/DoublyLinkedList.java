package datastructure;

public class DoublyLinkedList {
	private Node head;
	private Node tail;
	private int size = 0;

	private class Node {
		private Object data;// 데이터
		private Node next;// 다음
		private Node prev;// 이전

		public Node(Object input) {
			this.data = input;
			this.next = null;
			this.prev = null;
		}

		public String toString() {
			return String.valueOf(this.data);
		}
	}

	public void addFirst(Object input) {// 처음추가
		Node newNode = new Node(input);
		newNode.next = head;// 새로운노드의 다음노드는 헤드
		if (head != null)// 기존노드가 존재하면
			head.prev = newNode;// 헤드노드의 이전노드를 새로운노드로지정
		head = newNode;// 새로운노드가 헤드가됨
		size++;// 크기증가
		if (head.next == null) {// 처음추가했을때
			tail = head;
		}
	}

	public void addLast(Object input) {// 마지막추가
		Node newNode = new Node(input);
		if (size == 0) {// 아무것도없으면 처음추가
			addFirst(input);
		} else {
			tail.next = newNode;// 꼬리의 마지막을 새로운노드
			newNode.prev = tail;// 새로운노드의 이전값은 꼬리
			tail = newNode;// 꼬리는 새로운노드
			size++;// 크기증가
		}
	}

	Node node(int index) {// 순회
		if (index < size / 2) {// 앞부터
			Node x = head;// 머리부터
			for (int i = 0; i < index; i++)
				x = x.next;
			return x;
		} else {// 뒤부터
			Node x = tail;// 꼬리부터
			for (int i = size - 1; i > index; i--)
				x = x.prev;
			return x;
		}
	}

	public void add(int k, Object input) {
		if (k == 0) {// k==0이면 첫번째노드추가이기떄문에 처음추가사용
			addFirst(input);
		} else {
			Node temp1 = node(k - 1);//
			Node temp2 = temp1.next;// k번째노드를 새로운노드로 지정
			Node newNode = new Node(input);// 새로운노드
			temp1.next = newNode;// temp1의 다음은 새로운노드
			newNode.next = temp2;// 새로운 노드의 다음은 temp2
			if (temp2 != null)
				temp2.prev = newNode;

			newNode.prev = temp1;
			size++;
			if (newNode.next == null) {// 새로운노드의 다음노드가없으면 꼬리
				tail = newNode;
			}
		}

	}

	public String toString() {
		if (head == null) {
			return "[]";
		}
		Node temp = head;
		String str = "[";
		while (temp.next != null) {
			str += temp.data + ",";
			temp = temp.next;
		}
		str += temp.data;
		return str + "]";
	}

	public Object removeFirst() {// 처음노드삭제
		Node temp = head;// temp를 head로지정
		head = temp.next;// head에 temp의 다음값
		Object returnData = temp.data;// 삭제할데이터보관
		temp = null;// temp를 비움
		if (head != null)// 노드가 있다면 head의 이전값을 null
			head.prev = null;
		size--;// 크기감소
		return returnData;// 삭제데이터반환
	}

	public Object remove(int k) {
		if (k == 0)
			return removeFirst();// 처음데이터일경우
		Node temp = node(k - 1);// k-1번째 노드를 temp값으로 지정
		Node todoDeleted = temp.next;// 삭제값 기록, 바로삭제시 연결x

		temp.next = temp.next.next;// 삭제노드의 다음노드로 삭제노드의 다음노드를지정
		if (temp.next != null) {
			temp.next.prev = temp;// 삭제할노드의 전후노드연결
		}
		Object returnData = todoDeleted.data;// 삭제된 데이터를 리턴하기위해 기록
		if (todoDeleted == tail) {// 꼬리면 그냥삭제 연결할필요가없기때문
			tail = temp;
		}
		todoDeleted = null;// 삭제
		size--;// 크기감소
		return returnData;
	}

	public Object removeLast() {// 마지막노드삭제
		return remove(size - 1);
	}

	public int size() {// 크기반환
		return size;
	}

	public Object get(int k) {// 원소반환
		Node temp = node(k);
		return temp.data;
	}

	public int indexOf(Object data) {// 탐색
		Node temp = head;
		int index = 0;
		while (temp.data != data) {
			temp = temp.next;// 다음값
			index++;// 증가시키며값을찾음
			if (temp == null)// 마지막인경우
				return -1;// 못찾은경우
		}
		return index;// 찾은경우
	}

	class ListIterator {
		private Node lastReturned;
		private Node next;
		private int nextIndex;

		ListIterator() {
			next = head;
			nextIndex = 0;
		}

		public Object next() {// 메소드호출시 next의 참조값이 기존next.next로 변경
			lastReturned = next;
			next = next.next;
			nextIndex++;
			return lastReturned.data;
		}

		public boolean hasNext() {
			return nextIndex < size();
		}

		public void add(Object input) {
			Node newNode = new Node(input);
			if (lastReturned == null) {
				head = newNode;
				newNode.next = next;
			} else {
				lastReturned.next = newNode;
				newNode.prev = lastReturned;
				if (next != null) {
					newNode.next = next;
					next.prev = newNode;
				} else {
					tail = newNode;
				}
			}
			lastReturned = newNode;
			nextIndex++;
			size++;
		}

		public void remove() {
			if (nextIndex == 0) {
				throw new IllegalStateException();
			}
			Node n = lastReturned.next;
			Node p = lastReturned.prev;
			
			if(p==null) {
				head=n;
				head.prev=null;
				lastReturned=null;
			}
			else {
				p.next=next;
				lastReturned.prev=null;
			}
			if(n==null) {
				tail=p;
				tail.next=null;
			}
			else {
				n.prev=p;
			}
			if(next==null) {
				lastReturned=tail;
			}
			else {
				lastReturned=next.prev;
			}
			size--;
			nextIndex--;
		}
	}
}
