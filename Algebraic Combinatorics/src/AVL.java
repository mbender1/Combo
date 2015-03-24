public class AVL<E extends Comparable<E>> {

	protected AVLNode<E> head;
	protected int size;
	
	public AVL(){
		head = null;
		size = 0;
	}
	
	public void add(E e){
		AVLNode<E> add = new AVLNode<E>(e);
		add.height = 1;
		if(size == 0){
			head = add;
			size++;
		} else {
			size++;
			AVLNode<E> cur = head;
			while((cur.elem.compareTo(e)<0 && cur.right!=null) 
			   || (cur.elem.compareTo(e)>=0 && cur.left!=null)){
				if(cur.elem.compareTo(e)<0){
					cur = cur.right;
				} else {
					cur = cur.left;
				}
			} if(cur.elem.compareTo(e)<0){
				cur.cright(add);
			} else {
				cur.cleft(add);
			} add.cprev(cur);
			while(cur!=null){
				cur.height = max(getHeight(cur.left), getHeight(cur.right))+1;
				if(getHeight(cur.left)-getHeight(cur.right)>1 || getHeight(cur.right)-getHeight(cur.left)>1){
					AVLNode<E> z = cur;
					AVLNode<E> y = tallerChild(cur);
					AVLNode<E> x = tallerChild(y);
					restructure(x,y,z);
					return;
				} cur = cur.prev;
			}
		}
	}
	
	public E delete(E e){
		AVLNode<E> cur = head;
		E temp = null;
		while(!(cur == null || cur.elem.equals(e))){
			if(cur.elem.compareTo(e)<0){
				cur = cur.right;
			} else {
				cur = cur.left;
			}
		} if(cur == null){
			return null;
		} else if(size == 1){
			temp = cur.elem;
			head = null;
		} else if(cur.left==cur.right){ //both null, so leaf
			temp = cur.elem;
			cur = cur.prev;
			if(cur.left.elem.equals(e)){
				cur.left = null;
			} else {
				cur.right = null;
			} while(cur!=null){
				cur.height = max(getHeight(cur.left), getHeight(cur.right))+1;
				if(getHeight(cur.left)-getHeight(cur.right)>1 || getHeight(cur.right)-getHeight(cur.left)>1){
					AVLNode<E> z = cur;
					AVLNode<E> y = tallerChild(cur);
					AVLNode<E> x = tallerChild(y);
					restructure(x,y,z);
				} cur = cur.prev;
			}
		} else {
			temp = cur.elem;
			AVLNode<E> found = cur;
			if(cur.right == null){
				cur = cur.left;
				while(cur.right!=null){
					cur = cur.right;
				} 
			} else {
				cur = cur.right;
				while(cur.left!=null){
					cur = cur.left;
				}
			} found.celem(cur.elem);
			if(cur.prev.left == cur){
				cur = cur.prev;
				if(cur.left.right==null){
					cur.cleft(cur.left.left);
				} else {
					cur.cleft(cur.left.right);
				} if(cur.left!=null){
					cur.left.cprev(cur);
				}
			} else {
				cur = cur.prev;
				if(cur.right.left==null){
					cur.cright(cur.right.right);
				} else {
					cur.cright(cur.right.left);
				} if(cur.right!=null){
					cur.right.cprev(cur);
				}
			}	
			while(cur!=null){
				cur.height = max(getHeight(cur.left), getHeight(cur.right))+1;
				if(getHeight(cur.left)-getHeight(cur.right)>1 || getHeight(cur.right)-getHeight(cur.left)>1){
					AVLNode<E> z = cur;
					AVLNode<E> y = tallerChild(cur);
					AVLNode<E> x = tallerChild(y);
					restructure(x,y,z);
				} else {
					cur = cur.prev;
				}
			}
		} size--;
		return temp;
	}
	
	public E pop(){
		if(size==0){
			return null;
		} return delete(head.elem);
	}
	
	private void restructure(AVLNode<E> x, AVLNode<E> y, AVLNode<E> z){
		AVLNode<E> a,b,c;
		if(x.elem.compareTo(z.elem)>0 && x.elem.compareTo(y.elem)<=0){
			a = z; b = x; c = y;
		} else if(y.elem.compareTo(z.elem)<=0 && x.elem.compareTo(y.elem)>0){
			a = y; b = x; c = z;
		} else if(y.elem.compareTo(z.elem)>0 && x.elem.compareTo(y.elem)>0){
			a = z; b = y; c = x;
		} else {
			a = x; b = y; c = z;
		} if (head == z){
			head = b;
			b.cprev(null);
		} else {
			if(z.prev.left==z){
				z.prev.cleft(b);
				if(b!=null){
					b.cprev(z.prev);
				}
			} else {
				z.prev.cright(b);
				if(b!=null){
					b.cprev(z.prev);
				}
			}
		} if(b.left != x && b.left != y && b.left!= z){
			a.cright(b.left);
			if(b.left!=null){
				b.left.cprev(a);
			}
		} if(b.right != x && b.right != y && b.right != z){
			c.cleft(b.right);
			if(b.right!=null){
				b.right.cprev(c);
			}
		} b.cleft(a);
		if(a!=null){
			a.cprev(b);
		} b.cright(c);
		if(c!=null){
			c.cprev(b);
		} if(b.left!=null){
			b.left.height = max(getHeight(b.left.left), getHeight(b.left.right))+1;
		}
		if(b.right!=null){
			b.right.height = max(getHeight(b.right.left), getHeight(b.right.right))+1;
		}
		b.height = max(getHeight(b.left), getHeight(b.right))+1;
	}
	
	private int max(int a, int b){
		if(a>=b){
			return a;
		} return b;
	}
	
	private int getHeight(AVLNode<E> a){
		if(a==null){
			return 0;
		} else {
			return a.height;
		}
	}
	
	private AVLNode<E> tallerChild(AVLNode<E> cur){
		if(cur==null || (cur.left==null && cur.right==null)){
			return null;
		} else if(cur.left==null || cur.right==null){
			if(cur.left==null){
				return cur.right;
			} else {
				return cur.left;
			}
		} else {
			if(getHeight(cur.left)<getHeight(cur.right)){
				return cur.right;
			} else {
				return cur.left;
			}
		}
	}
	
	public Boolean contains(E e){
		if(size==0){
			return false;
		} else {
			AVLNode<E> cur = head;
			while(cur!=null){
				if(cur.elem.equals(e)){
					return true;
				} else if(cur.elem.compareTo(e)<0){
					cur = cur.right;
				} else {
					cur = cur.left;
				}
			} return false;
		}
	}
	
	public void printTree(AVLNode<E> cur){
		if(cur.left!=null){
			printTree(cur.left);
		} System.out.print(cur.elem+" ");
		if(cur.right!=null){
			printTree(cur.right);
		}
	}
	
	public void printTreeLevels(){
		SLL<AVLNode<E>> queue = new SLL<AVLNode<E>>();
		queue.add(head);
		while(queue.size!=0){
			if(queue.head.elem.left!=null){
				queue.add(queue.head.elem.left);
			} if(queue.head.elem.right!=null){
				queue.add(queue.head.elem.right);
			} if(queue.head.elem.prev!=null){
				System.out.println("Elem: "+queue.head.elem.elem+" Prev: "+queue.head.elem.prev.elem);
			} else {
				System.out.println("Elem: "+queue.head.elem.elem);
			}
			queue.deleteFirst();
		}
	}
	
	private String toStringRec(AVLNode<E> cur, String output){
		if(cur.left!=null){
			output = toStringRec(cur.left, output);
		} output += cur.elem + ", ";
		if(cur.right!=null){
			output = toStringRec(cur.right, output);
		} return output;
	}
	
	public String toString(){
		String output = toStringRec(head,"");
		return output.substring(0, output.length()-2);
	}
}
