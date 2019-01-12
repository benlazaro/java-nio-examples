/**
 * 
 */
package com.homecorp.examples.nio.streams.filter;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author benlm
 *
 */
public class ScrambledOutputStream extends FilterOutputStream {

  private final int[] map;

  public ScrambledOutputStream(OutputStream out, int[] map) {
    super(out);
    if (map == null) {
      throw new IllegalArgumentException("map cannot be null");
    } else if (map.length != 256) {
      throw new IllegalArgumentException("map length should be 256");
    }
    this.map = map;
  }

  @Override
  public void write(int b) throws IOException {
    super.write(map[b]);
  }

}
