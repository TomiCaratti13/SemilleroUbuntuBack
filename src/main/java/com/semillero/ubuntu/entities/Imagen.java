package com.semillero.ubuntu.entities;

import jakarta.persistence.*;
import lombok.Data;


@Entity @Data
public class Imagen {
        @Id
        @GeneratedValue
        private Long id;

        private String cloudinaryUrl;
        private Boolean dadaDeAlta;
        public void setCloudinaryUrl(String cloudinaryUrl) {
            this.cloudinaryUrl = cloudinaryUrl;
        }
}

