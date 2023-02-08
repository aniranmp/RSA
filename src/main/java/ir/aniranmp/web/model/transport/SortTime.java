package ir.aniranmp.web.model.transport;

import lombok.Data;

public class SortTime {
    private final long arrayLength;
    private final String sortType;
    private final long time;

    private SortTime(SortTimeBuilder builder) {
        this.arrayLength = builder.arrayLength;
        this.sortType = builder.sortType;
        this.time = builder.time;
    }

    public long getArrayLength() {
        return arrayLength;
    }

    public String getSortType() {
        return sortType;
    }

    public long getTime() {
        return time;
    }

    public static class SortTimeBuilder {
        private final long arrayLength;
        private String sortType;
        private long time;


        public SortTimeBuilder(long arrayLength) {
            this.arrayLength = arrayLength;
        }


        public SortTimeBuilder sortType(String sortType) {
            this.sortType = sortType;
            return this;
        }

        public SortTimeBuilder time(long time) {
            this.time = time;
            return this;
        }

        public SortTime build() {
            SortTime sortTime = new SortTime(this);
            return sortTime;
        }
    }
}
