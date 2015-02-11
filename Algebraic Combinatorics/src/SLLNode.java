public class SLLNode<E>{

	protected E elem;
	protected SLLNode<E> next;
	
	public SLLNode(E elem1, SLLNode<E> next1){
		elem = elem1;
		next = next1;
	}
	
	public void changeElem(E elem1){
		elem = elem1;
	}
	
	public void changeNext(SLLNode<E> next1){
		next = next1;
	}
	
	public String toString(){
		return elem.toString();
	}

}