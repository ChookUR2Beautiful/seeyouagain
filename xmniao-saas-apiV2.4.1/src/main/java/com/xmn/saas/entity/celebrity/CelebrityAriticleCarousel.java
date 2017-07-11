package com.xmn.saas.entity.celebrity;

public class CelebrityAriticleCarousel {
    private Long id;

    private Long celebrityAriticleId;

    private String image;

    private Integer order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCelebrityAriticleId() {
        return celebrityAriticleId;
    }

    public void setCelebrityAriticleId(Long celebrityAriticleId) {
        this.celebrityAriticleId = celebrityAriticleId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}