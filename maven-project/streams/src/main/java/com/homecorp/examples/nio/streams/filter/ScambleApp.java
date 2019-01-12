/**
 * 
 */
package com.homecorp.examples.nio.streams.filter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * @author benlm
 *
 */
public class ScambleApp {

  /**
   * @param args
   */
  public static void main(String[] args) {
    if (args.length != 2) {
      throw new IllegalArgumentException("usage: java ScrambleApp srcPath destPath");
    }

    try (FileInputStream fis = new FileInputStream(args[0]);
        FileOutputStream fos = new FileOutputStream(args[1]);
        ScrambledOutputStream sos = new ScrambledOutputStream(fos, createMap())) {
      int dataByte;
      // Here we transfer from input to output using the filter
      while ((dataByte = fis.read()) != -1) {
        sos.write(dataByte);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  private static int[] createMap() {
    int[] result = new int[256];
    for (int i = 0; i < result.length; i++) {
      result[i] = i;
    }
    Random r = new Random(0);
    for (int i = 0; i < result.length; i++) {
      int n = r.nextInt(result.length);
      int temp = result[i];
      result[i] = n;
      result[n] = temp;
    }
    return result;
  }

}
