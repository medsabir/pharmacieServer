package dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.annotations.Type;

import com.sun.xml.internal.txw2.annotation.XmlElement;

@Entity
@Table(name="PHARMACIE")
@XmlRootElement(name = "Pharmacie")
@XmlType(propOrder = {"id","nom","adresse","lat","log","num","garde"})
public class Pharmacie {
	
	@Id @GeneratedValue
	@Column (name="ID")
	private int id;
	
	@Column (name="NOM")
	private String nom;
	
	@Column (name="ADRESSE")
	private String adresse;
	
	@Column (name="LATITUDE")
	private Double lat;
	
	@Column (name="LONGITUDE")
	private Double log;
	
	@Column (name="NUM")
	private String num;
	
	@Column (name="GARDE")
	@Type (type="true_false")
	private Boolean garde;
	
	@Column (name="URL")
	private String url;
	
	@XmlElement
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@XmlElement
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	@XmlElement
	public String getAdresse() {
		return adresse;
	}
	
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	@XmlElement
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	@XmlElement
	public Double getLog() {
		return log;
	}
	public void setLog(Double log) {
		this.log = log;
	}

	@XmlElement
	public String getNum() {
		return num;
	}
	
	public void setNum(String num) {
		this.num = num;
	}
	@XmlElement
	public Boolean getGarde() {
		return garde;
	}
	
	public void setGarde(Boolean garde) {
		this.garde = garde;
	}
	
	public Pharmacie(String nom, String adresse, Double lat, Double log, String num, Boolean garde) {
		super();
		this.nom = nom;
		this.adresse = adresse;
		this.lat = lat;
		this.log = log;
		this.num = num;
		this.garde = garde;
	}
	public Pharmacie() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	

}