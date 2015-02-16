
public class DLLCNode<E extends Comparable<E>> {
	
	protected DLLCNode<E> prev;
	protected DLLCNode<E> next;
	protected E elem;
	
	public DLLCNode (E e){
		elem =e;
	}
	
	public DLLCNode<E> cprev(DLLCNode<E> p){
		prev = p;
		return prev;
	}
	
	public DLLCNode<E> cnext(DLLCNode<E> n){
		next = n;
		return next;
	}
	
	public E celem(E e){
		elem = e;
		return elem;
	}
}
