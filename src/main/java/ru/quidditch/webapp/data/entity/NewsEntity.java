package ru.quidditch.webapp.data.entity;

import ru.quidditch.webapp.data.enums.NewsTypes;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "news")
public class NewsEntity {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String headline;

    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name = "operator_id")
    private OperatorEntity operator;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private TeamEntity team;

    @Column
    private NewsTypes newsType;

    @Column
    private Date date;

    public NewsEntity() {
    }

    public NewsEntity(String headline, String content) {
        this.headline = headline;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public OperatorEntity getOperator() {
        return operator;
    }

    public void setOperator(OperatorEntity operator) {
        this.operator = operator;
    }

    public NewsTypes getNewsType() {
        return newsType;
    }

    public void setNewsType(NewsTypes newsType) {
        this.newsType = newsType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TeamEntity getTeam() {
        return team;
    }

    public void setTeam(TeamEntity team) {
        this.team = team;
    }
}
