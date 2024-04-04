package mandatories.unused;

import java.io.IOException;
import java.sql.Date;

import mandatories.project.PDU;
import mandatories.project.ProjectGet;

public class Record {
 int index;
 long inoctects;
 long outoctets;
 long speed;
 Date date;
 String ip;
 double in_bw;
 double out_bw;
 String port;
 double data_in_mb;
 double data_out_mb;
 double data_datarate_in_mbps;
 double data_datarate_out_mbps;

 static String oid_inoctets;
 static String oid_outoctets;
 static String community = "public";
 static String oid_IFindexes = "1.3.6.1.2.1.2.2.1.1";
 static String oid_speed = "1.3.6.1.2.1.2.2.1.5.";
 static String oid_status = "1.3.6.1.2.1.2.2.1.8.";
 static String oid_interface_dscr = "1.3.6.1.2.1.2.2.1.2.";
 static String oid_basic_inoctetes = "1.3.6.1.2.1.2.2.1.10.";
 static String oid_basic_outoctetes = "1.3.6.1.2.1.2.2.1.16.";
 static String oid_HCInoctetes = " 1.3.6.1.2.1.31.1.1.1.6.";
 static String oid_HCOutoctetes = " 1.3.6.1.2.1.31.1.1.1.10.";
 public static String oid_inoctetes;
 public static String oid_outoctetes;
 public static int inoctets;

 Record(String ip, String port, String community) throws IOException {
  this.ip = ip;
  this.port = port;

  date = new Date(inoctects);

  String InOctets_oidval = oid_inoctets + port;
  PDU In_resp_from_get = ProjectGet.projectGet(ip, community, InOctets_oidval);
  inoctects = ProjectGet.getPDUvalue(In_resp_from_get);

  String OutOctets_oidval = oid_outoctets + port;
  PDU Out_resp_from_get = ProjectGet.projectGet(ip, community, OutOctets_oidval);
  outoctets = ProjectGet.getPDUvalue(Out_resp_from_get);

  String IfSpeed_oidval = oid_speed + port;
  PDU Speed_resp_from_get = ProjectGet.projectGet(ip, community, IfSpeed_oidval);
  speed = ProjectGet.getPDUvalue(Speed_resp_from_get);
 }

 Record(Record r) {
  this.ip = r.ip;
  this.data_datarate_in_mbps = r.data_datarate_in_mbps;
  this.data_datarate_out_mbps = r.data_datarate_out_mbps;
  this.data_in_mb = r.data_in_mb;
  this.data_out_mb = r.data_out_mb;
  this.date = r.date;
  this.in_bw = r.in_bw;
  this.inoctects = r.inoctects;
  this.out_bw = r.out_bw;
  this.outoctets = r.outoctets;
  this.port = r.port;
  this.speed = r.speed;
  this.index = r.index;
 }
}
