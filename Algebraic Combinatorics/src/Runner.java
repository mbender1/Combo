
public class Runner {

	public static void main(String[] args){
		Runner runner = new Runner();
		int[] ints = new int[] {2,6,7,1,3,8,5,10,9,4};
		for(int i = 0; i< ints.length; i++){
			ints[i] = ints[i]-1;
		}
		SLL<SLL<Integer>> cycles = runner.generateCycles(ints);
		SLLNode<SLL<Integer>> cur1 = cycles.head;
		for(int i = 0; i<cycles.size; i++){
			System.out.println("Cycle "+(i+1));
			SLLNode<Integer> cur2 = cur1.elem.head;
			for(int j = 0; j<cur1.elem.size; j++){
				System.out.print((cur2.elem+1)+" ");
				cur2 = cur2.next;
			} System.out.println(); cur1 = cur1.next;
		} System.out.println("\n\n\n");
		SLL<transP> transpositions = new SLL<transP>();
		cur1 = cycles.head;
		SLLNode<transP> cur3;
		for(int i = 0; i<cycles.size; i++){
			if(cur1.elem.size>1){
				SLL<transP> curCycle = runner.generateTransPs(cur1.elem);
				cur3 = curCycle.head;
				for(int j = 0; j<curCycle.size; j++){
					transpositions.add(cur3.elem);
					cur3 = cur3.next;
				}
			} cur1 = cur1.next;
		} cur3 = transpositions.head;
		System.out.println("Transpositions: ");
		for(int i = 0; i<transpositions.size; i++){
			System.out.print("(" + (cur3.elem.a+1)+", "+(cur3.elem.b+1)+") ");
			cur3 = cur3.next;
		} System.out.println("\n\n\n");
		
		DLLC<Integer> adjTrans = runner.generateAdjTrans(transpositions);
		DLLCNode<Integer> cur4 = adjTrans.head;
		for(int i = 0; i<adjTrans.size; i++){
			System.out.print((cur4.elem+1)+" ");
			cur4 = cur4.next;
		} AVL<DLLC<Integer>> finalList = runner.tryToDecrease(adjTrans);
		System.out.println("There are "+finalList.size+ " decompositions.");
		//System.out.println(finalList);
	}
	
	public SLL<SLL<Integer>> generateCycles(int[] p){
		int[] check = new int[p.length];
		SLL<SLL<Integer>> cycles = new SLL<SLL<Integer>>();
		int i = 0;
		Boolean cycleStart = true;
		SLL<Integer> cur = new SLL<Integer>();
		while(i<p.length){
			if(check[i]==1){
				if(!cycleStart){
					cycles.add(cur);
					cur = new SLL<Integer>();	
				} cycleStart = true;
				i++;
			} else {
				cycleStart = false;
				cur.add(i);
				check[i] = 1;
				i = p[i];
			}
		} return cycles;
	}
	
	public SLL<transP> generateTransPs(SLL<Integer> cycle){
		SLL<transP> sll = new SLL<transP>();
		SLLNode<Integer> cur = cycle.head;
		int f = cur.elem;
		cur = cur.next;
		for(int i = 1; i<cycle.size; i++){
			sll.addFirst(new transP(f, cur.elem));
			cur = cur.next;
		} return sll;
	}
	
	public DLLC<Integer> generateAdjTrans(SLL<transP> transps){
		DLLC<Integer> adjTrans = new DLLC<Integer>();
		SLLNode<transP> cur = transps.head;
		for(int i = 0; i<transps.size; i++){
			int start = cur.elem.a;
			int end = cur.elem.b;
			for(int j = start; j<end; j++){
				System.out.print((j+1) + " ");
				if(adjTrans.size>0 && adjTrans.tail.elem.equals(j)){
					adjTrans.deleteLast();
				} else {
					adjTrans.addLast(j);
				}
			} for(int j = end-2; j>= start; j--){
				System.out.print((j+1) + " ");
				adjTrans.addLast(j);
			} cur = cur.next;
		} System.out.println();
		return adjTrans;
	}
	
