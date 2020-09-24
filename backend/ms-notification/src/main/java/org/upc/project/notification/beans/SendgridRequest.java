package org.upc.project.notification.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SendgridRequest {

    private Email from;
    private List<DynamicTemplate> personalizations;

    @JsonProperty("template_id")
    private String template;

    private String subject;
}
