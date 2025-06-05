package ru.library.entitiesmodule.entities.event;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UuidGenerator;
import ru.library.entitiesmodule.entities.event.enums.EvaluationMethod;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "categories_evaluation_methods")
public class CategoryEvaluationMethodEntity {

    @Id
    @UuidGenerator
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    UUID uuid;

    @Column(name = "category_uuid")
    UUID categoryUuid;

    @Enumerated(EnumType.STRING)
    @Column(name = "evaluation_method")
    EvaluationMethod evaluationMethod;
}
