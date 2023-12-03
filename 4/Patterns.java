package h4;

public class Patterns {
	public boolean ab(char[] input) {
		int state=0;
		for(int index =0; (state!=-1 && index<input.length);index++) {
			if(index==0 && state==0 && input[index]=='a') {
				state=1;
			}else if(index==1 && state==1 && input[index]=='b') {
				state=2;
			}else {
				state=-1;
			}
		}
		if(state==2) {
			return true;
		}
		return false;
	}

	public boolean aPlusb(char[] input) {
		int state =0;
		for (int index =0; (state!=-1 && index<input.length); index++) {
			if(state==0 && input[index]!='\0') {
				state=1;
			}else if(state==1 && input[index]=='a') {
				state=2;
			}else if(state==2 && input[index]=='a') {
				state=2;
			}else if(state==2 && input[index]=='b') {
				state=3;
			}else if(state==3 && input[index]!='\0') {
				state=4;
			}else {
				state=-1;
			}
		}
		if(state==4) {
			return true;
		}
		return false;
	}

	public boolean dotABdot(char[] input) {
		int state =0;
		for (int index =0; (state!=-1 && index<input.length); index++) {
			if(state==0 && input[index]!='\0') {
				state=1;
			}else if(state==1 && input[index]=='a') {
				state=2;
			}else if(state==2 && input[index]=='b') {
				state=3;
			}else if(state==3 && input[index]!='\0') {
				state=4;
			}else {
				state=-1;
			}
		}
		if(state==4) {
			return true;
		}
		return false;
	}
	public boolean aORbANDc(char[] input) {
		int state =0;
		for (int index =0; (state!=-1 && index<input.length); index++) {
			if(state==0 && (input[index] == 'a'|| input[index]=='b')) {
				state=1;
			}else if(state==1 && input[index] == 'c') {
				state=2;
			}else if(state==0 && input[index]=='c') {
				state=2;
			}else {
				state=-1;
			}
		}
		if(state==2) {
			return true;
		}
		return false;
	}
	
	public boolean aORbANDcORc(char[] input) {
		int state =0;
		for (int index =0; (state!=-1 && index<input.length); index++) {
			if(state==0 && (input[index] == 'a'|| input[index]=='b')) {
				state=1;
			}else if(state==1 && input[index] == 'c') {
				state=2;
			}else if(state==0 && input[index]=='c') {
				state=2;
			}else {
				state=-1;
			}
		}
		if(state==2) {
			return true;
		}
		return false;
	}
	
	
	
	public boolean OneOrMoreaORbAtStartORcAtEnd(char[] input) {
		int state =0;
		for (int index =0; (state!=-1 && index<input.length); index++) {
			if(state==0 && (input[index] == 'a'|| input[index]=='b')) {
				state=2;
			}else if(input[input.length-1] == 'c') {
				state=2;
			}
		}
		if(state==2) {
			return true;
		}
		return false;
	}
	
	
	public boolean palindrome(char[] input) {
		int state =0;
		for (int index =0; (state!=-1 && index<input.length); index++) {
			if(input[index]==input[input.length-index-1]) {
				state+=1;
			}else {
				state=-1;
			}
		}
		if(state==4) {
			return true;
		}
		return false;
	}
	
}
