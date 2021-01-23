package com.example.paquito.Pokemon;

public class Pokemon {
    private int Pokedexnum;
    private String name;
    private int altura;
    private int peso;
    private int exp;
    private String[] nuev;
    private String url;
    private long id;

    public Pokemon(int pokedexnum, String name, int altura, int peso, int exp, String[] nuev, String url,long id) {
        Pokedexnum = pokedexnum;
        this.name = name;
        this.altura = altura;
        this.peso = peso;
        this.exp = exp;
        this.nuev = nuev;
        this.url = url;
        this.id=id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setPokedexnum(int pokedexnum) {
        Pokedexnum = pokedexnum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setNuev(String[] nuev) {
        this.nuev = nuev;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPokedexnum() {
        return Pokedexnum;
    }

    public String getName() {
        return name;
    }

    public int getAltura() {
        return altura;
    }

    public int getPeso() {
        return peso;
    }

    public int getExp() {
        return exp;
    }

    public String[] getNuev() {
        return nuev;
    }

    public String getUrl() {
        return url;
    }
}
