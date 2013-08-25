package org.heatmanagment.hibernate.domain;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * BuildingInfo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see org.heatmanagment.hibernate.domain.BuildingInfo
 * @author MyEclipse Persistence Tools
 */

public class BuildingInfoDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(BuildingInfoDAO.class);
	// property constants
	public static final String BLDNAME = "bldname";
	public static final String BLDADDRESS = "bldaddress";
	public static final String HEATTYPE = "heattype";
	public static final String GIS = "gis";
	public static final String PICADDRESS = "picaddress";
	public static final String COMM = "comm";

	protected void initDao() {
		// do nothing
	}

	public void save(BuildingInfo transientInstance) {
		log.debug("saving BuildingInfo instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(BuildingInfo persistentInstance) {
		log.debug("deleting BuildingInfo instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BuildingInfo findById(java.lang.Long id) {
		log.debug("getting BuildingInfo instance with id: " + id);
		try {
			BuildingInfo instance = (BuildingInfo) getHibernateTemplate().get(
					"org.heatmanagment.hibernate.domain.BuildingInfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<BuildingInfo> findByExample(BuildingInfo instance) {
		log.debug("finding BuildingInfo instance by example");
		try {
			List<BuildingInfo> results = (List<BuildingInfo>) getHibernateTemplate()
					.findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding BuildingInfo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from BuildingInfo as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<BuildingInfo> findByBldname(Object bldname) {
		return findByProperty(BLDNAME, bldname);
	}

	public List<BuildingInfo> findByBldaddress(Object bldaddress) {
		return findByProperty(BLDADDRESS, bldaddress);
	}

	public List<BuildingInfo> findByHeattype(Object heattype) {
		return findByProperty(HEATTYPE, heattype);
	}

	public List<BuildingInfo> findByGis(Object gis) {
		return findByProperty(GIS, gis);
	}

	public List<BuildingInfo> findByPicaddress(Object picaddress) {
		return findByProperty(PICADDRESS, picaddress);
	}

	public List<BuildingInfo> findByComm(Object comm) {
		return findByProperty(COMM, comm);
	}

	public List findAll() {
		log.debug("finding all BuildingInfo instances");
		try {
			String queryString = "from BuildingInfo";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public BuildingInfo merge(BuildingInfo detachedInstance) {
		log.debug("merging BuildingInfo instance");
		try {
			BuildingInfo result = (BuildingInfo) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BuildingInfo instance) {
		log.debug("attaching dirty BuildingInfo instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BuildingInfo instance) {
		log.debug("attaching clean BuildingInfo instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BuildingInfoDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (BuildingInfoDAO) ctx.getBean("BuildingInfoDAO");
	}
}