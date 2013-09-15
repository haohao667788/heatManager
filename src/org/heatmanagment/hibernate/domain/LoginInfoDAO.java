package org.heatmanagment.hibernate.domain;

import java.sql.SQLException;
import java.sql.Timestamp;
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
 * LoginInfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see org.heatmanagment.hibernate.domain.LoginInfo
 * @author MyEclipse Persistence Tools
 */

public class LoginInfoDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(LoginInfoDAO.class);
	// property constants
	public static final String IP = "ip";
	public static final String SUCCESS = "success";
	public static final String FAILRSN = "failrsn";
	public static final String OLTIME = "oltime";
	public static final String ISVALID = "isvalid";

	protected void initDao() {
		// do nothing
	}

	public void save(LoginInfo transientInstance) {
		log.debug("saving LoginInfo instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(LoginInfo persistentInstance) {
		log.debug("deleting LoginInfo instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public LoginInfo findById(java.lang.Long id) {
		log.debug("getting LoginInfo instance with id: " + id);
		try {
			LoginInfo instance = (LoginInfo) getHibernateTemplate().get(
					"org.heatmanagment.hibernate.domain.LoginInfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<LoginInfo> findByExample(LoginInfo instance) {
		log.debug("finding LoginInfo instance by example");
		try {
			List<LoginInfo> results = (List<LoginInfo>) getHibernateTemplate()
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
		log.debug("finding LoginInfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from LoginInfo as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<LoginInfo> findByIp(Object ip) {
		return findByProperty(IP, ip);
	}

	public List<LoginInfo> findBySuccess(Object success) {
		return findByProperty(SUCCESS, success);
	}

	public List<LoginInfo> findByFailrsn(Object failrsn) {
		return findByProperty(FAILRSN, failrsn);
	}

	public List<LoginInfo> findByOltime(Object oltime) {
		return findByProperty(OLTIME, oltime);
	}

	public List<LoginInfo> findByIsvalid(Object isvalid) {
		return findByProperty(ISVALID, isvalid);
	}

	public List findAll() {
		log.debug("finding all LoginInfo instances");
		try {
			String queryString = "from LoginInfo as c where c.isvalid=true";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findPage(final int start, final int limit) {
		log.debug("finding all LoginInfo instances with boundary");
		try {
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String q = "from LoginInfo as d where d.isvalid=:valid";
					Query query = session.createQuery(q).setFirstResult(start)
							.setMaxResults(limit);
					query.setBoolean("valid", true);
					return query.list();
				}
			});
		} catch (RuntimeErrorException re) {
			log.error("find all LoginInfo with boundary failed", re);
			throw re;
		}
	}

	public void remove(Long id) {
		LoginInfo info = new LoginInfo();
		info.setLginid(id);
		info.setIsvalid(false);
		attachDirty(info);
	}

	public LoginInfo merge(LoginInfo detachedInstance) {
		log.debug("merging LoginInfo instance");
		try {
			LoginInfo result = (LoginInfo) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(LoginInfo instance) {
		log.debug("attaching dirty LoginInfo instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(LoginInfo instance) {
		log.debug("attaching clean LoginInfo instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static LoginInfoDAO getFromApplicationContext(ApplicationContext ctx) {
		return (LoginInfoDAO) ctx.getBean("LoginInfoDAO");
	}
}