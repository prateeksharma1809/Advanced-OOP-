package h5;


/**
 * 
 * @author Prateek Sharma, Kirti Sharma
 *
 */
public class Picture {
	
	private String man[] = {  
			  "                ###"
			, "                ###"
			, "                 #"
			, "               #####"
			, "              # ### #"
			, "                ###"
			, "               #  #"
			, "              ##   ##"
			, "########################"
			, "##                    ##"
			, "##                    ##"};
	
	/***
	 * prints the disappering man based on the number of wrong guesses that has been done so far 
	 * @param wrongGusses -> number of wrong guesses so far 
	 */
	public void printDisapperingMan(int wrongGusses) {
		int reamingMan = (int) (man.length * wrongGusses / 9);
		for (int index = 0; index < man.length; index++) {
			if (reamingMan > index) {
				System.out.println();
			} else {
				System.out.println(man[index]);
			}
		}
	}

}
