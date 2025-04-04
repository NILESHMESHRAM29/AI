package com.gimini.AI.Controller;

import java.util.Map;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gimini.AI.Service.AiService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AiController {
   
    private final AiService aiService;

    @PostMapping("/ask")
    public ResponseEntity<String> askQuestion(@RequestBody Map<String, String> payload){
          String question=payload.get("question");
          String answer=aiService.getAns(question);
          return ResponseEntity.ok(answer);    
    }
}
