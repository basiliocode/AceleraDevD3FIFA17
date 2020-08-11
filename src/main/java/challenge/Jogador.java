package challenge;

public class Jogador {

    private String fullName,club, age, birthDate, nationality, wage, releaseClause;

    public Jogador(String fullName, String club, String age, String birthDate,
                   String nationality, String wage, String releaseClause ){
        this.fullName = fullName;
        this.club = club;
        this.age = age;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.wage = wage;
        this.releaseClause = releaseClause;
    }

    public String getFullName() {
        return fullName;
    }

    public String getClub() {
        return club;
    }

    public int getAge() {
        return Integer.parseInt(age);
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getNationality() {
        return nationality;
    }

    public String getWage() {
        return wage;
    }

    public Double getReleaseClause() {
        return !releaseClause.isEmpty() ? Double.parseDouble(releaseClause) : 0;
    }

    @Override
    public String toString() {
        return "Player{" +
                ", fullName='" + fullName + '\'' +
                ", club='" + club + '\'' +
                ", age='" + age + '\'' +
                ", birthDate='" + birthDate + '\'' +
                "nationality='" + nationality + '\'' +
                ", wage='" + wage + '\'' +
                ", releaseClause='" + releaseClause + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (this == null || getClass() != o.getClass()) return false;
        Jogador jogador = (Jogador) o;
        return fullName.equals(jogador.fullName) &&
                club.equals(jogador.club) &&
                age.equals(jogador.age) &&
                birthDate.equals(jogador.birthDate) &&
                wage.equals(jogador.wage) &&
                releaseClause.equals(jogador.releaseClause);
    }
}
