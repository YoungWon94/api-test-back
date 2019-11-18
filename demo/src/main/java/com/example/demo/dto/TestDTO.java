package com.example.demo.dto;

import java.util.Date;

import lombok.Setter;

import lombok.Getter;


public class TestDTO {
    private int service_api_no;
    private int service_no;
    private String service_api; 
    private String api_url; 
    private Object param; 
    private String method; 
    private String content_type; 
    private String is_deleted; 
    private Date insert_timestamp; 
    private Date updated_timestamp; 
    private int employee_no; 
    private String service_api_name;


    public int getService_api_no() {
        return this.service_api_no;
    }

    public void setService_api_no(int service_api_no) {
        this.service_api_no = service_api_no;
    }

    public int getService_no() {
        return this.service_no;
    }

    public void setService_no(int service_no) {
        this.service_no = service_no;
    }

    public String getService_api() {
        return this.service_api;
    }

    public void setService_api(String service_api) {
        this.service_api = service_api;
    }

    public String getApi_url() {
        return this.api_url;
    }

    public void setApi_url(String api_url) {
        this.api_url = api_url;
    }

    public Object getParam() {
        return this.param;
    }

    public void setParam(Object param) {
        this.param = param;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getContent_type() {
        return this.content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }

    public String getIs_deleted() {
        return this.is_deleted;
    }

    public void setIs_deleted(String is_deleted) {
        this.is_deleted = is_deleted;
    }

    public Date getInsert_timestamp() {
        return this.insert_timestamp;
    }

    public void setInsert_timestamp(Date insert_timestamp) {
        this.insert_timestamp = insert_timestamp;
    }

    public Date getUpdated_timestamp() {
        return this.updated_timestamp;
    }

    public void setUpdated_timestamp(Date updated_timestamp) {
        this.updated_timestamp = updated_timestamp;
    }

    public int getEmployee_no() {
        return this.employee_no;
    }

    public void setEmployee_no(int employee_no) {
        this.employee_no = employee_no;
    }

    public String getService_api_name() {
        return this.service_api_name;
    }

    public void setService_api_name(String service_api_name) {
        this.service_api_name = service_api_name;
    }





    @Override
    public String toString() {
        return "{" +
            " service_api_no='" + service_api_no + "'" +
            ", service_no='" + service_no + "'" +
            ", service_api='" + service_api + "'" +
            ", api_url='" + api_url + "'" +
            ", param='" + param + "'" +
            ", method='" + method + "'" +
            ", content_type='" + content_type + "'" +
            ", is_deleted='" + is_deleted + "'" +
            ", insert_timestamp='" + insert_timestamp + "'" +
            ", updated_timestamp='" + updated_timestamp + "'" +
            ", employee_no='" + employee_no + "'" +
            ", service_api_name='" + service_api_name + "'" +
            "}";
    }
    
}