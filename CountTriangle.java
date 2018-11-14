public class CountTriangle extends Configured implements Tool {

    /**
     * Maps <Long, Text> to <Long, Long>
     */
    public static class MapperLongLong extends Mapper<LongWritable, Text, LongWritable, LongWritable> {
        
        @Override
        public void map(LongWritable key, Text value, Context context) {
            String[] val = value.toString().split("\\s+");

            if (val.length > 1) {
                long val1 = Long.parseLong(val[0]);
                long val2 = Long.parseLong(val[1]);

                // TODO: min(val1,val2), max(val1,val2)
                context.write(new LongWritable(val1), new LongWritable(val2));
            }
        }
    }

    /**
     * Reduces <Long, Long> to <Text, Text> for writing to file
     */
    public static class LongLongReducer extends Reducer<LongWritable, LongWritable, Text, Text> {
        private LongWritable dollar = new LongWritable(-1);

        public void reduce(LongWritable key, Iterable<LongWritable> values, Context context) {
            Iterator<LongWritable> it = values.iterator();

            // TODO: Find another syntax to check while iterator is not done.
            while(it.hasNext()) {
                
            }
        } 
    }

    public static class MapperTextLong extends Mapper<LongWritable, Text, Text, LongWritable> {
        
    }

    public static void main(String[] args) {
        int status = 
    }
}