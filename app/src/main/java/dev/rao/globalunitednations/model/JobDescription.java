package dev.rao.globalunitednations.model;

public class JobDescription {

    String Name, time;

    public JobDescription(String name, String time) {
        Name = name;
        this.time = time;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
