import finalproject.wrappers.IRequestWrapper;
import finalproject.command.*;
import finalproject.dao.DAOFactory;
import finalproject.dao.PaymentDAO;
import finalproject.dao.ServiceDAO;
import finalproject.dao.UserDAO;
import finalproject.entity.Payment;
import finalproject.entity.services.IPTelefony;
import finalproject.entity.services.Internet;
import finalproject.entity.services.Service;
import finalproject.entity.services.Television;
import finalproject.entity.services.tarifs.IPTelefonyTarif;
import finalproject.entity.services.tarifs.InternetTarif;
import finalproject.entity.services.tarifs.TelevisionTarif;
import finalproject.entity.users.Abonent;
import finalproject.entity.users.User;
import finalproject.wrappers.TestRequestWrapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;


import org.junit.runners.JUnit4;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RunWith(JUnit4.class)
public class Test {

    private IRequestWrapper wrapper;

    @Before
    public void init() {
        wrapper = new TestRequestWrapper();
    }

    private void login() {
        wrapper.setAttribute("login", "vadim7");
        wrapper.setAttribute("password", "vadim");
        Command command = new LoginCommand();
        command.execute(wrapper, true);
    }

    @org.junit.Test
    public void testSuccessLogin() {
        wrapper.setAttribute("login", "vadim7");
        wrapper.setAttribute("password", "vadim");
        Command command = new LoginCommand();
        command.execute(wrapper, true);
        System.out.println(wrapper.getSession().getAttribute("services"));
        User user = (User) wrapper.getSession().getAttribute("user");
        Assert.assertNotNull(user);
    }

    @org.junit.Test
    public void testAddService() {
        login();
        User user = (User) wrapper.getSession().getAttribute("user");
        Map<Integer, Service> serviceMap = (Map<Integer, Service>) wrapper.getSession().getAttribute("services");

        if (!serviceMap.containsKey(3)) {
            DAOFactory factory = DAOFactory.getInstance();
            factory.setTest(true);
            ServiceDAO serviceDAO = factory.getServiceDAO();

            wrapper.setAttribute("id", "3");
            wrapper.setAttribute("name", "ip-telefony");
            wrapper.setAttribute("rouming-minutes", "1");
            wrapper.setAttribute("videocall", "on");
            Command command = new AddServiceCommand();
            command.execute(wrapper, true);

            IPTelefony telefony = serviceDAO.getIPTelefony(user);
            Assert.assertTrue(telefony.getRoumingMinutes() == 1 && telefony.isVideoCall());
        } else {
            System.out.println("Exists");
            Assert.assertTrue("This product exists in users services", true);
        }
    }

    @org.junit.Test
    public void removeServiceTest() {
        login();
        User user = (User) wrapper.getSession().getAttribute("user");
        Map<Integer, Service> serviceMap = (Map<Integer, Service>) wrapper.getSession().getAttribute("services");

        if (serviceMap.containsKey(1)) {
            int fistSize = serviceMap.size();
            wrapper.setAttribute("id", "1");
            Command command = new RemoveServiceCommand();
            command.execute(wrapper, true);
            int secondSize = serviceMap.size();
            System.out.println(fistSize + " " + secondSize);
            DAOFactory factory = DAOFactory.getInstance();
            factory.setTest(true);
            ServiceDAO serviceDAO = factory.getServiceDAO();
            Internet internet = serviceDAO.getInternet(user);
            Assert.assertTrue(internet == null && fistSize > secondSize);
        } else {
            Assert.assertTrue("This product isn't exists in user services", true);
        }
    }

    @org.junit.Test
    public void payServiceTest() {
        login();
        User user = (User) wrapper.getSession().getAttribute("user");
        int firstBalance = ((Abonent)user).getBalance();
        wrapper.setAttribute("service_id", "2");
        wrapper.setAttribute("price", "15");
        Command command = new PayServiceCommand();
        command.execute(wrapper, true);

        int secondBalance = ((Abonent)user).getBalance();
        System.out.println(firstBalance + " "  + secondBalance);
        Payment payment = null;
        DAOFactory factory = DAOFactory.getInstance();
        factory.setTest(true);
        PaymentDAO paymentDAO = factory.getPaymentDAO();
        List<Payment> payments = paymentDAO.getPaymentsFilter(true);

        for (Payment payment1 : payments) {
            if (payment1.getService().getId() == 2) {
                payment = payment1;
                break;
            }
        }

        Assert.assertTrue(firstBalance > secondBalance && payment != null && payment.isPaid());
    }

