public class Citizen {
    
    String name;
    String IC;
    String state;
    int age;
    String category;
    String firstDoseStat;
    String secondDoseStat;
    String vaccineCompCert;

    //normal constructor
    public Citizen(String name, String IC, String state, int age, String category, String firstDoseStat,
            String secondDoseStat, String vaccineCompCert) {
        this.name = name;
        this.IC = IC;
        this.state = state;
        this.age = age;
        this.category = category;
        this.firstDoseStat = firstDoseStat;
        this.secondDoseStat = secondDoseStat;
        this.vaccineCompCert = vaccineCompCert;
    }

    //accessor
    public String getName() {
        return name;
    }

    public String getIC() {
        return IC;
    }

    public String getState() {
        return state;
    }

    public int getAge() {
        return age;
    }

    public String getCategory() {
        return category;
    }

    public String getFirstDoseStat() {
        return firstDoseStat;
    }

    public String getSecondDoseStat() {
        return secondDoseStat;
    }

    public String getVaccineCompCert() {
        return vaccineCompCert;
    }

    //mutator
    public void setName(String name) {
        this.name = name;
    }

    public void setIC(String iC) {
        IC = iC;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setFirstDoseStat(String firstDoseStat) {
        this.firstDoseStat = firstDoseStat;
    }

    public void setSecondDoseStat(String secondDoseStat) {
        this.secondDoseStat = secondDoseStat;
    }

    public void setVaccineCompCert(String vaccineCompCert) {
        this.vaccineCompCert = vaccineCompCert;
    }

    //display with toString
    public String toString() {
        return String.format("\t| %30s | %18s | %23s | %5s | %10s | %15s | %15s | %19s | %n " ,name, IC, state , age , category , firstDoseStat , secondDoseStat , vaccineCompCert);
    }

}