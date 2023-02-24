package com.example.claimsmicroservice.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class PieResponse {
    private List<String> labels;
    private List<Long> values;
}
