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
import java.util.List;

public class CheckStrawpollDelegate implements JavaDelegate{

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        System.out.println("Prüfe Antwort...");

        String strawpollId = (String) delegateExecution.getVariable("strawpollId");

        String pollURL = "http://strawpoll.me/api/v2/polls/" + strawpollId;

        ClientConfig cc = new DefaultClientConfig();
        cc.getClasses().add(JacksonJsonProvider.class);

        Client client = Client.create(cc);

        WebResource webResource = client.resource(pollURL);

        ClientResponse response = webResource.header("user-agent","").type(MediaType.APPLICATION_JSON).get(ClientResponse.class);

        if(response.getStatus() != 200) {
            throw new Exception("Der Server meldet Fehlercode: " + response.getStatus());
        }
        else {
            CheckStrawpollDTO ret = response.getEntity(CheckStrawpollDTO.class);

            System.out.println(ret.toString());

            List<Integer> votes = ret.getVotes();

            if(votes.get(0) == 1) {
                delegateExecution.setVariable("pollAnswer", true);
                delegateExecution.setVariable("dateApproved", true);
                System.out.println("Termin akzeptiert");
            }
            else if(votes.get(1) == 1) {
                delegateExecution.setVariable("pollAnswer", true);
                delegateExecution.setVariable("dateApproved", false);
                System.out.println("Termin nicht akzeptiert");
            }
            else {
                System.out.println("Keine Antwort erhalten!");
            }

        }
    }
}

