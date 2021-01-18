package hashmap;

import java.util.List;

public class Car {
		/******* CLASS VARIABLES *************************************************************************/
		private String plateNumber = null;
		private String make = null;
		private String model = null;
		private String color = null;
		private String year = null;
		private String price = null;
		
		/******* GETTERS AND SETTERS *********************************************************************/
		public String getPlateNumber() {
			return plateNumber;
		}
		public String getMake() {
			return make;
		}
		public String getModel() {
			return model;
		}
		public String getColor() {
			return color;
		}
		public String getYear() {
			return year;
		}
		public String getPrice() {
			return price;
		}
		
		/******* CONSTRUCTORS ************************************************************************/
		public Car(String plateNumber, String make, String model, String color, String year, String price){
			this.plateNumber = plateNumber;
			this.make = make;
			this.model = model;
			this.color = color;
			this.year = year;
			this.price = price;
		}
		
		/******* MISC METHODS *************************************************************************/
		@Override
		public String toString() {
			StringBuffer buf = new StringBuffer();
				buf.append("Plate Number: ");
				buf.append(getPlateNumber());
				buf.append("\n");
				buf.append("Make: ");
				buf.append(getMake());
				buf.append("\n");
				buf.append("Model: ");
				buf.append(getModel());
				buf.append("\n");
				buf.append("Year: ");
				buf.append(getYear());
				buf.append("\n");
				buf.append("Price: ");
				buf.append(getPrice());
				buf.append("\n");
			return buf.toString();
		}
}
