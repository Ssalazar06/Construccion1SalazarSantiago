package app.adapters.MedicalReport;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.adapters.MedicalReport.entity.MedicalReportEntity;
import app.adapters.MedicalReport.repository.MedicalReportRepository;
import app.adapters.order.entity.OrderEntity;
import app.adapters.persons.entity.PersonEntity;
import app.adapters.pet.entity.PetEntity;
import app.adapters.users.entity.UserEntity;
import app.domain.models.MedicalReport;
import app.domain.models.Order;
import app.domain.models.Person;
import app.domain.models.Pet;
import app.domain.models.User;
import app.ports.MedicalReportPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Service
public class MedicalReportAdapter implements MedicalReportPort{
    @Autowired
    MedicalReportRepository MedicalReportRepository;

    
    @Override
    public void saveMedicalReport(MedicalReport medicalReport) throws Exception {
        MedicalReportEntity medicalReportEntity = medicalReportAdapter(medicalReport);
        MedicalReportRepository.save(medicalReportEntity);
        medicalReport.setMedicalReportId((long) medicalReportEntity.getMedicalReportId());
        medicalReport.setDateCreated(medicalReportEntity.getDateCreated());
        System.out.println("Historia Clinica Guardada!");
    }  
    
    @Override
    public MedicalReport getMedicalReportByMedicalReportId(long MedicalReportId) throws Exception {
        MedicalReport MedicalReport = medicalReportAdapter(MedicalReportRepository.findByMedicalReportId(MedicalReportId));
                if(MedicalReport == null){throw new Exception("No existe una historia con ese documento");}
                return MedicalReport;
            }
        
            private MedicalReportEntity medicalReportAdapter(MedicalReport MedicalReport){
        MedicalReportEntity medicalReportEntity = new MedicalReportEntity();
        medicalReportEntity.setMedicalReportId(MedicalReport.getMedicalReportId());
        medicalReportEntity.setDateCreated(MedicalReport.getDateCreated());
        medicalReportEntity.setConsultation(MedicalReport.getConsultation());
        medicalReportEntity.setSymptoms(MedicalReport.getSymptoms());
        medicalReportEntity.setDiagnostic(MedicalReport.getDiagnostic());
        medicalReportEntity.setDetailProcedure(MedicalReport.getProcedure());
        medicalReportEntity.setOrder(orderAdapter(MedicalReport.getOrder()));  //Obtener Cliente, Mascota y Veterinario
        medicalReportEntity.setVaccinationHistory(MedicalReport.getVaccinationHistory());
        medicalReportEntity.setAllergies(MedicalReport.getAllergies());
        medicalReportEntity.setDetailProcedure(MedicalReport.getDetailProcedure());
        return medicalReportEntity;
    }

    private MedicalReport medicalReportAdapter(MedicalReportEntity medicalReportEntity){
        MedicalReport medicalReport = new MedicalReport();
        medicalReport.setMedicalReportId((long) medicalReportEntity.getMedicalReportId());
        medicalReport.setDateCreated(medicalReportEntity.getDateCreated());
        medicalReport.setConsultation(medicalReportEntity.getConsultation());
        medicalReport.setSymptoms(medicalReportEntity.getSymptoms());
        medicalReport.setDiagnostic(medicalReportEntity.getDiagnostic());
        medicalReport.setProcedure(medicalReportEntity.getDetailProcedure());
        medicalReport.setOrder(orderAdapter(medicalReportEntity.getOrder()));  //Obtener Cliente, Mascota y Veterinario
        medicalReport.setVaccinationHistory(medicalReportEntity.getVaccinationHistory());
        medicalReport.setAllergies(medicalReportEntity.getAllergies());
        medicalReport.setProcedure(medicalReportEntity.getDetailProcedure());
        return medicalReport;
    }

