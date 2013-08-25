package org.heatmanagment.hibernate.domain;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * UnitInfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see org.heatmanagment.hibernate.domain.UnitInfo
 * @author MyEclipse Persistence Tools
 */

public class UnitInfoDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(UnitInfoDAO.class);
	// property constants
	public static final String CMTNAME = "cmtname";
	public static final String BLDNAME = "bldname";
	public static final String UNTNAME = "untname";
	public static final String GIS = "gis";
	public static final String PICADDRESS = "picaddress";
	public static final String COMM = "comm";

	public void save(UnitInfo transientInstance) {
		log.debug("saving UnitInfo instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(UnitInfo persistentInstance) {
		log.debug("deleting UnitInfo instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UnitInfo findById(java.lang.Long id) {
		log.debug("getting UnitInfo instance with id: " + id);
		try {
			UnitInfo instance = (UnitInfo) getSession().get(
					"org.heatmanagment.hibernate.domain.UnitInfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<UnitInfo> findByExample(UnitInfo instance) {
		log.debug("finding UnitInfo instance by example");
		try {
			List<UnitInfo> results = (List<UnitInfo>) getSession()
					.createCriteria(
							"org.heatmanagment.hibernate.domain.UnitInfo")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding UnitInfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from UnitInfo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<UnitInfo> findByCmtname(Object cmtname) {
		return findByProperty(CMTNAME, cmtname);
	}

	public List<UnitInfo> findByBldname(Object bldname) {
		return findByProperty(BLDNAME, bldname);
	}

	public List<UnitInfo> findByUntname(Object untname) {
		return findByProperty(UNTNAME, untname);
	}

	public List<UnitInfo> findByGis(Object gis) {
		return findByProperty(GIS, gis);
	}

	public List<UnitInfo> findByPicaddress(Object picaddress) {
		return findByProperty(PICADDRESS, picaddress);
	}

	public List<UnitInfo> findByComm(Object comm) {
		return findByProperty(COMM, comm);
	}

	public List findAll() {
		log.debug("finding all UnitInfo instances");
		try {
			String queryString = "from UnitInfo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public UnitInfo merge(UnitInfo detachedInstance) {
		log.debug("merging UnitInfo instance");
		try {
			UnitInfo result = (UnitInfo) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(UnitInfo instance) {
		log.debug("attaching dirty UnitInfo instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UnitInfo instance) {
		log.debug("attaching clean UnitInfo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}