package com.example.demo.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


import java.util.Collections;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class BodyResponseDTO<T> {
    private String status;
    private String message;
    private Integer totalPage;
    private Integer pageNumber;
    private Integer pageSize;
    private Long totalElements;
    private T data;
    private List<T> list;

    public BodyResponseDTO(String status, String message) {
        this(status, message, null, null);
    }

    public BodyResponseDTO(String status, String message, T data) {
        this(status, message, data, null);
    }

    public BodyResponseDTO(String status, String message, T data, Long totalElements) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.totalElements = totalElements;
    }

    public BodyResponseDTO(String status, String message, Page<T> page) {
        this(page);
        this.status = status;
        this.message = message;
    }

    public BodyResponseDTO(Page<T> page) {
        if (page == null) {
            page = new PageImpl(Collections.emptyList());
        }
        this.totalElements = page.getTotalElements();
        this.pageNumber = page.getNumber() + 1;
        this.pageSize = page.getSize();
        this.totalPage = page.getTotalPages();
        this.list = page.getContent();
    }

    public BodyResponseDTO(List<T> page, Pageable pageable, long total) {
        this(new PageImpl<T>(page, pageable, total));
    }

    public BodyResponseDTO(T data, Long totalElements, int totalPage, int pageSize, int pageNumber) {
        this.list = (List<T>) data;
        this.totalElements = totalElements;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.totalPage = totalPage;
    }

    public BodyResponseDTO(T data) {
        this.list = (List<T>) data;
    }

    public BodyResponseDTO() {

    }
}
