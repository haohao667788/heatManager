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
 * HeatsourceInfo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see org.heatmanagment.hibernate.domain.HeatsourceInfo
 * @author MyEclipse Persistence Tools
 */

public class HeatsourceInfoDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(HeatsourceInfoDAO.class);
	// property constants
	public static final String SRCNAME = "srcname";
	public static final String SRCADDRESS = "srcaddress";
	public static final String HEATTYPE = "heattype";
	public static final String DESP = "desp";
	public static final String COMM = "comm";

	public void save(HeatsourceInfo transientInstance) {
		log.debug("saving HeatsourceInfo instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(HeatsourceInfo persistentInstance) {
		log.debug("deleting HeatsourceInfo instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public HeatsourceInfo findById(java.lang.Long id) {
		log.debug("getting HeatsourceInfo instance with id: " + id);
		try {
			HeatsourceInfo instance = (HeatsourceInfo) getSession().get(
					"org.heatmanagment.hibernate.domain.HeatsourceInfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<HeatsourceInfo> findByExample(HeatsourceInfo instance) {
		log.debug("finding HeatsourceInfo instance by example");
		try {
			List<HeatsourceInfo> results = (List<HeatsourceInfo>) getSession()
					.createCriteria(
							"org.heatmanagment.hibernate.domain.HeatsourceInfo")
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
		log.debug("finding HeatsourceInfo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from HeatsourceInfo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<HeatsourceInfo> findBySrcname(Object srcname) {
		return findByProperty(SRCNAME, srcname);
	}

	public List<HeatsourceInfo> findBySrcaddress(Object srcaddress) {
		return findByProperty(SRCADDRESS, srcaddress);
	}

	public List<HeatsourceInfo> findByHeattype(Object heattype) {
		return findByProperty(HEATTYPE, heattype);
	}

	public List<HeatsourceInfo> findByDesp(Object desp) {
		return findByProperty(DESP, desp);
	}

	public List<HeatsourceInfo> findByComm(Object comm) {
		return findByProperty(COMM, comm);
	}

	public List findAll() {
		log.debug("finding all HeatsourceInfo instances");
		try {
			String queryString = "from HeatsourceInfo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public HeatsourceInfo merge(HeatsourceInfo detachedInstance) {
		log.debug("merging HeatsourceInfo instance");
		try {
			HeatsourceInfo result = (HeatsourceInfo) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(HeatsourceInfo instance) {
		log.debug("attaching dirty HeatsourceInfo instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(HeatsourceInfo instance) {
		log.debug("attaching clean HeatsourceInfo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}