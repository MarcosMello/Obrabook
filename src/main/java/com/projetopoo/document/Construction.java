package com.projetopoo.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "constructions")
public class Construction {
    @Transient
    public static final String SEQUENCE_NAME = "construction_sequence";

    @Id
    private long id;

    @NonNull
    private String name;

    @NonNull
    private long engineerID;

    private long clientID;
}
