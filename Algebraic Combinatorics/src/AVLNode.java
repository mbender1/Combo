public class AVLNode<E> {

	protected E elem;
	protected AVLNode<E> prev;
	protected AVLNode<E> left;
	protected AVLNode<E> right;
	public int height;
	
	public AVLNode(E e){
		elem = e;
		prev = null;
		left = null;
		right = null;
		height = 0;
	}
	
	public E celem(E e){
		elem = e;
		return e;
	}
	
	public AVLNode<E> cprev(AVLNode<E> p){
		prev = p;
		return prev;
	}
	
	public AVLNode<E> cleft(AVLNode<E> l){
		left = l;
		return left;
	}
	
	public AVLNode<E> cright(AVLNode<E> r){
		right = r;
		return right;
	}
	
	public AVLNode<E> sibling(){
		if(prev!=null){
			if(prev.left==this){
				return prev.right;
			} else {
				return prev.left;
			}
		} else {
			return null;
		}
	}
}
