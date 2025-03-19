package com.ra.base_spring_boot.model;

import com.ra.base_spring_boot.model.base.BaseObject;
import com.ra.base_spring_boot.model.constants.RoleName;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "roles")
public class Role extends BaseObject {
    @Column(name = "role_name", length = 30, nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private RoleName roleName;
}
