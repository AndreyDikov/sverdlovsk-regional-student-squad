package ru.library.entitiesmodule.entities.event;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(name = "events_weights")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventWeightEntity {

    @Id
    @UuidGenerator
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    UUID uuid;

    @Column(name = "category_evaluation_method_uuid")
    UUID categoryEvaluationMethodUuid;

    String name;
    Double weight;
}
