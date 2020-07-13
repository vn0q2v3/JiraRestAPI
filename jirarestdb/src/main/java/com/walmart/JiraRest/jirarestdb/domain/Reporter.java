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
public class Reporter {
	@JsonProperty("key")
	//@Column(name="reporter_key")
    private String reporter_key;
	
	@JsonProperty("emailAddress")
	//@Column(name="reporter_emailAddress")
    private String reporter_emailAddress;
	
	@JsonProperty("displayName")
	//@Column(name="reporter_displayName")
    private String reporter_displayName;

    public Reporter() {}
}
