package entity;



import java.util.Date;

public class Game {
    private long id;
    private String name;
    private int cost;
    private Date releaseDate;
    private String description;
    private SystemRequirements minimalSystemRequirements;
    private SystemRequirements recommendedSystemRequirements;
    private String developer;
    private String posterLink;


    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public SystemRequirements getMinimalSystemRequirements() {
        return minimalSystemRequirements;
    }

    public void setMinimalSystemRequirements(SystemRequirements minimalSystemRequirements) {
        this.minimalSystemRequirements = minimalSystemRequirements;
    }

    public SystemRequirements getRecommendedSystemRequirements() {
        return recommendedSystemRequirements;
    }

    public void setRecommendedSystemRequirements(SystemRequirements recommendedSystemRequirements) {
        this.recommendedSystemRequirements = recommendedSystemRequirements;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getPosterLink() {
        return posterLink;
    }

    public void setPosterLink(String posterLink) {
        this.posterLink = posterLink;
    }

    public Game(long id, String name, int cost, Date releaseDate, String description, SystemRequirements minimalSystemRequirements,
                SystemRequirements recommendedSystemRequirements, String developer) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.releaseDate = releaseDate;
        this.description = description;
        this.minimalSystemRequirements = minimalSystemRequirements;
        this.recommendedSystemRequirements = recommendedSystemRequirements;
        this.developer = developer;
    }

    public Game(long id) {
        this.id = id;
    }

    public Game() {
    }

    @Override
    public boolean equals(Object obj) {
        boolean answer = false;
        Game game = (Game) obj;
        if(this.name.equalsIgnoreCase(game.getName()))answer = true;
        return answer;
    }
}
