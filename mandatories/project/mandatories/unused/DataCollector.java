package mandatories.unused;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.html.HTMLDocument.Iterator;

import mandatories.project.PDU;
import mandatories.project.ProjectGet;
import path.to.DataProcessor;

public class DataCollector {

  private static final long NULL_DATA_RECEIVED = 0;

  static void check_if_HC_octets_present(String ip, String port, String community) throws IOException {
    System.out.println("Checking the type of octets");

    System.out.println("First using HCOctets");
    String oid_HCInoctetes = " 1.3.6.1.2.1.31.1.1.1.6.";
    String oid_HCOutoctetes = " 1.3.6.1.2.1.31.1.1.1.10.";

    String InOctets_oidval = oid_HCInoctetes + port;
    PDU In_resp_from_get = ProjectGet.projectGet(ip, community, InOctets_oidval);
    long inoctects = ProjectGet.getPDUvalue(In_resp_from_get);

    String OutOctets_oidval = oid_HCOutoctetes + port;
    PDU Out_resp_from_get = ProjectGet.projectGet(ip, community, OutOctets_oidval);
    long outoctets = ProjectGet.getPDUvalue(Out_resp_from_get);
    System.out.println("Get value outoctets: " + outoctets + " inoctets: " + inoctects);

    String oid_inoctetes = "1.3.6.1.2.1.2.2.1.10.";
    String oid_outoctetes = "1.3.6.1.2.1.2.2.1.16.";

    Record.oid_inoctetes = oid_HCInoctetes;
    Record.oid_outoctetes = oid_HCOutoctetes;

    System.out.println("setting oid to inoctets: " + Record.oid_inoctetes + " outoctets: " + Record.oid_outoctetes);

    if (inoctects == NULL_DATA_RECEIVED && outoctets == NULL_DATA_RECEIVED) {
      System.out.println("Changing octets oid");

      InOctets_oidval = oid_inoctetes + port;
      In_resp_from_get = ProjectGet.projectGet(ip, community, InOctets_oidval);
      inoctects = ProjectGet.getPDUvalue(In_resp_from_get);

      OutOctets_oidval = oid_outoctetes + port;
      Out_resp_from_get = ProjectGet.projectGet(ip, community, OutOctets_oidval);
      outoctets = ProjectGet.getPDUvalue(Out_resp_from_get);

      Record.oid_inoctetes = oid_inoctetes;
      Record.oid_outoctetes = oid_outoctetes;

      System.out.println("Get value outoctets: " + outoctets + " inoctets: " + inoctects);
    }
  }

  static int startindex;
  static int endindex;

  static int no_of_slots = 100;
  static int size_record_queue = 10;
  static Record tmp_recording[][];

  static ArrayList<Integer> list_of_ports = new ArrayList<Integer>();
  static int tmp_index;
  static final int update_freq = 15;
  static final int calc_freq = 1;
  static final int data_store_freq = 1;
  static ArrayList<Record> data_collected_copy_for_gui = new ArrayList<Record>();
  static double current_inbw;
  static double current_outbw;
  static String mapping_table[][];
  static ArrayList<Integer> free_slots_list = new ArrayList<Integer>();
  static ArrayList<Integer> used_slots_list = new ArrayList<Integer>();
  static ArrayList<Integer> ports_list = new ArrayList<Integer>();
  static ArrayList<String> ip_list = new ArrayList<String>();
  static int index = 0;

  public static void add(String ip, Integer port, String community) {
    if (!ip_list.contains(ip) || !ports_list.contains(port)) {
      if (free_slots_list.size() != 0) {
        index = free_slots_list.get(0);
      } else {
        System.out.println("No free slots available");
      }
      free_slots_list.remove((Integer) index);
      used_slots_list.add((Integer) index);
      ports_list.add(port);
      ip_list.add(ip);
      mapping_table[index][0] = ip;
      mapping_table[index][1] = "" + port;
      mapping_table[index][2] = community;
    }
  }

