package app.adapters.rest.request;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class MedicalReportRequest {
    private String consultation;
    private String symptoms;
    private String diagnostic;
    private String procedure;
    private long order;
    private String vaccinationHistory;
    private String allergies;
    private String detailProcedure;  
}
