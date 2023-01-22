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
@Document(collection = "workers")
public class Worker {
    @Transient
    public static final String SEQUENCE_NAME = "worker_sequence";

    @Id
    private long id;

    @NonNull
    private String firstName;
    @NonNull
    private String lastName;

    @NonNull
    private float dailyWage;

    @NonNull
    private String phoneNo;

    @NonNull
    private String type;

    @NonNull
    private int status;

    @NonNull
    private boolean isAssigned;

    private String cpf;
    private int age;
    private String street;
    private String district;
    private int number;
    private String zip_code;

    @NonNull
    private long engineerID;
}
