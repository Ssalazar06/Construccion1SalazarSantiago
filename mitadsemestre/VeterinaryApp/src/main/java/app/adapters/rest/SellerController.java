package app.adapters.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import app.Exceptions.BusinessException;
import app.Exceptions.InputException;
import app.adapters.rest.request.InvoiceRequest;
import app.adapters.rest.resposive.InvoiceRest;
import app.adapters.rest.resposive.OrderRest;
import app.adapters.rest.resposive.PersonRest;
import app.adapters.rest.resposive.PetRest;
import app.domain.models.Invoice;
import app.domain.models.Order;
import app.domain.models.Person;
import app.domain.models.Pet;
import app.domain.models.User;
import app.domain.services.SellerService;
import app.ports.OrderPort;

@RestController
public class SellerController {

    @Autowired
    private OrderPort orderPort;

    @Autowired
    private SellerService sellerService;
    
    @PostMapping("/invoice")
    public ResponseEntity saveInvoice(@RequestBody InvoiceRequest request){
        try {
            System.out.println(request.toString());
            long orderId = request.getOrderId();
            System.out.println(orderId);
            Order order = orderPort.findByOrderId(orderId);
            if(order == null){
                throw new BusinessException("No existe una order con ese ID");
            }
            Invoice invoice = new Invoice();
            invoice.setOrder(order);
            invoice.setAmount(request.getAmount());
            invoice.setMedicationDosis(request.getMedicationDosis());

            if(request.getItem() != null){
                invoice.setItem(null);}(request.getItem());
            }else{
                invoice.setItem("Sin productos adicionales");
            } 
            if(request.getAmount() ==0 || request.getMedicationDosis() ==0){
                throw new InputException("Datos Invalidos");
            }

            sellerService.saveInvoice(invoice);
            return new ResponseEntity("Factura Creada", HttpStatus.OK);
        }catch (BusinessException be){
            return new ResponseEntity(be.getMessage(),HttpStatus.CONFLICT);
        }catch(InputException ie){
            return new ResponseEntity(ie.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/invoice/{invoiceId}")
    public ResponseEntity getInvoice(@PathVariable long invoiceId){
        try {
            Invoice invoice = sellerService.getInvoiceByInvoiceId(invoiceId);
            InvoiceRest invoiceRest = invoiceRestAdapter(invoice);
            return new ResponseEntity(invoiceRest,HttpStatus.OK);
        }catch(BusinessException be){
            return new ResponseEntity(be.getMessage(),HttpStatus.CONFLICT);
        }catch(InputException ie){
            return new ResponseEntity(ie.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/GetAllInvoice")
    public ResponseEntity getInvoice(){
        try {
            List<Invoice> invoice = sellerService.getAllInvoice();
            List<InvoiceRest> invoiceRest = invoiceAdapterList(invoice);
            return new ResponseEntity(invoiceRest,HttpStatus.OK);
        }catch(BusinessException be){
            return new ResponseEntity(be.getMessage(),HttpStatus.CONFLICT);
        }catch(InputException ie){
            return new ResponseEntity(ie.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private InvoiceRest invoiceRestAdapter(Invoice invoice){
        InvoiceRest invoiceRest = new InvoiceRest();
        invoiceRest.setInvoiceId(invoice.getInvoiceId());
        invoiceRest.setOrder(orderRestAdapter(invoice.getOrder()));
        invoiceRest.setAmount(invoice.getAmount());
        invoiceRest.setMedicationDosis(invoice.getMedicationDosis());
        invoiceRest.setItem(invoice.getItem());
        invoiceRest.setDate(invoice.getDate());
        return invoiceRest;
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

    public List<InvoiceRest> invoiceAdapterList(List<Invoice> invoiceList)throws Exception{
        List<InvoiceRest> invoiceRestList = new ArrayList<>();
        for(Invoice invoice : invoiceList){
            invoiceRestList.add(invoiceRestAdapter(invoice));
        }
        return invoiceRestList;
    }
    
}
