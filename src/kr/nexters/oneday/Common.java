package kr.nexters.oneday;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import kr.nexters.oneday.vo.Person;
import android.content.Context;

public class Common {
	private static Set<Person> personSet;
	private static Set<Person> personSelectedSet;
	
	private static Context sContext;  

	public static void initialize(Context context) {
		sContext = context;
		personSet = new HashSet<Person>();
		personSelectedSet = new HashSet<Person>();
	}

	public static Context getMainContext() {
		return sContext;
	}
	
	public static void addPerson(Person person) {
		personSet.remove(person);
		personSet.add(person);
	}
	
	public static void addSelectedPerson(Person person) {
		personSelectedSet.remove(person);
		personSelectedSet.add(person);
	}

	public static Set<Person> getPersonSet() {
		return personSet;
	}

	public static Set<Person> getPersonSelectedSet() {
		return personSelectedSet;
	}
}
