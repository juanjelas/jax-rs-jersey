package com.jaxrs.messenger.model;

public class Link {

    private String link;
    private String rel;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Link link = (Link) o;

        return rel != null ? rel.equals(link.rel) : link.rel == null;
    }

    @Override
    public int hashCode() {
        return rel != null ? rel.hashCode() : 0;
    }
}
