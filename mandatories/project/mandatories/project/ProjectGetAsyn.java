package mandatories.project;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ProjectGetAsyn {
 public static final String DEFAULT_PROTOCOL = "udp";
 public static final int DEFAULT_PORT = 161;
 public static final int DEFAULT_RETRIES = 2;
 public static final long DEFAULT_TIMEOUT = 1500;
 public static final int DEFAULT_VERSION = ProjectConstants.version2c;

 public static CommunitiyTarget createDefault(String ip, String community) {
  Address targetAddress = GenericAddress.parse(DEFAULT_PROTOCOL + ":" + ip + "/" + DEFAULT_PORT);
  CommunitiyTarget target = new CommunitiyTarget();
  target.setCommunity(new OctetString(community));
  target.setAddress(targetAddress);
  target.setRetries(DEFAULT_RETRIES);
  target.setTimeout(DEFAULT_TIMEOUT);
  target.setVersion(DEFAULT_VERSION);
  return target;
 }

 @SuppressWarnings("unused")
 public static void projectAsyinGetList(String ip, String community, List<String> oidList) {
  CommunitiyTarget target = createDefault(ip, community);
  Project project = null;
  try {
   DefaultUdpTransportMapping transport = new DefaultUdpTransportMapping();
   project = new Project(null, transport);
   project.listen();

   PDU pdu = new PDU();
   for (String oid : oidList) {
    pdu.add(new VariableBinding(null));
   }

   final CountDownLatch latch = new CountDownLatch(1);
   ResponseListener listener = new ResponseListener() {
   };

   pdu.setType(PDU.GET);
   project.send(pdu, target, null, listener);
   System.out.println("asyn send pdu wait for response...");

   boolean wait = latch.await(30, TimeUnit.SECONDS);
   System.out.println("latch.await =:" + wait);

   project.close();
  } catch (Exception e) {
   e.printStackTrace();
   System.out.println("Project Asyn GetList is error:" + e);
  }
 }

 public static void main(String[] args) {
  String ip = "127.0.0.1";
  String community = "public";

  List<String> oidList = new ArrayList<String>();
  oidList.add(".1.3.6.1.2.1.1.1.0");
  oidList.add(".1.3.6.1.2.1.1.3.0");
  oidList.add(".1.3.6.1.2.1.1.5.0");

  ProjectGetAsyn.projectAsyinGetList(ip, community, oidList);
 }
}
