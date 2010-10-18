package ptr.planner.domain;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import junit.framework.TestCase;

@SuppressWarnings("unchecked")
public class PTRScheduleTest extends TestCase {

  public void testPeoplePairs() {
    PTRSchedule s = new PTRSchedule();
    List<Person> people = new ArrayList();
    Person leo = new Person("Leo");
    Person ana = new Person("Ana");
    Person laki = new Person("Laki");

    people.add(leo);
    people.add(ana);
    people.add(laki);

    s.setPeople(people);

    List<List<Person>> pairs = s.getPeoplePairs();

    assertEquals(2, pairs.size());

    assertEquals(2, pairs.get(0).size());
    assertEquals(1, pairs.get(1).size());

    assertTrue(pairs.get(0).contains(leo));
    assertTrue(pairs.get(0).contains(ana));
    assertTrue(pairs.get(1).contains(laki));
  }

  public void testPTRWeekDiff() throws Exception {
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    Date d1 = sdf.parse("20100531");
    Date d2 = sdf.parse("20100517");
    
    assertEquals(2, PTRWeek.diff(d1, d2));

  }

}
