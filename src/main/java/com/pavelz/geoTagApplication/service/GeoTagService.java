package com.pavelz.geoTagApplication.service;

import com.pavelz.geoTagApplication.entity.GeoTag;

import java.util.List;

public interface GeoTagService {
    public void addGeoTag(GeoTag geoTag);
    public void updateGeoTag(GeoTag geoTag);
    public void removeGeoTag (int id);
    public GeoTag getGeoTagById(int id);
    public List<GeoTag> listGeoTag();
}
