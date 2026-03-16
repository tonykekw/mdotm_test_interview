package MDOTM.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;

@Getter
@AllArgsConstructor
@Document(collection = "pets")
public class Pet {

    @Id
    private String id;

    @NonNull
    private String name;

    @NonNull
    private String species;


    private  Integer age;

    @Field("pet_name")
    private String ownerName;


    public Pet(){};

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setSpecies(@NonNull String species) {
        this.species = species;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", species='" + species + '\'' +
                ", age=" + age +
                ", ownerName='" + ownerName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(id, pet.id) && Objects.equals(name, pet.name) && Objects.equals(species, pet.species) && Objects.equals(age, pet.age) && Objects.equals(ownerName, pet.ownerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, species, age, ownerName);
    }
}
