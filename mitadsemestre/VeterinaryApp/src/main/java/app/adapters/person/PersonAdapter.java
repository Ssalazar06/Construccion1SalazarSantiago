package app.adapters.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.Exceptions.BusinessException;
import app.adapters.person.entity.PersonEntity;
import app.adapters.person.repository.PersonRepository;
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
        return personRepository.existsByDocument(personDocument);
    }

    public void savePerson(Person person){
        PersonEntity personEntity = personAdapter(person);        
        personRepository.save(personEntity);
    }
    
    public Person findByDocument(long Document)throws Exception{
        PersonEntity personEntity = personRepository.findByDocument(Document);
        if(personEntity == null){
            throw new BusinessException("No existe el cliente con el documento: " + Document);
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
    public boolean existPerson(long personDocument) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existPerson'");
    }

    @Override
    public Person getPersonId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPersonId'");
    }

    @Override
    public Person findByPersonDocument(long personDocument) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByPersonDocument'");
    }
    
}
