package app.domain.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.adapters.inputs.AdminInput;
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
public class AdminServices{

    @Autowired
    private PersonPort personPort;
    @Autowired
    private UserPort userPort;
    @Autowired
    private PetPort petPort;
    @Autowired
    public OrderPort ordenPort;
    @Autowired
    public MedicalReportPort medicalReportPort;

    public void registerVeterinarian(User user)throws Exception{
        if(personPort.existPerson(user.getDocument())){
            throw new Exception("Ya existe un veterinario con ese nombre");
        }
        if(userPort.existUserName(user.getUserName())){
            throw new Exception("Ya existe un usuario con ese nombre");
        }
        personPort.savePerson(user);
        userPort.saveUser(user);
    }

    public void registerSeller(User user)throws Exception{
        if(personPort.existPerson(user.getDocument())){
            throw new Exception("Ya existe un vendedor con ese nombre");
        }
        if(userPort.existUserName(user.getUserName())){
            throw new Exception("Ya existe un usuario con ese nombre");
        }
        personPort.savePerson(user);
        userPort.saveUser(user);
        
    }
    
    public void registerOwner(Person person) throws Exception{
        if(personPort.existPerson(person.getDocument())){
            throw new Exception("Ya existe un cliente con ese documento");
        }
        
        personPort.savePerson(person);
    }

    public void registerPet(Pet pet) throws Exception{
        Person person = personPort.findByPersonDocument(pet.getPersonId().getDocument());
        if(person == null){
            throw new Exception("No existe una persona con ese documetno");
        }
        pet.setPersonId(person);
        petPort.savePet(pet);
    }

    public void registerOrder(Order orden)throws Exception{
        Pet pet = petPort.findByPetId(orden.getPet().getPetId());
        Person person = personPort.findByPersonDocument(orden.getOwner().getDocument());
        User user = userPort.findByPersonDocument(orden.getVeterinarian().getDocument());
        if(pet == null){
            throw new Exception("No existe una mascota con ese documento");
        }
        if(person == null){
            throw new Exception("No existe un cliente con ese documetno");
        }
        if(user == null){
            throw new Exception("No existe un veterinario con ese documetno");
        }
        orden.setPet(pet);
        orden.setOwner(person);
        orden.setVeterinarian(user);
        ordenPort.saveOrder(orden);
    }
 
    public void registerMedicalReport(MedicalReport medicalReport) throws Exception {
        Order orden = ordenPort.findByOrderId(medicalReport.getOrder().getOrderId());
        if(orden == null){
            throw new Exception("No existe una orden con ese documento");
        }
        medicalReport.setOrder(orden);
        medicalReportPort.saveMedicalReport(medicalReport);
    }

  public MedicalReport getMedicalReportByMedicalReportId(long medicalReportId) throws Exception{
        MedicalReport medicalReport = medicalReportPort.getMedicalReportByMedicalReportId(medicalReportId);
        if(medicalReport == null){
            throw new Exception("No existe una historia clinica con ese ID");
        }
        return medicalReport;
    }

   public  List<Order> getAllOrderes() throws Exception {
        return ordenPort.getAllOrder();
    }

    public List<MedicalReport> getAllMedicalReports() throws Exception {
        return medicalReportPort.getAllMedicalReport();
    }

    public Order getOrderByOrderId(long orderId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOrderByOrderId'");
    }
}
