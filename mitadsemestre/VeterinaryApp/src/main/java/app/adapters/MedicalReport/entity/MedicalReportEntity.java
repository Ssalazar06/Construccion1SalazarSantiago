package app.adapters.MedicalReport.entity;

import java.sql.Timestamp;

import app.adapters.order.entity.OrderEntity;
import app.adapters.users.entity.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name="medical_Report")
@Setter
@Getter
@NoArgsConstructor
public class MedicalReportEntity {
    @Id
    @Column(name="medical_Report_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long medicalReportId;
    @Column(name="date")
    private Timestamp date;
    @Column(name ="consultation")
    private String consultation;
    @Column(name="syntomatology")
    private String syntomatology;
    @Column(name="diagnostic")
    private String diagnostic;
    @Column(name="treatment")
    private String treatment;
    //Obtener Cliente, Mascota y Veterinario
    @JoinColumn(name="order_id")
    @OneToOne
    private OrderEntity order;
    @Column(name="vaccination_History")
    private String vaccinationHistory;
    @Column(name="allergies")
    private String allergies;
    @Column(name="detailProcedure")
    private String detailProcedure;
    public String getSymptoms() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSymptoms'");
    }
    public Object getMedicalReportId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMedicalReportId'");
    }
    public void setSymptoms(String symptoms) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setSymptoms'");
    }
    public void setClinicaId(long medicalReportId2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setClinicaId'");
    }

}
