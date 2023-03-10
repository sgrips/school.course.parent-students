package it.brt.school.course.parentstudents.services;

import it.brt.school.course.parentstudents.models.StudentJustificationMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ParentStudentsService {
    @Value("${spring.kafka.topics}")
    String topicName;


    @Autowired
    private KafkaTemplate<String, StudentJustificationMessage> studentJustificationKafkaTemplate;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String msg) {
        kafkaTemplate.send(topicName, msg);
    }


    public void sendMessage(StudentJustificationMessage msg) {
        studentJustificationKafkaTemplate.send(topicName, msg);
    }



}
