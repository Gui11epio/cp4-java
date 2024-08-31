package org.example.Entidades;

public abstract class _EntidadeBase {
    private int id;
    private boolean deleted;
    private boolean published;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    @Override
    public String toString() {
        return "_EntidadeBase{" +
                "id=" + id +
                ", deleted=" + deleted +
                ", published=" + published +
                '}';
    }
}
