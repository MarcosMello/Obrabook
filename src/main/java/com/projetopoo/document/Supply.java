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
@Document(collection = "supplies")
public class Supply {
    @Transient
    public static final String SEQUENCE_NAME = "supply_sequence";

    @Id
    private long id;

    @NonNull
    private String name;
    @NonNull
    private float pricePerKg;

    @NonNull
    private long engineerID;
}
