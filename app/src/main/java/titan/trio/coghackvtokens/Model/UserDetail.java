package titan.trio.coghackvtokens.Model;

public class UserDetail {
    String name,phone,gender,status,balance;

    public UserDetail() {
    }

    public UserDetail(String name, String phone, String gender, String status, String balance) {
        this.name = name;
        this.phone = phone;
        this.gender = gender;
        this.status = status;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
