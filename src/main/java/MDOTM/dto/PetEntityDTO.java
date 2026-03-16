package MDOTM.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class PetEntityDTO {


    private String id;
    private String name;
    private String ownerName;
    private Integer age;
    private String species;

    public PetEntityDTO(){}

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PetEntityDTO that = (PetEntityDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(ownerName, that.ownerName) && Objects.equals(age, that.age) && Objects.equals(species, that.species);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, ownerName, age, species);
    }

    @Override
    public String toString() {
        return "PetEntityDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", age=" + age +
                ", species='" + species + '\'' +
                '}';
    }
}
