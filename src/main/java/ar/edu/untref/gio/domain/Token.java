package ar.edu.untref.gio.domain;

import javax.persistence.*;

@Entity
@Table(name = "token_user")
public class Token {

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "value")
    private String value;

    @Id
    @Column(name = "id", length = 11, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id = 0;

    public Token() {}

    public Token(Integer userId, String value) {
        this.userId = userId;
        this.value = value;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getValue() {
        return value;
    }

    public Integer getId() {
        return id;
    }
}
