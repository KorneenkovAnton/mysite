package entity;

import enums.Genre;

import java.util.Date;
import java.util.List;

public class Game {
    private int id;
    private String name;
    private int cost;
    private Date releaseDate;
    private String descriprtion;
    private SystemRequirements minimumSystemRequirements;
    private SystemRequirements normalSystemRequirements;
    private String developer;
    private List<Genre> genres;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescriprtion() {
        return descriprtion;
    }

    public void setDescriprtion(String descriprtion) {
        this.descriprtion = descriprtion;
    }

    public SystemRequirements getMinimumSystemRequirements() {
        return minimumSystemRequirements;
    }

    public void setMinimumSystemRequirements(SystemRequirements minimumSystemRequirements) {
        this.minimumSystemRequirements = minimumSystemRequirements;
    }

    public SystemRequirements getNormalSystemRequirements() {
        return normalSystemRequirements;
    }

    public void setNormalSystemRequirements(SystemRequirements normalSystemRequirements) {
        this.normalSystemRequirements = normalSystemRequirements;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public Game(int id, String name, int cost, Date releaseDate, String descriprtion,
                SystemRequirements minimumSystemRequirements, SystemRequirements normalSystemRequirements,
                String developer, List<Genre> genres) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.releaseDate = releaseDate;
        this.descriprtion = descriprtion;
        this.minimumSystemRequirements = minimumSystemRequirements;
        this.normalSystemRequirements = normalSystemRequirements;
        this.developer = developer;
        this.genres = genres;
    }

    public Game() {
    }
}
