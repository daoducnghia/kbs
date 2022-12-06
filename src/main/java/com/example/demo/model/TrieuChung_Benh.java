
package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TrieuChung_Benh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int count;
    
    @ManyToOne
    @JoinColumn(name="id_benh")
    private Benh benh;
    
    @ManyToOne
    @JoinColumn(name="id_trieuchung")
    private TrieuChung trieuchung;
    
    @ManyToOne
    @JoinColumn(name="id_dophobien")
    private DoPhoBien dophobien;
}
