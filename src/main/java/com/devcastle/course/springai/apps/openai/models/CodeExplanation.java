package com.devcastle.course.springai.apps.openai.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record CodeExplanation(String language, String summary,
                              @JsonProperty("line_by_line")
                              List<LineExplanation> lineByLine,
                              @JsonProperty("final_explanation")
                              String finalExplanation) {

    public record LineExplanation(Integer line, String explanation) {}
}
