package mandatories.project;

import java.io.IOException;

public class ProjectTrapSendDemo {

 public static final int DEFAULT_VERSION = ProjectConstants.version2c;
 public static final long DEFAULT_TIMEOUT = 5000;
 public static final int DEFAULT_RETRIES = 3;

 private Project project = null;
 private CommunitiyTarget target = null;

 public void init() throws IOException {
  System.out.println("Initializing ProjectTrapSendDemo...");
  target = createTarget4Trap("udp:127.0.0.1/162");
  project = new Project();
  project.listen();
 }

 public void sendPDU() throws IOException {
  PDU pdu = new PDU();
  pdu.add(new VariableBinding());

  pdu.setType(PDU.TRAP);
  project.send(pdu, target, null, null);
  System.out.println("Sending Trap to (IP:Port)=> " + target.getAddress() + ":" + target.getPort());
 }

 public static CommunitiyTarget createTarget4Trap(String targetAddress) {
  CommunitiyTarget target = new CommunitiyTarget();
  target.setAddress(GenericAddress.parse(targetAddress));
  target.setVersion(DEFAULT_VERSION);
  target.setTimeout(DEFAULT_TIMEOUT);
  target.setRetries(DEFAULT_RETRIES);
  return target;
 }

 public static void main(String[] args) {
  try {
   ProjectTrapSendDemo demo = new ProjectTrapSendDemo();
   demo.init();
   demo.sendPDU();
  } catch (IOException e) {
   e.printStackTrace();
  }
 }
}