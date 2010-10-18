package ptr.planner.move;

import java.util.Collection;
import java.util.Collections;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.drools.FactHandle;
import org.drools.WorkingMemory;
import org.drools.solver.core.localsearch.decider.accepter.tabu.TabuPropertyEnabled;
import org.drools.solver.core.move.Move;

import ptr.planner.domain.PTRWeek;
import ptr.planner.domain.Person;


/**
 * 
 * @author lgomes
 * 
 */
public class WeekChangeMove implements Move, TabuPropertyEnabled {

  private PTRWeek ptrWeek;
  private Person toFirstPerson;
  private Person toSecondPerson;

  public WeekChangeMove(PTRWeek ptrWeek, Person firstPerson, Person secondPerson) {
    this.ptrWeek = ptrWeek;
    this.toFirstPerson = firstPerson;
    this.toSecondPerson = secondPerson;
  }

  public Move createUndoMove(WorkingMemory workingMemory) {
    return new WeekChangeMove(ptrWeek, ptrWeek.getFirstPerson(), ptrWeek
        .getSecondPerson());
  }

  public void doMove(WorkingMemory workingMemory) {
    FactHandle weekHandle = workingMemory.getFactHandle(ptrWeek);
    workingMemory.modifyRetract(weekHandle);
    ptrWeek.setFirstPerson(toFirstPerson);
    ptrWeek.setSecondPerson(toSecondPerson);
    workingMemory.modifyInsert(weekHandle, ptrWeek);
  }

  public boolean isMoveDoable(WorkingMemory workingMemory) {
    return
        //!ObjectUtils.equals(toFirstPerson, toSecondPerson) &&
        !ObjectUtils.equals(ptrWeek.getFirstPerson(), toFirstPerson) ||
        !ObjectUtils.equals(ptrWeek.getSecondPerson(), toSecondPerson);
  }

  public Collection<? extends Object> getTabuProperties() {
    return Collections.singletonList(ptrWeek);
  }

  public boolean equals(Object o) {
    if (this == o) {
      return true;
    } else if (o instanceof WeekChangeMove) {
      WeekChangeMove other = (WeekChangeMove) o;
      return new EqualsBuilder()
                .append(ptrWeek, other.ptrWeek)
                .append(toFirstPerson, other.toFirstPerson)
                .append(toSecondPerson, other.toSecondPerson)
                .isEquals();
    } else {
      return false;
    }
  }

  public int hashCode() {
    return new HashCodeBuilder()
            .append(ptrWeek)
            .append(toFirstPerson)
            .append(toSecondPerson)
            .toHashCode();
  }

}
