package com.walmart.JiraRest.jirarestdb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@AllArgsConstructor
@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
public class Assignee {
	@JsonProperty("key")
	//@Column(name="assignee_key")
    private String assignee_key;
	
	@JsonProperty("emailAddress")
	//@Column(name="assignee_emailAddress")
    private String assignee_email_address;
	
	@JsonProperty("displayName")
	//@Column(name="assignee_display_name")
    private String assignee_displayName;

    public Assignee() {}
}
