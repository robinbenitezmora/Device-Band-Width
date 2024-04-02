package mandatories.project;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Vector;

public class ProjectTrapMultiThreadReceiver implements CommandResponder {
 private Project project = null;

 public ProjectTrapMultiThreadReceiver() {
 }

 private void init() throws UnknownHostException, IOException {
  ThreadPool.create("TrapPool", 2);
  new MultiThreadedMessageDispatcher(null, null);
  Project project = new Project(null, null); // Change the variable type to Project
  ((Project) project.getMessageDispatcher()).addMessageProcessingModel(new MPv1()); // Replace 'project' with 'snmp'
  ((Project) project.getMessageDispatcher()).addMessageProcessingModel(new MPv2c()); // Replace 'project' with 'snmp'
  ((Project) project.getMessageDispatcher()).addMessageProcessingModel(new MPv3()); // Replace 'project' with 'snmp'
  USM usm = new USM();
  ((Project) project.getMessageDispatcher()).addSecurityModel(usm); // Cast the getMessageDispatcher() method to the
                                                                    // appropriate type
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

 public void processPdu(CommandResponderEvent event) {
  System.out.println("----> ResponderEvent: <----");
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