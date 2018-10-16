package companyStructure;

import java.util.ArrayList;

public class TechnicalLead extends TechnicalEmployee {
    int headCount;
    private ArrayList<SoftwareEngineer> directReports;
    private Accountant supportingAccountant;

    public TechnicalLead(String name) {
        super(name);
        this.isManager = true;
        this.headCount = 4;
        this.baseSalary = 1.3 * TechnicalLead.BASE_SALARY;
        this.directReports = new ArrayList<>();
    }

    public boolean hasHeadCount() {
        return this.headCount > this.getDirectReports().size();
    }

    public boolean addReport(SoftwareEngineer e) {// Recheck
        if (this.hasHeadCount()) {
            this.getDirectReports().add(e);
            return true;
        } else {
            return false;
        }
    }

    public boolean acceptCheckIn(SoftwareEngineer e) {
        return this.getDirectReports().contains(e) && e.getCodeAccess();
    }

    public boolean requestBonus(Employee e, double bonus) {
        return this.supportingAccountant.
                getSupportingBusinessLead()
                .approveBonus(e, bonus);
    }

    public String getTeamStatus() {
        if (this.getDirectReports().size() == 0) {
            return this.employeeStatus() + " and has no direct reports yet.";
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.employeeStatus());
            this.getDirectReports().forEach(engineer -> stringBuilder.append(engineer.employeeStatus()));
            return stringBuilder.toString();
        }
    }

    public ArrayList<SoftwareEngineer> getDirectReports() {
        return directReports;
    }
}
