package spring_boot.spring_boot.MongoDB.MongoDB_2;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Player {

    @Id
    private int id;

    @Field
    private String name;

@Field
    private String surname;

@Field
    private int age;

@Field
    private int nrPlayer;


@Field
    private String trainer;

    public Player() {
    }

    public Player(int id, String name, String surname, int age, int nrPlayer, String trainer) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.nrPlayer = nrPlayer;
        this.trainer = trainer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNrPlayer() {
        return nrPlayer;
    }

    public void setNrPlayer(int nrPlayer) {
        this.nrPlayer = nrPlayer;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }
}
