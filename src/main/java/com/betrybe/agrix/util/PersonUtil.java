package com.betrybe.agrix.util;

import com.betrybe.agrix.dtos.PersonResponse;
import com.betrybe.agrix.dtos.PersonResquest;
import com.betrybe.agrix.models.entities.Person;

/**
 *  CLasse ultil para Person.
 */
public class PersonUtil {

  /**
   *  Metodo para DTO convert.
   */
  public static PersonResponse personResponseConvert(Person person) {
    return new PersonResponse(
        person.getId(),
        person.getUsername(),
        person.getRole()
    );
  }

  /**
   *  Metodo para DTO convert.
   */
  public static Person personDtoCreatConvertId(PersonResponse personResponse) {
    Person person = new Person();

    person.setId(personResponse.id());
    person.setUsername(personResponse.username());
    person.setRole(personResponse.role());
    return person;
  }

  /**
   *  Metodo para DTO convert.
   */
  public static Person persoDtoCreateConvertUser(PersonResquest personResquest) {
    Person person = new Person();

    person.setUsername(personResquest.username());
    person.setPassword(personResquest.password());
    person.setRole(personResquest.role());

    return person;
  }


}
