package com.example.book_store.controller;

import com.example.book_store.aspect.LogData;
import com.example.book_store.model.BookModel;
import com.example.book_store.service.BookService;
import com.example.book_store.service.MetricService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class MetricsController {
    private final MetricService metricService;
    @GetMapping(value = "/metric")
    @ResponseBody
    public Map getStatusMetric() {
        return metricService.getFullMetric();
    }
}
