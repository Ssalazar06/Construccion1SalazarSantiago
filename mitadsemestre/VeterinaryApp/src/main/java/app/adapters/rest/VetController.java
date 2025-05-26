package app.adapters.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import app.Exceptions.BusinessException;
import app.Exceptions.InputException;
import app.adapters.MedicalReport.MedicalReportAdapter;
import app.adapters.rest.request.MedicalReportRequest;
import app.adapters.rest.request.OrderRequest;
import app.adapters.rest.request.PersonRequest;
import app.adapters.rest.request.PetRequest;
import app.adapters.rest.resposive.MedicalRest;
import app.adapters.rest.resposive.OrderRest;
import app.adapters.rest.resposive.PersonRest;
import app.adapters.rest.resposive.PetRest;
import app.domain.models.MedicalReport;
import app.domain.models.Order;
import app.domain.models.Person;
import app.domain.models.Pet;
import app.domain.models.User;
import app.domain.services.VeterinaryServices;
import app.ports.MedicalReportPort;
import app.ports.OrderPort;
import app.ports.PersonPort;
import app.ports.PetPort;
import app.ports.UserPort;

@RestController
public class VetController {
    @Autowired
    private VeterinaryServices veterinaryServices;
    @Autowired
    private PersonPort personPort;
    // @Autowired
    // private PersonValidator personValidator;
    @Autowired
    private PetPort petPort;
    @Autowired
    private UserPort userPort;
    @Autowired
    private OrderPort orderPort;
    @Autowired
    private MedicalReportPort medicalReportPort;

    @GetMapping("/")
    public String itsAlive(){
        return "Its alive!";
    }