  public static void remove(String ip, String port) {
    if (ip_list.contains(ip) && ports_list.contains(Integer.valueOf(port))) {
      for (int i : used_slots_list) {
        if (mapping_table[i][0].equals(ip) && mapping_table[i][1].equals(port)) {
          mapping_table[i] = null;
          used_slots_list.remove((Integer) i);
          free_slots_list.add((Integer) i);
          ports_list.remove(Integer.valueOf(port));
          ip_list.remove(ip);
          break;
        }
      }
    }
  }

  @SuppressWarnings("rawtypes")
  public static ArrayList get_current_recording() {
    System.out.println("sending arraylist of size: " + data_collected_copy_for_gui.size());
    return data_collected_copy_for_gui;
  }

  public static void copyArrayList() {
    try {
      Record[] data_collected = new Record[0]; // Initialize the data_collected array
      for (Record r : data_collected) {
        Record new_r = new Record(r);
        data_collected_copy_for_gui.add(new_r);
      }
    } catch (Exception ex) {
      System.out.println("Error in copying arraylist for gui Datacollector.copyArrayList()");
      ex.printStackTrace(System.out);
    }
  }

public static void clear_copyArrayList(Date last_shown){
    
  Iterator<Record> iter = data_collected_copy_for_gui.iterator();

  public static void addports(int port) {
    if (!list_of_ports.contains(port)) {
      list_of_ports.add(port);
    } else {
      System.out.println("The port you are trying to add is already added");
    }
  }

  static int recordindex = 0;

  // not used now
  public static void add_device(String ip, int port) {

    if (!list_of_ports.contains(port)) {
      list_of_ports.add(port);
    } else {
      System.out.println("The port you are trying to add is already added");
    }
  }

  public static void calc_bandwidth(Record[][] tmp_recordings, int start, int end) {
    int i, x, j;
    int loop;

    if (start < end) {
      loop = end - start;
    } else {
      loop = (size_record_queue - start) + end;
    }
    for (Integer p : used_slots_list) {

      for (j = 0, i = start + 1; j < loop; i++, j++) {

        if (tmp_recordings[((p - 1 + size_record_queue) % size_record_queue)] == null) {
          continue;
        }

        long delta_inoctets = (tmp_recordings[p].length
            - tmp_recordings[((p - 1 + size_record_queue) % size_record_queue)].length);
        long delta_outoctets = (tmp_recordings[p].length
            - tmp_recordings[((p - 1 + size_record_queue) % size_record_queue)].length);

        long ifspeed = tmp_recordings[p].length;
        long time_difference = (tmp_recordings[p].length
            - tmp_recordings[((p - 1 + size_record_queue) % size_record_queue)].length);
        long delta_seconds = TimeUnit.MILLISECONDS.toSeconds(time_difference);

        if (delta_seconds < update_freq) {
          delta_seconds = update_freq;
        }

        tmp_recordings[p][i % size_record_queue].data_in_mb = ((double) delta_inoctets) / (1024 * 1024);
        tmp_recordings[p][i % size_record_queue].data_out_mb = ((double) delta_outoctets) / (1024 * 1024);
        tmp_recordings[p][i
            % size_record_queue].data_datarate_in_mbps = (tmp_recordings[p][i % size_record_queue].data_in_mb
                / (double) delta_seconds) * 8;
        tmp_recordings[p][i
            % size_record_queue].data_datarate_out_mbps = (tmp_recordings[p][i % size_record_queue].data_out_mb
                / (double) delta_seconds) * 8;

        double in_num = (double) delta_inoctets * 8 * 100;
        double in_denom = (double) delta_seconds * ifspeed;
        double inbw = in_num / in_denom;

        double out_num = (double) delta_outoctets * 8 * 100;
        double out_denom = (double) delta_seconds * ifspeed;
        double outbw = out_num / out_denom;

        inbw = Math.floor(inbw * 100000) / 100000;
        outbw = Math.floor(outbw * 100000) / 100000;

        tmp_recordings[p][i % size_record_queue].in_bw = inbw;
        tmp_recordings[p][i % size_record_queue].out_bw = outbw;

        if (delta_inoctets >= 0 && delta_outoctets >= 0) {
          PDU data_collected;
          data_collected.add(tmp_recordings[p][i % size_record_queue]);
        }

        current_inbw = inbw;
        current_outbw = outbw;
      }

    }
  }

  public static void initialise_mapping() {
    mapping_table = new String[no_of_slots][3];
    used_slots_list.clear();
    free_slots_list.clear();
    for (int j = 0; j < no_of_slots; j++) {
      free_slots_list.add(j);
    }
  }

  public static String get_snmp_description(String ip, int port, String community) throws IOException {
    String oid = Record.oid_interface_dscr + port;
    PDU In_resp_from_get = ProjectGet.projectGet(ip, community, oid);
    String hexresponse = ProjectGet.getPDUStringvalue(In_resp_from_get);
    Pattern pattern = Pattern.compile("[0-9a-fA-f][0-9a-fA-f]:[0-9a-fA-f][0-9a-fA-f]");
    Matcher matcher = pattern.matcher(hexresponse);

    String description = hexresponse;

    if (matcher.find()) {
      description = "";
      String newhexstring = hexresponse.replaceAll(":", " ");
      String str[] = newhexstring.split(" ");
      for (String s : str) {
        int num = hex2decimal(s);
        description += (char) num;
      }
    }
    return description;
  }

  public static int hex2decimal(String s) {
    String digits = "0123456789ABCDEF";
    s = s.toUpperCase();
    int val = 0;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      int d = digits.indexOf(c);
      val = 16 * val + d;
    }
    return val;
  }