	public AVL<DLLC<Integer>> tryToDecrease(DLLC<Integer> cycle){
		if(cycle.size==0){
			return new AVL<DLLC<Integer>>();
		} System.out.print("\nStart cycle: ");
		DLLCNode<Integer> print = cycle.head;
		for(int i = 0; i<cycle.size; i++){
			System.out.print(print.elem+1+" ");
			print = print.next;
		}
		AVL<DLLC<Integer>> trash = new AVL<DLLC<Integer>>();
		AVL<DLLC<Integer>> queue = new AVL<DLLC<Integer>>();
		queue.add(cycle);
		trash.add(cycle);
		int counter = 0;
		while(queue.size>0){
			counter++;
			if(queue.size%10000==0 || counter % 1000 == 0){
				System.out.print("\nQueue size: "+queue.size+" Length: "+trash.head.elem.size+" Trash size: "+trash.size);
			}
			DLLC<Integer> pop = queue.pop();
			/**System.out.print("");
			DLLNode<Integer> poprun = pop.head;
			for(int i = 0; i<pop.size; i++){
				System.out.print((poprun.elem+1) + " ");
				poprun = poprun.next;
			}**/
			DLLCNode<Integer> cur = pop.head;
			int i;
			for(i = 0; i<pop.size-2; i++){
				if(cur.elem.equals(cur.next.elem)){
					DLLC<Integer> dll = new DLLC<Integer>();
					DLLCNode<Integer> tempCur = pop.head;
					for(int j = 0; j<i; j++){
						dll.addLast(tempCur.elem);
						tempCur = tempCur.next;
					} tempCur = tempCur.next;
					tempCur = tempCur.next;
					for(int j = i+2; j<pop.size; j++){
						dll.addLast(tempCur.elem);
						tempCur = tempCur.next;
					} System.out.println("\nDown to size "+dll.size);
					return tryToDecrease(dll);
				} if(cur.elem - cur.next.elem > 1 || cur.next.elem - cur.elem > 1){
					DLLC<Integer> temp = new DLLC<Integer>();
					DLLCNode<Integer> tempCur = pop.head;
					for(int j = 0; j<pop.size; j++){
						temp.addLast(tempCur.elem);
						tempCur = tempCur.next;
					} tempCur = temp.head; 
					for(int j = 0; j<i; j++){
						tempCur = tempCur.next;
					} DLLCNode<Integer> tempNode = tempCur.next;
					if(tempCur.prev!=null){
						tempCur.prev.cnext(tempNode);
					} else {
						temp.head = tempNode;
					} if(tempNode.next!=null){
						tempNode.next.cprev(tempCur);
					} else {
						temp.tail = tempCur;
					} tempCur.cnext(tempNode.next);
					tempNode.cprev(tempCur.prev);
					tempCur.cprev(tempNode);
					tempNode.cnext(tempCur);
					if(!trash.contains(temp)){
						if(!queue.contains(temp)){
							queue.add(temp);
						} trash.add(temp);
					}
				} if((cur.next.elem-cur.elem==1 && cur.next.next.elem-cur.next.elem==-1)
				  || (cur.next.elem-cur.elem==-1 && cur.next.next.elem-cur.next.elem==1)){
					DLLC<Integer> temp = new DLLC<Integer>();
					DLLCNode<Integer> tempCur = pop.head;
					for(int j = 0; j<pop.size; j++){
						temp.addLast(tempCur.elem);
						tempCur = tempCur.next;
					} tempCur = temp.head; 
					for(int j = 0; j<i; j++){
						tempCur = tempCur.next;
					} tempCur.celem(tempCur.elem + (tempCur.next.elem-tempCur.elem));
					tempCur.next.celem(tempCur.next.next.elem + (tempCur.elem-tempCur.next.elem));
					int tempInt = tempCur.elem + 1 - 1;
					tempCur.next.next.celem(tempInt);
					if(!trash.contains(temp)){
						if(!queue.contains(temp)){
							queue.add(temp);
						} trash.add(temp);
					}
				} cur = cur.next;
			} if(cur.elem - cur.next.elem > 1 || cur.next.elem - cur.elem > 1){
				DLLC<Integer> temp = new DLLC<Integer>();
				DLLCNode<Integer> tempCur = pop.head;
				for(int j = 0; j<pop.size; j++){
					temp.addLast(tempCur.elem);
					tempCur = tempCur.next;
				} tempCur = temp.head; 
				for(int j = 0; j<i; j++){
					tempCur = tempCur.next;
				} DLLCNode<Integer> tempNode = tempCur.next;
				if(tempCur.prev!=null){
					tempCur.prev.cnext(tempNode);
				} else {
					temp.head = tempNode;
				} if(tempNode.next!=null){
					tempNode.next.cprev(tempCur);
				} else {
					temp.tail = tempCur;
				} tempCur.cnext(tempNode.next);
				tempNode.cprev(tempCur.prev);
				tempCur.cprev(tempNode);
				tempNode.cnext(tempCur);
				if(!trash.contains(temp)){
					if(!queue.contains(temp)){
						queue.add(temp);
					} trash.add(temp);
				}
			} if(cur.elem.equals(cur.next.elem)){
				DLLC<Integer> temp = new DLLC<Integer>();
				cur = pop.head;
				for(int j = 0; j<pop.size-2; j++){
					temp.addLast(cur.elem);
					cur = cur.next;
				} return tryToDecrease(temp);
			}
		} //SLLNode<DLL<Integer>> trashCur = trash.head;
		System.out.println("\nTrash size: "+trash.size);
		/**for(int i = 0; i<trash.size; i++){
			DLLNode<Integer> trashCur2 = trashCur.elem.head;
			System.out.println();
			for(int j = 0; j <trashCur.elem.size; j++){
				System.out.print(trashCur2.elem+1+" ");
				trashCur2 = trashCur2.next;
			} trashCur = trashCur.next;
		}**/
		System.out.println("There were "+counter + " iterations of the while loop.");
		return trash;
	}
	
