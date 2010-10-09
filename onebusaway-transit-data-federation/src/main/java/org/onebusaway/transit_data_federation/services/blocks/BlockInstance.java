package org.onebusaway.transit_data_federation.services.blocks;

import org.onebusaway.gtfs.model.calendar.LocalizedServiceId;
import org.onebusaway.transit_data_federation.services.tripplanner.BlockConfigurationEntry;
import org.onebusaway.transit_data_federation.services.tripplanner.BlockEntry;
import org.onebusaway.transit_data_federation.services.tripplanner.StopTimeEntry;
import org.onebusaway.transit_data_federation.services.tripplanner.TripInstance;

/**
 * A block instance is the combination of a {@link BlockEntry} and a service
 * date for which that block is active. The "service date" is the
 * "midnight time" from which the {@link StopTimeEntry} entries are relative.
 * Blocks are slightly more complicated than {@link TripInstance}, because a
 * block can be composed of trips with different service ids, not all which are
 * necessarily active on a given service date.
 * 
 * @author bdferris
 * @see BlockEntry
 * @see LocalizedServiceId
 */
public class BlockInstance {

  private final BlockConfigurationEntry _block;

  private final long _serviceDate;

  public BlockInstance(BlockConfigurationEntry block, long serviceDate) {
    if (block == null)
      throw new IllegalArgumentException();
    _block = block;
    _serviceDate = serviceDate;
  }

  public BlockConfigurationEntry getBlock() {
    return _block;
  }

  /**
   * The service date that the block instance is operating. This is the
   * "midnight" time relative to the stop times for the trip.
   * 
   * @return the service date on which the block is operating (Unix-time)
   */
  public long getServiceDate() {
    return _serviceDate;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + _block.hashCode();
    result = prime * result + (int) (_serviceDate ^ (_serviceDate >>> 32));
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    BlockInstance other = (BlockInstance) obj;
    if (!_block.equals(other._block))
      return false;
    if (_serviceDate != other._serviceDate)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return _block.toString() + " " + _serviceDate;
  }

}