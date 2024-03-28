package mandatories.project;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Vector;

public class ProjectTrapMultiThreadReceiver implements CommandResponder {
 private Project project = null;
 private Address listenAddress;

 public ProjectTrapMultiThreadReceiver() {
 }

 private void init() throws UnknownHostException, IOException {
  ThreadPool.create("TrapPool", 2);
  new MultiThreadedMessageDispatcher();
  UdpAddress udpAddress = (UdpAddress) listenAddress;
  DefaultTcpTransportMapping transport;
  if (udpAddress != null) {
   transport = new DefaultUdpTransportMapping();
  } else {
   transport = new DefaultTcpTransportMapping();
  }
  project = new Project();
  project.getMessageDispatcher().addMessageProcessingModel(new MPv1()); // Replace 'project' with 'snmp'
  project.getMessageDispatcher().addMessageProcessingModel(new MPv2c()); // Replace 'project' with 'snmp'
  project.getMessageDispatcher().addMessageProcessingModel(new MPv3()); // Replace 'project' with 'snmp'
  USM usm = new USM();
  SecurityModels.getInstance().addSecurityModel(usm);
  project.listen();
 }

 public void run() {
  System.out.println("----> Trap Receiver run ... <----");
  try {
   init();
   project.addCommandResponder(this);
   System.out.println("----> Trap message  <----");
  } catch (Exception ex) {
   ex.printStackTrace();
  }
 }

 @SuppressWarnings("unchecked")
 public void processPdu(CommandResponderEvent event) {
  System.out.println("----> 开始解析ResponderEvent: <----");
  if (event == null || event.getPDU() == null) {
   System.out.println("[Warn] ResponderEvent or PDU is null");
   return;
  }
  Vector<VariableBinding> vbVect = ((PDU) event.getPDU()).getVariableBindings();
  for (VariableBinding vb : vbVect) {
   System.out.println(vb.getOid() + " = " + vb.getVariable());
  }
  System.out.println("----> ResponderEvent <----");
 }

 public static void main(String[] args) {
  ProjectTrapMultiThreadReceiver trapReceiver = new ProjectTrapMultiThreadReceiver();
  trapReceiver.run();
 }
}