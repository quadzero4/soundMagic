package w0106;

import java.util.Vector;

public class makePair {
	Vector<Pair> pa;
	makePair(Vector<Pair> pa){
		this.pa = pa;
	}
	public void maker(int people) {
		
			int i=0;
			//pa.add(new Pair());
			for(int j=0; j<people; j++) {
				for(int x=1; x<people && j<x ; x++) {
					Pair p = new Pair();
					p.x = j;
					p.y = x;
					pa.add(p);
					i++;
				}
			}
		
	}
}
