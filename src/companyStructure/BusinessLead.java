package companyStructure;

import java.util.ArrayList;

public class BusinessLead extends BusinessEmployee{
    int headCount;
    ArrayList<Accountant> directReports;

    public BusinessLead(String name) {
        super(name);
        this.isManager = true;
        this.baseSalary = 2 * BusinessEmployee.BASE_SALARY;
        this.headCount = 10;
        this.directReports = new ArrayList<>();
    }

    public boolean hasHeadCount() {
        return this.directReports.size() < this.headCount;
    }

    public boolean addReport(Accountant e, TechnicalLead supportTeam) {
        if (this.hasHeadCount()) {
            this.directReports.add(e);
            this.bonusBudget = this.bonusBudget + 1.1 * e.getBaseSalary();
            e.supportTeam(supportTeam);
            return true;
        } else {
            return false;
        }
    }

    public boolean requestBonus(Employee e, double bonus) {
        if (bonus <= this.bonusBudget) {
            this.bonusBudget = this.bonusBudget - bonus;
            e.rewardBonus(bonus);
            return true;
        } else {
            return false;
        }
    }

    public boolean approveBonus(Employee e, double bonus) {
        for (Accountant accountant: this.directReports) {
            if (accountant.getTeamSupported().equals(e.getManager())) {
                if (accountant.approveBonus(bonus)) {
                    accountant.giveBonus(e, bonus);
                    return true;
                }
            }
        }
        return false;
    }
}
