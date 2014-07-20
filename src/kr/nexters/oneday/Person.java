package kr.nexters.oneday;

public class Person {
	private int Num;      
	private String Name;

	Person(int Num, String Name ) {
		this.Num = Num;
		this.Name = Name;
	}

	public void setNum(int Num) {
		this.Num = Num;
	}

	public int getNum() {
		return Num;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public String getName() {
		return Name;
	}
}
