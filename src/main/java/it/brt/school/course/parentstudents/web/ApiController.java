package it.brt.school.course.parentstudents.web;


import it.brt.school.course.parentstudents.models.StudentJustification;
import it.brt.school.course.parentstudents.services.ParentStudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

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
    public ResponseEntity<String> signatureJustification(@RequestParam(name = "name") String name, @RequestParam(name = "studentname") String studentName) {
        //simple message
        parentStudentServices.sendMessage(String.format("giustificazione %1s, genitore %2s", name, studentName));
        //custom message
        parentStudentServices.sendMessage(new StudentJustification() {{
            setStudentName(studentName);
            setParentSignature(name);
            setCreateTime(new Date());
        }});


        return new ResponseEntity<>("giustificianzione inviata, grazie di aver usato il servizio smart justification", HttpStatus.OK);
    }

    @GetMapping(value = "/justification")
    public ResponseEntity<String> listJustifications() {
        return new ResponseEntity<>("not implemented", HttpStatus.OK);
    }
}
