package app.adapters.persons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.adapters.persons.entity.PersonEntity;
import app.adapters.persons.repository.PersonRepository;
import app.domain.models.Person;
import app.ports.PersonPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Service
public class PersonAdapter implements PersonPort {
    @Autowired
    private PersonRepository personRepository;

    public boolean existsPerson(long document){
        return personRepository.existsByDocument(document);
    }

    public void savePerson(Person person){
        PersonEntity personEntity = personAdapter(person);        
        personRepository.save(personEntity);
        person.setDocument(personEntity.getDocument());
        System.out.println("Cliente Creado.");
    }
    
    public Person findByPersonDocument(long document)throws Exception{
        PersonEntity personEntity = personRepository.findByDocument(document);
        if(personEntity==null){
            throw new Exception("No existe una persona con ese documento");
        }
        return personAdapter(personEntity);
    }

    
    private Person personAdapter(PersonEntity personEntity){
        Person person = new Person();
        person.setName(personEntity.getName());
        person.setDocument(personEntity.getDocument());
        person.setAge(personEntity.getAge());
        return person;
    }

    private PersonEntity personAdapter(Person person){
        PersonEntity personEntity = new PersonEntity();
        personEntity.setName(person.getName());
        personEntity.setDocument(person.getDocument());
        personEntity.setAge(person.getAge());
        return personEntity;
    }

    @Override
    public boolean existPerson(long document) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existPerson'");
    }

    @Override
    public Person getPersonId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPersonId'");
    }
    
}
