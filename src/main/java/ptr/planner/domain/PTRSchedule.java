package ptr.planner.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.drools.solver.core.solution.Solution;

public class PTRSchedule implements Solution {

  private List<Person> people;
  private List<Timeslot> timeslots;
  private List<PTRWeek> ptrWeeks;

  public List<PTRWeek> getPtrWeeks() {
    return ptrWeeks;
  }

  public void setPtrWeeks(List<PTRWeek> ptrWeeks) {
    this.ptrWeeks = ptrWeeks;
  }

  public List<Person> getPeople() {
    return people;
  }

  public void setPeople(List<Person> people) {
    this.people = people;
  }

  public List<Timeslot> getTimeslots() {
    return timeslots;
  }

  public void setTimeslots(List<Timeslot> timeslots) {
    this.timeslots = timeslots;
  }

  public Solution cloneSolution() {
    PTRSchedule clone = new PTRSchedule();
    clone.people = people;
    clone.timeslots = timeslots;
    List<PTRWeek> clonedPTRWeeks = new ArrayList<PTRWeek>();
    for (PTRWeek ptrWeek : ptrWeeks) {
      clonedPTRWeeks.add(ptrWeek.clone());
    }
    clone.ptrWeeks = clonedPTRWeeks;
    return clone;
  }

  public Collection getFacts() {
    List<Object> facts = new ArrayList<Object>();
    facts.addAll(timeslots);
    facts.addAll(people);
    facts.addAll(ptrWeeks);
    return facts;

  }

  public List<List<Person>> getPeoplePairs() {

    List<List<Person>> pairs = new ArrayList<List<Person>>();

    for (int i = 0; i < people.size(); i += 2) {
      List<Person> pair = new ArrayList<Person>();
      pair.add(people.get(i));
      if (i+1 < people.size()) {
        pair.add(people.get(i+1));
      }
      pairs.add(pair);
    }
    
    return pairs;
  }

}
