package net.vince.gists;


import io.vavr.collection.Queue;
import io.vavr.control.Option;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

@NoArgsConstructor
public class Cluster {

  @Getter
  private Queue<Server> servers = Queue.empty();
  private Queue<Server> rebootedServers = Queue.empty();
  private Option<Server> rebooting = Option.none();

  public Cluster register(Server newServer) {

    var newCluster = new Cluster();

    if (servers.isEmpty() && rebooting.isDefined()) {
      newCluster.servers = rebootedServers.append(newServer);
      newCluster.rebootedServers = Queue.empty();
      newCluster.rebooting = Option.none();
    } else if (rebooting.isDefined()) {
      var dequeue = servers.dequeue();
      newCluster.rebooting = Option.of(dequeue._1);
      newCluster.rebootedServers = rebootedServers.append(newServer);
      newCluster.servers = dequeue._2;
    } else {
      newCluster.servers = servers.append(newServer);
    }

    return newCluster;

  }

  public Cluster reboot() {
    var newCluster = new Cluster();

    var dequeue = servers.dequeue();
    newCluster.rebooting = Option.of(dequeue._1);
    newCluster.servers = dequeue._2;
    newCluster.rebootedServers = rebootedServers;

    return newCluster;
  }

  @Value
  public static class Server {
    private String name;
  }

}
