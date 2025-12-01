package use_case.post_job;

public class PostJobInputData {

    private final String title;
    private final String description;
    private final String location;

    public PostJobInputData(String title, String description, String location) {
        this.title = title;
        this.description = description;
        this.location = location;
    }

    public String getTitle() { return title; }

    public String getDescription() { return description; }

    public String getLocation() { return location; }
}
