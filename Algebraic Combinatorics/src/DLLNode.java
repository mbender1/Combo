
public class DLLNode<E> {
	
	protected DLLNode<E> prev;
	protected DLLNode<E> next;
	protected E elem;
	
	public DLLNode (E e){
		elem =e;
	}
	
	public DLLNode<E> cprev(DLLNode<E> p){
		prev = p;
		return prev;
	}
	
	public DLLNode<E> cnext(DLLNode<E> n){
		next = n;
		return next;
	}
	
	public E celem(E e){
		elem = e;
		return elem;
	}
}
