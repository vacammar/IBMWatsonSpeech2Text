package com.ibm.business;

import com.ibm.integration.IntegrationException;
import com.ibm.integration.ibmwatson.SpeechToTextComponent;
import com.ibm.model.TranscriptionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class SpeechToTextService implements ISpeechToTextService {

    @Autowired
    @Qualifier("ibmWatsonComponent")
    private SpeechToTextComponent speechToTextComponent;

    @Override
    public TranscriptionDTO transcript(MultipartFile file) {
        try {
            return this.speechToTextComponent.transcript(file);
        } catch (IntegrationException e) {
            log.error("ERROR: {}", e.getMessage());
            return this.getErrorTranscription();
        }
    }

    /**
     * Return default message when errors occurs.
     *
     * @return error text: Error when try to transcript audio file.
     */
    private TranscriptionDTO getErrorTranscription() {
        return TranscriptionDTO.builder()
                .text("Error when try to transcript audio file.")
                .build();
    }
}
