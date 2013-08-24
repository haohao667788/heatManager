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
 * MachinesetInfo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see org.heatmanagment.hibernate.domain.MachinesetInfo
 * @author MyEclipse Persistence Tools
 */

public class MachinesetInfoDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MachinesetInfoDAO.class);
	// property constants
	public static final String MCHNAME = "mchname";
	public static final String GIS = "gis";

	public void save(MachinesetInfo transientInstance) {
		log.debug("saving MachinesetInfo instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MachinesetInfo persistentInstance) {
		log.debug("deleting MachinesetInfo instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MachinesetInfo findById(java.lang.Long id) {
		log.debug("getting MachinesetInfo instance with id: " + id);
		try {
			MachinesetInfo instance = (MachinesetInfo) getSession().get(
					"org.heatmanagment.hibernate.domain.MachinesetInfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<MachinesetInfo> findByExample(MachinesetInfo instance) {
		log.debug("finding MachinesetInfo instance by example");
		try {
			List<MachinesetInfo> results = (List<MachinesetInfo>) getSession()
					.createCriteria(
							"org.heatmanagment.hibernate.domain.MachinesetInfo")
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
		log.debug("finding MachinesetInfo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MachinesetInfo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<MachinesetInfo> findByMchname(Object mchname) {
		return findByProperty(MCHNAME, mchname);
	}

	public List<MachinesetInfo> findByGis(Object gis) {
		return findByProperty(GIS, gis);
	}

	public List findAll() {
		log.debug("finding all MachinesetInfo instances");
		try {
			String queryString = "from MachinesetInfo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MachinesetInfo merge(MachinesetInfo detachedInstance) {
		log.debug("merging MachinesetInfo instance");
		try {
			MachinesetInfo result = (MachinesetInfo) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MachinesetInfo instance) {
		log.debug("attaching dirty MachinesetInfo instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MachinesetInfo instance) {
		log.debug("attaching clean MachinesetInfo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}