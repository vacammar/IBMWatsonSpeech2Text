package com.ibm.api;

import com.ibm.business.ISpeechToTextService;
import com.ibm.model.TranscriptionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/transcript")
public class SpeechToTextAPI {

    @Autowired
    private ISpeechToTextService speechToTextService;

    @PostMapping
    public ResponseEntity<TranscriptionDTO> transcript(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(this.speechToTextService.transcript(file));
    }
}
