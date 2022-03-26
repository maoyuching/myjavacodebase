package hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

public class MapClass extends Mapper<LongWritable, Text, Text, IntWritable> {

    // 可写对象，暂时不知道是干嘛的
    private final static IntWritable one = new IntWritable(1);
    // 文本 顾名思义，估计是 字符串之类的东西
    private Text word = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        // 用空格 split 开
        StringTokenizer st = new StringTokenizer(line," ");
        while(st.hasMoreTokens()){
            word.set(st.nextToken());
            context.write(word,one);
        }
    }
}
