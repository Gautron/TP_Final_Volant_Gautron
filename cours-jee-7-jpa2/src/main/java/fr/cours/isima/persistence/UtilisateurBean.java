package fr.cours.isima.persistence;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity(name = "Utilisateur")
@Table(name = "UTILISATEUR")
public class UtilisateurBean {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
	
	@NotNull(message = "Nom utilisateur obligatoire")
    @Column(name = "username", unique = true)
    @Size(min = 1, max = 15)
    private String username;
	
	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public String getUsername() {
    	return username;
    }
    
    public void setUsername(String username){
    	this.username = username;
    }
	
}
