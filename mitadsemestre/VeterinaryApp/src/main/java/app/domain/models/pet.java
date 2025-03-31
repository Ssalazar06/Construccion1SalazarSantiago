package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class Pet {

    private String petName;
    private long personId;
    private long petAge;
    private long petId;
    private String petSpecies;
    private String petRace;
    private String petCharacteristics;
    private double petWeight;

    public Pet(String petName, long personId, long petAge, long petId, String petSpecies, String petRace,
            String petCharacteristics, long petWeight) {
        this.petName = petName;
        this.personId = personId;
        this.petAge = petAge;
        this.petId = petId;
        this.petSpecies = petSpecies;
        this.petRace = petRace;
        this.petCharacteristics = petCharacteristics;
        this.petWeight = petWeight;
    }

    public void setPersonDocument(Person person) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPersonDocument'");
    }
}
