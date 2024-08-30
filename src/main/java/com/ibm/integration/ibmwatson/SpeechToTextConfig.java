package com.ibm.integration.ibmwatson;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "ibm.watson.services.speech2text")
public class SpeechToTextConfig {
    private String apiKey;
    private String url;
    private String model;
}
