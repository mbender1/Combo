public class SLL<E> {

	protected SLLNode<E> head;
	protected SLLNode<E> tail;
	protected int size;
	
	public SLL(){
		head = null;
		tail = null;
		size = 0;
	}
	
	public SLL(E e){
		head = new SLLNode<E>(e, null);
		tail = head;
		size = 1;
	}
	
	public void add(E elem){
		if(size==0){
			head = new SLLNode<E>(elem,null);
			tail = head;
			size=1;
		} else if (size==1){
			head.changeNext(new SLLNode<E>(elem, null));
			tail = head.next;
			size=2;
		} else {
			tail.changeNext(new SLLNode<E>(elem, null));
			tail = tail.next;
			size++;
		}
	}
	
	public void addFirst(E elem){
		if(size==0){
			head = new SLLNode<E>(elem, null);
			tail = head;
			size=1;
		} else {
			head = new SLLNode<E>(elem, head);
			size++;
		}
	}
	
	public SLLNode<E> deleteFirst(){
		if(size==0){
			return null;
		} else if(size==1){
			SLLNode<E> temp = head;
			head = null; tail = null;
			size = 0;
			return temp;
		} else {
			SLLNode<E> temp = head;
			head = head.next;
			size--;
			return temp;
		}
	}
	
	public SLLNode<E> delete(E elem1){
		SLLNode<E> cur = head;
		SLLNode<E> prev = null;
		while(!(cur==null || cur.elem.equals(elem1))){
			prev = cur;
			cur = cur.next;
		}
		if(cur==null){
			return null;
		} else if(prev==null){
			if(size==1){
				head = null;
				tail = null;
			} else {
				head = head.next;
			} size--;
		} else {
			if(cur==tail){
				tail = prev;
			} prev.changeNext(cur.next);
			size--;
		} return cur;
	}
	
	public SLL<E> myClone(){
		SLL<E> sll = new SLL<E>();
		SLLNode<E> cur = head;
		while(cur!=null){
			sll.add(cur.elem);
			cur = cur.next;
		} return sll;
	}
	@Override
	public boolean equals(Object o){
		if(this==o){
			return true;
		} if(!(o instanceof SLL<?>)){
			return false;
		} @SuppressWarnings("unchecked")
		SLL<E> sll = (SLL<E>) o;
		if(size!=sll.size){
			return false;
		} else {
			SLLNode<E> cur1 = head;
			SLLNode<E> cur2 = sll.head;
			for(int i = 0; i<sll.size; i++){
				if(!cur1.elem.equals(cur2.elem)){
					return false;
				} cur1 = cur1.next; 
				  cur2 = cur2.next;
			} return true;
		}
	}

	public Boolean contains(E e){
		SLLNode<E> cur = head;
		for(int i = 0; i<size; i++){
			if(cur.elem.equals(e)){
				return true;
			} cur = cur.next;
		} return false;
	}
	
	public static void main(String[] args){
		SLL<Integer> sl = new SLL<Integer>();
		SLL<Integer> sk = new SLL<Integer>();
		sl.add(5);
		sk.add(5);
		if(sl==sk){
			System.out.println("1");
		} if(sl.equals(sk)){
			System.out.println("2");	
		} System.out.println("3");
		
		SLL<DLL<Integer>> s1 = new SLL<DLL<Integer>>();
		SLL<DLL<Integer>> s2 = new SLL<DLL<Integer>>();
		DLL<Integer> d1 = new DLL<Integer>();
		DLL<Integer> d2 = new DLL<Integer>();
		
		for(int i = 0; i<10; i++){
			d1.addLast(i);
			d2.addLast(i);
		}
		
		s1.add(d1);
		s2.add(d2);
		if(s1.equals(s2)){
			System.out.println("Hm");
		} else {
			System.out.println("Fuck");
		}
		Integer k = 1;
		Integer l = 2;
		System.out.println(k.compareTo(l));
	}
}