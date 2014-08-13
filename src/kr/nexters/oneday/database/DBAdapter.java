package kr.nexters.oneday.database;

import java.util.List;

import kr.nexters.oneday.vo.Person;
import kr.nexters.oneday.vo.TimeInfo;

public interface DBAdapter {
	public abstract List<TimeInfo> getUserTimeInfos(String userId);
    public abstract void addTimeInfo(String userId,TimeInfo timeInfo);
    public abstract void removeTimeInfo(TimeInfo time);
	public abstract List<Person> getPeople();
    public abstract void addPerson(Person person);
    public abstract void removePerson(Person person);
}
