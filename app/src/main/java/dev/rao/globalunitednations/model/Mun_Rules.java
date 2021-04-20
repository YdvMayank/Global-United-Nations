package dev.rao.globalunitednations.model;

public class Mun_Rules {

    private String title;
    private String description;
    private boolean expanded;

    public Mun_Rules(String title, String description) {
        this.title = title;
        this.description = description;
        this.expanded = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", rating='" + description + '\'' +
                ", expanded=" + expanded +
                '}';
    }
}