	/*** Old Stuff
	public DLL<Integer> generateAdjTrans(SLL<transP> transps){
		DLL<Integer> adjTrans = new DLL<Integer>();
		SLLNode<transP> cur = transps.head;
		for(int i = 0; i<transps.size; i++){
			int start = cur.elem.a;
			int end = cur.elem.b;
			for(int j = start; j<end; j++){
				System.out.print((j+1) + " ");
				if(adjTrans.size>0 && adjTrans.tail.elem.equals(j)){
					adjTrans.deleteLast();
				} else {
					adjTrans.addLast(j);
				}
			} for(int j = end-2; j>= start; j--){
				System.out.print((j+1) + " ");
				adjTrans.addLast(j);
			} cur = cur.next;
		} System.out.println();
		return adjTrans;
	}
	
	public SLL<DLL<Integer>> tryToDecrease(DLL<Integer> cycle){
		System.out.print("\nStart cycle: ");
		DLLNode<Integer> print = cycle.head;
		for(int i = 0; i<cycle.size; i++){
			System.out.print(print.elem+1+" ");
			print = print.next;
		}
		SLL<DLL<Integer>> trash = new SLL<DLL<Integer>>();
		DLL<DLL<Integer>> queue = new DLL<DLL<Integer>>();
		queue.addLast(cycle);
		trash.addFirst(cycle);
		int counter = 0;
		while(queue.size>0){
			counter++;
			if(queue.size%10000==0 || counter % 1000 == 0){
				System.out.print("\nQueue size: "+queue.size+" Length: "+trash.head.elem.size+" Trash size: "+trash.size);
			}
			DLL<Integer> pop = queue.deleteFirst();
			System.out.print("");
			DLLNode<Integer> poprun = pop.head;
			for(int i = 0; i<pop.size; i++){
				System.out.print((poprun.elem+1) + " ");
				poprun = poprun.next;
			}
			DLLNode<Integer> cur = pop.head;
			int i;
			for(i = 0; i<pop.size-2; i++){
				if(cur.elem.equals(cur.next.elem)){
					DLL<Integer> dll = new DLL<Integer>();
					DLLNode<Integer> tempCur = pop.head;
					for(int j = 0; j<i; j++){
						dll.addLast(tempCur.elem);
						tempCur = tempCur.next;
					} tempCur = tempCur.next;
					tempCur = tempCur.next;
					for(int j = i+2; j<pop.size; j++){
						dll.addLast(tempCur.elem);
						tempCur = tempCur.next;
					} System.out.println("\nDown to size "+dll.size);
					return tryToDecrease(dll);
				} if(cur.elem - cur.next.elem > 1 || cur.next.elem - cur.elem > 1){
					DLL<Integer> temp = new DLL<Integer>();
					DLLNode<Integer> tempCur = pop.head;
					for(int j = 0; j<pop.size; j++){
						temp.addLast(tempCur.elem);
						tempCur = tempCur.next;
					} tempCur = temp.head; 
					for(int j = 0; j<i; j++){
						tempCur = tempCur.next;
					} DLLNode<Integer> tempNode = tempCur.next;
					if(tempCur.prev!=null){
						tempCur.prev.cnext(tempNode);
					} else {
						temp.head = tempNode;
					} if(tempNode.next!=null){
						tempNode.next.cprev(tempCur);
					} else {
						temp.tail = tempCur;
					} tempCur.cnext(tempNode.next);
					tempNode.cprev(tempCur.prev);
					tempCur.cprev(tempNode);
					tempNode.cnext(tempCur);
					if(!trash.contains(temp)){
						if(!queue.contains(temp)){
							queue.addLast(temp);
						} trash.addFirst(temp);
					}
				} if((cur.next.elem-cur.elem==1 && cur.next.next.elem-cur.next.elem==-1)
				  || (cur.next.elem-cur.elem==-1 && cur.next.next.elem-cur.next.elem==1)){
					DLL<Integer> temp = new DLL<Integer>();
					DLLNode<Integer> tempCur = pop.head;
					for(int j = 0; j<pop.size; j++){
						temp.addLast(tempCur.elem);
						tempCur = tempCur.next;
					} tempCur = temp.head; 
					for(int j = 0; j<i; j++){
						tempCur = tempCur.next;
					} tempCur.celem(tempCur.elem + (tempCur.next.elem-tempCur.elem));
					tempCur.next.celem(tempCur.next.next.elem + (tempCur.elem-tempCur.next.elem));
					int tempInt = tempCur.elem + 1 - 1;
					tempCur.next.next.celem(tempInt);
					if(!trash.contains(temp)){
						if(!queue.contains(temp)){
							queue.addLast(temp);
						} trash.addFirst(temp);
					}
				} cur = cur.next;
			} if(cur.elem - cur.next.elem > 1 || cur.next.elem - cur.elem > 1){
				DLL<Integer> temp = new DLL<Integer>();
				DLLNode<Integer> tempCur = pop.head;
				for(int j = 0; j<pop.size; j++){
					temp.addLast(tempCur.elem);
					tempCur = tempCur.next;
				} tempCur = temp.head; 
				for(int j = 0; j<i; j++){
					tempCur = tempCur.next;
				} DLLNode<Integer> tempNode = tempCur.next;
				if(tempCur.prev!=null){
					tempCur.prev.cnext(tempNode);
				} else {
					temp.head = tempNode;
				} if(tempNode.next!=null){
					tempNode.next.cprev(tempCur);
				} else {
					temp.tail = tempCur;
				} tempCur.cnext(tempNode.next);
				tempNode.cprev(tempCur.prev);
				tempCur.cprev(tempNode);
				tempNode.cnext(tempCur);
				if(!trash.contains(temp)){
					if(!queue.contains(temp)){
						queue.addLast(temp);
					} trash.addFirst(temp);
				}
			} if(cur.elem.equals(cur.next.elem)){
				DLL<Integer> temp = new DLL<Integer>();
				cur = pop.head;
				for(int j = 0; j<pop.size-2; j++){
					temp.addLast(cur.elem);
					cur = cur.next;
				} return tryToDecrease(temp);
			}
		} //SLLNode<DLL<Integer>> trashCur = trash.head;
		System.out.println("\nTrash size: "+trash.size);
		for(int i = 0; i<trash.size; i++){
			DLLNode<Integer> trashCur2 = trashCur.elem.head;
			System.out.println();
			for(int j = 0; j <trashCur.elem.size; j++){
				System.out.print(trashCur2.elem+1+" ");
				trashCur2 = trashCur2.next;
			} trashCur = trashCur.next;
		}
		System.out.println("There were "+counter + " iterations of the while loop.");
		return trash;
	}	***/
}