import lombok.SneakyThrows;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.AggregateOperator;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.util.Collector;

import java.util.Arrays;
import java.util.List;

/**
 * 批处理 word count
 */
public class WordCount {
    @SneakyThrows
    public static void main(String[] args) {

        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        String inputPath = "C:\\Users\\Administrator\\Documents\\Code\\Java\\Demo\\myjavacodebase\\flink-learning\\src\\main\\resources\\hello.txt";
        DataSource<String> input = env.readTextFile(inputPath);

        DataSet<Tuple2<String, Integer>> result =
                input.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
                    @Override
                    public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {
                        String[] words = s.split(" ");
                        for (String w : words) {
                            collector.collect(new Tuple2<>(w, 1));
                        }
                    }
                }).groupBy(0).sum(1);

        result.collect().stream().forEach(System.out::println);
    }
}
