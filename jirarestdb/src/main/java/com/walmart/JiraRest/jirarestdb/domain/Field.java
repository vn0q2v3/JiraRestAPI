package com.walmart.JiraRest.jirarestdb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@AllArgsConstructor
@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
public class Field {
	@JsonProperty("summary")
	//@Column(name="summary")
    private String summary;
	
	@JsonProperty("created")
	//@Column(name="created")
    private String created;
	
	@Embedded
	@JsonProperty("status")
    private Status status;
	
	@Embedded
	@JsonProperty("reporter")
	private Reporter reporter;
	
	@Embedded
	@JsonProperty("assignee")
	private Assignee assignee;
	
    public Field() {}
}