    @org.junit.Test
    public void updateBalanceTest() {
        login();
        User user = (User) wrapper.getSession().getAttribute("user");
        wrapper.setAttribute("update_balance", "50");
        int firstBalance = ((Abonent)user).getBalance();
        Command command = new FillUpBalanceCommand();
        command.execute(wrapper, true);
        int secondBalance = ((Abonent)user).getBalance();
        System.out.println(firstBalance + " " + secondBalance);
        Assert.assertTrue(firstBalance + 50 == secondBalance);
    }

    @org.junit.Test
    public void registerTest() {
        wrapper.setAttribute("name", "Sergey");
        wrapper.setAttribute("login", "sergey7");
        wrapper.setAttribute("password", "s123456");
        Command command = new RegisterCommand();
        command.execute(wrapper, true);
        DAOFactory factory = DAOFactory.getInstance();
        factory.setTest(true);
        UserDAO userDAO = factory.getUserDAO();
        User user = userDAO.getUser("sergey7");
        Assert.assertTrue(user != null && user.getPassword().equals("s123456"));
    }

    @org.junit.Test
    public void allUsersTest() {
        Command command = new ViewAllUsersAdminCommand();
        command.execute(wrapper, true);
        List<User> users = (List<User>) wrapper.getSession().getAttribute("all users");
        Assert.assertTrue(users != null && users.size() > 0);
    }

    @org.junit.Test
    public void unpaidPayments() {
        wrapper.setAttribute("status", "false");
        Command command = new ViewPaymentsAdminCommand();
        command.execute(wrapper, true);
        List<Payment> payments = (List<Payment>) wrapper.getSession().getAttribute("all payments");

        for (Payment payment : payments) {
            Assert.assertTrue(!payment.isPaid());
        }

    }

    @org.junit.Test
    public void myAccountCommandTest() {
        login();
        Command command = new ViewMyAccountCommand();
        String page = command.execute(wrapper, true);
        Assert.assertEquals(page,"abonent-page/abonentPage.jsp");
    }

    @org.junit.Test
    public void mainPageCommandTest() {
        DAOFactory factory = DAOFactory.getInstance();
        factory.setTest(true);
        ServiceDAO serviceDAO = factory.getServiceDAO();
        List<Service> services = serviceDAO.getServices();
        Assert.assertTrue(services.size() == 4);
    }

    @org.junit.Test
    public void exitCommandTest() {
        login();
        HttpSession session = wrapper.getSession();
        Assert.assertTrue(session.getAttribute("user") != null && session.getAttribute("services") != null && session.getAttribute("admin") != null);
        Command command = new ExitCommand();
        command.execute(wrapper, true);
        Assert.assertTrue(session.getAttribute("user") == null && session.getAttribute("services") == null && session.getAttribute("admin") == null);
    }

    @org.junit.Test
    public void abonentPaymentsTest() {
        login();
        Command command = new AbonentPaymentsCommand();
        command.execute(wrapper, true);
        List<Payment> payments = (List<Payment>) wrapper.getSession().getAttribute("my payments");
        Assert.assertTrue(payments != null && payments.size() > 0);
    }

    @org.junit.Test
    public void blockUserTest() {
        wrapper.setAttribute("id", "3");
        wrapper.setAttribute("submit", "Block user");
        Command command = new BlockUserCommand();
        command.execute(wrapper, true);
        DAOFactory factory = DAOFactory.getInstance();
        factory.setTest(true);
        UserDAO userDAO = factory.getUserDAO();
        List<User> users = userDAO.getNoAdminUsers();
        User user = null;

        for (User user1 : users) {
            if (user1.getId() == 3) {
                user = user1;
            }
        }

        Assert.assertTrue(user != null && ((Abonent)user).isBlocked());
    }

    @org.junit.Test
    public void changeLanguageTest() {
        wrapper.setAttribute("this_path", "");
        wrapper.setAttribute("language", "ru-RU");
        Command command = new ChangeLanguageCommand();
        command.execute(wrapper, true);
        String language = (String) wrapper.getSession().getAttribute("language");
        Assert.assertEquals(language, "ru-RU");
    }

    @org.junit.Test
    public void tarifTelevisionTest() {
        Television television = new Television();
        television.setCountChanels(14);
        television.setTarif(new TelevisionTarif());
        Assert.assertEquals(television.getPrice(), 42);
    }

    @org.junit.Test
    public void tarifInternetTest() {
        Internet internet = new Internet();
        internet.setSpeed(100);
        internet.setTarif(new InternetTarif());
        Assert.assertEquals(internet.getPrice(), 500);
    }

    @org.junit.Test
    public void tarifIPTelefonyTest() {
        IPTelefony telefony = new IPTelefony();
        telefony.setRoumingMinutes(1);
        telefony.setVideoCall(true);
        telefony.setTarif(new IPTelefonyTarif());
        Assert.assertEquals(telefony.getPrice(), 46);
    }

}
