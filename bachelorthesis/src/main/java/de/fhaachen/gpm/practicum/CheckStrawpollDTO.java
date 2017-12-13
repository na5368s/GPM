package de.fhaachen.gpm.practicum;

import java.io.Serializable;
import java.util.List;

public class CheckStrawpollDTO implements Serializable{

    private String id;
    private String title;
    private List<String> options;
    private List<Integer> votes;
    private boolean multi;
    private String dupcheck;
    private boolean captcha;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CheckStrawpollDTO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", options=" + options +
                ", votes=" + votes +
                ", multi=" + multi +
                ", dupcheck='" + dupcheck + '\'' +
                ", captcha=" + captcha +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public List<Integer> getVotes() {
        return votes;
    }

    public void setVotes(List<Integer> votes) {
        this.votes = votes;
    }

    public boolean isMulti() {
        return multi;
    }

    public void setMulti(boolean multi) {
        this.multi = multi;
    }

    public String getDupcheck() {
        return dupcheck;
    }

    public void setDupcheck(String dupcheck) {
        this.dupcheck = dupcheck;
    }

    public boolean isCaptcha() {
        return captcha;
    }

    public void setCaptcha(boolean captcha) {
        this.captcha = captcha;
    }
}

