/**
 * David Man 111940002 RO3
 *The Train Linked List class that creates the linked list for train
 */

public class TrainLinkedList {
	//An object that represents the first node in the list
	private TrainCarNode head;
	//An object that represents the last node in the list
	private TrainCarNode tail;
	//An object that represents the cursor node in the list
	private TrainCarNode cursor;
	//A variable that represents the arrow pointing towards the cursor
	private int arrow;
	//A variable that keeps track of the number of cars on the train
	private int size;
	//A variable that keeps track of the total length of the train
	private double totalLength;
	//A variable that keeps track of the total weight of the train
	private double totalWeight;
	//A variable that keeps track of the total value of the products
	private double totalValue;
	//A variable that checks if there is dangerous product on the train
	private boolean dangerous;
	
	public TrainLinkedList() {
		head = null;
		tail = null;
		cursor = null;
		arrow = 0;
		size = 0;
		totalLength = 0.0;
		totalWeight = 0.0;
		totalValue = 0.0;
		dangerous = false;
	}
	
	public TrainCar getCursorData() {
		if (cursor != null) {
			return cursor.getCar();
		}
		else {
			throw new IllegalArgumentException("cursor is null");
		}
	}
	public void setCursorData(TrainCar car) {
		if (cursor != null) {
			cursor.setCar(car);
		}
		else {
			throw new IllegalArgumentException("cursor is null");
		}
	}

	/**
	 * CursorForward method for moving the cursor forward
	 */
	public void cursorForward() {
		if (cursor != null && cursor != tail) {
			cursor = cursor.getNext();
			arrow++;
			System.out.println("Cursor moved forward\n");
		}
		else {
			cursor = tail;
			System.out.println(
			  "No next car, cannot move cursor foward.\n");
			}
	}

	/**
	 * CursorBackward method for moving the cursor backward
	 */
	public void cursorBackward() {
		if (cursor != null && cursor != head) {
			cursor = cursor.getPrev();
			arrow--;
			System.out.println("Cursor moved backward\n");
		}
		else {
			cursor = head;
			System.out.println(
			  "No previous car, cannot move cursor backward.\n");
		}
	}

	/**
	 * InsertAfterCursor method for creating new train car and inserting it
	 * @param newCar
	 *   TrainCar object for adding new train car to train
	 * @throws IllegalArgumentException
	 *   Indicates if TrainCar object being added is null
	 */
	public void insertAfterCursor(TrainCar newCar)
	  throws IllegalArgumentException {
		TrainCarNode newNode = new TrainCarNode(newCar);
		
		if (newCar == null) {
			throw new IllegalArgumentException("Invalid Car, Try Again");
		}
		
		if (cursor == null) {
			head = newNode;
			tail = newNode;
			cursor = newNode;
		}
		else if (cursor.getNext() == null) {
			newNode.setPrev(cursor);
			newNode.setNext(null);
			cursor.setNext(newNode);
			cursor = newNode;
		}
		else {
			newNode.setPrev(cursor);
			newNode.setNext(cursor.getNext());
			cursor.getNext().setPrev(newNode);
			cursor.setNext(newNode);
			cursor = newNode;
		}
		
		if (cursor.getPrev() == null) {
			head = cursor;
		}
		if (cursor.getNext() == null) {
			tail = cursor;
		}
		
		size++;
		arrow++;
		totalLength += newCar.getCarLength();
		totalWeight += newCar.getCarWeight();

		System.out.println(
		  "\nNew train car " + newCar.getCarLength() +
		  " meters " + newCar.getCarWeight() + 
		  " tons inserted into train.\n");
	}

	/**
	 * RemoveCursor method for removing car from train at cursor
	 * @return
	 *   Returns the car that was removed from train
	 */
	public TrainCar removeCursor() {
		TrainCar temp = new TrainCar();
		if (cursor == null) {
			throw new IllegalArgumentException("cursor is null");
		}
		else {
			if (cursor == head && cursor == tail) {
				temp = getCursorData();
				cursor = null;
				head = null;
				tail = null;
				arrow--;
			}
			else if (cursor == head) {
				temp = getCursorData();
				cursor.getNext().setPrev(null);
				cursor = cursor.getNext();
				head = cursor;
			}
			else if (cursor == tail) {
				temp = getCursorData();
				cursor.getPrev().setNext(null);
				cursor = cursor.getPrev();
				tail = cursor;
				arrow--;
			}
			else {
				temp = getCursorData();
				cursor.getPrev().setNext(cursor.getNext());
				cursor.getNext().setPrev(cursor.getPrev());
				cursor = cursor.getNext();
			}
			
			size--;
			totalLength -= temp.getCarLength();
			totalValue -= temp.getLoad().getValue();
			totalWeight -= (temp.getLoad().getWeight() +
			  temp.getCarWeight());
			return temp;
		}
	}

	/**
	 * Size method to return number of cars on train currently
	 * @return
	 *   Returns the number of cars
	 */
	public int size() {
		return size;
	}

