package com.ibm.model;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TranscriptionDTO implements Serializable {

    private String text;
}
