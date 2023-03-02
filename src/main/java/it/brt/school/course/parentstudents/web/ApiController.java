package it.brt.school.course.parentstudents.web;


import it.brt.school.course.parentstudents.models.StudentJustificationMessage;
import it.brt.school.course.parentstudents.services.ParentStudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;


@RestController
@RequestMapping(value = "/api/v1/school/parent")
public class ApiController {

    @Autowired
    ParentStudentsService parentStudentServices;

    @GetMapping(value = {"/hello", "/hello/{name}"})
    public String Hello(@PathVariable(name = "name") Optional<String> name) {
        return "Hello :" + ((name.isEmpty()) ? "Anonymous" : name.get()) + ", I'm Spring Boot";
    }

    @PostMapping(value = "/justification/signature")
    public ResponseEntity<String> signatureJustification(@RequestBody(required = true) StudentJustificationMessage studentJustification) {
        //simple message
//        parentStudentServices.sendMessage(String.format("giustificazione %1s, genitore %2s", name, studentName));

        //custom message
        parentStudentServices.sendMessage(studentJustification);


        return new ResponseEntity<>("giustificianzione inviata, grazie di aver usato il servizio smart Sgrips-Justification", HttpStatus.OK);
    }

}