        private OrderEntity orderAdapter(Order order){
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(order.getOrderId());
        orderEntity.setPet(petAdapter(order.getPet()));
        orderEntity.setPerson(personAdapter(order.getOwner()));
        orderEntity.setUser(userAdapter(order.getVeterinarian()));
        orderEntity.setMedicationName(order.getMedicationName());
        orderEntity.setMedicationDosis(order.getMedicationDosis());
        orderEntity.setDate(order.getDate());
        orderEntity.setOrderStatus(order.getOrderStatus());
        return orderEntity;
    }

    private Order orderAdapter(OrderEntity orderEntity){
        Order order = new Order();
        order.setOrderId(orderEntity.getOrderId());
        order.setPet(petAdapter(orderEntity.getPet()));
        order.setOwner(personAdapter(orderEntity.getPerson()));
        order.setVeterinarian(userAdapter(orderEntity.getUser()));
        order.setMedicationName(orderEntity.getMedicationName());
        order.setMedicationDosis(orderEntity.getMedicationDosis());
        order.setDate(orderEntity.getDate());
        order.setOrderStatus(orderEntity.getOrderStatus());
        return order;
    }
        private PetEntity petAdapter(Pet pet){
        PetEntity petEntity = new PetEntity();
        petEntity.setPetId(pet.getPetId());
        petEntity.setPetName(pet.getPetName());
        petEntity.setPetAge(pet.getPetAge());
        petEntity.setPetSpecies(pet.getPetSpecies());
        petEntity.setPetRace(pet.getPetRace());
        petEntity.setPetWeight(pet.getPetWeight());
        return petEntity;
    }

    private PersonEntity personAdapter(Person person){
        PersonEntity personEntity = new PersonEntity();
        personEntity.setName(person.getName());
        personEntity.setDocument(person.getDocument());
        personEntity.setAge(person.getAge());
        return personEntity;
    }

    private Pet petAdapter(PetEntity petEntity) {
        Pet pet = new Pet();
        pet.setPetId(petEntity.getPetId());
        pet.setPetName(petEntity.getPetName());
        pet.setPetAge(petEntity.getPetAge());
        pet.setPetSpecies(petEntity.getPetSpecies());
        pet.setPetRace(petEntity.getPetRace());
        pet.setPetWeight(petEntity.getPetWeight());
        return pet;
    }

    private Person personAdapter(PersonEntity personEntity){
        Person person = new Person();
        person.setName(personEntity.getName());
        person.setDocument(personEntity.getDocument());
        person.setAge(personEntity.getAge());
        return person;
    }
    public User userAdapter(UserEntity userEntity){
        User user = new User();
        user.setDocument(userEntity.getPerson().getDocument());
        user.setName(userEntity.getPerson().getName());
        user.setAge(userEntity.getPerson().getAge());
        user.setUserId(userEntity.getUserId());
        user.setUserName(userEntity.getUserName());
        user.setPassword(userEntity.getPassword());
        user.setRole(userEntity.getRole());
        return user;
    }

    private UserEntity userAdapter(User user){
        //reemplazo metodo personAdpter por mala implementación
        PersonEntity personEntity = new PersonEntity();
        personEntity.setName(user.getName());
        personEntity.setDocument(user.getDocument());
        personEntity.setAge(user.getAge());

        UserEntity userEntity = new UserEntity();
        userEntity.setPerson(personEntity);
        userEntity.setUserId(user.getUserId());
        userEntity.setUserName(user.getUserName());
        userEntity.setPassword(user.getPassword());
        userEntity.setRole(user.getRole());
        return userEntity;
    }

    @Override
    public List<MedicalReport> getAllMedicalReport() throws Exception {
        List<MedicalReportEntity> medicalReportEntityList = MedicalReportRepository.findAll();
        if(medicalReportEntityList.isEmpty()){throw new Exception("No hay historias medicalReports");}
        return medicalReportAdapterList(medicalReportEntityList);
    }
    
    public List<MedicalReport> medicalReportAdapterList(List<MedicalReportEntity> medicalReportEntityList){
        List<MedicalReport> MedicalReportList = new ArrayList<>();
        for(MedicalReportEntity medicalReportEntity : medicalReportEntityList){
            MedicalReportList.add(medicalReportAdapter(medicalReportEntity));
        }
        return MedicalReportList;
    }
}
