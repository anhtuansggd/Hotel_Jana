package Controllers;

import Modules.Notification;
import junit.framework.TestCase;

public class NotificationControllerTest extends TestCase {

    public void testAdd() {
        Notification notification = new Notification(6,"ok");

        NotificationController nc = new NotificationController();

        nc.add(notification);
    }

    public void testUpdate() {
    }

    public void testDelete() {
    }
}