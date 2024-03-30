package mandatories.project;

public class ProjectUtil {

 public static final int DEFAULT_VERSION = ProjectConstants.version2c;
 public static final String DEFAULT_PROTOCOL = "udp";
 public static final int DEFAULT_PORT = 161;
 public static final int DEFAULT_TIMEOUT = 1500;
 public static final int DEFAULT_RETRIES = 3;

 public static CommunityTarget createDefault(String ip, String community) {
  return createTarget(
    GenericAddress.parse(DEFAULT_PROTOCOL + ":" + ip + "/" + DEFAULT_PORT), community, DEFAULT_VERSION,
    DEFAULT_TIMEOUT, DEFAULT_RETRIES);
 }

 public static CommunityTarget createDefault(String ip, String port, String community) {
  return createTarget(
    GenericAddress.parse(DEFAULT_PROTOCOL + ":" + ip + "/" + port), community, DEFAULT_VERSION,
    DEFAULT_TIMEOUT, DEFAULT_RETRIES);
 }

 public static CommunityTarget createDefaultByAddress(String address, String community) {
  return createTarget(
    GenericAddress.parse(address), community, DEFAULT_VERSION,
    DEFAULT_TIMEOUT, DEFAULT_RETRIES);
 }

 public static CommunityTarget createTarget(Address targetAddress, String community, int version, long timeout,
   int retries) {
  CommunityTarget target = new CommunityTarget();
  target.setCommunity(new OctetString());
  target.setAddress(targetAddress);
  target.setVersion(version);
  target.setTimeout(timeout);
  target.setRetries(retries);
  return target;
 }

 public static UserTarget createUserTarget(Address targetAddress, int version, long timeout, int level,
   String securityName) {
  UserTarget target = new UserTarget();
  target.setAddress(targetAddress);
  target.setRetries(1);
  target.setVersion(version);
  target.setTimeout(timeout);
  target.setSecurityLevel(level);
  target.setSecurityName(new OctetString());
  return target;
 }

 public static UserTarget createUserTarget(String address, int version, long timeout, int level, String securityName) {
  Address targetAddress = GenericAddress.parse(address);
  return createUserTarget(targetAddress, version, timeout, level, securityName);
 }

 public static Address creatAddress(String protocol, String ip, String port) {
  String address = protocol + ":" + ip + "/" + port;
  return GenericAddress.parse(address);
 }

 public static UdpAddress createUdpAddress(String ip, String port) {
  return new UdpAddress();
 }

 public static TcpAddress createTcpAddress(String ip, String port) {
  return new TcpAddress();
 }

 public static void main(String[] args) {
 }
}