    @PostMapping("/client")
    public ResponseEntity registerClient(@RequestBody PersonRequest request){ 
        try {
            System.out.println(request.toString());
            Person person = new Person();
            //CONSULTAR CON EL PROFESOR
                //person.setPersonDocument(personValidator.documentValidator(Long.toString(request.getPersonDocument())));
            person.setPersonDocument(request.getPersonDocument());
            person.setPersonName(request.getPersonName());
            person.setPersonAge(request.getPersonAge());

            if(request.getPersonDocument() == 0 || request.getPersonName() == null || request.getPersonAge() == 0){
                throw new InputException("Datos invalidos");
            }

            veterinaryServices.registerClient(person);
            
            return new ResponseEntity("Se ha registrado el cliente", HttpStatus.OK);
        }catch(BusinessException be){
            return new ResponseEntity(be.getMessage(), HttpStatus.CONFLICT);
        }catch(InputException ie){
            return new ResponseEntity(ie.getMessage(),HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/pets")
    public ResponseEntity registerPet(@RequestBody PetRequest request){
        try {
            System.out.println(request.toString());
            System.out.println();
            
            Pet pet = new Pet();
            long ownerDocument = request.getOwnerDocument();
            Person owner = personPort.findByPersonDocument(ownerDocument);
            pet.setPersonId(owner);
            pet.setPetName(request.getPetName());
            pet.setPetAge(request.getPetAge());
            pet.setPetSpecies(request.getPetSpecies());
            pet.setPetRace(request.getPetRace());
            pet.setPetWeight(request.getPetWeight());

            if(request.getOwnerDocument()==0 || request.getPetName() == null || request.getPetSpecies() == null || request.getPetRace() == null || request.getPetWeight() == 0){
                throw new InputException("Datos invalidos");
            }

            veterinaryServices.registerPet(pet);
            return new ResponseEntity("Se ha registrado la mascota", HttpStatus.OK);
            
        }catch(BusinessException be){
            return new ResponseEntity(be.getMessage(), HttpStatus.CONFLICT);
        }catch(InputException ie){
            return new ResponseEntity(ie.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pets/{id}")
    public ResponseEntity getPet(@PathVariable long id){
        try {
            Pet pet = petPort.findByPetId(id);
            PetRest petRest = petRestAdapter(pet);
            return new ResponseEntity(petRest, HttpStatus.OK);
        }catch (BusinessException be){
            return new ResponseEntity(be.getMessage(),HttpStatus.CONFLICT);
        }catch(InputException ie){
            return new ResponseEntity(ie.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/order")
    public ResponseEntity registerOrder(@RequestBody OrderRequest request){
        try {
            long petId = request.getPetId();
            Pet pet = petPort.findByPetId(petId);
            if (pet == null){
                throw new BusinessException("No existe la mascota con el id " + petId);
            }

            Person owner = pet.getPersonId();

            long vetId = request.getVeterinarianId();
            User veterinarian = userPort.findByUserId(vetId);
            if (veterinarian == null){
                throw new BusinessException("No existe el veterinario con el documento: " + vetId);
            }

            Order order = new Order();
            order.setPet(pet);
            order.setOwner(owner);
            order.setVeterinarian(veterinarian);
            order.setMedicationName(request.getMedicationName());
            order.setMedicationDosis(request.getMedicationDosis());
            order.setOrderStatus("Activa");
            if(request.getPetId()==0 || request.getVeterinarianId()==0 || request.getMedicationName() == null || request.getMedicationDosis() == 0){
                throw new InputException("Datos invalidos");
            }
            veterinaryServices.registerOrder(order);
            return new ResponseEntity("Se ha registrado la order", HttpStatus.OK);
        }catch (BusinessException be) {
            return new ResponseEntity(be.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/Record")
    public ResponseEntity registerRecord(@RequestBody MedicalReportRequest request){
        try {
            System.out.println(request.toString());
            long orderId = request.getOrder();
            Order order = orderPort.findByOrderId(orderId);
            MedicalReport medicalReport = new MedicalReport();
            medicalReport.setOrder(order);
            medicalReport.setConsultation(request.getConsultation());
            medicalReport.setSymptoms(request.getSymptoms());
            medicalReport.setDiagnostic(request.getDiagnostic());
            medicalReport.setProcedure(request.getProcedure());

            if ( medicalReport.getOrder().getPet().getPetSpecies().equalsIgnoreCase("perro") || medicalReport.getOrder().getPet().getPetSpecies().equalsIgnoreCase("gato")) {
                medicalReport.setVaccinationHistory(request.getVaccinationHistory());
            } else {
                medicalReport.setVaccinationHistory("No aplica");
            }
            
            medicalReport.setAllergies(request.getAllergies());
            medicalReport.setDetailProcedure(request.getDetailProcedure());

            if(request.getOrder()==0 || request.getConsultation() == null || request.getSymptoms() == null || request.getDiagnostic() == null || request.getProcedure() == null){
                throw new InputException("Datos invalidos");
            }

            veterinaryServices.registerMedicalReport(medicalReport);
            return new ResponseEntity("Se ha registado la historia clinica", HttpStatus.OK);

        }catch(BusinessException be){
            return new ResponseEntity(be.getMessage(),HttpStatus.CONFLICT);
        }catch(InputException ie){
            return new ResponseEntity(ie.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/GetAllRecord")    
    public ResponseEntity getAlMedicalreport(){
        try {
            List<MedicalReport> records = veterinaryServices.getAllMedicalReports();
            List<MedicalRest> medicalRestList = MedicalReportAdapterList(records);
            return new ResponseEntity(medicalRestList, HttpStatus.OK);
        }catch(BusinessException be){
            return new ResponseEntity(be.getMessage(),HttpStatus.CONFLICT);
        }catch(InputException ie){
            return new ResponseEntity(ie.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private List<MedicalRest> MedicalReportAdapterList(List<MedicalReport> records) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'MedicalReportAdapterList'");
    }

    @GetMapping("/GetAllOrder")
    public ResponseEntity getAllOrderes(){
        try {
            List<Order> orderes = veterinaryServices.getAllOrderes();
            List<OrderRest> orderRerstList = orderAdapterList(orderes);
            return new ResponseEntity(orderRerstList,HttpStatus.OK);
        }catch(BusinessException be){
            return new ResponseEntity(be.getMessage(),HttpStatus.CONFLICT);
        }catch(InputException ie){
            return new ResponseEntity(ie.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    @GetMapping("/GetOrder/{orderId}")
    public ResponseEntity getOrder(@PathVariable long orderId){
        try {
            Order order = orderPort.findByOrderId(orderId);
            OrderRest orderRest = orderRestAdapter(order);
            if(order == null){
                throw new BusinessException("No existe la order con el id: " + orderId);
            }
            return new ResponseEntity(orderRest,HttpStatus.OK);
        }catch(BusinessException be){
            return new ResponseEntity(be.getMessage(),HttpStatus.CONFLICT);
        }catch(InputException ie){
            return new ResponseEntity(ie.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("GetRecord/{clinicaId}")
    public ResponseEntity getRecord(@PathVariable long clinicaId){
        try {
            MedicalReport medicalReport = medicalReportPort.getMedicalReportByMedicalReportId(clinicaId);
            MedicalRest medicalRest = medicalRestAdapter(medicalReport);
            if(medicalReport == null){
                throw new BusinessException("No existe la historia clinica con el id: " + clinicaId);
            }
            return new ResponseEntity(medicalRest, HttpStatus.OK);
        }catch (Exception e){
            return null;
        }
    }

    @PatchMapping("/CancelOrder/{orderId}")
    public ResponseEntity cancelOrder(@PathVariable long orderId){
        try {
            Order order = orderPort.findByOrderId(orderId);
            if(order == null){
                throw new BusinessException("No existe la order con el id: " + orderId);
            }
            veterinaryServices.cancelOrder(order);
            return new ResponseEntity("Se ha cancelado la order", HttpStatus.OK);
        }catch(BusinessException be){
            return new ResponseEntity(be.getMessage(),HttpStatus.CONFLICT);
        }catch(InputException ie){
            return new ResponseEntity(ie.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/UpdateOrder/{orderId}")
    public ResponseEntity updateOrder(@PathVariable long orderId, @RequestBody OrderRequest request){
        try {
            Order order = orderPort.findByOrderId(orderId);
            if(order == null){
                throw new BusinessException("No existe la order con el id: " + orderId);
            }
            if(request.getMedicationName() != null) {order.setMedicationName(request.getMedicationName());}
            if(request.getMedicationDosis() != 0) {order.setMedicationDosis(request.getMedicationDosis());}
            veterinaryServices.updateOrder(order);
            return new ResponseEntity("Se ha actualizado la order", HttpStatus.OK);
        }catch(BusinessException be){
            return new ResponseEntity(be.getMessage(),HttpStatus.CONFLICT);
        }catch(InputException ie){
            return new ResponseEntity(ie.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/UpdateRecord/{clinicaId}")
    public ResponseEntity updateRecord(@PathVariable long clinicaId, @RequestBody MedicalReportRequest request){
        try {
            MedicalReport medicalReport = medicalReportPort.getMedicalReportByMedicalReportId(clinicaId);
            if(medicalReport == null){
                throw new BusinessException("No existe la historia clinica con el id: " + clinicaId);
            }
            if(request.getConsultation() != null) {medicalReport.setConsultation(request.getConsultation());}
            if(request.getSymptoms() != null) {medicalReport.setSymptoms(request.getSymptoms());}
            if(request.getDiagnostic() != null) {medicalReport.setDiagnostic(request.getDiagnostic());}
            if(request.getProcedure() != null) {medicalReport.setProcedure(request.getProcedure());}

            if(medicalReport.getOrder().getPet().getPetSpecies().equalsIgnoreCase("perro") || medicalReport.getOrder().getPet().getPetSpecies().equalsIgnoreCase("gato")){
                if(request.getVaccinationHistory() != null) {medicalReport.setVaccinationHistory(request.getVaccinationHistory());}
            }
            
            if(request.getAllergies()!= null){medicalReport.setAllergies(request.getAllergies());}
            if(request.getDetailProcedure() != null){medicalReport.setDetailProcedure(request.getDetailProcedure());}
            veterinaryServices.updateMedicalReport(medicalReport);
            return new ResponseEntity("Se ha actualizado la historia clinica", HttpStatus.OK);
        }catch(BusinessException be){
            return new ResponseEntity(be.getMessage(),HttpStatus.CONFLICT);
        }catch(InputException ie){
            return new ResponseEntity(ie.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    private PetRest petRestAdapter(Pet pet){
        PetRest petRest = new PetRest();
        petRest.setPetId(pet.getPetId());
        petRest.setPetName(pet.getPetName());
        petRest.setPetAge(pet.getPetAge());
        petRest.setPetSpecies(pet.getPetSpecies());
        petRest.setPetRace(pet.getPetRace());
        petRest.setPetWeight(pet.getPetWeight());
        return petRest;
    }

    private PersonRest personRestAdapter(Person person){
        PersonRest personRest = new PersonRest();
        personRest.setDocument(person.getDocument());
        personRest.setName(person.getName());
        personRest.setAge(person.getAge());
        return personRest;
    }

    private PersonRest userRestAdapter(User user){
        PersonRest personRest = new PersonRest();
        personRest.setDocument(user.getDocument());
        personRest.setName(user.getName());
        personRest.setAge(user.getAge());
        return personRest;
    }

    private OrderRest orderRestAdapter(Order order){
        OrderRest orderRest = new OrderRest();
        orderRest.setOrderId(order.getOrderId());
        orderRest.setPet(petRestAdapter(order.getPet()));
        orderRest.setOwner(personRestAdapter(order.getOwner()));
        orderRest.setVeterinarian(userRestAdapter(order.getVeterinarian()));
        orderRest.setMedicationName(order.getMedicationName());
        orderRest.setMedicationDosis(order.getMedicationDosis());
        orderRest.setOrderStatus(order.getOrderStatus());
        orderRest.setDate(order.getDate());
        return orderRest;
    }

    public List<OrderRest> orderAdapterList(List<Order> orderList){
        List<OrderRest> orderRestList = new ArrayList<>();
        for(Order order : orderList){
            orderRestList.add(orderRestAdapter(order));
        }
        return orderRestList;
    }

    public MedicalRest medicalRestAdapter(MedicalReport medicalReport){
        MedicalRest medicalRest = new MedicalRest();
        medicalRest.setMedicalReportId(medicalReport.getMedicalReportId());
        medicalRest.setDateCreated(medicalReport.getDateCreated());
        medicalRest.setConsultation(medicalReport.getConsultation());
        medicalRest.setSymptoms(medicalReport.getSymptoms());
        medicalRest.setDiagnostic(medicalReport.getDiagnostic());
        medicalRest.setProcedure(medicalReport.getProcedure());
        medicalRest.setOrder(orderRestAdapter(medicalReport.getOrder()));
        medicalRest.setVaccinationHistory(medicalReport.getVaccinationHistory());
        medicalRest.setAllergies(medicalReport.getAllergies());
        medicalRest.setDetailProcedure(medicalReport.getDetailProcedure());
        return medicalRest;
    }

    public List<MedicalRest> clinicaAdapterList(List<MedicalReport> medicalReportList){
        List<MedicalRest> medicalRestList = new ArrayList<>();
        for(MedicalReport medicalReport : medicalReportList){
            medicalRestList.add(medicalRestAdapter(medicalReport));
        }
        return medicalRestList;
    }

}
