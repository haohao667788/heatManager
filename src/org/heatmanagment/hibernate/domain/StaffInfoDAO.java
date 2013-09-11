package org.heatmanagment.hibernate.domain;

import java.sql.SQLException;
import java.sql.Timestamp;
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
 * StaffInfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see org.heatmanagment.hibernate.domain.StaffInfo
 * @author MyEclipse Persistence Tools
 */

public class StaffInfoDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(StaffInfoDAO.class);
	// property constants
	public static final String STFNAME = "stfname";
	public static final String CONTACTNUMBER = "contactnumber";
	public static final String LOGINNAME = "loginname";
	public static final String PWD = "pwd";

	protected void initDao() {
		// do nothing
	}

	public void save(StaffInfo transientInstance) {
		log.debug("saving StaffInfo instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(StaffInfo persistentInstance) {
		log.debug("deleting StaffInfo instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public StaffInfo findById(java.lang.String id) {
		log.debug("getting StaffInfo instance with id: " + id);
		try {
			StaffInfo instance = (StaffInfo) getHibernateTemplate().get(
					"org.heatmanagment.hibernate.domain.StaffInfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<StaffInfo> findByExample(StaffInfo instance) {
		log.debug("finding StaffInfo instance by example");
		try {
			List<StaffInfo> results = (List<StaffInfo>) getHibernateTemplate()
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
		log.debug("finding StaffInfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from StaffInfo as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<StaffInfo> findByStfname(Object stfname) {
		return findByProperty(STFNAME, stfname);
	}

	public List<StaffInfo> findByContactnumber(Object contactnumber) {
		return findByProperty(CONTACTNUMBER, contactnumber);
	}

	public List<StaffInfo> findByLoginname(Object loginname) {
		return findByProperty(LOGINNAME, loginname);
	}

	public List<StaffInfo> findByPwd(Object pwd) {
		return findByProperty(PWD, pwd);
	}

	public List findAll() {
		log.debug("finding all StaffInfo instances");
		try {
			String queryString = "from StaffInfo";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findPage(final int start, final int limit) {
		log.debug("finding all StaffInfo instances with boundary");
		try {
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String q = "from StaffInfo";
					Query query = session.createQuery(q).setFirstResult(start)
							.setMaxResults(limit);
					return query.list();
				}
			});
		} catch (RuntimeErrorException re) {
			log.error("find all StaffInfo with boundary failed", re);
			throw re;
		}
	}

	public StaffInfo merge(StaffInfo detachedInstance) {
		log.debug("merging StaffInfo instance");
		try {
			StaffInfo result = (StaffInfo) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(StaffInfo instance) {
		log.debug("attaching dirty StaffInfo instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(StaffInfo instance) {
		log.debug("attaching clean StaffInfo instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static StaffInfoDAO getFromApplicationContext(ApplicationContext ctx) {
		return (StaffInfoDAO) ctx.getBean("StaffInfoDAO");
	}
}