package com.ibm.integration.ibmwatson;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.integration.ISpeechToTextComponent;
import com.ibm.integration.IntegrationException;
import com.ibm.model.TranscriptionDTO;
import com.ibm.watson.speech_to_text.v1.SpeechToText;
import com.ibm.watson.speech_to_text.v1.model.RecognizeOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;

@Slf4j
@Component
@Qualifier("ibmWatsonComponent")
public class SpeechToTextComponent implements ISpeechToTextComponent {

    @Autowired
    private SpeechToTextConfig config;
    private SpeechToText speechToText;

    @PostConstruct
    protected void initSpeechToText() {
        var authenticator = new IamAuthenticator(config.getApiKey());
        this.speechToText = new SpeechToText(authenticator);
        this.speechToText.setServiceUrl(config.getUrl());
    }

    @Override
    public TranscriptionDTO transcript(MultipartFile file) throws IntegrationException {
        try {
            RecognizeOptions recognizeOptions = new RecognizeOptions.Builder()
                    .model(config.getModel())
                    .audio(file.getInputStream())
                    .contentType(file.getContentType())
                    .wordAlternativesThreshold((float) 0.9)
                    .keywords(Arrays.asList("colorado", "tornado", "tornadoes"))
                    .keywordsThreshold((float) 0.5)
                    .build();

            var speechRecognitionResults = speechToText.recognize(recognizeOptions).execute().getResult();
            return TranscriptionDTO.builder()
                    .text(speechRecognitionResults.getResults().get(0).getAlternatives().get(0).getTranscript())
                    .build();
        } catch (IOException e) {
            log.error("ERROR: {}", e.getMessage());
            throw new IntegrationException("ERROR: IBM Watson integration layer.", e);
        }
    }
}
