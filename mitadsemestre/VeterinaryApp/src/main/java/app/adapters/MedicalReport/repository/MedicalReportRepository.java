package app.adapters.MedicalReport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.adapters.MedicalReport.entity.MedicalReportEntity;

@Repository
public interface MedicalReportRepository extends JpaRepository<MedicalReportEntity, Long>{
    public MedicalReportEntity findByOrderOrderId(long orderId);
    public MedicalReportEntity findByOrderPetPetId(long petId);
    public MedicalReportEntity findByOrderPersonDocument(long personDocument);
    public MedicalReportEntity findByMedicalReportId(long medicalReportId);
}
