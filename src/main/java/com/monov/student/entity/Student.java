package com.monov.student.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class Student {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "com.monov.commons.utils.StringUUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private String id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;

}
//    @GeneratedValue(generator = “UUID”)
//    @GenericGenerator(
//            name = “UUID”,
//            strategy = “org.hibernate.id.UUIDGenerator”,
//            parameters = {
//                    @Parameter(
//                            name = “uuid_gen_strategy_class”,
//                            value = “org.hibernate.id.uuid.CustomVersionOneStrategy”
//                    )
//            }
//    )
//    @Column(name = “id”, updatable = false, nullable = false)