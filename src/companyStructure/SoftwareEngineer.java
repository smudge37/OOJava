package companyStructure;

public class SoftwareEngineer extends TechnicalEmployee {
    private boolean codeAccess;

    public SoftwareEngineer(String name) {
        super(name);
        this.successfulCheckins = 0;
    }

    public boolean getCodeAccess() {
        return codeAccess;
    }

    public void setCodeAccess(boolean codeAccess) {
        this.codeAccess = codeAccess;
    }

    public int getSuccessfulCheckins() {
        return successfulCheckins;
    }

    public boolean checkInCode() {
        if (this.manager instanceof TechnicalLead) {
            if (((TechnicalLead) this.manager).acceptCheckIn(this)) {
                this.successfulCheckins++;
                return true;
            }
        }
        return false;
    }
}
