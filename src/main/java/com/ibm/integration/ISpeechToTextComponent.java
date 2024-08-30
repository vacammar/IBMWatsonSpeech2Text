package com.ibm.integration;

import com.ibm.model.TranscriptionDTO;
import org.springframework.web.multipart.MultipartFile;

public interface ISpeechToTextComponent {

    /**
     * Transcript audio file with third party service.
     * For this PoC, we use IBM Watson.
     *
     * @param file
     * @return
     * @throws IntegrationException
     */
    TranscriptionDTO transcript(MultipartFile file) throws IntegrationException;
}
