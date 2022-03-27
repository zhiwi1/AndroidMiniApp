package by.bsuir.lab1mobilki.model;

import java.time.ZonedDateTime;
import java.util.Objects;

public class User {
    private long id;
    private ZonedDateTime createdAt;
    private long score;

    public User(long id, ZonedDateTime createdAt, long score) {
        this.id = id;
        this.createdAt = createdAt;
        this.score = score;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && score == user.score && Objects.equals(createdAt, user.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, score);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", score=" + score +
                '}';
    }
}
