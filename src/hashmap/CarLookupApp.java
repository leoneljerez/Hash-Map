package hashmap;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import oracle.jrockit.jfr.JFR;

public class CarLookupApp extends JFrame implements ActionListener {
	/******* CLASS VARIABLES ******************************************/
	private JLabel lblPlateNumber = null;
	private JLabel lblMake = null;
	private JLabel lblModel = null;
	private JLabel lblColor = null;
	private JLabel lblYear = null;
	private JLabel lblPrice = null;
	private JTextField txtPlateNumber = null;
	private JButton btnSearch = null;
	private JPanel pnlMain = null; 
	private SimpleIntegerKeyedHashMap<Car> carMap = null;

	/******* GETTERS AND SETTTERS **************************************/
	public JLabel getLblPlateNumber() {
		if (lblPlateNumber == null){
			lblPlateNumber = new JLabel();
		}
		return lblPlateNumber;
	}
	public JLabel getLblMake() {
		if (lblMake == null){
			lblMake = new JLabel();
		}
		return lblMake;
	}
	public JLabel getLblModel() {
		if (lblModel == null){
			lblModel = new JLabel();
		}
		return lblModel;
	}
	public JLabel getLblColor() {
		if (lblColor == null){
			lblColor = new JLabel();
		}
		return lblColor;
	}
	public JLabel getLblYear() {
		if (lblYear == null){
			lblYear = new JLabel();
		}
		return lblYear;
	}
	public JLabel getLblPrice() {
		if (lblPrice == null){
			lblPrice = new JLabel();
		}
		return lblPrice;
	}
	public JTextField getTxtPlateNumber() {
		if(txtPlateNumber == null){
			txtPlateNumber = new JTextField(10);
		}
		return txtPlateNumber;
	}
	public JButton getBtnSearch() {
		if(btnSearch == null){
			btnSearch = new JButton("Search");
			btnSearch.addActionListener(this);
		}
		return btnSearch;
	}
	public JPanel getPnlMain(){
		if(pnlMain == null){
			pnlMain = new JPanel();
			pnlMain.setLayout(new GridBagLayout());
			Insets insets = new Insets(5,5,0,0);
			GridBagConstraints gbc = new GridBagConstraints(0, 0, 2, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, insets, 0, 0);
			pnlMain.add(new JLabel("Enter license plate number: "), gbc);
			gbc.gridwidth = 1;
			gbc.gridx = 2;
			pnlMain.add(getTxtPlateNumber(), gbc);
			gbc.gridx = 3;
			pnlMain.add(getBtnSearch(), gbc);
			gbc.gridx = 0;
			gbc.gridy = 1;
			pnlMain.add(new JLabel("Plate number: "), gbc);
			gbc.gridx = 1;
			pnlMain.add(getLblPlateNumber(), gbc);
			gbc.gridx = 0;
			gbc.gridy = 2;
			pnlMain.add(new JLabel("Make: "), gbc);
			gbc.gridx = 1;
			pnlMain.add(getLblMake(), gbc);
			gbc.gridx = 0;
			gbc.gridy = 3;
			pnlMain.add(new JLabel("Model: "), gbc);
			gbc.gridx = 1;
			pnlMain.add(getLblModel(), gbc);
			gbc.gridx = 0;
			gbc.gridy = 4;
			pnlMain.add(new JLabel("Color: "), gbc);
			gbc.gridx = 1;
			pnlMain.add(getLblColor(), gbc);
			gbc.gridx = 0;
			gbc.gridy = 5;
			pnlMain.add(new JLabel("Year: "), gbc);
			gbc.gridx = 1;
			pnlMain.add(getLblYear(), gbc);
			gbc.gridx = 0;
			gbc.gridy = 6;
			pnlMain.add(new JLabel("Price: "), gbc);
			gbc.gridx = 1;
			pnlMain.add(getLblPrice(), gbc);

		
		}
		return pnlMain;
	}
	
	/////////****STILL NOT DONE****/
	public SimpleIntegerKeyedHashMap<Car> getCarMap(){
	    carMap = new SimpleIntegerKeyedHashMap<Car>(5000);
		return carMap;
	}
	
