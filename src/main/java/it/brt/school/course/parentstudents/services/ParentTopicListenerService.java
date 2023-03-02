package it.brt.school.course.parentstudents.services;

import it.brt.school.course.parentstudents.models.StudentJustification;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ParentTopicListenerService
{

    @KafkaListener(topics = "#{'${spring.kafka.topics}'.split(',')}", groupId = "groupid")
    public void listenMessage(String message) {
        System.out.println("Received Message in group foo: " + message);
    }

   @KafkaListener(
            topics =  "#{'${spring.kafka.topics}'.split(',')}",
            groupId = "groupid",
            containerFactory = "studentJustificationKafkaListenerContainerFactory")
    public void studentJustificationListener(StudentJustification studentJustification) {
        System.out.println("Received Message in group foo: " + studentJustification.toString());
    }

}
