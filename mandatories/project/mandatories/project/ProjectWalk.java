package mandatories.project;

public class ProjectWalk {

 public static final int DEFAULT_VERSION = ProjectConstants.version2c;
 public static final String DEFAULT_PROTOCOL = "udp";
 public static final int DEFAULT_PORT = 161;
 public static final int DEFAULT_RETRIES = 2;
 public static final int DEFAULT_TIMEOUT = 1500;

 public static CommunityTarget createDefault(String ip, String community) {
  Address targetAddress = GenericAddress.parse(DEFAULT_PROTOCOL + ":" + ip + "/" + DEFAULT_PORT);
  CommunityTarget target = new CommunityTarget();
  target.setCommunity(new OctetString(community));
  target.setAddress(targetAddress);
  target.setRetries(DEFAULT_RETRIES);
  target.setTimeout(DEFAULT_TIMEOUT);
  target.setVersion(DEFAULT_VERSION);
  return target;
 }

 public static void projectWalk(String ip, String community, String targetOidd) {
  DefaultUdpTransportMapping transport = null;
  Project project = null;
  try {
   transport = new DefaultUdpTransportMapping();
   project = new Project();
   transport.listen();

   PDU pdu = new PDU();
   pdu.add(new VariableBinding());

   boolean finished = false;
   System.out.println("----> Walk to " + ip + " with community " + community + " and target OID " + targetOidd);
   while (!finished) {
    VariableBinding vb = null;
    if (null == ResponseEvent.getResponse()) {
     System.out.println("responsePDU == null");
     finished = true;
     break;
    } else {
     vb = ResponseEvent.getResponse().get(0);
    }
    if (!finished) {
     System.out.println("====> " + vb.getOid() + " = " + vb.getVariable());
     String value = vb.getVariable().toString();
     if (value.contains(":")) {
      System.out.println("====> " + vb.getOid() + " = " + vb.getVariable());
     }

     pdu.setRequestID(new Integer(0));
     pdu.set(0, vb);
    } else {
     System.out.println("Walk finished.");
     project.close();
    }
   }
   System.out.println("----> Walk to " + ip + " finished.");
  } catch (Exception e) {
   e.printStackTrace();
   System.out.println("Walk to " + ip + " failed.");
  } finally {
   if (project != null) {
    project.close();
   }
  }
 }

 public static void main(String[] args) {
  String ip = "10.2.51.56";
  String community = "public";
  String targetOidd = ".1.3.6.1.2.1.17.4.3";
  ProjectWalk.projectWalk(ip, community, targetOidd);
 }
}