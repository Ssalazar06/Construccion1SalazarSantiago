package app.ports;

import java.util.List;

public interface medicalReportPort {
    public List<medicalReportPort> getAllMedicalReport();
    public List<medicalReportPort> getAllMedicalReportByVet();    
}
