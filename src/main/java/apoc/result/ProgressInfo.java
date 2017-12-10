package apoc.result;

/**
 * @author mh
 * @since 22.05.16
 */
public class ProgressInfo {
    public static final ProgressInfo EMPTY = new ProgressInfo(null, null, null);
    public final String file;
    public String source;
    public final String format;
    public long nodes;
    public long relationships;
    public long properties;
    public long time;
    public long rows;
    public long batchSize = -1;
    public long batches;
    public boolean done;

    public ProgressInfo(String file, String source, String format) {
        this.file = file;
        this.source = source;
        this.format = format;
    }

    @Override
    public String toString() {
        return String.format("nodes = %d rels = %d properties = %d", nodes, relationships, properties);
    }

    public ProgressInfo update(long nodes, long relationships, long properties) {
        this.nodes += nodes;
        this.relationships += relationships;
        this.properties += properties;
        return this;
    }

    public ProgressInfo updateTime(long start) {
        this.time = System.currentTimeMillis() - start;
        return this;
    }
    public ProgressInfo done(long start) {
        this.done = true;
        return updateTime(start);
    }

    public void nextRow() {
        this.rows++;
    }
}
