/**
 * David Man 111940002 RO3
 *The Train Car Node class that creates car node for the train linked list
 */

public class TrainCarNode {
	private TrainCarNode prev;
	private TrainCarNode next;
	private TrainCar car;
	
	public TrainCarNode() {
		prev = null;
		next = null;
		car = null;
	}
	public TrainCarNode(TrainCar car) {
		this.car = car;
	}
	
	public TrainCarNode getPrev() {
		return prev;
	}
	public void setPrev(TrainCarNode prev) {
		this.prev = prev;
	}
	
	public TrainCarNode getNext() {
		return next;
	}
	public void setNext(TrainCarNode next) {
		this.next = next;
	}
	
	public TrainCar getCar() {
		return car;
	}
	public void setCar(TrainCar car) {
		this.car = car;
	}
	
	public String toString() {
		TrainLinkedList train = new TrainLinkedList();
		String s = 
		  "        Name      Weight (t)     Value ($)   Dangerous\n"+
		  "    ===================================================";
		s += (String.format("\n          %-7s %10s %13s %11s\n",
			  getCar().getLoad().getName(),
			  getCar().getLoad().getWeight(),
			  getCar().getLoad().getValue(),
			  train.printDangerous(getCar().getLoad().isDangerous())));

		return s;
	}
}
