package hadoop;
import java.io.IOException;
import java.util.Iterator;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ReduceClass extends Reducer{

    protected void reduce(Text key, Iterable values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        Iterator<IntWritable> valuesIt = values.iterator();
        while(valuesIt.hasNext()){
            sum = sum + valuesIt.next().get();
        }
        context.write(key, new IntWritable(sum));
    }
}

