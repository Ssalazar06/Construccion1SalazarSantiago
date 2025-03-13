package app.ports;

import app.domain.models.person;

public interface personPort {
    
    public boolean existPerson(long personId);
    public void savePerson(person person);
    public person findByPersonId(long personId);
    
}
