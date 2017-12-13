package de.fhaachen.gpm.practicum;

import java.io.Serializable;
import java.util.List;

public class CreateStrawpollDTO implements Serializable {
    private String title;
    private List<String> options;

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public List<String> getOptions(){
        return options;
    }

    public void setOptions(List<String> options){
        this.options = options;
    }
}
