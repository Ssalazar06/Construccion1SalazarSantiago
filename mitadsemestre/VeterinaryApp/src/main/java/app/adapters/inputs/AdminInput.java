package app.adapters.inputs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapters.inputs.utils.ClinicaValidator;
import app.adapters.inputs.utils.OrderValidator;
import app.adapters.inputs.utils.PersonValidator;
import app.adapters.inputs.utils.PetValidator;
import app.adapters.inputs.utils.UserValidator;
import app.adapters.inputs.utils.Utils;
import app.domain.models.MedicalRecord;
import app.domain.models.MedicalReport;
import app.domain.models.Order;
import app.domain.models.Person;
import app.domain.models.Pet;
import app.domain.models.User;
import app.domain.services.AdminServices;
import app.ports.InputPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Component
public class AdminInput implements InputPort{
    @Autowired
    private PersonValidator personValidator;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private AdminServices adminService;

    @Autowired
    private PetValidator petValidator;

    @Autowired
    private OrderValidator orderValidator;

    @Autowired
    private ClinicaValidator clinicaValidator;

    private final String MENU = "Ingrese la opción: " + "\n 1. Para Crear Veterinario" + "\n 2. Para Crear Vendedor" + "\n 3. Para Crear un cliente" + "\n 4. Para Crear Una Mascota\3" 
    + "\n 5. Crear Order"+"\n 6. Cear Historia Clinica"+"\n 7. ver orderes de una mascota" +"\n 8. Ver todas las orderes" + "\n 9. ver historias clínicas de una mascota" +"\n 10. Ver todas las historias clinicas"+ "\n 11. Cerrar Sesion";
    @Override
    public void menu() throws Exception {
        boolean sesion = true;
        while (sesion) {
            sesion = options();
        }
    }
    private boolean options() {
		try {
			System.out.println(MENU);
			String option = Utils.getReader().nextLine();
			switch (option) {
			case "1": {
                this.creteVet();
			    return true;
			}
            case "2":
                this.creteSeller();
                return true;
            case "3":
                this.createCliend();
                return true;
            case "4":
                this.cretedPet();
                return true;
            case "5":
                this.createOrder();
                return true;
            case "6":
                this.createClinica();
                return true;
            case "7":
                this.getOrder();
                return true;
            case "8":
                adminService.getAllOrderes().forEach(order -> System.out.println(toStringOrder(order)));
                System.out.println();
                return true;
            case "9":
                this.getClinicalRecord();
                return true;
            case "10":
                adminService.getAllMedicalReports().forEach(medicalReport -> System.out.println(toStringClinica(medicalReport)));
                System.out.println();
                return true;
			case "11" :{
				System.out.println("Se ha cerrado sesion");
				return false;
			}
			default:
				System.out.println("opcion no valida");
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return true;
		}
	}
    
    private void creteVet() throws Exception{
        System.out.print("Ingrese el nombre del veterinario: ");
        String name = personValidator.nameValidator(Utils.getReader().nextLine());
        System.out.print("Ingrese el documento del veterinario: ");
        long document = personValidator.documentValudator(Utils.getReader().nextLine());
        System.out.print("Ingrese la edad del veterinario: ");
        int age = personValidator.ageValidator(Utils.getReader().nextLine());
        System.out.print("Ingrese el userName del veterinario: ");
        String userName = userValidator.userNameValidator(Utils.getReader().nextLine());
        System.out.print("Ingrese la contraseña de veterinario: ");
        String password = userValidator.passwordValidator(Utils.getReader().nextLine());
        User user = new User();
        user.setPersonName(name);
        user.setPersonDocument(document);
        user.setPersonAge(age);
        user.setUserName(userName);
        user.setPassword(password);
        user.setRole("veterinary");
        adminService.registerVeterinarian(user);
        System.out.println("Veterinario Registrado");
        System.out.println(toStringPerson(user));
        System.out.println(user.getUserName());
        System.out.println(user.getPassword());
    }

    private void creteSeller() throws Exception{
        System.out.print("Ingrese el nombre del vendedor: ");
        String name = personValidator.nameValidator(Utils.getReader().nextLine());
        System.out.print("Ingrese el documento del vendedor: ");
        long document = personValidator.documentValudator(Utils.getReader().nextLine());
        System.out.print("Ingrese la edad del vendedor: ");
        int age = personValidator.ageValidator(Utils.getReader().nextLine());
        System.out.print("Ingrese el userName del vendedor: ");
        String userName = userValidator.userNameValidator(Utils.getReader().nextLine());
        System.out.print("Ingrese la contraseña de vendedor: ");
        String password = userValidator.passwordValidator(Utils.getReader().nextLine());
        User user = new User();
        user.setPersonName(name);
        user.setPersonDocument(document);
        user.setPersonAge(age);
        user.setUserName(userName);
        user.setPassword(password);
        user.setRole("seller");
        adminService.registerSeller(user);
        System.out.println("Vendedor Registrado");
        System.out.println(toStringPerson(user));
        System.out.println(user.getUserName());
        System.out.println(user.getPassword());
    }

    private void createCliend() throws Exception{
        System.out.print("Nombre de la persona: ");
        String name = personValidator.nameValidator(Utils.getReader().nextLine());
        System.out.print("Cedula de la persona: ");
        long document = personValidator.documentValudator(Utils.getReader().nextLine());
        System.out.print("Edad de la persona: ");
        int age = personValidator.ageValidator(Utils.getReader().nextLine());
        Person person = new Person();
        person.setPersonName(name);
        person.setPersonDocument(document);
        person.setPersonAge(age);
        adminService.registerOwner(person);
        System.out.println("Cliente Registrado");
        System.out.println(toStringPerson(person));
    }

    private void cretedPet() throws Exception{
        System.out.print("Cedula del dueño: ");
        long personDocument = personValidator.documentValudator(Utils.getReader().nextLine());
        System.out.print("Nombre de la mascota: ");
        String name = petValidator.nameValidator(Utils.getReader().nextLine());
        System.out.print("Edad de la mascota: ");
        int age = petValidator.ageValidator(Utils.getReader().nextLine());
        System.out.print("Especie de la mascota: ");
        String species = validPet();
        System.out.print("Raza de la mascota: ");
        String race = petValidator.raceValidator(Utils.getReader().nextLine());
        System.out.print("Peso de la mascota(Kg): ");
        double weigth = petValidator.weigthValidator(Utils.getReader().nextLine());
        Pet pet = new Pet();
        Person owner = new Person();
        owner.setPersonDocument(personDocument);
        pet.setPersonId(owner);
        pet.setPetAge(age);
        pet.setPetName(name);
        pet.setPetSpecies(species);
        pet.setPetRace(race);
        pet.setPetWeight(weigth);
        adminService.registerPet(pet);
        System.out.println("Madcota Registrada");
        System.out.println(toStringPet(pet));
    }

    private void createOrder() throws Exception{
        System.out.print("Ingrese el id de la mascota: ");
        long petId = petValidator.idValidator(Utils.getReader().nextLine());
        System.out.print("Cedula del dueño: ");
        long personDocument = personValidator.documentValudator(Utils.getReader().nextLine());
        System.out.print("Documento del veterinario: ");
        long userDocument = userValidator.documentValudator(Utils.getReader().nextLine());
        System.out.print("Nombre del medicamento: ");
        String medicationName = orderValidator.nameValidator(Utils.getReader().nextLine());
        System.out.print("Dosis del medicamento: ");
        double medicationDosis = orderValidator.dosisValidator(Utils.getReader().nextLine());
        Pet pet = new Pet();
        Person person = new Person();
        User user = new User();
        pet.setPetId(petId);
        person.setPersonDocument(personDocument);
        user.setPersonDocument(userDocument);
        Order order = new Order();
        order.setMedicationName(medicationName);
        order.setMedicationDosis(medicationDosis);
        order.setOrderStatus("Activa");
        order.setPet(pet);
        order.setOwner(person);
        order.setVeterinarian(user);
        System.out.println(order.getPet().getPetId());
        adminService.registerOrder(order);
        System.out.println("Order Creada");
        System.out.println(toStringOrder(order));
    }

    private void createClinica()throws Exception{
        System.out.print("Motivo de consulta: ");
        String consultation = clinicaValidator.stringClinicaValidator(Utils.getReader().nextLine());
        System.out.print("Diagnostico: ");
        String diagnostic = clinicaValidator.stringClinicaValidator(Utils.getReader().nextLine());
        System.out.print("Sintomatología: ");
        String symptoms = clinicaValidator.stringClinicaValidator(Utils.getReader().nextLine());
        System.out.print("Tratamiento: ");
        String procedure = clinicaValidator.stringClinicaValidator(Utils.getReader().nextLine());
        System.out.print("Id de la Order: ");
        long orderId = orderValidator.orderIdValidator(Utils.getReader().nextLine());
        Order order = new Order();
        order.setOrderId(orderId);
        order = adminService.medicalReportPort.getOrderByOrderId(adminService, orderId);
        String vacumHistory = validVaccinationHistory(order.getPet());
        System.out.print("Allergia a medicamentos: ");
        String allergies = clinicaValidator.stringClinicaValidator(Utils.getReader().nextLine());
        System.out.print("Detalles del tratamiento: ");
        String procedureDetails = clinicaValidator.stringClinicaValidator(Utils.getReader().nextLine());
        
        MedicalReport medicalReport = new MedicalReport();
        medicalReport.setConsultation(consultation);
        medicalReport.setDiagnostic(diagnostic);
        medicalReport.setSymptoms(symptoms);
        medicalReport.setDetailProcedure(procedure);
        medicalReport.setOrder(order);
        medicalReport.setVaccinationHistory(vacumHistory);
        medicalReport.setAllergies(allergies);
        medicalReport.setDetailProcedure(procedureDetails);
        adminService.registerMedicalReport(medicalReport);
        System.out.println("Historia Clinica Creada");
        System.out.println(toStringClinica(medicalReport));
    }

    private void getOrder()throws Exception{
        try {
            Order order = new Order();
            System.out.print("Ingrese el id de la order: ");
            long orderId = orderValidator.orderIdValidator(Utils.getReader().nextLine());
            order = adminService.medicalReportPort.getOrderByOrderId(adminService, orderId);
            System.out.println(toStringOrder(order));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void getClinicalRecord() throws Exception{
        try {
            MedicalReport medicalReport = new MedicalReport();
            System.out.print("Ingrese el id de la Historia Clinica: ");
            long clinicalRecordId = clinicaValidator.clinicaIdValidator(Utils.getReader().nextLine());
            medicalReport = adminService.getClinicalRecordByMedicalReportId(clinicalRecordId);
            System.out.println(toStringClinica(medicalReport));
            }
         catch (Exception e) {
            System.out.println(e.getMessage());
        }
    } 
    
    private String toStringOrder(Order order){
        return "\nID Order: "+order.getOrderId()+"\nId Mascota: "+order.getPet().getPetId()+"\nCedula Dueño: "+order.getOwner().getPersonDocument()
                                    +"\nCedula Veterinario: "+order.getVeterinarian().getPersonDocument()+"\nNombre medigamento: "+order.getMedicationName()
                                    +"\nDosis: "+order.getMedicationDosis()+"\nFecha: "+order.getDate()+"\nEstado: "+order.getOrderStatus();
    }

    private String toStringClinica(MedicalReport medicalReport){
        return "\nID Historia Clinica: "+medicalReport.getMedicalReportId()+"\nFecha: "+medicalReport.getDate()+"\nMotivo de consulta: "+medicalReport.getConsultation()
                                    +"\nDiagnostico: "+medicalReport.getDiagnostic()+"\nSintomatología: "+medicalReport.getSymptoms()+"\nTratamiento: "+medicalReport.getprocedure()
                                    +"\nId Order: "+medicalReport.getOrder().getOrderId()+"\nId Mascota: "+medicalReport.getOrder().getPet().getPetId()+"\nCedula Dueño: "+medicalReport.getOrder().getOwner().getPersonDocument()
                                    +"\nCedula Veterinario: "+medicalReport.getOrder().getVeterinarian().getPersonDocument()+"\nNombre medicamento: "+medicalReport.getOrder().getMedicationName()+"\nDosis: "+medicalReport.getOrder().getMedicationDosis()
                                    +"\nHistorial de vacunas: "+medicalReport.getVaccinationHistory()+"\nAlergia a medicamentos: "+medicalReport.getAllergies()
                                    +"\nDetalles del tratamiento: "+medicalReport.getDetailProcedure()+"\n";
    }

    private String toStringPet(Pet pet){
        return "\nDocumento del dueño: "+pet.getPersonId().getPersonDocument()+"\nNombre del dueño: "+pet.getPersonId().getPersonName()+"\nID Mascota: "+pet.getPetId()+"\nNombre: "+pet.getPetName()+"\nEdad: "+pet.getPetAge()+"\nEspecie: "+pet.getPetSpecies()+"\nRaza: "+pet.getPetRace()+"\nPeso(Kg): "+pet.getPetWeight();
    }
    private String toStringPerson(Person person){
        return "\nNombre: "+person.getPersonName()+"\nDocumento: "+person.getPersonDocument()+"\nEdad: "+person.getPersonAge();
    }

    private String validPet() throws Exception {
        String option = "";
        boolean validOption = false;  
    
        while (!validOption) {  
            try {
                System.out.println("1. Perro 2. Gato 3. Otro");
                option = Utils.getReader().nextLine(); 
                
                switch (option) {
                    case "1":
                        validOption = true; 
                        return "Perro";
                    case "2":
                        validOption = true;
                        return "Gato";
                    case "3":
                        validOption = true; 
                        System.out.println("Ingrese la especie: ");
                        String otherPet = Utils.getReader().nextLine();
                        return otherPet;
                    default:
                        System.out.println("Opción inválida. Por favor, ingrese 1 para Perro, 2 para Gato, o 3 para Otro.");
                        break;  
                }
            } catch (Exception e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
                return e.getMessage();  
            }
        }
    
        return "";
    }

    private String validVaccinationHistory(Pet pet) throws Exception{
        if(pet.getPetSpecies().equalsIgnoreCase("Perro") || pet.getPetSpecies().equalsIgnoreCase("Gato")){
            System.out.print("Historial de vacunas: ");
            String vacum = Utils.getReader().nextLine();
            return vacum;
        }
        return "No aplica";
    }
}
