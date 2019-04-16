package com.example.listtraining.model;

public class Anime {
    // déclarer les variables qui correspondent aux objets souhaités parsés

    private String Title;
    private String image;
    private String LongTitle;
    private String PrincipalMaker;


    public Anime(){

    }

    public Anime(String title, String image, String longTitle, String principalMaker) {
        Title = title;
        this.image = image;
        LongTitle = longTitle;
        PrincipalMaker = principalMaker;
    }


    public void setTitle(String title) {
        Title = title;
    }

    public void setPrincipalMaker(String principalMaker) {
        PrincipalMaker = principalMaker;
    }

    public String getPrincipalMaker() {
        return PrincipalMaker;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setLongTitle(String longTitle) {
        LongTitle = longTitle;
    }

    public String getTitle() {
        return Title;
    }

    public String getImage() {
        return image;
    }

    public String getLongTitle() {
        return LongTitle;
    }
}