	/******* CONSTRUCTORS ******************************************/
	public CarLookupApp() {
		initialize();
	}

	/******* MISC METHODS ******************************************/
	private void initialize(){
		setSize(new Dimension(400,200));
		setTitle("Car Lookup App");
		setContentPane(getPnlMain());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		loadCars("C:\\Users\\leone\\Desktop\\Data Structure\\HashMap Homework\\bin\\hashmap\\cars.csv");	
	}
	
	/**
	 * Creates an Integer key we can use to lookup a Car from the 
	 * Car map. It does this by using the hashCode method of the
	 * String class.  Since values might be negative, we should return
	 * the absolute value of the hashcode.
	 * @return
	 */
	private Integer createKeyFromPlateNumber(String plateNum){
		return new Integer(Math.abs(plateNum.hashCode()));	
	}
	
	/****** UI METHODS *********************************************/
	/**
	 * Clears all of the data labels.  Should be called when Search Button is clicked
	 * before setting new values (since the serach resutl may be empty). 
	 */
	private void clearLabels(){
		getLblPlateNumber().setText("");
		getLblMake().setText("");
		getLblModel().setText("");
		getLblColor().setText("");
		getLblYear().setText("");
		getLblPrice().setText("");
	}
	/**
	 * Creates an integer key from the plate number String passed in (using the 
	 * createKeyFromPlateNumber() method) and then uses this key to get a Car object
	 * from the hashmap.  If the Car object is not null, it should extract all of the 
	 * information from the Car and set each of the labels used to display this
	 * information (i.e., plate number, make, model, etc.). To display text on a label, 
	 * call the setText(String text) method on it (e.g., getLblModel().setText(some string).
	 * @param plateNum
	 */
	public void displayCarDetails(String plateNum){
		Integer key = createKeyFromPlateNumber(plateNum);
		Car car = carMap.get(key);
		if(car != null){
			lblPlateNumber.setText(car.getPlateNumber());
			lblMake.setText(car.getMake());
			lblModel.setText(car.getModel());
			lblColor.setText(car.getColor());
			lblYear.setText(car.getYear());
			lblPrice.setText(car.getPrice());
		}
		
	}
	
	/****** DATA LOADING METHODS ***********************************/
	/**
	 * This method takes in a String representing a file name for a CSV file
	 * containing the following information about Cars:
	 *  - license plate number,
	 *  - make
	 *  - model
	 *  - color
	 *  - year
	 *  - price
	 *  Each line in this file contains data about a single car.  This method reads
	 *  in each line of this file, extracts the 6 parameters listed above, creates a 
	 *  Car object from them, and then adds the Car object to the car map, using the
	 *  license plate number as the key.  The license plate number will need to be
	 *  converted to a hash value by calling the createKeyFromPlateNumber method.
	 *  
	 *  When you read in one line of data from the file, it willl be stored in a single
	 *  String.  An example String might look like this:
	 *  
	 *  String dataString = "LFH-9721,Chevrolet,Malibu,White,1997,25260"
	 *  
	 *  To extract the individual values from this String, you can use the String class'
	 *  splt method like this:
	 *  
	 *  String[] values = dataString.split(",");
	 *  
	 *  This code will result in the following items to be stored in the values arrray:
	 *   values[0] = "LFH-9721"
	 *   values[1] = "Chevrolet"
	 *   values[2] = "Malibu"
	 *   values[3] = "White"
	 *   values[4] = "1997"
	 *   values[5] = "25260"
	 *   
	 *   This information can then be used to construct a new Car object.
	 * @param fileName
	 */
	public void loadCars(String fileName){
		String dataLine;
		getCarMap();
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
			
			while((dataLine = br.readLine()) != null){
				String[] values = dataLine.split(",");
				carMap.put(createKeyFromPlateNumber(values[0]), 
						new Car(values[0], values[1], values[2], values[3], values[4], values[5]));
			}
		

		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	/****** EVENT HANDLERS *****************************************/
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == getBtnSearch()){
			clearLabels();
			displayCarDetails(getTxtPlateNumber().getText());
		}

	}

	/******* MAIN METHOD *******************************************/
	public static void main(String[] args) {
		new CarLookupApp();

	}

}
