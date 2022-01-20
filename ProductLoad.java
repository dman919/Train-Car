/**
 * David Man 111940002 RO3
 *The Product Load class that creates the product load for car
 */

public class ProductLoad {
	private String name;
	private double weight;
	private double value;
	private boolean isDangerous;
	
	public ProductLoad() {
		name = "Empty";
		weight = 0.0;
		value = 0.0;
		isDangerous = false;
	}
	
	public ProductLoad (
	  String name, double weight, double value, boolean isDangerous)
	  throws IllegalArgumentException {
		this.setName(name);
		this.setWeight(weight);
		this.setValue(value);
		this.setDangerous(isDangerous);
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public double getWeight() {
		return weight;
	}
	/**
	 * Product weight mutator method
	 * @param weight
	 *   The weight of the product
	 * @throws IllegalArgumentException
	 *   Indicates if weight is a negative value
	 */
	public void setWeight(double weight) 
	  throws IllegalArgumentException {
		if (weight < 0) {
			throw new IllegalArgumentException
			  ("Invalid Weight, Try Again");
			}
		
		this.weight = weight;
	}

	public double getValue() {
		return value;
	}
	/**
	 * Product value mutator method
	 * @param value
	 *   The value of the product
	 * @throws IllegalArgumentException
	 *   Indicates if value is a negative value
	 */
	public void setValue(double value)
	  throws IllegalArgumentException {
		if (value < 0) {
			throw new IllegalArgumentException
			  ("Invalid Value, Try Again");
		}
	
		this.value = value;
	}

	public boolean isDangerous() {
		return isDangerous;
	}
	public void setDangerous(boolean isDangerous) {
		this.isDangerous = isDangerous;
	}
}