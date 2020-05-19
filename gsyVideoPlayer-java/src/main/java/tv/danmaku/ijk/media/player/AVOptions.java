package tv.danmaku.ijk.media.player;

import java.util.ArrayList;
import java.util.List;

public class AVOptions {

    public final List<Option> options = new ArrayList<>();

    public void setOption(int category, String name, String value) {
        options.add(new Option(category, name, value));
    }

    public void setOption(int category, String name, long value) {
        options.add(new Option(category, name, value));
    }


    public static class Option {
        public int category;
        public String name;
        public String value;
        public long _value;

        private Option(int category, String name, String value) {
            this.category = category;
            this.name = name;
            this.value = value;
        }

        private Option(int category, String name, long _value) {
            this.category = category;
            this.name = name;
            this._value = _value;
        }
    }
}
