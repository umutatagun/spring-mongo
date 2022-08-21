package com.example.eshop.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Data
public class BaseEntity {
    @CreatedDate
    private Date createdDate = new Date();
    @CreatedBy
    private String createdBy = "Admin";
    @LastModifiedBy
    private String lastModifiedBy = "Admin";
    @LastModifiedDate
    private Date lastModifiedDate = new Date();
}
