package mandatories.project;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ProjectWalkAsyn {

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
  Project project = null;
  try {
   System.out.println("Start to walk...");

   project = new Project(null, null);
   project.listen();

   final PDU pdu = new PDU();
   final CountDownLatch latch = new CountDownLatch(1);
   pdu.add(new VariableBinding(null));

   ResponseListener listener = new ResponseListener() {
   };
   project.send(pdu, target, null, listener);
   System.out.println("Waiting for results...");

   boolean wait = latch.await(30, TimeUnit.SECONDS);
   System.out.println("Waiting finished: " + wait);
   project.close();

   System.out.println("End of walk.");
  } catch (Exception e) {
   e.printStackTrace();
   System.out.println("Walk Stop: " + e);
  }
 }

 boolean finished = false;

 public ProjectWalkAsyn() {
  new CountDownLatch(1);
 }

 public void walk(String ip, String community, String oid) {
  final CommunityTarget target = createDefault(ip, community);
  Project project = null;
  try {
   System.out.println("Start to walk...");

   project = new Project(null, null);
   project.listen();

   final PDU pdu = new PDU();
   final CountDownLatch latch = new CountDownLatch(1);
   pdu.add(new VariableBinding(null));

   ResponseListener listener = new ResponseListener() {
   };
   project.send(pdu, target, null, listener);
   System.out.println("Waiting for results...");

   boolean wait = latch.await(30, TimeUnit.SECONDS);
   System.out.println("Waiting finished: " + wait);
   project.close();

   System.out.println("End of walk.");
  } catch (Exception e) {
   e.printStackTrace();
   System.out.println("Walk Stop: " + e);
  }
 }

 public static void main(String[] args) {
  ProjectWalkAsyn walk = new ProjectWalkAsyn();
  walk.walk("", null, null);
 }
}
