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
 * DistrictInfo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see org.heatmanagment.hibernate.domain.DistrictInfo
 * @author MyEclipse Persistence Tools
 */

public class DistrictInfoDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(DistrictInfoDAO.class);
	// property constants
	public static final String DSTNAME = "dstname";
	public static final String COMM = "comm";

	public void save(DistrictInfo transientInstance) {
		log.debug("saving DistrictInfo instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(DistrictInfo persistentInstance) {
		log.debug("deleting DistrictInfo instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DistrictInfo findById(java.lang.Long id) {
		log.debug("getting DistrictInfo instance with id: " + id);
		try {
			DistrictInfo instance = (DistrictInfo) getSession().get(
					"org.heatmanagment.hibernate.domain.DistrictInfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<DistrictInfo> findByExample(DistrictInfo instance) {
		log.debug("finding DistrictInfo instance by example");
		try {
			List<DistrictInfo> results = (List<DistrictInfo>) getSession()
					.createCriteria(
							"org.heatmanagment.hibernate.domain.DistrictInfo")
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
		log.debug("finding DistrictInfo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from DistrictInfo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<DistrictInfo> findByDstname(Object dstname) {
		return findByProperty(DSTNAME, dstname);
	}

	public List<DistrictInfo> findByComm(Object comm) {
		return findByProperty(COMM, comm);
	}

	public List findAll() {
		log.debug("finding all DistrictInfo instances");
		try {
			String queryString = "from DistrictInfo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public DistrictInfo merge(DistrictInfo detachedInstance) {
		log.debug("merging DistrictInfo instance");
		try {
			DistrictInfo result = (DistrictInfo) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(DistrictInfo instance) {
		log.debug("attaching dirty DistrictInfo instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DistrictInfo instance) {
		log.debug("attaching clean DistrictInfo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}