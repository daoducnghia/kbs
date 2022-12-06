
package com.example.demo.model;

<<<<<<< HEAD
import java.io.Serializable;
=======
>>>>>>> 90993b3d59b2cd3b4256c21b0ac48ad375b018a0
import javax.persistence.Column;
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
public class CachDung  implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
<<<<<<< HEAD
    @Column(columnDefinition = "TEXT", name = "useT")
=======
    
    @Column(name = "use1")
>>>>>>> 90993b3d59b2cd3b4256c21b0ac48ad375b018a0
    private String use;
    
    @ManyToOne
    @JoinColumn(name="id_thuoc")
    private Thuoc thuoc;
}
