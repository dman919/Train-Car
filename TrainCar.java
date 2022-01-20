/**
 * David Man 111940002 RO3
 *The Train Car class that creates the car for train
 */

public class TrainCar {
	private double carLength;
	private double carWeight;
	private ProductLoad load = new ProductLoad();
	
	public TrainCar() {
		carLength = 0.0;
		carWeight = 0.0;
	}
	
	public TrainCar(double carLength, double carWeight) {
		this.carWeight = carWeight;
		this.carLength = carLength;
	}

	public double getCarLength() {
		return carLength;
	}

	public double getCarWeight() {
		return carWeight;
	}

	public ProductLoad getLoad() {
		return load;
	}
	/**
	 * Product load mutator method
	 * @param load
	 *   The load on the car
	 */
	public void setLoad(ProductLoad load) {
		this.load = load;
	}
	
	public boolean isEmpty() {
		if (load == null) {
			return true;
		}
		return false;
	}
}