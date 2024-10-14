package com.nisum.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Schema(example = "Juan Rodriguez")
    private String name;

    @Column(nullable = false, unique = true)
    @Schema(example = "juan@rodriguez.cl")
    private String email;

    @Column(name = "password", nullable = false)
    @Schema(example = "a2asfGfdfdf4")
    private String password;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    private List<Phone> phones;

    //Se usa zona horaria Etc/GMT+4 para forzar horario correcto actual, se debe usar zona horaria America/Santiago pero debe estar bien configurada en el sistema operativo donde se ejecuta el servicio
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMM d, yyyy HH:mm:ss a", timezone = "Etc/GMT+4", locale = "ES")
    @Schema(hidden = true)
    private Date created = new Date();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMM d, yyyy HH:mm:ss a", timezone = "Etc/GMT+4", locale = "ES")
    @Schema(hidden = true)
    private Date modified = new Date();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMM d, yyyy HH:mm:ss a", timezone = "Etc/GMT+4", locale = "ES")
    @Schema(hidden = true)
    private Date lastLogin = new Date();

    @Schema(hidden = true)
    @Column(length = 500)
    private String token;

    @Schema(hidden = true)
    private Boolean isActive = true;

    public User(String name, String email, String password, List<Phone> phones) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phones = phones;
    }
}
