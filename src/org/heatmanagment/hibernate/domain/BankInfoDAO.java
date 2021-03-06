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
 * BankInfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see org.heatmanagment.hibernate.domain.BankInfo
 * @author MyEclipse Persistence Tools
 */

public class BankInfoDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(BankInfoDAO.class);
	// property constants
	public static final String BNKNUM = "bnknum";
	public static final String BNKNAME = "bnkname";
	public static final String ACCOUNTNUM = "accountnum";
	public static final String DESP = "desp";
	public static final String ISVALID = "isvalid";

	protected void initDao() {
		// do nothing
	}

	public void save(BankInfo transientInstance) {
		log.debug("saving BankInfo instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(BankInfo persistentInstance) {
		log.debug("deleting BankInfo instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BankInfo findById(java.lang.Long id) {
		log.debug("getting BankInfo instance with id: " + id);
		try {
			BankInfo instance = (BankInfo) getHibernateTemplate().get(
					"org.heatmanagment.hibernate.domain.BankInfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<BankInfo> findByExample(BankInfo instance) {
		log.debug("finding BankInfo instance by example");
		try {
			List<BankInfo> results = (List<BankInfo>) getHibernateTemplate()
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
		log.debug("finding BankInfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from BankInfo as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<BankInfo> findByBnknum(Object bnknum) {
		return findByProperty(BNKNUM, bnknum);
	}

	public List<BankInfo> findByBnkname(Object bnkname) {
		return findByProperty(BNKNAME, bnkname);
	}

	public List<BankInfo> findByAccountnum(Object accountnum) {
		return findByProperty(ACCOUNTNUM, accountnum);
	}

	public List<BankInfo> findByDesp(Object desp) {
		return findByProperty(DESP, desp);
	}

	public List<BankInfo> findByIsvalid(Object isvalid) {
		return findByProperty(ISVALID, isvalid);
	}

	public List findAll() {
		log.debug("finding all BankInfo instances");
		try {
			String queryString = "from BankInfo as c where c.isvalid=true";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findPage(final int start, final int limit) {
		log.debug("finding all BankInfo instances with boundary");
		try {
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String q = "from BankInfo as d where d.isvalid=:valid";
					Query query = session.createQuery(q).setFirstResult(start)
							.setMaxResults(limit);
					query.setBoolean("valid", true);
					return query.list();
				}
			});
		} catch (RuntimeErrorException re) {
			log.error("find all BankInfo with boundary failed", re);
			throw re;
		}
	}
	

	public Long count() {
		log.debug("count BankInfos");
		String hql = "select count(*) from BankInfo  where isvalid=true";
		return (Long) getHibernateTemplate().find(hql).listIterator().next();
	}

	public void remove(Long id) {
		BankInfo bnk = new BankInfo();
		bnk.setBnkid(id);
		bnk.setIsvalid(false);
		attachDirty(bnk);
	}

	public BankInfo merge(BankInfo detachedInstance) {
		log.debug("merging BankInfo instance");
		try {
			BankInfo result = (BankInfo) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BankInfo instance) {
		log.debug("attaching dirty BankInfo instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BankInfo instance) {
		log.debug("attaching clean BankInfo instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BankInfoDAO getFromApplicationContext(ApplicationContext ctx) {
		return (BankInfoDAO) ctx.getBean("BankInfoDAO");
	}
}