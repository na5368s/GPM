package de.fhaachen.gpm.practicum;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import javax.ws.rs.core.MediaType;

import static java.util.Arrays.asList;

public class CreateStrawpollDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String colloquiumDate = (String) delegateExecution.getVariable("colloquiumDate");

        //String pollURL = "http://localhost:4500/api/v2/polls";
        String pollURL = "http://strawpoll.me/api/v2/polls";
        CreateStrawpollDTO poll = new CreateStrawpollDTO();
        poll.setTitle("Kolloquium am " + colloquiumDate + "?");
        poll.setOptions(asList("Termin akzeptieren", "Termin nicht akzeptieren"));

        ClientConfig cc = new DefaultClientConfig();
        cc.getClasses().add(JacksonJsonProvider.class);

        Client client = Client.create(cc);

        WebResource webResource = client.resource(pollURL);

        ClientResponse response = webResource.header("user-agent","").type(MediaType.APPLICATION_JSON).post(ClientResponse.class, poll);

        if(response.getStatus() != 200) {
            throw new Exception("Der Server meldet Fehlercode: " + response.getStatus());
        }
        else {
            System.out.println("Testiiiing");
            CheckStrawpollDTO ret = response.getEntity(CheckStrawpollDTO.class);

            delegateExecution.setVariable("strawpollId", ret.getId());
            delegateExecution.setVariable("pollAnswer", false);
        }

    }
}
