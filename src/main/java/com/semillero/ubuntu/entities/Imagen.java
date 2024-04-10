package com.semillero.ubuntu.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.Data;


@Entity @Data
public class Imagen {
        @Id
        @GeneratedValue
        private Long id;
     //   private String nombre;
        private String cloudinaryUrl;
        private Boolean dadaDeAlta;
        @Max(value = 3000000)
        private Long size;
        public void setCloudinaryUrl(String cloudinaryUrl) {
            this.cloudinaryUrl = cloudinaryUrl;
        }
}

