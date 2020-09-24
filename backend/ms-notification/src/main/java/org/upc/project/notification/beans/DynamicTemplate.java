package org.upc.project.notification.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class DynamicTemplate {

    private List<Email> to;

    @JsonProperty("dynamic_template_data")
    private DataEmail dataEmail;

    @JsonProperty("subject")
    private String subject;

}
