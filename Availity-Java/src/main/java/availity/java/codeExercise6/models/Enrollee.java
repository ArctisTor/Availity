package availity.java.codeExercise6.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
public class Enrollee implements Comparable<Enrollee> {


    private String id;
    private String firstName;
    private String lastName;
    private Integer version;
    private String insuranceCompany;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enrollee enrollee = (Enrollee) o;
        return id.equals(enrollee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id + "," + firstName + "," + lastName + "," + version + "," + insuranceCompany;
    }


    public String[] toCSVFormat() {
        return new String[]{id, firstName, lastName, Integer.toString(version), insuranceCompany};
    }

    @Override
    public int compareTo(Enrollee o) {
        if (lastName == null || o.lastName == null) {
            return 0;
        } else if (lastName.compareTo(o.lastName) == 0) {
            //same last name
            if (firstName.compareTo(o.firstName) == 0) {
                //same first name
                return version.compareTo(o.version);
            } else {
                return firstName.compareTo(o.firstName);
            }
        } else {
            return lastName.compareTo(o.lastName);
        }
    }
}