  public static String get_snmp_status(String ip, int port, String community) throws IOException {
    String status = "";
    String oid = Record.oid_status + port;
    PDU In_resp_from_get = ProjectGet.projectGet(ip, community, oid);
    long status_code = ProjectGet.getPDUvalue(In_resp_from_get);
    if (status_code == 1) {
      status = "up";
    }
    if (status_code == 2) {
      status = "down";
    }
    if (status_code == 3) {
      status = "testing";
    }
    if (status_code == 5) {
      status = "dormant";
    }
    if (status_code == 6) {
      status = "notpresent";
    }
    return status;
  }

  public static Double get_snmp_speed(String ip, int port, String community) throws IOException {
    String oid = Record.oid_speed + port;
    PDU In_resp_from_get = ProjectGet.projectGet(ip, community, oid);
    long speed_in_bps = ProjectGet.getPDUvalue(In_resp_from_get);
    Double speed = ((double) speed_in_bps) / (1024 * 1024);
    return speed;
  }

  static ScheduledExecutorService scheduler;

  public static void main(String[] args) throws IOException {
    System.out.println("new circular array based implementation");
    Timer timer = new Timer();
    int interval = update_freq;
    int delay = 0;

    initialise_mapping();

    add("127.0.0.1", 7, "public");
    add("127.0.0.1", 36, "public");
    add("127.0.0.1", 37, "public");
    add("127.0.0.1", 39, "public");
    add("127.0.0.1", 41, "public");
    add("127.0.0.1", 42, "public");
    add("127.0.0.1", 43, "public");
    add("127.0.0.1", 44, "public");
    add("127.0.0.1", 46, "public");
    add("127.0.0.1", 47, "public");

    repeatfunctions tt = new repeatfunctions();
    scheduler = Executors.newScheduledThreadPool(1);
    scheduler.scheduleWithFixedDelay(tt, delay, interval, TimeUnit.SECONDS);
  }

  static int time_sec = 0;
  static int timewrap = 2147483647 - (2147483647 % (update_freq * 3));
  static boolean initialise = true;
  static boolean pause = false;
  static Boolean initialise_in_octets_type = true;

  public static void initialise_data_collection() {
    try {
      if (initialise) {
        startindex = 0;
        endindex = -1;
        initialise_mapping();
        Record[][] tmp_recordings = new Record[no_of_slots][size_record_queue];
        System.out.println("Table created");
        initialise = false;
      }
    } catch (Exception ex) {
      System.out.println("Error in Initialising Datacollector.repeatfunction()");
      ex.printStackTrace();
    }
  }

