package com.betrybe.agrix.controllers;

import static com.betrybe.agrix.util.PersonUtil.persoDtoCreateConvertUser;
import static com.betrybe.agrix.util.PersonUtil.personResponseConvert;

import com.betrybe.agrix.dtos.PersonResponse;
import com.betrybe.agrix.dtos.PersonResquest;
import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  CropsController com resposta para o cliente.
 */
@RestController
@RequestMapping("/persons")
public class PersonsController {

  @Autowired
  private PersonService personService;

  /**
   * Metodo para criar uma person.
   */
  @PostMapping
  public ResponseEntity<PersonResponse> personCreate(@RequestBody PersonResquest personResquest) {
    Person person = personService.create(
        persoDtoCreateConvertUser(personResquest)
    );
    PersonResponse personResponse = personResponseConvert(person);
    return ResponseEntity.status(HttpStatus.CREATED).body(personResponse);
  }
}
