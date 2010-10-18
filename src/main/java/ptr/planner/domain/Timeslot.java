package ptr.planner.domain;

import java.util.Calendar;

import org.apache.commons.lang.builder.CompareToBuilder;

public class Timeslot implements Comparable<Timeslot> {

  private int index;
  private Calendar week;
  
  public Timeslot(){}
  
  public Timeslot(int index, Calendar week) {
    super();
    this.index = index;
    this.week = week;
  }



  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public Calendar getWeek() {
    return week;
  }

  public void setWeek(Calendar week) {
    this.week = week;
  }

  public int compareTo(Timeslot other) {
    return new CompareToBuilder()
        .append(index, other.index)
        .append(week, other.week)
        .toComparison();
  }

}
