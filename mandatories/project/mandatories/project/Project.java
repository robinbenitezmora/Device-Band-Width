package mandatories.project;

public class Project {

 public void listen() {
 }

 public void cancel(Object request, ResponseListener responseListener) {
 }

 public void close() {
 }

 public Object getMessageDispatcher() {
  return null;
 }

 public void addCommandResponder(ProjectTrapMultiThreadReceiver projectTrapMultiThreadReceiver) {
 }

 public void addMessageProcessingModel(MPv2c mPv2c) {
 }

 public void addMessageProcessingModel(MPv1 mPv1) {
 }

 public void addMessageProcessingModel(MPv3 mPv3) {
 }

 public void addSecurityModel(USM usm) {

 }

 public void send(PDU pdu, CommunityTarget target, Object object, ResponseListener listener) {
 }

 public void send(PDU pdu, CommunitiyTarget target, Object object, ResponseListener listener) {
  // TODO Auto-generated method stub
  throw new UnsupportedOperationException("Unimplemented method 'send'");
 }

}
