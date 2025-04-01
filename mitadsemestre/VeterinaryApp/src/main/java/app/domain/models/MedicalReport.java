package app.domain.models;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class MedicalReport {
    
    private long medicalReportId;
    private Timestamp dateCreated;
    private String Consultation;
    private String symptoms;
    private String diagnostic;
    private String procedure;
    private Order order;
    private String vaccinationHistory;
    private String allergies;
    private String detailProcedure;
    

    public MedicalReport(long medicalReportId, Timestamp dateCreated, String consultation, String symptoms,
            String diagnostic, String procedure, Order order, String vaccinationHistory, String allergies,
            String detailProcedure) {
        this.medicalReportId = medicalReportId;
        this.dateCreated = dateCreated;
        Consultation = consultation;
        this.symptoms = symptoms;
        this.diagnostic = diagnostic;
        this.procedure = procedure;
        this.order = order;
        this.vaccinationHistory = vaccinationHistory;
        this.allergies = allergies;
        this.detailProcedure = detailProcedure;
    }
    public String getClinicaId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getClinicaId'");
    }
}
