package com.mysoft.basicspringjwt.model;

import java.io.Serializable;

public class JwtRequest implements Serializable {
    private static final long serialVersionUID = 926468583005150707L;

    private String name;
    private Integer id;
    private Boolean validated;

    public JwtRequest() {
    }

    public JwtRequest(String name, Integer id, Boolean validated) {
        this.name = name;
        this.id = id;
        this.validated = validated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getValidated() {
        return validated;
    }

    public void setValidated(Boolean validated) {
        this.validated = validated;
    }
}
