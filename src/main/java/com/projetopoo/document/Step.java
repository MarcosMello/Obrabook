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
@Document(collection = "steps")
public class Step {
    @Transient
    public static final String SEQUENCE_NAME = "step_sequence";

    @Id
    private long id;

    @NonNull
    private String name;
    @NonNull
    private double expectedValue;
    @NonNull
    private double actualValue;

    @NonNull
    private boolean isComplete;

    @NonNull
    private long constructionID;

    public boolean getIsComplete() {
        return isComplete;
    }
}
