package app.ports;

import java.util.List;

import app.domain.models.MedicalReport;
import app.domain.models.Order;
import app.domain.services.AdminServices;

public interface MedicalReportPort {
    public MedicalReport getMedicalReportByMedicalReportId(long MedicalReportId)throws Exception;
    public List<MedicalReport> getAllMedicalReport()throws Exception;
    public void saveMedicalReport(MedicalReport medicalReport)throws Exception;
    default Order getOrderByOrderId(AdminServices adminServices, long ordenId) throws Exception{
        Order orden = adminServices.ordenPort.findByOrderId(ordenId);
        if(orden == null){
            throw new Exception("No existe una orden con ese ID");
        }
        return orden;
    } 
}
