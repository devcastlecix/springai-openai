package com.devcastle.course.springai.apps.openai.models;

import java.util.List;

public record CodeGeneration(String requirement, String summary, String code, List<String> notes) {
}
