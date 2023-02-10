package com.example.demo.web.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyLimitClosingReportReq {
    private String title;
    private String fromDate;
    private String toDate;
    private String currentTime;
    private List<BaoCao> baocaos;
}
