package org.heatmanagment.hibernate.domain;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import javax.management.RuntimeErrorException;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

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

public class MachinesetInfoDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(MachinesetInfoDAO.class);
	// property constants
	public static final String MCHNAME = "mchname";
	public static final String GIS = "gis";

	protected void initDao() {
		// do nothing
	}

	public void save(MachinesetInfo transientInstance) {
		log.debug("saving MachinesetInfo instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MachinesetInfo persistentInstance) {
		log.debug("deleting MachinesetInfo instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MachinesetInfo findById(java.lang.Long id) {
		log.debug("getting MachinesetInfo instance with id: " + id);
		try {
			MachinesetInfo instance = (MachinesetInfo) getHibernateTemplate()
					.get("org.heatmanagment.hibernate.domain.MachinesetInfo",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<MachinesetInfo> findByExample(MachinesetInfo instance) {
		log.debug("finding MachinesetInfo instance by example");
		try {
			List<MachinesetInfo> results = (List<MachinesetInfo>) getHibernateTemplate()
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
		log.debug("finding MachinesetInfo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MachinesetInfo as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
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
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findPage(final int start, final int limit) {
		log.debug("finding all MachinesetInfo instances with boundary");
		try {
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String q = "from MachinesetInfo";
					Query query = session.createQuery(q).setFirstResult(start)
							.setMaxResults(limit);
					return query.list();
				}
			});
		} catch (RuntimeErrorException re) {
			log.error("find all MachinesetInfo with boundary failed", re);
			throw re;
		}
	}

	public MachinesetInfo merge(MachinesetInfo detachedInstance) {
		log.debug("merging MachinesetInfo instance");
		try {
			MachinesetInfo result = (MachinesetInfo) getHibernateTemplate()
					.merge(detachedInstance);
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
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MachinesetInfo instance) {
		log.debug("attaching clean MachinesetInfo instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MachinesetInfoDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MachinesetInfoDAO) ctx.getBean("MachinesetInfoDAO");
	}
}