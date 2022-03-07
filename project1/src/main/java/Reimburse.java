import jakarta.persistence.*;

@Entity
public class Reimburse {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String username;

    private Integer amount;

    private String status;


    public Reimburse(){}

    public Reimburse(int id, String username, String status) {
        this.id = id;
        this.username = username;
        this.status = status;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return username;
    }

    public void setName(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Reimburse{" +
                "id=" + id +
                ", name='" + username + '\'' +
                ", amount='"+amount+'\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
