package no.hvl.dat110.rest.counters;

import lombok.Data;

import com.google.gson.Gson;


@Data
public class TODO {

    private Long id;
    private String summary;
    private String description;


    public TODO () {
        this.id = 0L;
        this.summary = "Summary";
        this.description = "Description";
    }

    public TODO (Long id, String summary, String description) {
        this.id = id;
        this.summary = summary;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    String toJson () {

        Gson gson = new Gson();

        String jsonInString = gson.toJson(this);

        return jsonInString;
    }

}
