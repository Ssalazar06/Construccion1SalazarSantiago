package app.adapters.invoice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.adapters.invoice.entity.InvoiceEntity;
import app.adapters.invoice.repository.InvoiceRepository;
import app.adapters.order.entity.OrderEntity;
import app.adapters.persons.entity.PersonEntity;
import app.adapters.pet.entity.PetEntity;
import app.adapters.users.entity.UserEntity;
import app.domain.models.Invoice;
import app.domain.models.Order;
import app.domain.models.Person;
import app.domain.models.Pet;
import app.domain.models.User;
import app.ports.InvoicePort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Service
public class InvoiceAdapter implements InvoicePort{
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public void saveInvoice(Invoice invoice) {
        InvoiceEntity invoiceEntity = invoiceAdapter(invoice);
        invoiceRepository.save(invoiceEntity);
        invoice.setInvoiceId(invoiceEntity.getInvoiceId());
        invoice.setDate(invoiceEntity.getDate());
    }

    @Override
    public Invoice findByInvoiceId(long invoiceId) throws Exception{
        InvoiceEntity invoiceEntity = invoiceRepository.findByInvoiceId(invoiceId);
        if(invoiceEntity == null){
            throw new Exception("No existe una fatura con ese Id");
        }
        return invoiceAdapter(invoiceEntity);
    }

    private InvoiceEntity invoiceAdapter(Invoice invoice){
        InvoiceEntity invoiceEntity = new InvoiceEntity();
        invoiceEntity.setAmount(invoice.getAmount());
        invoiceEntity.setOrder(orderAdapter(invoice.getOrder()));
        invoiceEntity.setInvoiceId(invoice.getInvoiceId());
        invoiceEntity.setItem(invoice.getItem());
        invoiceEntity.setAmount(invoice.getAmount());
        invoiceEntity.setMedicationQuantity(invoice.getMedicationQuantity());
        invoiceEntity.setDate(invoice.getDate());    
        return invoiceEntity;
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
        personEntity.setPersonName(person.getPersonName());
        personEntity.setPersonDocument(person.getPersonDocument());
        person.setPersonAge(person.getPersonAge());
        return personEntity;
    }

    private OrderEntity orderAdapter(Order order){
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setPerson(personAdapter(order.getOwner()));
        orderEntity.setPet(petAdapter(order.getPet()));
        orderEntity.setUser(userAdapter(order.getVeterinarian()));
        orderEntity.setOrderId(order.getOrderId());
        orderEntity.setMedicationName(order.getMedicationName());
        orderEntity.setMedicationDosis(order.getMedicationDosis());
        orderEntity.setDate(order.getDate());
        orderEntity.setOrderStatus(order.getOrderStatus());
        return orderEntity;
    }

    private Pet petAdapter(PetEntity petEntity) {
        Pet pet = new Pet();
        pet.setPetName(petEntity.getPetName());
        pet.setPetAge(petEntity.getPetAge());
        pet.setPetSpecies(petEntity.getPetSpecies());
        pet.setPetRace(petEntity.getPetRace());
        pet.setPetWeight(petEntity.getPetWeight());
        return pet;
    }

    private Person personAdapter(PersonEntity personEntity){
        Person person = new Person();
        person.setPersonName(personEntity.getPersonName());
        person.setPersonDocument(personEntity.getPersonDocument());
        person.setPersonAge(personEntity.getPersonAge());
        return person;
    }

    private Order orderAdapter(OrderEntity orderEntity){
        Order order = new Order();
        order.setOrderId(orderEntity.getOrderId());
        order.setOwner(personAdapter(orderEntity.getPerson()));
        order.setPet(petAdapter(orderEntity.getPet()));
        order.setVeterinarian(userAdapter(orderEntity.getUser()));
        order.setMedicationName(orderEntity.getMedicationName());
        order.setMedicationDosis(orderEntity.getMedicationDosis());
        order.setDate(orderEntity.getDate());
        order.setOrderStatus(orderEntity.getOrderStatus());
        return order;
    }



    private Invoice invoiceAdapter(InvoiceEntity invoiceEntity){
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(invoiceEntity.getInvoiceId());
        invoice.setOrder(orderAdapter(invoiceEntity.getOrder()));
        invoice.setItem(invoiceEntity.getItem());
        invoice.setAmount(invoiceEntity.getAmount());
        invoice.setMedicationQuantity(invoiceEntity.getMedicationQuantity());
        invoice.setDate(invoiceEntity.getDate());
        return invoice;
    }
    

    private User userAdapter(UserEntity userEntity){
        User user = new User();
        user.setUserId(userEntity.getUserId());
        user.setPersonName(userEntity.getPerson().getPersonName());
        user.setPersonDocument(userEntity.getPerson().getPersonDocument());
        user.setPersonAge(userEntity.getPerson().getPersonAge());
        user.setUserName(userEntity.getUserName());
        return user;
    }

    private UserEntity userAdapter(User user){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(user.getUserId());
        userEntity.setPerson(personAdapter(user));
        return userEntity;
    }

    @Override
    public List<Invoice> getAllInvoice() throws Exception {
        List<InvoiceEntity> invoiceEntityList = invoiceRepository.findAll();
        if(invoiceEntityList.isEmpty()){
            throw new Exception("No hay facturas");
        }
        return invoiceAdapterList(invoiceEntityList);
    }

    public List<Invoice> invoiceAdapterList(List<InvoiceEntity> invoiceEntityList)throws Exception{
        List<Invoice> invoiceList = new ArrayList<>();
        for(InvoiceEntity invoiceEntity : invoiceEntityList){
            invoiceList.add(invoiceAdapter(invoiceEntity));
        }
        return invoiceList;
    }
}
