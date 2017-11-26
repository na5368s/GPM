package de.fhaachen.gpm.practicum;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class Test implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        Long matrikelnummer = (Long) execution.getVariable("matrikelnummer");
        String thesis_title = (String) execution.getVariable( "thesis_title");

        execution.setVariable("studentId", matrikelnummer);
    }
}
