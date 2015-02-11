
public class DLL<E> {

	protected DLLNode<E> head;
	protected DLLNode<E> tail;
	protected int size;
	
	public DLL(){
		head = null;
		tail = null;
		size = 0;
	}
	
	public void addFirst(E e){
		DLLNode<E> temp = new DLLNode<E>(e);
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
		DLLNode<E> temp = new DLLNode<E>(e);
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
		} if(!(o instanceof DLL<?>)){
			return false;
		}
		@SuppressWarnings("unchecked")
		DLL<E> dll = (DLL<E>) o;
		if(size!=dll.size){
			return false;
		} else {
			DLLNode<E> cur1 = head;
			DLLNode<E> cur2 = dll.head;
			for(int i = 0; i<dll.size; i++){
				if(!cur1.elem.equals(cur2.elem)){
					return false;
				} cur1 = cur1.next; 
				  cur2 = cur2.next;
			} return true;
		}
	}
	
	public Boolean contains(E e){
		DLLNode<E> cur = head;
		for(int i = 0; i<size; i++){
			if(cur.elem.equals(e)){
				return true;
			} cur = cur.next;
		} return false;
	}
	
}
