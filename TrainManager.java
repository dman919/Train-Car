/**
 * David Man 111940002 RO3
 * The Train Manager class that manages the train
 */

import java.util.Scanner;

public class TrainManager {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		
		TrainLinkedList train = new TrainLinkedList();
		int i = 0;
		
		while (i == 0) {
			//Menu for train
			System.out.print(
			  "F - Cursor Forward\n" + 
			  "B - Cursor Backward\n" + 
			  "I - Insert Car After Cursor\n" +
			  "R - Remove Car At Cursor\n" + 
			  "L - Set Product Load\n" +
			  "S - Search For Product\n" + 
			  "T - Display Train\n" + 
			  "M - Display Manifest\n" + 
			  "D - Remove Dangerous Cars\n" + 
			  "Q - Quit\n" +
			  "\nEnter a selection: ");
			String selection = input.next();
			System.out.println();
			
			//moves cursor forward
			if (selection.equals("F") || selection.equals("f")) {
				train.cursorForward();
			}
			
			//moves cursor backward
			if (selection.equals("B") || selection.equals("b")) {
				train.cursorBackward();
			}
			
			//adds car to train after cursor
			if (selection.equals("I") || selection.equals("i")) {
				System.out.print("Enter car length in meters: ");
				double length = input.nextDouble();
				System.out.print("Enter car weight in tons: ");
				double weight = input.nextDouble();

				TrainCar newCar = new TrainCar(length, weight);
				train.insertAfterCursor(newCar);
			}
			
			//removes car from train at cursor
			if (selection.equals("R") || selection.equals("r")) {
				TrainCarNode temp = new TrainCarNode(train.removeCursor());
				
				System.out.println(
				  "Car successully unlinked. " +
				  "The following load has been removed from the train:\n");
				System.out.println(temp.toString());
				}
			
			//adds product to car at cursor
			if (selection.equals("L") || selection.equals("l")) {
				System.out.print("Enter product name: ");
				String name = input.next();

				System.out.print("Enter product weight in tons: ");
				double weight = input.nextDouble();
				while (weight < 0) {
					try {
						train.getCursorData().getLoad().setWeight(weight);
					}
					catch (Exception e) {
						System.out.println(e.getMessage());
						System.out.print(
						  "Enter product weight in tons: ");
						weight = input.nextDouble();
					}
				}

				System.out.print("Enter product value in dollars: ");
				double value = input.nextDouble();
				while (value < 0) {
					try {
						train.getCursorData().getLoad().setValue(value);
					}
					catch (Exception e) {
						System.out.println(e.getMessage());
						System.out.print("Enter product value in dollars: ");
						value = input.nextDouble();
					}
				}
				
				boolean dangerous = false;
				System.out.print("Enter is product dangerous? (y/n): ");
				char isDangerous = input.next().charAt(0);
				
				if (isDangerous == 'Y' || isDangerous == 'y') {
					dangerous = true;
				}
				if (isDangerous == 'N' || isDangerous == 'n') {
					dangerous = false;
				}
				
				train.setTotalValue(value + train.getTotalValue());
				train.setTotalWeight(weight + train.getTotalWeight());
				if (dangerous == true) {
					train.setDangerous(true);
				}
				
				System.out.println();
				ProductLoad newLoad = new 
				  ProductLoad(name, weight, value, dangerous);
				
				train.getCursorData().setLoad(newLoad);
				
				System.out.println(
				  weight + " tons of " + 
				  name + " added to the current car.\n");
			}
			
			//searches for specified product
			if (selection.equals("S") || selection.equals("s")) {
				System.out.print("Enter product name: ");
				String name = input.next();
				
				train.findProduct(name);
			}
			
			//prints train summary
			if (selection.equals("T") || selection.equals("t")) {
				String s = "";
				if (train.isDangerous()) {
					s = "DANGEROUS.";
				}
				else {
					s = "not dangerous.";
				}
				System.out.println(
				  "Train: " + train.size() + " cars, " +
				  train.getLength() + " meters, " +
				  train.getTotalWeight() + " tons, $" +
				  train.getValue() + " value, " +
				  s + "\n");
			}
			
			//prints all cars
			if (selection.equals("M") || selection.equals("m")) {
				train.printManifest();
			}
			
			//removes dangerous cars
			if (selection.equals("D") || selection.equals("d")) {
				train.removeDangerousCars();
			}
			
			//ends the program
			if (selection.equals("Q") || selection.equals("q")) {
				System.out.print("Program terminating successfully...");
				System.exit(i);;
			}
		}
	}
}
