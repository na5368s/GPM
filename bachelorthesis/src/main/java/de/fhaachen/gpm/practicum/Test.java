package de.fhaachen.gpm.practicum;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class Test implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        String email = (String) execution.getVariable("email");
        String subject = (String) execution.getVariableLocal("subject");
        String mailText = (String) execution.getVariableLocal("mailText");
        System.out.println("TEst");
    }

}
