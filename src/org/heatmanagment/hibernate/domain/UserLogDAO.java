package org.heatmanagment.hibernate.domain;

import java.sql.SQLException;
import java.util.List;

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
 * UserLog entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see org.heatmanagment.hibernate.domain.UserLog
 * @author MyEclipse Persistence Tools
 */

public class UserLogDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(UserLogDAO.class);
	// property constants
	public static final String LOGTYPE = "logtype";
	public static final String LOGTITLE = "logtitle";
	public static final String LOGCONTENT = "logcontent";
	public static final String DESP = "desp";
	public static final String ISVALID = "isvalid";

	protected void initDao() {
		// do nothing
	}

	public void save(UserLog transientInstance) {
		log.debug("saving UserLog instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(UserLog persistentInstance) {
		log.debug("deleting UserLog instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UserLog findById(java.lang.Long id) {
		log.debug("getting UserLog instance with id: " + id);
		try {
			UserLog instance = (UserLog) getHibernateTemplate().get(
					"org.heatmanagment.hibernate.domain.UserLog", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<UserLog> findByExample(UserLog instance) {
		log.debug("finding UserLog instance by example");
		try {
			List<UserLog> results = (List<UserLog>) getHibernateTemplate()
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
		log.debug("finding UserLog instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from UserLog as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<UserLog> findByLogtype(Object logtype) {
		return findByProperty(LOGTYPE, logtype);
	}

	public List<UserLog> findByLogtitle(Object logtitle) {
		return findByProperty(LOGTITLE, logtitle);
	}

	public List<UserLog> findByLogcontent(Object logcontent) {
		return findByProperty(LOGCONTENT, logcontent);
	}

	public List<UserLog> findByDesp(Object desp) {
		return findByProperty(DESP, desp);
	}

	public List<UserLog> findByIsvalid(Object isvalid) {
		return findByProperty(ISVALID, isvalid);
	}

	public List findAll() {
		log.debug("finding all UserLog instances");
		try {
			String queryString = "from UserLog as c where c.isvalid=true";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findPage(final int start, final int limit) {
		log.debug("finding all UserLog instances with boundary");
		try {
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String q = "from UserLog as d where d.isvalid=:valid";
					Query query = session.createQuery(q).setFirstResult(start)
							.setMaxResults(limit);
					query.setBoolean("valid", true);
					return query.list();
				}
			});
		} catch (RuntimeErrorException re) {
			log.error("find all UserLog with boundary failed", re);
			throw re;
		}
	}

	public void remove(Long id) {
		UserLog log = new UserLog();
		log.setLogid(id);
		log.setIsvalid(false);
		attachDirty(log);
	}

	public UserLog merge(UserLog detachedInstance) {
		log.debug("merging UserLog instance");
		try {
			UserLog result = (UserLog) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(UserLog instance) {
		log.debug("attaching dirty UserLog instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UserLog instance) {
		log.debug("attaching clean UserLog instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static UserLogDAO getFromApplicationContext(ApplicationContext ctx) {
		return (UserLogDAO) ctx.getBean("UserLogDAO");
	}
}