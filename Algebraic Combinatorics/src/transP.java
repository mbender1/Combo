
public class transP {

	protected int a;
	protected int b;
	
	public transP(int c, int d){
		a = c;
		b = d;
		this.reset();
	}
	
	public void set(int c, int d){
		a = c;
		b = d;
		this.reset();
	}
		
	public void reset(){
		int dif = b-a;
		if(dif<0){
			a = b;
			b = a - dif;
		}
	}
}