	/**
	 * GetLength method to find the total length of cars on train
	 * @return
	 *   Returns the total length of all the cars
	 */
	public double getLength() {
		return totalLength;
	}
	
	/**
	 * GetValue method to find the total value of product on train
	 * @return
	 *   Returns the total value of all the products
	 */
	public double getValue() {
		return totalValue;
	}	

	/**
	 * GetWeight method to find total weight of cars and product on train
	 * @return
	 *   Returns the total weight of all the cars and products
	 */
	public double getWeight() {
		return totalWeight;
	}

	/**
	 * IsDangerous method to find any dangerous products on train
	 * @return
	 *   Boolean is true if dangerous product is found
	 *     and false if none are found
	 */
	public boolean isDangerous() {
		return dangerous;
	}
	
	/**
	 * FindProduct method to find total weight and value and if dangerous
	 *   for specified name of product
	 * @param name
	 *   String for finding specific product
	 */
	public void findProduct(String name) {
		TrainCarNode temp = new TrainCarNode();
		temp = cursor;
		cursor = head;
		double totalWeight = 0;
		double totalValue = 0;
		boolean danger = false;
		int count = 0;
				
		for (int i = 0; i < size(); i++) {
			if (getCursorData().getLoad().getName().equals(name)) {
				if (totalWeight == 0) {
					danger = getCursorData().getLoad().isDangerous();
				}
				totalWeight += getCursorData().getLoad().getWeight();
				totalValue += getCursorData().getLoad().getValue();
				count++;
			}
			cursor = cursor.getNext();
		}
		
		cursor = temp;
		if (totalWeight == 0 && totalValue == 0) {
			System.out.println("\nNo record of " + 
			  name + " on board train.\n");
		}
		else {
			System.out.println(
			  "\nThe following products were found on on " +
			  count + " cars:\n");
			System.out.println(
			  "        Name      Weight (t)     Value ($)   Dangerous\n"
			  + "    ===================================================");
			System.out.println(String.format(
			  "          %-7s %10s %13s %11s\n",
			  name, totalWeight, totalValue, printDangerous(danger)));
		}
	}

	/**
	 * Print method for printing all the cars on train
	 */
	public void printManifest() {
		System.out.println(toString());
	}
	
	/**
	 * RemoveDangerousCars method for removing all cars with dangerous product
	 */
	public void removeDangerousCars() {
		cursor = head;
		arrow = 0;
		
		for (int i = 0; i < size(); i++) {
			if (getCursorData().getLoad().isDangerous()) {
				removeCursor();
			}
			else {
				cursor = cursor.getNext();
			}
			arrow++;
		}
		
		cursor = head;
		for (int i = 1; i < arrow; i++) {
			cursor = cursor.getNext();
		}

		System.out.println(
		  "Dangerous cars successfully removed from the train.\n");
	}

	/**
	 * ToString method for creating formatted table of cars on train
	 */
	public String toString() {
		TrainCarNode temp = new TrainCarNode();
		temp = cursor;
		cursor = head;
		String s = 
		  "    CAR:                               LOAD:\n" +
		  "      Num   Length (m)    Weight (t)  |" +
		  "    Name      Weight (t)     Value ($)   Dangerous" +
		  "\n    ==================================+" +
		  "===================================================\n";
		
		for (int i = 0; i < size(); i++) {
			if ((i + 1) == arrow) {
				s += (String.format(" %-3s%4s%14s%14s  ",
				  "->", (i + 1),
				  getCursorData().getCarLength(),
				  getCursorData().getCarWeight()));
			}
			else {
				s += (String.format("    %4s%14s%14s  ",
				  (i + 1),
				  getCursorData().getCarLength(),
				  getCursorData().getCarWeight()));
			}

			s += (String.format("|%10s %13s %13s %11s\n",
			  getCursorData().getLoad().getName(),
			  getCursorData().getLoad().getWeight(),
			  getCursorData().getLoad().getValue(),
			  printDangerous(getCursorData().getLoad().isDangerous())));

			cursor = cursor.getNext();
		}
		
		cursor = temp;
		return s;
	}
	
	/**
	 * PrintDangerous method for printing YES or NO based on dangerousness
	 * @param isDangerous
	 *   boolean value for product
	 * @return
	 *   Returns "YES" if product is dangerous and
	 *     "NO" if product is not dangerous
	 */
	public String printDangerous(boolean isDangerous) {
		String s = "";
		if (isDangerous) {
			s = "YES";
		}
		else if (!isDangerous) {
			s = "NO";
		}
		
		return s;
	}

	public double getTotalLength() {
		return totalLength;
	}
	public void setTotalLength(double totalLength) {
		this.totalLength = totalLength;
	}

	public double getTotalValue() {
		return totalValue;
	}
	public void setTotalValue(double totalValue) {
		this.totalValue = totalValue;
	}

	public double getTotalWeight() {
		return totalWeight;
	}
	public void setTotalWeight(double totalWeight) {
		this.totalWeight = totalWeight;
	}

	public boolean getDangerous() {
		return dangerous;
	}
	public void setDangerous(boolean dangerous) {
		this.dangerous = dangerous;
	}
}