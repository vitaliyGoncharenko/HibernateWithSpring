package goncharenko.GVV.dao;

import goncharenko.GVV.entity.Contact;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Vitaliy on 09.02.2016.
 */
@Transactional
@Repository("contactDao")
public class ContactDaoImpl implements ContactDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContactDaoImpl.class);
    private SessionFactory sessionFactory;

    @Transactional(readOnly = true)
    public List<Contact> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Contact с").list();
    }

    @Transactional(readOnly = true)
    public List<Contact> findAllWithDetail() {
        return sessionFactory.getCurrentSession().getNamedQuery("Contact.findAllWithDetail").list();
    }

    @Transactional(readOnly = true)
    public Contact findById(Long id) {
        return (Contact)sessionFactory.getCurrentSession().
                getNamedQuery("Contact.findByid").setParameter("id",id).uniqueResult();
    }

    public Contact save(Contact contact) {
        sessionFactory.getCurrentSession().saveOrUpdate(contact);
        LOGGER.info("Contact save with id - "+ contact.getId());
        return contact;
    }

    public void delete(Contact contact) {
        sessionFactory.getCurrentSession().delete(contact);
        LOGGER.info("Delete contact");
    }

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
