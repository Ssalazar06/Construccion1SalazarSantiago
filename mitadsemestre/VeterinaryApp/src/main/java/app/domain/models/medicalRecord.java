package app.domain.models;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class medicalRecord {
    
    private Timestamp dateCreated;
    private String vet;
    private String reasonConsultation;
    private String symptoms;
    private String diagnostic;
    private String procedure;
    private String item;
    private String dose;
    private String vaccinationHistory;
    private String allergies;
    private String detailProcedure;
    private boolean isActive;
    
    public medicalRecord(Timestamp dateCreated, String vet, String reasonConsultation, String symptoms,
            String diagnostic, String procedure, String item, String dose, String vaccinationHistory, String allergies,
            String detailProcedure, boolean isActive) {
        this.dateCreated = dateCreated;
        this.vet = vet;
        this.reasonConsultation = reasonConsultation;
        this.symptoms = symptoms;
        this.diagnostic = diagnostic;
        this.procedure = procedure;
        this.item = item;
        this.dose = dose;
        this.vaccinationHistory = vaccinationHistory;
        this.allergies = allergies;
        this.detailProcedure = detailProcedure;
        this.isActive = isActive;
    }
    
}
