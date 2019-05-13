package se.jasmin.exjobb.trainapp.api.dto;


public class CreateNewUserDto {


    private String username;
    private String name;
    private String goals;


    public CreateNewUserDto(String username) {
        this.username = username;
    }

    public CreateNewUserDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    @Override
    public String toString() {
        return "CreateNewUserDto{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", goals='" + goals + '\'' +
                '}';
    }
}
