package ptr.planner.domain;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.time.DateUtils;

public class PTRWeek implements Comparable<PTRWeek> {

  private Person firstPerson;
  private Person secondPerson;
  private Timeslot week;

  private Date begin;
  private Date end;

  public PTRWeek() {
  }

  public PTRWeek(Person firstPerson, Person secondPerson, Timeslot week) {
    super();
    this.firstPerson = firstPerson;
    this.secondPerson = secondPerson;
    setWeek(week);
  }

  public PTRWeek(Timeslot slot) {
    this.week = slot;
  }

  public Person getFirstPerson() {
    return firstPerson;
  }

  public void setFirstPerson(Person firstPerson) {
    this.firstPerson = firstPerson;
  }

  public Person getSecondPerson() {
    return secondPerson;
  }

  public void setSecondPerson(Person secondPerson) {
    this.secondPerson = secondPerson;
  }

  public Date getBegin() {
    return begin;
  }

  public Date getFinish() {
    return end;
  }

  public Timeslot getWeek() {
    return week;
  }

  public void setWeek(Timeslot week) {
    this.week = week;
    this.begin = week.getWeek().getTime();
    setEnd(begin);
  }

  private void setEnd(Date begin) {
    Calendar end = Calendar.getInstance();
    end.setTime(begin);
    end.add(Calendar.DAY_OF_YEAR, 5);
    this.end = end.getTime();

  }

  public int compareTo(PTRWeek o) {
    return new CompareToBuilder()
        .append(firstPerson, o.firstPerson)
        .append(secondPerson, o.secondPerson)
        .append(week, o.week)
        .toComparison();
  }

  public PTRWeek clone() {
    PTRWeek clone = new PTRWeek(firstPerson, secondPerson, week);
    return clone;
  }

  public String getFirstPersonName() {
    return firstPerson == null ? "" : firstPerson.getName();
  }

  public String getSecondPersonName() {
    return secondPerson == null ? "" : secondPerson.getName();
  }
  
  public static int diff(Date one, Date two) {
    
    if (one == null || two == null) {
      return -1;
    }
    
    long diff = two.getTime() - one.getTime();
    
    if (diff < 0) {
      diff *= -1;
    }

    Calendar c = Calendar.getInstance();
    c.setTimeInMillis(diff);
    return c.get(Calendar.WEEK_OF_YEAR)-1;
   
  }

}
