package kr.nexters.oneday;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import kr.nexters.oneday.database.PersonDBAdapter;
import kr.nexters.oneday.vo.Person;
import android.content.Context;
import android.util.Log;

public class Common {
	private static Set<Person> personSet;
	private static Set<Person> personSelectedSet;
	
	private static Context sContext;  
	
	private static PersonDBAdapter dbAdapter;

	public static void initialize(Context context) {
		sContext = context;
		personSet = new HashSet<Person>();
		personSelectedSet = new HashSet<Person>();
		dbAdapter = new PersonDBAdapter(context);
		
		loadCommonFromDatabase();
	}

	public static Context getMainContext() {
		return sContext;
	}
	
	public static void addPerson(Person person) {
		long id = dbAdapter.addPersonInfo(person);
		person.setId(id);
		dbAdapter.addTimeInfo(person);
		personSet.remove(person);
		personSet.add(person);
		
		Log.i("db", person.toString());
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
	
	public static void deletePerson(Person person) {
		personSet.remove(person);
		removeSelectedPerson(person);
	}
	
	public static void removeSelectedPerson(Person person) {
		personSelectedSet.remove(person);
	}
	
	public static Person getPerson(String name) {
		Person me = new Person(name, null, null, true);
		if(personSet.contains(me)) {
			Iterator<Person> it = Common.getPersonSet().iterator();
			while(it.hasNext()) {
				Person p = it.next();
				if(p.equals(me)) {
					return p;
				}
			}
		}
		return null;
	}

	private static void loadCommonFromDatabase() {
		for(Person person : dbAdapter.getPeople()) {
			personSet.add(person);
			if (person.selected) {
				personSelectedSet.add(person);
			}
		}
	}
}
