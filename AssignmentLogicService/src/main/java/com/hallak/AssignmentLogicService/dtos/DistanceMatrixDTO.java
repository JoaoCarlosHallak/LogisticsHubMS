package com.hallak.AssignmentLogicService.dtos;


import lombok.Data;

import java.util.List;

@Data
public class DistanceMatrixDTO {

    private List<String> destination_addresses;
    private List<String> origin_addresses;
    private List<Row> rows;
    private String status;

    @Data
    public static class Row {
        private List<Element> elements;
    }

    @Data
    public static class Element {
        private Distance distance;
        private Duration duration;
        private String status;
    }

    @Data
    public static class Distance {
        private String text;
        private int value;
    }

    @Data
    public static class Duration {
        private String text;
        private int value;
    }
}
