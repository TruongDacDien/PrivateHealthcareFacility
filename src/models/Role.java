package models;

public class Role {
    private int roleId;
    private boolean role1;
    private boolean role2;
    private boolean role3;
    private boolean role4;
    private boolean role5;
    private boolean role6;
    private boolean role7;
    private String position;
    private int userId;

    public Role() {
        super();
    }
    
    public Role(int roleId, boolean role1, boolean role2, boolean role3, boolean role4, boolean role5, boolean role6, boolean role7, String position, int userId) {
        super();
        this.roleId = roleId;
        this.role1 = role1;
        this.role2 = role2;
        this.role3 = role3;
        this.role4 = role4;
        this.role5 = role5;
        this.role6 = role6;
        this.role7 = role7;
        this.position = position;
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public boolean isRole1() {
        return role1;
    }

    public void setRole1(boolean role1) {
        this.role1 = role1;
    }

    public boolean isRole2() {
        return role2;
    }

    public void setRole2(boolean role2) {
        this.role2 = role2;
    }

    public boolean isRole3() {
        return role3;
    }

    public void setRole3(boolean role3) {
        this.role3 = role3;
    }

    public boolean isRole4() {
        return role4;
    }

    public void setRole4(boolean role4) {
        this.role4 = role4;
    }

    public boolean isRole5() {
        return role5;
    }

    public void setRole5(boolean role5) {
        this.role5 = role5;
    }

    public boolean isRole6() {
        return role6;
    }

    public void setRole6(boolean role6) {
        this.role6 = role6;
    }

    public boolean isRole7() {
        return role7;
    }

    public void setRole7(boolean role7) {
        this.role7 = role7;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Role [roleId=").append(roleId).append(", role1=").append(role1).append(", role2=").append(role2)
                .append(", role3=").append(role3).append(", role4=").append(role4).append(", role5=").append(role5)
                .append(", role6=").append(role6).append(", role7=").append(role7).append(", position=").append(position)
                .append(", userId=").append(userId).append("]");
        return builder.toString();
    }
}
