package com.pavelz.geoTagApplication.repository;

import com.pavelz.geoTagApplication.entity.GeoTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GeoTagRepository/* extends JpaRepository */{
    public void addGeoTag(GeoTag geoTag);
    public void updateGeoTag(GeoTag geoTag);
    public void removeGeoTag (int id);
    public GeoTag getGeoTagById(int id);
    public List<GeoTag> listGeoTag();
}
