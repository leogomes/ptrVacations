package ptr.planner.domain;

import org.apache.commons.lang.builder.CompareToBuilder;

public class Person implements Comparable<Person> {

  private String name;
  
  public Person(){}
  
  public Person(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int compareTo(Person other) {
    return new CompareToBuilder()
        .append(name, other.name)
        .toComparison();
  }

}
