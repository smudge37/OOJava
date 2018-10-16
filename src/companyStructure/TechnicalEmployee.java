package companyStructure;

public class TechnicalEmployee extends Employee {
    static int BASE_SALARY = 75000; // Necessary?
    int successfulCheckins;

    public TechnicalEmployee(String name) {
        super(name, 75000);
    }

    public String employeeStatus() {
        return this.toString() + " has " + this.successfulCheckins + " successful check-ins.";
    }
}
