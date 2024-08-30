package com.ibm.business;

import com.ibm.model.TranscriptionDTO;
import org.springframework.web.multipart.MultipartFile;

public interface ISpeechToTextService {

    /**
     * Transcript audio.
     *
     * @param file
     * @return transcription
     */
    TranscriptionDTO transcript(MultipartFile file);
}
