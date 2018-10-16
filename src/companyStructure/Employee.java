package companyStructure;

public abstract class Employee {
    static int NUMBER_OF_EMPLOYEES;
    String name;
    double baseSalary;
    int employeeID;
    boolean isManager = false;
    Employee manager;

    public Employee (String name, double baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
        this.employeeID = Employee.NUMBER_OF_EMPLOYEES = Employee.getNumberOfEmployees() + 1;
    }

    public static int getNumberOfEmployees() {
        return NUMBER_OF_EMPLOYEES;
    }

    public boolean equals(Employee other) {
        return this.getEmployeeID() == other.getEmployeeID();
    }

    @Override
    public String toString() {
        return this.getEmployeeID() + " " + this.getName();
    }

    public void rewardBonus(double bonus) {}

    public abstract String employeeStatus();

    // Getters
    public String getName() {
        return name;
    }
    public double getBaseSalary() {
        return baseSalary;
    }
    public int getEmployeeID() {
        return employeeID;
    }
    public Employee getManager() {
        return manager;
    }
}
