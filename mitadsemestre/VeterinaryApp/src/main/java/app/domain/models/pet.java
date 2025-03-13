package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class pet {

    private String petName;
    private long ownerId;
    private long petAge;
    private long petId;
    private String species;
    private String race;
    private String characteristics;
    private long weigth;
    
    public pet(String petName, long ownerId, long petAge, long petId, String species, String race,
            String characteristics, long weigth) {
        this.petName = petName;
        this.ownerId = ownerId;
        this.petAge = petAge;
        this.petId = petId;
        this.species = species;
        this.race = race;
        this.characteristics = characteristics;
        this.weigth = weigth;
    }
}
