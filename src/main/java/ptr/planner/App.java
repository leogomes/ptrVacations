package ptr.planner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.drools.solver.config.XmlSolverConfigurer;
import org.drools.solver.core.Solver;
import org.drools.solver.core.solution.Solution;

import ptr.planner.domain.PTRSchedule;
import ptr.planner.domain.PTRWeek;
import ptr.planner.domain.Person;
import ptr.planner.domain.Timeslot;


public class App {

  private static final Logger log = Logger.getLogger(App.class);
  
  private Solver solver;

  public static final String SOLVER_CONFIG = "/ptrVacationsConfig.xml";

  public static void main(String[] args) {

    try {

      new App(args[0], Integer.parseInt(args[1]));

    } catch (Exception e) {
      log.error("Couldn't run!");
      e.printStackTrace();
    }

  }

  public App(String beginDate, int numberOfWeeks) throws ParseException {

    PTRSchedule schedule = new PTRSchedule();
    schedule.setPeople(getPeople());

    List<Timeslot> slots = getTimeslots(beginDate, numberOfWeeks);
    schedule.setTimeslots(slots);
    schedule.setPtrWeeks(getPTRWeeks(slots));

    solver = createSolver();
    solver.setStartingSolution(schedule);
    solver.solve();
    printSolution(solver.getBestSolution());
  }

  private void printSolution(Solution bestSolution) {
    
    PTRSchedule schedule = (PTRSchedule) bestSolution;
    List<PTRWeek> weeks = schedule.getPtrWeeks();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    for (PTRWeek ptrWeek : weeks) {
      String begin = sdf.format(ptrWeek.getBegin());
      
      StringBuilder b = new StringBuilder();
      
      b.append(begin).append(" - ").append(ptrWeek.getFirstPersonName())
      .append(" / ").append(ptrWeek.getSecondPersonName());
      System.out.println(b.toString());
    }
    
    
  }

  private Solver createSolver() {
    XmlSolverConfigurer configurer = new XmlSolverConfigurer();
    configurer.configure(SOLVER_CONFIG);
    return configurer.buildSolver();
  }

  private List<PTRWeek> getPTRWeeks(List<Timeslot> slots) {

    List<PTRWeek> ptrs = new ArrayList<PTRWeek>();

    for (Timeslot slot : slots) {
      ptrs.add(new PTRWeek(slot));
    }

    return ptrs;

  }

  private List<Person> getPeople() {

    List<Person> people = new ArrayList<Person>();

    people.add(new Person("LG"));
    people.add(new Person("TF"));
    people.add(new Person("BB"));
    people.add(new Person("NR"));
    people.add(new Person("FP"));
    people.add(new Person("JBA"));
    people.add(new Person("VG"));
    people.add(new Person("VB"));

    return people;
  }

  List<Timeslot> getTimeslots(String beginDate, int numberOfWeeks)
      throws ParseException {

    List<Timeslot> slots = new ArrayList<Timeslot>();

    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    Calendar week = Calendar.getInstance();

    week.setTime(sdf.parse(beginDate));

    // First week
    slots.add(new Timeslot(0, week));

    for (int i = 1; i < numberOfWeeks; i++) {
      // Next week
      Calendar nextWeek = Calendar.getInstance();
      nextWeek.setTime(week.getTime());
      nextWeek.add(Calendar.DAY_OF_YEAR, 7 * i);

      slots.add(new Timeslot(i, nextWeek));
    }

    return slots;
  }

}
