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
 * BankCertificate entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see org.heatmanagment.hibernate.domain.BankCertificate
 * @author MyEclipse Persistence Tools
 */

public class BankCertificateDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(BankCertificateDAO.class);
	// property constants
	public static final String CTFTYPE = "ctftype";
	public static final String CTFNUMBER = "ctfnumber";
	public static final String MONEY = "money";
	public static final String UNDERTAKER = "undertaker";
	public static final String IMPORTER = "importer";
	public static final String RELATEDNUM = "relatednum";
	public static final String ISVALID = "isvalid";

	protected void initDao() {
		// do nothing
	}

	public void save(BankCertificate transientInstance) {
		log.debug("saving BankCertificate instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(BankCertificate persistentInstance) {
		log.debug("deleting BankCertificate instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BankCertificate findById(java.lang.Long id) {
		log.debug("getting BankCertificate instance with id: " + id);
		try {
			BankCertificate instance = (BankCertificate) getHibernateTemplate()
					.get("org.heatmanagment.hibernate.domain.BankCertificate",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<BankCertificate> findByExample(BankCertificate instance) {
		log.debug("finding BankCertificate instance by example");
		try {
			List<BankCertificate> results = (List<BankCertificate>) getHibernateTemplate()
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
		log.debug("finding BankCertificate instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from BankCertificate as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<BankCertificate> findByCtftype(Object ctftype) {
		return findByProperty(CTFTYPE, ctftype);
	}

	public List<BankCertificate> findByCtfnumber(Object ctfnumber) {
		return findByProperty(CTFNUMBER, ctfnumber);
	}

	public List<BankCertificate> findByMoney(Object money) {
		return findByProperty(MONEY, money);
	}

	public List<BankCertificate> findByUndertaker(Object undertaker) {
		return findByProperty(UNDERTAKER, undertaker);
	}

	public List<BankCertificate> findByImporter(Object importer) {
		return findByProperty(IMPORTER, importer);
	}

	public List<BankCertificate> findByRelatednum(Object relatednum) {
		return findByProperty(RELATEDNUM, relatednum);
	}

	public List<BankCertificate> findByIsvalid(Object isvalid) {
		return findByProperty(ISVALID, isvalid);
	}

	public List findAll() {
		log.debug("finding all BankCertificate instances");
		try {
			String queryString = "from BankCertificate as c where c.isvalid=true";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findPage(final int start, final int limit) {
		log.debug("finding all BankCertificate instances with boundary");
		try {
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String q = "from BankCertificate as d where d.isvalid=:valid";
					Query query = session.createQuery(q).setFirstResult(start)
							.setMaxResults(limit);
					query.setBoolean("valid", true);
					return query.list();
				}
			});
		} catch (RuntimeErrorException re) {
			log.error("find all BankCertificate with boundary failed", re);
			throw re;
		}
	}

	public void remove(Long id) {
		BankCertificate ctf = new BankCertificate();
		ctf.setCtfid(id);
		ctf.setIsvalid(false);
		attachDirty(ctf);
	}

	public BankCertificate merge(BankCertificate detachedInstance) {
		log.debug("merging BankCertificate instance");
		try {
			BankCertificate result = (BankCertificate) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BankCertificate instance) {
		log.debug("attaching dirty BankCertificate instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BankCertificate instance) {
		log.debug("attaching clean BankCertificate instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BankCertificateDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (BankCertificateDAO) ctx.getBean("BankCertificateDAO");
	}
}