public class CountTriangle extends Configured implements Tool {

    /**
     * Maps <Long, Text> to <Long, Long>
     */
    public static class MapperLongLong extends Mapper<LongWritable, Text, LongWritable, LongWritable> {
        
        @Override
        public void map(LongWritable key, Text value, Context context) {
            String[] vals = value.toString().split("\\s+");

            if (vals.length > 1) {
                long val1 = Long.parseLong(vals[0]);
                long val2 = Long.parseLong(vals[1]);

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
        Text outKey = new Text();
        Text outValue = new Text();

        @Override
        public void reduce(LongWritable key, Iterable<LongWritable> values, Context context) {
            Iterator<LongWritable> it = values.iterator();

            // TODO: Find another syntax to check while iterator is not done.
            while (it.hasNext()) {
                long v = it.next().get();

                outKey.set(key.toString() + ',' + Long.toString(v));
                outValue.set(dollar.toString);
                context.write(outKey, outValue);

                if (it.hasNext()) {
                    long v_next =  it.next().get();
                    
                    outKey.set(key.toString() + ',' + Long.toString(v_next));
                    outValue.set(dollar.toString());
                    context.write(outKey, outValue);

                    // Check for triangles
                    if (v != v_next) {
                        outKey.set(Long.toString(v) + ',' + Long.toString(v_next));
                        outValue.set(key.toString());
                        context.write(outKey, outValue);
                    }
                }
            }
        } 
    }

    public static class MapperTextLong extends Mapper<LongWritable, Text, Text, LongWritable> {
        
        @Override
        public void map(LongWritable key, Text value, Context context) {
            String[] vals = value.toString().split("\\s+");

            if (vals.length > 1) {
                context.write(new Text(vals[0]), new LongWritable(Long.parseLong(vals[1])));
            }
        }
    }

    public static class TextLongReducer extends Reducer<Text, LongWritable, LongWritable, LongWritable> {
        private long dollar = -1;
        private int count = 0;
        boolean isDollar  = false;

        @Override
        public void reduce(Text key, Iterable<LongWritable> values, Context context) {
            Iterator<LongWritable> it = values.iterator();

            while (it.hasNext()) {
                long v = it.next().get();

                if (v != dollar) {
                    count += 1
                }

                isDollar |= v == dollar;
            }
        }
    }

    public static class SumReducer extends Reducer<Text, LongWritable, LongWritable, NullWritable> {
        
        @Override
        public void reduce(Text key, Iterable<LongWritable> values, Context context) {
            long sum = 0;
            Iterator<LongWritable> it = values.iterator();
            while (it.hasNext()) {
                sum += vs.next().get();
            }
            context.write(new LongWritable(sum), NullWritable.get());
        }
    }

    public static void main(String[] args) {
        int status = 
    }
}