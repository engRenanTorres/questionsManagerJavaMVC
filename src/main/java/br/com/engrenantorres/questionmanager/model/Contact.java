package br.com.engrenantorres.questionmanager.model;


import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class Contact implements Serializable {

    private String linkedin = "";
    private String twitter = "";
    private String facebook = "";
    private String officialSite = "";


    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getOfficialSite() {
        return officialSite;
    }

    public void setOfficialSite(String officialSite) {
        this.officialSite = officialSite;
    }
}
