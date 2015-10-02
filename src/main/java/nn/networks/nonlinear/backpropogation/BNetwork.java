package nn.networks.nonlinear.backpropogation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import nn.common.INetwork;
import nn.common.utils.MathUtils;

public class BNetwork implements INetwork{

	private static final long serialVersionUID = -8815833917712316294L;
	private final BLayer[] layers;

	public BNetwork(BNeuronFactory factory, int...layers){
		this.layers = new BLayer[layers.length - 1];
		for (int i = 0; i < layers.length - 1; i++){
			this.layers[i] = new BLayer(layers[i], layers[i + 1], factory);
		}
	}


	public BNetwork(BLayer...layers){
		this.layers = new BLayer[layers.length];
		for (int i = 0; i < layers.length; i++){
			this.layers[i] = layers[i];
		}
	}

	@Override
	public double[] test(double...data) {
		double[] outs = data;

		for (int i = 0; i < layers.length; i++){
			outs = layers[i].test(outs);
		}

		return outs;
	}
	
	public BigDecimal[] roundTest(double...data) {
		return round(test(data));
	}

	@Override
	public double result(int i) {
		return lastLayer().out(i);
	}

	@Override
	public void teach(double[][] data, double[][] targets, int count) {
		for (int i = 0; i < count; i++){
			for (int k = 0; k < data.length; k++){
				test(data[k]);
				backpropogate(targets[k]);
			}
		}
	}

	public void teach(double[] data, double[] targets) {
		test(data);
		backpropogate(targets);
	}
	
	public int getNeuronCountAt(int layer){
		if (layer > layers.length)
			throw new IllegalArgumentException("Only " + layers.length + "layers available!");
		return layers[layer].getNeuronsCount();
	}

	protected void backpropogate(double[] data){
		double[] outs = MathUtils.diff(lastLayer().outs(), data);

		for (int i = layers.length - 1; i >= 0; i--){
			outs = layers[i].backpropogate(outs);
		}
	}

	private BLayer lastLayer(){
		return layers[layers.length - 1];
	}

	private BigDecimal[] round(double[] values) {
		BigDecimal[] result = new BigDecimal[values.length];
		for (int i = 0; i < values.length; i++){
			result[i] = new BigDecimal(values[i], new MathContext(2,RoundingMode.HALF_UP));
		}
		return result;
	}
	
	public void save(String filename) throws IOException{
	    FileOutputStream fos = new FileOutputStream(filename);
	    ObjectOutputStream oos = new ObjectOutputStream(fos);
	    oos.writeObject(this);
	    oos.flush();
	    oos.close();
	}
	
	public static BNetwork load(String filename) throws Exception{
		FileInputStream fis = new FileInputStream(filename);
		ObjectInputStream oin = new ObjectInputStream(fis);
		BNetwork obj = (BNetwork) oin.readObject();
		oin.close();
		return obj;
	}
}
