package com.prakash.backend_service.ResponseBody;

import java.time.LocalDate;

public class ReportResponse {
    private String report;
    private String status;
    private String time;

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ReportResponse{" +
                "report='" + report + '\'' +
                ", status='" + status + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public ReportResponse(String report, String status, String time) {
        this.report = report;
        this.status = status;
        this.time = time;
    }


}
