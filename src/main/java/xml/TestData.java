package xml;

import lombok.Data;

@Data
public class TestData {
  String test;
  String id;
  String flag;
  String location;
  public TestData(String test, String id, String flag, String location) {
    this.test = test;
    this.id = id;
    this.flag = flag;
    this.location = location;
  }
  
}