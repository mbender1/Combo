public class AVL<E extends Comparable<E>> {

	protected AVLNode<E> head;
	protected int size;
	
	public AVL(){
		head = null;
		size = 0;
	}
	
	public void add(E e){
		AVLNode<E> add = new AVLNode<E>(e);
		if(size == 0){
			head = add;
			size++;
		} else {
			AVLNode<E> cur = head;
			while((cur.elem.compareTo(e)>0 && cur.right!=null) || (cur.elem.compareTo(e)<=0 && cur.left!=null)){
				if(cur.elem.compareTo(e)>0){
					cur = cur.left;
				} else {
					cur = cur.right;
				}
			} if(cur.elem.compareTo(e)>0){
				cur.cright(add);
			} else {
				cur.cleft(add);
			} add.cprev(cur);
			size++;
			while(cur!=null){
				cur.height++;
				if(cur.sibling()!=null && (cur.sibling().height-cur.height>1 || cur.height-cur.sibling().height>1)){
					if(cur.prev.left==cur){
						
					}
				}
			}
		}
	}
	
}
