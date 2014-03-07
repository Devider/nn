package nn.common;

import java.util.ArrayList;
import java.util.List;


public abstract class EntryPoint<D extends Dendrite>  {
	private double signal;

	private List<D> linkedDendrites= new ArrayList<>();

	protected void notifyDendrites(){
		for (D a : linkedDendrites){
			a.setNewSignal(signal);
		}
	}

	public void addListener(D dendrite) {
		linkedDendrites.add(dendrite);
	}

	public void setSignal(double signal) {
		this.signal = signal;
		notifyDendrites();
	}

	protected List<D> dendrites(){
		return linkedDendrites;
	}

	protected D dendrite(int i){
		return linkedDendrites.get(i);
	}

}
