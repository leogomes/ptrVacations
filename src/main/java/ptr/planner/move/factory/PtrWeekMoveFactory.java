package ptr.planner.move.factory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.drools.solver.core.move.Move;
import org.drools.solver.core.move.factory.CachedMoveFactory;
import org.drools.solver.core.solution.Solution;

import ptr.planner.domain.PTRSchedule;
import ptr.planner.domain.PTRWeek;
import ptr.planner.domain.Person;
import ptr.planner.move.WeekChangeMove;


/**
 * Creates moves that will eventually find a feasible solution.
 * 
 * @author lgomes
 * 
 */
public class PtrWeekMoveFactory extends CachedMoveFactory {

  @SuppressWarnings("unchecked")
  public List createCachedMoveList(Solution solution) {
    List<Move> moveList = new ArrayList<Move>();
    PTRSchedule ptrSchedule = (PTRSchedule) solution;
    for (PTRWeek ptrWeek : ptrSchedule.getPtrWeeks()) {
      for (Iterator iterator = ptrSchedule.getPeoplePairs().iterator(); iterator
          .hasNext();) {
        List<Person> pair = (List<Person>) iterator.next();
        moveList.add(new WeekChangeMove(ptrWeek, pair.get(0), pair.get(1)));
      }
    }
    return moveList;
  }

}
