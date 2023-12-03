package h7;

import java.util.Objects;

/**
 * 
 * @author Kirti Sharma, Prateek Sharma
 *
 */

public class MusicStorageOfThePast implements Comparable<MusicStorageOfThePast>{
	private int year;
     private String artist;
     private String title;
    private float length;
     private int tracks;
     
     
	public MusicStorageOfThePast(int year, String artist, String title, float length, int tracks) {
		super();
		this.year = year;
		this.artist = artist;
		this.title = title;
		this.length = length;
		this.tracks = tracks;
	}
	
	/**
	 * getter method 
	 * @return year value for the calling object
	 */
	public int getYear() {
		return year;
	}
	
	/**
	 * setter method
	 * @param year sets the year value for the calling object 
	 */
	public void setYear(int year) {
		this.year = year;
	}
	
	/**
	 * getter method 
	 * @return artist value for the calling object
	 */
	public String getArtist() {
		return artist;
	}
	
	/**
	 * setter method
	 * @param artist sets the artist value for the calling object 
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	/**
	 * getter method 
	 * @return title value for the calling object
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * setter method
	 * @param title sets the title value for the calling object 
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * getter method 
	 * @return length value for the calling object
	 */
	public float getLength() {
		return length;
	}
	
	/**
	 * setter method
	 * @param length sets the length value for the calling object 
	 */
	public void setLength(float length) {
		this.length = length;
	}
	
	/**
	 * getter method 
	 * @return tracks value for the calling object
	 */
	public int getTracks() {
		return tracks;
	}
	
	/**
	 * setter method
	 * @param tracks sets the tracks value for the calling object 
	 */
	public void setTracks(int tracks) {
		this.tracks = tracks;
	}
	
	/**
	 * method to convert the class to string representation
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MusicStorageOfThePast [year=");
		builder.append(year);
		builder.append(", artist=");
		builder.append(artist);
		builder.append(", title=");
		builder.append(title);
		builder.append(", length=");
		builder.append(length);
		builder.append(", tracks=");
		builder.append(tracks);
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
		return Objects.hash(artist, length, title, tracks, year);
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
		MusicStorageOfThePast other = (MusicStorageOfThePast) obj;
		return Objects.equals(artist, other.artist)
				&& Float.floatToIntBits(length) == Float.floatToIntBits(other.length)
				&& Objects.equals(title, other.title) && tracks == other.tracks && year == other.year;
	}
	
	/**
	 * overides the compare to method from comparable interface 
	 * compares them based on length and then if length is equal are they actually same object 
	 * @param object from which it needs to compare
	 * @return value based on the comparison 
	 */
	@Override
	public int compareTo(MusicStorageOfThePast obj) {
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
