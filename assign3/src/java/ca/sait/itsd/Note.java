/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sait.itsd;

import java.util.Date;

/**
 * Note.java
 * 
 * @author john, Jonghyun Park
 * @version Nov 22, 2020
 */
public class Note {

    String noteID;
    String fKusername;
    String note;
    String noteDateTime;

    public Note() {

    }

    public Note(String note) {
        this.note = note;
    }

    public Note(String noteID, String fKusername, String note, String noteDateTime) {
        this.noteID = noteID;
        this.fKusername = fKusername;
        this.note = note;
        this.noteDateTime = noteDateTime;
    }

    public String getNoteID() {
        return noteID;
    }

    public void setNoteID(String noteID) {
        this.noteID = noteID;
    }

    public String getfKusername() {
        return fKusername;
    }

    public void setfKusername(String fKusername) {
        this.fKusername = fKusername;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNoteDateTime() {
        return noteDateTime;
    }

    public void setNoteDateTime(String noteDateTime) {
        this.noteDateTime = noteDateTime;
    }
}
