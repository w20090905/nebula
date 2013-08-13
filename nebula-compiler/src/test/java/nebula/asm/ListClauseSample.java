package nebula.asm;

import java.util.List;

import nebula.data.Entity;
import nebula.lang.Nebula;

public class ListClauseSample {

	public List<Entity> search(List<Entity> in) {
		int i= 1000;
		List<Entity>  out = Nebula.filter(in, new EntityFunctionSample(),i+10,i+11,i+12,i+13,i+14,i+15,i+16,i+17,i+18,i+19,i+20);		
		return out;
	}
}
