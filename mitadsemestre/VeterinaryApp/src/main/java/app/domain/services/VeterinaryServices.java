package app.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.models.MedicalReport;
import app.domain.models.Order;
import app.domain.models.Person;
import app.domain.models.Pet;
import app.domain.models.User;
import app.ports.MedicalReportPort;
import app.ports.OrderPort;
import app.ports.PersonPort;
import app.ports.PetPort;
import app.ports.UserPort;

@Service
public class VeterinaryServices {
    @Autowired
    private MedicalReportPort medicalReportPort;
    @Autowired
    private PetPort petPort;
    @Autowired
    private PersonPort personPort;
    @Autowired
    private UserPort userPort;
    @Autowired
    private OrderPort orderPort;

    public void registerPet(Pet pet) throws Exception{
        Person person = personPort.findByPersonDocument(pet.getPersonId().getDocument());
        if(person == null){
            throw new Exception("No existe una persona con ese documento");
        }
        
        pet.setPersonId(person);
        petPort.savePet(pet);
    }
    
    public void registerClient(Person person)throws Exception{
        if(personPort.existPerson(person.getDocument())){
            throw new Exception("Ya existe una Ciente con esa cedula");
        }
        personPort.savePerson(person);
        System.out.println("Cliente Creado");
    }

    public void registerOrder(Order order) throws Exception{
        Pet pet = petPort.findByPetId(order.getPet().getPetId());
        Person person = personPort.findByPersonDocument(order.getOwner().getDocument());
        User user = userPort.findByPersonDocument(order.getVeterinarian().getDocument());
        if(pet == null){
            throw new Exception("No existe mascota con ese ID");
        }
        if(person == null){
            throw new Exception("No existe cliente con esa cedula"); 
        }
        if(user == null){
            throw new Exception("No existe veterinario con esa cedula");
        }
        order.setPet(pet);
        order.setOwner(person);
        order.setVeterinarian(user);
        orderPort.saveOrder(order);
    }

    public Order getOrderByOrderId(long orderId) throws Exception{
        Order order = orderPort.findByOrderId(orderId);
        if(order == null){
            throw new Exception("No existe una order con ese ID");
        }
        return order;
    }

    public void registerMedicalReport(MedicalReport medicalReport) throws Exception {
        Order order = orderPort.findByOrderId(medicalReport.getOrder().getOrderId());
        if(order == null){
            throw new Exception("No existe una order con ese documento");
        }
        medicalReport.setOrder(order);
        medicalReportPort.saveMedicalReport(medicalReport);
    }

    public void cancelOrder(Order order)throws Exception {
        if(order == null){
            throw new Exception("No existe una order con ese ID");
        }
        order.setOrderStatus("Anulada");
        orderPort.saveOrder(order);
    }

    public MedicalReport getClinicalRecordByClinicaId(long medicalReportId) throws Exception{
        MedicalReport medicalReport = medicalReportPort.getMedicalReportByMedicalReportId(medicalReportId);
        if(medicalReport == null){
            throw new Exception("No existe una historia clinica con ese ID");
        }
        return medicalReport;
    }

    public  List<Order> getAllOrderes() throws Exception {
        return orderPort.getAllOrder();
    }

    public List<MedicalReport> getAllMedicalReports() throws Exception {
        return medicalReportPort.getAllMedicalReport();
    }

    public void updateMedicalReport(MedicalReport medicalReport)throws Exception {
        medicalReportPort.saveMedicalReport(medicalReport);
    }

    public void updateOrder(Order order)throws Exception {
        orderPort.saveOrder(order);
    }

}
