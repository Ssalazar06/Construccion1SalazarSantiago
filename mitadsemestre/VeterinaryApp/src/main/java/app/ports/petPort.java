package app.ports;

import app.domain.models.pet;

public interface petPort {
    
    public boolean existPet(long petId);
    public void savePet(pet pet);
    public pet findByPetId(long petId);
    
}
