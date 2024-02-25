package org.myapps.pollutionfinder.user.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "userprofile")
@Getter
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_profile_id")
    private Long userProfileId;
    @Setter
    @NotBlank
    private String email;
    @Setter
    @NotBlank
    private String name;
    @Setter
    @NotBlank
    private String username;
    @Setter
    @NotBlank
    private String password;

}
