/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.ittepic.aeu2e01.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jorge Arellano
 */
@Entity
@Table(name = "company")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Company.findAll", query = "SELECT c FROM Company c")
    , @NamedQuery(name = "Company.findByIdcompany", query = "SELECT c FROM Company c WHERE c.idcompany = :idcompany")
    , @NamedQuery(name = "Company.findByName", query = "SELECT c FROM Company c WHERE c.name = :name")
    , @NamedQuery(name = "Company.findByNeighborhood", query = "SELECT c FROM Company c WHERE c.neighborhood = :neighborhood")
    , @NamedQuery(name = "Company.findByZipcode", query = "SELECT c FROM Company c WHERE c.zipcode = :zipcode")
    , @NamedQuery(name = "Company.findByCity", query = "SELECT c FROM Company c WHERE c.city = :city")
    , @NamedQuery(name = "Company.findByCountry", query = "SELECT c FROM Company c WHERE c.country = :country")
    , @NamedQuery(name = "Company.findByState", query = "SELECT c FROM Company c WHERE c.state = :state")
    , @NamedQuery(name = "Company.findByRegion", query = "SELECT c FROM Company c WHERE c.region = :region")
    , @NamedQuery(name = "Company.findByStreetnumber", query = "SELECT c FROM Company c WHERE c.streetnumber = :streetnumber")
    , @NamedQuery(name = "Company.findByRfc", query = "SELECT c FROM Company c WHERE c.rfc = :rfc")
    , @NamedQuery(name = "Company.findByLogo", query = "SELECT c FROM Company c WHERE c.logo = :logo")
    , @NamedQuery(name = "Company.findByPhone", query = "SELECT c FROM Company c WHERE c.phone = :phone")})
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcompany")
    private Integer idcompany;
    @Size(max = 50)
    @Column(name = "name")
    private String name;
    @Size(max = 50)
    @Column(name = "neighborhood")
    private String neighborhood;
    @Size(max = 10)
    @Column(name = "zipcode")
    private String zipcode;
    @Size(max = 50)
    @Column(name = "city")
    private String city;
    @Size(max = 50)
    @Column(name = "country")
    private String country;
    @Size(max = 50)
    @Column(name = "state")
    private String state;
    @Size(max = 50)
    @Column(name = "region")
    private String region;
    @Size(max = 6)
    @Column(name = "streetnumber")
    private String streetnumber;
    @Size(max = 13)
    @Column(name = "rfc")
    private String rfc;
    @Size(max = 255)
    @Column(name = "logo")
    private String logo;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 15)
    @Column(name = "phone")
    private String phone;

    public Company() {
    }

    public Company(Integer idcompany) {
        this.idcompany = idcompany;
    }

    public Integer getIdcompany() {
        return idcompany;
    }

    public void setIdcompany(Integer idcompany) {
        this.idcompany = idcompany;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStreetnumber() {
        return streetnumber;
    }

    public void setStreetnumber(String streetnumber) {
        this.streetnumber = streetnumber;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcompany != null ? idcompany.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Company)) {
            return false;
        }
        Company other = (Company) object;
        if ((this.idcompany == null && other.idcompany != null) || (this.idcompany != null && !this.idcompany.equals(other.idcompany))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.edu.ittepic.aeu2e01.entities.Company[ idcompany=" + idcompany + " ]";
    }
    
}
