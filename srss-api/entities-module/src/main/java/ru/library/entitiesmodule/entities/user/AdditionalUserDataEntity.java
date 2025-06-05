package ru.library.entitiesmodule.entities.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UuidGenerator;
import ru.library.entitiesmodule.entities.user.enums.Gender;

import java.util.UUID;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(name = "additional_users_data")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdditionalUserDataEntity {

    @Id
    @UuidGenerator
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    UUID uuid;

    @Column(name="user_uuid", nullable = false, unique = true)
    UUID userUuid;

    @Column(name="squad_uuid")
    UUID squadUuid;

    @Enumerated(EnumType.STRING)
    Gender gender;

    //todo: добавить поле для аватара

    Double score;
}
