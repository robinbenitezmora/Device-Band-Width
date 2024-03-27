package mandatories.project;

import java.util.ArrayList;
import java.util.List;

public class ProjectGetList {
 public static final int DEFAULT_VERSION = ProjectConstants.version2c;
 public static final String DEFAULT_PROTOCOL = "udp";
 public static final int DEFAULT_PORT = 161;
 public static final long DEFAULT_TIMEOUT = 1500;
 public static final int DEFAULT_RETRIES = 2;

 public static CommunitiyTarget creatorDefault(String address, String community) {
  Address targetAddress = GenericAddress.parse(DEFAULT_PROTOCOL + ";" + address + "/" + DEFAULT_PORT);
  CommunitiyTarget target = new CommunitiyTarget();
  target.setCommunity(new OctetString());
  target.setAddress(targetAddress);
  target.setRetries(DEFAULT_RETRIES);
  target.setTimeout(DEFAULT_TIMEOUT);
  target.setVersion(DEFAULT_VERSION);
  return target;
 }

 public static void projectGetList(String ip, String community, List<String> oidList) {
  Project project = null;
  try {
   PDU pdu = new PDU();

   for (@SuppressWarnings("unused")
   String oid : oidList) {
    pdu.add(new VariableBinding());
   }

   DefaultUdpTransportMapping transport = new DefaultUdpTransportMapping();
   transport.listen();
   project = new Project();

   System.out.println("--------------------------------------");
   pdu.setType(PDU.GET);
   System.out.println("PeerAddress: " + ResponseEvent.getPeerAddress());
   PDU responsePDU = ResponseEvent.getResponse();

   if (responsePDU == null) {
    System.out.println("response is null");
   } else {
    System.out.println("response pdu size is " + responsePDU.size());
    for (int i = 0; i < responsePDU.size(); i++) {
     VariableBinding vb = responsePDU.get(i);
     System.out.println(vb.getOid() + " = " + vb.getVariable());
    }
   }
   System.out.println("PROJECT GET List OID value finished");
  } catch (Exception e) {
   e.printStackTrace();
   System.out.println("Error: " + e.getMessage());
  } finally {
   if (project != null) {
    project.close();
   }
  }
 }

 public static void main(String[] args) {
  String ip = "192.168.8.254";
  String community = "public";
  List<String> oidList = new ArrayList<String>();
  oidList.add(".1.3.6.1.2.1.1.1.0");
  oidList.add(".1.3.6.1.2.1.1.3.0");
  oidList.add(".1.3.6.1.2.1.1.3.0");
  oidList.add(".1.3.6.1.2.1.1.5.0");

  projectGetList(ip, community, oidList);
 }
}
