package hadoop;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


public class WordCount extends Configured implements Tool{

    public static void main(String[] args) throws Exception{
        int exitCode = ToolRunner.run(new WordCount(), args);
        System.exit(exitCode);
    }
    public int run(String[] args) throws Exception {
        // 输入参数,
        if (args.length != 2) {
            System.err.printf("Usage: %s needs two arguments, input and output files\n", getClass().getSimpleName());
            return -1;
        }
        Job job = new Job();
        job.setJarByClass(this.getClass());
        job.setJobName("WordCounter");
        // 设置任务的输入, path在hadoop中表示一个文件
        FileInputFormat.addInputPath(job, new Path(args[0]));
        // 设置任务的输出, path在hadoop中表示一个文件
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        // 输出 key is str, value is int
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        // 把kv打成文本输出
        job.setOutputFormatClass(TextOutputFormat.class);
        // set mapper and reducer
        job.setMapperClass(MapClass.class);
        job.setReducerClass(ReduceClass.class);
        // running job......
        int returnValue = job.waitForCompletion(true) ? 0:1;
        if(job.isSuccessful()) {
            System.out.println("Job was successful");
        } else if(!job.isSuccessful()) {
            System.out.println("Job was not successful");
        }
        return returnValue;
    }
}