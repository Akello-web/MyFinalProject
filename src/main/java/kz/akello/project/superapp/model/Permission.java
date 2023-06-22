package kz.akello.project.superapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "t_permissions")
@Getter
@Setter
public class Permission extends BaseModel implements GrantedAuthority {
    @Column(name = "user_role")
    private String role;

    @Override
    public String getAuthority() {
        return this.role;
    }
}
