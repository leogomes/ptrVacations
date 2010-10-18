package ptr.planner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import ptr.planner.App;
import ptr.planner.domain.Timeslot;

import junit.framework.TestCase;


public class AppTest extends TestCase {

  public void testGetTimeslots() throws ParseException {
    SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
    App app = new App(null, 0);
    List<Timeslot> slots = app.getTimeslots("20100517", 20);
    for (Timeslot timeslot : slots) {
      System.out.println(timeslot.getIndex() + " - " + f.format(timeslot.getWeek().getTime()));
    }
    
  }

}
