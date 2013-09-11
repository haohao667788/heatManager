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
 * AccountrangeInfo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see org.heatmanagment.hibernate.domain.AccountrangeInfo
 * @author MyEclipse Persistence Tools
 */

public class AccountrangeInfoDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(AccountrangeInfoDAO.class);
	// property constants
	public static final String CURBALANCE = "curbalance";
	public static final String CURCHARGE = "curcharge";
	public static final String CURMONEY = "curmoney";
	public static final String FINACERANGE = "finacerange";
	public static final String DONEFINACERANGE = "donefinacerange";

	protected void initDao() {
		// do nothing
	}

	public void save(AccountrangeInfo transientInstance) {
		log.debug("saving AccountrangeInfo instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(AccountrangeInfo persistentInstance) {
		log.debug("deleting AccountrangeInfo instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AccountrangeInfo findById(java.lang.String id) {
		log.debug("getting AccountrangeInfo instance with id: " + id);
		try {
			AccountrangeInfo instance = (AccountrangeInfo) getHibernateTemplate()
					.get("org.heatmanagment.hibernate.domain.AccountrangeInfo",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<AccountrangeInfo> findByExample(AccountrangeInfo instance) {
		log.debug("finding AccountrangeInfo instance by example");
		try {
			List<AccountrangeInfo> results = (List<AccountrangeInfo>) getHibernateTemplate()
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
		log.debug("finding AccountrangeInfo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from AccountrangeInfo as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<AccountrangeInfo> findByCurbalance(Object curbalance) {
		return findByProperty(CURBALANCE, curbalance);
	}

	public List<AccountrangeInfo> findByCurcharge(Object curcharge) {
		return findByProperty(CURCHARGE, curcharge);
	}

	public List<AccountrangeInfo> findByCurmoney(Object curmoney) {
		return findByProperty(CURMONEY, curmoney);
	}

	public List<AccountrangeInfo> findByFinacerange(Object finacerange) {
		return findByProperty(FINACERANGE, finacerange);
	}

	public List<AccountrangeInfo> findByDonefinacerange(Object donefinacerange) {
		return findByProperty(DONEFINACERANGE, donefinacerange);
	}

	public List findAll() {
		log.debug("finding all AccountrangeInfo instances");
		try {
			String queryString = "from AccountrangeInfo";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findPage(final int start, final int limit) {
		log.debug("finding all AccountrangeInfo instances with boundary");
		try {
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String q = "from AccountrangeInfo";
					Query query = session.createQuery(q).setFirstResult(start)
							.setMaxResults(limit);
					return query.list();
				}
			});
		} catch (RuntimeErrorException re) {
			log.error("find all AccountrangeInfo with boundary failed", re);
			throw re;
		}
	}

	public AccountrangeInfo merge(AccountrangeInfo detachedInstance) {
		log.debug("merging AccountrangeInfo instance");
		try {
			AccountrangeInfo result = (AccountrangeInfo) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(AccountrangeInfo instance) {
		log.debug("attaching dirty AccountrangeInfo instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AccountrangeInfo instance) {
		log.debug("attaching clean AccountrangeInfo instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AccountrangeInfoDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (AccountrangeInfoDAO) ctx.getBean("AccountrangeInfoDAO");
	}
}