package model;

public class Person {
    private static int nrOfPersons = 0;
    private int id;

    private String firstName;
    private String lastName;
    private int age;

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;

        nrOfPersons++;
        id = nrOfPersons;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Person id: "); sb.append(this.id);
        sb.append('\n');
        sb.append("First Name: "); sb.append(this.firstName);
        sb.append('\n');
        sb.append("Last Name: "); sb.append(this.lastName);
        sb.append('\n');
        sb.append("------");
        sb.append('\n');

        return sb.toString();
    }

    public String getFirstName() {
        return firstName;
    }

    public int getId() {
        return id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
