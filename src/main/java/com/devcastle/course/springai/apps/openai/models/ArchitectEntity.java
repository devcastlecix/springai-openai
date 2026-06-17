package com.devcastle.course.springai.apps.openai.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ArchitectEntity(String question,
                              String recommendation,
                              List<String> pros,
                              List<String> cons,
                              @JsonProperty("final_decision") String finalDecision) {
}
