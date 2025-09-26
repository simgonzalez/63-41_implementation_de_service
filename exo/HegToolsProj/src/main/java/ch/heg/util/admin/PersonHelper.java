package ch.heg.util.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class PersonHelper {
  private PersonHelper() {}

  static final ObjectMapper mapper = new ObjectMapper();

  public static String toJson(Person person) throws JsonProcessingException {
    return mapper.writeValueAsString(person);
  }

  public static String toJson(List<Person> persons) throws JsonProcessingException {
    return mapper.writeValueAsString(persons);
  }

  public static Person fromJson(String json) throws JsonProcessingException {
    return mapper.readValue(json, Person.class);
  }

  public static List<Person> fromJsonList(String json) throws JsonProcessingException {
    return List.of(mapper.readValue(json, Person[].class));
  }
}
