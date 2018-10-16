
package companyStructure;


public class Accountant extends BusinessEmployee {
    private TechnicalLead teamSupported;
    private BusinessLead supportingBusinessLead;

    public Accountant(String name) {
        super(name);
        this.bonusBudget = 0;
        teamSupported = null;
    }

    public TechnicalLead getTeamSupported() {
        return teamSupported;
    }

    public void supportTeam(TechnicalLead leader) {
        this.teamSupported = leader;
        this.bonusBudget = 0;
        leader.getDirectReports().forEach( engineer -> this.bonusBudget = this.bonusBudget + (engineer.getBaseSalary() * 1.1));
    }

    public boolean approveBonus(double bonus) {
        return this.teamSupported!=null && this.bonusBudget >= bonus;
    }

    public void giveBonus(Employee e, double bonus) {
        if (this.teamSupported.getDirectReports().contains((SoftwareEngineer) e)) {
            this.bonusBudget = this.bonusBudget - bonus;
            e.rewardBonus(bonus);
        }
    }


    public String employeeStatus() {
        if (teamSupported != null) {
            return this.toString() + " with a budget of " + this.bonusBudget + " is supporting " + this.teamSupported;
        } else {
            return super.employeeStatus();
        }
    }

    public BusinessLead getSupportingBusinessLead() {
        return supportingBusinessLead;
    }
}
