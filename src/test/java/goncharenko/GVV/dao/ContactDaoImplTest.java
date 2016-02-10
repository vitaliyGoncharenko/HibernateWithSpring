package goncharenko.GVV.dao;

import goncharenko.GVV.entity.Contact;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.List;

/**
 * Created by Vitaliy on 10.02.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:app-context-annotation.xml"})
public class ContactDaoImplTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContactDaoImplTest.class);

    @Autowired
    private ContactDao contactDao;

    //for testFindAll()
    private static String firstNameExpected = "Chris";
    private static String lastNameExpected = "Schaefer";
    private static Date birthDateExpected = new Date(1981 - 05 - 03);

    //for testFindById()
    private static Long id = 1L;

    //for save
    private static String firstNameSave = "ChrisSave";
    private static String lastNameSave = "SchaeferSave";
    private static Date birthDateSave = new Date(1983 - 05 - 03);

    @Test
    public void testFindAll() throws Exception {
        List<Contact> contacts = contactDao.findAll();
        Assert.assertEquals(firstNameExpected, contacts.get(0).getFirstName());
        Assert.assertEquals(lastNameExpected, contacts.get(0).getLastName());
        listContacts(contacts);
    }

    @Test
    public void testFindAllWithDetail() throws Exception {
        List<Contact> contacts = contactDao.findAllWithDetail();
        Assert.assertEquals(firstNameExpected, contacts.get(0).getFirstName());
        Assert.assertEquals(lastNameExpected, contacts.get(0).getLastName());
        Assert.assertEquals(lastNameExpected, contacts.get(0).getLastName());
        listContactsWithDetails(contacts);
    }

    @Test
    public void testFindById() throws Exception {
        Contact contact = contactDao.findById(id);
        Assert.assertEquals(firstNameExpected, contact.getFirstName());
        Assert.assertEquals(lastNameExpected, contact.getLastName());
        LOGGER.info("Get contact by id - " + id + " : " + contact);
    }

    @Test
    public void testSave() throws Exception {
        //save new contact
        Contact contact = new Contact();
        contact.setFirstName(firstNameSave);
        contact.setLastName(lastNameSave);
        contact.setBirthDate(birthDateSave);

        contactDao.save(contact);
        //check saved contact
        Contact actual = contactDao.findById(contact.getId());
        Assert.assertEquals(firstNameSave, actual.getFirstName());
        Assert.assertEquals(lastNameSave, contact.getLastName());

        contactDao.delete(actual);
    }

    @Test
    public void testDelete() throws Exception {
        //save new contact
        Contact contact = new Contact();
        contact.setFirstName(firstNameSave);
        contact.setLastName(lastNameSave);
        contact.setBirthDate(birthDateSave);
        contactDao.save(contact);
        // delete created contact
        contactDao.delete(contact);
    }

    private static void listContacts(List<Contact> contacts) {
        LOGGER.info("\nListing contacts without details:");

        for (Contact contact : contacts) {
            LOGGER.info("\n" + contact);
        }
    }

    private static void listContactsWithDetails(List<Contact> contacts) {
        LOGGER.info("\nListing contacts without details:");

        for (Contact contact : contacts) {
            LOGGER.info("\n" + contact);
            LOGGER.info("\n" + contact.getContactTelDetails());
            LOGGER.info("\n" + contact.getHobbies());
        }
    }
}