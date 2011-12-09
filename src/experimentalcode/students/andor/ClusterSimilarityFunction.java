package experimentalcode.students.andor;

import de.lmu.ifi.dbs.elki.data.Cluster;
import de.lmu.ifi.dbs.elki.data.type.SimpleTypeInformation;
import de.lmu.ifi.dbs.elki.database.Database;
import de.lmu.ifi.dbs.elki.database.ids.ArrayModifiableDBIDs;
import de.lmu.ifi.dbs.elki.database.ids.DBID;
import de.lmu.ifi.dbs.elki.database.ids.DBIDUtil;
import de.lmu.ifi.dbs.elki.distance.distancevalue.IntegerDistance;
import de.lmu.ifi.dbs.elki.distance.similarityfunction.AbstractPrimitiveSimilarityFunction;

public class ClusterSimilarityFunction<C extends Cluster<?>> extends AbstractPrimitiveSimilarityFunction<C, IntegerDistance> {
  /**
   * @param rep
   */
  public ClusterSimilarityFunction(Database database) {
    super();
  }

  @Override
  public IntegerDistance similarity(C o1, C o2) {
    ArrayModifiableDBIDs data1 = DBIDUtil.newArray(o1.getIDs());
    ArrayModifiableDBIDs data2 = DBIDUtil.newArray(o2.getIDs());
    
    data1.sort();
    data2.sort();
    int intersection = 0;
    for(DBID id: data1){
      if(data2.binarySearch(id)>=0){
        intersection++;
      }
    }
    return new IntegerDistance(intersection);
  }

  @Override
  public SimpleTypeInformation<? super Cluster<?>> getInputTypeRestriction() {
    return new SimpleTypeInformation<Cluster<?>>(Cluster.class);
  }

  @Override
  public IntegerDistance getDistanceFactory() {
    return IntegerDistance.FACTORY;
  }
}