package com.pavelz.geoTagApplication.service;

import com.pavelz.geoTagApplication.entity.GeoTag;
import com.pavelz.geoTagApplication.repository.GeoTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GeoTagServiceImpl implements GeoTagService {

    @Autowired
    private GeoTagRepository geoTagRepository;

    public void setGeoTagRepository(GeoTagRepository geoTagRepository) {
        this.geoTagRepository = geoTagRepository;
    }

    @Override
    @Transactional
    public void addGeoTag(GeoTag geoTag) {
        this.geoTagRepository.updateGeoTag(geoTag);
    }

    @Override
    @Transactional
    public void updateGeoTag(GeoTag geoTag) {
        this.geoTagRepository.updateGeoTag(geoTag);
    }

    @Override
    @Transactional
    public void removeGeoTag(int id) {
        this.geoTagRepository.removeGeoTag(id);
    }

    @Override
    @Transactional
    public GeoTag getGeoTagById(int id) {
        return this.getGeoTagById(id);
    }

    @Override
    @Transactional
    public List<GeoTag> listGeoTag() {
        return this.listGeoTag();
    }
}