  public static class repeatfunctions implements Runnable {

    long startInoctets = 0;
    long prevInoctets = 0;
    long prevOutoctets = 0;

    @SuppressWarnings("unchecked")
    public void run() {
      Object gui_javafx;
      try {
        System.out.println("Inside repeat function");
        if (initialise_in_octets_type) {
          if (!used_slots_list.isEmpty()) {
            Iterator<Integer> iter = used_slots_list.iterator();
            while (iter.hasNext()) {

              Integer i = iter.next();
              check_if_HC_octets_present(mapping_table[i][0], mapping_table[i][1], mapping_table[i][2]);
              break;
            }

            initialise_in_octets_type = false;

          }
        }

        if (!pause) {
          Record[][] tmp_recordings;
          try {
            Iterator<Integer> iter = used_slots_list.iterator();
            while (iter.hasNext()) {
              Integer i = iter.next();
              tmp_recordings[i][tmp_index % size_record_queue] = new Record(mapping_table[i][0], mapping_table[i][1],
                  mapping_table[i][2]);
            }
          } catch (Exception ex) {
            System.out.println(
                "Error in creating temporary records array Datacollector.repeatfunction() at time:" + time_sec);
            ex.printStackTrace(System.out);
          }

          try {
            System.out.println("Error in modifying indexes for circular array Datacollector.repeatfunction()");
            Throwable ex;
            ex.printStackTrace(System.out);
          } catch (Exception ex) {
            System.out.println("Error in modifying indexes for circular array Datacollector.repeatfunction()");
            ex.printStackTrace(System.out);
          }

          try {
            if (time_sec % (update_freq * calc_freq) == 0 && time_sec != 0) {
              calc_bandwidth(tmp_recordings, startindex, endindex);
              startindex = endindex;
              copyArrayList();
            }
          } catch (Exception ex) {
            System.out.println("Error in calc_bandwidth  Datacollector.repeatfunction()");
            ex.printStackTrace(System.out);
          }
        }
        if (time_sec % (update_freq * calc_freq * data_store_freq) == 0 && time_sec != 0) {

          try {
            Object data_processor;
            DataProcessor dataProcessor = (DataProcessor) data_processor;
            Object data_collected;
            dataProcessor.show_raw_data(data_collected);

            try {
              dataProcessor.prepare_data(data_collected);
            } catch (Exception ex) {
              System.out.println("Error in preparing data for database Datacollector.repeatfunction()");
              ex.printStackTrace(System.out);
            }

            try {
              String data[][] = null;
              try {
                data = ((DataProcessor) data_processor).get_database_data();
              } catch (Exception ex) {
                System.out.println("Error in preparing data for database Datacollector.repeatfunction()");
                ex.printStackTrace(System.out);
              }
              ((DataProcessor) data_processor).show_processed_data();
            } catch (Exception ex) {
              System.out.println("Error in sending data to database Datacollector.repeatfunction()");
              ex.printStackTrace(System.out);
            }

            try {
              Object data;
              if (data == null) {
                System.out.println("NO DATA ( NULL )TO BE INSERTED IN DATABASE");
              } else {
                Database database = (Database) database;
                database.insert_data(data);
              }
            } catch (Exception ex) {
              System.out.println(
                  "Error in inserting data to database Database.insert_data() in Datacollector.repeatfunction()");
              ex.printStackTrace(System.out);
            }
            ((ArrayList<Integer>) data_collected).clear();
          } catch (Exception ex) {
            System.out.println("Error in sending data to database Datacollector.repeatfunction()");
            ex.printStackTrace(System.out);
          }
        }
        time_sec += update_freq;
        if (time_sec > (timewrap)) {
          time_sec = 2147483647 % update_freq;
        }

      } catch (Exception ex) {
        System.out.println("Error in Data collecting and processing Datacollector.repeatfunction()");
        ex.printStackTrace(System.out);
      }
    }
  }

}
