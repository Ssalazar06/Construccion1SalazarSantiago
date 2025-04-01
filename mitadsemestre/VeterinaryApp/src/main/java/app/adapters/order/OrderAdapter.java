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
        System.out.println("Orden creada");
    }
    
    @Override
    public Order findByOrderId(long orderId) throws Exception {
        Order order = orderAdapter(orderRepository.findByOrderId(orderId));
        if(order == null) { throw new Exception("No existe una orden con este numero"); }
        return order;
    }
    
    private OrderEntity orderAdapter(Order order) {
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
        personEntity.setName(person.getName());
        personEntity.setDocument(person.getDocument());
        person.setAge(person.getAge());
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
        person.setName(personEntity.getName());
        person.setDocument(personEntity.getDocument());
        person.setAge(personEntity.getAge());
        return person;
    }

    @Override
    public List<Order> getAllOrder() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllOrder'");
    }

}
