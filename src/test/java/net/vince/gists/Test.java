package net.vince.gists;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import net.vince.gists.Cluster.Server;

public class Test {

  @org.junit.jupiter.api.Test
  public void test() {

    var cluster = new Cluster();

    cluster = cluster.register(new Server("1"));

    assertFalse(cluster.getServers().isEmpty());
    assertEquals("1", cluster.getServers().dequeue()._1.getName());
    assertTrue(cluster.getServers().dequeue()._2.isEmpty());

    cluster = cluster.register(new Server("2"));

    assertFalse(cluster.getServers().isEmpty());
    assertEquals("1", cluster.getServers().dequeue()._1.getName());
    assertEquals("2", cluster.getServers().dequeue()._2.dequeue()._1.getName());
    assertTrue(cluster.getServers().dequeue()._2.dequeue()._2.isEmpty());

    cluster = cluster.register(new Server("3"));

    assertFalse(cluster.getServers().isEmpty());
    assertEquals("1", cluster.getServers().dequeue()._1.getName());
    assertEquals("2", cluster.getServers().dequeue()._2.dequeue()._1.getName());
    assertEquals("3", cluster.getServers().dequeue()._2.dequeue()._2.dequeue()._1.getName());
    assertTrue(cluster.getServers().dequeue()._2.dequeue()._2.dequeue()._2.isEmpty());

    cluster = cluster.reboot();

    assertFalse(cluster.getServers().isEmpty());
    assertEquals("2", cluster.getServers().dequeue()._1.getName());
    assertEquals("3", cluster.getServers().dequeue()._2.dequeue()._1.getName());
    assertTrue(cluster.getServers().dequeue()._2.dequeue()._2.isEmpty());

    cluster = cluster.register(new Server("1"));

    assertFalse(cluster.getServers().isEmpty());
    assertEquals("3", cluster.getServers().dequeue()._1.getName());
    assertTrue(cluster.getServers().dequeue()._2.isEmpty());

    cluster = cluster.register(new Server("2"));

    assertTrue(cluster.getServers().isEmpty());

    cluster = cluster.register(new Server("3"));

    assertFalse(cluster.getServers().isEmpty());
    assertEquals("1", cluster.getServers().dequeue()._1.getName());
    assertEquals("2", cluster.getServers().dequeue()._2.dequeue()._1.getName());
    assertEquals("3", cluster.getServers().dequeue()._2.dequeue()._2.dequeue()._1.getName());
    assertTrue(cluster.getServers().dequeue()._2.dequeue()._2.dequeue()._2.isEmpty());

  }
}
