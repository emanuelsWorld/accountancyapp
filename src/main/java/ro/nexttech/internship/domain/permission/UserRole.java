package ro.nexttech.internship.domain.permission;

public enum UserRole {
    EMPLOYEE("ADMIN"), MANAGER("MANAGER"), ADMIN("ADMIN"), GUEST("GUEST");
    private String label;

    UserRole(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
