package by.exadel.internship.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "form_status")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FormStatus {

    @Id
    @Column(name = "fm_st_id")
    private String name;
}
