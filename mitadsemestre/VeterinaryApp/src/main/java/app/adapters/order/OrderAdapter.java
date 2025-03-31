package app.adapters.order;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.adapters.order.entity.OrderEntity;
import app.adapters.order.repository.OrderRepository;
import app.adapters.persons.entity.PersonEntity;
import app.adapters.pet.entity.PetEntity;
import app.adapters.users.UserAdapter;
import app.adapters.users.entity.UserEntity;
import app.domain.models.Order;
import app.domain.models.Person;
import app.domain.models.Pet;
import app.domain.models.User;
import app.ports.OrderPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Service
public class OrderAdapter implements OrderPort{
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void saveOrder(Order order) {
        OrderEntity orderEntity = orderAdapter(order);
        orderRepository.save(orderEntity);
        order.setOrderId(orderEntity.getOrderId());
        order.setDate(orderEntity.getDate());
        System.out.println("Order Created");
    }
    
    @Override
    public Order findByOrderId(long orderId) throws Exception {
        Order order = orderAdapter(orderRepository.findByOrderId(orderId));
        if(order == null) { throw new Exception("No order exists with that ID"); }
        return order;
    }
    
    private OrderEntity orderAdapter(Order order) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(order.getOrderId());
        orderEntity.setPet(petAdapter(order.getPet()));
        orderEntity.setPerson(personAdapter(order.getOwner()));
        orderEntity.setUser(UserAdapter(order.getVeterinarian()));
        orderEntity.setMedicationName(order.getMedicationName());
        orderEntity.setMedicationDosis(order.getMedicationDosis());
        orderEntity.setDate(order.getDate());
        orderEntity.setOrderStatus(order.getOrderStatus());
        return orderEntity;
    }
    
    private Order orderAdapter(OrderEntity orderEntity) {
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
    
    private PetEntity petAdapter(Pet pet) {
        PetEntity petEntity = new PetEntity();
        petEntity.setPetId(pet.getPetId());
        petEntity.setPetName(pet.getPetName());
        petEntity.setPetAge(pet.getPetAge());
        petEntity.setPetSpecies(pet.getPetSpecies());
        petEntity.setPetRace(pet.getPetRace());
        petEntity.setPetWeight(pet.getPetWeight());
        return petEntity;
    }
    
    private PersonEntity personAdapter(Person person) {
        PersonEntity personEntity = new PersonEntity();
        personEntity.setPersonName(person.getPersonName());
        personEntity.setPersonDocument(person.getPersonDocument());
        person.setPersonAge(person.getPersonAge());
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
    
    private Person personAdapter(PersonEntity personEntity) {
        Person person = new Person();
        person.setPersonName(personEntity.getPersonName());
        person.setPersonDocument(personEntity.getPersonDocument());
        person.setPersonAge(personEntity.getPersonAge());
        return person;
    }

    @Override
    public List<Order> getAllOrder() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllOrder'");
    }

}
