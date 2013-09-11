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
 * ChargeRecord entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see org.heatmanagment.hibernate.domain.ChargeRecord
 * @author MyEclipse Persistence Tools
 */

public class ChargeRecordDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(ChargeRecordDAO.class);
	// property constants
	public static final String ACCRANGEID = "accrangeid";
	public static final String MONEY = "money";
	public static final String CHGTYPE = "chgtype";
	public static final String CHECKNUM = "checknum";
	public static final String RCDPIC = "rcdpic";
	public static final String CHGYEAR = "chgyear";
	public static final String CHARGERID = "chargerid";
	public static final String FINANCECHECKER = "financechecker";
	public static final String CID = "cid";
	public static final String COMM = "comm";

	protected void initDao() {
		// do nothing
	}

	public void save(ChargeRecord transientInstance) {
		log.debug("saving ChargeRecord instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ChargeRecord persistentInstance) {
		log.debug("deleting ChargeRecord instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ChargeRecord findById(java.lang.Long id) {
		log.debug("getting ChargeRecord instance with id: " + id);
		try {
			ChargeRecord instance = (ChargeRecord) getHibernateTemplate().get(
					"org.heatmanagment.hibernate.domain.ChargeRecord", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ChargeRecord> findByExample(ChargeRecord instance) {
		log.debug("finding ChargeRecord instance by example");
		try {
			List<ChargeRecord> results = (List<ChargeRecord>) getHibernateTemplate()
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
		log.debug("finding ChargeRecord instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ChargeRecord as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ChargeRecord> findByAccrangeid(Object accrangeid) {
		return findByProperty(ACCRANGEID, accrangeid);
	}

	public List<ChargeRecord> findByMoney(Object money) {
		return findByProperty(MONEY, money);
	}

	public List<ChargeRecord> findByChgtype(Object chgtype) {
		return findByProperty(CHGTYPE, chgtype);
	}

	public List<ChargeRecord> findByChecknum(Object checknum) {
		return findByProperty(CHECKNUM, checknum);
	}

	public List<ChargeRecord> findByRcdpic(Object rcdpic) {
		return findByProperty(RCDPIC, rcdpic);
	}

	public List<ChargeRecord> findByChgyear(Object chgyear) {
		return findByProperty(CHGYEAR, chgyear);
	}

	public List<ChargeRecord> findByChargerid(Object chargerid) {
		return findByProperty(CHARGERID, chargerid);
	}

	public List<ChargeRecord> findByFinancechecker(Object financechecker) {
		return findByProperty(FINANCECHECKER, financechecker);
	}

	public List<ChargeRecord> findByCid(Object cid) {
		return findByProperty(CID, cid);
	}

	public List<ChargeRecord> findByComm(Object comm) {
		return findByProperty(COMM, comm);
	}

	public List findAll() {
		log.debug("finding all ChargeRecord instances");
		try {
			String queryString = "from ChargeRecord";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findPage(final int start, final int limit) {
		log.debug("finding all ChargeRecord instances with boundary");
		try {
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String q = "from ChargeRecord";
					Query query = session.createQuery(q).setFirstResult(start)
							.setMaxResults(limit);
					return query.list();
				}
			});
		} catch (RuntimeErrorException re) {
			log.error("find all ChargeRecord with boundary failed", re);
			throw re;
		}
	}

	public ChargeRecord merge(ChargeRecord detachedInstance) {
		log.debug("merging ChargeRecord instance");
		try {
			ChargeRecord result = (ChargeRecord) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ChargeRecord instance) {
		log.debug("attaching dirty ChargeRecord instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ChargeRecord instance) {
		log.debug("attaching clean ChargeRecord instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ChargeRecordDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ChargeRecordDAO) ctx.getBean("ChargeRecordDAO");
	}
}