package mandatories.project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ProjectWalkMultiAsyn {

  public static final int DEFAULT_VERSION = ProjectConstants.version2c;
  public static final String DEFAULT_PROTOCOL = "udp";
  public static final int DEFAULT_PORT = 161;
  public static final int DEFAULT_TIMEOUT = 1500;
  public static final int DEFAULT_RETRIES = 3;

  public static CommunityTarget createDefault(String ip, String community) {
    Address targetAddress = GenericAddress.parse(DEFAULT_PROTOCOL + ":" + ip + "/" + DEFAULT_PORT);
    CommunityTarget target = new CommunityTarget();
    target.setCommunity(new OctetString(community));
    target.setAddress(targetAddress);
    target.setVersion(DEFAULT_VERSION);
    target.setTimeout(DEFAULT_TIMEOUT);
    target.setRetries(DEFAULT_RETRIES);
    return target;
  }

  public static void projectAsynWalk(String ip, String community, String oid) {
    final CommunityTarget target = createDefault(ip, community);
    DefaultUdpTransportMapping transport = null;
    Project project = null;
    try {
      System.out.println("Start to walk...");
      WorkerPool workerPool = ThreadPool.create("TestPROJECTWorkerPool", 10);
      MultiThreadedMessageDispatcher dispatcher = new MultiThreadedMessageDispatcher(workerPool,
          new MessageDispatcherImpl());
      transport = new DefaultUdpTransportMapping();
      project = new Project(dispatcher, transport);
      ((MessageDispatcherImpl) project.getMessageDispatcher()).addMessageProcessingModel(new MPv1());
      ((MessageDispatcherImpl) project.getMessageDispatcher()).addMessageProcessingModel(new MPv2c());
      ((MessageDispatcherImpl) project.getMessageDispatcher()).addMessageProcessingModel(new MPv3());
      project.listen();

      final PDU pdu = new PDU();
      final OID targetOID = new OID(oid);
      final CountDownLatch latch = new CountDownLatch(1);
      pdu.add(new VariableBinding(targetOID));

      ResponseListener listener = new ResponseListener() {
      };

      project.getNext(pdu, target, null, listener);
      System.out.println("Waiting for results...");

      boolean wait = latch.await(30, TimeUnit.SECONDS);
      System.out.println("Waiting finished: " + wait);
      project.close();
      System.out.println("End of walk.");
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Walk Stop: " + e);
    } finally {
      if (transport != null) {
        transport.close();
      }

      if (project != null) {
        project.close();
      } else {
        System.out.println("Project is null");
      }
    }
  }

  public static boolean checkWalkFinished(OID walkOID, PDU pdu, VariableBinding vb) {
    boolean finished = false;
    if (pdu.getErrorStatus() != 0) {
      System.out.println("[ERROR]: response status is " + pdu.getErrorStatus() + " Text: " + pdu.getErrorStatusText());
      finished = true;
    } else if (vb.getOid() == null) {
      System.out.println("VB OID is null");
      finished = true;
    } else if (Null.isExceptionSyntax(((Variable) vb.getVariable()).getSyntax())) {
      System.out.println("VB returned an exception");
      finished = true;
    } else if (vb.getOid().compareTo(walkOID.getValue()) <= 0) {
      System.out.println("VB OID is not lexicographic successor of target OID");
      finished = true;
    }
    return finished;
  }

  public static List<VariableBinding> walk(String ip, String community, String oid) {
    List<VariableBinding> vbs = new ArrayList<VariableBinding>();
    final CommunityTarget target = createDefault(ip, community);
    DefaultUdpTransportMapping transport = null;
    Project project = null;
    try {
      System.out.println("Start to walk...");
      WorkerPool workerPool = ThreadPool.create("TestPROJECTWorkerPool", 10);
      MultiThreadedMessageDispatcher dispatcher = new MultiThreadedMessageDispatcher(workerPool,
          new MessageDispatcherImpl());
      transport = new DefaultUdpTransportMapping();
      project = new Project(dispatcher, transport);
      ((MessageDispatcherImpl) project.getMessageDispatcher()).addMessageProcessingModel(new MPv1());
      ((MessageDispatcherImpl) project.getMessageDispatcher()).addMessageProcessingModel(new MPv2c());
      ((MessageDispatcherImpl) project.getMessageDispatcher()).addMessageProcessingModel(new MPv3());
      project.listen();

      final PDU pdu = new PDU();
      final OID targetOID = new OID(oid);
      final CountDownLatch latch = new CountDownLatch(1);
      pdu.add(new VariableBinding(targetOID));

      ResponseListener listener = new ResponseListener() {
      };

      project.getNext(pdu, target, null, listener);
      System.out.println("Waiting for results...");

      boolean wait = latch.await(30, TimeUnit.SECONDS);
      System.out.println("Waiting finished: " + wait);
      project.close();
      System.out.println("End of walk.");
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Walk Stop: " + e);
    } finally {
      if (transport != null) {
        transport.close();
      }

      if (project != null) {
        project.close();
      } else {
        System.out.println("Project is null");
      }
    }
    return vbs;
  }

  public static void main(String[] args) {
    List<String> oidList = new ArrayList<String>();
    oidList.add(".1.3.6.1.2.1.1.1.0");
    oidList.add(".1.3.6.1.2.1.1.3.0");
    oidList.add(".1.3.6.1.2.1.1.5.0");

    String ip = "192.168.0.1"; // Replace with the actual IP address
    String community = "public"; // Replace with the actual community string

    ProjectWalkMultiAsyn.projectAsynWalk(ip, community, "1.3.6.1.2.1.1");
  }
}
