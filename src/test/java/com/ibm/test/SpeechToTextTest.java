package com.ibm.test;

import com.ibm.business.ISpeechToTextService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SpeechToTextTest {
    private final String AUDIO_FILE_PATH = "/audio/";
    private final String AUDIO_FILE = "audio-demo.flac";
    private final String EXPECTED_TEST = "a line of severe thunderstorms with several possible tornadoes is approaching Colorado on Sunday ";

    @Autowired
    private ISpeechToTextService speechToTextService;

    @Test
    public void transcribeTest() {
        try {
            var fileAsBytes = this.getClass().getResourceAsStream(AUDIO_FILE_PATH + AUDIO_FILE).readAllBytes();
            var audioFile = new AudioMultipartFile(AUDIO_FILE, fileAsBytes, "Simple audio file");
            var transcriptionDTO = this.speechToTextService.transcript(audioFile);
            Assert.assertEquals("Transcription OK, well done Watson.", EXPECTED_TEST, transcriptionDTO.getText());
        } catch (Exception e) {
            log.error("ERROR: {}", e.getMessage());
            Assert.fail("Exception occurred");
        }
    }

    private static class AudioMultipartFile extends ByteArrayResource implements MultipartFile {
        private final String fileName;
        private final byte[] byteArray;
        private final MediaType mediaType = MediaType.APPLICATION_OCTET_STREAM;

        public AudioMultipartFile(String fileName, byte[] byteArray, String description) {
            super(byteArray, description);
            this.fileName = fileName;
            this.byteArray = byteArray;
        }

        @Override
        public String getFilename() {
            return fileName;
        }

        @Override
        public String getName() {
            return this.fileName;
        }

        @Override
        public String getOriginalFilename() {
            return this.fileName;
        }

        @Override
        public String getContentType() {
            return this.mediaType.toString();
        }

        @Override
        public boolean isEmpty() {
            return (this.byteArray == null || this.byteArray.length == 0);
        }

        @Override
        public long getSize() {
            return this.byteArray.length;
        }

        @Override
        public byte[] getBytes() throws IOException {
            return this.byteArray;
        }

        @Override
        public void transferTo(File dest) throws IOException, IllegalStateException {

        }
    }
}
