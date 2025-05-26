package app.adapters.rest.resposive;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MedicalRest {
    private long medicalReportId;
    private Timestamp dateCreated;
    private String Consultation;
    private String symptoms;
    private String diagnostic;
    private String procedure;
    private OrderRest order;
    private String vaccinationHistory;
    private String allergies;
    private String detailProcedure;
}
