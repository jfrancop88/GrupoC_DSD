package org.upc.project.examplerest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "ecommerce")
public class    Ecommerce {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ecommerce_id")
    private Long id;  //Ripley

    @Column(name = "ecommerce_code")
    private String ecommerceCode;

    @Column(name = "name")
    private String name; // Ripley SAC
    
    @Column(name = "enabled")
    private boolean enabled; //true
}
