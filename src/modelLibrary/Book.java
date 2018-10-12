package modelLibrary;


import modelLibrary.LibraryExceptions.DoubleReservationException;

import java.util.Date;

public class Book {
    private int price;
    private String title;
    private String author;
    private String genre;
    private long numberReads;
    private int dateReleased;
    private boolean isBorrowed;
    private Member holder;
    private boolean isReserved;
    private Member reserver;
    private Date dateDue;
    private int refNum;

    public Book(String title, String author, String genre, int refNum) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isBorrowed = false;
        this.refNum = refNum;
    }

    public void setProperty(String property, Object value) {
        property = property.toLowerCase();
        try {
            this.getClass().getField(property).set(this, value);
        } catch (NoSuchFieldException e) {
            System.out.println("No such property.");
        } catch (IllegalAccessException e) {
            System.out.println("Problem accessing property.");
        } catch (Exception e) {
            System.out.println("Action not possible. Sorry!");
        }
    }

    public Object getProperty(String property) {
        try {
            return this.getClass().getField(property).get(this);
        } catch (NoSuchFieldException e){
            System.out.println("No such property.");
            return null;
        } catch (IllegalAccessException e) {
            return null;
        }
    }

    public int getRefNum() {
        return this.refNum;
    }

    public Member getHolder() {
        return this.holder;
    }

    public Member getReserver() {
        return this.reserver;
    }

    public boolean isReserved() { return this.isReserved; }

    public void reserve(Member member) {
        if (this.isReserved) {
            throw new DoubleReservationException("This book is already reserved.");
        } else {
            this.isReserved = true;
            this.reserver = member;
        }
    }

    public void cancelReservation() {
        this.isReserved = false;
        this.reserver = null;
    }

    public boolean isBorrowed() {
        return this.isBorrowed;
    }

    public void borrow(Member member, Date date) {
        this.cancelReservation();
        this.isBorrowed = true;
        this.holder = member;
        this.dateDue = date;

    }

    public void renew(Date date) {
        this.dateDue = date;
    }

    public void replace() {
        this.isBorrowed = false;
        this.holder = null;
        // Notify reserver
    }


}
