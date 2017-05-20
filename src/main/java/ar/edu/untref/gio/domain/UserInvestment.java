package ar.edu.untref.gio.domain;


import javax.persistence.*;

@Entity
@Table(name = "user_investment")
public class UserInvestment {

    @Id
    @Column(name = "id", length = 11, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id = 0;

    @Column(name = "owner_id")
    private Integer ownerId;

    @Column(name = "investment_id")
    private Integer investmentId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private UserInvestmentStatus status;

    public UserInvestment(){}

    public UserInvestment(Integer ownerId, Integer investmentId, UserInvestmentStatus status) {
        this.ownerId = ownerId;
        this.investmentId = investmentId;
        this.status = status;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public Integer getInvestmentId() {
        return investmentId;
    }

    public UserInvestmentStatus getStatus() {
        return status;
    }

    public void finalize() {
        this.status = UserInvestmentStatus.FINALIZED;
    }

    public Integer getId() {
        return id;
    }
}
