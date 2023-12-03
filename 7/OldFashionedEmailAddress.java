package h7;

import java.util.Objects;

/**
 * 
 * @author Kirti Sharma, Prateek Sharma
 *
 */
public class OldFashionedEmailAddress implements Comparable<OldFashionedEmailAddress>{
	private int houseNumber;
	private String streetName;
	private String nameOfTown;
	private String state;
	private int zipCode;
	
	public OldFashionedEmailAddress(int houseNumber, String streetName, String nameOfTown, String state, int zipCode) {
		super();
		this.houseNumber = houseNumber;
		this.streetName = streetName;
		this.nameOfTown = nameOfTown;
		this.state = state;
		this.zipCode = zipCode;
	}
	
	/**
	 * getter method 
	 * @return houseNumber value for the calling object
	 */
	public int getHouseNumber() {
		return houseNumber;
	}
	
	/**
	 * setter method
	 * @param houseNumber sets the houseNumber value for the calling object 
	 */
	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}
	
	/**
	 * getter method 
	 * @return StreetName value for the calling object
	 */
	public String getStreetName() {
		return streetName;
	}
	
	/**
	 * setter method
	 * @param StreetName sets the StreetName value for the calling object 
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	
	/**
	 * getter method 
	 * @return NameOfTown value for the calling object
	 */
	public String getNameOfTown() {
		return nameOfTown;
	}
	
	/**
	 * setter method
	 * @param nameOfTown sets the nameOfTown value for the calling object 
	 */
	public void setNameOfTown(String nameOfTown) {
		this.nameOfTown = nameOfTown;
	}
	
	/**
	 * getter method 
	 * @return state value for the calling object
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * setter method
	 * @param state sets the state value for the calling object 
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * getter method 
	 * @return ZipCode value for the calling object
	 */
	public int getZipCode() {
		return zipCode;
	}
	
	/**
	 * setter method
	 * @param zipCode sets the zipCode value for the calling object 
	 */
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	
	/**
	 * method to convert the class to string representation
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OldFashionedEmailAddress [houseNumber=");
		builder.append(houseNumber);
		builder.append(", streetName=");
		builder.append(streetName);
		builder.append(", nameOfTown=");
		builder.append(nameOfTown);
		builder.append(", state=");
		builder.append(state);
		builder.append(", zipCode=");
		builder.append(zipCode);
		builder.append("]");
		return builder.toString();
	}
	
	/**
	 * use to calculate unique code for every unique object and it will be same for each
	 * @param object from which it needs to compare
	 * @return value based on the comparison 
	 */
	@Override
	public int hashCode() {
		return Objects.hash(houseNumber, nameOfTown, state, streetName, zipCode);
	}
	
	/**
	 * use to check if the two objects of the class is equal
	 * @param object which needs to check if it is equal
	 * @return true if equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OldFashionedEmailAddress other = (OldFashionedEmailAddress) obj;
		return houseNumber == other.houseNumber && Objects.equals(nameOfTown, other.nameOfTown)
				&& Objects.equals(state, other.state) && Objects.equals(streetName, other.streetName)
				&& zipCode == other.zipCode;
	}
	
	/**
	 * overides the compare to method from comparable interface 
	 * compares them based on length and then if length is equal are they actually same object 
	 * @param object from which it needs to compare
	 * @return value based on the comparison 
	 */
	@Override
	public int compareTo(OldFashionedEmailAddress obj) {
		if (obj == null)
			return 1;
		else if (this.hashCode() < obj.hashCode()) {
			return 1;
		} else if (this.hashCode() > obj.hashCode()) {
			return -1;
		} else {
			return 0;
		}
	}
	
    

}
