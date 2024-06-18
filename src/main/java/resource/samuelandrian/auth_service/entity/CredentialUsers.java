package resource.samuelandrian.auth_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users",schema = "credential")
public class CredentialUsers {
    @Id
    private String username;

    private String password;

    private String token;

    @Column(name="token_expired_at")
    private Date tokenExpiredAt;
}
