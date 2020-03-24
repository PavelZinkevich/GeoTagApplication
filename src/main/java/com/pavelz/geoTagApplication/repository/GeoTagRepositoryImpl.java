package com.pavelz.geoTagApplication.repository;

import com.pavelz.geoTagApplication.entity.GeoTag;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GeoTagRepositoryImpl implements GeoTagRepository {

    private static final Logger logger = LoggerFactory.getLogger(GeoTagRepositoryImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addGeoTag(GeoTag geoTag) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(geoTag);
        logger.info("GeoTag successfully saved. GeoTag details: " + geoTag);
    }

    @Override
    public void updateGeoTag(GeoTag geoTag) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(geoTag);
        logger.info("GeoTag successfully updated. GeoTag details: " + geoTag);
    }

    @Override
    public void removeGeoTag(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        GeoTag geoTag = (GeoTag) session.load(GeoTag.class, id);

        if (geoTag!=null){
            session.delete(geoTag);
            logger.info("GeoTag successfully removed. GeoTag details: " + geoTag);
        } else{
            logger.info("GeoTag with current id does not exist");
        }
    }

    @Override
    public GeoTag getGeoTagById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        GeoTag geoTag = (GeoTag) session.load(GeoTag.class, id);
        logger.info("GeoTag successfully loaded. GeoTag details: " + geoTag);
        return geoTag;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<GeoTag> listGeoTag() {
        Session session = this.sessionFactory.getCurrentSession();
        List<GeoTag> geoTagList = session.createQuery("from GeoTag").list();

        geoTagList.stream().forEach(geoTag->logger.info("User list: " + geoTag));

        return geoTagList;
    }
}
