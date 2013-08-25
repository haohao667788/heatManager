package org.heatmanagment.hibernate.domain;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * UsersInfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see org.heatmanagment.hibernate.domain.UsersInfo
 * @author MyEclipse Persistence Tools
 */

public class UsersInfoDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(UsersInfoDAO.class);
	// property constants
	public static final String USERTYPE = "usertype";
	public static final String ADDRESS = "address";
	public static final String USRNAME = "usrname";
	public static final String PHONE = "phone";
	public static final String CONTRACTTYPE = "contracttype";
	public static final String CONTRACTVER = "contractver";
	public static final String CONTRACTPIC = "contractpic";
	public static final String IDPIC = "idpic";
	public static final String HOUSEIDPIC = "houseidpic";
	public static final String HOUSEPIC = "housepic";
	public static final String COMM = "comm";

	public void save(UsersInfo transientInstance) {
		log.debug("saving UsersInfo instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(UsersInfo persistentInstance) {
		log.debug("deleting UsersInfo instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UsersInfo findById(java.lang.Long id) {
		log.debug("getting UsersInfo instance with id: " + id);
		try {
			UsersInfo instance = (UsersInfo) getSession().get(
					"org.heatmanagment.hibernate.domain.UsersInfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<UsersInfo> findByExample(UsersInfo instance) {
		log.debug("finding UsersInfo instance by example");
		try {
			List<UsersInfo> results = (List<UsersInfo>) getSession()
					.createCriteria(
							"org.heatmanagment.hibernate.domain.UsersInfo")
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
		log.debug("finding UsersInfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from UsersInfo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<UsersInfo> findByUsertype(Object usertype) {
		return findByProperty(USERTYPE, usertype);
	}

	public List<UsersInfo> findByAddress(Object address) {
		return findByProperty(ADDRESS, address);
	}

	public List<UsersInfo> findByUsrname(Object usrname) {
		return findByProperty(USRNAME, usrname);
	}

	public List<UsersInfo> findByPhone(Object phone) {
		return findByProperty(PHONE, phone);
	}

	public List<UsersInfo> findByContracttype(Object contracttype) {
		return findByProperty(CONTRACTTYPE, contracttype);
	}

	public List<UsersInfo> findByContractver(Object contractver) {
		return findByProperty(CONTRACTVER, contractver);
	}

	public List<UsersInfo> findByContractpic(Object contractpic) {
		return findByProperty(CONTRACTPIC, contractpic);
	}

	public List<UsersInfo> findByIdpic(Object idpic) {
		return findByProperty(IDPIC, idpic);
	}

	public List<UsersInfo> findByHouseidpic(Object houseidpic) {
		return findByProperty(HOUSEIDPIC, houseidpic);
	}

	public List<UsersInfo> findByHousepic(Object housepic) {
		return findByProperty(HOUSEPIC, housepic);
	}

	public List<UsersInfo> findByComm(Object comm) {
		return findByProperty(COMM, comm);
	}

	public List findAll() {
		log.debug("finding all UsersInfo instances");
		try {
			String queryString = "from UsersInfo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public UsersInfo merge(UsersInfo detachedInstance) {
		log.debug("merging UsersInfo instance");
		try {
			UsersInfo result = (UsersInfo) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(UsersInfo instance) {
		log.debug("attaching dirty UsersInfo instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UsersInfo instance) {
		log.debug("attaching clean UsersInfo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}