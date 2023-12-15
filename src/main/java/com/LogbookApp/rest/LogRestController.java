package com.LogbookApp.rest;

import com.LogbookApp.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/log")
public class LogRestController {
    @Autowired
    private LogService logService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> logDetail(@PathVariable Long id) {
        var response = logService.logDetail(id);
        return ResponseEntity.status(200).body(response);
    }
}
