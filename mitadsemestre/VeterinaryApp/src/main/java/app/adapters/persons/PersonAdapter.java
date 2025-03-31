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

    public boolean existsPerson(long personDocument){
        return personRepository.existsByPersonDocument(personDocument);
    }

    public void savePerson(Person person){
        PersonEntity personEntity = personAdapter(person);        
        personRepository.save(personEntity);
        person.setPersonDocument(personEntity.getPersonDocument());
        System.out.println("Cliente Creado.");
    }
    
    public Person findByPersonDocument(long personDocument)throws Exception{
        PersonEntity personEntity = personRepository.findByPersonDocument(personDocument);
        if(personEntity==null){
            throw new Exception("No existe una persona con ese documento");
        }
        return personAdapter(personEntity);
    }

    
    private Person personAdapter(PersonEntity personEntity){
        Person person = new Person();
        person.setPersonName(personEntity.getPersonName());
        person.setPersonDocument(personEntity.getPersonDocument());
        person.setPersonAge(personEntity.getPersonAge());
        return person;
    }

    private PersonEntity personAdapter(Person person){
        PersonEntity personEntity = new PersonEntity();
        personEntity.setPersonName(person.getPersonName());
        personEntity.setPersonDocument(person.getPersonDocument());
        personEntity.setPersonAge(person.getPersonAge());
        return personEntity;
    }

    @Override
    public boolean existPerson(long personDocument) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existPerson'");
    }
    
}
