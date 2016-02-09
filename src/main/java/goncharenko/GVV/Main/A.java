package goncharenko.GVV.Main;

import goncharenko.GVV.dao.ContactDao;
import goncharenko.GVV.entity.Contact;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

/**
 * Created by Vitaliy on 09.02.2016.
 */
public class A {
    public static void main (String [] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("app-context-annotation.xml");
        ctx.refresh();
        ContactDao contactDao = ctx.getBean("contactDao", ContactDao.class);
        listContacts(contactDao.findAll());
    }
    private static void listContacts(List<Contact> contacts) {
        System.out.println("");
        System.out.println("Listing contacts without details:");
        for (Contact contact : contacts) {
            System.out.println(contact);
            System.out.println();
        }
    }
}
