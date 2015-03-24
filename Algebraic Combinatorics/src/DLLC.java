
public class DLLC<E extends Comparable<E>> implements Comparable<DLLC<E>> {

	protected DLLCNode<E> head;
	protected DLLCNode<E> tail;
	protected int size;
	
	public DLLC(){
		head = null;
		tail = null;
		size = 0;
	}
	
	public void addFirst(E e){
		DLLCNode<E> temp = new DLLCNode<E>(e);
		if(size == 0){
			head = temp;
			tail = temp;
			size++;
		} else {
			temp.cnext(head);
			head.cprev(temp);
			head = temp;
			size++;
		}
	}
	
	public void addLast(E e){
		DLLCNode<E> temp = new DLLCNode<E>(e);
		if(size == 0){
			head = temp;
			tail = temp;
			size++;
		} else {
			temp.cprev(tail);
			tail.cnext(temp);
			tail = temp;
			size++;
		}
	}
	
	public E deleteFirst(){
		if(size == 0){
			return null;
		} else if (size == 1){
			E temp = head.elem;
			head = null;
			tail = null;
			size = 0;
			return temp;
		} else {
			E temp = head.elem;
			head.next.cprev(null);
			head = head.next;
			size--;
			return temp;
		}
	}
	
	public E deleteLast(){
		if(size == 0){
			return null;
		} else if (size == 1){
			E temp = tail.elem;
			head = null;
			tail = null;
			size = 0;
			return temp;
		} else {
			E temp = tail.elem;
			tail.prev.cnext(null);
			tail = tail.prev;
			size--;
			return temp;
		}
	}
	@Override
	public boolean equals(Object o){
		if(o==this){
			return true;
		} if(!(o instanceof DLLC<?>)){
			return false;
		}
		@SuppressWarnings("unchecked")
		DLLC<E> DLLC = (DLLC<E>) o;
		if(size!=DLLC.size){
			return false;
		} else {
			DLLCNode<E> cur1 = head;
			DLLCNode<E> cur2 = DLLC.head;
			for(int i = 0; i<DLLC.size; i++){
				if(!cur1.elem.equals(cur2.elem)){
					return false;
				} cur1 = cur1.next; 
				  cur2 = cur2.next;
			} return true;
		}
	}
	
	public Boolean contains(E e){
		DLLCNode<E> cur = head;
		for(int i = 0; i<size; i++){
			if(cur.elem.equals(e)){
				return true;
			} cur = cur.next;
		} return false;
	}

	@Override
	public int compareTo(DLLC<E> DLLC) {
		DLLCNode<E> cur1 = head;
		DLLCNode<E> cur2 = DLLC.head;
		for(int i = 0; i<size; i++){
			if(cur2 == null){
				return 1;
			}
			if(cur1.elem.compareTo(cur2.elem)>0){
				return 1;
			} else if(cur1.elem.compareTo(cur2.elem)<0){
				return -1;
			} else {
				cur1 = cur1.next;
				cur2 = cur2.next;
			}
		} if(cur2 == null){
			return 0;
		} else {
			return -1;
		}
	}
	
	public String toString(){
		String output = "[";
		DLLCNode<E> cur = head;
		for(int i = 0; i<size; i++){
			output += cur.elem + ", ";
			cur = cur.next;
		} return output.substring(0,output.length()-2)+"]";
	}
}
