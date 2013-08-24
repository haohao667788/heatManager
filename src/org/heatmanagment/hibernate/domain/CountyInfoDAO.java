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
 * CountyInfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see org.heatmanagment.hibernate.domain.CountyInfo
 * @author MyEclipse Persistence Tools
 */

public class CountyInfoDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CountyInfoDAO.class);
	// property constants
	public static final String CTYNAME = "ctyname";
	public static final String COMM = "comm";

	public void save(CountyInfo transientInstance) {
		log.debug("saving CountyInfo instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CountyInfo persistentInstance) {
		log.debug("deleting CountyInfo instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CountyInfo findById(java.lang.Long id) {
		log.debug("getting CountyInfo instance with id: " + id);
		try {
			CountyInfo instance = (CountyInfo) getSession().get(
					"org.heatmanagment.hibernate.domain.CountyInfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<CountyInfo> findByExample(CountyInfo instance) {
		log.debug("finding CountyInfo instance by example");
		try {
			List<CountyInfo> results = (List<CountyInfo>) getSession()
					.createCriteria(
							"org.heatmanagment.hibernate.domain.CountyInfo")
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
		log.debug("finding CountyInfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from CountyInfo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<CountyInfo> findByCtyname(Object ctyname) {
		return findByProperty(CTYNAME, ctyname);
	}

	public List<CountyInfo> findByComm(Object comm) {
		return findByProperty(COMM, comm);
	}

	public List findAll() {
		log.debug("finding all CountyInfo instances");
		try {
			String queryString = "from CountyInfo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CountyInfo merge(CountyInfo detachedInstance) {
		log.debug("merging CountyInfo instance");
		try {
			CountyInfo result = (CountyInfo) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CountyInfo instance) {
		log.debug("attaching dirty CountyInfo instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CountyInfo instance) {
		log.debug("attaching clean CountyInfo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}