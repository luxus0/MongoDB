package spring_boot.spring_boot.MongoDB.MongoDB_1;

import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Id;

@Document
public class CreditCard {

    @Id
    private Object id;

    private String name;

    private String nameCard;


    private String surname;


    private long pesel;


    private int age;
    private int numberCount;

    public CreditCard(){}
    public CreditCard(Object id, String name, String surname, String nameCard, long pesel, int age, int numberCount) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.nameCard = nameCard;
        this.pesel = pesel;
        this.age = age;
        this.numberCount = numberCount;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public long getPesel() {
        return pesel;
    }

    public void setPesel(long pesel) {
        this.pesel = pesel;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNumberCount() {
        return numberCount;
    }

    public void setNumberCount(int numberCount) {
        this.numberCount = numberCount;
    }

    public String getNameCard() {
        return nameCard;
    }

    public void setNameCard(String nameCard) {
        this.nameCard = nameCard;
    }
}
