package com.dev.model;

import java.util.Map;

import lombok.Data;

@Data
public class Note {
    private String subject;
    private String content;
    private Map<String, String> data;
    private String token;
}