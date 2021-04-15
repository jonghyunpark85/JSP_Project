/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sait.itsd;

/**
 *
 * @author john
 */
public class Note {
    String note="";
    String dateTime="";
    
    public Note() {
        
    }
    
    public Note(String note, String dateTime) {
        this.note=note;
        this.dateTime=dateTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dataTime) {
        this.dateTime = dataTime;
    }
    
    
}
