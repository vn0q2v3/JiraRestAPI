package com.walmart.JiraRest.jirarestdb.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "jira_ticket_info")
public class User {

    @Id
    @JsonProperty("id")
    //@Column(name="id")
    private Long id;
    
    @JsonProperty("key")
    //@Column(name="key")
    private String key;
    
    @Embedded
    @JsonProperty("fields")
    private Field fields;
    

    public User() {}
}
