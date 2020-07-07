package datastructure;

public class DoublyLinkedList {
	private Node head;
	private Node tail;
	private int size = 0;

	private class Node {
		private Object data;// ������
		private Node next;// ����
		private Node prev;// ����

		public Node(Object input) {
			this.data = input;
			this.next = null;
			this.prev = null;
		}

		public String toString() {
			return String.valueOf(this.data);
		}
	}

	public void addFirst(Object input) {// ó���߰�
		Node newNode = new Node(input);
		newNode.next = head;// ���ο����� �������� ���
		if (head != null)// ������尡 �����ϸ�
			head.prev = newNode;// ������� ������带 ���ο��������
		head = newNode;// ���ο��尡 ��尡��
		size++;// ũ������
		if (head.next == null) {// ó���߰�������
			tail = head;
		}
	}

	public void addLast(Object input) {// �������߰�
		Node newNode = new Node(input);
		if (size == 0) {// �ƹ��͵������� ó���߰�
			addFirst(input);
		} else {
			tail.next = newNode;// ������ �������� ���ο���
			newNode.prev = tail;// ���ο����� �������� ����
			tail = newNode;// ������ ���ο���
			size++;// ũ������
		}
	}

	Node node(int index) {// ��ȸ
		if (index < size / 2) {// �պ���
			Node x = head;// �Ӹ�����
			for (int i = 0; i < index; i++)
				x = x.next;
			return x;
		} else {// �ں���
			Node x = tail;// ��������
			for (int i = size - 1; i > index; i--)
				x = x.prev;
			return x;
		}
	}

	public void add(int k, Object input) {
		if (k == 0) {// k==0�̸� ù��°����߰��̱⋚���� ó���߰����
			addFirst(input);
		} else {
			Node temp1 = node(k - 1);//
			Node temp2 = temp1.next;// k��°��带 ���ο���� ����
			Node newNode = new Node(input);// ���ο���
			temp1.next = newNode;// temp1�� ������ ���ο���
			newNode.next = temp2;// ���ο� ����� ������ temp2
			if (temp2 != null)
				temp2.prev = newNode;

			newNode.prev = temp1;
			size++;
			if (newNode.next == null) {// ���ο����� ������尡������ ����
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

	public Object removeFirst() {// ó��������
		Node temp = head;// temp�� head������
		head = temp.next;// head�� temp�� ������
		Object returnData = temp.data;// �����ҵ����ͺ���
		temp = null;// temp�� ���
		if (head != null)// ��尡 �ִٸ� head�� �������� null
			head.prev = null;
		size--;// ũ�Ⱘ��
		return returnData;// ���������͹�ȯ
	}

	public Object remove(int k) {
		if (k == 0)
			return removeFirst();// ó���������ϰ��
		Node temp = node(k - 1);// k-1��° ��带 temp������ ����
		Node todoDeleted = temp.next;// ������ ���, �ٷλ����� ����x

		temp.next = temp.next.next;// ��������� �������� ��������� ������带����
		if (temp.next != null) {
			temp.next.prev = temp;// �����ҳ���� ���ĳ�忬��
		}
		Object returnData = todoDeleted.data;// ������ �����͸� �����ϱ����� ���
		if (todoDeleted == tail) {// ������ �׳ɻ��� �������ʿ䰡���⶧��
			tail = temp;
		}
		todoDeleted = null;// ����
		size--;// ũ�Ⱘ��
		return returnData;
	}

	public Object removeLast() {// ������������
		return remove(size - 1);
	}

	public int size() {// ũ���ȯ
		return size;
	}

	public Object get(int k) {// ���ҹ�ȯ
		Node temp = node(k);
		return temp.data;
	}

	public int indexOf(Object data) {// Ž��
		Node temp = head;
		int index = 0;
		while (temp.data != data) {
			temp = temp.next;// ������
			index++;// ������Ű�簪��ã��
			if (temp == null)// �������ΰ��
				return -1;// ��ã�����
		}
		return index;// ã�����
	}

	class ListIterator {
		private Node lastReturned;
		private Node next;
		private int nextIndex;

		ListIterator() {
			next = head;
			nextIndex = 0;
		}

		public Object next() {// �޼ҵ�ȣ��� next�� �������� ����next.next�� ����
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
