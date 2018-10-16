package companyStructure;

public abstract class BusinessEmployee extends Employee {
    static int BASE_SALARY = 50000; // Necessary?
    double bonusBudget;

    public BusinessEmployee(String name) {
        super(name, 50000);
    }

    public String employeeStatus() {
        return this.toString() + " with a budget of " + this.bonusBudget;
    }

    public double getBonusBudget() {
        return this.bonusBudget;
    }
}
