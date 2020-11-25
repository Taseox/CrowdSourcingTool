
package ch.hegarc.ig.business;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class DonationList {
    @JsonProperty("id")
    private int id;
    @JsonProperty("projet")
    private String projet;
    @JsonProperty("donateurs")
    private List<Donateur> donateurs = new ArrayList<>();
/*

    public DonateurList() {
    }

    public List<Donateur> getDonateurs() {
        return donateurs;
    }

    public static DonateurList newPopulatedDonateurs(){
        DonateurList donateurList= new DonateurList();
        //donateurList.getDonateurs().add(new Student(1, null, "Erich", 55, true, "M"));
        return donateurList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Donateur d: getDonateurs()) {
            sb.append(d);
            sb.append("\n");
        }
        
        return sb.toString();
    }
    */
}